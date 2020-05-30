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
package com.valdbms.wards;

import static com.valdbms.wards.Ward.WARDS;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ugimson
 * @since May 30, 2020 3:50:42 PM
 */
@Entity
@Table(name = WARDS)
public class Ward implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    private String ward;
    private String wardName;

    public Ward()
    {
    }

    public String getWard()
    {
        return ward;
    }

    public void setWard(String ward)
    {
        this.ward = ward;
    }

    public String getWardName()
    {
        return wardName;
    }

    public void setWardName(String wardName)
    {
        this.wardName = wardName;
    }

    public static final String WARDS = "wards";
    public static final String WARD = "ward";
    public static final String WARD_NAME = "wardName";

}
