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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jevison7x
 * @since 16 Apr 2026 06:38:49
 */
@WebServlet("/import-wards")
public class WardImportServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static final String FAILED_LGA_IDS = "6,10,142,389";

    private List<Integer> parseLgaIds(String lgaIdsParam)
    {
        List<Integer> lgaIds = new ArrayList<>();
        if(lgaIdsParam == null || lgaIdsParam.trim().isEmpty())
            return lgaIds;

        String[] parts = lgaIdsParam.split(",");
        for(String part : parts)
        {
            String value = part.trim();
            if(!value.isEmpty())
                lgaIds.add(Integer.parseInt(value));
        }

        return lgaIds;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.setBufferSize(1024);
        PrintWriter out = response.getWriter();
        out.println("Starting ward import...");
        out.flush();
        response.flushBuffer();

        try(Connection conn = DBConnection.getConnection())
        {
            out.println("Database connection opened.");
            out.flush();
            response.flushBuffer();

            WardImporter importer = new WardImporter();
            out.println("WardImporter created.");
            out.flush();
            response.flushBuffer();

            String lgaIdsParam = request.getParameter("lgaIds");
            if(lgaIdsParam != null && !lgaIdsParam.trim().isEmpty())
            {
                List<Integer> lgaIds = parseLgaIds(lgaIdsParam);
                out.println("Running targeted import for LGA IDs: " + lgaIds);
                out.flush();
                response.flushBuffer();
                importer.importWardsByIds(conn, lgaIds, out);
            }
            else if("true".equalsIgnoreCase(request.getParameter("retryLastFailures")))
            {
                List<Integer> lgaIds = parseLgaIds(FAILED_LGA_IDS);
                out.println("Retrying last confirmed failures for LGA IDs: " + lgaIds);
                out.flush();
                response.flushBuffer();
                importer.importWardsByIds(conn, lgaIds, out);
            }
            else
                importer.importAllWards(conn, out);

            out.println("Import completed. Go celebrate or something.");
            out.flush();
            response.flushBuffer();

        }
        catch(Throwable e)
        {
            e.printStackTrace(out);
            out.flush();
            response.flushBuffer();
        }
    }
}
