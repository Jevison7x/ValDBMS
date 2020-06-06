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

import static com.valdbms.members.Member.MEMBERS;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ugimson
 * @since May 30, 2020 4:15:41 PM
 */
@Entity
@Table(name = MEMBERS)
public class Member implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int sn;
    @Id
    private String mobileNo;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String role;
    private String state;
    private String lga;
    private String ward;
    private String bank;
    private String accountName;
    private String accountNo;
    private String email;
    private Timestamp dateAdded;
    private String addedBy;

    public Member()
    {
    }

    public int getSn()
    {
        return sn;
    }

    public void setSn(int aSn)
    {
        sn = aSn;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String aMobileNo)
    {
        mobileNo = aMobileNo;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String aFirstName)
    {
        firstName = aFirstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String aLastName)
    {
        lastName = aLastName;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String aRole)
    {
        role = aRole;
    }

    public String getWard()
    {
        return ward;
    }

    public void setWard(String aWard)
    {
        ward = aWard;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String aBank)
    {
        bank = aBank;
    }

    public String getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo(String aAccountNo)
    {
        accountNo = aAccountNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String aEmail)
    {
        email = aEmail;
    }

    public Timestamp getDateAdded()
    {
        return dateAdded;
    }

    public void setDateAdded(Timestamp aDateAdded)
    {
        dateAdded = aDateAdded;
    }

    public String getAddedBy()
    {
        return addedBy;
    }

    public void setAddedBy(String aAddedBy)
    {
        addedBy = aAddedBy;
    }

    public String getLga()
    {
        return lga;
    }

    public void setLga(String lga)
    {
        this.lga = lga;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public static final String MEMBERS = "members";
    public static final String SN = "sn";
    public static final String MOBILE_NO = "mobileNo";
    public static final String TITLE = "title";
    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String ROLE = "role";
    public static final String STATE = "state";
    public static final String LGA = "lga";
    public static final String WARD = "ward";
    public static final String BANK = "bank";
    public static final String ACCOUNT_NO = "accountNo";
    public static final String EMAIL = "email";
    public static final String DATE_ADDED = "dateAdded";
    public static final String ADDED_BY = "addedBy";
}
