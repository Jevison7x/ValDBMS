/*
 * Copyright (c) 2018, Xyneex Technologies. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are not meant to edit or modify this source code unless you are
 * authorized to do so.
 *
 * Please contact Xyneex Technologies, #1 Orok Orok Street, Calabar, Nigeria.
 * or visit www.xyneex.com if you need additional information or have any
 * questions.
 */
package com.valdbms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jevison7x
 * @since 16 Apr 2026 05:26:49
 */
public class WardImporter
{
    private void log(PrintWriter out, String message)
    {
        out.println(message);
        out.flush();
    }

    public List<LgaDto> getAllLgas(Connection conn) throws SQLException
    {
        List<LgaDto> list = new ArrayList<>();

        String sql = "SELECT lg.id, lg.name, s.name AS state_name "
                + "FROM local_governments lg "
                + "JOIN states s ON lg.state_id = s.id";

        try(PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery())
        {
            while(rs.next())
            {
                LgaDto dto = new LgaDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setStateName(rs.getString("state_name"));
                list.add(dto);
            }
        }

        return list;
    }

    public List<LgaDto> getLgasByIds(Connection conn, List<Integer> lgaIds) throws SQLException
    {
        if(lgaIds == null || lgaIds.isEmpty())
            return Collections.emptyList();

        List<LgaDto> list = new ArrayList<>();
        StringBuilder placeholders = new StringBuilder();
        for(int i = 0; i < lgaIds.size(); i++)
        {
            if(i > 0)
                placeholders.append(", ");
            placeholders.append("?");
        }

        String sql = "SELECT lg.id, lg.name, s.name AS state_name "
                + "FROM local_governments lg "
                + "JOIN states s ON lg.state_id = s.id "
                + "WHERE lg.id IN (" + placeholders + ") "
                + "ORDER BY s.name, lg.name";

        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            for(int i = 0; i < lgaIds.size(); i++)
                ps.setInt(i + 1, lgaIds.get(i));

            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next())
                {
                    LgaDto dto = new LgaDto();
                    dto.setId(rs.getInt("id"));
                    dto.setName(rs.getString("name"));
                    dto.setStateName(rs.getString("state_name"));
                    list.add(dto);
                }
            }
        }

        return list;
    }

    public String fetchWards(String stateName, String lgaName, PrintWriter out) throws Exception
    {
        String encodedState = URLEncoder.encode(stateName, StandardCharsets.UTF_8);
        String encodedLga = URLEncoder.encode(lgaName, StandardCharsets.UTF_8);

        String url = "https://inecnigeria.org/wp-content/themes/rishi/custom/views/wardView.php"
                + "?lga_id=" + encodedLga
                + "&state_id=" + encodedState;
        log(out, "URL: " + url);

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .build();
        log(out, "Finished building HttpClient...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(java.time.Duration.ofSeconds(15))
                .header("Accept", "*/*")
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();

        log(out, "Finished building HttpRequest...");
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log(out, "Finished getting HttpResponse...");
        log(out, "HTTP Status: " + response.statusCode());
        log(out, "Response Body: " + response.body());
        return response.body();
    }

    public List<String> parseWards(String json) throws Exception
    {
        if(json == null || json.trim().isEmpty())
            return new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> list = mapper.readValue(json, new TypeReference<List<Map<String, String>>>()
        {
        });
        List<String> wards = new ArrayList<>();
        for(Map<String, String> item : list)
            wards.add(item.get("ward"));
        return wards;
    }

    public void saveWards(Connection conn, int lgaId, List<String> wards) throws SQLException
    {
        String sql = "INSERT INTO wards (lga_id, name) VALUES (?, ?) ON DUPLICATE KEY UPDATE name = VALUES(name)";
        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            for(String ward : wards)
            {
                ps.setInt(1, lgaId);
                ps.setString(2, ward);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public void importAllWards(Connection conn, PrintWriter out) throws Exception
    {
        List<LgaDto> lgas = getAllLgas(conn);
        importWards(conn, lgas, out);
    }

    public void importWardsByIds(Connection conn, List<Integer> lgaIds, PrintWriter out) throws Exception
    {
        List<LgaDto> lgas = getLgasByIds(conn, lgaIds);
        importWards(conn, lgas, out);
    }

    private void importWards(Connection conn, List<LgaDto> lgas, PrintWriter out) throws Exception
    {
        for(LgaDto lga : lgas)
            try
            {
                log(out, "Processing: " + lga.getStateName() + " - " + lga.getName());

                String json = fetchWards(lga.getStateName().toUpperCase(), lga.getName().toUpperCase(), out);
                List<String> wards = parseWards(json);
                log(out, "Parsed " + wards.size() + " wards.");
                log(out, "About to save wards...");
                try
                {
                    saveWards(conn, lga.getId(), wards);
                    log(out, "Saved wards successfully.");
                }
                catch(Exception e)
                {
                    log(out, "Error while saving wards:");
                    e.printStackTrace(out);
                    out.flush();
                }
                log(out, "Finished saving wards...");
                Thread.sleep(200); // be polite, you're scraping, not attacking

            }
            catch(Exception e)
            {
                System.err.println("Failed for: " + lga.getName());
                e.printStackTrace(out);
                out.flush();
            }
    }
}
