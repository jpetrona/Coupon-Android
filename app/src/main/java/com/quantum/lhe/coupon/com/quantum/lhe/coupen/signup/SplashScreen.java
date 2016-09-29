package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.PreferencesHandler;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Utils;

import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants.UUID;

/**
 * Created by appster on 6/16/2016.
 */
public class SplashScreen extends AppCompatActivity {
    Button button_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_splash_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            requestForPermission();
        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void requestForPermission() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(android.Manifest.permission.READ_PHONE_STATE)) {
                showMessageOKCancel(getResources().getString(R.string.permission_text),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        PreferencesHandler.updatePreferences(UUID, Utils.getUUID(this));
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.ok), okListener)
                .setNegativeButton(getResources().getString(R.string.cancel), null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted

                PreferencesHandler.updatePreferences(UUID, Utils.getUUID(this));

            } else {
//                     Permission Denied
                Toast.makeText(this, R.string.permission_deny, Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
