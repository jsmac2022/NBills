package com.nictbills.app.activities.tbo.hotel;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.databinding.ActivityHotelBookBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class HotelBookActivity extends BaseActivity {
    SharedPreferences shared;
    ActivityHotelBookBinding binding;
    String gettds ,getagentmarlup ,getagentcommision ,getservicetax ,getdiscount,getoffer_roundoffprice ,getpublish_roundoffprice ,getofferprice ,getpublishprice ,getother_charge ,getchiled_charge ,getguestextracharge ,gettrace_id,getCurrency_Code ,getRoom_price ,gettax , getcountry_code, getRoom_PlanName ,getRoom_Plancode ,getRoom_TypeCode ,getRoom_TypeName ,getagency_id, getcity_code, gethotel_index, gethotel_name, gethotel_code, gettokenhotel_id;
    HashMap<String, String> hotelBookingparam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_book);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        String json = shared.getString("HotelPaymentStatus", "");
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.e("bookign jsonObject 1", "hsow" + jsonObject);
            JSONObject jsonobj = jsonObject.getJSONObject("data");
            Log.e("bookign confirm 1", "hsow" + jsonobj);
            binding.tvbookingId.setText(jsonobj.optString("bookingId"));
            binding.tvrefnoId.setText(jsonobj.optString("bookingReferenceNo"));
            binding.tvorderId.setText(jsonobj.optString("orderId"));
            binding.tvcnf.setText(jsonobj.optString("confirmationNo"));
            binding.tvhotelcode.setText(jsonobj.optString("HotelCode"));
            binding.tvhotelname.setText(jsonobj.optString("HotelName"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        click();
    }
    public void click() {
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelBookActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HotelBookActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

}