package com.quantum.lhe.coupon.com.quantum.lhe.coupen.models;

/**
 * Created by Sharjeel on 9/23/2016.
 */

public class CouponOverviewModel {
    private String state;

    private String forValueX;

    private String forValueY;

    private String toDate;

    private String type;

    private String visibleFromDateTime;

    private String visibleToDateTime;

    private String id;

    private String subCategoryId;

    private String ValidDayOfWeek;

    private String title;

    private String customerId;

    private String minutesBetweenRedeemes;

    private String fromDate;

    private ShopsModel[] shops;

    private String description;

    private String dailyToTime;

    private String value;

    private String dailyFromTime;

    private String subCategoryName;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getForValueX() {
        return forValueX;
    }

    public void setForValueX(String forValueX) {
        this.forValueX = forValueX;
    }

    public String getForValueY() {
        return forValueY;
    }

    public void setForValueY(String forValueY) {
        this.forValueY = forValueY;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibleFromDateTime() {
        return visibleFromDateTime;
    }

    public void setVisibleFromDateTime(String visibleFromDateTime) {
        this.visibleFromDateTime = visibleFromDateTime;
    }

    public String getVisibleToDateTime() {
        return visibleToDateTime;
    }

    public void setVisibleToDateTime(String visibleToDateTime) {
        this.visibleToDateTime = visibleToDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getValidDayOfWeek() {
        return ValidDayOfWeek;
    }

    public void setValidDayOfWeek(String ValidDayOfWeek) {
        this.ValidDayOfWeek = ValidDayOfWeek;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMinutesBetweenRedeemes() {
        return minutesBetweenRedeemes;
    }

    public void setMinutesBetweenRedeemes(String minutesBetweenRedeemes) {
        this.minutesBetweenRedeemes = minutesBetweenRedeemes;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public ShopsModel[] getShops() {
        return shops;
    }

    public void setShops(ShopsModel[] shops) {
        this.shops = shops;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDailyToTime() {
        return dailyToTime;
    }

    public void setDailyToTime(String dailyToTime) {
        this.dailyToTime = dailyToTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDailyFromTime() {
        return dailyFromTime;
    }

    public void setDailyFromTime(String dailyFromTime) {
        this.dailyFromTime = dailyFromTime;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    @Override
    public String toString() {
        return "ClassPojo [state = " + state + ", forValueX = " + forValueX + ", forValueY = " + forValueY + ", toDate = " + toDate + ", type = " + type + ", visibleFromDateTime = " + visibleFromDateTime + ", visibleToDateTime = " + visibleToDateTime + ", id = " + id + ", subCategoryId = " + subCategoryId + ", ValidDayOfWeek = " + ValidDayOfWeek + ", title = " + title + ", customerId = " + customerId + ", minutesBetweenRedeemes = " + minutesBetweenRedeemes + ", fromDate = " + fromDate + ", shops = " + shops + ", description = " + description + ", dailyToTime = " + dailyToTime + ", value = " + value + ", dailyFromTime = " + dailyFromTime + ", subCategoryName = " + subCategoryName + "]";
    }
}