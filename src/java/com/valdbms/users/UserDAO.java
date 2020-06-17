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
package com.valdbms.users;

import com.valdbms.database.DBConfiguration;
import com.valdbms.security.DigestMatcher;
import static com.valdbms.users.User.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Ugimson
 * @since May 30, 2020 4:53:50 PM
 */
public class UserDAO
{
    public static boolean createNewUser(User user, String encPassword) throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "INSERT INTO " + USERS + " ("
                    + EMAIL + ", "
                    + USER_NAME + ", "
                    + PASSWORD + ", "
                    + DATE_CREATED
                    + ") VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getUserName());
            pst.setString(3, encPassword);
            pst.setTimestamp(4, user.getDateCreated());
            int update = pst.executeUpdate();
            if(update == 1)
                return true;
            else
                return false;
        }
    }

    public static User loginUser(String userNameOrEmail, String password) throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT * FROM " + USERS + " WHERE " + USER_NAME + " = ? OR " + EMAIL + " = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userNameOrEmail);
            pst.setString(2, userNameOrEmail);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                String encPassword = rs.getString(PASSWORD);
                DigestMatcher matcher = new DigestMatcher();
                String salt = matcher.getSalt(encPassword);
                boolean matched = matcher.doMatch(password, salt);
                if(matched == true)
                    return getUser(rs.getString(USER_NAME));
                else
                    throw new IllegalArgumentException("Invalid username or password.");
            }
            else
                throw new IllegalArgumentException("Invalid username or password.");
        }
    }

    public static User getUser(String username)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            return em.find(User.class, username);
        }
        finally
        {
            em.close();
        }
    }

    public static User getUserByEmail(String email)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            Query q = em.createNativeQuery("SELECT * FROM " + USERS + " WHERE " + EMAIL + " = ?", User.class);
            q.setParameter(1, email);
            User user = (User)q.getSingleResult();
            return user;
        }
        catch(NoResultException xcp)
        {
            return null;
        }
        finally
        {
            em.close();
        }
    }

    public static int getUsersTotalCount() throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT COUNT(*) FROM " + User.USERS;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return rs.getInt(1);
            else
                return 0;
        }
    }
}
