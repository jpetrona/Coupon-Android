package com.quantum.lhe.coupon.com.quantum.lhe.coupen.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.AddressModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.CouponOverviewModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.FromModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.GpsModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.OpeningHoursModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.ShopsModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.ToModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.PreferencesHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.LAT;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.LONG;

/**
 * Created by Sharjeel on 9/26/2016.
 */

public class ShopsExpandableAdapter extends BaseExpandableListAdapter {


    private Context _context;
    private Activity activity;
    private ArrayList<ShopsModel> listShops;
    private ArrayList<String> _listDataHeader;
    ShopsModel shopsModel;

    static class ViewHolder {
        TextView tvAddress, tvCity, tvTel, tvEmail, tvWebAdd;
        LinearLayout linear_timing_layout;
        Button btnRoute;

    }

    public ShopsExpandableAdapter(Context context, Activity activity,
                                  ArrayList<ShopsModel> listShops) {
        this._context = context;
        this.activity = activity;
        this.listShops = listShops;
    }

    @Override
    public int getGroupCount() {
        return listShops.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return shopsModel = listShops.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return shopsModel = listShops.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        ShopsModel shopsModel = listShops.get(groupPosition);

        LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.row_expand_shop_group, parent, false);
        TextView tvShopName = (TextView) convertView.findViewById(R.id.tv_ShopName);
        TextView tvShopDistance = (TextView) convertView.findViewById(R.id.tv_ShopDist);
        ImageView imageView_arrow = (ImageView) convertView.findViewById(R.id.indicator);


        tvShopName.setText(shopsModel.getName());
        tvShopDistance.setText(getDist(shopsModel) + " Km");

        if (isExpanded) {
            imageView_arrow.setImageResource(R.drawable.expand_arrow);
        } else {
            imageView_arrow.setImageResource(R.drawable.closed_arrow);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        final ShopsModel shopsModel = listShops.get(groupPosition);

        LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.row_expand_shop_child, parent, false);

        holder = new ViewHolder();
        holder.linear_timing_layout = (LinearLayout) convertView.findViewById(R.id.layout_timings);
        holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_add);
        holder.tvCity = (TextView) convertView.findViewById(R.id.tv_city);
        holder.tvTel = (TextView) convertView.findViewById(R.id.tv_tel);
        holder.tvWebAdd = (TextView) convertView.findViewById(R.id.tv_web);
        holder.tvEmail = (TextView) convertView.findViewById(R.id.tv_mail);
        holder.btnRoute = (Button) convertView.findViewById(R.id.btn_route);

        AddressModel addressModel = shopsModel.getAddress();
        holder.tvAddress.setText(addressModel.getAddress());
        holder.tvCity.setText(addressModel.getZipCode() + " " + addressModel.getCity());
        holder.tvTel.setText("Tel. " + " +923364641254");
        holder.tvWebAdd.setText("www.gracias.dk");
        holder.tvEmail.setText("info@gracias.dk");

        OpeningHoursModel[] openingHoursModel = shopsModel.getOpeningHours();
        String[] dayNames = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (OpeningHoursModel hoursModel : openingHoursModel
                ) {
            final RelativeLayout newView = (RelativeLayout) infalInflater.inflate(R.layout.coupon_timing_layout, null);
            TextView tvDay = (TextView) newView.findViewById(R.id.tv_d);
            TextView tvTime = (TextView) newView.findViewById(R.id.tv_t);

            String name = dayNames[Integer.parseInt(hoursModel.getDayOfWeek()) - 1];
            tvDay.setText(name);
            ToModel toModel = hoursModel.getTo();
            FromModel fromModel = hoursModel.getFrom();
            tvTime.setText(fromModel.getHours() + ":00" + "-" + toModel.getHours() + ":00");
            holder.linear_timing_layout.addView(newView);
        }

        final GpsModel gpsModel = shopsModel.getGps();
        holder.btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", Double.parseDouble(gpsModel.getLatitude()), Double.parseDouble(gpsModel.getLongitude()), shopsModel.getCity());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                _context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    String getDist(ShopsModel shopsModel) {
        double lat = (double) PreferencesHandler.getObjectPreferences(LAT, double.class);
        double lon = (double) PreferencesHandler.getObjectPreferences(LONG, double.class);
        Location locationA = new Location("point A");
        Location locationB = new Location("point B");
        GpsModel gpsModel = shopsModel.getGps();
        locationA.setLatitude(Double.parseDouble(gpsModel.getLatitude()));
        locationA.setLongitude(Double.parseDouble(gpsModel.getLongitude()));
        locationB.setLatitude(lat);
        locationB.setLongitude(lon);
        float distance = locationA.distanceTo(locationB);
        double kilometers = distance * 0.001;

        return String.format("%.1f", kilometers);
    }
}
