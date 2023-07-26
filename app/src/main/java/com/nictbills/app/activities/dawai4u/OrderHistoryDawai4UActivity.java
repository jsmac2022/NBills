package com.nictbills.app.activities.dawai4u;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.adapter.dawai_4_u.UserMedicineOrderHistoryAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.access_token.GetAccessTokenDawai4U;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.access_token.GetAccessTokenDawai4URequest;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.medicine_order_history.Datum;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.medicine_order_history.GetUserMedicineOrderHistory;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryDawai4UActivity extends BaseActivity  {

    private RecyclerView medicine_order_history_RV;
    private List<Datum> data;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView backArrow_IMG;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private String credential,reg_mobile;
    private LinearLayout show_transaction_history_LL,no_transaction_history_LL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_dawai4_uactivity);

        medicine_order_history_RV = findViewById(R.id.medicine_order_history_RV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        show_transaction_history_LL = findViewById(R.id.show_transaction_history_LL);
        no_transaction_history_LL = findViewById(R.id.no_transaction_history_LL);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        credential = sharedPreferences.getString("cred_1", "");
        reg_mobile = sharedPreferences.getString("cred_2", "");

        layoutManager = new LinearLayoutManager(OrderHistoryDawai4UActivity.this);
        medicine_order_history_RV.setLayoutManager(layoutManager);
        medicine_order_history_RV.setItemAnimator(new DefaultItemAnimator());

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderHistoryDawai4UActivity.this,Dawai4UDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        GetAccessTokenDawai4URequest accessTokenDawai4URequest = new GetAccessTokenDawai4URequest();
        accessTokenDawai4URequest.setMobile(reg_mobile);
        getAccessTokenDawai4U(accessTokenDawai4URequest);

    }

    
    private void setAdapter() {
        UserMedicineOrderHistoryAdapter adapter = new UserMedicineOrderHistoryAdapter(OrderHistoryDawai4UActivity.this, data);
        medicine_order_history_RV.setAdapter(adapter);
    }

    private void getOrderHistory(String token) {

        retroFitInterface = RetrofitClient.getClient(Constant.TEST_VINOD).create(RetrofitInterface.class);

        Call<GetUserMedicineOrderHistory> call = retroFitInterface.getOrderHistoryDawai4U("Bearer "+token, reg_mobile);

        call.enqueue(new Callback<GetUserMedicineOrderHistory>() {
            @Override
            public void onResponse(Call<GetUserMedicineOrderHistory> call, Response<GetUserMedicineOrderHistory> response) {
                progressDialogDismiss();

                GetUserMedicineOrderHistory body = response.body();

                if (response.code() == 200) {

               /*     data = body.getData();
                    setAdapter();*/

                 //   data = body.getData();
                 //   setAdapter();

                   /*if ((body.getData().size()==0)){

                        show_transaction_history_LL.setVisibility(View.GONE);
                        no_transaction_history_LL.setVisibility(View.VISIBLE);

                    } else */
                    if (body.getData()==null){

                        show_transaction_history_LL.setVisibility(View.GONE);
                        no_transaction_history_LL.setVisibility(View.VISIBLE);

                    }else {
                        show_transaction_history_LL.setVisibility(View.VISIBLE);
                        no_transaction_history_LL.setVisibility(View.GONE);
                        data = body.getData();
                        setAdapter();
                    }

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(OrderHistoryDawai4UActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(OrderHistoryDawai4UActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                  //  Log.e("50000","500000");
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(OrderHistoryDawai4UActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(OrderHistoryDawai4UActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                  //  Log.e("50000","600000");
                    Toast.makeText(OrderHistoryDawai4UActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetUserMedicineOrderHistory> call, Throwable t) {
                progressDialogDismiss();
               // t.printStackTrace();
                Toast.makeText(OrderHistoryDawai4UActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void getAccessTokenDawai4U(GetAccessTokenDawai4URequest accessTokenDawai4URequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.TEST_VINOD).create(RetrofitInterface.class);

        Call<GetAccessTokenDawai4U> call = retroFitInterface.getAccessTokenDawai4U(accessTokenDawai4URequest);

        call.enqueue(new Callback<GetAccessTokenDawai4U>() {
            @Override
            public void onResponse(Call<GetAccessTokenDawai4U> call, Response<GetAccessTokenDawai4U> response) {

                GetAccessTokenDawai4U body = response.body();

                if (response.code() == 200) {

                  /*  SharedPreferences.Editor editor = dawai4uShared.edit();
                    editor.putString("token_dawai4u", body.getData().getAuthToken());
                    editor.putBoolean("isLogin", true);
                    editor.apply();
                    editor.commit();*/

                    getOrderHistory(body.getData().getAuthToken());

                    //getDawai4UShops(,pincode);



                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(OrderHistoryDawai4UActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("Dawai4U", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
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
                        Toast.makeText(OrderHistoryDawai4UActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(OrderHistoryDawai4UActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(OrderHistoryDawai4UActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetAccessTokenDawai4U> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(OrderHistoryDawai4UActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderHistoryDawai4UActivity.this,Dawai4UDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}