package com.nictbills.app.activities.health_id_abdm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init.HealthIdInitRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init.HealthIdInitResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init.ResendHealthIdInitRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verify_abha_aadhaar.ConfirmWithAadhaarRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verify_abha_aadhaar.ConfirmWithAadhaarResponse;
import com.nictbills.app.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyABHAUsingMobileOTPActivity extends BaseActivity {

    private EditText aadhar_otp_EditText;
    private Button verify_aadhar_OTP_Button;
    private TextView counttime_TV,resent_OTP_TV;
    private RetrofitInterface retroFitInterface;
    private String accessToken,credential,mobileNumber,healthId,txnId;
    private SharedPreferences sharedPreferences,sharedHI;
    private SharedPreferences.Editor editor;
    private int counter;
    private ImageView backArrow_IMG;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_abhausing_mobile_otpactivity);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");

        aadhar_otp_EditText = findViewById(R.id.aadhar_otp_EditText);
        verify_aadhar_OTP_Button = findViewById(R.id.verify_aadhar_OTP_Button);
        counttime_TV = findViewById(R.id.counttime_TV);
        resent_OTP_TV = findViewById(R.id.resent_OTP_TV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);

        Intent intent = getIntent();
        healthId = intent.getStringExtra("HEALTH_ID");

        try {
            sharedHI = Util.getSharedPreferenceInstance(this,"HI",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

       // sharedHI = getSharedPreferences("HI", Context.MODE_PRIVATE);
        accessToken = sharedHI.getString("accessToken", "");


        HealthIdInitRequest idInitRequest = new HealthIdInitRequest();
        idInitRequest.setHealthid(healthId);
        idInitRequest.setAuthMethod(Constant.AUTH_METHOD);
        healthIdInit(idInitRequest);

        verify_aadhar_OTP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                if (isEmpty(aadhar_otp_EditText)){
                    Snackbar.make(view, getString(R.string.enter_otp), Snackbar.LENGTH_SHORT).show();
                } else {
                    ConfirmWithAadhaarRequest confirmWithAadhaarRequest = new ConfirmWithAadhaarRequest();
                    confirmWithAadhaarRequest.setOtp(aadhar_otp_EditText.getText().toString());
                    confirmWithAadhaarRequest.setTxnId(txnId);
                    verifyAadhaarOTP(confirmWithAadhaarRequest);
                }

            }
        });

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countDownTimer!=null){
                    countDownTimer.cancel();
                }
                onBackPressed();

            }
        });

        resent_OTP_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ResendHealthIdInitRequest resendHealthIdInitRequest = new ResendHealthIdInitRequest();
                resendHealthIdInitRequest.setTxnId(txnId);
                resendHealthIdInitOtp(resendHealthIdInitRequest);

            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void noCount(){

        countDownTimer = new CountDownTimer(60000,1000) {
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

    @Override
    public void onBackPressed() {

        if (countDownTimer!=null){
            countDownTimer.cancel();
        }

        super.onBackPressed();
    }

    private void healthIdInit(HealthIdInitRequest idInitRequest) {
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<HealthIdInitResponse> call = retroFitInterface.healthIdInit("Bearer "+accessToken,Constant.HIP_ID,idInitRequest);

        call.enqueue(new Callback<HealthIdInitResponse>() {
            @Override
            public void onResponse(Call<HealthIdInitResponse> call, Response<HealthIdInitResponse> response) {
                progressDialogDismiss();

                HealthIdInitResponse body = response.body();

                if (response.code() == 200) {

                    txnId= body.getTxnId();
                    noCount();

                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.otp_send_successfully), Toast.LENGTH_SHORT).show();



                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(VerifyABHAUsingMobileOTPActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(VerifyABHAUsingMobileOTPActivity.this, ABHALandingPageActivity.class);
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
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.invalid_aadhaar_number_entered), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422) {

                    progressDialogDismiss();

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        JSONArray jarray= jsonObject.getJSONArray("details");
                        JSONObject deatilsJsonOb = jarray.getJSONObject(0);
                        deatilsJsonOb.getString("message");
                        Log.e("error",deatilsJsonOb.getString("message"));
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, deatilsJsonOb.getString("message"), Toast.LENGTH_LONG).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                   // Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.you_have_requested_multiple_in_this_transaction), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HealthIdInitResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void resendHealthIdInitOtp(ResendHealthIdInitRequest resendHealthIdInitRequest) {
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.healthIdInitResendOTP("Bearer "+accessToken,Constant.HIP_ID,resendHealthIdInitRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialogDismiss();

                ResponseBody body = response.body();

                if (response.code() == 200) {


                    Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.otp_resend_successfully), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(VerifyABHAUsingMobileOTPActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(VerifyABHAUsingMobileOTPActivity.this, ABHALandingPageActivity.class);
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
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();

                    Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422) {

                    progressDialogDismiss();

                    Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.you_have_requested_multiple_in_this_transaction), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyAadhaarOTP(ConfirmWithAadhaarRequest confirmWithAadhaarRequest) {
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ConfirmWithAadhaarResponse> call = retroFitInterface.confirmWithAadhaarOTP("Bearer "+accessToken,Constant.HIP_ID,confirmWithAadhaarRequest);

        call.enqueue(new Callback<ConfirmWithAadhaarResponse>() {
            @Override
            public void onResponse(Call<ConfirmWithAadhaarResponse> call, Response<ConfirmWithAadhaarResponse> response) {
                progressDialogDismiss();

                ConfirmWithAadhaarResponse body = response.body();

                if (response.code() == 200) {

                    SharedPreferences.Editor editor = sharedHI.edit();
                    editor.putString("x_token", body.getToken());
                    editor.putString("x_refresh_token",body.getRefreshToken());
                    editor.apply();

                    Intent intent = new Intent(VerifyABHAUsingMobileOTPActivity.this,ABHAUserProfileActivity.class);
                    startActivity(intent);

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(VerifyABHAUsingMobileOTPActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(VerifyABHAUsingMobileOTPActivity.this, ABHALandingPageActivity.class);
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
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(VerifyABHAUsingMobileOTPActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();

                    Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.otp_verification_failed), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422) {

                    progressDialogDismiss();
                    Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.otp_verification_failed), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ConfirmWithAadhaarResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(VerifyABHAUsingMobileOTPActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

}