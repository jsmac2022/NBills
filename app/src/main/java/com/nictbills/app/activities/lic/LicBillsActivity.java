package com.nictbills.app.activities.lic;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.lic.FetchLicBillRequest;
import com.nictbills.app.activities.health_id_abdm.dto.lic.bill_fetch_response.LicBillsFetchResponse;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LicBillsActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private ImageView backArrow_IMG;
    private Button search_Button,pay_Button;
    private EditText policy_number_EditText,lic_email_EditText;
    private RetrofitInterface retroFitInterface;
    private TextView user_name_TV,lic_amount_TV,due_date_TV;
    private LinearLayout user_details_LL;
    private String pay_amount="",credential;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lic_bills);

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        search_Button = findViewById(R.id.search_Button);
        policy_number_EditText = findViewById(R.id.policy_number_EditText);
        lic_email_EditText = findViewById(R.id.lic_email_EditText);
        user_name_TV = findViewById(R.id.user_name_TV);
        lic_amount_TV = findViewById(R.id.lic_amount_TV);
        user_details_LL = findViewById(R.id.user_details_LL);
        due_date_TV = findViewById(R.id.due_date_TV);
        pay_Button = findViewById(R.id.pay_Button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LicBillsActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        search_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(policy_number_EditText)){

                    Snackbar.make(view, getString(R.string.enter_policy_number), Snackbar.LENGTH_SHORT).show();

                } else if (isEmpty(lic_email_EditText)){

                    Snackbar.make(view, getString(R.string.enter_email_id), Snackbar.LENGTH_SHORT).show();

                } else if (!isEmail(lic_email_EditText)){

                    Snackbar.make(view, getString(R.string.enter_valid_email), Snackbar.LENGTH_SHORT).show();

                }else {

                    FetchLicBillRequest licBillsFetchResponse = new FetchLicBillRequest();
                    licBillsFetchResponse.setCanumber(policy_number_EditText.getText().toString());
                    licBillsFetchResponse.setAd1(lic_email_EditText.getText().toString());
                    licBillsFetchResponse.setMode("online");

                    getLicUserDetails(licBillsFetchResponse);
                }
            }
        });


        pay_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(policy_number_EditText)){

                    Snackbar.make(view, getString(R.string.enter_policy_number), Snackbar.LENGTH_SHORT).show();

                } else if (isEmpty(lic_email_EditText)){

                    Snackbar.make(view, getString(R.string.enter_email_id), Snackbar.LENGTH_SHORT).show();

                } else if (!isEmail(lic_email_EditText)){

                    Snackbar.make(view, getString(R.string.enter_valid_email), Snackbar.LENGTH_SHORT).show();

                } else if (pay_amount.trim().equalsIgnoreCase("")){

                    Snackbar.make(view, getString(R.string.invalid_amount), Snackbar.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(LicBillsActivity.this,LICPaymentActivity.class);

                    intent.putExtra("PAY_FOR","LIC");
                    intent.putExtra("POLICY_NUMBER",policy_number_EditText.getText().toString());
                    intent.putExtra("REWARD_STATUS","No");
                    intent.putExtra("EMAIL",lic_email_EditText.getText().toString());
                    intent.putExtra("AMOUNT",pay_amount);

                    startActivity(intent);

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LicBillsActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }


    private void getLicUserDetails(FetchLicBillRequest fetchLicBillRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<LicBillsFetchResponse> call = retroFitInterface.getLicDetails(credential,fetchLicBillRequest);

        call.enqueue(new Callback<LicBillsFetchResponse>() {
            @Override
            public void onResponse(Call<LicBillsFetchResponse> call, Response<LicBillsFetchResponse> response) {
                progressDialogDismiss();

                LicBillsFetchResponse body = response.body();

                if (response.code() == 200) {

                    if (body.getStatus()){

                        user_details_LL.setVisibility(View.VISIBLE);
                        user_name_TV.setText(body.getName());
                        lic_amount_TV.setText("₹ "+body.getAmount());
                        pay_amount= body.getAmount();

                      // String userDate =  dateFormatChanger(body.getDuedate())

                        due_date_TV.setText(body.getDuedate());

                    }else {
                        user_details_LL.setVisibility(View.GONE);

                        Toast.makeText(LicBillsActivity.this, getString(R.string.LIC_premium_bill_not_generated), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(LicBillsActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(LicBillsActivity.this, LoginActivity.class);
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
                        Toast.makeText(LicBillsActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(LicBillsActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(LicBillsActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LicBillsFetchResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(LicBillsActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onListItem(View view, int adapterPosition) {

    }

   /* public String dateFormatChanger(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }*/

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}