package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters.AllCouponAdapter;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.ApiURLs;
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

public class AllCouponActivity extends Activity implements UniversalDataListener {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewSearch;
    ImageView btnMenu;
    SearchView searchView;
    View centreLine;
    RelativeLayout filter;
    private ProgressDialog progressDialog;
    ArrayList<CouponOverviewModel> couponOverviewModelArrayList = new ArrayList<>();
    ArrayList<CouponOverviewModel> searchResults = new ArrayList<>();
    AllCouponAdapter couponAdapter;

    VolleyNetworkController networkController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_all_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerViewSearch = (RecyclerView) findViewById(R.id.recycle_viewSearch);
        btnMenu = (ImageView) findViewById(R.id.btn_menu);
        searchView = (SearchView) findViewById(R.id.searchView);
        centreLine = (View) findViewById(R.id.centreline);
        filter = (RelativeLayout) findViewById(R.id.filerContainer);


        networkController = new VolleyNetworkController(this);
        if (NetUtils.isNetworkAvailable(this)) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.plz_wait));
            networkController.JsonArrayGet(ApiURLs.COUPON_BASE, this);
        } else {

        }
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllCouponActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                couponAdapter.getFilter().filter(newText);
                int textLength = newText.length();
                searchResults = new ArrayList<>();

                for (int i = 0; i < couponOverviewModelArrayList.size(); i++) {
                    //Log.e(TAG, "title = .....get");
                    String title = couponOverviewModelArrayList.get(i).getTitle().toString();
                    //Log.e(TAG, "title = ...."+ title);
                    if (textLength <= title.length()) {

                        if (newText.equalsIgnoreCase(title.substring(0, textLength)))
                            searchResults.add(couponOverviewModelArrayList.get(i));

                    }
                    couponAdapter = new AllCouponAdapter(searchResults, AllCouponActivity.this, networkController);
                    couponAdapter.notifyDataSetChanged();
                    recyclerViewSearch.setAdapter(couponAdapter);
                    recyclerViewSearch.setLayoutManager(new LinearLayoutManager(AllCouponActivity.this));

                    recyclerViewSearch.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (searchResults!=null){
                    if (searchResults.size()>0)
                        searchResults.clear();
                }
                centreLine.setVisibility(View.VISIBLE);
                filter.setVisibility(View.VISIBLE);
                couponAdapter = new AllCouponAdapter(couponOverviewModelArrayList, AllCouponActivity.this, networkController);
                couponAdapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                recyclerViewSearch.setVisibility(View.GONE);
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setMaxWidth(Integer.MAX_VALUE);
                centreLine.setVisibility(View.GONE);
                filter.setVisibility(View.GONE);
            }
        });
        /*recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(AllCouponActivity.this, CouponDetailsActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/
    }

    @Override
    public void onDataReceived(JSONObject jsonObject) {
        Log.i("AllCouponActivity", jsonObject.toString());
    }

    @Override
    public void onDataReceived(JSONArray jsonArray) {
        progressDialog.dismiss();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CouponOverviewModel>>() {
        }.getType();
        couponOverviewModelArrayList = gson.fromJson(jsonArray.toString(), listType);
        Log.i("AllCouponActivity", couponOverviewModelArrayList.toString());
        couponAdapter = new AllCouponAdapter(couponOverviewModelArrayList, this, networkController);
        recyclerView.setAdapter(couponAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnError(String message) {
        progressDialog.dismiss();

        Log.e("AllCouponActivity", message);
    }
}
