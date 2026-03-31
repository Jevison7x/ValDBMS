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
import com.valdbms.states.LGA;
import static com.valdbms.states.LGA.LGA_S;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since May 31, 2020 9:09:13 PM
 */
public class LGA_DAO
{
    public static List<String> getDistinctLGAs(String state)
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT DISTINCT " + Ward.LGA + " FROM " + Ward.WARDS + " WHERE " + Ward.STATE + " = ?";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, state);
            List<Object> result = query.getResultList();
            List<String> lgaList = new ArrayList<>();
            for(Object obj : result)
                lgaList.add((String)obj);
            return lgaList;
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

    public static int getLgasTotalCount()
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT COUNT(*) FROM " + LGA_S;
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

    public static List<LGA> getLGAs(int stateId) throws Exception
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT * FROM " + LGA.LGA_S + " WHERE " + LGA.STATE_ID + " = ?";
            Query query = em.createNativeQuery(sql, LGA.class);
            query.setParameter(1, stateId);
            List<LGA> lgas = query.getResultList();
            return lgas;
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
        }
    }

    public static List<LGA> getAllLGAs() throws Exception
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT * FROM " + LGA.LGA_S;
            Query query = em.createNativeQuery(sql, LGA.class);
            List<LGA> lgas = query.getResultList();
            return lgas;
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
        }
    }
}
