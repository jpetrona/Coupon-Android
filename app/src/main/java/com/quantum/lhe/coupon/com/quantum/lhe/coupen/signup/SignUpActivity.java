package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quantum.lhe.coupon.R;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.ApiURLs;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.UserCreationModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.UserDataModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers.VolleyNetworkController;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Statics;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.views.AllCouponActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by appster on 6/17/2016.
 */
public class SignUpActivity extends Activity implements UniversalDataListener {
    Button button_back, button_signup;
    ImageView imageView_photo;
    TextView textView_login, textView_dob;
    EditText editText_name, editText_email, editText_pass, editText_pass_c;
    VolleyNetworkController networkController;
    String ProfilePicBase64;
    String[] value;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signup_screen);

        networkController = new VolleyNetworkController(this);

        button_back = (Button) findViewById(R.id.button_back);
        button_signup = (Button) findViewById(R.id.button_signup);
        textView_login = (TextView) findViewById(R.id.textView_login);
        textView_dob = (TextView) findViewById(R.id.textView_dob);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_pass = (EditText) findViewById(R.id.editText_pass);
        editText_pass_c = (EditText) findViewById(R.id.editText_c_pass);
        editText_email = (EditText) findViewById(R.id.editText_email);
        imageView_photo = (ImageView) findViewById(R.id.imageView_add);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);

        value = new String[]{((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString()};
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                value[0] = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
            }
        });

        textView_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkvalidation()) {
                    try {
                        progressDialog = ProgressDialog.show(SignUpActivity.this,"",getString(R.string.plz_wait));
                        UserDataModel informationModel = new UserDataModel();
                        informationModel.setName(editText_name.getText().toString());
                        informationModel.setEmail(editText_email.getText().toString());
                        informationModel.setDateOfBirth(textView_dob.getText().toString());
                        informationModel.setGender("M");
                        informationModel.setCountryId("DK");

                        UserCreationModel creationModel = new UserCreationModel();
                        creationModel.setUserData(informationModel);
                        creationModel.setAcceptTerms(true);
                        creationModel.setPassword(editText_pass.getText().toString());
                        creationModel.setConfirmPassword(editText_pass_c.getText().toString());
//                        informationModel.setPicture(ProfilePicBase64);
                        Gson gson = new Gson();
                        String json = gson.toJson(creationModel);
                        JSONObject jsonObject = new JSONObject(json);
                        networkController.UniversalPost(jsonObject, ApiURLs.CREATE_USER, SignUpActivity.this);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        imageView_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectProfileImage(getString(R.string.add_photo));
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textView_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_date();
            }
        });


    }

    public boolean checkvalidation() {
        if (editText_name.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.enter_name, Toast.LENGTH_LONG).show();
        } else if (editText_email.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.enter_email, Toast.LENGTH_LONG).show();
        } else if (!Statics.checkEmail(editText_email.getText().toString().trim())) {
            Toast.makeText(this, R.string.enter_valid_email, Toast.LENGTH_LONG).show();
        } else if (editText_pass.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.enter_pass, Toast.LENGTH_LONG).show();
        } else if (editText_pass_c.getText().toString().trim().length() == 0) {
            Toast.makeText(this, R.string.confirm_pass, Toast.LENGTH_LONG).show();
        } else if (!editText_pass.getText().toString().trim().equals(editText_pass_c.getText().toString().trim())) {
            Toast.makeText(this, R.string.pass_mismatch, Toast.LENGTH_LONG).show();
        } else if (textView_dob.getText().toString().equals(getString(R.string.date_of_birth))) {
            Toast.makeText(this, R.string.dob_enter, Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }

    private void selectProfileImage(String dialogTitle) {
        final CharSequence[] options = {getResources().getString(R.string.take_photo), getResources().getString(R.string.choose_gallery), getResources().getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals(getString(R.string.take_photo))) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, 0);
                } else if (options[item].equals(getString(R.string.choose_gallery))) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, 1);
                } else if (options[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView_photo.setImageBitmap(photo);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                byte[] b = baos.toByteArray();
                ProfilePicBase64 = Base64.encodeToString(b, Base64.DEFAULT);


            }
        } else if (requestCode == 1) {

            Log.e("", "image from gallery ");
            if (resultCode == Activity.RESULT_OK) {

                try {
                    Uri selectedImageUri = data.getData();
                    String picturePath = getPath(selectedImageUri);

                    Bitmap bitmapSelectedImage = Statics.getCameraPhotoOrientation(this, selectedImageUri, picturePath, BitmapFactory.decodeFile(picturePath));

                    if (bitmapSelectedImage == null) {
                        selectProfileImage("Re-Select Picture");
                        return;
                    }
                    imageView_photo.setImageBitmap(bitmapSelectedImage);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmapSelectedImage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                    byte[] b = baos.toByteArray();
                    ProfilePicBase64 = Base64.encodeToString(b, Base64.DEFAULT);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void show_date() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                if (view.isShown()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                    Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                    System.out.println("Date : " + sdf.format(calendar.getTime()));
                    System.out.println("Date : " + year + monthOfYear + dayOfMonth);

                    textView_dob.setText("" + sdf.format(calendar.getTime()));
                    monthOfYear = monthOfYear + 1;
                    // location.requestFocus();

                }

            }
        }, year, month, day);
        Calendar maxDate = Calendar.getInstance();
        maxDate.set(Calendar.YEAR, 2000);
        dpd.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
        dpd.show();
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onDataReceived(JSONObject jsonObject) {

        progressDialog.dismiss();
        Toast.makeText(SignUpActivity.this, "SignUp successful", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SignUpActivity.this, AllCouponActivity.class);
        startActivity(intent);
        Log.d("RegistrationResponse", jsonObject.toString());
    }

    @Override
    public void onDataReceived(JSONArray jsonArray) {

    }

    @Override
    public void OnError(String message) {
        progressDialog.dismiss();
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
