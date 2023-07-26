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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.adapter.paniwala.PaniwalaSubWaterCategoryAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.sub_service_category.GetSubCategoryResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.sub_service_category.Result;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectWaterTypeCategoryActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private ImageView backArrow_IMG;
    private RecyclerView water_category_RV;
    private RetrofitInterface retroFitInterface;
    private String mainServiceID,subServiceID,pincode;
    private List<Result> waterSubCategoryResults;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout capacity_data_LL,no_capacity_available_LL;
   // private BottomSheetDialog bottomSheetWaterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_water_type_category);

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        water_category_RV = findViewById(R.id.water_category_RV);
        capacity_data_LL = findViewById(R.id.capacity_data_LL);
        no_capacity_available_LL = findViewById(R.id.no_capacity_available_LL);
        //water_category_RV = findViewById(R.id.water_category_RV);

        Intent intent = getIntent();
        mainServiceID = intent.getStringExtra("MAIN_SERVICE_ID");
        subServiceID = intent.getStringExtra("SUB_SERVICE_ID");
        pincode = intent.getStringExtra("PINCODE");



        getSubCategoryList();

    //    bottomSheetWaterCategory = new BottomSheetDialog(this);
    //    bottomSheetWaterCategory.setContentView(R.layout.paniwala_water_category_bottom_layout);

    //    water_category_recycler = bottomSheetWaterCategory.findViewById(R.id.water_category_recycler);
       // title_bottomSheet = bottomSheetWaterCategory.findViewById(R.id.title_bottomSheet);

        layoutManager = new LinearLayoutManager(SelectWaterTypeCategoryActivity.this);
        water_category_RV.setLayoutManager(layoutManager);
        water_category_RV.setItemAnimator(new DefaultItemAnimator());


     //   bottomSheetWaterCategory.show();

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectWaterTypeCategoryActivity.this, PaniwalaDashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    private void setAdapter() {
        PaniwalaSubWaterCategoryAdapter adapter = new PaniwalaSubWaterCategoryAdapter(SelectWaterTypeCategoryActivity.this, waterSubCategoryResults, this);
        water_category_RV.setAdapter(adapter);
    }

    private void getSubCategoryList() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.PANIWALA_URL).create(RetrofitInterface.class);

        Call<GetSubCategoryResponse> call = retroFitInterface.getSubWaterCategoryPaniwala(mainServiceID,subServiceID);

        call.enqueue(new Callback<GetSubCategoryResponse>() {
            @Override
            public void onResponse(Call<GetSubCategoryResponse> call, Response<GetSubCategoryResponse> response) {
                progressDialogDismiss();

                GetSubCategoryResponse body = response.body();

                if (response.code() == 200) {

                     if (body.getResult()==null){

                         no_capacity_available_LL.setVisibility(View.VISIBLE);
                         capacity_data_LL.setVisibility(View.GONE);

                    }else {

                         no_capacity_available_LL.setVisibility(View.GONE);
                         capacity_data_LL.setVisibility(View.VISIBLE);

                        waterSubCategoryResults = body.getResult();
                        setAdapter();

                    }

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SelectWaterTypeCategoryActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(SelectWaterTypeCategoryActivity.this, LoginActivity.class);
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
                        Toast.makeText(SelectWaterTypeCategoryActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(SelectWaterTypeCategoryActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(SelectWaterTypeCategoryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetSubCategoryResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(SelectWaterTypeCategoryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SelectWaterTypeCategoryActivity.this, PaniwalaDashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onListItem(View view, int adapterPosition) {

        switch (view.getId()) {

            case R.id.select_sub_category_water_LL:


               Intent intent = new Intent(SelectWaterTypeCategoryActivity.this,VendorProfileListActivity.class);
               intent.putExtra("MAIN_SERVICE_ID",mainServiceID);
               intent.putExtra("SUB_SERVICE_ID",subServiceID);
               intent.putExtra("PINCODE",pincode);
               intent.putExtra("CAPACITY",waterSubCategoryResults.get(adapterPosition).getCapacity());
               startActivity(intent);


                //Toast.makeText(this, waterSubCategoryResults.get(adapterPosition).getSubServ(), Toast.LENGTH_SHORT).show();

                break;

        }

    }
}