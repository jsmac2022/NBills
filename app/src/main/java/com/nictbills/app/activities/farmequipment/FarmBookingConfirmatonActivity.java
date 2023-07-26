package com.nictbills.app.activities.farmequipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.databinding.ActivityFarmBookingConfirmatonBinding;

import org.json.JSONObject;

public class FarmBookingConfirmatonActivity extends AppCompatActivity {
ActivityFarmBookingConfirmatonBinding binding;
    SharedPreferences shared;
    String  getitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farm_booking_confirmaton);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_farm_booking_confirmaton);
        shared = getSharedPreferences("nict", MODE_PRIVATE);
        getitle = shared.getString("Title", "");

        String json = shared.getString("FarmBooking", "");
        Log.e("jat", "1" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            Log.e("getorder_no", "1" + jsonObject.getString("order_no"));
            binding.tvOrderid.setText(jsonObject.getString("order_no"));
            binding.tvBookingid.setText(jsonObject.getString("booking_id"));
            binding.tvPname.setText(getitle);
            binding.tvBookigdate.setText(jsonObject.getString("order_date"));
            binding.tvCmobile.setText(jsonObject.getString("mobile_no"));
        }
        catch (Exception e)
        {

        }
        click();
    }
    public  void click()
    {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FarmBookingConfirmatonActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FarmBookingConfirmatonActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}