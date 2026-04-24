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
package com.valdbms.pollingunits;

import static com.valdbms.pollingunits.PollingUnit.POLLING_UNITS;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jevison7x
 * @since 17 Apr 2026 12:00:00
 */
@Entity
@Table(name = POLLING_UNITS)
public class PollingUnit implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final String POLLING_UNITS = "polling_units";
    public static final String ID = "id";
    public static final String WARD_ID = "ward_id";
    public static final String CODE = "code";
    public static final String NAME = "name";

    @Id
    private int id;
    @Column(name = WARD_ID)
    private int wardId;
    private String code;
    private String name;

    public PollingUnit()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getWardId()
    {
        return wardId;
    }

    public void setWardId(int wardId)
    {
        this.wardId = wardId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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
