package com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Sharjeel on 2/2/2016.
 */
public interface UniversalDataListener {

    public void onDataReceived(JSONObject jsonObject);
    public void onDataReceived(JSONArray  jsonArray);
    public void OnError(String message);


}
