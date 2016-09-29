package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.AllCouponAdapter;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.RecyclerItemClickListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.ApiURLs;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.controllers.CouponList;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponOverviewModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers.VolleyNetworkController;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class SaveActivity extends Activity implements View.OnClickListener, UniversalDataListener{

    private RecyclerView recyclerView;
    ImageView btnMenu;
    ArrayList<CouponOverviewModel> couponOverviewModelArrayList = new ArrayList<>();
    private ProgressDialog progressDialog;
    VolleyNetworkController networkController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_save_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        btnMenu = (ImageView) findViewById(R.id.btn_menu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        networkController = new VolleyNetworkController(this);
        if (NetUtils.isNetworkAvailable(this)) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.plz_wait));
            networkController.JsonArrayGet(ApiURLs.BOOKMARK_LIST, this);
        } else {

        }
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(SaveActivity.this, CouponDetailsActivity.class);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            default:
                break;
        }
    }

    @Override
    public void onDataReceived(JSONObject jsonObject) {

    }

    @Override
    public void onDataReceived(JSONArray jsonArray) {
        progressDialog.dismiss();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CouponOverviewModel>>() {
        }.getType();
        couponOverviewModelArrayList = gson.fromJson(jsonArray.toString(), listType);
        Log.i("BookMarkCoupons", couponOverviewModelArrayList.toString());
        recyclerView.setAdapter(new AllCouponAdapter(couponOverviewModelArrayList, this,networkController));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnError(String message) {
        progressDialog.dismiss();

    }
}