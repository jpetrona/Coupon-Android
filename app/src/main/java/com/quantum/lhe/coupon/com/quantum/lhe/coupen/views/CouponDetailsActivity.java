package com.quantum.lhe.coupon.com.quantum.lhe.coupen.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.StoreTimingsModel;

/**
 * Created by Sharjeel on 7/12/2016.
 */

public class CouponDetailsActivity extends Activity {

    LinearLayout linear_timing_layout;
    ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_activity);

        linear_timing_layout = (LinearLayout) findViewById(R.id.layout_timings);
        back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        StoreTimingsModel[] timinigs = new StoreTimingsModel[4];

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
            final RelativeLayout newView = (RelativeLayout) getLayoutInflater().inflate(R.layout.timing_layout, null);
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
        }
    }
}