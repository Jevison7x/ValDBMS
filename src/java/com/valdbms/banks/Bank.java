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
package com.valdbms.banks;

import static com.valdbms.banks.Bank.BANKS;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jevison7x
 * @since Jun 7, 2020 12:56:58 AM
 */
@Entity
@Table(name = BANKS)
public class Bank implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int sn;
    @Id
    private String bank;

    public Bank()
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

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public static final String BANKS = "banks";
    public static final String SN = "sn";
    public static final String BANK = "bank";
}
