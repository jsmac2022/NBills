package com.nictbills.app.activities.farmequipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.flight.FlightDashActivity;
import com.nictbills.app.databinding.ActivityFarmEquipmentDashboardBinding;

public class FarmEquipmentDashboardActivity extends AppCompatActivity {

    ActivityFarmEquipmentDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farm_equipment_dashboard);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_equipment_dashboard);

        click();

    }

    public void click() {
        binding.layBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmEquipmentDashboardActivity.this, FarmEquipmentHistoryActivity.class);
                startActivity(intent);
            }
        });
        binding.layNewreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FarmEquipmentDashboardActivity.this, FarmEquipmentListActivity.class);
                startActivity(intent);

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
    public void onBackPressed() {
        Intent intent = new Intent(FarmEquipmentDashboardActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}