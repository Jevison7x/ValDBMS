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
package com.valdbms.adminpins;

import com.valdbms.database.DBConfiguration;
import com.valdbms.util.DateTimeUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since May 30, 2020 7:11:39 PM
 */
public class AdminPinDAO
{
    public static void createAdminPin(AdminPin adminPin)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(adminPin);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static AdminPin getAdminPin(String pin)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            return em.find(AdminPin.class, pin);
        }
        finally
        {
            em.close();
        }
    }

    public static boolean pinIsValid(String pin)
    {
        AdminPin adminPin = getAdminPin(pin);
        if(adminPin != null)
            if(adminPin.getUsedBy() == null)
                return true;
            else
                return false;
        else
            return false;
    }

    public static List<AdminPin> getAdminPins()
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + AdminPin.ADMIN_PINS;
            Query q = em.createNativeQuery(sql, AdminPin.class);
            List<AdminPin> adminPins = q.getResultList();
            return adminPins;
        }
        finally
        {
            em.close();
        }
    }

    public static void updatePin(String userName, String pin)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            AdminPin adminPin = em.find(AdminPin.class, pin);
            adminPin.setUsedBy(userName);
            adminPin.setDateUsed(DateTimeUtil.getTodayTimeZone());
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }
}
