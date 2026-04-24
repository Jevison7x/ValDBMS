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
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jevison7x
 * @since 16 Apr 2026 10:58:00
 */
public class PollingUnitImporter
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private void log(PrintWriter out, String message)
    {
        out.println(message);
        out.flush();
    }

    public List<WardInfoDto> getAllWards(Connection conn) throws SQLException
    {
        List<WardInfoDto> list = new ArrayList<>();

        String sql = "SELECT w.id, w.name, lg.name AS lga_name, s.name AS state_name "
                + "FROM wards w "
                + "JOIN local_governments lg ON w.lga_id = lg.id "
                + "JOIN states s ON lg.state_id = s.id "
                + "ORDER BY s.name, lg.name, w.name";

        try(PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery())
        {
            while(rs.next())
            {
                WardInfoDto dto = new WardInfoDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setLgaName(rs.getString("lga_name"));
                dto.setStateName(rs.getString("state_name"));
                list.add(dto);
            }
        }

        return list;
    }

    public List<WardInfoDto> getWardsByIds(Connection conn, List<Integer> wardIds) throws SQLException
    {
        if(wardIds == null || wardIds.isEmpty())
            return Collections.emptyList();

        List<WardInfoDto> list = new ArrayList<>();
        StringBuilder placeholders = new StringBuilder();
        for(int i = 0; i < wardIds.size(); i++)
        {
            if(i > 0)
                placeholders.append(", ");
            placeholders.append("?");
        }

        String sql = "SELECT w.id, w.name, lg.name AS lga_name, s.name AS state_name "
                + "FROM wards w "
                + "JOIN local_governments lg ON w.lga_id = lg.id "
                + "JOIN states s ON lg.state_id = s.id "
                + "WHERE w.id IN (" + placeholders + ") "
                + "ORDER BY s.name, lg.name, w.name";

        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            for(int i = 0; i < wardIds.size(); i++)
                ps.setInt(i + 1, wardIds.get(i));

            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next())
                {
                    WardInfoDto dto = new WardInfoDto();
                    dto.setId(rs.getInt("id"));
                    dto.setName(rs.getString("name"));
                    dto.setLgaName(rs.getString("lga_name"));
                    dto.setStateName(rs.getString("state_name"));
                    list.add(dto);
                }
            }
        }

        return list;
    }

    public List<WardInfoDto> getWardsWithoutPollingUnits(Connection conn) throws SQLException
    {
        List<WardInfoDto> list = new ArrayList<>();

        String sql = "SELECT w.id, w.name, lg.name AS lga_name, s.name AS state_name "
                + "FROM wards w "
                + "JOIN local_governments lg ON w.lga_id = lg.id "
                + "JOIN states s ON lg.state_id = s.id "
                + "LEFT JOIN polling_units pu ON pu.ward_id = w.id "
                + "GROUP BY w.id, w.name, lg.name, s.name "
                + "HAVING COUNT(pu.id) = 0 "
                + "ORDER BY s.name, lg.name, w.name";

        try(PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery())
        {
            while(rs.next())
            {
                WardInfoDto dto = new WardInfoDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setLgaName(rs.getString("lga_name"));
                dto.setStateName(rs.getString("state_name"));
                list.add(dto);
            }
        }

        return list;
    }

    public String fetchPollingUnits(String stateName, String lgaName, String wardName, PrintWriter out) throws Exception
    {
        String encodedState = URLEncoder.encode(stateName, StandardCharsets.UTF_8);
        String encodedLga = URLEncoder.encode(lgaName, StandardCharsets.UTF_8);
        String encodedWard = URLEncoder.encode(wardName, StandardCharsets.UTF_8);

        String url = "https://inecnigeria.org/wp-content/themes/rishi/custom/views/pollingView.php"
                + "?state_id=" + encodedState
                + "&lga_id=" + encodedLga
                + "&ward_id=" + encodedWard;
        log(out, "URL: " + url);

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        log(out, "Finished building HttpClient...");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .header("Accept", "*/*")
                .header("User-Agent", "Mozilla/5.0")
                .header("X-Requested-With", "XMLHttpRequest")
                .GET()
                .build();

        log(out, "Finished building HttpRequest...");
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log(out, "Finished getting HttpResponse...");
        log(out, "HTTP Status: " + response.statusCode());
        log(out, "Response Body: " + response.body());
        return response.body();
    }

    public List<Map<String, String>> parsePollingUnits(String json) throws Exception
    {
        if(json == null || json.trim().isEmpty())
            return new ArrayList<>();

        return OBJECT_MAPPER.readValue(json, new TypeReference<List<Map<String, String>>>()
        {
        });
    }

    public void savePollingUnits(Connection conn, int wardId, List<Map<String, String>> pollingUnits) throws SQLException
    {
        String sql = "INSERT INTO polling_units (ward_id, code, name) "
                + "SELECT ?, ?, ? "
                + "WHERE NOT EXISTS ("
                + "SELECT 1 FROM polling_units WHERE ward_id = ? AND code <=> ? AND name <=> ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql))
        {
            for(Map<String, String> pollingUnit : pollingUnits)
            {
                String code = pollingUnit.get("delim");
                String name = pollingUnit.get("pu");

                ps.setInt(1, wardId);
                ps.setString(2, code);
                ps.setString(3, name);
                ps.setInt(4, wardId);
                ps.setString(5, code);
                ps.setString(6, name);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public void importAllPollingUnits(Connection conn, PrintWriter out) throws Exception
    {
        importPollingUnits(conn, getAllWards(conn), out);
    }

    public void importPollingUnitsByWardIds(Connection conn, List<Integer> wardIds, PrintWriter out) throws Exception
    {
        importPollingUnits(conn, getWardsByIds(conn, wardIds), out);
    }

    public void importMissingPollingUnits(Connection conn, PrintWriter out) throws Exception
    {
        importPollingUnits(conn, getWardsWithoutPollingUnits(conn), out);
    }

    private void importPollingUnits(Connection conn, List<WardInfoDto> wards, PrintWriter out) throws Exception
    {
        for(WardInfoDto ward : wards)
            try
            {
                log(out, "Processing: " + ward.getStateName() + " - " + ward.getLgaName() + " - " + ward.getName());

                String json = fetchPollingUnits(
                        ward.getStateName().toUpperCase(),
                        ward.getLgaName().toUpperCase(),
                        ward.getName().toUpperCase(),
                        out);
                List<Map<String, String>> pollingUnits = parsePollingUnits(json);
                log(out, "Parsed " + pollingUnits.size() + " polling units.");
                log(out, "About to save polling units...");
                savePollingUnits(conn, ward.getId(), pollingUnits);
                log(out, "Saved polling units successfully.");
                log(out, "Finished saving polling units...");
                Thread.sleep(200);
            }
            catch(Exception e)
            {
                System.err.println("Failed for ward: " + ward.getName());
                e.printStackTrace(out);
                out.flush();
            }
    }
}
