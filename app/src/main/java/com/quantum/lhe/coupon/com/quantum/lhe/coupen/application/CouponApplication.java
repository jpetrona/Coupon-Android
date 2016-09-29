package com.quantum.lhe.coupon.com.quantum.lhe.coupen.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.PreferencesHandler;

/**
 * Created by Quantum on 5/4/2016.
 */

//@ReportsCrashes(formKey = "", mailTo = "adj@quantumcph.com",
//        mode = ReportingInteractionMode.SILENT, resToastText = R.string.app_name)


public class CouponApplication extends Application {


    private RequestQueue mRequestQueue;
    private static CouponApplication mInstance;
    private static final String TAG = CouponApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        //GetCurrentDate.changeBirthdayDateFormat("2015-08-12T09:10:30.9900000");
        // The following line triggers the initialization of ACRA

//        ACRA.init(this);
        mInstance = this;

        new PreferencesHandler(this);

        // init Flurry
//        FlurryAgent.init(this, FLURRY_API_KEY);

        // facebook sdk initialize
//        FacebookSdk.sdkInitialize(getApplicationContext());

        //	SugarContext.init(this);

        // Simply add the handler, and that's it! No need to add any code
        // to every activity. Everything is contained in MyLifecycleHandler
        // with just a few lines of code. Now *that's* nice.
//        registerActivityLifecycleCallbacks(new MyLifecycleHandler());




//         ACRA.getErrorReporter().handleException(null);
    }


    public static synchronized CouponApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

//	public ImageLoader getImageLoader() {
//		getRequestQueue();
//		if (mImageLoader == null) {
//			mImageLoader = new ImageLoader(this.mRequestQueue,
//					new LruBitmapCache());
//		}
//		return this.mImageLoader;
//	}

    public <T> void addToRequestQueue(Request<T> req, String tag) {

        req.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {

        req.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //SugarContext.terminate();
    }

    //    public  void cancelAllRequests(){
//        if(mRequestQueue !=null){
//            mRequestQueue.
//        }
//    }

}
