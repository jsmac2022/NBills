package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nictbills.app.R;
import com.nictbills.app.activities.dawai4u.Dawai4UDashboardActivity;
import com.nictbills.app.activities.digilocker.DigiLockerActivity;
import com.nictbills.app.activities.health_id_abdm.ABHALandingPageActivity;
import com.nictbills.app.activities.health_id_abdm.HealthIdDashboardActivity;

public class NICTArogActivity extends AppCompatActivity {

    private ImageView backArrow_IMG;
    private LinearLayout digilocker_LinearLayout,book_covid_LinearLayout,download_certificate_LinearLayout,abha_health_id_LinearLayout,dawai4U_LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nictarog);

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        book_covid_LinearLayout = findViewById(R.id.book_covid_LinearLayout);
        download_certificate_LinearLayout = findViewById(R.id.download_certificate_LinearLayout);
        abha_health_id_LinearLayout = findViewById(R.id.abha_health_id_LinearLayout);
        dawai4U_LinearLayout = findViewById(R.id.dawai4U_LinearLayout);
        digilocker_LinearLayout = findViewById(R.id.digilocker_LinearLayout);

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NICTArogActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });

        book_covid_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NICTArogActivity.this,VaccineDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        download_certificate_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NICTArogActivity.this,VaccineDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dawai4U_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NICTArogActivity.this, Dawai4UDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        abha_health_id_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NICTArogActivity.this, ABHALandingPageActivity.class);
                startActivity(intent);
                finish();

            }
        });

       /* digilocker_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NICTArogActivity.this, DigiLockerActivity.class);
                startActivity(intent);
                finish();

            }
        });*/

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NICTArogActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}