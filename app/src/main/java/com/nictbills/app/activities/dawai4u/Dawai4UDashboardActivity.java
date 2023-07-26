package com.nictbills.app.activities.dawai4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;

public class Dawai4UDashboardActivity extends BaseActivity {

    private LinearLayout book_medicine_LinearLayout,order_history_LinearLayout,contact_us_LinearLayout;
    private ImageView backArrow_IMG;
   /* private RetrofitInterface retroFitInterface;
    private String credential,full_name,gender,email,pincode,state,address,city,alt_mobile,reg_mobile,accessToken;
    private SharedPreferences sharedPreferences,dawai4uShared;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView dawai_4_u_shops_RV;
    private List<Datum> data;
    private ImageView backArrow_IMG;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dawai4_udashboard);

        book_medicine_LinearLayout = findViewById(R.id.book_medicine_LinearLayout);
        order_history_LinearLayout = findViewById(R.id.order_history_LinearLayout);
        contact_us_LinearLayout = findViewById(R.id.contact_us_LinearLayout);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dawai4UDashboardActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });


        book_medicine_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dawai4UDashboardActivity.this,BookMedicineDawai4UActivity.class);
                startActivity(intent);
                finish();
            }
        });

        order_history_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dawai4UDashboardActivity.this,OrderHistoryDawai4UActivity.class);
                startActivity(intent);
                finish();
            }
        });


        contact_us_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dawai4UDashboardActivity.this,Dawai4UContactUsActivity.class);
                startActivity(intent);
                finish();
            }
        });

/*
        dawai_4_u_shops_RV= findViewById(R.id.dawai_4_u_shops_RV);
        backArrow_IMG= findViewById(R.id.backArrow_IMG);

        sharedPreferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
        credential = sharedPreferences.getString("cred_1", "");
        reg_mobile = sharedPreferences.getString("cred_2", "");
        dawai4uShared = this.getSharedPreferences("Dawai4U", Context.MODE_PRIVATE);
        accessToken= dawai4uShared.getString("token_dawai4u", "");

        getUserProfile();

        layoutManager = new LinearLayoutManager(Dawai4UDashboardActivity.this);
        dawai_4_u_shops_RV.setLayoutManager(layoutManager);
        dawai_4_u_shops_RV.setItemAnimator(new DefaultItemAnimator());


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Dawai4UDashboardActivity.this, NICTArogActivity.class);
                startActivity(intent);
                finish();

            }
        });*/

    }

   /* private void setAdapter() {
        Dawai4UShopsAdapter adapter = new Dawai4UShopsAdapter(Dawai4UDashboardActivity.this, data, this);
        dawai_4_u_shops_RV.setAdapter(adapter);
    }


    private void getUserProfile() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_LIC).create(RetrofitInterface.class);

        Call<GetUserProfile> call = retroFitInterface.getUserProfile(credential);

        call.enqueue(new Callback<GetUserProfile>() {
            @Override
            public void onResponse(Call<GetUserProfile> call, Response<GetUserProfile> response) {
                progressDialogDismiss();

                GetUserProfile body = response.body();

                if (response.code() == 200) {


                    if (body.getFound().equalsIgnoreCase("yes")){

                        full_name = body.getUserProfile().getFullName();
                        gender = body.getUserProfile().getGender();
                        email = body.getUserProfile().getEmail();
                        pincode = body.getUserProfile().getPincode();
                        state = body.getUserProfile().getState();
                        address = body.getUserProfile().getAddress();
                        city = body.getUserProfile().getCity();
                        alt_mobile = body.getUserProfile().getAltMobile();
                        reg_mobile = body.getUserProfile().getRegMobile();

                        GetAccessTokenDawai4URequest accessTokenDawai4URequest = new GetAccessTokenDawai4URequest();
                        accessTokenDawai4URequest.setMobile(reg_mobile);
                        getAccessTokenDawai4U(accessTokenDawai4URequest);

                    } else {

                       Intent intent = new Intent(Dawai4UDashboardActivity.this,UserProfileActivity.class);
                       intent.putExtra("FLAG","DAWAI");
                       startActivity(intent);

                    }



                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Dawai4UDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(Dawai4UDashboardActivity.this, LoginActivity.class);
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
                        Toast.makeText(Dawai4UDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Dawai4UDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(Dawai4UDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetUserProfile> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(Dawai4UDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
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
                progressDialogDismiss();

                GetAccessTokenDawai4U body = response.body();

                if (response.code() == 200) {

                    SharedPreferences.Editor editor = dawai4uShared.edit();
                    editor.putString("token_dawai4u", body.getData().getAuthToken());
                    editor.putBoolean("isLogin", true);
                    editor.apply();
                    editor.commit();

                    getDawai4UShops(body.getData().getAuthToken(),pincode);



                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Dawai4UDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                        Toast.makeText(Dawai4UDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Dawai4UDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(Dawai4UDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetAccessTokenDawai4U> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(Dawai4UDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getDawai4UShops(String accessToken1, String pincode) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.TEST_VINOD).create(RetrofitInterface.class);

        Call<GetDawai4UShop> call = retroFitInterface.getDawai4UShops("Bearer "+accessToken1,pincode);

        call.enqueue(new Callback<GetDawai4UShop>() {
            @Override
            public void onResponse(Call<GetDawai4UShop> call, Response<GetDawai4UShop> response) {
                progressDialogDismiss();

                GetDawai4UShop body = response.body();

                if (response.code() == 200) {


                    data = body.getData();
                    setAdapter();


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Dawai4UDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                        Toast.makeText(Dawai4UDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Dawai4UDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(Dawai4UDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetDawai4UShop> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(Dawai4UDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onListItem(View view, int adapterPosition) {
        switch (view.getId()) {

            case R.id.continue_BTN:

                Intent intent = new Intent(Dawai4UDashboardActivity.this,UploadPrescriptionActivity.class);
                intent.putExtra("STORE_ID",data.get(adapterPosition).getStoreId());
                intent.putExtra("STORE_NAME",data.get(adapterPosition).getStoreName());
                intent.putExtra("NAME",full_name);
                intent.putExtra("GENDER",gender);
                intent.putExtra("EMAIL",email);
                intent.putExtra("PINCODE",pincode);
                intent.putExtra("ADDRESS",address);
                intent.putExtra("STATE",state);
                intent.putExtra("CITY",city);
                intent.putExtra("ALTERNATE_MOBILE",alt_mobile);
                intent.putExtra("MOBILE",reg_mobile);

                startActivity(intent);



                break;


        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Dawai4UDashboardActivity.this, NICTArogActivity.class);
        startActivity(intent);
        finish();
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Dawai4UDashboardActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}