package com.nictbills.app.activities.tbo.hotel;

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
import com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres.HotelPaymentResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.orderresmodel.HotelOrderResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityHotelPaymentBinding;
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

public class HotelPaymentActivity extends BaseActivity implements PaymentResultWithDataListener, ExternalWalletListener {
    ActivityHotelPaymentBinding binding;
    private AlertDialog.Builder alertDialogBuilder;
    SharedPreferences shared, sharedPreferences;
    String gettrace_id, gethotel_index, amount, razorpaySignature, mobileNumber, gethotel_roomindex, gethotel_name, getRoom_Desc, getsequenceno, getpublishprice, getnoofnight;
    HashMap<String, String> hotelparamorder;
    String getorderid = "";
    HashMap<String, String> PaymentStatusparam;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hotel_payment);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_payment);
        Checkout.preload(getApplicationContext());
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gettrace_id = shared.getString("TraceId", "");
        gethotel_index = shared.getString("Hotelindex", "");
//        gettokenhotel_id = shared.getString("Tokenid_Hotel", "");
//        getcountry_code = shared.getString("CountryCode", "");
//        getcity_code = shared.getString("CityCode", "");
//        gethotel_index = shared.getString("Hotelindex", "");
//        gethotel_code = shared.getString("Hotelcode", "");
        gethotel_name = shared.getString("HotelName", "");
        getpublishprice = shared.getString("publishprice", "");
        getnoofnight = shared.getString("NOOFNIGHT", "");
        getsequenceno = shared.getString("SequenceNo", "");
        gethotel_roomindex = shared.getString("Roomindex", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
        Log.e("sa...", "sam" + mobileNumber);
//        getagency_id = shared.getString("Agencyid", "");
//        getRoom_TypeCode = shared.getString("RoomTypecode", "");
        getRoom_Desc = shared.getString("Roomdesc", "");
//        getRoom_TypeName = shared.getString("RoomTypeName", "");
//        getRoom_Plancode = shared.getString("RoomPlanCode", "");
//        getRoom_PlanName = shared.getString("RoomPlanName", "");
        binding.tvHotelname.setText(gethotel_name);
        binding.tvRoomtype.setText(getRoom_Desc);
        binding.tvRoomprice.setText("Rs." + getpublishprice);
        binding.tvNoofnight.setText(getnoofnight);
        binding.totalTvrs.setText("Rs." + getpublishprice);
        binding.grandTvrs.setText("Rs." + getpublishprice);
//        alertDialogBuilder = new AlertDialog.Builder(HotelPaymentActivity.this);
//        alertDialogBuilder.setCancelable(false);
//        alertDialogBuilder.setTitle("Payment Result");
//        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {
//            //do nothing
//        });

        hotelparamorder = new HashMap<>();
        hotelparamorder.put("mobileNo", mobileNumber);
        hotelparamorder.put("resultIndex", gethotel_index);
        hotelparamorder.put("traceId", gettrace_id);
        hotelordergenerate(hotelparamorder);

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

    public void hotelordergenerate(HashMap<String, String> hotelparamorder) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelOrderResponseModel> call = retroFitInterface.orderhotel(hotelparamorder);
        call.enqueue(new Callback<HotelOrderResponseModel>() {
            @Override
            public void onResponse(Call<HotelOrderResponseModel> call, Response<HotelOrderResponseModel> response) {
                Log.e("orderfor hotel", "onResponse: " + response.body());
                progressDialogDismiss();

                if (response.code() == 200) {
                    HotelOrderResponseModel hotelOrderResponseModel = response.body();
                    Log.e("order id", "onResponse: " + hotelOrderResponseModel.getOrderId());
                    Log.e("recipt  no", "onResponse: " + hotelOrderResponseModel.getReceipt());
                    Log.e("amount  no", "onResponse: " + hotelOrderResponseModel.getAmount());
                    getorderid = hotelOrderResponseModel.getOrderId();
                    amount = hotelOrderResponseModel.getAmount();
                } else {
                    Toast.makeText(HotelPaymentActivity.this, "Response issue", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<HotelOrderResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("hotelgenearte", "onResponse: " + t.toString());

            }
        });
    }

    public void startPayment() {
        final Activity activity = this;
        final Checkout co = new Checkout();
        co.setImage(R.drawable.nict_bills_services_logo);
        co.setKeyID("rzp_live_5JBK5KK8qnwB4L");
//        String samount = "500";
//        int amount = Math.round(Float.parseFloat(samount) * 100);
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Hotel Booking");
            options.put("description", "Hotel Booking Payment");
            options.put("order_id", getorderid);
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("theme.color", "#2196F3");
            options.put("currency", "INR");
            options.put("amount", amount);
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

    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try {
//            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: " + paymentData.getData());
//            alertDialogBuilder.show();
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
//            razorpaySignature = paymentData.getSignature().toString();
//            Log.e("signature","failur"+razorpaySignature);
//            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
////            JSONObject jsonObject1 = jsonObject.getJSONObject("error");
//            alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: " + jsonObject.optString("razorpay_payment_id"));
////            alertDialogBuilder.setMessage("Payment Successful:\n "+jsonObject1.optString("description")+"\n"+"Reason :"+jsonObject1.optString("reason"));
//            Log.e("on success ", "response" + paymentData.getData());
//            alertDialogBuilder.show();
            Log.e("signature", "success" + paymentData.getData());
//            PaymentStatusparam = new HashMap<>();
//            PaymentStatusparam.put("orderId",getorderid);
//            paymentcheck(PaymentStatusparam);

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
//            razorpaySignature = paymentData.getSignature();
//            Log.e("signature","failur"+razorpaySignature);
//            razorpaySignature = paymentData.getSignature().toString();
//            Log.e("signature","failur"+razorpaySignature);

            Log.e("on failed ", "response" + paymentData.getData());
//            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
//            JSONObject jsonObject1 = jsonObject.getJSONObject("error");
//
//            Log.e("on faile23 ", "response2" + jsonObject1);
//            Log.e("on faile23 ", "response2" + jsonObject1.optString("code"));
//            alertDialogBuilder.setMessage("Payment Failed:\n " + jsonObject1.optString("description") + "\n" + "Reason :" + jsonObject1.optString("reason"));
//            alertDialogBuilder.show();
//            PaymentStatusparam = new HashMap<>();
//            PaymentStatusparam.put("orderId", getorderid);
//            paymentcheck(PaymentStatusparam);
//            PaymentStatusparam = new HashMap<>();
//            PaymentStatusparam.put("orderId",getorderid);
//            paymentcheck(PaymentStatusparam);

            Toast.makeText(context, "Payment Failur" , Toast.LENGTH_SHORT);
            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
            JSONObject jsonObject1 = jsonObject.getJSONObject("error");
            Toast.makeText(context, jsonObject1.toString() , Toast.LENGTH_SHORT);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paymentcheck(HashMap<String, String> PaymentStatusparam) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
//        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL_ORDER).create(RetrofitInterface.class);
        Call<HotelPaymentResponseModel> call = retroFitInterface.gethotelpayment(PaymentStatusparam);
        call.enqueue(new Callback<HotelPaymentResponseModel>() {
            @Override
            public void onResponse(Call<HotelPaymentResponseModel> call, Response<HotelPaymentResponseModel> response) {
                Log.e("PAYMENT CHECK1 ", "onResponse: " + response.body());
                progressDialogDismiss();
                HotelPaymentResponseModel hotelPaymentResponseModel = response.body();
                if (response.code() == 200) {
//                    Log.e("PAYMENT 2 ", "onResponse: "+flightPaymentStatusResponse. );
                    Log.e("PAYMENT CHECK12  is success ", "onResponse:");
                    if(hotelPaymentResponseModel.getErrorCode()==0)
                    {
                        if(hotelPaymentResponseModel.getData()==null){
                            Toast.makeText(HotelPaymentActivity.this,"Technical Issue", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(HotelPaymentActivity.this, DashboardActivity.class);
//                            startActivity(intent);
                        }
                        else if(hotelPaymentResponseModel.getData().equals(""))
                        {
                            Toast.makeText(HotelPaymentActivity.this,"Technical Issue", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(HotelPaymentActivity.this, DashboardActivity.class);
//                            startActivity(intent);
                        }
                        else
                        {
                            shared = getSharedPreferences("nict", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(hotelPaymentResponseModel); // myObject - instance of MyObject
                            editor.putString("HotelPaymentStatus", json);
                            editor.apply();
                            Intent intent = new Intent(HotelPaymentActivity.this, HotelBookActivity.class);
                            startActivity(intent);
                        }


                    }
                    else if(hotelPaymentResponseModel.getErrorCode()==500){
                        Toast.makeText(HotelPaymentActivity.this, hotelPaymentResponseModel.getErrorMessage(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(HotelPaymentActivity.this, DashboardActivity.class);
//                        startActivity(intent);
                    }
                    else if(hotelPaymentResponseModel.getErrorCode()==2)
                    {
                        Toast.makeText(HotelPaymentActivity.this, hotelPaymentResponseModel.getErrorMessage(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(HotelPaymentActivity.this, DashboardActivity.class);
//                        startActivity(intent);
                    }


                } else {
                    Log.e("PAYMENT 9", "onResponse: ");

                    Toast.makeText(HotelPaymentActivity.this, "Response issue", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<HotelPaymentResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("hotelgenearte", "onResponse: " + t.toString());

            }
        });
    }


}