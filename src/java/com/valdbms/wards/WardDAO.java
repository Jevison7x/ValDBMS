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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Ugimson
 * @since May 31, 2020 9:08:11 PM
 */
public class WardDAO
{
    public static void createNewWard(Ward ward)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(ward);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static List<Ward> getWards(String state, String lga)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Ward.WARDS + " WHERE " + Ward.STATE + " = ? AND " + Ward.LGA + " = ?";
            Query q = em.createNativeQuery(sql, Ward.class);
            q.setParameter(1, state);
            q.setParameter(2, lga);
            List<Ward> wards = q.getResultList();
            return wards;
        }
        finally
        {
            em.close();
        }
    }

    public static Ward getWard(String state, String lga, String wardName)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Ward.WARDS + " "
                    + "WHERE " + Ward.WARD + " = ? "
                    + "AND " + Ward.LGA + " = ? "
                    + "AND " + Ward.STATE + " = ?";
            Query q = em.createNativeQuery(sql, Ward.class);
            q.setParameter(1, wardName);
            q.setParameter(2, lga);
            q.setParameter(3, state);
            Ward ward = (Ward)q.getSingleResult();
            return ward;
        }
        catch(NoResultException nre)
        {
            return null;
        }
        finally
        {
            em.close();
        }
    }

    public static int getWardsTotalCount()
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT COUNT(*) FROM " + Ward.WARDS;
            Query query = em.createNativeQuery(sql);
            Object result = query.getSingleResult();
            return ((Number)result).intValue();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            return 0;
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
        }
    }

    public static List<Ward> getAllWards()
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Ward.WARDS;
            Query q = em.createNativeQuery(sql, Ward.class);
            List<Ward> allWards = q.getResultList();
            return allWards;
        }
        finally
        {
            em.close();
        }
    }
}
