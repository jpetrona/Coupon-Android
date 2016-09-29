package com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener;

import org.json.JSONObject;

import static com.android.volley.Response.Listener;

/**
 * Created by Adeel on 9/30/2015.
 */
public interface VolleyDataReceivedListener extends Listener<JSONObject> {

    @Override
    void onResponse(JSONObject response);
}
