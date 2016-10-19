package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 10/19/2016.
 */

public class AddressModel implements Serializable
{
    private String address;

    private String zipCode;

    private String countryCode;

    private String address2;

    private String city;

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getZipCode ()
    {
        return zipCode;
    }

    public void setZipCode (String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getAddress2 ()
    {
        return address2;
    }

    public void setAddress2 (String address2)
    {
        this.address2 = address2;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", zipCode = "+zipCode+", countryCode = "+countryCode+", address2 = "+address2+", city = "+city+"]";
    }
}
