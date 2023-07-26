package com.nictbills.app.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.AppVersionDTO;
import com.nictbills.app.utils.Util;
import com.yariksoffice.lingver.Lingver;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView NavHeader_Mobile;

    private String mobileNumber,selectLanguage;
    private SharedPreferences sharedPreferences;
    private LinearLayout help_LinearLayout,Parentid_LinearLayout,privacyPolicy_LinearLayout,aboutUs_LinearLayout;
    private TextView app_version_TV;
    private RetrofitInterface retroFitInterface;
    private Dialog selectLanguageDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Parentid_LinearLayout = findViewById(R.id.Parentid_LinearLayout);
        privacyPolicy_LinearLayout = findViewById(R.id.privacyPolicy_LinearLayout);
        aboutUs_LinearLayout = findViewById(R.id.aboutUs_LinearLayout);
        app_version_TV = findViewById(R.id.app_version_TV);
        help_LinearLayout = findViewById(R.id.help_LinearLayout);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");

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

        getAppVersion();

        privacyPolicy_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AboutUsActivity.this,PrivacyPolicyActivity.class);
                startActivity(intent);
                finish();

            }
        });


        aboutUs_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AboutUsActivity.this,AboutActivity.class);
                startActivity(intent);
                finish();

            }
        });


        help_LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AboutUsActivity.this,HelpAndDemoActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Intent intent = new Intent(AboutUsActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
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

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

            Intent intent = new Intent(AboutUsActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid_LinearLayout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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


        } else if (id == R.id.nav_transaction_history) {

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

            Intent intent = new Intent(AboutUsActivity.this,TransactionHistoryActivity.class);
            startActivity(intent);
            finish();


            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid_LinearLayout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

            Intent intent = new Intent(AboutUsActivity.this,SupportActivity.class);
            startActivity(intent);
            finish();

                } else {


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(Parentid_LinearLayout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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


        } else if (id == R.id.nav_rewards) {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid_LinearLayout.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
                Intent intent = new Intent(AboutUsActivity.this,RewardPointsShowActivity.class);
                startActivity(intent);
                finish();

            } else{


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid_LinearLayout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

        }else if (id == R.id.nav_help_demo) {

                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        connected = true;

            Intent intent = new Intent(AboutUsActivity.this,HelpAndDemoActivity.class);
            startActivity(intent);
            finish();

                    } else {


                        connected = false;

                        Snackbar snackbar = Snackbar
                                .make(Parentid_LinearLayout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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



        }
        else if (id == R.id.nav_termsAndConditions) {

                        boolean connected = false;
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network
                            connected = true;

            Intent intent = new Intent(AboutUsActivity.this,TermsAndConditionsActivity.class);
            startActivity(intent);
            finish();

                        } else {


                            connected = false;

                            Snackbar snackbar = Snackbar
                                    .make(Parentid_LinearLayout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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


        }  else if (id == R.id.nav_logout) {

            final SweetAlertDialog sad = new SweetAlertDialog(AboutUsActivity.this, SweetAlertDialog.WARNING_TYPE);
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
                    Intent intent= new Intent(AboutUsActivity.this, LoginActivity.class);
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

        }else if (id == R.id.nav_languageSetting) {

        selectLanguagePonUpDialog();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getAppVersion() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(AboutUsActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<AppVersionDTO> call = retroFitInterface.getAppVersion();

        call.enqueue(new Callback<AppVersionDTO>() {
            @Override
            public void onResponse(Call<AppVersionDTO> call, Response<AppVersionDTO> response) {
                progressLog.dismiss();

                AppVersionDTO versionDTO = response.body();

                if (response.code() == 200) {

                    String DBAppVersion =versionDTO.getCurrentVersion();

                    app_version_TV.setText(DBAppVersion);

                } else if (response.code() == 500) {
                    Toast.makeText(AboutUsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AboutUsActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(AboutUsActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AppVersionDTO> call, Throwable t) {
                progressLog.dismiss();
                //Log.e("MSG",t.getMessage());

                Toast.makeText(AboutUsActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void selectLanguagePonUpDialog(){
        selectLanguageDialog = new Dialog(AboutUsActivity.this,R.style.Theme_Dialog);
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

            Intent i = new Intent(AboutUsActivity.this, DashboardActivity.class);
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }
}
