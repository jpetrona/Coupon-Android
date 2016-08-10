package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.AllCouponAdapter;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.RecyclerItemClickListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.controllers.CouponList;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class AllCouponActivity extends Activity  {

    private RecyclerView recyclerView;
    ImageView btnMenu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_coupon_activity);

        CouponList.saveOffersNewsData(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        btnMenu = (ImageView) findViewById(R.id.btn_menu);
        recyclerView.setAdapter(new AllCouponAdapter(CouponList.shop_objects,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCouponActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(AllCouponActivity.this, CouponDetailsActivity.class);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
}
