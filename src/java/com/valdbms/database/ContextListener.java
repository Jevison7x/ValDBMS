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

import static com.valdbms.database.DBConfiguration.localDBProperties;
import static com.valdbms.database.DBConfiguration.remoteDBProperties;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Charles Archibong
 */
public class ContextListener implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("Context is Initialized!");
        this.loadRemoteDBProperties();
        this.loadLocalDBProperties();
        this.loadRemoteEntityManagerFactory();
        this.loadLocalEntityManagerFactory();
        ServletContext sc = sce.getServletContext();
        String dashboardURL = sc.getInitParameter("Dashboard URL");
        sc.setAttribute("dashboardURL", dashboardURL);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadRemoteDBProperties()
    {
        //Load the database properties file into an InputStream object.
        try(InputStream dbPropInputStream = ContextListener.class.getResourceAsStream("Config.remote.properties");)
        {
            //Instantiate the library's properties object.
            DBConfiguration.remoteDBProperties = new Properties();
            //Load the properties from the InputStream object to the Properties object.
            DBConfiguration.remoteDBProperties.load(dbPropInputStream);
        }
        catch(IOException | IllegalArgumentException xcp)
        {
            //Log Errors Here
        }
    }

    private void loadLocalDBProperties()
    {
        //Load the database properties file into an InputStream object.
        try(InputStream dbPropInputStream = ContextListener.class.getResourceAsStream("Config.local.properties");)
        {
            //Instantiate the library's properties object.
            DBConfiguration.localDBProperties = new Properties();
            //Load the properties from the InputStream object to the Properties object.
            DBConfiguration.localDBProperties.load(dbPropInputStream);
        }
        catch(IOException | IllegalArgumentException xcp)
        {
            //Log Errors Here
        }
    }

    private void loadRemoteEntityManagerFactory()
    {
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("javax.persistence.jdbc.url", remoteDBProperties.getProperty("db.url"));
        persistenceMap.put("javax.persistence.jdbc.user", remoteDBProperties.getProperty("db.user"));
        persistenceMap.put("javax.persistence.jdbc.password", remoteDBProperties.getProperty("db.pass"));
        persistenceMap.put("javax.persistence.jdbc.driver", remoteDBProperties.getProperty("driverName"));
        DBConfiguration.remoteEntityManagerFactory = Persistence.createEntityManagerFactory("ValDBMS_PU", persistenceMap);
    }

    private void loadLocalEntityManagerFactory()
    {
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("javax.persistence.jdbc.url", localDBProperties.getProperty("db.url"));
        persistenceMap.put("javax.persistence.jdbc.user", localDBProperties.getProperty("db.user"));
        persistenceMap.put("javax.persistence.jdbc.password", localDBProperties.getProperty("db.pass"));
        persistenceMap.put("javax.persistence.jdbc.driver", localDBProperties.getProperty("driverName"));
        DBConfiguration.localEntityManagerFactory = Persistence.createEntityManagerFactory("ValDBMS_PU", persistenceMap);
    }
}
