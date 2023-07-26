package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.ResponseVerifyOTP;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.VaccineLoginRequestDto;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.VaccineLoginResponseDto;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.VaccineOTPRequestDto;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineDashboardActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private String mobileNumber,credential,txn_id,vaccine_OTP,is_new_ben;
    private ImageView backArrow_IMG;
    private EditText vaccine_mobile_EditText, vaccine_otp_EditText;
    private Button get_vaccine_OTP_Button,verify_vaccine_OTP_Button;
    private RetrofitInterface retroFitInterface;
    private LinearLayout otp_Linear_layout;
    private TextView resend_otp_TV;
    private boolean covidlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_dashboard);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
        covidlogin = sharedPreferences.getBoolean("covid_status", false);
        is_new_ben = sharedPreferences.getString("is_new", "");


        if (covidlogin) {

            if (is_new_ben.trim().equalsIgnoreCase("Y")){

                Intent intent = new Intent(VaccineDashboardActivity.this, AddNewCovidBeneficiaryActivity.class);
                intent.putExtra("COME","LOGIN");
                startActivity(intent);
                finish();

            } else {

                Intent intent = new Intent(VaccineDashboardActivity.this, BeneficiaryListActivity.class);
                startActivity(intent);
                finish();
            }
        } else {

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        vaccine_mobile_EditText = findViewById(R.id.vaccine_mobile_EditText);
        get_vaccine_OTP_Button = findViewById(R.id.get_vaccine_OTP_Button);
        vaccine_otp_EditText = findViewById(R.id.vaccine_otp_EditText);
        verify_vaccine_OTP_Button = findViewById(R.id.verify_vaccine_OTP_Button);
        otp_Linear_layout = findViewById(R.id.otp_Linear_layout);
        resend_otp_TV = findViewById(R.id.resend_otp_TV);


        vaccine_mobile_EditText.setText(mobileNumber);

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VaccineDashboardActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });


        get_vaccine_OTP_Button.setOnClickListener(new View.OnClickListener() {
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

                    mobileNumber = vaccine_mobile_EditText.getText().toString();

                    if (isEmpty(vaccine_mobile_EditText)) {

                        Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                    } else if (!validMobile(mobileNumber)) {

                        //Toast.makeText(LoginActivity.this, "Invalid Mobile", Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } else if (!isValidMobileNumber(vaccine_mobile_EditText.getText().toString())){

                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } else {
                        VaccineLoginRequestDto mobile = new VaccineLoginRequestDto();
                        mobile.setMobile(mobileNumber);
                        getVaccineOTP(mobile);

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


        resend_otp_TV.setOnClickListener(new View.OnClickListener() {
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

                    mobileNumber = vaccine_mobile_EditText.getText().toString();

                    if (isEmpty(vaccine_mobile_EditText)) {

                        Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                    } else if (!validMobile(mobileNumber)) {

                        //Toast.makeText(LoginActivity.this, "Invalid Mobile", Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } else if (!isValidMobileNumber(vaccine_mobile_EditText.getText().toString())){

                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } else {

                        VaccineLoginRequestDto mobile = new VaccineLoginRequestDto();
                        mobile.setMobile(mobileNumber);
                        getVaccineOTP(mobile);

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


        verify_vaccine_OTP_Button.setOnClickListener(new View.OnClickListener() {
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

                    vaccine_OTP = vaccine_otp_EditText.getText().toString();

                    if (isEmpty(vaccine_otp_EditText)) {

                        Snackbar.make(v, getString(R.string.kindly_enter_the_OTP_sent_on_your_mobile_number), Snackbar.LENGTH_SHORT).show();

                    }  else {

                        VaccineOTPRequestDto vaccineOTPRequestDto = new VaccineOTPRequestDto();
                        vaccineOTPRequestDto.setOtp(vaccine_OTP);
                        vaccineOTPRequestDto.setTxnId(txn_id);
                        verifyVaccineOTP(vaccineOTPRequestDto);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(VaccineDashboardActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    public static boolean isValidMobileNumber(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
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


    private void getVaccineOTP(VaccineLoginRequestDto mobile) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(VaccineDashboardActivity.this);
        progressLog.setTitle(getString(R.string.getting_OTP));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL_NICT).create(RetrofitInterface.class);

        Call<VaccineLoginResponseDto> call = retroFitInterface.getVaccineOTP(credential,mobile);

        call.enqueue(new Callback<VaccineLoginResponseDto>() {
            @Override
            public void onResponse(Call<VaccineLoginResponseDto> call, Response<VaccineLoginResponseDto> response) {
                progressLog.dismiss();

                VaccineLoginResponseDto profileDTO = response.body();

                if (response.code() == 200) {

                    txn_id = profileDTO.getTxnId();

                    get_vaccine_OTP_Button.setVisibility(View.GONE);
                    otp_Linear_layout.setVisibility(View.VISIBLE);
                    verify_vaccine_OTP_Button.setVisibility(View.VISIBLE);


                }else if (response.code() == 401){

                    Toast.makeText(VaccineDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    otp_Linear_layout.setVisibility(View.GONE);
                    verify_vaccine_OTP_Button.setVisibility(View.GONE);
                    get_vaccine_OTP_Button.setVisibility(View.VISIBLE);
                } else if (response.code() == 500) {
                    otp_Linear_layout.setVisibility(View.GONE);
                    verify_vaccine_OTP_Button.setVisibility(View.GONE);
                    get_vaccine_OTP_Button.setVisibility(View.VISIBLE);
                    Toast.makeText(VaccineDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {
                    otp_Linear_layout.setVisibility(View.GONE);
                    verify_vaccine_OTP_Button.setVisibility(View.GONE);
                    get_vaccine_OTP_Button.setVisibility(View.VISIBLE);
                    Toast.makeText(VaccineDashboardActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<VaccineLoginResponseDto> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());
                otp_Linear_layout.setVisibility(View.GONE);
                Toast.makeText(VaccineDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyVaccineOTP(VaccineOTPRequestDto otpRequestDto) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(VaccineDashboardActivity.this);
        progressLog.setTitle(getString(R.string.verify));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL_NICT).create(RetrofitInterface.class);

        Call<ResponseVerifyOTP> call = retroFitInterface.verifyVaccineOTP(credential,otpRequestDto);

        call.enqueue(new Callback<ResponseVerifyOTP>() {
            @Override
            public void onResponse(Call<ResponseVerifyOTP> call, Response<ResponseVerifyOTP> response) {
                progressLog.dismiss();

                ResponseVerifyOTP body = response.body();

                if (response.code() == 200) {

                    if (body.getIsNewAccount().equalsIgnoreCase("Y")){


                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("cred_vaccine", body.getToken());
                        editor.putString("is_new", body.getIsNewAccount());
                        editor.putBoolean("covid_status", true);
                        editor.apply();
                        editor.commit();
                        Intent intent = new Intent(VaccineDashboardActivity.this,AddNewCovidBeneficiaryActivity.class);
                        startActivity(intent);
                        finish();

                    } else if (body.getIsNewAccount().equalsIgnoreCase("N")){

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("cred_vaccine", body.getToken());
                        editor.putString("is_new", body.getIsNewAccount());
                        editor.putBoolean("covid_status", true);
                        editor.apply();
                        editor.commit();
                        Intent intent = new Intent(VaccineDashboardActivity.this,BeneficiaryListActivity.class);
                        startActivity(intent);
                        finish();


                    } else {

                        Toast.makeText(VaccineDashboardActivity.this, getString(R.string.someting_went_wrong), Toast.LENGTH_SHORT).show();

                    }

                } else if (response.code() == 500) {

                    Toast.makeText(VaccineDashboardActivity.this, getString(R.string.invalid_OTP), Toast.LENGTH_SHORT).show();
                }
                else  {

                    Toast.makeText(VaccineDashboardActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseVerifyOTP> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());
                Toast.makeText(VaccineDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

}