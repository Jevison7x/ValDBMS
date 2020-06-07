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
package com.valdbms.banks;

import com.valdbms.database.DBConfiguration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Jevison7x
 * @since Jun 7, 2020 1:01:47 AM
 */
public class BankDAO
{
    public static List<String> getDistinctBanks() throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT DISTINCT " + Bank.BANK + " FROM " + Bank.BANKS;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<String> banksList = new ArrayList<>();
            while(rs.next())
                banksList.add(rs.getString(Bank.BANK));
            return banksList;
        }
    }

    public static void createBank(Bank bank)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(bank);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static Bank getBank(String bankName)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            Bank bank = em.find(Bank.class, bankName);
            return bank;
        }
        finally
        {
            em.close();
        }
    }
}
