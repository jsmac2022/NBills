package com.nictbills.app.activities.health_id_abdm;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.adapter.ABHAUserProfileAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.GenerateHealthIdTokenResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.LoginUsingMobileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.LoginUsingMobileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.ResendMobileLoginOtpRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.VerifyMobileOTPRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.abha_profile_details.GetABHAUserProfileDetails;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.abha_profile_details.MobileLinkedHid;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user.GetABHAUserProfileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user.GetUserIndividualProfileDetails;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.enc_data.EncryptDataRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.enc_data.EncryptDataResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verifyABHAAddress.SearchByHealthIdResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verifyABHAAddress.VerifyABHAAddressRequest;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ABHALoginActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private RetrofitInterface retroFitInterface;
    private Button more_details_BTN,get_login_otp_Button, verify_login_otp_Button,verify_abha_Button;
    private EditText mobile_number_EditText, mobile_otp_EditText,abha_number_EditText;
    private SharedPreferences sharedPreferences, sharedHI;
    private String accessToken, credential, xToken, mobileNumber, getOTPTxn,txnIDProfileGet,healthId;
    private boolean is_loginHI;
    private SharedPreferences.Editor editor;
    private LinearLayout ABHA_Screen_LL,abha_user_name_LL,abha_user_details_LL,enter_abha_LL,otp_arear_LL, mobile_otp_LL,login_in_with_mobile_No_LL,abha_number_abha_address_LL;
    private RecyclerView abha_user_name_RV;
    private RecyclerView.LayoutManager layoutManager;
    private List<MobileLinkedHid> mobileLinkedHids;
    private ImageView backArrow_IMG;
    private TextView user_name_TV,abha_address_TV,abha_number_TV,counttime_TV,resent_OTP_TV;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhalogin);

        get_login_otp_Button = findViewById(R.id.get_login_otp_Button);
        mobile_number_EditText = findViewById(R.id.mobile_number_EditText);
        verify_login_otp_Button = findViewById(R.id.verify_login_otp_Button);
        otp_arear_LL = findViewById(R.id.otp_arear_LL);
        mobile_otp_EditText = findViewById(R.id.mobile_otp_EditText);
        mobile_otp_LL = findViewById(R.id.mobile_otp_LL);
        abha_user_name_RV = findViewById(R.id.abha_user_name_RV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        login_in_with_mobile_No_LL = findViewById(R.id.login_in_with_mobile_No_LL);
        abha_number_abha_address_LL = findViewById(R.id.abha_number_abha_address_LL);
        enter_abha_LL = findViewById(R.id.enter_abha_LL);
        abha_user_details_LL = findViewById(R.id.abha_user_details_LL);
        abha_user_name_LL = findViewById(R.id.abha_user_name_LL);
        verify_abha_Button = findViewById(R.id.verify_abha_Button);
        user_name_TV = findViewById(R.id.user_name_TV);
        abha_address_TV = findViewById(R.id.abha_address_TV);
        abha_number_TV = findViewById(R.id.abha_number_TV);
        abha_number_EditText = findViewById(R.id.abha_number_EditText);
        ABHA_Screen_LL = findViewById(R.id.ABHA_Screen_LL);
        counttime_TV = findViewById(R.id.counttime_TV);
        resent_OTP_TV = findViewById(R.id.resent_OTP_TV);
        more_details_BTN = findViewById(R.id.more_details_BTN);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");


        try {
            sharedHI = Util.getSharedPreferenceInstance(this,"HI",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
       // sharedHI = getSharedPreferences("HI", Context.MODE_PRIVATE);

        accessToken = sharedHI.getString("accessToken", "");
        xToken = sharedHI.getString("x_token", "");
        is_loginHI = sharedHI.getBoolean("is_loginHI", false);


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                Intent intent = new Intent(ABHALoginActivity.this,ABHALandingPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });



        login_in_with_mobile_No_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                mobile_otp_LL.setVisibility(View.VISIBLE);
                enter_abha_LL.setVisibility(View.GONE);
                abha_number_abha_address_LL.setVisibility(View.VISIBLE);
                login_in_with_mobile_No_LL.setVisibility(View.GONE);

            }
        });


        more_details_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                Intent intent = new Intent(ABHALoginActivity.this,VerifyABHAUsingMobileOTPActivity.class);
                intent.putExtra("HEALTH_ID",healthId);
                startActivity(intent);

            }
        });

        abha_number_abha_address_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                mobile_otp_LL.setVisibility(View.GONE);
                enter_abha_LL.setVisibility(View.VISIBLE);
                abha_number_abha_address_LL.setVisibility(View.GONE);
                login_in_with_mobile_No_LL.setVisibility(View.VISIBLE);

            }
        });


        verify_abha_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (isEmpty(abha_number_EditText)){

                //    Toast.makeText(ABHALoginActivity.this, getString(R.string.), Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, getString(R.string.enter_ABHA_Address_ABHA_Number), Snackbar.LENGTH_SHORT).show();

                } else {

                    getAccessTokenHI("VERIFY");
                }

            }
        });

        if (is_loginHI) {

            Intent intent = new Intent(ABHALoginActivity.this, ABHAUserProfileActivity.class);
            startActivity(intent);
            finish();

        }


        abha_user_name_RV.setHasFixedSize(true);

        //   layoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);

        layoutManager = new LinearLayoutManager(ABHALoginActivity.this);
        abha_user_name_RV.setLayoutManager(layoutManager);
        abha_user_name_RV.setItemAnimator(new DefaultItemAnimator());

        get_login_otp_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(mobile_number_EditText)) {

                    Snackbar.make(view, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                } else if (!isValidMobileNumber(mobile_number_EditText.getText().toString())){

                    Snackbar.make(view, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                }else {

                    getAccessTokenHI("MOBILE");

                }

            }
        });


        verify_login_otp_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(mobile_number_EditText)) {

                   // Toast.makeText(ABHALoginActivity.this, getString(R.string.), Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                } else if (!isValidMobileNumber(mobile_number_EditText.getText().toString())){

                    Snackbar.make(view, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                }else if (isEmpty(mobile_otp_EditText)){

                   // Toast.makeText(ABHALoginActivity.this, getString(R.string.), Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, getString(R.string.enter_otp), Snackbar.LENGTH_SHORT).show();

                } else{

                    EncryptDataRequest encryptDataRequest = new EncryptDataRequest();
                    encryptDataRequest.setData(mobile_otp_EditText.getText().toString());
                    encData(encryptDataRequest, "MOBILE_OTP");
                }



            }
        });

        resent_OTP_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                ResendMobileLoginOtpRequest resendMobileLoginOtpRequest = new ResendMobileLoginOtpRequest();
                resendMobileLoginOtpRequest.setAuthMethod(Constant.AUTH_METHOD);
                resendMobileLoginOtpRequest.setTxnId(getOTPTxn);
                resendOtp(resendMobileLoginOtpRequest);

            }
        });


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

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void setAdapter() {
        ABHAUserProfileAdapter adapter = new ABHAUserProfileAdapter(ABHALoginActivity.this, mobileLinkedHids, this);
        abha_user_name_RV.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ABHALoginActivity.this,ABHALandingPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void getAccessTokenHI(String tag) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_LIC).create(RetrofitInterface.class);

        Call<GenerateHealthIdTokenResponse> call = retroFitInterface.getAccessToken(credential);

        call.enqueue(new Callback<GenerateHealthIdTokenResponse>() {
            @Override
            public void onResponse(Call<GenerateHealthIdTokenResponse> call, Response<GenerateHealthIdTokenResponse> response) {
                // progressDialogDismiss();

                GenerateHealthIdTokenResponse body = response.body();

                if (response.code() == 200) {

                    accessToken = body.getAccessToken();

                  //  editor = getSharedPreferences("HI", MODE_PRIVATE).edit();

                    SharedPreferences.Editor editor = sharedHI.edit();
                    editor.putString("accessToken", body.getAccessToken());
                    editor.apply();

                    //  verify_aadhar_OTP_Button.setVisibility(View.GONE);

                    if (tag.equalsIgnoreCase("MOBILE")){
                        LoginUsingMobileRequest loginUsingMobileRequest = new LoginUsingMobileRequest();
                        loginUsingMobileRequest.setMobile(mobile_number_EditText.getText().toString());
                        doLoginMobileNumber(loginUsingMobileRequest);
                    } else {
                        VerifyABHAAddressRequest verifyABHAAddressRequest = new VerifyABHAAddressRequest();
                        verifyABHAAddressRequest.setHealthId(abha_number_EditText.getText().toString());
                        verifyABHANumber_Address(verifyABHAAddressRequest);
                    }


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, LoginActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GenerateHealthIdTokenResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void doLoginMobileNumber(LoginUsingMobileRequest usingMobileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<LoginUsingMobileResponse> call = retroFitInterface.loginUsingMobileNumber("Bearer " + accessToken, Constant.HIP_ID, usingMobileRequest);

        call.enqueue(new Callback<LoginUsingMobileResponse>() {
            @Override
            public void onResponse(Call<LoginUsingMobileResponse> call, Response<LoginUsingMobileResponse> response) {
                progressDialogDismiss();
                LoginUsingMobileResponse body = response.body();

                if (response.code() == 200) {


                    //  Toast.makeText(ABHALoginActivity.this, "send", Toast.LENGTH_SHORT).show();
                    getOTPTxn = body.getTxnId();
                    mobile_otp_EditText.setVisibility(View.VISIBLE);
                    verify_login_otp_Button.setVisibility(View.VISIBLE);
                    get_login_otp_Button.setVisibility(View.GONE);
                    otp_arear_LL.setVisibility(View.VISIBLE);
                    noCount();

                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, HealthIdDashboardActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.invalid_mobile_number), Toast.LENGTH_SHORT).show();
                }  else if (response.code() == 422) {

                    progressDialogDismiss();

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        JSONArray jarray= jsonObject.getJSONArray("details");
                        JSONObject deatilsJsonOb = jarray.getJSONObject(0);
                        deatilsJsonOb.getString("message");
                        Log.e("error",deatilsJsonOb.getString("message"));
                        Toast.makeText(ABHALoginActivity.this, deatilsJsonOb.getString("message"), Toast.LENGTH_LONG).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }                }
            }

            @Override
            public void onFailure(Call<LoginUsingMobileResponse> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resendOtp(ResendMobileLoginOtpRequest otpRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.resendMobileOtp("Bearer " + accessToken, Constant.HIP_ID, otpRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialogDismiss();
                ResponseBody body = response.body();

                if (response.code() == 200) {

                    Toast.makeText(ABHALoginActivity.this, getString(R.string.otp_resend_successfully), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, HealthIdDashboardActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.invalid_mobile_number), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422){
                    progressDialogDismiss();

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        JSONArray jarray= jsonObject.getJSONArray("details");
                        JSONObject deatilsJsonOb = jarray.getJSONObject(0);
                        deatilsJsonOb.getString("message");
                        Log.e("error",deatilsJsonOb.getString("message"));
                        Toast.makeText(ABHALoginActivity.this, deatilsJsonOb.getString("message"), Toast.LENGTH_LONG).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }
                  //  Toast.makeText(ABHALoginActivity.this, getString(R.string.you_have_requested_multiple_in_this_transaction), Toast.LENGTH_SHORT).show();

                }
                else {

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void encData(EncryptDataRequest encryptDataRequest, String encDataFor) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_LIC).create(RetrofitInterface.class);

        Call<EncryptDataResponse> call = retroFitInterface.encData(credential, encryptDataRequest);

        call.enqueue(new Callback<EncryptDataResponse>() {
            @Override
            public void onResponse(Call<EncryptDataResponse> call, Response<EncryptDataResponse> response) {
                //  progressDialogDismiss();

                EncryptDataResponse body = response.body();

                if (response.code() == 200) {

                    if (encDataFor.equalsIgnoreCase("MOBILE_OTP")) {

                        VerifyMobileOTPRequest verifyMobileOTPRequest = new VerifyMobileOTPRequest();
                        verifyMobileOTPRequest.setOtp(body.getEncData());
                        verifyMobileOTPRequest.setTxnId(getOTPTxn);
                        verifyMobileOTP(verifyMobileOTPRequest);

                    }


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, LoginActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EncryptDataResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyMobileOTP(VerifyMobileOTPRequest usingMobileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<GetABHAUserProfileDetails> call = retroFitInterface.otpMobileVerify("Bearer " + accessToken, Constant.HIP_ID, usingMobileRequest);

        call.enqueue(new Callback<GetABHAUserProfileDetails>() {
            @Override
            public void onResponse(Call<GetABHAUserProfileDetails> call, Response<GetABHAUserProfileDetails> response) {
                progressDialogDismiss();
                GetABHAUserProfileDetails body = response.body();

                if (response.code() == 200) {


                    mobile_otp_LL.setVisibility(View.GONE);
                    ABHA_Screen_LL.setVisibility(View.GONE);

                    mobileLinkedHids = body.getMobileLinkedHid();

                    xToken = body.getToken();

                    txnIDProfileGet = body.getTxnId();

                   // editor = getSharedPreferences("HI", MODE_PRIVATE).edit();

                    SharedPreferences.Editor editor = sharedHI.edit();

                    editor.putString("x_token",body.getToken());
                    editor.apply();
                    abha_user_name_LL.setVisibility(View.VISIBLE);
                    setAdapter();


                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, HealthIdDashboardActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.otp_verification_failed), Toast.LENGTH_SHORT).show();

                } else if (response.code()==422){
                    progressDialogDismiss();

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());

                        if (jsonObject.getJSONArray("details")!=null){

                            JSONArray jsonArray = jsonObject.getJSONArray("details");

                            if (jsonArray.getJSONObject(0)!=null){

                                JSONObject jsonObjectMsg = jsonArray.getJSONObject(0);
                                // Log.e("aaabbb",jsonObjectMsg.getString("message"));
                                if (jsonObjectMsg.getString("code").equalsIgnoreCase("HIS-1008")){
                                    Toast.makeText(ABHALoginActivity.this,getString(R.string.no_user_account_can_be_found_with_the_mobile_number_provided), Toast.LENGTH_LONG).show();


                                } else if (jsonObjectMsg.getString("code").equalsIgnoreCase("HIS-1013")){
                                    Toast.makeText(ABHALoginActivity.this,getString(R.string.otp_verification_failed), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ABHALoginActivity.this,getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_LONG).show();

                                }


                            } else {
                                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();

                        }


                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<GetABHAUserProfileDetails> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onListItem(View view, int adapterPosition) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        switch (view.getId()) {

            case R.id.more_details_BTN:

                GetABHAUserProfileRequest getABHAUserProfileRequest = new GetABHAUserProfileRequest();
                getABHAUserProfileRequest.setHealthId(mobileLinkedHids.get(adapterPosition).getHealthIdNumber());
                getABHAUserProfileRequest.setTxnId(txnIDProfileGet);
                getProfileDetails( getABHAUserProfileRequest);


                break;


        }
    }



    private void getProfileDetails(GetABHAUserProfileRequest userProfileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<GetUserIndividualProfileDetails> call = retroFitInterface.getIndividualProfile("Bearer " + accessToken, Constant.HIP_ID, "Bearer "+xToken, userProfileRequest);

        call.enqueue(new Callback<GetUserIndividualProfileDetails>() {
            @Override
            public void onResponse(Call<GetUserIndividualProfileDetails> call, Response<GetUserIndividualProfileDetails> response) {
                progressDialogDismiss();
                GetUserIndividualProfileDetails body = response.body();

                if (response.code() == 200) {


                   // editor = getSharedPreferences("HI", MODE_PRIVATE).edit();

                    SharedPreferences.Editor editor = sharedHI.edit();
                    editor.putString("x_token",body.getToken());
                    editor.putBoolean("is_loginHI",true);
                    editor.apply();

                    Intent intent = new Intent(ABHALoginActivity.this,ABHAUserProfileActivity.class);
                    startActivity(intent);

                    //  Toast.makeText(ABHALoginActivity.this, "send", Toast.LENGTH_SHORT).show();
                   /* getOTPTxn = body.getTxnId();
                    mobile_otp_EditText.setVisibility(View.VISIBLE);
                    verify_login_otp_Button.setVisibility(View.VISIBLE);
                    get_login_otp_Button.setVisibility(View.GONE);
                    otp_arear_LL.setVisibility(View.VISIBLE);*/

                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, HealthIdDashboardActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GetUserIndividualProfileDetails> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyABHANumber_Address(VerifyABHAAddressRequest usingMobileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<SearchByHealthIdResponse> call = retroFitInterface.verifyAbhaAddress("Bearer " + accessToken, Constant.HIP_ID, usingMobileRequest);

        call.enqueue(new Callback<SearchByHealthIdResponse>() {
            @Override
            public void onResponse(Call<SearchByHealthIdResponse> call, Response<SearchByHealthIdResponse> response) {
                progressDialogDismiss();
                SearchByHealthIdResponse body = response.body();

                if (response.code() == 200) {

                    abha_user_details_LL.setVisibility(View.VISIBLE);
                    ABHA_Screen_LL.setVisibility(View.GONE);
                    user_name_TV.setText(body.getName());
                    abha_address_TV.setText(body.getHealthId());
                    healthId=body.getHealthId();
                    abha_number_TV.setText(body.getHealthIdNumber());


                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ABHALoginActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(ABHALoginActivity.this, HealthIdDashboardActivity.class);
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
                        Log.e("error", jsonObject.getString("error"));
                        Toast.makeText(ABHALoginActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this,getString(R.string.no_user_account_can_be_found_with_the_ABHA_Number) , Toast.LENGTH_LONG).show();

               //     Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code()==422){

                    progressDialogDismiss();
                    Toast.makeText(ABHALoginActivity.this,getString(R.string.no_user_account_can_be_found_with_the_ABHA_Number) , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchByHealthIdResponse> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(ABHALoginActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean isValidMobileNumber(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

}