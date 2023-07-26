package com.nictbills.app.activities.tbo.flight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nictbills.app.R;

public class BookingReviewActivity extends AppCompatActivity {
    SharedPreferences shared ;
    TextView tvpusher ,addressshow ,uname ,gender ,countrynmae ,departdate ,arrdate ,flightno ,flightnmae ,origincode ,desticode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_review);
        tvpusher = findViewById(R.id.tvpusher);
        addressshow = findViewById(R.id.address);
        uname = findViewById(R.id.uname);
        gender = findViewById(R.id.gender);
        countrynmae = findViewById(R.id.countryname);
        flightno = findViewById(R.id.flightno);
        flightnmae = findViewById(R.id.flightname);
        origincode = findViewById(R.id.origincode);
        desticode = findViewById(R.id.destcode);
        departdate = findViewById(R.id.deptdate);
        arrdate = findViewById(R.id.arrdate);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        String getuname = shared.getString("name", "");
        String getmobile = shared.getString("mobileuser", "");
        String getpusher = shared.getString("pusher", "");
        String getgender = shared.getString("gender", "");
        String getaddress = shared.getString("Address", "");
        String getcity = shared.getString("City", "");
        String getcountry = shared.getString("Country", "");
        String getflihtno = shared.getString("flightno", "");
        String getorigincode = shared.getString("origincode", "");
        String getdesticode = shared.getString("destinationcode", "");
        String getflightname = shared.getString("flightname", "");
        String getflightdeptdatetime = shared.getString("fulldeptdatetime", "");
        String getflightarrdatetime = shared.getString("fullarrdatetime", "");
        departdate.setText(getflightdeptdatetime);
        arrdate.setText(getflightarrdatetime);
        flightnmae.setText(getflightname);
        origincode.setText(getorigincode);
        desticode.setText(getdesticode);
        flightno.setText(getflihtno);
        tvpusher.setText(getpusher);
        gender.setText(getgender);
        uname.setText(getuname);
        addressshow.setText(getaddress+","+getcity);
        countrynmae.setText(getcountry);

    }
}