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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since May 31, 2020 9:09:13 PM
 */
public class LGA_DAO
{
    public static List<String> getDistictLGAs() throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT DISTINT " + Ward.LGA + " FROM " + Ward.WARDS;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<String> lgaList = new ArrayList<>();
            while(rs.next())
                lgaList.add(rs.getString(Ward.LGA));
            return lgaList;
        }
    }

    public static List<Ward> getWards(String lga)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Ward.WARDS + " WHERE " + Ward.LGA + " = ?";
            Query q = em.createNativeQuery(sql, Ward.class);
            q.setParameter(1, lga);
            List<Ward> wards = q.getResultList();
            return wards;
        }
        finally
        {
            em.close();
        }
    }
}