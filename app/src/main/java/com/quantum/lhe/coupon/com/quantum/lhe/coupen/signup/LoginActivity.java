package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Statics;

/**
 * Created by appster on 6/17/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    Button button_send, button_back;
    TextView textView_signup, textView_forgotpass;
    EditText editText_email, editText_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
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
                    toast("Login successful");
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
}
