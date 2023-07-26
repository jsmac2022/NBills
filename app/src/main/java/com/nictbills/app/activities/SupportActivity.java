package com.nictbills.app.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.R;
import com.nictbills.app.utils.Util;
import com.yariksoffice.lingver.Lingver;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Locale;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SupportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView NavHeader_Mobile;
    private Button byMail_CV,byPhone_CV;
    private String mobileNumber,selectLanguage;
    private SharedPreferences sharedPreferences;
    private Dialog selectLanguageDialog;
    private LinearLayout Parentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        byMail_CV = findViewById(R.id.byMail_CV);
        byPhone_CV = findViewById(R.id.byPhone_CV);
        Parentid = findViewById(R.id.Parentid);


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



        byMail_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    String[] recipients={"supportnb@nictgroup.in"};
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Regarding");
               /* emailIntent.putExtra(Intent.EXTRA_TEXT,"Respected Sir/Ma'am,"+
                        "\n\n\n\n\n\n\n\n\n Regards," +
                        "\n"+EmployeeName+
                        "\nNICT code -"+code +
                        "\nMobile No. - "+EmployeeMobile);*/
                    emailIntent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(emailIntent, "Send mail"));

                } else {
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

        byPhone_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;



                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:9981755005"));
                    startActivity(intent);


                } else {
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
           Intent intent = new Intent(SupportActivity.this,DashboardActivity.class);
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

            Intent intent = new Intent(SupportActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_transaction_history) {

            Intent intent = new Intent(SupportActivity.this,TransactionHistoryActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_rewards) {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
                Intent intent = new Intent(SupportActivity.this,RewardPointsShowActivity.class);
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

        }else if (id == R.id.nav_support) {



        } else if (id == R.id.nav_help_demo) {

            Intent intent = new Intent(SupportActivity.this,HelpAndDemoActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(SupportActivity.this,AboutUsActivity.class);
            startActivity(intent);
            finish();

        }
        else if (id == R.id.nav_termsAndConditions) {

            Intent intent = new Intent(SupportActivity.this,TermsAndConditionsActivity.class);
            startActivity(intent);
            finish();

        }  else if (id == R.id.nav_logout) {

            final SweetAlertDialog sad = new SweetAlertDialog(SupportActivity.this, SweetAlertDialog.WARNING_TYPE);
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
                    Intent intent= new Intent(SupportActivity.this, LoginActivity.class);
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


    private void selectLanguagePonUpDialog(){
        selectLanguageDialog = new Dialog(SupportActivity.this,R.style.Theme_Dialog);
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

            Intent i = new Intent(SupportActivity.this, DashboardActivity.class);
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }
}
