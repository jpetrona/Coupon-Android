package com.quantum.lhe.coupon.com.quantum.lhe.coupen.signup;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.constants.AppConstants;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.listener.UniversalDataListener;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.UserCreationModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.models.UserDataModel;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.networkcontrollers.VolleyNetworkController;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Statics;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.UserInfoDialog;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Utils;
import com.quantum.lhe.coupon.com.quantum.lhe.coupen.views.AllCouponActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static com.quantum.lhe.coupon.com.quantum.lhe.coupen.utils.Utils.getRoundedCornerBitmap;

/**
 * Created by appster on 6/17/2016.
 */
public class SignUpActivity extends Activity implements UniversalDataListener {
    Button button_back, button_signup;
    ImageView imageView_photo;
    Bitmap profile_image_bitmap = null;
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

    private void selectProfileImage(String dialogTitle) {
        final CharSequence[] options = {getString(R.string.take_photo_title), getString(R.string
                .choose_from_gallery), getString(R.string.cancel_title)};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals(getString(R.string.take_photo_title))) {
                    requestForCameraPermission(getString(R.string.take_photo_title));
                } else if (options[item].equals(getString(R.string.choose_from_gallery))) {
                    requestForCameraPermission(getString(R.string.choose_from_gallery));

                } else if (options[item].equals(getString(R.string.cancel_title))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstants.ACTION_TAKE_PHOTO_IMAGEVIEW) {
            if (resultCode == RESULT_OK) {

                try {

                    rotatePicture(file,true);
//                    saveUserProfile();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == AppConstants.ACTION_TAKE_PHOTO_FROM_GALLERY) {

            if (resultCode == RESULT_OK) {

                try {

                    Uri selectedImageUri = data.getData();
                    rotatePicture(selectedImageUri,false);
//                    saveUserProfile();


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }

    }


    private int REQUEST_CAMERA_PERMISSION = 100;
    final private int REQUEST_EXTERNAL_PERMISSIONS = 124;
    public void requestForCameraPermission(String request) {

        int rCode = REQUEST_EXTERNAL_PERMISSIONS;

        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();

        if (request.equals(getString(R.string.take_photo_title))) {
            rCode = REQUEST_CAMERA_PERMISSION;
            if (!addPermission(permissionsList, android.Manifest.permission.CAMERA))
                permissionsNeeded.add("Camera");
        } else
            rCode = REQUEST_EXTERNAL_PERMISSIONS;

        if (!addPermission(permissionsList, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("Write External Storage");
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("Read External Storage");


        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = getString(R.string.error_permission_required) + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);

                final int finalRCode = rCode;
                UserInfoDialog.createDialog(this, message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(SignUpActivity.this,
                                permissionsList.toArray(new String[permissionsList.size()]),
                                finalRCode);
                    }
                });

            }
            ActivityCompat.requestPermissions(this,
                    permissionsList.toArray(new String[permissionsList.size()]),
                    rCode);
        } else {

            if (rCode == REQUEST_CAMERA_PERMISSION)
                dispatchTakePictureIntent();
            else
                openGallery();
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                // Show permission rationale
                return false;
            }
        }
        return true;
    }

    private Uri file;
    // New function for captureImageIntent
    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(Utils.getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        startActivityForResult(intent, AppConstants.ACTION_TAKE_PHOTO_IMAGEVIEW);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, AppConstants.ACTION_TAKE_PHOTO_FROM_GALLERY);
    }

    private void rotatePicture(Uri uri, boolean isCam) {
        try {
            String picturePath;
            if (!isCam)
                picturePath = getPath(uri);
            else
                picturePath = uri.getPath();

            int mDstHeight = imageView_photo.getMeasuredHeight();/*getResources().getDimensionPixelSize(R.dimen.createview_destination_height);*/
            int mDstWidth = imageView_photo.getMeasuredWidth();
            profile_image_bitmap = Utils.loadImage(picturePath,mDstWidth,mDstHeight);
//            profile_image_bitmap = getRoundedCornerBitmap(profile_image_bitmap,100);
            imageView_photo.setImageBitmap(profile_image_bitmap);
/*//            Log.e("", "image from gallery path " + picturePath);
            //SharedPreferenceManager.saveString(RegistrationProfileActivity.this, GlobelConstants.profile_image, profile_image_array);
            Bitmap bitmapSelectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
//            Bitmap bitmapSelectedImage = BitmapFactory.decodeFile(picturePath);
            if (bitmapSelectedImage == null) {
                selectProfileImage(getString(R.string.reselect_picture_title));
                return;
            }
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(picturePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            Bitmap bmRotated = Utils.rotateBitmap(bitmapSelectedImage, orientation);
            profile_image_bitmap = bmRotated;
            imageView_user.setImageBitmap(bmRotated);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String index = cursor.getString(column_index);
        cursor.close();
        return index;
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


}
