package com.nictbills.app.activities.tbo.flight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.hotel.HotelBookActivity;
import com.nictbills.app.databinding.ActivityBookingConfirmShowBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingConfirmShowActivity extends AppCompatActivity {
    ActivityBookingConfirmShowBinding binding;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_booking_confirm_show);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_confirm_show);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);

        String json = shared.getString("Flightpaymenystatus", "");
        Log.e("bookign confirm1 ", "hsow" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.e("bookign jsonObject 1", "hsow" + jsonObject);

            JSONObject jsonobj = jsonObject.getJSONObject("data");
            Log.e("bookign confirm 1", "hsow" + jsonobj);

            binding.tvbookingId.setText(jsonobj.optString("bookingId"));
            binding.tvpnrId.setText(jsonobj.optString("pnr"));
            binding.tvflightnoId.setText(jsonobj.optString("flight_number"));
            binding.tvairlinecodeId.setText(jsonobj.optString("airline_code"));
            binding.tvorderId.setText(jsonobj.optString("orderId"));
            binding.tvprice.setText("RS."+jsonobj.optString("PublishedFare"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingConfirmShowActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingConfirmShowActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BookingConfirmShowActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}