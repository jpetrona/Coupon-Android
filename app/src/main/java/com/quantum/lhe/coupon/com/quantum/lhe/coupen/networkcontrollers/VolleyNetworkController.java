package com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.application.CouponApplication;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.VolleyDataReceivedListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.VolleyErrorListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.PreferencesHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.ClientVersion;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.nClientVersion;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.InstallID;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.Platform;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.nPlatform;

/**
 * Created by Sharjeel on 15/04/2016.
 */
public class VolleyNetworkController implements VolleyErrorListener, VolleyDataReceivedListener, Response.Listener<JSONObject> {

    private Context context;

    private String TAG = VolleyNetworkController.class.getSimpleName();

    UniversalDataListener universalDataListener;

    private String requestType;
    private static String flag;
    private final String GET = "GET";
    private final String POST = "POST";
    private final String PUT = "PUT";
    private final String GET_WITH_FLAG = "GET_WITH_FLAG";


    public VolleyNetworkController(Context context) {
        this.context = context;
    }


    public void UniversalGet(String url, UniversalDataListener universalDataListener) {
        requestType = GET;
        this.universalDataListener = universalDataListener;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, this, this) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(InstallID, PreferencesHandler.getStringPreferences(AppConstants.UUID));
                headers.put(Platform, nPlatform);
                headers.put(ClientVersion, ClientVersion);
                return headers;
            }
        };
        CouponApplication.getInstance().addToRequestQueue(request, url);
    }


    public void UniversalPost(JSONObject jsonObject, String url, UniversalDataListener universalDataListener) {
        requestType = POST;
        this.universalDataListener = universalDataListener;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, this, this) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(InstallID, PreferencesHandler.getStringPreferences(AppConstants.UUID));
                headers.put(Platform, nPlatform);
                headers.put(ClientVersion, ClientVersion);
                return headers;
            }


        };
        CouponApplication.getInstance().addToRequestQueue(request, url);
    }

    public void UniversalPut(JSONObject jsonObject, String url, UniversalDataListener universalDataListener) {
        requestType = POST;
        this.universalDataListener = universalDataListener;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, this, this) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(InstallID, PreferencesHandler.getStringPreferences(AppConstants.UUID));
                headers.put(Platform, nPlatform);
                headers.put(ClientVersion, nClientVersion);
                return headers;
            }
        };
        CouponApplication.getInstance().addToRequestQueue(request, url);
    }

    public void JsonArrayGet(String url, final UniversalDataListener universalDataListener) {
        requestType = POST;
        this.universalDataListener = universalDataListener;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                universalDataListener.onDataReceived(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // hidePDialog();

            }
        });
        CouponApplication.getInstance().addToRequestQueue(jsonArrayRequest, url);
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, response.toString());
        /*if (progressDialog != null)
            progressDialog.dismiss();*/
        //region SWITCH STATEMENT
        switch (requestType) {

            case GET: {
                try {
                    universalDataListener.onDataReceived(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case POST: {
                try {
                    universalDataListener.onDataReceived(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }


        }
        //endregion
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        String message = VolleyErrorHelper.getMessage(volleyError, context);
        switch (requestType) {
            case GET: {
                universalDataListener.OnError(message);
                break;
            }
            case POST: {
                universalDataListener.OnError(message);
                break;
            }
        }
    }

    public void postNewComment() {
        StringRequest sr = new StringRequest(Request.Method.POST, "http://www.softhof.com/project/badamli/signup.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("str", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "sharjeelhaidder@gmail.com");
                params.put("name", "sharjeeel");
                params.put("pass", "123456");
                params.put("address", "lahore");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        CouponApplication.getInstance().addToRequestQueue(sr, "http://www.softhof.com/project/badamli/signup.php");

    }
}
