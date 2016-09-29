package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

/**
 * Created by Sharjeel on 9/23/2016.
 */

public class ToModel {
    private String minutes;

    private String hours;

    public String getMinutes ()
    {
        return minutes;
    }

    public void setMinutes (String minutes)
    {
        this.minutes = minutes;
    }

    public String getHours ()
    {
        return hours;
    }

    public void setHours (String hours)
    {
        this.hours = hours;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [minutes = "+minutes+", hours = "+hours+"]";
    }
}