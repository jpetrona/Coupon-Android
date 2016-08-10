package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

import java.io.Serializable;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class CouponModel implements Serializable {

    int Id;

    String CouponTitle;

    String CouponDisc;

    String Discount;

    String City;

    String Logo_image;

    String Shop_image;

    String Distance;


    String BackImageUrl;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCouponTitle() {
        return CouponTitle;
    }

    public void setCouponTitle(String CouponTitle) {
        this.CouponTitle = CouponTitle;
    }

    public String getCouponDisc() {
        return CouponDisc;
    }

    public void setCouponDisc(String CouponDisc) {
        this.CouponDisc = CouponDisc;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public String getLogo_image() {
        return Logo_image;
    }

    public void setLogo_image(String Logo_image) {
        this.Logo_image = Logo_image;
    }

    public String getShop_image() {
        return Shop_image;
    }

    public void setShop_image(String Shop_image) {
        this.Shop_image = Shop_image;
    }

    public String getBackImageUrl() {
        return BackImageUrl;
    }

    public void setBackImageUrl(String BackImageUrl) {
        this.BackImageUrl = BackImageUrl;
    }


}