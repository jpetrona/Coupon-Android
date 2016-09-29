package com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants;

import android.content.Context;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.application.CouponApplication;

/**
 * Created by Sharjeel on 8/10/2016.
 */

public class ApiURLs {

    public static Context context = CouponApplication.getInstance().getApplicationContext();


    public static final String URL_KEY = context.getString(R.string.BASE_URL);


    public static final String USER_BASE = URL_KEY+"users/";

    public static final String COUPON_BASE = URL_KEY+"coupons/";


    public static final String CREATE_USER = URL_KEY+"users";

    public static final String LOGIN_USER = USER_BASE+"login";

    public static final String BOOKMARK_COUPONS = COUPON_BASE+"{id}/bookmark";

    public static final String BOOKMARK_LIST = COUPON_BASE+"bookmarked";




}
