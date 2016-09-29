package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 9/2/2016.
 */

public class UserDataModel implements Serializable {

    private String picture;

    private String dateOfBirth;

    private String countryId;

    private String cellPhoneNumber;

    private String email;

    private String name;

    private String gender;

    public String getPicture ()
    {
        return picture;
    }

    public void setPicture (String picture)
    {
        this.picture = picture;
    }

    public String getDateOfBirth ()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth (String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryId ()
    {
        return countryId;
    }

    public void setCountryId (String countryId)
    {
        this.countryId = countryId;
    }

    public String getCellPhoneNumber ()
    {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber (String cellPhoneNumber)
    {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [picture = "+picture+", dateOfBirth = "+dateOfBirth+", countryId = "+countryId+", cellPhoneNumber = "+cellPhoneNumber+", email = "+email+", name = "+name+", gender = "+gender+"]";
    }
}