/*
 * Copyright (c) 2019, Zealnetworks Technologies. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * You are not meant to edit or modify this source code unless you are
 * authorized to do so.
 *
 * Please contact me at contact@zealtech.com.ng
 * or visit www.zealtech.com.ng if you need additional information or have any
 * questions.
 */
package com.valdbms.database;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Charles Archibong
 * @since Nov 5, 2019 12:34:21 AM
 */
public class DBConfiguration
{

    public static Properties remoteDBProperties;

    public static Properties localDBProperties;

    public static EntityManagerFactory remoteEntityManagerFactory;

    public static EntityManagerFactory localEntityManagerFactory;

    public static EntityManager getEntityManager()
    {
        return createDBEntityManager();
    }

    private static EntityManager createDBEntityManager()
    {
        EntityManager em = remoteEntityManagerFactory.createEntityManager();
        if(em != null)
            return em;
        else
        {
            em = localEntityManagerFactory.createEntityManager();
            if(em != null)
                return em;
            else
                return null;
        }
    }
}
