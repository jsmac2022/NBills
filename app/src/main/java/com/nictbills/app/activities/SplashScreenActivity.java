package com.nictbills.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nictbills.app.R;
import com.yariksoffice.lingver.Lingver;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SplashScreenActivity extends BaseActivity {
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    public static final String TOPIC_INSTALLED = "INSTALLED";


    private ArrayList<String> requestpermiosn;
    private String[] permission = new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Dialog selectLanguageDialog;
    private String selectLanguage;
    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (isInternetAvailable()){
                    String token = task.getResult();
                    //tv_token.setText(token);
                    Log.e("TAG", "token: "+token);
                    SharedPreferences fbTok = getSharedPreferences("tok",MODE_PRIVATE);
                    SharedPreferences.Editor editor = fbTok.edit();
                    editor.putString("fb_tok",token);
                    editor.apply();
                }

            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_INSTALLED).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.e("vinod", "TOPIC_INSTALLED Subscribe success");
                } else if (task.isComplete()) {
                    Log.e("vinod", "TOPIC_INSTALLED Subscribe Complete");

                } else if (task.isCanceled()) {
                    Log.e("vinod", "TOPIC_INSTALLED Subscribe Canceled");
                } else {

                    Log.e("vinod", "TOPIC_INSTALLED Subscribe failed");
                }
            }
        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                checkPermission(permission);

            }
        }, 500)
        ;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }


    public void checkPermission(String[] permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             requestpermiosn = new ArrayList<>();
            for (String s : permissions) {

                requestpermiosn.add(s);
            }
            if (!requestpermiosn.isEmpty()) {
                String[] stockArr = new String[requestpermiosn.size()];
                stockArr = requestpermiosn.toArray(stockArr);
                requestPermissions(stockArr, MY_CAMERA_REQUEST_CODE);

            } else {

                loadLocale();

            }
        } else {

            loadLocale();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE:
                boolean flag = true;

                for (int i : grantResults) {
                    if (i == PackageManager.PERMISSION_DENIED) {
                        flag = false;
                    }
                }

                if (flag) {

                    loadLocale();

                } else {
                    cameraPermission();
                    //checkPermission(permission);
                }
                break;
        }
    }



    private void cameraPermission() {
        final Dialog permissionDialog;
        permissionDialog = new Dialog(SplashScreenActivity.this, R.style.Theme_Dialog);
        permissionDialog.setContentView(R.layout.permissions_give_pop_up);
        permissionDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        permissionDialog.setCancelable(false);

        Button enable_permission_button = permissionDialog.findViewById(R.id.enable_permission_button);


        enable_permission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionDialog.dismiss();
                checkPermission(permission);
            }
        });

        permissionDialog.show();
    }

    private void selectLanguagePonUpDialog(){
        selectLanguageDialog = new Dialog(SplashScreenActivity.this,R.style.Theme_Dialog);
        selectLanguageDialog.setContentView(R.layout.select_language_popup);
        selectLanguageDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        selectLanguageDialog.setCancelable(false);
        RadioGroup radioLanguage = selectLanguageDialog.findViewById(R.id.radioLanguage);
        Button submit_btn = selectLanguageDialog.findViewById(R.id.submit_btn);
        selectLanguage = "en";

        radioLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioEnglish:

                        selectLanguage = "en";

                        break;

                    case R.id.radioHindi:

                        selectLanguage = "hi";

                        break;
                }

            }
        });


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //     setLocale(selectLanguage);
                updateLanguage(selectLanguage);

                selectLanguageDialog.dismiss();

            }
        });

        selectLanguageDialog.show();

    }


    private void updateLanguage(String language)
    {

        try {
            Lingver.init(getApplication(), "en");
        }catch (Exception e){

        }

        Lingver.getInstance().setLocale(Objects.requireNonNull(getApplicationContext()), new Locale(language));

        /*Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());*/

        SharedPreferences languagepref = getSharedPreferences("setLanguage",MODE_PRIVATE);
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("languageToLoad",language);
        editor.apply();

        Intent i = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }


    public void loadLocale() {

        SharedPreferences languagepref = getSharedPreferences("setLanguage",MODE_PRIVATE);
        selectLanguage= languagepref.getString("languageToLoad","");

        if (selectLanguage.equals("")){

            selectLanguagePonUpDialog();

        }else {

            Intent i = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
            startActivity(i);
            finish();

        }
    }



}
