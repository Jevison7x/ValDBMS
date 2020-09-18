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
package com.valdbms.members;

import com.valdbms.database.DBConfiguration;
import com.valdbms.util.XyneexURL;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since Jun 5, 2020 2:34:28 AM
 */
public class MembersDAO
{
    public static void createNewMember(Member member)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static List<Member> getAllMembers()
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Member.MEMBERS;
            Query q = em.createNativeQuery(sql, Member.class);
            List<Member> members = q.getResultList();
            return members;
        }
        finally
        {
            em.close();
        }
    }

    public static int getMembersTotalCount() throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT COUNT(*) FROM " + Member.MEMBERS;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
                return rs.getInt(1);
            else
                return 0;
        }
    }

    public static List<Member> getSearchResults(String searchCriteria, String sortColumn, String sortDirection, int start, int length)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Member.MEMBERS + getSearchSQL(sortColumn, sortDirection) + " LIMIT " + start + ", " + length;
            Query q = em.createNativeQuery(sql, Member.class);
            q.setParameter(1, "%" + searchCriteria + "%");
            q.setParameter(2, "%" + searchCriteria + "%");
            q.setParameter(3, "%" + searchCriteria + "%");
            q.setParameter(4, "%" + searchCriteria + "%");
            q.setParameter(5, "%" + searchCriteria + "%");
            q.setParameter(6, "%" + searchCriteria + "%");
            q.setParameter(7, "%" + searchCriteria + "%");
            q.setParameter(8, "%" + searchCriteria + "%");
            q.setParameter(9, "%" + searchCriteria + "%");
            q.setParameter(10, "%" + searchCriteria + "%");
            q.setParameter(11, "%" + searchCriteria + "%");
            q.setParameter(12, "%" + searchCriteria + "%");
            q.setParameter(13, "%" + searchCriteria + "%");
            q.setParameter(14, "%" + searchCriteria + "%");
            List<Member> members = q.getResultList();
            return members;
        }
        finally
        {
            em.close();
        }
    }

    public static int getSearchTotal(String searchCriteria, String sortColumn, String sortDirection) throws SQLException, IOException, IllegalArgumentException, ClassNotFoundException
    {
        DBConfiguration dbConfig = new DBConfiguration();
        try(Connection conn = dbConfig.getDatabaseConnection())
        {
            String sql = "SELECT COUNT(*) FROM " + Member.MEMBERS + getSearchSQL(sortColumn, sortDirection);
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + searchCriteria + "%");
            pst.setString(2, "%" + searchCriteria + "%");
            pst.setString(3, "%" + searchCriteria + "%");
            pst.setString(4, "%" + searchCriteria + "%");
            pst.setString(5, "%" + searchCriteria + "%");
            pst.setString(6, "%" + searchCriteria + "%");
            pst.setString(7, "%" + searchCriteria + "%");
            pst.setString(8, "%" + searchCriteria + "%");
            pst.setString(9, "%" + searchCriteria + "%");
            pst.setString(10, "%" + searchCriteria + "%");
            pst.setString(11, "%" + searchCriteria + "%");
            pst.setString(12, "%" + searchCriteria + "%");
            pst.setString(13, "%" + searchCriteria + "%");
            pst.setString(14, "%" + searchCriteria + "%");
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return rs.getInt(1);
            else
                return 0;
        }
    }

    private static String getSearchSQL(String colVal, String sortDirection)
    {
        return " WHERE "
                + Member.MOBILE_NO + " LIKE ? OR "
                + Member.TITLE + " LIKE ? OR "
                + Member.FIRST_NAME + " LIKE ? OR "
                + Member.MIDDLE_NAME + " LIKE ? OR "
                + Member.LAST_NAME + " LIKE ? OR "
                + Member.ROLE + " LIKE ? OR "
                + Member.STATE + " LIKE ? OR "
                + Member.LGA + " LIKE ? OR "
                + Member.WARD + " LIKE ? OR "
                + Member.BANK + " LIKE ? OR "
                + Member.ACCOUNT_NAME + " LIKE ? OR "
                + Member.ACCOUNT_NO + " LIKE ? OR "
                + Member.EMAIL + " LIKE ? OR "
                + Member.ADDED_BY + " LIKE ? "
                + "ORDER BY " + colVal + " " + sortDirection;
    }

    public static List<Member> filteredMembers(String role, String state, String lga, String ward, String bank)
    {
        List<Member> allMembers = getAllMembers();
        List<Member> filteredMembers = new ArrayList<>();
        for(Member member : allMembers)
        {
            if(!role.isEmpty())
                if(!member.getRole().equals(role))
                    continue;
            if(!state.isEmpty())
                if(!member.getState().equals(state))
                    continue;
            if(!lga.isEmpty())
                if(!member.getLga().equals(lga))
                    continue;
            if(!ward.isEmpty())
                if(!member.getWard().equals(ward))
                    continue;
            if(!bank.isEmpty())
                if(!member.getBank().equals(bank))
                    continue;
            filteredMembers.add(member);
        }
        return filteredMembers;
    }

    public static List<Member> getChairmanList()
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + Member.MEMBERS + " WHERE " + Member.ROLE + " = 'CHAIRMAN' ";
            Query q = em.createNativeQuery(sql, Member.class);
            List<Member> members = q.getResultList();
            return members;
        }
        finally
        {
            em.close();
        }
    }

    public static boolean updateOldPhoneNumber(String oldPhoneNumber, String mobileNo)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            String sql = "UPDATE " + Member.MEMBERS + " "
                    + "SET " + Member.MOBILE_NO + " = ? "
                    + "WHERE " + Member.MOBILE_NO + " = ?";
            Query q = em.createNativeQuery(sql);
            q.setParameter(1, mobileNo);
            q.setParameter(2, oldPhoneNumber);
            int update = q.executeUpdate();
            em.getTransaction().commit();
            if(update == 1)
                return true;
            else
                return false;
        }
        finally
        {
            em.close();
        }
    }

    public static void updateMember(Member member)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            Member m = em.find(Member.class, member.getMobileNo());
            m.setTitle(member.getTitle());
            m.setFirstName(member.getFirstName());
            m.setLastName(member.getLastName());
            m.setMiddleName(member.getMiddleName());
            m.setAddedBy(member.getAddedBy());
            m.setEmail(member.getEmail());
            m.setDateAdded(member.getDateAdded());
            m.setGender(member.getGender());
            m.setState(member.getState());
            m.setLga(member.getLga());
            m.setWard(member.getWard());
            m.setRole(member.getRole());
            m.setBank(member.getBank());
            m.setAccountNo(member.getAccountNo());
            m.setAccountName(member.getAccountName());
            m.setDateOfBirth(member.getDateOfBirth());
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static Member getMember(String phoneNumber)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            Member member = em.find(Member.class, phoneNumber);
            return member;
        }
        finally
        {
            em.close();
        }
    }

    public static void delete(String phoneNumber)
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            em.getTransaction().begin();
            Member member = em.find(Member.class, phoneNumber);
            em.remove(member);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public static String loginSMSLive247(String url, String subAccount, String subAccountEmail, String password) throws IOException
    {
        XyneexURL xyneexURL = new XyneexURL(url);
        xyneexURL.addParameter("cmd", "login");
        xyneexURL.addParameter("owneremail", subAccountEmail);
        xyneexURL.addParameter("subacct", subAccount);
        xyneexURL.addParameter("subacctpwd", password);
        xyneexURL.setGetRequest();
        String response = xyneexURL.getResponse();
        System.out.println(response);
        return response;
    }

    public static void sendSMSLive247(String url, String sessionId, String message, String sender, String sendTo) throws IOException
    {
        XyneexURL xyneexURL = new XyneexURL(url);
        xyneexURL.addParameter("cmd", "sendmsg");
        xyneexURL.addParameter("sessionid", sessionId);
        xyneexURL.addParameter("message", message);
        xyneexURL.addParameter("sender", sender);
        xyneexURL.addParameter("sendto", sendTo);
        xyneexURL.addParameter("msgtype", "0");
        xyneexURL.setGetRequest();
        String response = xyneexURL.getResponse();
        System.out.println(response);
    }

    public static String sendSmartSMSSolutions(String message, String sender, String sendTo) throws IOException, NoSuchAlgorithmException, KeyManagementException
    {
        XyneexURL xyneexURL = new XyneexURL("https://smartsmssolutions.com/api/json.php");
        xyneexURL.addParameter("message", message);
        xyneexURL.addParameter("to", sendTo);
        xyneexURL.addParameter("sender", sender);
        xyneexURL.addParameter("type", "0");
        xyneexURL.addParameter("routing", "6");
        xyneexURL.addParameter("token", "YlPuhkIKOe8zhIsdzWwGbloHi8IhX2ZgUgI7gdh1hFLFT8ILf0RmRHRB0p2OKtL0lnRt8fBQC2wMuJ7oVJuCXLFeBxwIcWjybwOj");
        xyneexURL.setHTTPSGetRequest();
        String response = xyneexURL.getHTTPSResponse();
        return response;
    }
}
