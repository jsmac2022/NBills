package com.nictbills.app.activities.tbo.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.hotel.HotelBookActivity;
import com.nictbills.app.databinding.ActivityBusBookingConfirmationBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class BusBookingConfirmationActivity extends AppCompatActivity {

    ActivityBusBookingConfirmationBinding binding;

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bus_booking_confirmation);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_bus_booking_confirmation);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        String json = shared.getString("BusPaymentStatus", "");



        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.e("bookign jsonObject 1", "hsow" + jsonObject);

            JSONObject jsonobj = jsonObject.getJSONObject("data");
            Log.e("bookign confirm 1", "hsow" + jsonobj);
            binding.tvBusid.setText(jsonobj.optString("busId"));
//            binding.tvrefnoId.setText(jsonobj.optString("bookingReferenceNo"));
            binding.tvorderId.setText(jsonobj.optString("orderId"));
            binding.tvTicketno.setText(jsonobj.optString("ticketNo"));
            binding.tvInvoiceno.setText(jsonobj.optString("invoiceNumber"));
            binding.tvTavelpnr.setText(jsonobj.optString("travelOperatorPNR"));
            binding.tvBording.setText(jsonobj.optString("boardingName"));
            binding.tvDroping.setText(jsonobj.optString("droppingName"));
            binding.tvTravelname.setText(jsonobj.optString("travellerName"));

//            binding.tvcnf.setText(jsonobj.optString("confirmationNo"));
//            binding.tvhotelcode.setText(jsonobj.optString("HotelCode"));
//            binding.tvhotelname.setText(jsonobj.optString("HotelName"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        click();

    }

    public void click()
    {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(BusBookingConfirmationActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
            }
        });

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusBookingConfirmationActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusBookingConfirmationActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}