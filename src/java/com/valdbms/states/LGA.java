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

import static com.valdbms.states.LGA.LGA_S;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jevison7x
 * @since 31 Mar 2026 09:54:30
 */
@Entity
@Table(name = LGA_S)
public class LGA implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @Column(name = STATE_ID)
    private int stateId;
    private String name;

    public LGA()
    {
    }

    public static final String LGA_S = "local_governments";
    public static final String ID = "id";
    public static final String STATE_ID = "state_id";
    public static final String NAME = "name";

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getStateId()
    {
        return stateId;
    }

    public void setStateId(int stateId)
    {
        this.stateId = stateId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
