package com.nictbills.app.activities.tbo.bus;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.bus.model.busorderresp.BusOrderCreateResponseModelResponse;
import com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse.BusPaymentResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityBusPaymentBinding;
import com.nictbills.app.utils.Util;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusPaymentActivity extends BaseActivity implements PaymentResultWithDataListener, ExternalWalletListener {
    private AlertDialog.Builder alertDialogBuilder;
    SharedPreferences shared, sharedPreferences;
    ActivityBusPaymentBinding binding;
    HashMap<String, String> hotelparamorder;
    String getorderid ,final_mainamount ,amount, mobileNumber, getfinal_finalseat ,getfinal_finalamount ,getseatname, getpublishedprice, gettravlesname, getoriginname, getdropingname, getulastname, getgendertype, getuserage, getuseradd, getusermobile, getuseremail, getname, getpublished, getseattype, getseatstatus, getseatrow, getseatcolumnno, getdroping_index, getseatindex, getbording_index, getBusToken, getresultindex, getAgency_id, getMember_id, getTrace_id;
    HashMap<String, String> PaymentStatusparam;
    HashMap<String, String> Busparamorder;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bus_payment);
        Checkout.preload(getApplicationContext());
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getTrace_id = shared.getString("TraceId", "");
        getresultindex = shared.getString("Resultindex", "");
        gettravlesname = shared.getString("BusTravles", "");
        getoriginname = shared.getString("originname", "");
        getdropingname = shared.getString("Dropinname", "");
        getpublishedprice = shared.getString("Publishedprice", "");
        getfinal_finalseat = shared.getString("finalseat", "");
        getfinal_finalamount = shared.getString("finalamount", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
//        alertDialogBuilder = new AlertDialog.Builder(BusPaymentActivity.this);
//        alertDialogBuilder.setCancelable(false);
//        alertDialogBuilder.setTitle("Payment Result");
//        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {
//            //do nothing
//        });
        binding.tvTravelname.setText(gettravlesname);
        binding.tvOrigin.setText(getoriginname);
        binding.tvDestination.setText(getdropingname);
        binding.tvSeatno.setText(getfinal_finalseat);
        binding.tvSeatprice.setText(getpublishedprice);
        binding.tvPrice.setText("Rs." + getfinal_finalamount);
        final_mainamount= String.valueOf(Double.valueOf(getpublishedprice)+Double.valueOf(getfinal_finalamount));
        Log.e("final_mainamount",".ty.."+final_mainamount);
        binding.totalTvrs.setText("Rs." + getfinal_finalamount);
        binding.grandTvrs.setText("Rs." + getfinal_finalamount);
//        binding.totalTvrs.setText("Rs." + final_mainamount);
//        binding.grandTvrs.setText("Rs." + final_mainamount);

        Busparamorder = new HashMap<>();
        Busparamorder.put("mobileNo",mobileNumber);
        Busparamorder.put("resultIndex",getresultindex);
        Busparamorder.put("traceId",getTrace_id);
        busordergenerate(Busparamorder);

        binding.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnPay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaymentStatusparam = new HashMap<>();
                PaymentStatusparam.put("orderId",getorderid);
                   paymentcheck(PaymentStatusparam);
            }
        });


    }


    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try {
            Log.e("on waleet ", "response" + paymentData.getData());



            PaymentStatusparam = new HashMap<>();
            PaymentStatusparam.put("orderId",getorderid);
            paymentcheck(PaymentStatusparam);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
            Log.e("on success ", "response" + paymentData.getData());

            PaymentStatusparam = new HashMap<>();
            PaymentStatusparam.put("orderId",getorderid);
            paymentcheck(PaymentStatusparam);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try {
            Toast.makeText(context, "Payment Failur" , Toast.LENGTH_SHORT);
            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
            JSONObject jsonObject1 = jsonObject.getJSONObject("error");
            Toast.makeText(context, jsonObject1.toString() , Toast.LENGTH_SHORT);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//

    public void busordergenerate(HashMap<String, String> Busparamorder) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusOrderCreateResponseModelResponse> call = retroFitInterface.busordercreation(Busparamorder);
        call.enqueue(new Callback<BusOrderCreateResponseModelResponse>() {
            @Override
            public void onResponse(Call<BusOrderCreateResponseModelResponse> call, Response<BusOrderCreateResponseModelResponse> response) {
                Log.e("orderfor bus", "onResponse: " + response.body());
                progressDialogDismiss();
//                BusOrderCreateResponseModelResponse
                if (response.code() == 200) {
                    BusOrderCreateResponseModelResponse busOrderCreateResponseModelResponse = response.body();
                    Log.e("order id", "onResponse: " + busOrderCreateResponseModelResponse.getOrderId());
                    Log.e("recipt  no", "onResponse: " + busOrderCreateResponseModelResponse.getReceipt());
                    Log.e("amount  no", "onResponse: " + busOrderCreateResponseModelResponse.getAmount());
                    getorderid = busOrderCreateResponseModelResponse.getOrderId();
                    amount = busOrderCreateResponseModelResponse.getAmount();
                } else {
                    Toast.makeText(BusPaymentActivity.this, "Response issue", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<BusOrderCreateResponseModelResponse> call, Throwable t) {
                progressDialogDismiss();
                Log.e("hotelgenearte", "onResponse: " + t.toString());

            }
        });
    }

    public void startPayment() {
        final Activity activity = this;
        final Checkout co = new Checkout();
        co.setKeyID("rzp_live_5JBK5KK8qnwB4L");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Bus Booking");
            options.put("description", "Bus Booking Payment");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            options.put("order_id", getorderid);
            options.put("currency", "INR");
            options.put("theme.color", "#2196F3");
            options.put("amount",amount);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", mobileNumber);
            options.put("prefill", preFill);
            co.open(activity, options);

        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

    }

    public void paymentcheck(HashMap<String, String> PaymentStatusparam)
    {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusPaymentResponseModel> call = retroFitInterface.getbuspayment(PaymentStatusparam);
        call.enqueue(new Callback<BusPaymentResponseModel>() {
            @Override
            public void onResponse(Call<BusPaymentResponseModel> call, Response<BusPaymentResponseModel> response) {
                Log.e("PAYMENT CHECK1 ", "onResponse: " + response.body());
                progressDialogDismiss();
                BusPaymentResponseModel busPaymentResponseModel = response.body();
                if (response.code() == 200) {
//                    Log.e("PAYMENT 2 ", "onResponse: "+flightPaymentStatusResponse. );
                    Log.e("PAYMENT CHECK12  is success ", "onResponse:");

                    if(busPaymentResponseModel.getErrorCode()==0)
                    {
                        if(busPaymentResponseModel.getData()==null){
                            Toast.makeText(BusPaymentActivity.this,"Technical Issue", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BusPaymentActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }
                        else if(busPaymentResponseModel.getData().equals(""))
                        {
                            Toast.makeText(BusPaymentActivity.this,"Technical Issue", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BusPaymentActivity.this, DashboardActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            shared = getSharedPreferences("nict", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(busPaymentResponseModel); // myObject - instance of MyObject
                            editor.putString("BusPaymentStatus", json);
                            editor.apply();
                            Intent intent = new Intent(BusPaymentActivity.this, BusBookingConfirmationActivity.class);
                            startActivity(intent);
                        }


                    }
                    else if(busPaymentResponseModel.getErrorCode()==500){
                        Toast.makeText(BusPaymentActivity.this, busPaymentResponseModel.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BusPaymentActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                    else if(busPaymentResponseModel.getErrorCode()==2)
                    {
                        Toast.makeText(BusPaymentActivity.this, busPaymentResponseModel.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BusPaymentActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }


                } else {
                    Log.e("PAYMENT 9", "onResponse: ");

                    Toast.makeText(BusPaymentActivity.this, "Response issue", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<BusPaymentResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("hotelgenearte", "onResponse: " + t.toString());

            }
        });

    }


}