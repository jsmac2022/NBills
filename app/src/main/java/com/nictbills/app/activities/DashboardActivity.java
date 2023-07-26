package com.nictbills.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nictbills.app.Constant;
import com.nictbills.app.R;

import com.nictbills.app.activities.LabTest.LabListActivity;
import com.nictbills.app.activities.dawai4u.Dawai4UDashboardActivity;
import com.nictbills.app.activities.digilocker.DigiLockerActivity;
import com.nictbills.app.activities.farmequipment.FarmEquipmentDashboardActivity;
import com.nictbills.app.activities.fastag.MainActivity;
import com.nictbills.app.activities.health_id_abdm.ABHALandingPageActivity;
import com.nictbills.app.activities.household.activity.HomeActivity;
import com.nictbills.app.activities.lic.LicBillsActivity;
import com.nictbills.app.activities.paniwala.PaniwalaDashboardActivity;
import com.nictbills.app.activities.paysprint.mobile_recharge_paysprint.MobileRechargeDashboardPaySprintActivity;
import com.nictbills.app.activities.tbo.bus.BusDashBoardActivity;
import com.nictbills.app.activities.tbo.flight.FlightDashActivity;
import com.nictbills.app.activities.tbo.hotel.HotelDashBoardActivity;
import com.nictbills.app.adapter.BannerSliderAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.AppVersionDTO;
import com.nictbills.app.activities.health_id_abdm.dto.Get_otp_dto;
import com.nictbills.app.activities.health_id_abdm.dto.banner.Banner;
import com.nictbills.app.activities.health_id_abdm.dto.banner.Result;
import com.nictbills.app.utils.Util;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.yariksoffice.lingver.Lingver;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView NavHeader_Mobile;
    private LinearLayout hotel_booking_LL,train_tickets_LinearLayout,bus_tickets_LL,flight_ticket_LL,download_certificate_LinearLayout,book_covid_LinearLayout,dawai4U_LinearLayout,digilocker_LinearLayout,fastag_LL,abha_health_id_LinearLayout,paniwala_LL,profile_LL,house_hold_services_LL,linerLayout_credit_score,lic_LinearLayout,linerLayout_rewards_point,banner_area,dth_recharge_LinearLayout,mobile_recharge_LinearLayout,vaccine_finder_LinearLayout,msbm_LinearLayout,try_again_LinearLayout,something_went_wrong_LinearLayout,icon_id_LinearLayout,Parentid,Linear_layout_about,mppkvvcl_bill_LinearLayout,transaction_histroy_LinearLayout,linerLayout_terms_and_conditions,linerLayout_support ,linerLayout_farm_equipment ,linerLayout_bookedlabtest;
    private RetrofitInterface retroFitInterface;
    private String credential,mobileNumber,Appversion,selectLanguage,tok;
    private SharedPreferences sharedPreferences;
    private Button internet_try_again_Button;
    private Dialog selectLanguageDialog;
    private BannerSliderAdapter bannerSliderAdapter;
    private SliderView imageSlider;
    public  final String TOPIC_OTP_VERIFIED = "OTP_VERIFIED";
    public static final String TOPIC_INSTALLED = "INSTALLED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //checkPermission(permission);

        mppkvvcl_bill_LinearLayout = findViewById(R.id.mppkvvcl_bill_LinearLayout);
        transaction_histroy_LinearLayout = findViewById(R.id.transaction_histroy_LinearLayout);
        //linearLayout_Help = findViewById(R.id.linearLayout_Help);
        linerLayout_terms_and_conditions = findViewById(R.id.linerLayout_terms_and_conditions);
        linerLayout_support = findViewById(R.id.linerLayout_support);
        Linear_layout_about = findViewById(R.id.Linear_layout_about);
        Parentid = findViewById(R.id.Parentid);
        icon_id_LinearLayout = findViewById(R.id.icon_id_LinearLayout);
        something_went_wrong_LinearLayout = findViewById(R.id.something_went_wrong_LinearLayout);
        internet_try_again_Button = findViewById(R.id.internet_try_again_Button);
        try_again_LinearLayout = findViewById(R.id.try_again_LinearLayout);
        vaccine_finder_LinearLayout = findViewById(R.id.vaccine_finder_LinearLayout);
        msbm_LinearLayout = findViewById(R.id.msbm_LinearLayout);
        mobile_recharge_LinearLayout = findViewById(R.id.mobile_recharge_LinearLayout);
        dth_recharge_LinearLayout = findViewById(R.id.dth_recharge_LinearLayout);
        imageSlider = findViewById(R.id.imageSlider);
        banner_area = findViewById(R.id.banner_area);
        linerLayout_rewards_point = findViewById(R.id.linerLayout_rewards_point);
        linerLayout_farm_equipment = findViewById(R.id.linerLayout_farmequipment);
        lic_LinearLayout = findViewById(R.id.lic_LinearLayout);
        linerLayout_credit_score = findViewById(R.id.linerLayout_credit_score);
        house_hold_services_LL = findViewById(R.id.house_hold_services_LL);
        profile_LL = findViewById(R.id.profile_LL);
        paniwala_LL = findViewById(R.id.paniwala_LL);
        abha_health_id_LinearLayout = findViewById(R.id.abha_health_id_LinearLayout);
        fastag_LL = findViewById(R.id.fastag_LL);
        digilocker_LinearLayout = findViewById(R.id.digilocker_LinearLayout);
        dawai4U_LinearLayout = findViewById(R.id.dawai4U_LinearLayout);
        book_covid_LinearLayout = findViewById(R.id.book_covid_LinearLayout);
        linerLayout_bookedlabtest = findViewById(R.id.bloodtest);
        download_certificate_LinearLayout = findViewById(R.id.download_certificate_LinearLayout);
        flight_ticket_LL = findViewById(R.id.flight_ticket_LL);
        bus_tickets_LL = findViewById(R.id.bus_tickets_LL);
        train_tickets_LinearLayout = findViewById(R.id.train_tickets_LinearLayout);
        hotel_booking_LL = findViewById(R.id.hotel_booking_LL);

