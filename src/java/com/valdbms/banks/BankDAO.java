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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since Jun 7, 2020 1:01:47 AM
 */
public class BankDAO
{
    public static List<String> getDistinctBanks()
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT DISTINCT " + Bank.BANK + " FROM " + Bank.BANKS;
            Query query = em.createNativeQuery(sql);
            List<Object> result = query.getResultList();
            List<String> banksList = new ArrayList<>();
            for(Object obj : result)
                banksList.add((String)obj);
            return banksList;
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            return new ArrayList<>();
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
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
