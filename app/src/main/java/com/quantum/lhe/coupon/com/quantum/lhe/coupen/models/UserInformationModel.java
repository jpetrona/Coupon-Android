package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 8/11/2016.
 */

public class UserInformationModel implements Serializable
{
    private String Picture;

    private String Name;

    private String Email;

    private String DOB;

    private String Gender;

    private String CellPhoneNumber;

    private String CountryCode;

    public String getPicture ()
    {
        return Picture;
    }

    public void setPicture (String Picture)
    {
        this.Picture = Picture;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getDOB ()
    {
        return DOB;
    }

    public void setDOB (String DOB)
    {
        this.DOB = DOB;
    }

    public String getGender ()
    {
        return Gender;
    }

    public void setGender (String Gender)
    {
        this.Gender = Gender;
    }

    public String getCellPhoneNumber ()
    {
        return CellPhoneNumber;
    }

    public void setCellPhoneNumber (String CellPhoneNumber)
    {
        this.CellPhoneNumber = CellPhoneNumber;
    }

    public String getCountryCode ()
    {
        return CountryCode;
    }

    public void setCountryCode (String CountryCode)
    {
        this.CountryCode = CountryCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Picture = "+Picture+", Name = "+Name+", Email = "+Email+", DOB = "+DOB+", Gender = "+Gender+", CellPhoneNumber = "+CellPhoneNumber+", CountryCode = "+CountryCode+"]";
    }
}