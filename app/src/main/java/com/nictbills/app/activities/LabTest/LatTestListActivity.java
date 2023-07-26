package com.nictbills.app.activities.LabTest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.bus.BusDashBoardActivity;
import com.nictbills.app.databinding.ActivityLatTestListBinding;

public class LatTestListActivity extends AppCompatActivity {
    ActivityLatTestListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lat_test_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lat_test_list);
        click();
    }

    public void click() {
        binding.backArrowIMG.setOnClickListener(v -> {
            onBackPressed();

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
