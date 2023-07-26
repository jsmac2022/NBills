package com.nictbills.app.activities.paniwala;

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
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.activities.UserProfileActivity;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_category.Result;
import com.nictbills.app.adapter.paniwala.PaniwalaWaterCategoryAdapter;
import com.nictbills.app.adapter.paniwala.PaniwalaWaterTypeAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_category.GetWaterCategoryResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_type.GetWaterTypeResponse;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.get_user_profile.GetUserProfile;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
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

public class PaniwalaDashboardActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private RecyclerView water_type_RV,water_category_recycler;
    private List<com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_type.Result> resultList;
    private ImageView backArrow_IMG;
    private RetrofitInterface retroFitInterface;
    private RecyclerView.LayoutManager layoutManager;
    private List<Result> waterCategoryResults;
    private BottomSheetDialog bottomSheetWaterCategory;
    private String mainServiceCode,subServiceCode,credential,reg_mobile,pincode;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paniwala_dashboard);

        water_type_RV = findViewById(R.id.water_type_RV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        reg_mobile = sharedPreferences.getString("cred_2", "");

        getWaterTypePaniwala();

        layoutManager = new LinearLayoutManager(PaniwalaDashboardActivity.this);
        water_type_RV.setLayoutManager(layoutManager);
        water_type_RV.setItemAnimator(new DefaultItemAnimator());


        bottomSheetWaterCategory = new BottomSheetDialog(this);
        bottomSheetWaterCategory.setContentView(R.layout.paniwala_water_category_bottom_layout);

        water_category_recycler = bottomSheetWaterCategory.findViewById(R.id.water_category_recycler);
        // title_bottomSheet = bottomSheetWaterCategory.findViewById(R.id.title_bottomSheet);

        layoutManager = new LinearLayoutManager(PaniwalaDashboardActivity.this);
        water_category_recycler.setLayoutManager(layoutManager);
        water_category_recycler.setItemAnimator(new DefaultItemAnimator());


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PaniwalaDashboardActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setAdapter() {
        PaniwalaWaterTypeAdapter adapter = new PaniwalaWaterTypeAdapter(PaniwalaDashboardActivity.this, resultList, this);
        water_type_RV.setAdapter(adapter);
    }

    private void setAdapterCategory() {
        PaniwalaWaterCategoryAdapter adapter = new PaniwalaWaterCategoryAdapter(PaniwalaDashboardActivity.this, waterCategoryResults, this);
        water_category_recycler.setAdapter(adapter);
    }

    private void getWaterTypePaniwala() {
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.PANIWALA_URL).create(RetrofitInterface.class);
        Call<GetWaterTypeResponse> call = retroFitInterface.getWaterTypePaniwala();
        call.enqueue(new Callback<GetWaterTypeResponse>() {
            @Override
            public void onResponse(Call<GetWaterTypeResponse> call, Response<GetWaterTypeResponse> response) {
                progressDialogDismiss();

                GetWaterTypeResponse body = response.body();

                if (response.code() == 200) {


                    if ((body.getResult().size()==0)){

                      //  no_reward_history_Linear.setVisibility(View.VISIBLE);
                      //  reward_history_Linear.setVisibility(View.GONE);

                    } else if (body.getResult()==null){

                      //  no_reward_history_Linear.setVisibility(View.VISIBLE);
                      //  reward_history_Linear.setVisibility(View.GONE);

                    }else {

                      //  no_reward_history_Linear.setVisibility(View.GONE);
                      //  reward_history_Linear.setVisibility(View.VISIBLE);

                        resultList = body.getResult();
                        setAdapter();

                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PaniwalaDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PaniwalaDashboardActivity.this, LoginActivity.class);
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
                        Toast.makeText(PaniwalaDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(PaniwalaDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(PaniwalaDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetWaterTypeResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(PaniwalaDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PaniwalaDashboardActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



    private void getWaterCategoryPaniwala(String serviceId) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.PANIWALA_URL).create(RetrofitInterface.class);

        Call<GetWaterCategoryResponse> call = retroFitInterface.getWaterCategoryPaniwala(serviceId);

        call.enqueue(new Callback<GetWaterCategoryResponse>() {
            @Override
            public void onResponse(Call<GetWaterCategoryResponse> call, Response<GetWaterCategoryResponse> response) {
                progressDialogDismiss();

                GetWaterCategoryResponse body = response.body();

                if (response.code() == 200) {

                   if (body.getResult()==null){

                        //  no_reward_history_Linear.setVisibility(View.VISIBLE);
                        //  reward_history_Linear.setVisibility(View.GONE);

                    }else {

                        //  no_reward_history_Linear.setVisibility(View.GONE);
                        //  reward_history_Linear.setVisibility(View.VISIBLE);

                        waterCategoryResults = body.getResult();
                        setAdapterCategory();
                        bottomSheetWaterCategory.show();

                    }

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PaniwalaDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PaniwalaDashboardActivity.this, LoginActivity.class);
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
                        Toast.makeText(PaniwalaDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(PaniwalaDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(PaniwalaDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetWaterCategoryResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(PaniwalaDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onListItem(View view, int adapterPosition) {
        switch (view.getId()) {

            case R.id.continue_water_type_BTN:
                mainServiceCode = resultList.get(adapterPosition).getMainServCode();
                getWaterCategoryPaniwala(resultList.get(adapterPosition).getMainServCode());
                break;

            case R.id.water_category_LL:

                bottomSheetWaterCategory.dismiss();
                //Toast.makeText(this, waterCategoryResults.get(adapterPosition).getSubServCode(), Toast.LENGTH_SHORT).show();
                //subServiceCode
                subServiceCode = waterCategoryResults.get(adapterPosition).getSubServCode();

              /*    Intent intent = new Intent(PaniwalaDashboardActivity.this,SelectWaterTypeCategoryActivity.class);
                  intent.putExtra("MAIN_SERVICE_ID",mainServiceCode);
                  intent.putExtra("SUB_SERVICE_ID",waterCategoryResults.get(adapterPosition).getSubServCode());
                  startActivity(intent);
*/
                getUserProfile();
                break;

        }
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

                      /*  full_name = body.getUserProfile().getFullName();
                        gender = body.getUserProfile().getGender();
                        email = body.getUserProfile().getEmail();
                        pincode = body.getUserProfile().getPincode();
                        state = body.getUserProfile().getState();
                        address = body.getUserProfile().getAddress();
                        city = body.getUserProfile().getCity();
                        alt_mobile = body.getUserProfile().getAltMobile();*/

                      //  pincode = body.getUserProfile().getPincode();
                     //   reg_mobile = body.getUserProfile().getRegMobile();

                        Intent intent = new Intent(PaniwalaDashboardActivity.this,SelectWaterTypeCategoryActivity.class);
                        intent.putExtra("MAIN_SERVICE_ID",mainServiceCode);
                        intent.putExtra("SUB_SERVICE_ID",subServiceCode);
                        intent.putExtra("PINCODE",body.getUserProfile().getPincode());
                        startActivity(intent);

                    } else {
                        progressDialogDismiss();

                        Intent intent = new Intent(PaniwalaDashboardActivity.this, UserProfileActivity.class);
                        intent.putExtra("FLAG","PANIWALA");
                        startActivity(intent);

                    }



                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PaniwalaDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PaniwalaDashboardActivity.this, LoginActivity.class);
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
                        Toast.makeText(PaniwalaDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(PaniwalaDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(PaniwalaDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetUserProfile> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(PaniwalaDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

}