//        sharedPreferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
        Log.e("sa...","sam"+mobileNumber);


        sharedPreferences = getSharedPreferences("tok", Context.MODE_PRIVATE);
        tok = sharedPreferences.getString("fb_tok", "");



        try {
            PackageInfo pInfo = DashboardActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
            Appversion = pInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        
        FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC_INSTALLED).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.e("vinod", "TOPIC_INSTALLED unSubscribe success");
                } else if (task.isComplete()) {
                    Log.e("vinod", "TOPIC_INSTALLED unSubscribe Complete");

                } else if (task.isCanceled()) {
                    Log.e("vinod", "TOPIC_INSTALLED unSubscribe Canceled");
                } else {

                    Log.e("vinod", "TOPIC_INSTALLED unSubscribe failed");
                }
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_OTP_VERIFIED).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Log.e("vinod", "TOPIC_OTP_VERIFIED Subscribe success");
                  //  Toast.makeText(MainActivity.this, "TOPIC_OTP_VERIFIED Subscribe success", Toast.LENGTH_SHORT).show();
                } else if (task.isComplete()) {
                    Log.e("vinod", "TOPIC_OTP_VERIFIED Subscribe Complete");
                //    Toast.makeText(MainActivity.this, "TOPIC_OTP_VERIFIED Subscribe Complete", Toast.LENGTH_SHORT).show();

                } else if (task.isCanceled()) {
                    Log.e("vinod", "TOPIC_OTP_VERIFIED Subscribe Canceled");
                //    Toast.makeText(MainActivity.this, "TOPIC_OTP_VERIFIED Subscribe Canceled", Toast.LENGTH_SHORT).show();
                } else {

                    Log.e("vinod", "TOPIC_OTP_VERIFIED Subscribe failed");
                 //   Toast.makeText(MainActivity.this, "TOPIC_OTP_VERIFIED Subscribe failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;

            try_again_LinearLayout.setVisibility(View.GONE);
            icon_id_LinearLayout.setVisibility(View.VISIBLE);
        verifyAPIKey();
        getBanners();
        } else{
            icon_id_LinearLayout.setVisibility(View.GONE);
            try_again_LinearLayout.setVisibility(View.VISIBLE);
            connected = false;
            Snackbar snackbar = Snackbar
                    .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            snackbar.show();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);
        NavHeader_Mobile = (TextView) hView.findViewById(R.id.NavHeader_Mobile);
        NavHeader_Mobile.setText("+91"+mobileNumber);

        abha_health_id_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ABHALandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bus_tickets_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                {
                    //we are connected to a network
                    connected = true;
                    // Toast.makeText(DashboardActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DashboardActivity.this, BusDashBoardActivity.class);
//                    Intent intent = new Intent(DashboardActivity.this, BusPaymentActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(view, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });


        train_tickets_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DashboardActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DashboardActivity.this, AddUserList.class);
