package com.nictbills.app.activities.tbo.flight;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.flight.model.flightpaymentstatus.FlightPaymentStatusResponse;
import com.nictbills.app.activities.tbo.hotel.model.orderresmodel.HotelOrderResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFlightPaymentBinding;
import com.nictbills.app.utils.Util;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightPaymentActivity extends BaseActivity implements PaymentResultWithDataListener, ExternalWalletListener {
    private static final String TAG = FlightPaymentActivity.class.getSimpleName();
    private AlertDialog.Builder alertDialogBuilder;
    Button button;
    ActivityFlightPaymentBinding binding;
    SharedPreferences shared, sharedPreferences;
    String getseatlist, getcountry, getseatno, getuname, getcityname, getlname, getusermobile, getuemail, getaddress, getgender, gettoken, gettrace, getagency_id, getresultindex;
    String getflightname, getorigincode, getdesticode, getemail, getairlinecode, getflihtno, mobileNumber,  gettype, getpublishedprice, getllctype, amount, finalprice, getorderid;
    HashMap<String, String> Flightparamorder;
    HashMap<String, String> PaymentStatusparam;
    HashMap<String, String> TicketNONllc;
    JSONObject jsonobjfare;
    String get_finalprice = "0.0";
    int getseatcount;
    int addnoofmember=0,count =0,countchild=0,countinfant=0;
    SeatViewActivity seatViewActivity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_payment);
        Checkout.preload(getApplicationContext());
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gettoken = shared.getString("Tokenid", "");
        gettrace = shared.getString("TraceId", "");
        getagency_id = shared.getString("Agencyid", "");
        getresultindex = shared.getString("ResultIndex", "");
        gettype = shared.getString("type", "");
        getpublishedprice = shared.getString("publishedprice", "");
        getllctype = shared.getString("LLCType", "");
        gettype = shared.getString("type", "");
        getcountry = shared.getString("Country", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
        getseatno = shared.getString("seatno", "");
        get_finalprice = shared.getString("price", "");
        count = Integer.parseInt(String.valueOf(Integer.parseInt(shared.getString("Adultno", ""))));
        countchild = Integer.parseInt(shared.getString("Childno", ""));
        countinfant = Integer.parseInt(shared.getString("Infant", ""));
        getflihtno = shared.getString("flightno", "");
        getorigincode = shared.getString("origincode", "");
        getdesticode = shared.getString("destinationcode", "");
        getflightname = shared.getString("flightname", "");
        String json = shared.getString("FarequoteResponse", "");
        getairlinecode = shared.getString("Airlinecode", "");
        getseatcount = shared.getInt("seatcount", 1);
        getseatlist = shared.getString("listseat", "");
        addnoofmember=count+countchild+countinfant;
        seatViewActivity = new SeatViewActivity();
        binding.tvNoofmember.setText(addnoofmember+"");
        binding.tvFLIGHTname.setText(getflightname);
        binding.tvFlightno.setText(getflihtno);
        binding.tvFlightprice.setText("Rs." + getpublishedprice);

        Log.e("get_finalprice","ty"+get_finalprice);

        if (gettype.equals("seat"))
        {
            if (get_finalprice.equalsIgnoreCase("")) {
                binding.layseatprefrencePrice.setVisibility(View.GONE);
                binding.totalTvfprice.setText("Rs." + getpublishedprice);
                binding.grandTvfprice.setText("Rs." + getpublishedprice);
            } else {
                binding.layoutPref.setVisibility(View.VISIBLE);
                binding.tvSeatprice.setText("Rs." + get_finalprice);
                finalprice = String.valueOf(Double.valueOf(getpublishedprice) + Double.valueOf(get_finalprice));
                binding.totalTvfprice.setText("Rs." + finalprice);
                binding.grandTvfprice.setText("Rs." + finalprice);

            }
            if (getseatno.equalsIgnoreCase("")) {
                binding.layoutPref.setVisibility(View.GONE);
            } else {
                binding.layseatprefrencePrice.setVisibility(View.VISIBLE);
                binding.tvSeatPrefrence.setText(getseatno);
            }
        }
        else {
            binding.layoutPref.setVisibility(View.GONE);
            binding.layseatprefrencePrice.setVisibility(View.GONE);
            binding.totalTvfprice.setText("Rs." + getpublishedprice);
            binding.grandTvfprice.setText("Rs." + getpublishedprice);
        }

        if (gettype.equals("seat"))
        {
            if (get_finalprice.equalsIgnoreCase("")) {
                Flightparamorder = new HashMap<>();
                Flightparamorder.put("isLcc", getllctype);
                Flightparamorder.put("mobileNo", mobileNumber);
                Flightparamorder.put("resultIndex", getresultindex);
                Flightparamorder.put("extraCharge", get_finalprice);
            }
            else {

                JSONArray jsonarrayseat = new JSONArray();
                for (int i = 0; i < seatViewActivity.arList.size(); i++) {
                    JSONObject seatobj = new JSONObject();
                    try {
                        seatobj.put("AirlineCode", getairlinecode);
                        seatobj.put("FlightNumber", getflihtno);
//                seatobj.put("CraftType", "year[i]");
                        seatobj.put("Origin", getorigincode);
                        seatobj.put("Destination", getdesticode);
                        seatobj.put("AvailablityType", seatViewActivity.arList.get(i).getSeatavilability());
//                seatobj.put("Description", "birthday[i]");
//                seatobj.put("Code", "birthday[i]");
//                seatobj.put("RowNo", "birthday[i]");
                        seatobj.put("SeatNo", seatViewActivity.arList.get(i).getSeatno());
//                seatobj.put("SeatType", "birthday[i]");
//                seatobj.put("SeatWayType", "birthday[i]");
//                seatobj.put("Deck", "birthday[i]");
                        seatobj.put("Currency", "INR");
                        seatobj.put("Price", seatViewActivity.arList.get(i).getSeatprice());
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    jsonarrayseat.put(seatobj);
                }
                Log.e("send","call"+jsonarrayseat.toString());

                Flightparamorder = new HashMap<>();
                Flightparamorder.put("isLcc", getllctype);
                Flightparamorder.put("mobileNo", mobileNumber);
                Flightparamorder.put("resultIndex", getresultindex);
                Flightparamorder.put("extraCharge", get_finalprice);
                Flightparamorder.put("seat", jsonarrayseat.toString());

            }
        }
        else
        {
            Flightparamorder = new HashMap<>();
            Flightparamorder.put("isLcc", getllctype);
            Flightparamorder.put("mobileNo", mobileNumber);
            Flightparamorder.put("resultIndex", getresultindex);
            Flightparamorder.put("extraCharge", get_finalprice);
        }

        ordergenrate(Flightparamorder);
//        alertDialogBuilder = new AlertDialog.Builder(FlightPaymentActivity.this);
//        alertDialogBuilder.setCancelable(false);
//        alertDialogBuilder.setTitle("Payment Result");
//        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {
//            //do nothing
//        });

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonresponse = jsonObject.getJSONObject("Response");
            JSONObject jsonresult = jsonresponse.getJSONObject("Results");
            jsonobjfare = jsonresult.getJSONObject("Fare");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        binding.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        binding.btnPay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentStatusparam = new HashMap<>();
                PaymentStatusparam.put("order_id", getorderid);
                PaymentStatusparam.put("mobileNo", mobileNumber);
                PaymentStatusparam.put("ResultIndex", getresultindex);
                PaymentStatusparam.put("trace_id", gettrace);
                PaymentStatusparam.put("isLcc", getllctype);
                paymentcheck(PaymentStatusparam);
            }
        });

        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try {
//            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: " + paymentData.getData());
//            alertDialogBuilder.show();
            Log.e("on walet ", "response" + paymentData.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
//            JSONObject jsonObject1 = jsonObject.getJSONObject("error");
//            alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: " + jsonObject.optString("razorpay_payment_id"));
//            alertDialogBuilder.setMessage("Payment Successful:\n "+jsonObject1.optString("description")+"\n"+"Reason :"+jsonObject1.optString("reason"));
            Log.e("on success ", "response" + paymentData.getData());
//            PaymentStatusparam.put("order_id", getorderid);
//            paymentcheck(PaymentStatusparam);
//            alertDialogBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try {

            Log.e("on failed ", "response" + paymentData.getData());



//            JSONObject jsonObject = new JSONObject(String.valueOf(paymentData.getData()));
//            JSONObject jsonObject1 = jsonObject.getJSONObject("error");
//            Log.e("on faile23 ", "response2" + jsonObject1);
//            Log.e("on faile23 ", "response2" + jsonObject1.optString("code"));
//            alertDialogBuilder.setMessage("Payment Failed:\n " + jsonObject1.optString("description") + "\n" + "Reason :" + jsonObject1.optString("reason"));
//            alertDialogBuilder.show();

//                            Toast.makeText(SeatViewActivity.this, paymentData.getData(), Toast.LENGTH_SHORT).show();
//            PaymentStatusparam = new HashMap<>();
//
//            PaymentStatusparam.put("order_id", getorderid);
//            paymentcheck(PaymentStatusparam);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //ordercreate
    public void ordergenrate(HashMap<String, String> Flightparamorder) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
//        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL_ORDER).create(RetrofitInterface.class);
        Call<HotelOrderResponseModel> call = retroFitInterface.flightorder(Flightparamorder);
        call.enqueue(new Callback<HotelOrderResponseModel>() {

            @Override
            public void onResponse(Call<HotelOrderResponseModel> call, Response<HotelOrderResponseModel> response) {
                Log.e("orderfor hotel", "onResponse: " + response.body());
                progressDialogDismiss();

                if (response.code() == 200) {
                    HotelOrderResponseModel hotelOrderResponseModel = response.body();
                    Log.e("orderfor get", "onResponse: " + hotelOrderResponseModel.getOrderId());
                    Log.e("recipt  no", "onResponse: " + hotelOrderResponseModel.getReceipt());
                    getorderid = hotelOrderResponseModel.getOrderId();
                    amount = hotelOrderResponseModel.getAmount();
                    Log.e("amount  no", "onResponse: " + amount);
                } else {
                    Toast.makeText(FlightPaymentActivity.this, "Response issue", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<HotelOrderResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("hotelgenearte", "onResponse: " + t.toString());

            }
        });
    }

    //callpaymentpapge
    public void startPayment() {
        final Activity activity = this;
        final Checkout co = new Checkout();
//        co.setKeyID("rzp_test_LW7laP0Vn8XIVS");
        co.setKeyID("rzp_test_i9KfAZHIaZkLA0");
//        int amount = Math.round(Float.parseFloat(samount) * 100);
//        int amount = Integer.parseInt(samount);
//        int amount = Integer.parseInt(samount);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Flight Booking");
            options.put("description", "Flight Booking Payment");
            options.put("order_id", getorderid);
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("theme.color", "#2196F3");
            options.put("amount", amount);
            Log.e("amountfinla", "11" + amount);
            Log.e("prderfinla", "11" + getorderid);
            JSONObject preFill = new JSONObject();
            preFill.put("email", getemail);
            preFill.put("contact", mobileNumber);
            options.put("prefill", preFill);
            co.open(activity, options);

        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

    }

    public void paymentcheck(HashMap<String, String> PaymentStatusparam) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<FlightPaymentStatusResponse> call = retroFitInterface.flightgetpayment(PaymentStatusparam);
        call.enqueue(new Callback<FlightPaymentStatusResponse>() {
            @Override
            public void onResponse(Call<FlightPaymentStatusResponse> call, Response<FlightPaymentStatusResponse> response) {
                Log.e("PAYMENT PaymentStatusparam ", "onResponse: " + PaymentStatusparam);
                Log.e("PAYMENT CHECK1 ", "onResponse: " + response.body());
                progressDialogDismiss();
                FlightPaymentStatusResponse flightPaymentStatusResponse = response.body();
                if (response.code() == 200) {
                    Log.e("PAYMENT 2 ", "onResponse: ");

//                    flightPaymentStatusResponse.getStatus().equals("captured")
                    if (flightPaymentStatusResponse.getErrorCode() == 0)
                    {
                        Log.e("getId 3", "onResponse: " + flightPaymentStatusResponse.getData().getId());
//                        Log.e("status1 3", "onResponse: "+flightPaymentStatusResponse.getStatus());
                        shared = getSharedPreferences("nict", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(flightPaymentStatusResponse); // myObject - instance of MyObject
                        editor.putString("Flightpaymenystatus", json);
                        editor.apply();
                        Intent intent = new Intent(FlightPaymentActivity.this, BookingConfirmShowActivity.class);
                        startActivity(intent);
                    } else if (flightPaymentStatusResponse.getErrorCode() == 500) {
                        Toast.makeText(FlightPaymentActivity.this, flightPaymentStatusResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    } else {

//                        Toast.makeText(FlightPaymentActivity.this, flightPaymentStatusResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(FlightPaymentActivity.this, DashboardActivity.class);
//                        startActivity(intent);
                    }


                } else {
                    Log.e("PAYMENT 9", "onResponse: ");

                    Toast.makeText(FlightPaymentActivity.this, "Response issue", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<FlightPaymentStatusResponse> call, Throwable t) {
                progressDialogDismiss();
                Log.e("hotelgenearte", "onResponse: " + t.toString());

            }
        });
    }
}











