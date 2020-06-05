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
}
