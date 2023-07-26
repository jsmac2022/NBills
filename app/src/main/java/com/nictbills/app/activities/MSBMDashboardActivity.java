package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.nictbills.app.R;

import java.util.ArrayList;
import java.util.List;

public class MSBMDashboardActivity extends AppCompatActivity {
    private WebView course_webView;
    private String courseWebLink;
    private Activity activity ;
    private ImageView backArrow_IMG;
    private ProgressDialog progDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_s_b_m_dashboard);
        course_webView = findViewById(R.id.course_webView);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        activity = this;

        progDailog = ProgressDialog.show(activity, "Loading","Please wait...", true);
        progDailog.setCancelable(false);


        Intent intent = getIntent();
        courseWebLink = intent.getStringExtra("course_link");

        //course_webView.loadUrl(courseWebLink);

        course_webView.getSettings().setJavaScriptEnabled(true);
        course_webView.getSettings().setLoadWithOverviewMode(true);
        course_webView.getSettings().setUseWideViewPort(true);
        course_webView.setWebViewClient(new WebViewClient(){

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

        course_webView.loadUrl(courseWebLink);


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (course_webView != null && course_webView.canGoBack())
                    course_webView.goBack();// if there is previous page open it
                else {

                    Intent intent = new Intent(MSBMDashboardActivity.this, MSBM_Course_SelectActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }


    @Override
    public void onBackPressed() {

        if (course_webView != null && course_webView.canGoBack())
            course_webView.goBack();// if there is previous page open it
        else {

            Intent intent = new Intent(MSBMDashboardActivity.this, MSBM_Course_SelectActivity.class);
            startActivity(intent);
            finish();
        }

       /* if (course_webView.canGoBack()) {
            course_webView.goBack();

        } else {
            Intent intent = new Intent(MSBMDashboardActivity.this,MSBM_Course_SelectActivity.class);
            startActivity(intent);
            finish();
        }*/

    }
}