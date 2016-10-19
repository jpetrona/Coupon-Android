package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.ShopsExpandableAdapter;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.ApiURLs;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponOverviewModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.ShopsModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.StoreTimingsModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers.VolleyNetworkController;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class CouponDetailsActivity extends Activity implements UniversalDataListener, View.OnClickListener {

    ImageButton back;
    private ProgressDialog progressDialog;
    VolleyNetworkController networkController;
    private ExpandableListView shopExpandList;
    ShopsExpandableAdapter shopsExpandAdapter;

    ImageView ivCoupon, ivShare, ivFavorite;
    TextView tvTitle, tvDescription, tvDiscount;

    String requestType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_activity);

        tvTitle = (TextView) findViewById(R.id.tv_coup_heading);
        tvDescription = (TextView) findViewById(R.id.tv_coup_detail);
        tvDiscount = (TextView) findViewById(R.id.tv_discount);
        ivCoupon = (ImageView) findViewById(R.id.iv_coupon);
        ivShare = (ImageView) findViewById(R.id.iv_share);
        ivFavorite = (ImageView) findViewById(R.id.iv_fav);
        shopExpandList = (ExpandableListView) findViewById(R.id.exShopList);
        back = (ImageButton) findViewById(R.id.back);

        ivFavorite.setOnClickListener(this);
        back.setOnClickListener(this);
        ivShare.setOnClickListener(this);

        networkController = new VolleyNetworkController(this);
        if (NetUtils.isNetworkAvailable(this)) {
            CouponOverviewModel overviewModel = (CouponOverviewModel) getIntent().getExtras().getSerializable("MyClass");
            if (overviewModel==null){
                progressDialog = ProgressDialog.show(this, "", getString(R.string.plz_wait));
                requestType = ApiURLs.COUPON_BASE;
                networkController.UniversalGet(ApiURLs.COUPON_BASE+"8a99e8d9-905d-4f7a-84fa-aa780187d289", this);
            }else{
                setValues(overviewModel);
            }

        } else {

        }

        //region Dummy Data
        /*StoreTimingsModel[] timinigs = new StoreTimingsModel[4];

        timinigs[0] = new StoreTimingsModel();
        timinigs[0].setFromDay("Mon");
        timinigs[0].setToDay("Fri");
        timinigs[0].setOpeningTiming("11:00");
        timinigs[0].setClosingTiming("2:00");

        timinigs[1] = new StoreTimingsModel();
        timinigs[1].setFromDay("Thu");
        timinigs[1].setToDay("Sun");
        timinigs[1].setOpeningTiming("12:00");
        timinigs[1].setClosingTiming("5:00");

        timinigs[2] = new StoreTimingsModel();
        timinigs[2].setFromDay("Thu");
        timinigs[2].setToDay("Fri");
        timinigs[2].setOpeningTiming("4:00");
        timinigs[2].setClosingTiming("5:00");


        timinigs[3] = new StoreTimingsModel();
        timinigs[3].setFromDay("Tue");
        timinigs[3].setToDay("Fri");
        timinigs[3].setOpeningTiming("12:00");
        timinigs[3].setClosingTiming("5:00");

        for (StoreTimingsModel st : timinigs
                ) {
            final RelativeLayout newView = (RelativeLayout) getLayoutInflater().inflate(R.layout.coupon_timing_layout, null);
            String t1 = st.getFromDay() + "-" + st.getToDay();
            TextView tv = (TextView) newView.findViewById(R.id.tv_d);
            tv.setText(t1);


            if (!st.getIsException()){
                String t2 = st.getOpeningTiming() + "-" + st.getClosingTiming();
                TextView tv1 = (TextView) newView.findViewById(R.id.tv_t);
                tv1.setText(t2);
                this.linear_timing_layout.addView(newView);
            }
            else {
                String t2 = st.getDescription();
                TextView tv1 = (TextView) newView.findViewById(R.id.tv_t);
                tv1.setText(t2);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    LinearLayout.LayoutParams relativeParams = null;
                    relativeParams = new LinearLayout.LayoutParams(
                            new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT));
                    relativeParams.setMargins(0, 35, 0, 0);
                    newView.setLayoutParams(relativeParams);
                    newView.requestLayout();
                }
                this.linear_timing_layout.addView(newView);
            }
        }*/
        //endregion
    }

    String couponId;
    @Override
    public void onDataReceived(JSONObject jsonObject) {
        progressDialog.dismiss();
        if (requestType == ApiURLs.COUPON_BASE) {
            Gson gson = new Gson();
            CouponOverviewModel couponOverviewModel = gson.fromJson(jsonObject.toString(), CouponOverviewModel.class);
            setValues(couponOverviewModel);
        }else{
            Log.i("CouponDetailActivity", jsonObject.toString());
        }
    }

    @Override
    public void onDataReceived(JSONArray jsonArray) {

    }

    @Override
    public void OnError(String message) {

    }


    private void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_share:

                break;
            case R.id.iv_fav:
                String url = ApiURLs.BOOKMARK_COUPONS;
                url = url.replace("{id}",couponId);
                requestType = ApiURLs.BOOKMARK_COUPONS;
                networkController.UniversalGet(url,this);
                ivFavorite.setImageResource(R.drawable.heart_filled);

                break;
        }
    }

    void setValues(CouponOverviewModel couponOverviewModel){
        Log.i("CouponDetailActivity", couponOverviewModel.toString());
        ArrayList<ShopsModel> shopsModelArrayList = new ArrayList<ShopsModel>(Arrays.asList(couponOverviewModel.getShops()));
        shopsExpandAdapter = new ShopsExpandableAdapter(this,this,shopsModelArrayList);
        shopExpandList.setAdapter(shopsExpandAdapter);
        setListViewHeight(shopExpandList);

        couponId = couponOverviewModel.getId();
        tvTitle.setText(couponOverviewModel.getTitle());
        tvDescription.setText(couponOverviewModel.getDescription());
        shopExpandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });
    }
}