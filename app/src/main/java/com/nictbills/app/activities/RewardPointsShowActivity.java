package com.nictbills.app.activities;

import androidx.appcompat.widget.Toolbar;
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
import android.widget.TextView;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.RewardPointsAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.GetRewardPointsResponse;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.reward_ledger.Ledger;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.reward_ledger.RewardPointsLedger;
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

public class RewardPointsShowActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private TextView total_reward_point_balance_TV;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private String credential;
    private List<Ledger> ledgers;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView reward_status_recyclerView;
    private LinearLayout reward_history_Linear,no_reward_history_Linear;
    private ImageView backArrow_IMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_points_show);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        total_reward_point_balance_TV= findViewById(R.id.total_reward_point_balance_TV);
        reward_status_recyclerView= findViewById(R.id.reward_status_recyclerView);
        reward_history_Linear= findViewById(R.id.reward_history_Linear);
        no_reward_history_Linear= findViewById(R.id.no_reward_history_Linear);
        backArrow_IMG= findViewById(R.id.backArrow_IMG);


        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        credential = sharedPreferences.getString("cred_1", "");
        reward_status_recyclerView.setHasFixedSize(true);

        //   layoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);

        layoutManager = new LinearLayoutManager(RewardPointsShowActivity.this);
        reward_status_recyclerView.setLayoutManager(layoutManager);
        reward_status_recyclerView.setItemAnimator(new DefaultItemAnimator());


        getRewardPoints();
        getTransactionList();


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardPointsShowActivity.this,DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


    }


    private void setAdapter() {
        RewardPointsAdapter adapter = new RewardPointsAdapter(RewardPointsShowActivity.this, ledgers, this);
        reward_status_recyclerView.setAdapter(adapter);
    }

    private void getRewardPoints() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetRewardPointsResponse> call = retroFitInterface.getRewardPoints(credential);

        call.enqueue(new Callback<GetRewardPointsResponse>() {
            @Override
            public void onResponse(Call<GetRewardPointsResponse> call, Response<GetRewardPointsResponse> response) {
                progressDialogDismiss();

                GetRewardPointsResponse body = response.body();

                if (response.code() == 200) {

                    total_reward_point_balance_TV.setText(getString(R.string.total_balance)+" : "+body.getBalance());


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(RewardPointsShowActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(RewardPointsShowActivity.this, LoginActivity.class);
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
                        Toast.makeText(RewardPointsShowActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(RewardPointsShowActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(RewardPointsShowActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetRewardPointsResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(RewardPointsShowActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getTransactionList() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<RewardPointsLedger> call = retroFitInterface.getRewardPointsDetails(credential);

        call.enqueue(new Callback<RewardPointsLedger>() {
            @Override
            public void onResponse(Call<RewardPointsLedger> call, Response<RewardPointsLedger> response) {
                progressDialogDismiss();

                RewardPointsLedger statusList = response.body();

                if (response.code() == 200) {

                    if ((statusList.getLedger().size()==0)){

                        no_reward_history_Linear.setVisibility(View.VISIBLE);
                        reward_history_Linear.setVisibility(View.GONE);

                    } else if (statusList.getLedger()==null){

                        no_reward_history_Linear.setVisibility(View.VISIBLE);
                        reward_history_Linear.setVisibility(View.GONE);

                    }else {

                        no_reward_history_Linear.setVisibility(View.GONE);
                        reward_history_Linear.setVisibility(View.VISIBLE);

                        ledgers = statusList.getLedger();
                        setAdapter();

                    }

                } else if (response.code() == 500) {

                    Toast.makeText(RewardPointsShowActivity.this, getResources().getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(RewardPointsShowActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getResources().getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getResources().getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(RewardPointsShowActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else {

                    Toast.makeText(RewardPointsShowActivity.this, getResources().getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RewardPointsLedger> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(RewardPointsShowActivity.this, getResources().getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(RewardPointsShowActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onListItem(View view, int adapterPosition) {

        switch (view.getId()) {

            case R.id.reward_point_LL:

                Intent intent = new Intent(RewardPointsShowActivity.this,RewardPointsDescriptionActivity.class);
                intent.putExtra("refno",ledgers.get(adapterPosition).getRefno());
                intent.putExtra("amount",ledgers.get(adapterPosition).getAmount());
                intent.putExtra("descr",ledgers.get(adapterPosition).getDescr());
                intent.putExtra("service",ledgers.get(adapterPosition).getService());
                intent.putExtra("service_id",ledgers.get(adapterPosition).getServiceId());
                intent.putExtra("ttype",ledgers.get(adapterPosition).getTtype());
                intent.putExtra("edt",ledgers.get(adapterPosition).getEdt());
                startActivity(intent);

                break;


        }

    }
}