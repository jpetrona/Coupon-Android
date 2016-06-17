package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.lhe.coupon.R;

/**
 * Created by appster on 6/16/2016.
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView_cancel, textView_login, textView_signup;
    Button button_syncfb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        button_syncfb = (Button) findViewById(R.id.synce_facebook);
        textView_cancel = (TextView) findViewById(R.id.textView_cancel);
        textView_login = (TextView) findViewById(R.id.textView_login);
        textView_signup = (TextView) findViewById(R.id.textView_sign_up);
        button_syncfb.setOnClickListener(this);
        textView_cancel.setOnClickListener(this);
        textView_login.setOnClickListener(this);
        textView_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.synce_facebook:
                toast("fb");
                break;
            case R.id.textView_login:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_sign_up:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_cancel:
                finish();
                break;
            default:
                break;
        }

    }

    public void toast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }
}
