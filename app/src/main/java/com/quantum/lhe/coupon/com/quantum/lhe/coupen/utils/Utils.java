package com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Sharjeel on 8/10/2016.
 */

public class Utils {





    /*
   * Get Unique Identifier for each device
   */
    public static String getUUID(Context context) {
        TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tManager.getDeviceId();
        return uuid;
    }


    /*
    * Base64 String to Bitmap
    */
    public Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    /*
    * Bitmap to Base64 String
    */
    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /*
    * Checks whether the Value String is Empty or Not
    */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value.trim())) {
            return true;
        }
        return false;
    }
}
