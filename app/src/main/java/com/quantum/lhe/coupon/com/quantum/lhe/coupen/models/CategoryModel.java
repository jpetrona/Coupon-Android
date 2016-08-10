package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class CategoryModel implements Serializable {

    int Id;

    String CategoryTitle;

    String BackImageUrl;

    public int getId(){
        return Id;
    }

    public void setId (int Id)
    {
        this.Id = Id;
    }


    public String getCategoryTitle(){
        return CategoryTitle;
    }

    public void setCategoryTitle (String CategoryTitle)
    {
        this.CategoryTitle = CategoryTitle;
    }


    public String getBackImageUrl(){
        return BackImageUrl;
    }

    public void setBackImageUrl (String BackImageUrl)
    {
        this.BackImageUrl = BackImageUrl;
    }


}