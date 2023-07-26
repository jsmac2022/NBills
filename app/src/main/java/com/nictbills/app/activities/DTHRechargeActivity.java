package com.nictbills.app.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.OperatorCircleAdapter;
import com.nictbills.app.adapter.mobile_recharge_paysprint.OperatorCirclePaySprintAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.getMobileRechargeProvider.Operator;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.Datum;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.GetOperatorPaySprint;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.GetRewardPointsResponse;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DTHRechargeActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private EditText operator_circle_EditText,customer_EditText,plan_amount_EditText;
    private BottomSheetDialog bottomSheetDialogSelectDTHOperator;
    //private List<Operator> operatorsList;
    private List<Datum> operatorsList;
    private RecyclerView operator_circle_recycler;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private String credential,useReward="No";
    private ImageView backArrow_IMG;
    private TextView title_bottomSheet;
    private Button pay_Button;
    private int operatorValue=-1,rewardPoints=0,rewardPointForward=0;
    private CheckBox checkbox_reward_balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_t_h_recharge);

        operator_circle_EditText= findViewById(R.id.operator_circle_EditText);
        customer_EditText= findViewById(R.id.customer_EditText);
        plan_amount_EditText= findViewById(R.id.plan_amount_EditText);
        backArrow_IMG= findViewById(R.id.backArrow_IMG);
        pay_Button= findViewById(R.id.pay_Button);
        checkbox_reward_balance = findViewById(R.id.checkbox_reward_balance);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        bottomSheetDialogSelectDTHOperator = new BottomSheetDialog(this);
        bottomSheetDialogSelectDTHOperator.setContentView(R.layout.select_operator_circle_bottomsheet);

        operator_circle_recycler = bottomSheetDialogSelectDTHOperator.findViewById(R.id.operator_circle_recycler);
        title_bottomSheet = bottomSheetDialogSelectDTHOperator.findViewById(R.id.title_bottomSheet);
        getRewardPoints();
        operator_circle_recycler.setHasFixedSize(true);
        operator_circle_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        operator_circle_recycler.setItemAnimator(new DefaultItemAnimator());

        operator_circle_EditText.setInputType(InputType.TYPE_NULL);
        operator_circle_EditText.setFocusable(false);
        operator_circle_EditText.setClickable(true);

        checkbox_reward_balance.setEnabled(false);

        operator_circle_EditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getProviderOperatorCircle();
                getPaySprintDTHOperator();
            }
        });

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DTHRechargeActivity.this,DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        checkbox_reward_balance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //checked
                    useReward="Yes";
                    rewardPointForward=rewardPoints;
                    //Toast.makeText(PayUsingActivity.this, "check", Toast.LENGTH_SHORT).show();
                } else {
                    useReward="No";
                    rewardPointForward=0;
                    //not checked
                    //Toast.makeText(PayUsingActivity.this, "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pay_Button.setOnClickListener(new View.OnClickListener() {
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


                   if (operatorValue==-1){

                        Snackbar.make(v, getString(R.string.operator), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(customer_EditText)){

                       Snackbar.make(v, getString(R.string.enter_dth_customer_id), Snackbar.LENGTH_SHORT).show();

                   } else if (isEmpty(plan_amount_EditText)){

                        Snackbar.make(v, getString(R.string.enter_recharge_amount), Snackbar.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(DTHRechargeActivity.this,PayUsingUPIRechargeActivity.class);
                        intent.putExtra("PAY_FOR","DTH");
                        intent.putExtra("CUSTOMER_ID",customer_EditText.getText().toString());
                        intent.putExtra("OPERATOR",operatorValue);
                        intent.putExtra("AMOUNT",plan_amount_EditText.getText().toString());
                        intent.putExtra("REWARD_STATUS",useReward);
                        intent.putExtra("REWARD_POINT",rewardPointForward);
                        startActivity(intent);

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

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void setAdapterGetOperators(List<Operator> operatorsList){
        OperatorCircleAdapter adapter = new OperatorCircleAdapter(DTHRechargeActivity.this, operatorsList,this);
        operator_circle_recycler.setAdapter(adapter);
    }


    /*private void getProviderOperatorCircle() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(DTHRechargeActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetProvider> call = retroFitInterface.getMobileOperatorCircle(credential);

        call.enqueue(new Callback<GetProvider>() {
            @Override
            public void onResponse(Call<GetProvider> call, Response<GetProvider> response) {
                progressLog.dismiss();

                GetProvider body = response.body();

                if (response.code() == 200) {
                    operatorsList = new ArrayList<>();

                    for (Operator op : body.getOperators()){

                        if (op.getType().trim().equalsIgnoreCase("dth")){

                            Operator opr = new Operator();
                            opr.setId(op.getId());
                            opr.setName(op.getName());
                            opr.setType(op.getType());
                            operatorsList.add(opr);

                        }

                    }

                    setAdapterGetOperators(operatorsList);
                    title_bottomSheet.setText(getText(R.string.operator));
                    bottomSheetDialogSelectDTHOperator.show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(DTHRechargeActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DTHRechargeActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(DTHRechargeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(DTHRechargeActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetProvider> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(DTHRechargeActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }*/

    private void setAdapterGetOperatorsPaySprint(List<Datum> operatorsList){
        OperatorCirclePaySprintAdapter adapter = new OperatorCirclePaySprintAdapter(DTHRechargeActivity.this, operatorsList,this);
        operator_circle_recycler.setAdapter(adapter);
    }



    private void getPaySprintDTHOperator() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(DTHRechargeActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetOperatorPaySprint> call = retroFitInterface.getMobileOperatorCirclePaySprint(credential);

        call.enqueue(new Callback<GetOperatorPaySprint>() {
            @Override
            public void onResponse(Call<GetOperatorPaySprint> call, Response<GetOperatorPaySprint> response) {
                progressLog.dismiss();

                GetOperatorPaySprint body = response.body();

                if (response.code() == 200) {
                    operatorsList = new ArrayList<>();
                    for (Datum op : body.getData()){

                        if (op.getCategory().trim().equalsIgnoreCase("DTH")){
                            Datum opr = new Datum();
                            opr.setId(op.getId());
                            opr.setName(op.getName());
                            //opr.setName(op.getName());
                            opr.setCategory(op.getCategory());
                            Log.e("mopbilleee++", op.getName());
                            operatorsList.add(opr);
                            // operatorsList.add(operatorList);
                            //operators.set()
                        }

                    }
                    // Log.e("aaaaaa+",operators.toString());
                    // operators = body.getOperators();
                    setAdapterGetOperatorsPaySprint(operatorsList);
                    //title_bottomSheet.setText(getText(R.string.operator_circle));
                    title_bottomSheet.setText(getText(R.string.operator));
                    bottomSheetDialogSelectDTHOperator.show();
                   // bottomSheetDialogSelectOperatorCircle.show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(DTHRechargeActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DTHRechargeActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(DTHRechargeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(DTHRechargeActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetOperatorPaySprint> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(DTHRechargeActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onListItem(View view, int adapterPosition) {

        switch (view.getId()) {

            case R.id.operator_name_TV:

               // operatorValue = operatorsList.get(adapterPosition).getId();

                try {
                    operatorValue = Integer.parseInt(operatorsList.get(adapterPosition).getId());
                }
                catch (NumberFormatException e) {
                    operatorValue = 0;
                }

                //Toast.makeText(this, operatorsList.get(adapterPosition).getId() + "", Toast.LENGTH_SHORT).show();
                operator_circle_EditText.setText(operatorsList.get(adapterPosition).getName());
                bottomSheetDialogSelectDTHOperator.dismiss();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DTHRechargeActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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

                    rewardPoints = body.getBalance();
                    checkbox_reward_balance.setText(getString(R.string.total_balance)+" : "+rewardPoints);

                    if (rewardPoints==0){
                        checkbox_reward_balance.setEnabled(false);
                    }else {
                        checkbox_reward_balance.setEnabled(true);
                    }



                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DTHRechargeActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(DTHRechargeActivity.this, LoginActivity.class);
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
                        Toast.makeText(DTHRechargeActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(DTHRechargeActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(DTHRechargeActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<GetRewardPointsResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(DTHRechargeActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

}