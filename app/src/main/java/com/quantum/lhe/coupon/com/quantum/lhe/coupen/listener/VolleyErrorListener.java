package com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;

/**
 * Created by Ayesha on 9/30/2015.
 */
public interface VolleyErrorListener extends ErrorListener {

    @Override
    void onErrorResponse(VolleyError volleyError);

}
