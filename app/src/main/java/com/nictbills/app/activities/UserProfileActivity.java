package com.nictbills.app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.dawai4u.BookMedicineDawai4UActivity;
import com.nictbills.app.activities.dawai4u.Dawai4UDashboardActivity;
import com.nictbills.app.activities.paniwala.PaniwalaDashboardActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.referral_code.GetReferralCodeResponse;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.SaveProfileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.SaveProfileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.get_user_profile.GetUserProfile;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends BaseActivity  {

    private EditText user_name_ET,mobile_number_ET,user_email_ET,pincode_ET,state_ET,alt_mobile_number_ET,user_address_ET,user_city_ET;
    private Button submit_button,share_referral_code;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private String credential,flag,referral_code_id,mobile_number;
    private ImageView backArrow_IMG;
    private TextView referral_code;
    private LinearLayout referral_code_LL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user_name_ET = findViewById(R.id.user_name_ET);
        mobile_number_ET = findViewById(R.id.mobile_number_ET);
        user_email_ET = findViewById(R.id.user_email_ET);
        pincode_ET = findViewById(R.id.pincode_ET);
        state_ET = findViewById(R.id.state_ET);
        alt_mobile_number_ET = findViewById(R.id.alt_mobile_number_ET);
        user_address_ET = findViewById(R.id.user_address_ET);
        submit_button = findViewById(R.id.submit_button);
        user_city_ET = findViewById(R.id.user_city_ET);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        referral_code = findViewById(R.id.referral_code);
        share_referral_code = findViewById(R.id.share_referral_code);
        referral_code_LL = findViewById(R.id.referral_code_LL);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobile_number = sharedPreferences.getString("cred_2", "");

        Intent intent = getIntent();
        flag = intent.getStringExtra("FLAG");


        if (flag.equalsIgnoreCase("DASHBOARD")){
            getUserProfile();
        }

        mobile_number_ET.setText(mobile_number);
        mobile_number_ET.setFocusable(false);
        mobile_number_ET.setEnabled(false);
        mobile_number_ET.setCursorVisible(false);
        mobile_number_ET.setKeyListener(null);

        share_referral_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.use_my_referral_code));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,getString(R.string.use_my_referral_code)+" "+getString(R.string.referral_code)+" "+referral_code_id );
                startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag.equalsIgnoreCase("DAWAI")){

                    Intent intent1 = new Intent(UserProfileActivity.this, BookMedicineDawai4UActivity.class);
                    startActivity(intent1);
                    finish();

                } else if (flag.equalsIgnoreCase("DASHBOARD")){
                    Intent intent1 = new Intent(UserProfileActivity.this,DashboardActivity.class);
                    startActivity(intent1);
                    finish();
                } else if (flag.equalsIgnoreCase("PANIWALA")){
                    Intent intent1 = new Intent(UserProfileActivity.this, PaniwalaDashboardActivity.class);
                    startActivity(intent1);
                    finish();
                }

            }
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    if (isEmpty(user_name_ET)) {

                        Snackbar.make(v, getString(R.string.kindly_enter_your_full_name), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(mobile_number_ET)) {

                        //Toast.makeText(LoginActivity.this, "Invalid Mobile", Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(pincode_ET)){

                        Snackbar.make(v, getString(R.string.enter_pincode), Snackbar.LENGTH_SHORT).show();

                    }  else if (isEmpty(state_ET)){

                        Snackbar.make(v, getString(R.string.kindly_select_state), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(user_city_ET)){

                        Snackbar.make(v, getString(R.string.kindly_select_city), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(user_address_ET)){

                        Snackbar.make(v, getString(R.string.kindly_enter_address), Snackbar.LENGTH_SHORT).show();

                    }else {

                        SaveProfileRequest saveProfileRequest = new SaveProfileRequest();
                        saveProfileRequest.setFull_name(user_name_ET.getText().toString());
                        saveProfileRequest.setAlt_mobile(alt_mobile_number_ET.getText().toString());
                        saveProfileRequest.setEmail(user_email_ET.getText().toString());
                        saveProfileRequest.setPincode(pincode_ET.getText().toString());
                        saveProfileRequest.setState(state_ET.getText().toString());
                        saveProfileRequest.setCity(user_city_ET.getText().toString());
                        saveProfileRequest.setAddress(user_address_ET.getText().toString());
                        addUserProfileDetails( saveProfileRequest);

                    }
                } else {

                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }
            }
        });


    }

    private void addUserProfileDetails(SaveProfileRequest saveProfileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<SaveProfileResponse> call = retroFitInterface.saveUserProfile(credential,saveProfileRequest);

        call.enqueue(new Callback<SaveProfileResponse>() {
            @Override
            public void onResponse(Call<SaveProfileResponse> call, Response<SaveProfileResponse> response) {
                progressDialogDismiss();

                SaveProfileResponse body = response.body();

                if (response.code() == 200) {


                    if (body.getCode().equalsIgnoreCase("ok")){


                        if (flag.equalsIgnoreCase("DAWAI")){

                            Intent intent1 = new Intent(UserProfileActivity.this, BookMedicineDawai4UActivity.class);
                            startActivity(intent1);
                            finish();

                        }else if (flag.equalsIgnoreCase("DASHBOARD")){
                            /*Intent intent1 = new Intent(UserProfileActivity.this,DashboardActivity.class);
                            startActivity(intent1);
                            finish();*/

                            getUserProfile();
                        } else if (flag.equalsIgnoreCase("PANIWALA")){
                            Intent intent1 = new Intent(UserProfileActivity.this, PaniwalaDashboardActivity.class);
                            startActivity(intent1);
                            finish();
                        }

                        //Toast.makeText(UserProfileActivity.this, "added", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(UserProfileActivity.this,getString(R.string.try_again) , Toast.LENGTH_SHORT).show();

                    }



                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(UserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(UserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(UserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(UserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SaveProfileResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(UserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }


    private void getUserProfile() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetUserProfile> call = retroFitInterface.getUserProfile(credential);

        call.enqueue(new Callback<GetUserProfile>() {
            @Override
            public void onResponse(Call<GetUserProfile> call, Response<GetUserProfile> response) {

                GetUserProfile body = response.body();

                if (response.code() == 200) {


                    if (body.getFound().equalsIgnoreCase("yes")){

                        getReferralCode();

                        user_name_ET.setText(body.getUserProfile().getFullName());
                        mobile_number_ET.setText(body.getUserProfile().getRegMobile());
                        alt_mobile_number_ET.setText(body.getUserProfile().getAltMobile());
                        user_email_ET.setText(body.getUserProfile().getEmail());
                        pincode_ET.setText(body.getUserProfile().getPincode());
                        state_ET.setText(body.getUserProfile().getState());
                        user_city_ET.setText(body.getUserProfile().getCity());
                        user_address_ET.setText(body.getUserProfile().getAddress());

                        submit_button.setVisibility(View.GONE);
                        referral_code_LL.setVisibility(View.VISIBLE);

                        user_name_ET.setFocusable(false);
                        user_name_ET.setEnabled(false);
                        user_name_ET.setCursorVisible(false);
                        user_name_ET.setKeyListener(null);

                        mobile_number_ET.setFocusable(false);
                        mobile_number_ET.setEnabled(false);
                        mobile_number_ET.setCursorVisible(false);
                        mobile_number_ET.setKeyListener(null);

                        alt_mobile_number_ET.setFocusable(false);
                        alt_mobile_number_ET.setEnabled(false);
                        alt_mobile_number_ET.setCursorVisible(false);
                        alt_mobile_number_ET.setKeyListener(null);

                        user_email_ET.setFocusable(false);
                        user_email_ET.setEnabled(false);
                        user_email_ET.setCursorVisible(false);
                        user_email_ET.setKeyListener(null);

                        pincode_ET.setFocusable(false);
                        pincode_ET.setEnabled(false);
                        pincode_ET.setCursorVisible(false);
                        pincode_ET.setKeyListener(null);

                        state_ET.setFocusable(false);
                        state_ET.setEnabled(false);
                        state_ET.setCursorVisible(false);
                        state_ET.setKeyListener(null);

                        user_city_ET.setFocusable(false);
                        user_city_ET.setEnabled(false);
                        user_city_ET.setCursorVisible(false);
                        user_city_ET.setKeyListener(null);

                        user_address_ET.setFocusable(false);
                        user_address_ET.setEnabled(false);
                        user_address_ET.setCursorVisible(false);
                        user_address_ET.setKeyListener(null);

                    } else {
                        progressDialogDismiss();



                    }



                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(UserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(UserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(UserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(UserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetUserProfile> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(UserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getReferralCode() {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetReferralCodeResponse> call = retroFitInterface.getReferralCode(credential);

        call.enqueue(new Callback<GetReferralCodeResponse>() {
            @Override
            public void onResponse(Call<GetReferralCodeResponse> call, Response<GetReferralCodeResponse> response) {
                progressDialogDismiss();
                GetReferralCodeResponse body = response.body();

                if (response.code() == 200) {


                    referral_code.setText(body.getReferral_code());

                    referral_code_id = body.getReferral_code();

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(UserProfileActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(UserProfileActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(UserProfileActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(UserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetReferralCodeResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(UserProfileActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (flag.equalsIgnoreCase("DAWAI")){

            Intent intent1 = new Intent(UserProfileActivity.this, Dawai4UDashboardActivity.class);
            startActivity(intent1);
            finish();

        } else if (flag.equalsIgnoreCase("DASHBOARD")){
            Intent intent1 = new Intent(UserProfileActivity.this,DashboardActivity.class);
            startActivity(intent1);
            finish();

        } else if (flag.equalsIgnoreCase("PANIWALA")){
            Intent intent1 = new Intent(UserProfileActivity.this, PaniwalaDashboardActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}