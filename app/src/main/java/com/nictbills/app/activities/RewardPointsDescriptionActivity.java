package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nictbills.app.R;

public class RewardPointsDescriptionActivity extends AppCompatActivity {

    private String refno,amount,descr,service,service_id,ttype,edt;
    private TextView reward_status_TV,reward_amount_TV,transaction_Id_TV,description_Tv,date_Tv;
    private ImageView backArrow_IMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_points_description);

        reward_status_TV = findViewById(R.id.reward_status_TV);
        reward_amount_TV = findViewById(R.id.reward_amount_TV);
        transaction_Id_TV = findViewById(R.id.transaction_Id_TV);
        description_Tv = findViewById(R.id.description_Tv);
        date_Tv = findViewById(R.id.date_Tv);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        final Intent intent = getIntent();
        refno = intent.getStringExtra("refno");
        amount = intent.getStringExtra("amount");
        descr = intent.getStringExtra("descr");
        service = intent.getStringExtra("service");
        service_id = intent.getStringExtra("service_id");
        ttype = intent.getStringExtra("ttype");
        edt = intent.getStringExtra("edt");

        if (ttype.equalsIgnoreCase("RED")){
            reward_status_TV.setText(getString(R.string.paid));
            reward_status_TV.setTextColor(Color.parseColor("#FF0000"));
        } else {
            reward_status_TV.setText(getString(R.string.added));
            reward_status_TV.setTextColor(Color.parseColor("#228B22"));
        }

        reward_amount_TV.setText(amount+" "+getString(R.string.point));
        description_Tv.setText(descr);
        transaction_Id_TV.setText(getString(R.string.transaction_id)+" "+refno);
        date_Tv.setText(getString(R.string.on)+" "+edt);


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(RewardPointsDescriptionActivity.this,RewardPointsShowActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(RewardPointsDescriptionActivity.this,RewardPointsShowActivity.class);
        startActivity(intent1);
        finish();
    }
}