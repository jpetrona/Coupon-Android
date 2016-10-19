package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 9/23/2016.
 */

public class ShopsModel implements Serializable
{
    private String id;

    private OpeningHoursModel[] openingHours;

    private AddressModel address;

    private String zipCode;

    private String name;

    private String countryCode;

    private GpsModel gps;

    private String address2;

    private String city;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public OpeningHoursModel[] getOpeningHours ()
    {
        return openingHours;
    }

    public void setOpeningHours (OpeningHoursModel[] openingHours)
    {
        this.openingHours = openingHours;
    }

    public AddressModel getAddress ()
    {
        return address;
    }

    public void setAddress (AddressModel address)
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

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public GpsModel getGps ()
    {
        return gps;
    }

    public void setGps (GpsModel gps)
    {
        this.gps = gps;
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
        return "ClassPojo [id = "+id+", openingHours = "+openingHours+", address = "+address+", zipCode = "+zipCode+", name = "+name+", countryCode = "+countryCode+", gps = "+gps+", address2 = "+address2+", city = "+city+"]";
    }
}
