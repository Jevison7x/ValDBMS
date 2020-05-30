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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    private Properties prop;

    private String driverName;

    private String dbUser;

    private String dbPass;

    private void loadRemoteDBProperties() throws IOException, IllegalArgumentException
    {
        this.prop = remoteDBProperties;
    }

    private void loadLocalDBProperties() throws IOException, IllegalArgumentException
    {
        this.prop = localDBProperties;
    }

    public void loadDatabaseDriver() throws ClassNotFoundException, IOException, IllegalArgumentException
    {
        try
        {
            this.driverName = this.prop.getProperty("driverName");
            Class.forName(this.driverName);
        }
        catch(ClassNotFoundException | IllegalArgumentException xcp)
        {
            throw xcp;
        }
    }

    private Connection getConnection() throws ClassNotFoundException, IllegalArgumentException, IOException, SQLException
    {
        this.dbUser = this.prop.getProperty("db.user");
        this.dbPass = this.prop.getProperty("db.pass");
        this.loadDatabaseDriver();
        return DriverManager.getConnection(this.prop.getProperty("db.url"), this.dbUser, this.dbPass);
    }

    public Connection getDatabaseConnection() throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        try
        {
            try
            {
                this.loadRemoteDBProperties();
                return this.getConnection();
            }
            catch(SQLException xcp)
            {
                this.loadLocalDBProperties();
                return this.getConnection();
            }
        }
        catch(SQLException | IOException | IllegalArgumentException xcp)
        {
            throw xcp;
        }
    }

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
