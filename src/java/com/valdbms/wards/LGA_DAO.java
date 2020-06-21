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
package com.valdbms.wards;

import com.valdbms.database.DBConfiguration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jevison7x
 * @since May 31, 2020 9:09:13 PM
 */
public class LGA_DAO
{
    public static List<String> getDistinctLGAs(String state) throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT DISTINCT " + Ward.LGA + " FROM " + Ward.WARDS + " WHERE " + Ward.STATE + " = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, state);
            ResultSet rs = pst.executeQuery();
            List<String> lgaList = new ArrayList<>();
            while(rs.next())
                lgaList.add(rs.getString(Ward.LGA));
            return lgaList;
        }
    }

    public static int getLgasTotalCount() throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT DISTINCT " + Ward.LGA + " FROM " + Ward.WARDS;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.last();
            return rs.getRow();
        }
    }

    public static Map<String, Integer> getLGAWardsMap()
    {
        List<Ward> allWards = WardDAO.getAllWards();
        Map<String, Integer> lgaWardsMap = new HashMap<>();
        for(Ward ward : allWards)
        {
            String lga = ward.getLga();
            if(lgaWardsMap.containsKey(lga))
            {
                int noOfWards = lgaWardsMap.get(lga);
                noOfWards++;
                lgaWardsMap.put(lga, noOfWards);
            }
            else
                lgaWardsMap.put(lga, 1);
        }
        return lgaWardsMap;
    }
}
