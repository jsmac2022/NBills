package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.nictbills.app.R;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private ImageView backArrow_IMG;
    private WebView privacy_policy_Webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        backArrow_IMG= findViewById(R.id.backArrow_IMG);
        privacy_policy_Webview= findViewById(R.id.privacy_policy_Webview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PrivacyPolicyActivity.this,AboutUsActivity.class);
                startActivity(intent);
                finish();

            }
        });


        privacy_policy_Webview.loadUrl("https://www.nictbills.com/mobile_pages/privacy_policy.html");

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(PrivacyPolicyActivity.this,AboutUsActivity.class);
        startActivity(intent);
        finish();

    }


}
