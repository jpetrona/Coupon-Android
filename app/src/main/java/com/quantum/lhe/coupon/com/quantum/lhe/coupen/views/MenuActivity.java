package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.lhe.coupon.R;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class MenuActivity extends Activity implements View.OnClickListener {

    TextView btnHome, btnExplore, btnProfile, btnSaved, btnFeedback;
    ImageView btnClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_main_menu_activity);

        btnHome = (TextView) findViewById(R.id.btnHome);
        btnExplore = (TextView) findViewById(R.id.btnExplore);
        btnProfile = (TextView) findViewById(R.id.btnProfile);
        btnSaved = (TextView) findViewById(R.id.btnSaved);
        btnFeedback = (TextView) findViewById(R.id.btnFeedback);
        btnClose = (ImageView) findViewById(R.id.btnClose);

        btnHome.setOnClickListener(this);
        btnExplore.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnSaved.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
        btnClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnHome:
                intent = new Intent(this, AllCouponActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnExplore:
                intent = new Intent(this, CategoryOveviewActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnProfile:
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnSaved:
                intent = new Intent(this, SaveActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnFeedback:
                Toast.makeText(this, "Feedback Clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnClose:
                finish();
                break;
            default:
                break;
        }
    }
}