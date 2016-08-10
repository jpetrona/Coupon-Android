package com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sharjeel on 5/30/2016.
 */

public class AllCouponAdapter extends RecyclerView.Adapter<AllCouponAdapter.MyViewHolder> {


    Context context;
    ArrayList<CouponModel> coupon_objects = new ArrayList<>();
    int size;
    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Date;
        LinearLayout history_Items_Layout;

        public MyViewHolder(View view) {
            super(view);
//            Date = (TextView) view.findViewById(R.id.tv_date);
        }
    }


    public AllCouponAdapter(ArrayList<CouponModel> coupon_objects, Context context) {
        this.context = context;
        this.coupon_objects = coupon_objects;
        size = coupon_objects.size();

    }

    @Override
    public AllCouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_coupon_list, parent, false);
        return new AllCouponAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllCouponAdapter.MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return coupon_objects.size();
    }




}