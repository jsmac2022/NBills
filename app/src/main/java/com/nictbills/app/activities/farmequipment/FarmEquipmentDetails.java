package com.nictbills.app.activities.farmequipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nictbills.app.R;

import org.w3c.dom.Text;

public class FarmEquipmentDetails extends AppCompatActivity {
//ActivityFarmEquipmentDetailsBinding binding;

    SharedPreferences shared;
    String getimg ,getitle,getdesc ,getamount;
    TextView tvname ,tvdesc ,tv_rentamount;
    ImageView imgback ,imgequipment;
    Button btncontinue;
    String[] timelist = {"09 to 11 AM", "11 to 01 PM", "01 to 03 PM", "03 to 05 PM", "09 AM to 02 PM", "02 PM to 05 PM" ,"Other Timing"};
    Spinner spinnertime;
    String timedata;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_equipment_details);
        imgback=(ImageView)findViewById(R.id.backArrow_IMG);
        imgequipment=(ImageView)findViewById(R.id.img_eqpdtl);
        tvname=(TextView)findViewById(R.id.tv_farmname);
        tvdesc=(TextView)findViewById(R.id.tv_farmdesc);
        tv_rentamount=(TextView)findViewById(R.id.tv_rentamount);
        btncontinue=(Button)findViewById(R.id.btn_continue);
        spinnertime=(Spinner)findViewById(R.id.spinner_time);
//        binding= DataBindingUtil.setContentView(this,R.layout.activity_farm_equipment_details);
        shared = getSharedPreferences("nict", MODE_PRIVATE);
        getitle = shared.getString("Title", "");
        getdesc = shared.getString("Description", "");
        getamount = shared.getString("amount", "");
        getimg = shared.getString("img", "");
        tvdesc.setText(getdesc);
        tvname.setText(getitle);
        tv_rentamount.setText("Rs."+getamount);

        Glide.with(this)
                .load(getimg)
                .placeholder(R.drawable.hotel)
                .into(imgequipment);

        click();

    }
    public void click() {

        ArrayAdapter spinnertimeadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timelist);
        spinnertimeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertime.setAdapter(spinnertimeadapter);
        spinnertime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timedata = timelist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shared = getSharedPreferences("nict", MODE_PRIVATE);

                SharedPreferences.Editor editor = shared.edit();
                editor.putString("Timeselect", timedata);
                editor.apply();
                Intent intent = new Intent(FarmEquipmentDetails.this, FarmUserInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}