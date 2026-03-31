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
import static com.valdbms.users.User.EMAIL;
import static com.valdbms.users.User.USERS;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Ugimson
 * @since May 30, 2020 4:53:50 PM
 */
public class UserDAO
{
    public static boolean createNewUser(User user, String encPassword)
    {
        EntityManager em = null;
        EntityTransaction tx = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            String sql = "INSERT INTO " + User.USERS
                    + " (" + User.EMAIL + ", " + User.USER_NAME + ", " + User.PASSWORD + ", " + User.DATE_CREATED + ") "
                    + " VALUES (?, ?, ?, ?)";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, user.getEmail());
            query.setParameter(2, user.getUserName());
            query.setParameter(3, encPassword);
            query.setParameter(4, user.getDateCreated());
            int result = query.executeUpdate();
            tx.commit();
            return result == 1;
        }
        catch(Exception e)
        {
            if(tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace(System.err);
            return false;
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
        }
    }

    public static User loginUser(String userNameOrEmail, String password)
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT " + User.USER_NAME + ", " + User.PASSWORD + " FROM " + User.USERS + " WHERE " + User.USER_NAME + " = ? OR " + User.EMAIL + " = ?";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, userNameOrEmail);
            query.setParameter(2, userNameOrEmail);
            List<Object[]> result = query.getResultList();
            if(result.isEmpty())
                throw new IllegalArgumentException("Invalid username or password.");
            Object[] row = result.get(0);
            String encPassword = (String)row[1];
            DigestMatcher matcher = new DigestMatcher();
            String salt = matcher.getSalt(encPassword);
            boolean matched = matcher.doMatch(password, salt);
            if(matched == true)
                return getUser((String)row[0]);
            else
                throw new IllegalArgumentException("Invalid username or password.");
        }
        catch(Exception e)
        {
            if(e instanceof IllegalArgumentException)
                throw (IllegalArgumentException)e;
            e.printStackTrace(System.err);
            throw new IllegalArgumentException("Invalid username or password.");
        }
        finally
        {
            if(em != null && em.isOpen())
                em.close();
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

    public static int getUsersTotalCount()
    {
        EntityManager em = null;
        try
        {
            em = DBConfiguration.getEntityManager();
            String sql = "SELECT COUNT(*) FROM " + User.USERS;
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
}
