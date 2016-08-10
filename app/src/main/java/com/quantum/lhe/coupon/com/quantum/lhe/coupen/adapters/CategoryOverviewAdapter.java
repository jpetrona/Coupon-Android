package com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CategoryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sharjeel on 5/30/2016.
 */

public class CategoryOverviewAdapter extends ArrayAdapter<CategoryModel> {

    Context mContext;

    Activity activity;

    private ArrayList<CategoryModel> CategoryModelArrayList;


    public CategoryOverviewAdapter(Context c, Activity activity, int resource, ArrayList<CategoryModel> CategoryModelArrayList) {
        super(c,resource);
        mContext = c;
        this.activity = activity;
        this.CategoryModelArrayList = CategoryModelArrayList;
    }

    static class ViewHolder {
        TextView title;
        ImageView card_image;
    }

    @Override
    public int getCount() {
        return CategoryModelArrayList.size();
    }

    @Override
    public CategoryModel getItem(int position) {
        return this.CategoryModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getPosition(CategoryModel item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        View view = convertView;

        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.row_cat_list, null);
            holder = new ViewHolder();
            holder.title = (TextView) 	view.findViewById(R.id.grid_text);
            holder.card_image = (ImageView) 	view.findViewById(R.id.grid_image);
            view.setTag(holder);

        }else
            holder = (ViewHolder) view.getTag();

        final CategoryModel fav_obj= getItem(position);
//        holder.title.setText(fav_obj.getCardTitle());
//        Picasso.with(mContext).load(fav_obj.getFrontImageUrl()).transform(transformation).placeholder(R.drawable.mallapp_placeholder).fit().into(holder.card_image);


        return view;

    }
}