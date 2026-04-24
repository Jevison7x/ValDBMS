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
 * @since 16 Apr 2026 10:58:00
 */
@WebServlet("/import-polling-units")
public class PollingUnitImportServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private List<Integer> parseWardIds(String wardIdsParam)
    {
        List<Integer> wardIds = new ArrayList<>();
        if(wardIdsParam == null || wardIdsParam.trim().isEmpty())
            return wardIds;

        String[] parts = wardIdsParam.split(",");
        for(String part : parts)
        {
            String value = part.trim();
            if(!value.isEmpty())
                wardIds.add(Integer.parseInt(value));
        }

        return wardIds;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.setBufferSize(1024);
        PrintWriter out = response.getWriter();
        out.println("Starting polling unit import...");
        out.flush();
        response.flushBuffer();

        try(Connection conn = DBConnection.getConnection())
        {
            out.println("Database connection opened.");
            out.flush();
            response.flushBuffer();

            PollingUnitImporter importer = new PollingUnitImporter();
            out.println("PollingUnitImporter created.");
            out.flush();
            response.flushBuffer();

            String wardIdsParam = request.getParameter("wardIds");
            if(wardIdsParam != null && !wardIdsParam.trim().isEmpty())
            {
                List<Integer> wardIds = parseWardIds(wardIdsParam);
                out.println("Running targeted import for ward IDs: " + wardIds);
                out.flush();
                response.flushBuffer();
                importer.importPollingUnitsByWardIds(conn, wardIds, out);
            }
            else if("true".equalsIgnoreCase(request.getParameter("missingOnly")))
            {
                out.println("Running import for wards without polling units only.");
                out.flush();
                response.flushBuffer();
                importer.importMissingPollingUnits(conn, out);
            }
            else
                importer.importAllPollingUnits(conn, out);

            out.println("Polling unit import completed.");
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
