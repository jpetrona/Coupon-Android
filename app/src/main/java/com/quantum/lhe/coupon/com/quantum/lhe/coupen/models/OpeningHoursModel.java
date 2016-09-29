package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

/**
 * Created by Sharjeel on 9/23/2016.
 */

public class OpeningHoursModel {
    private ToModel to;

    private FromModel from;

    private String dayOfWeek;

    public ToModel getTo ()
    {
        return to;
    }

    public void setTo (ToModel to)
    {
        this.to = to;
    }

    public FromModel getFrom ()
    {
        return from;
    }

    public void setFrom (FromModel from)
    {
        this.from = from;
    }

    public String getDayOfWeek ()
    {
        return dayOfWeek;
    }

    public void setDayOfWeek (String dayOfWeek)
    {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [to = "+to+", from = "+from+", dayOfWeek = "+dayOfWeek+"]";
    }
}
