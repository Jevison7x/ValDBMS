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
package com.valdbms.roles;

import static com.valdbms.roles.Roles.ROLES;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ugimson
 * @since May 30, 2020 4:06:29 PM
 */
@Entity
@Table(name = ROLES)
public class Roles implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    @Id
    private String role;
    private String addedBy;
    private Timestamp dateAdded;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Roles()
    {
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getAddedBy()
    {
        return addedBy;
    }

    public void setAddedBy(String addedBy)
    {
        this.addedBy = addedBy;
    }

    public Timestamp getDateAdded()
    {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded)
    {
        this.dateAdded = dateAdded;
    }

    public static final String ROLES = "roles";
    public static final String ID = "id";
    public static final String ROLE = "role";
    public static final String ADDED_BY = "addedBy";
    public static final String DATE_ADDED = "dateAdded";
}
