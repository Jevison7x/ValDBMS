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
package com.valdbms.roles;

import com.valdbms.database.DBConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ugimson
 * @since Jun 7, 2020 4:33:30 AM
 */
public class RolesDAO
{
    public static List<String> getDistinctRoles()
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT DISTINCT " + Role.ROLE + " FROM " + Role.ROLES;
            Query query = em.createNativeQuery(sql);
            List<Object> result = query.getResultList();
            List<String> roleList = new ArrayList<>();
            for(Object obj : result)
                roleList.add((String)obj);
            return roleList;
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

    public static void createRole(Role role)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static Role getRole(String role)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            Role r = em.find(Role.class, role);
            return r;
        }
        finally
        {
            em.close();
        }
    }
}
