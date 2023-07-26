package com.nictbills.app.activities.paniwala;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.adapter.paniwala.PaniwalaVendorProfileAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaConfirmOrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaCreateOrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaCreateOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.confirm_order.PaniwalaConfirmOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.PaniwalaVendorProfileListResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.PaniwalaVendorProfileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.Result;
import com.nictbills.app.global.GlobalClass;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorProfileListActivity extends BaseActivity implements OnClickRecyclerViewInterface, PaymentResultWithDataListener {

    private RecyclerView paniwala_vendor_RV;
    private RetrofitInterface retroFitInterface;
    private List<Result> vendorProfileResult;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private String orderId,mainServiceID,subServiceID,pincode,capacity,reg_mobile,razorpayPayment_id,razorpayOrder_id,razorpaySignature;
    private Integer amount;
    private ImageView backArrow_IMG;
    private Dialog confirmDialog,paymentStatusDialog;
    private LinearLayout no_vendor_available_LL,vendor_available_LL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_profile_list);


        paniwala_vendor_RV= findViewById(R.id.paniwala_vendor_RV);
        backArrow_IMG= findViewById(R.id.backArrow_IMG);
        vendor_available_LL= findViewById(R.id.vendor_available_LL);
        no_vendor_available_LL= findViewById(R.id.no_vendor_available_LL);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
       /* credential = sharedPreferences.getString("cred_1", "");*/
        reg_mobile = sharedPreferences.getString("cred_2", "");

        Intent intent = getIntent();
        mainServiceID = intent.getStringExtra("MAIN_SERVICE_ID");
        subServiceID = intent.getStringExtra("SUB_SERVICE_ID");
        pincode = intent.getStringExtra("PINCODE");
        capacity = intent.getStringExtra("CAPACITY");

        layoutManager = new LinearLayoutManager(VendorProfileListActivity.this);
        paniwala_vendor_RV.setLayoutManager(layoutManager);
        paniwala_vendor_RV.setItemAnimator(new DefaultItemAnimator());

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });

        PaniwalaVendorProfileRequest paniwalaVendorProfileRequest = new PaniwalaVendorProfileRequest();
        paniwalaVendorProfileRequest.setCap(capacity);
        paniwalaVendorProfileRequest.setContact(reg_mobile);
        paniwalaVendorProfileRequest.setPincode(pincode);
        paniwalaVendorProfileRequest.setSsc(subServiceID);
        paniwalaVendorProfileRequest.setMsc(mainServiceID);
        getPaniwalaVendorProfile(paniwalaVendorProfileRequest);

    }
    private void setAdapter(String payStatus) {
        PaniwalaVendorProfileAdapter adapter = new PaniwalaVendorProfileAdapter(VendorProfileListActivity.this, vendorProfileResult, payStatus, this);
        paniwala_vendor_RV.setAdapter(adapter);
    }
    private void getPaniwalaVendorProfile(PaniwalaVendorProfileRequest paniwalaVendorProfileRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.PANIWALA_URL).create(RetrofitInterface.class);

        Call<PaniwalaVendorProfileListResponse> call = retroFitInterface.getVendorProfileList(paniwalaVendorProfileRequest);

        call.enqueue(new Callback<PaniwalaVendorProfileListResponse>() {
            @Override
            public void onResponse(Call<PaniwalaVendorProfileListResponse> call, Response<PaniwalaVendorProfileListResponse> response) {
                progressDialogDismiss();

                PaniwalaVendorProfileListResponse body = response.body();

                if (response.code() == 200) {


                   if (body.getResult()==null){


                       no_vendor_available_LL.setVisibility(View.VISIBLE);
                       vendor_available_LL.setVisibility(View.GONE);
                        //  no_reward_history_Linear.setVisibility(View.VISIBLE);
                        //  reward_history_Linear.setVisibility(View.GONE);

                    }else {

                        //  no_reward_history_Linear.setVisibility(View.GONE);
                        //  reward_history_Linear.setVisibility(View.VISIBLE);

                       no_vendor_available_LL.setVisibility(View.GONE);
                       vendor_available_LL.setVisibility(View.VISIBLE);
                        vendorProfileResult = body.getResult();
                        setAdapter(body.getPayStatus());

                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(VendorProfileListActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(VendorProfileListActivity.this, LoginActivity.class);
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
                        Toast.makeText(VendorProfileListActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(VendorProfileListActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PaniwalaVendorProfileListResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onListItem(View view, int adapterPosition) {

        switch (view.getId()) {

            case R.id.vendor_more_details_LL:


                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                        boolean connected = false;
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network
                            connected = true;



                            offerDialog();

/*
                            PaniwalaCreateOrderRequest paniwalaCreateOrderRequest  = new PaniwalaCreateOrderRequest();
                            paniwalaCreateOrderRequest.setAmount("30");
                            paniwalaCreateOrderRequest.setContact(reg_mobile);
                            createOrderPaniwalaPackage( paniwalaCreateOrderRequest);*/


                        } else {


                            connected = false;

                            Snackbar snackbar = Snackbar
                                    .make(view, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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


                break;

            case R.id.call_vendor_LL:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+vendorProfileResult.get(adapterPosition).getContact()));
                startActivity(intent);

                break;

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void createOrderPaniwalaPackage(PaniwalaCreateOrderRequest paniwalaCreateOrderRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.PANIWALA_URL).create(RetrofitInterface.class);

        Call<PaniwalaCreateOrderResponse> call = retroFitInterface.createRazorPayOrderPaniwala(paniwalaCreateOrderRequest);

        call.enqueue(new Callback<PaniwalaCreateOrderResponse>() {
            @Override
            public void onResponse(Call<PaniwalaCreateOrderResponse> call, Response<PaniwalaCreateOrderResponse> response) {
                progressDialogDismiss();

                PaniwalaCreateOrderResponse body = response.body();

                if (response.code() == 200) {
                    try {
                        JSONObject results = new JSONObject(body.getRESULT());
                        amount= results.getInt("amount");
                        orderId= results.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    /*
                    status = body.getStatus();
                    orderId = body.getId();
                    entity = body.getEntity();
                    amount = body.getAmount();
                    amountPaid = body.getAmountPaid();
                    amountDue = body.getAmountDue();
                    currency = body.getCurrency();
                    receipt = body.getReceipt();
                    attempts = body.getAttempts();
                    notes = body.getNotes();
                    createdAt =  body.getCreatedAt();*/


                    if (!orderId.equals(null)){

                        Toast.makeText(VendorProfileListActivity.this, getString(R.string.do_not_press_back_button), Toast.LENGTH_SHORT).show();

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                        globalVariable.setOrderId(orderId);
                        globalVariable.setAmount(amount);

                        startPayment(orderId);

                    }else {

                        Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                    }


                } else if (response.code() == 401) {

                    Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();


                } else if (response.code() == 500) {

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(VendorProfileListActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }



                } else if (response.code() == 400) {

                    Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<PaniwalaCreateOrderResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void startPayment(String orderId) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        Checkout.clearUserData(VendorProfileListActivity.this);
        co.setImage(R.drawable.nict_bills_services_logo);


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Paniwala");
            options.put("description", "Package");
            options.put("order_id", orderId);
            //You can omit the image option to fetch the image from dashboard
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", amount);
            options.put("theme.color", "#2196F3");

            JSONObject preFill = new JSONObject();

            preFill.put("email", "shubham.jagtap@nict.ind.in");
            preFill.put("contact", reg_mobile);
            options.put("prefill", preFill);

            co.open(activity, options);


        } catch (Exception e) {

            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            Log.e("Error in payment",e.getMessage());

            e.printStackTrace();

        }
    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Checkout.clearUserData(VendorProfileListActivity.this);
        try {

            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
            razorpayPayment_id = paymentData.getPaymentId();
            razorpayOrder_id = paymentData.getOrderId();
            razorpaySignature = paymentData.getSignature();

            PaniwalaConfirmOrderRequest updateRazPayOrderDto = new PaniwalaConfirmOrderRequest();
          //  updateRazPayOrderDto.setStatus("success");
          //  updateRazPayOrderDto.setService("MPPKVVCL");
            updateRazPayOrderDto.setRazorpay_order_id(razorpayOrder_id);
            updateRazPayOrderDto.setRazorpay_signature(razorpaySignature);
            updateRazPayOrderDto.setRazorpay_payment_id(razorpayPayment_id);
            confirmOrder(updateRazPayOrderDto);


        }catch (Exception e) {

            Log.e("TAG", "Error in submitting payment details", e);
            e.printStackTrace();;
            Log.e("===",e.getMessage().toString()+"======ww");

        }

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

        Checkout.clearUserData(VendorProfileListActivity.this);

        Toast.makeText(VendorProfileListActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();

        /*try {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
*//*
            UpdateRazPayOrderDto updateRazPayOrderDto = new UpdateRazPayOrderDto();
            updateRazPayOrderDto.setStatus("failed");
            updateRazPayOrderDto.setService("MPPKVVCL");
            updateRazPayOrderDto.setOrder_id(globalVariable.getOrderId());
            updateRazPayOrderDto.setCode(i);
            updateRazPayOrderDto.setMessage(s);*//*

            PaniwalaConfirmOrderRequest updateRazPayOrderDto = new PaniwalaConfirmOrderRequest();
            //  updateRazPayOrderDto.setStatus("success");
            //  updateRazPayOrderDto.setService("MPPKVVCL");
            updateRazPayOrderDto.setRazorpay_order_id(globalVariable.getOrderId());
            updateRazPayOrderDto.setRazorpay_signature(razorpaySignature);
            updateRazPayOrderDto.setRazorpay_payment_id(razorpayPayment_id);


            confirmOrder(updateRazPayOrderDto);

        }catch (Exception e) {

            Log.e("TAG", "Error in submitting payment details", e);
            e.printStackTrace();;
            Log.e("===",e.getMessage().toString()+"======ww");
            Log.e("===",e.getMessage().toString()+"======ww");

        }*/

    }


    private void confirmOrder(PaniwalaConfirmOrderRequest confirmOrderRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.PANIWALA_URL).create(RetrofitInterface.class);

        Call<PaniwalaConfirmOrderResponse> call = retroFitInterface.confirmRazorPayOrderPaniwala(confirmOrderRequest);

        call.enqueue(new Callback<PaniwalaConfirmOrderResponse>() {
            @Override
            public void onResponse(Call<PaniwalaConfirmOrderResponse> call, Response<PaniwalaConfirmOrderResponse> response) {
                progressDialogDismiss();

                PaniwalaConfirmOrderResponse updateOrder = response.body();

                if (response.code() == 200) {


                    if (updateOrder.getPayStatus().equalsIgnoreCase("Paid")){

                        paymentStatusDialog("success",updateOrder.getResult().get(0).getOrderId(),updateOrder.getResult().get(0).getCreatedAt());

                    }else {

                        paymentStatusDialog("failed",updateOrder.getResult().get(0).getOrderId(),updateOrder.getResult().get(0).getCreatedAt());

                    }


                } else if (response.code() == 401) {


                } else if (response.code() == 500) {

                    Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<PaniwalaConfirmOrderResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(VendorProfileListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void paymentStatusDialog(String status, String txnNO, int date){

        confirmDialog = new Dialog(VendorProfileListActivity.this);
        confirmDialog.setContentView(R.layout.razor_pay_payment_status_dilog);
        confirmDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        confirmDialog.setCancelable(false);

        TextView order_id_TV = confirmDialog.findViewById(R.id.order_id_TV);
        TextView txn_date_TV = confirmDialog.findViewById(R.id.txn_date_TV);

        //  TextView selected_amount_TV = confirmDialog.findViewById(R.id.selected_amount_TV);
        Button continue_BTN = confirmDialog.findViewById(R.id.continue_BTN);
        Button try_again_BTN = confirmDialog.findViewById(R.id.try_again_BTN);

        ImageView success_IMG = confirmDialog.findViewById(R.id.success_IMG);
        ImageView failed_IMG = confirmDialog.findViewById(R.id.failed_IMG);
        TextView payment_status_TV = confirmDialog.findViewById(R.id.payment_status_TV);
        // email_EditText = confirmDialog.findViewById(R.id.email_EditText);

        order_id_TV.setText(txnNO);

        Date tr_date = new Date(date * 1000);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        String dtStartOK = format.format(tr_date);
        //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);

        txn_date_TV.setText(dtStartOK);


        if (status.equalsIgnoreCase("success")){
            success_IMG.setVisibility(View.VISIBLE);
            failed_IMG.setVisibility(View.GONE);
            try_again_BTN.setVisibility(View.GONE);
            continue_BTN.setVisibility(View.VISIBLE);
            payment_status_TV.setText(getString(R.string.payment_successful));
        }else {
            success_IMG.setVisibility(View.GONE);
            failed_IMG.setVisibility(View.VISIBLE);
            continue_BTN.setVisibility(View.GONE);
            try_again_BTN.setVisibility(View.VISIBLE);
            payment_status_TV.setText(getString(R.string.payment_failed));
        }

        continue_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog.dismiss();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    PaniwalaVendorProfileRequest paniwalaVendorProfileRequest = new PaniwalaVendorProfileRequest();
                    paniwalaVendorProfileRequest.setCap(capacity);
                    paniwalaVendorProfileRequest.setContact(reg_mobile);
                    paniwalaVendorProfileRequest.setPincode(pincode);
                    paniwalaVendorProfileRequest.setSsc(subServiceID);
                    paniwalaVendorProfileRequest.setMsc(mainServiceID);
                    getPaniwalaVendorProfile(paniwalaVendorProfileRequest);


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


        try_again_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });

        confirmDialog.show();
    }


    private void offerDialog(){

        paymentStatusDialog = new Dialog(VendorProfileListActivity.this);
        paymentStatusDialog.setContentView(R.layout.paniwala_package_info);
        paymentStatusDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        paymentStatusDialog.setCancelable(false);


        Button pay_BTN = paymentStatusDialog.findViewById(R.id.pay_BTN);
        Button cancel_button = paymentStatusDialog.findViewById(R.id.cancel_button);
        // email_EditText = confirmDialog.findViewById(R.id.email_EditText);


        pay_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paymentStatusDialog.dismiss();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    PaniwalaCreateOrderRequest paniwalaCreateOrderRequest  = new PaniwalaCreateOrderRequest();
                    paniwalaCreateOrderRequest.setAmount("30");
                    paniwalaCreateOrderRequest.setContact(reg_mobile);
                    createOrderPaniwalaPackage( paniwalaCreateOrderRequest);


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


        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentStatusDialog.dismiss();
            }
        });

        paymentStatusDialog.show();
    }


}