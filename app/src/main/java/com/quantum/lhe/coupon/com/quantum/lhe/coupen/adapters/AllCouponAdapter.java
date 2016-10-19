package com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.ApiURLs;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponOverviewModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers.VolleyNetworkController;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.views.CouponDetailsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sharjeel on 5/30/2016.
 */

public class AllCouponAdapter extends RecyclerView.Adapter<AllCouponAdapter.MyViewHolder> {


    Context context;
    VolleyNetworkController networkController;
    ArrayList<CouponOverviewModel> overviewModels = new ArrayList<>();
    int size;

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvDescription;
        public TextView tvDiscount;
        public TextView tvDistance;
        public ImageView ivIsFav;
        public ImageView ivCouponLogo;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_heading);
            tvDescription = (TextView) view.findViewById(R.id.tv_detail);
            tvDiscount = (TextView) view.findViewById(R.id.tv_discount);
            tvDistance = (TextView) view.findViewById(R.id.tv_ShopDist);
            ivIsFav = (ImageView) view.findViewById(R.id.iv_fav);
            ivCouponLogo = (ImageView) view.findViewById(R.id.iv_coupon);
        }
    }


    public AllCouponAdapter(ArrayList<CouponOverviewModel> overviewModels, Context context, VolleyNetworkController networkController) {
        this.context = context;
        this.overviewModels = overviewModels;
        this.networkController = networkController;
        size = overviewModels.size();

    }

    @Override
    public AllCouponAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_coupon_list, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CouponDetailsActivity.class);
                CouponOverviewModel overviewModel = overviewModels.get(0);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MyClass", overviewModel);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return new AllCouponAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AllCouponAdapter.MyViewHolder holder, int position) {

        final CouponOverviewModel overviewModel = overviewModels.get(position);

        holder.tvTitle.setText(overviewModel.getTitle());
        holder.tvDescription.setText(overviewModel.getDescription());
        holder.ivIsFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ApiURLs.BOOKMARK_COUPONS;
                url = url.replace("{id}", overviewModel.getId());
                networkController.UniversalGet(url, (UniversalDataListener) context);
                holder.ivIsFav.setImageResource(R.drawable.heart_filled);

               /* if (!offer_obj.isFav()) {
                    holder.is_fav.setImageResource(R.drawable.offer_fav_p);
                    offer_obj.setFav(true);
                    url = ApiConstants.POST_FAV_OFFERS_URL_KEY+UserId+"&ActivityId="+offer_obj.getActivityId()+"&isDeleted=false";
                    volleyNetworkUtil.PostFavNnO(url);
                    url = "";
//                    updateMalls(offer_obj);
                } else {
                    holder.is_fav.setImageResource(R.drawable.offer_fav);
                    offer_obj.setFav(false);
                    url = ApiConstants.POST_FAV_OFFERS_URL_KEY+UserId+"&ActivityId="+offer_obj.getActivityId()+"&isDeleted=true";
                    volleyNetworkUtil.PostFavNnO(url);
                    url = "";
//                    updateMalls(offer_obj);
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return overviewModels.size();
    }


}