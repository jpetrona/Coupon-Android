package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantum.lhe.coupon.R;

/**
 * Created by appster on 6/17/2016.
 */
public class SignUpActivity extends Activity {
    Button button_back, button_signup;
    ImageView imageView_photo;
    TextView textView_login, textView_dob;
    EditText editText_name, editText_email, editText_pass, editText_pass_c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);
        button_back = (Button) findViewById(R.id.button_back);
        button_signup = (Button) findViewById(R.id.button_signup);
        textView_login = (TextView) findViewById(R.id.textView_login);
        textView_dob = (TextView) findViewById(R.id.textView_dob);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_pass = (EditText) findViewById(R.id.editText_pass);
        editText_pass_c = (EditText) findViewById(R.id.editText_c_pass);
        editText_email = (EditText) findViewById(R.id.editText_email);
        imageView_photo = (ImageView) findViewById(R.id.imageView_add);
        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
