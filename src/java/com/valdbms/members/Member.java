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
import com.valdbms.pollingunits.PollingUnit;
import com.valdbms.states.LGA;
import com.valdbms.states.State;
import com.valdbms.wards.Ward;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sn;
    private String mobileNo;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state", referencedColumnName = "id")
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lga", referencedColumnName = "id")
    private LGA lga;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward", referencedColumnName = "id")
    private Ward ward;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pollingUnit", referencedColumnName = "id")
    private PollingUnit pollingUnit;

    private String bank;
    private String accountName;
    private String accountNo;
    private String email;
    private Timestamp dateAdded;
    private String addedBy;

    public static final String MALE = "Male";
    public static final String FEMALE = "Female";

    public Member()
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

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public LGA getLga()
    {
        return lga;
    }

    public void setLga(LGA lga)
    {
        this.lga = lga;
    }

    public Ward getWard()
    {
        return ward;
    }

    public void setWard(Ward ward)
    {
        this.ward = ward;
    }

    public PollingUnit getPollingUnit()
    {
        return pollingUnit;
    }

    public void setPollingUnit(PollingUnit pollingUnit)
    {
        this.pollingUnit = pollingUnit;
    }

    public String getBank()
    {
        return bank;
    }

    public void setBank(String bank)
    {
        this.bank = bank;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Timestamp getDateAdded()
    {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded)
    {
        this.dateAdded = dateAdded;
    }

    public String getAddedBy()
    {
        return addedBy;
    }

    public void setAddedBy(String addedBy)
    {
        this.addedBy = addedBy;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName + " (" + email + ")";
    }

    public static final String MEMBERS = "members";
    public static final String SN = "sn";
    public static final String MOBILE_NO = "mobileNo";
    public static final String TITLE = "title";
    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String GENDER = "gender";
    public static final String DATE_OF_BIRTH = "dateOfBirth";
    public static final String ROLE = "role";
    public static final String STATE = "state";
    public static final String LGA = "lga";
    public static final String WARD = "ward";
    public static final String BANK = "bank";
    public static final String ACCOUNT_NAME = "accountName";
    public static final String ACCOUNT_NO = "accountNo";
    public static final String EMAIL = "email";
    public static final String DATE_ADDED = "dateAdded";
    public static final String ADDED_BY = "addedBy";
}
