package com.nictbills.app.activities.digilocker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.MSBMDashboardActivity;
import com.nictbills.app.activities.MSBM_Course_SelectActivity;
import com.nictbills.app.activities.NICTArogActivity;

public class DigiLockerActivity extends AppCompatActivity {

    private WebView digiLocker;
    private Activity activity ;
    private ImageView backArrow_IMG;
    private ProgressDialog progDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_locker);

        digiLocker = findViewById(R.id.digiLocker);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        activity = this;
        progDailog = ProgressDialog.show(activity, "Loading","Please wait...", true);
        progDailog.setCancelable(false);

        digiLocker.getSettings().setJavaScriptEnabled(true);
        digiLocker.getSettings().setLoadWithOverviewMode(true);
        digiLocker.getSettings().setUseWideViewPort(true);
        digiLocker.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });

        digiLocker.loadUrl("https://nictbills.com/Digilocker");
       // digiLocker.loadUrl("http://192.168.5.105:8081/Digilocker/Test");


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (digiLocker != null && digiLocker.canGoBack())
                    digiLocker.goBack();// if there is previous page open it
                else {

                    Intent intent = new Intent(DigiLockerActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
        //.loadUrl("http://192.168.5.105:8081/Digilocker");


    @Override
    public void onBackPressed() {
        if (digiLocker != null && digiLocker.canGoBack())
            digiLocker.goBack();// if there is previous page open it
        else {

            Intent intent = new Intent(DigiLockerActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
