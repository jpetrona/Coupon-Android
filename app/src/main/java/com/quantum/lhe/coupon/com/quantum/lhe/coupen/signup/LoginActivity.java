package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.ApiURLs;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers.VolleyNetworkController;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.NetUtils;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.PreferencesHandler;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Statics;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.views.AllCouponActivity;
import com.yayandroid.locationmanager.LocationBaseActivity;
import com.yayandroid.locationmanager.LocationConfiguration;
import com.yayandroid.locationmanager.LocationManager;
import com.yayandroid.locationmanager.constants.LogType;
import com.yayandroid.locationmanager.constants.ProviderType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.LAT;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.LONG;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.TOKEN;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.TOKEN_ID;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.password;
import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.username;

/**
 * Created by appster on 6/17/2016.
 */
public class LoginActivity extends LocationBaseActivity implements View.OnClickListener, UniversalDataListener {
    Button button_send, button_back;
    TextView textView_signup, textView_forgotpass;
    EditText editText_email, editText_pass;
    VolleyNetworkController networkController;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_screen);
        networkController = new VolleyNetworkController(this);
        button_back = (Button) findViewById(R.id.button_back);
        button_send = (Button) findViewById(R.id.button_login);
        textView_signup = (TextView) findViewById(R.id.textView_signup);
        textView_forgotpass = (TextView) findViewById(R.id.textView_forgotpass);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_pass = (EditText) findViewById(R.id.editText_pass);
        button_send.setOnClickListener(this);
        button_back.setOnClickListener(this);
        textView_signup.setOnClickListener(this);
        textView_forgotpass.setOnClickListener(this);

        LocationManager.setLogType(LogType.GENERAL);
        getLocation();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button_back:
                finish();
                break;
            case R.id.button_login:
                if (checkvalidation()) {
                    if (NetUtils.isNetworkAvailable(this)) {
                        try {
                            progressDialog = ProgressDialog.show(LoginActivity.this,"",getString(R.string.plz_wait));
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put(username, editText_email.getText().toString());
                            jsonObject.put(password, editText_pass.getText().toString());
                            networkController.UniversalPost(jsonObject, ApiURLs.LOGIN_USER, LoginActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }
                break;
            case R.id.textView_signup:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_forgotpass:
                intent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void toast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    public boolean checkvalidation() {
        if (editText_email.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.enter_email, Toast.LENGTH_LONG).show();
        } else if (!Statics.checkEmail(editText_email.getText().toString().trim())) {
            Toast.makeText(this, R.string.enter_valid_email, Toast.LENGTH_LONG).show();
        } else if (editText_pass.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.enter_pass, Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onDataReceived(JSONObject jsonObject) {
        progressDialog.dismiss();
        Log.d("Token", jsonObject.toString());
        try {
            String token = jsonObject.getString(TOKEN_ID);
            PreferencesHandler.updatePreferences(TOKEN, token);
            Intent intent = new Intent(this, AllCouponActivity.class);
            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDataReceived(JSONArray jsonArray) {

    }

    @Override
    public void OnError(String message) {
        progressDialog.dismiss();
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public LocationConfiguration getLocationConfiguration() {
        return new LocationConfiguration()
                .keepTracking(true)
                .askForGooglePlayServices(true)
                .setMinAccuracy(200.0f)
                .setWaitPeriod(ProviderType.GOOGLE_PLAY_SERVICES, 5 * 1000)
                .setWaitPeriod(ProviderType.GPS, 10 * 1000)
                .setWaitPeriod(ProviderType.NETWORK, 5 * 1000)
                .setGPSMessage("Would you mind to turn GPS on?")
                .setRationalMessage("Gimme the permission!");
    }

    @Override
    public void onLocationFailed(int failType) {

    }

    @Override
    public void onLocationChanged(Location location) {
        PreferencesHandler.updatePreferences(LAT, location.getLatitude());
        PreferencesHandler.updatePreferences(LONG, location.getLongitude());
    }
}
