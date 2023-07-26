package com.nictbills.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.Get_otp_dto;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerifyActivity extends BaseActivity {

    private TextView counttime_TV,resent_OTP_TV,mobile_number_confirm_TV;
    private int counter;
    private ImageView backArrow_IMG;
    private EditText et1,et2,et3,et4,et5;
    private Button verify_button;
    private String mobileNumber,edt1,edt2,edt3,edt4,edt5,fullOTP,token,referral_code;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_verify);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        counttime_TV = findViewById(R.id.counttime_TV);
        resent_OTP_TV = findViewById(R.id.resent_OTP_TV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        verify_button = findViewById(R.id.verify_button);
        mobile_number_confirm_TV = findViewById(R.id.mobile_number_confirm_TV);

//        sharedPreferences = this.getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        String cred_1 = sharedPreferences.getString("cre_1", "");
        String cred_2 = sharedPreferences.getString("cre_2", "");
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        Intent intent = getIntent();
        mobileNumber = intent.getStringExtra("mobile");
        referral_code = intent.getStringExtra("referral_code");
        mobile_number_confirm_TV.setText(getString(R.string.kindly_enter_the_OTP_sent_on_your_mobile_number)+" "+ "+91"+mobileNumber);

        noCount();

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                token = task.getResult();
                //tv_token.setText(token);
                Log.e("TAG", "token: "+token);
            }
        });


        resent_OTP_TV.setOnClickListener(new View.OnClickListener() {
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


                Get_otp_dto mobile = new Get_otp_dto();
                mobile.setMobile(mobileNumber);
                mobile.setReferred_by(referral_code);
                getOTP(mobile);

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


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OTPVerifyActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        verify_button.setOnClickListener(new View.OnClickListener() {
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

                edt1 = et1.getText().toString();
                edt2 = et2.getText().toString();
                edt3 = et3.getText().toString();
                edt4 = et4.getText().toString();
                edt5 = et5.getText().toString();



                if (isEmpty(et1) || isEmpty(et2) || isEmpty(et3) || isEmpty(et4) || isEmpty(et5)){

                    Snackbar.make(v, getString(R.string.invalid_OTP), Snackbar.LENGTH_SHORT).show();

                } else {

                    fullOTP =  edt1+edt2+edt3+edt4+edt5;

                    Get_otp_dto otp = new Get_otp_dto();
                    otp.setMobile(mobileNumber);
                    otp.setOtp(fullOTP);
                    otp.setFbToken(token);
                    verifyOTP(otp);

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

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et2.requestFocus();
                }
                else if(s.length()==0)
                {
                    et1.clearFocus();
                }
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et3.requestFocus();
                }
                else if(s.length()==0)
                {
                    et1.requestFocus();
                }
            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et4.requestFocus();
                }
                else if(s.length()==0)
                {
                    et2.requestFocus();
                }
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    et5.requestFocus();
                }
                else if(s.length()==0)
                {
                    et3.requestFocus();
                }
            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {

                    et5.clearFocus();
                }
                else if(s.length()==0)
                {
                    et4.requestFocus();
                }
            }
        });


    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void noCount(){

        new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime_TV.setText(String.valueOf(counter));
                counttime_TV.setText( getString(R.string.resend_OTP_in)+" " + millisUntilFinished / 1000 + " " + "sec");

            }
            @Override
            public void onFinish() {
                counttime_TV.setVisibility(View.GONE);
                resent_OTP_TV.setVisibility(View.VISIBLE);
            }
        }.start();


    }


    private void getOTP(Get_otp_dto mobile) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(OTPVerifyActivity.this);
        progressLog.setTitle(getString(R.string.getting_OTP));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<Get_otp_dto> call = retroFitInterface.getCustomerOtp(mobile);

        call.enqueue(new Callback<Get_otp_dto>() {
            @Override
            public void onResponse(Call<Get_otp_dto> call, Response<Get_otp_dto> response) {
                progressLog.dismiss();

                Get_otp_dto profileDTO = response.body();

                if (response.code() == 200) {

                    String otpStatus = profileDTO.getCode();

                    if (otpStatus.equalsIgnoreCase("OK")){

                        Toast.makeText(OTPVerifyActivity.this, getString(R.string.otp_resend_successfully), Toast.LENGTH_SHORT).show();
                        noCount();

                    }else if (otpStatus.equalsIgnoreCase("500")){

                        Toast.makeText(OTPVerifyActivity.this, getString(R.string.kindly_mentioned_correct_mobile_number), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }


                }else if (response.code() == 401){

                    Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 500) {

                    Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {

                    Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<Get_otp_dto> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());
                Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent =new Intent(OTPVerifyActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }


    private void verifyOTP(Get_otp_dto otp) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<Get_otp_dto> call = retroFitInterface.verifyOtp(otp);

        call.enqueue(new Callback<Get_otp_dto>() {
            @Override
            public void onResponse(Call<Get_otp_dto> call, Response<Get_otp_dto> response) {
                progressDialogDismiss();

                Get_otp_dto profileDTO = response.body();

                if (response.code() == 200) {

                    String otpStatus = profileDTO.getCode();

                    if (otpStatus.equalsIgnoreCase("OK")){


                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("cred_1", profileDTO.getApiKey());
                        editor.putString("cred_2", mobileNumber);
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();
                        editor.commit();

                        Intent intent = new Intent(OTPVerifyActivity.this,DashboardActivity.class);
                        startActivity(intent);
                        finish();

                    }else if (otpStatus.equalsIgnoreCase("ERROR")){

                        Toast.makeText(OTPVerifyActivity.this, getString(R.string.otp_verification_failed), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    }


                }else if (response.code() == 401){

                    Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 500) {

                    Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {

                    Toast.makeText(OTPVerifyActivity.this, getString(R.string.kindly_mentioned_correct_OTP), Toast.LENGTH_SHORT).show();

                }

            }


            @Override
            public void onFailure(Call<Get_otp_dto> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(OTPVerifyActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }




}
