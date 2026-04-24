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
package com.valdbms.pollingunits;

import com.valdbms.database.DBConfiguration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since 17 Apr 2026 12:00:00
 */
public class PollingUnitDAO
{
    public static void createNewPollingUnit(PollingUnit pollingUnit)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(pollingUnit);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static int getPollingUnitsTotalCount()
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT COUNT(*) FROM " + PollingUnit.POLLING_UNITS;
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

    public static List<PollingUnit> getAllPollingUnits()
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + PollingUnit.POLLING_UNITS;
            Query q = em.createNativeQuery(sql, PollingUnit.class);
            List<PollingUnit> allPollingUnits = q.getResultList();
            return allPollingUnits;
        }
        finally
        {
            em.close();
        }
    }

    public static List<PollingUnit> getPollingUnits(int wardId) throws Exception
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT * FROM " + PollingUnit.POLLING_UNITS + " WHERE " + PollingUnit.WARD_ID + " = ?";
            Query query = em.createNativeQuery(sql, PollingUnit.class);
            query.setParameter(1, wardId);
            List<PollingUnit> pollingUnits = query.getResultList();
            return pollingUnits;
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
        }
    }

    public static PollingUnit getPollingUnit(int pollingUnitId)
    {
        EntityManager em = null;

        try
        {
            em = DBConfiguration.getEntityManager();
            return em.find(PollingUnit.class, pollingUnitId);
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
        }
    }
}
