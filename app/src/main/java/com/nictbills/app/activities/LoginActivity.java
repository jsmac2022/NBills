package com.nictbills.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.Get_otp_dto;
import com.nictbills.app.utils.Util;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText mobile_EditText,referral_code_EditText;
    private Button get_OTP_Button;
    private RetrofitInterface retroFitInterface;
    private String mobileNumber;
    private SharedPreferences sharedPreferences;
    private CheckBox referral_code_CB;
    private LinearLayout referral_code_LL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        get_OTP_Button = findViewById(R.id.get_OTP_Button);
        mobile_EditText = findViewById(R.id.mobile_EditText);
        referral_code_CB = findViewById(R.id.referral_code_CB);
        referral_code_LL = findViewById(R.id.referral_code_LL);
        referral_code_EditText = findViewById(R.id.referral_code_EditText);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);



        if (isLoggedIn) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();

        } else {

            referral_code_CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        referral_code_LL.setVisibility(View.VISIBLE);

                    }
                    else
                    {

                        referral_code_LL.setVisibility(View.GONE);
                    }
                }
            });


        get_OTP_Button.setOnClickListener(new View.OnClickListener() {
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

                    mobileNumber = mobile_EditText.getText().toString();

                    if (isEmpty(mobile_EditText)) {

                        Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                    } else if (!validMobile(mobileNumber)) {

                        //Toast.makeText(LoginActivity.this, "Invalid Mobile", Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } else if (!isValidMobileNumber(mobile_EditText.getText().toString())){

                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } else {

                     if (isEmpty(referral_code_EditText)){

                         Get_otp_dto mobile = new Get_otp_dto();
                         mobile.setMobile(mobileNumber);
                         mobile.setReferred_by("");
                         getOTP(mobile);

                     }else {

                         Get_otp_dto mobile = new Get_otp_dto();
                         mobile.setMobile(mobileNumber);
                         mobile.setReferred_by(referral_code_EditText.getText().toString());
                         getOTP(mobile);

                     }

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
    }

    public static boolean isValidMobileNumber(String s) {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 6, 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private boolean validMobile(String str){
        return str.trim().length()!=0 && str!=null && str.length()==10 ;
    }



    private void getOTP(Get_otp_dto mobile) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(LoginActivity.this);
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

                        if (isEmpty(referral_code_EditText)){
                            Intent intent = new Intent(LoginActivity.this,OTPVerifyActivity.class);
                            intent.putExtra("mobile",mobileNumber);
                            intent.putExtra("referral_code","");
                            startActivity(intent);
                            finish();
                        }else {

                            Intent intent = new Intent(LoginActivity.this,OTPVerifyActivity.class);
                            intent.putExtra("mobile",mobileNumber);
                            intent.putExtra("referral_code",referral_code_EditText.getText().toString());
                            startActivity(intent);
                            finish();
                        }



                    }else if (otpStatus.equalsIgnoreCase("500")){

                        Toast.makeText(LoginActivity.this, getString(R.string.kindly_mentioned_correct_mobile_number), Toast.LENGTH_SHORT).show();

                    } else if(otpStatus.equalsIgnoreCase("ERROR")){

                        Toast.makeText(LoginActivity.this,profileDTO.getMessage(), Toast.LENGTH_LONG).show();

                    }else {

                        Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }


                }else if (response.code() == 401){

                    Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 500) {

                    Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {

                    Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<Get_otp_dto> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(LoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
            new AlertDialog.Builder(this)
                    //.setTitle("Really Exit?")
                    .setMessage(getString(R.string.are_you_sure_you_want_to_exit))
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            setResult(RESULT_OK, new Intent().putExtra(getString(R.string.exit), true));
                            finish();
                        }

                    }).create().show();

    }



}
