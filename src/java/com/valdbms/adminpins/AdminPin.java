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
package com.valdbms.adminpins;

import static com.valdbms.adminpins.AdminPin.ADMIN_PINS;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jevison7x
 * @since May 30, 2020 7:02:54 PM
 */
@Entity
@Table(name = ADMIN_PINS)
public class AdminPin implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int sn;
    @Id
    private String pin;
    private String generatedBy;
    private Timestamp dateGenerated;
    private String usedBy;
    private Timestamp dateUsed;

    public AdminPin()
    {
    }

    public int getSn()
    {
        return sn;
    }

    public void setSn(int sn)
    {
        this.sn = sn;
    }

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public String getGeneratedBy()
    {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy)
    {
        this.generatedBy = generatedBy;
    }

    public Timestamp getDateGenerated()
    {
        return dateGenerated;
    }

    public void setDateGenerated(Timestamp dateGenerated)
    {
        this.dateGenerated = dateGenerated;
    }

    public String getUsedBy()
    {
        return usedBy;
    }

    public void setUsedBy(String usedBy)
    {
        this.usedBy = usedBy;
    }

    public Timestamp getDateUsed()
    {
        return dateUsed;
    }

    public void setDateUsed(Timestamp dateUsed)
    {
        this.dateUsed = dateUsed;
    }

    public static final String ADMIN_PINS = "adminPins";
    public static final String SN = "sn";
    public static final String PIN = "pin";
    public static final String GENERATED_BY = "generatedBy";
    public static final String DATE_GENERATED = "dateGenerated";
    public static final String USED_BY = "usedBy";
    public static final String DATE_USED = "dateUsed";
}
