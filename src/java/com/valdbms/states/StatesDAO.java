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
package com.valdbms.states;

import com.valdbms.database.DBConfiguration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Jevison7x
 * @since 31 Mar 2026 11:03:03
 */
public class StatesDAO
{
    public static List<State> getAllStates()
    {
        EntityManager em = DBConfiguration.getEntityManager();
        try
        {
            String sql = "SELECT * FROM " + State.STATES;
            Query q = em.createNativeQuery(sql, State.class);
            List<State> allStates = q.getResultList();
            return allStates;
        }
        finally
        {
            em.close();
        }
    }
}
