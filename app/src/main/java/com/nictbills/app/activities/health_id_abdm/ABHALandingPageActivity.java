package com.nictbills.app.activities.health_id_abdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.NICTArogActivity;

public class ABHALandingPageActivity extends AppCompatActivity {

    private Button create_abha_Button,login_abha_Button;
    private ImageView backArrow_IMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhalanding_page);


        create_abha_Button = findViewById(R.id.create_abha_Button);
        login_abha_Button = findViewById(R.id.login_abha_Button);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);


        create_abha_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ABHALandingPageActivity.this,HealthIdDashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ABHALandingPageActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();


            }
        });


        login_abha_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ABHALandingPageActivity.this, ABHALoginActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ABHALandingPageActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}