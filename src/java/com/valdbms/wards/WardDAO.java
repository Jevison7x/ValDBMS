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

    public static Ward getWard(String wardName, String lga)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Ward.WARDS + " WHERE " + Ward.WARD + " = ? AND " + Ward.LGA + " = ?";
            Query q = em.createNativeQuery(sql, Ward.class);
            q.setParameter(1, wardName);
            q.setParameter(2, lga);
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
}
