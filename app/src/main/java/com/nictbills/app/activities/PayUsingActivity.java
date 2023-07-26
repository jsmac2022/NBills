package com.nictbills.app.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.GetOrderCreateResDto;
import com.nictbills.app.activities.health_id_abdm.dto.Notes;
import com.nictbills.app.activities.health_id_abdm.dto.RazPayElectricityRequest;
import com.nictbills.app.activities.health_id_abdm.dto.UpdateRazPayOrderDto;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.GetRewardPointsResponse;
import com.nictbills.app.activities.health_id_abdm.dto.update_order.PaymentEntity;
import com.nictbills.app.activities.health_id_abdm.dto.update_order.UpdateOrder;
import com.nictbills.app.global.GlobalClass;
import com.nictbills.app.utils.Util;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayUsingActivity extends BaseActivity implements PaymentResultWithDataListener {

    private ImageView backArrow_IMG;
    private LinearLayout  upi_checkbox_LinearLayout, raz_pay_checkbox_LinearLayout,parentId;
    private int rewardPoints=0,rewardPointForward=0;
    private TextView toolbar_title,selected_ivrs_TV,selected_name_TV,selected_amount_TV;
    private String UPI,credential,mobileNumber,useReward="No";
    private ListView app_listView;
    private String orderId,loc_cd,gr_no,entity,currency,receipt,status,email,razorpayPayment_id,razorpayOrder_id,razorpaySignature,
            paymentStatus,paymentReceipt,service,service_id,enc_data,ivrsNo,cons_name,net_bill,rd_no,cons_add,circle,net_incl_surchage,due_date;
    private Integer amount,attempts,amountPaid,amountDue,createdAt;
    private List<PaymentEntity> paymentEntities;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private Dialog confirmDialog;
    private EditText email_EditText;
    private Notes notes;
    private CheckBox checkbox_reward_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_using);

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        upi_checkbox_LinearLayout = findViewById(R.id.upi_checkbox_LinearLayout);

        toolbar_title = findViewById(R.id.toolbar_title);
        app_listView = findViewById(R.id.app_listView);
        parentId = findViewById(R.id.parentId);
        selected_ivrs_TV = findViewById(R.id.selected_ivrs_TV);
        selected_name_TV = findViewById(R.id.selected_name_TV);
        selected_amount_TV = findViewById(R.id.selected_amount_TV);
        checkbox_reward_balance = findViewById(R.id.checkbox_reward_balance);

        checkbox_reward_balance.setEnabled(false);
        raz_pay_checkbox_LinearLayout = findViewById(R.id.raz_pay_checkbox_LinearLayout);
        toolbar_title.setText(getString(R.string.pay_using));

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");

        Intent intent = getIntent();
        ivrsNo = intent.getStringExtra("ivrsNo");
        loc_cd = intent.getStringExtra("loc_cd");
        gr_no = intent.getStringExtra("gr_no");
        rd_no = intent.getStringExtra("rd_no");
        cons_name = intent.getStringExtra("cons_name");
        cons_add = intent.getStringExtra("cons_add");
        circle = intent.getStringExtra("circle");
        net_bill = intent.getStringExtra("net_bill");
        net_incl_surchage = intent.getStringExtra("net_incl_surchage");
        due_date = intent.getStringExtra("due_date");
        enc_data = intent.getStringExtra("enc_data");

        selected_ivrs_TV.setText(ivrsNo);
        selected_name_TV.setText(cons_name);
        selected_amount_TV.setText("₹ "+net_bill);

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(PayUsingActivity.this, AddIVRSActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finishAffinity();

            }
        });


        getRewardPoints();

        upi_checkbox_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(parentId.getWindowToken(), 0);

                float totAmount=Float.parseFloat(net_bill);

              //  Integer totAmount = Integer.valueOf(net_bill);

                if (totAmount<=0){

                    Snackbar.make(v, getString(R.string.invalid_amount), Snackbar.LENGTH_SHORT).show();

                } else {

                    Intent intentUPI= new Intent(PayUsingActivity.this, PayUsingUPIActivity.class);
                    intentUPI.putExtra("ivrsNo",ivrsNo);
                    intentUPI.putExtra("loc_cd",loc_cd);
                    intentUPI.putExtra("gr_no",gr_no);
                    intentUPI.putExtra("rd_no",rd_no);
                    intentUPI.putExtra("cons_name",cons_name);
                    intentUPI.putExtra("cons_add",cons_add);
                    intentUPI.putExtra("circle",circle);
                    intentUPI.putExtra("net_bill",net_bill);
                    intentUPI.putExtra("net_incl_surchage",net_incl_surchage);
                    intentUPI.putExtra("due_date",due_date);
                    intentUPI.putExtra("enc_data",enc_data);
                    intentUPI.putExtra("useReward",useReward);
                    intentUPI.putExtra("REWARD_POINT",rewardPointForward);
                    startActivity(intentUPI);
                    finish();

                }
            }
        });


        raz_pay_checkbox_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(parentId.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    float totAmount=Float.parseFloat(net_bill);
                    //Integer totAmount = Integer.valueOf(net_bill);

                    if (totAmount<=0){

                        Snackbar.make(v, getString(R.string.invalid_amount), Snackbar.LENGTH_SHORT).show();

                    } else {
                        razor_PayFeePopup();

                    }


            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(parentId, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

        checkbox_reward_balance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //checked
                    useReward="Yes";
                    rewardPointForward = rewardPoints;
                    //Toast.makeText(PayUsingActivity.this, "check", Toast.LENGTH_SHORT).show();
                } else {
                    useReward="No";
                    rewardPointForward=0;
                    //not checked
                    //Toast.makeText(PayUsingActivity.this, "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

   /* public void Check(View v)
    {
        String msg="";

        if(checkbox_reward_balance.isChecked())
            msg = msg + " Painting ";
        // Toast is created to display the
        // message using show() method.
        Toast.makeText(this, msg + "are selected",
                Toast.LENGTH_LONG).show();
    }
*/
    @Override
    public void onBackPressed() {

            Intent intent = new Intent(PayUsingActivity.this, AddIVRSActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finishAffinity();


    }


    private void createOrder(RazPayElectricityRequest payElectricityRequest) {

       progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetOrderCreateResDto> call = retroFitInterface.createOrder(credential, payElectricityRequest);

        call.enqueue(new Callback<GetOrderCreateResDto>() {
            @Override
            public void onResponse(Call<GetOrderCreateResDto> call, Response<GetOrderCreateResDto> response) {
                progressDialogDismiss();

                GetOrderCreateResDto body = response.body();

                if (response.code() == 200) {

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
                    createdAt =  body.getCreatedAt();


                    if (!orderId.equals(null)){

                        Toast.makeText(PayUsingActivity.this, getString(R.string.do_not_press_back_button), Toast.LENGTH_SHORT).show();

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                        globalVariable.setOrderId(orderId);
                        globalVariable.setAmount(amount);

                        startPayment(orderId);

                    }else {

                        Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PayUsingActivity.this, LoginActivity.class);
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
                        Toast.makeText(PayUsingActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }



                } else if (response.code() == 400) {

                    Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<GetOrderCreateResDto> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void startPayment(String orderId) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        Checkout.clearUserData(PayUsingActivity.this);
        co.setImage(R.drawable.nict_bills_services_logo);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "MPPKVVCL");
            options.put("description", "Electricity Bill Payment");
            options.put("order_id", orderId);
            //You can omit the image option to fetch the image from dashboard
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", amount);
            options.put("theme.color", "#2196F3");

            JSONObject preFill = new JSONObject();

            preFill.put("email", email);
            preFill.put("contact", mobileNumber);
            options.put("prefill", preFill);

            co.open(activity, options);


        } catch (Exception e) {

            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();

            Log.e("Error in payment",e.getMessage());

            e.printStackTrace();

        }
    }

    private void confirmOrder(UpdateRazPayOrderDto updateRazPayOrderDto) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<UpdateOrder> call = retroFitInterface.confirmOrder(credential, updateRazPayOrderDto);

        call.enqueue(new Callback<UpdateOrder>() {
            @Override
            public void onResponse(Call<UpdateOrder> call, Response<UpdateOrder> response) {
                progressDialogDismiss();

                UpdateOrder updateOrder = response.body();

                if (response.code() == 200) {

                    paymentStatus = updateOrder.getStatus();
                    paymentReceipt = updateOrder.getReceipt();
                    service = updateOrder.getService();
                    service_id = updateOrder.getServiceId();

                    if (paymentStatus.equalsIgnoreCase("success")){

                        paymentEntities = updateOrder.getPaymentEntity();

                        for (int i = 0; i < paymentEntities.size() ; i++){

                            if(paymentEntities.get(i).getStatus().equalsIgnoreCase("captured")){

                                PaymentEntity paymentEntity = new PaymentEntity();
                                paymentEntity.setEntity(paymentEntities.get(i).getEntity());
                                paymentEntity.setAmount(paymentEntities.get(i).getAmount());
                                paymentEntity.setStatus(paymentEntities.get(i).getStatus());
                                paymentEntity.setCurrency(paymentEntities.get(i).getCurrency());
                                paymentEntity.setOrderId(paymentEntities.get(i).getOrderId());
                                paymentEntity.setInternational(paymentEntities.get(i).getInternational());
                                paymentEntity.setMethod(paymentEntities.get(i).getMethod());
                                paymentEntity.setDescription(paymentEntities.get(i).getDescription());
                                paymentEntity.setCardId(paymentEntities.get(i).getCardId());
                                paymentEntity.setFee(paymentEntities.get(i).getFee());
                                paymentEntity.setId(paymentEntities.get(i).getId());

                                Intent intent = new Intent(PayUsingActivity.this,PaymentStatusActivity.class);
                                intent.putExtra("Details",paymentEntity);
                                intent.putExtra("Date",paymentEntities.get(i).getCreatedAt()+"");
                                intent.putExtra("paymentReceipt",paymentReceipt);
                                intent.putExtra("service",service);
                                intent.putExtra("service_id",service_id);
                                intent.putExtra("PayUSING","Razorpay");
                                startActivity(intent);
                                finish();
                            }

                        }

                    } else if (paymentStatus.equalsIgnoreCase("failed")){

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();


                        PaymentEntity paymentEntity = new PaymentEntity();
                        paymentEntity.setAmount(globalVariable.getAmount());
                        paymentEntity.setStatus("failed");
                        paymentEntity.setId(globalVariable.getOrderId());

                        Intent intent = new Intent(PayUsingActivity.this,PaymentStatusActivity.class);
                        intent.putExtra("Details",paymentEntity);
                        intent.putExtra("paymentReceipt",paymentReceipt);
                        intent.putExtra("service",service);
                        intent.putExtra("service_id",service_id);
                        intent.putExtra("PayUSING","Razorpay");
                        startActivity(intent);
                        finish();


                    }

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PayUsingActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<UpdateOrder> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        Checkout.clearUserData(PayUsingActivity.this);
        try {

            razorpayPayment_id = paymentData.getPaymentId();
            razorpayOrder_id = paymentData.getOrderId();
            razorpaySignature = paymentData.getSignature();

            UpdateRazPayOrderDto updateRazPayOrderDto = new UpdateRazPayOrderDto();
            updateRazPayOrderDto.setStatus("success");
            updateRazPayOrderDto.setService("MPPKVVCL");
            updateRazPayOrderDto.setOrder_id(razorpayOrder_id);
            updateRazPayOrderDto.setSignature(razorpaySignature);
            updateRazPayOrderDto.setPayment_id(razorpayPayment_id);
            confirmOrder(updateRazPayOrderDto);


        }catch (Exception e) {

            Log.e("TAG", "Error in submitting payment details", e);
            e.printStackTrace();;
            Log.e("===",e.getMessage().toString()+"======ww");

        }


    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

        Checkout.clearUserData(PayUsingActivity.this);

        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            UpdateRazPayOrderDto updateRazPayOrderDto = new UpdateRazPayOrderDto();
            updateRazPayOrderDto.setStatus("failed");
            updateRazPayOrderDto.setService("MPPKVVCL");
            updateRazPayOrderDto.setOrder_id(globalVariable.getOrderId());
            updateRazPayOrderDto.setCode(i);
            updateRazPayOrderDto.setMessage(s);
            confirmOrder(updateRazPayOrderDto);

        }catch (Exception e) {

            Log.e("TAG", "Error in submitting payment details", e);
            e.printStackTrace();;
            Log.e("===",e.getMessage().toString()+"======ww");
            Log.e("===",e.getMessage().toString()+"======ww");

        }

    }



    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void confirmPopUpDialog(){
        confirmDialog = new Dialog(PayUsingActivity.this);
        confirmDialog.setContentView(R.layout.bill_pay_confirm_popup);
        confirmDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        confirmDialog.setCancelable(false);

        TextView selected_ivrs_TV = confirmDialog.findViewById(R.id.selected_ivrs_TV);
        TextView selected_name_TV = confirmDialog.findViewById(R.id.selected_name_TV);
        TextView selected_amount_TV = confirmDialog.findViewById(R.id.selected_amount_TV);
        Button pay_BTN = confirmDialog.findViewById(R.id.pay_BTN);
        Button cancel_button = confirmDialog.findViewById(R.id.cancel_button);
        email_EditText = confirmDialog.findViewById(R.id.email_EditText);

        selected_ivrs_TV.setText(ivrsNo);
        selected_name_TV.setText(cons_name);
        selected_amount_TV.setText("₹ "+net_bill);


        pay_BTN.setOnClickListener(new View.OnClickListener() {
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


                    email = email_EditText.getText().toString();


                    if (isEmpty(email_EditText)){

                        email = "supportnb@nictgroup.in";
                        confirmDialog.dismiss();

                        RazPayElectricityRequest request = new RazPayElectricityRequest();
                        request.setEnc_data(enc_data);
                        request.setService("MPPKVVCL");
                        request.setUseReward(useReward);
                        createOrder(request);

                    }else {

                        confirmDialog.dismiss();
                        RazPayElectricityRequest request = new RazPayElectricityRequest();
                        request.setEnc_data(enc_data);
                        request.setService("MPPKVVCL");
                        request.setUseReward(useReward);
                        createOrder(request);
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


        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });

        confirmDialog.show();
    }


    private void razor_PayFeePopup() {
        final Dialog successDialog;
        successDialog = new Dialog(PayUsingActivity.this, R.style.Theme_Dialog);
        successDialog.setContentView(R.layout.razor_pay_fee_pop_up);
        successDialog.setCancelable(false);

        Button ok_button = successDialog.findViewById(R.id.ok_button);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successDialog.dismiss();
                confirmPopUpDialog();
            }
        });
        successDialog.show();
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
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PayUsingActivity.this, LoginActivity.class);
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
                        Toast.makeText(PayUsingActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(PayUsingActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetRewardPointsResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(PayUsingActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