//                startActivity(intent);
//                finish();
            }
        });

        hotel_booking_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(DashboardActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                {
                    //we are connected to a network
                    connected = true;
                    // Toast.makeText(DashboardActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DashboardActivity.this, HotelDashBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(view, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }


            }
        });


        fastag_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        internet_try_again_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                    try_again_LinearLayout.setVisibility(View.GONE);
                    verifyAPIKey();

                } else{

                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });



        book_covid_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    Intent intent = new Intent(DashboardActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else{
                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        linerLayout_bookedlabtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    Intent intent = new Intent(DashboardActivity.this, LabListActivity.class);
                    startActivity(intent);
                    finish();

                } else{
                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        flight_ticket_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                {
                    //we are connected to a network
                    connected = true;
                   // Toast.makeText(DashboardActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DashboardActivity.this, FlightDashActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });


        download_certificate_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        dawai4U_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    Intent intent = new Intent(DashboardActivity.this, Dawai4UDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else{

                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);
// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        paniwala_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    Intent intent = new Intent(DashboardActivity.this, PaniwalaDashboardActivity.class);
                    intent.putExtra("FLAG","DASHBOARD");
                    startActivity(intent);
                    finish();
                } else{

                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });
        profile_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                    Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
                    intent.putExtra("FLAG","DASHBOARD");
                    startActivity(intent);
                    finish();
                } else{

                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);
// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        linerLayout_credit_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                  /*  Intent intent = new Intent(DashboardActivity.this, PanCard.class);
                    startActivity(intent);
                    finish();*/
                } else{
                    connected = false;
                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });


        house_hold_services_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                    Intent intent = new Intent(DashboardActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                 /*   Intent intent = new Intent(DashboardActivity.this, DigiLockerActivity.class);
                    startActivity(intent);
                    finish();*/


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        dth_recharge_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, DTHRechargeActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        lic_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, LicBillsActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        linerLayout_rewards_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this,RewardPointsShowActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });
        linerLayout_farm_equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, FarmEquipmentDashboardActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });


        mobile_recharge_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, MobileRechargeDashboardPaySprintActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        msbm_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, MSBM_Course_SelectActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });


       vaccine_finder_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, NICTArogActivity.class);
                  //  Intent intent = new Intent(DashboardActivity.this, AddNewCovidBeneficiaryActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

     /*    digilocker_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashboardActivity.this, DigiLockerActivity.class);
                startActivity(intent);
                finish();

            }
        });
       */

        mppkvvcl_bill_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    Intent intent = new Intent(DashboardActivity.this, AddIVRSActivity.class);
                    startActivity(intent);
                    finish();


                } else{


                        connected = false;

                        Snackbar snackbar = Snackbar
                                .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                                .setAction(getString(R.string.retry), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                });

// Changing message text color
                        snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                        View sbView = snackbar.getView();
                        snackbar.show();
                    }

            }
        });

        transaction_histroy_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                Intent intent = new Intent(DashboardActivity.this,TransactionHistoryActivity.class);
                startActivity(intent);
                finish();


                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

        digilocker_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;



                    Intent intent = new Intent(DashboardActivity.this, DigiLockerActivity.class);
                    startActivity(intent);
                    finish();

                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });


        linerLayout_terms_and_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                Intent intent = new Intent(DashboardActivity.this,TermsAndConditionsActivity.class);
                startActivity(intent);
                finish();

                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }
            }
        });

        linerLayout_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                Intent intent = new Intent(DashboardActivity.this,SupportActivity.class);
                startActivity(intent);
                finish();

                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }
            }
        });

        Linear_layout_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                    Intent intent = new Intent(DashboardActivity.this,AboutUsActivity.class);
                startActivity(intent);
                finish();

                } else{


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    //.setTitle("Really Exit?")
                    .setMessage(getString(R.string.are_you_sure_you_want_to_exit))
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            setResult(RESULT_OK, new Intent().putExtra(getString(R.string.exit), true));
                            finish();
                        }

                    }).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {


        } else if (id == R.id.nav_transaction_history) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

            Intent intent = new Intent(DashboardActivity.this,TransactionHistoryActivity.class);
            startActivity(intent);
            finish();

            } else{


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                View sbView = snackbar.getView();
                snackbar.show();
            }


        } else if (id == R.id.nav_support) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

            Intent intent = new Intent(DashboardActivity.this,SupportActivity.class);
            startActivity(intent);
            finish();

            } else{


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                View sbView = snackbar.getView();
                snackbar.show();
            }

        } else if (id == R.id.nav_help_demo) {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

            Intent intent = new Intent(DashboardActivity.this,HelpAndDemoActivity.class);
            startActivity(intent);
            finish();

            } else{


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                View sbView = snackbar.getView();
                snackbar.show();
            }

        } else if (id == R.id.nav_about) {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

            Intent intent = new Intent(DashboardActivity.this,AboutUsActivity.class);
            startActivity(intent);
            finish();

            } else{

                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
// Changing message text color
                snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                View sbView = snackbar.getView();
                snackbar.show();
            }

        }
        else if (id == R.id.nav_rewards)
        {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
            Intent intent = new Intent(DashboardActivity.this,RewardPointsShowActivity.class);
            startActivity(intent);
            finish();

            } else{

                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

// Changing message text color
                snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                View sbView = snackbar.getView();
                snackbar.show();
            }

        }
