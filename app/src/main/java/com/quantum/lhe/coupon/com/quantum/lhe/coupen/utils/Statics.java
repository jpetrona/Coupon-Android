package com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils;

import android.app.ProgressDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by appster on 3/21/2016.
 */
public class Statics {
    public static ProgressDialog pDialog;
    public static final String PREF_KEY = "PROFILE";

    public static void showpDialog(Context context) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public static void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    public static String changeDateFormat(String encode_date) {
        if (!encode_date.equals("null")) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat sdf_1 = new SimpleDateFormat("dd-MM-yyyy kk:mm", Locale.getDefault());

            Date date = null;
            try {
                date = sdf.parse(encode_date);
                encode_date = sdf_1.format(date);

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            encode_date = "";
        }
        return encode_date;
    }
}