//        else if (id == R.id.nav_farm)
//        {
//
//
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);
//
//            boolean connected = false;
//            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//                //we are connected to a network
//                connected = true;
//                Intent intent = new Intent(DashboardActivity.this,RewardPointsShowActivity.class);
//                startActivity(intent);
//                finish();
//
//            } else{
//
//                connected = false;
//
//                Snackbar snackbar = Snackbar
//                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
//                        .setAction(getString(R.string.retry), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                            }
//                        });
//
//// Changing message text color
//                snackbar.setActionTextColor(Color.RED);
//
//// Changing action button text color
//                View sbView = snackbar.getView();
//                snackbar.show();
//            }
//
//        }


        else if (id == R.id.nav_termsAndConditions) {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
                Intent intent = new Intent(DashboardActivity.this,TermsAndConditionsActivity.class);
                startActivity(intent);
                finish();
            } else{
                connected = false;
                Snackbar snackbar = Snackbar
                        .make(Parentid, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
// Changing message text color
                snackbar.setActionTextColor(Color.RED);
// Changing action button text color
                View sbView = snackbar.getView();
                snackbar.show();
            }

        }

        else if (id == R.id.nav_logout) {

            final SweetAlertDialog sad = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.WARNING_TYPE);
            sad.setTitleText(getString(R.string.logout));
            sad.setContentText(getString(R.string.are_you_sure_you_want_to_logout));
            sad.setConfirmText(getString(R.string.exit_yes));
            sad.setCanceledOnTouchOutside(false);
            sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    SharedPreferences preferences =getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent= new Intent(DashboardActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            })
                    .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .show();



        } else if (id == R.id.nav_share) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.open_it_in_google_play_store));
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nictbills.app");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.nav_languageSetting) {

            selectLanguagePonUpDialog();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void verifyAPIKey() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<Get_otp_dto> call = retroFitInterface.verifyApiKey(credential);

        call.enqueue(new Callback<Get_otp_dto>() {
            @Override
            public void onResponse(Call<Get_otp_dto> call, Response<Get_otp_dto> response) {

                Get_otp_dto profileDTO = response.body();

                if (response.code() == 200) {

                    String otpStatus = profileDTO.getCode();

                    if (otpStatus.equalsIgnoreCase("OK")){
                       // getAppVersion();
                        something_went_wrong_LinearLayout.setVisibility(View.GONE);
                        icon_id_LinearLayout.setVisibility(View.VISIBLE);

                    }else if (otpStatus.equalsIgnoreCase("ERROR")){
                        progressDialogDismiss();
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                        sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                        sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                        sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                        sweetAlertDialog.setCanceledOnTouchOutside(false);
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                sDialog.dismissWithAnimation();

                            }
                        })
                                .show();

                    } else {
                        progressDialogDismiss();
                        something_went_wrong_LinearLayout.setVisibility(View.VISIBLE);
                        icon_id_LinearLayout.setVisibility(View.GONE);

//                        Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong)+1, Toast.LENGTH_SHORT).show();
                    }


                }else if (response.code() == 401){
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    something_went_wrong_LinearLayout.setVisibility(View.VISIBLE);
                    icon_id_LinearLayout.setVisibility(View.GONE);

                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 400) {
                    progressDialogDismiss();
                    something_went_wrong_LinearLayout.setVisibility(View.VISIBLE);
                    icon_id_LinearLayout.setVisibility(View.GONE);

//                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong)+2, Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<Get_otp_dto> call, Throwable t) {
                progressDialogDismiss();
                Log.e("MSG",t.getMessage());

//                Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later)+3, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getAppVersion() {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<AppVersionDTO> call = retroFitInterface.getAppVersion();

        call.enqueue(new Callback<AppVersionDTO>() {
            @Override
            public void onResponse(Call<AppVersionDTO> call, Response<AppVersionDTO> response) {

                AppVersionDTO versionDTO = response.body();

                if (response.code() == 200) {

                    String DBAppVersion =versionDTO.getCurrentVersion();

                    if (DBAppVersion.equalsIgnoreCase(Appversion)){
                        Get_otp_dto token = new Get_otp_dto();
                        token.setFbToken(tok);
                        fbToken(token);
                    }else {
                        progressDialogDismiss();
                        final SweetAlertDialog sad = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                        sad.setCustomImage(R.drawable.update);
                        sad.setTitleText(getString(R.string.update_available));
                        sad.setContentText(getString(R.string.new_update_is_available_on_google_play_store));
                        sad.setConfirmText(getString(R.string.update));
                        sad.setCancelable(false);
                        sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sad.dismissWithAnimation();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+"com.nictbills.app")));
                                finish();
                            }
                        }).show();

                    }

                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401){
                    progressDialogDismiss();

                    Toast.makeText(DashboardActivity.this, "this", Toast.LENGTH_SHORT).show();

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {
                    progressDialogDismiss();
                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AppVersionDTO> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void fbToken(Get_otp_dto token) {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<Get_otp_dto> call = retroFitInterface.fbToken(credential,token);

        call.enqueue(new Callback<Get_otp_dto>() {
            @Override
            public void onResponse(Call<Get_otp_dto> call, Response<Get_otp_dto> response) {
                progressDialogDismiss();

                if (response.code() == 200) {

                } else if (response.code() == 500) {
                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Get_otp_dto> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void selectLanguagePonUpDialog(){
        selectLanguageDialog = new Dialog(DashboardActivity.this,R.style.Theme_Dialog);
        selectLanguageDialog.setContentView(R.layout.select_language_popup);
        selectLanguageDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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

                //  setLocale(selectLanguage);
                updateLanguage(selectLanguage,true);

                selectLanguageDialog.dismiss();

            }
        });

        selectLanguageDialog.show();

    }



    private void updateLanguage(String language,boolean flag)

    {
        try {

            Lingver.init(getApplication(), "en");

        }catch (Exception e){

        }

        Lingver.getInstance().setLocale(Objects.requireNonNull(getApplicationContext()), new Locale(language));

       /* Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());*/

        SharedPreferences languagepref = getSharedPreferences("setLanguage",MODE_PRIVATE);
        SharedPreferences.Editor editor = languagepref.edit();
        editor.putString("languageToLoad",language);
        editor.apply();

        if (flag){

            Intent i = new Intent(DashboardActivity.this, DashboardActivity.class);
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


    }


    private void getBanners() {
        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BANNER_URL).create(RetrofitInterface.class);

        Call<Banner> call = retroFitInterface.getBanners("NICT_BILLS_APP");

        call.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                progressDialogDismiss();

                Banner banner = response.body();

                if (response.code() == 200) {
                    List<Result> resultList =banner.getResult();

                    if (resultList.size()==0){

                        banner_area.setVisibility(View.GONE);
                    } else {
                        banner_area.setVisibility(View.VISIBLE);
                        bannerSliderAdapter = new BannerSliderAdapter(DashboardActivity.this, resultList);
                        imageSlider.setSliderAdapter(bannerSliderAdapter);

                        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        imageSlider.setIndicatorSelectedColor(Color.WHITE);
                        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                        imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
                        imageSlider.startAutoCycle();
                    }


                } else if (response.code() == 500) {
                    banner_area.setVisibility(View.GONE);
//                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else if (response.code() == 404){
                    banner_area.setVisibility(View.GONE);
                } else {
                    banner_area.setVisibility(View.GONE);
//                    Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(DashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


}
