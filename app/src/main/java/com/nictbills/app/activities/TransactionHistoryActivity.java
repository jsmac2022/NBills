package com.nictbills.app.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import com.nictbills.app.adapter.TransactionStatusAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.transaction_history.TxnDetails;
import com.nictbills.app.activities.health_id_abdm.dto.transaction_history.UserTxnList;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;
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

public class TransactionHistoryActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickRecyclerViewInterface {

    private TextView NavHeader_Mobile;
    private RecyclerView transaction_status_recyclerView;
    private String mobileNumber, credential, selectLanguage;
    private SharedPreferences sharedPreferences;
    private LinearLayout Parentid,transaction_history_Linear,noTransaction_LinearLayout;
    private RetrofitInterface retroFitInterface;
    private List<UserTxnList> txnLists;
    private RecyclerView.LayoutManager layoutManager;
    private Dialog selectLanguageDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        transaction_status_recyclerView = findViewById(R.id.transaction_status_recyclerView);
        Parentid = findViewById(R.id.Parentid);
        transaction_history_Linear = findViewById(R.id.transaction_history_Linear);
        noTransaction_LinearLayout = findViewById(R.id.noTransaction_LinearLayout);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        credential = sharedPreferences.getString("cred_1", "");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View hView = navigationView.getHeaderView(0);
        NavHeader_Mobile = (TextView) hView.findViewById(R.id.NavHeader_Mobile);
        NavHeader_Mobile.setText("+91" + mobileNumber);

        getTransactionList();

        transaction_status_recyclerView.setHasFixedSize(true);

        //   layoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);

        layoutManager = new LinearLayoutManager(TransactionHistoryActivity.this);
        transaction_status_recyclerView.setLayoutManager(layoutManager);
        transaction_status_recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            Intent intent = new Intent(TransactionHistoryActivity.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

                Intent intent = new Intent(TransactionHistoryActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            } else {

                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
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


        }  else if (id == R.id.nav_rewards) {


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Parentid.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
                Intent intent = new Intent(TransactionHistoryActivity.this,RewardPointsShowActivity.class);
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

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

                Intent intent = new Intent(TransactionHistoryActivity.this, SupportActivity.class);
                startActivity(intent);
                finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
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

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

                Intent intent = new Intent(TransactionHistoryActivity.this, HelpAndDemoActivity.class);
                startActivity(intent);
                finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
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

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

                Intent intent = new Intent(TransactionHistoryActivity.this, AboutUsActivity.class);
                startActivity(intent);
                finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
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

        } else if (id == R.id.nav_termsAndConditions) {

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;

                Intent intent = new Intent(TransactionHistoryActivity.this, TermsAndConditionsActivity.class);
                startActivity(intent);
                finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(Parentid, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
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

        } else if (id == R.id.nav_logout) {

            final SweetAlertDialog sad = new SweetAlertDialog(TransactionHistoryActivity.this, SweetAlertDialog.WARNING_TYPE);
            sad.setTitleText(getResources().getString(R.string.logout));
            sad.setContentText(getResources().getString(R.string.are_you_sure_you_want_to_logout));
            sad.setConfirmText(getResources().getString(R.string.exit_yes));
            sad.setCanceledOnTouchOutside(false);
            sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                    SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent intent = new Intent(TransactionHistoryActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            })
                    .setCancelButton(getResources().getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .show();


        } else if (id == R.id.nav_share) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.open_it_in_google_play_store));
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nictbills.app");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

            // Toast.makeText(this, "Home5", Toast.LENGTH_SHORT).show();*/
        } else if (id == R.id.nav_languageSetting) {
            selectLanguagePonUpDialog();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void setAdapter() {
        TransactionStatusAdapter adapter = new TransactionStatusAdapter(TransactionHistoryActivity.this, txnLists, this);
        transaction_status_recyclerView.setAdapter(adapter);
    }

    private void getTransactionList() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<TxnDetails> call = retroFitInterface.getTransactionDetails(credential);

        call.enqueue(new Callback<TxnDetails>() {
            @Override
            public void onResponse(Call<TxnDetails> call, Response<TxnDetails> response) {
                progressDialogDismiss();

                TxnDetails statusList = response.body();

                if (response.code() == 200) {

                    if ((statusList.getUserTxnList().size()==0)){

                        noTransaction_LinearLayout.setVisibility(View.VISIBLE);
                        transaction_history_Linear.setVisibility(View.GONE);

                    } else if (statusList.getUserTxnList()==null){

                        noTransaction_LinearLayout.setVisibility(View.VISIBLE);
                        transaction_history_Linear.setVisibility(View.GONE);

                    }else {

                        noTransaction_LinearLayout.setVisibility(View.GONE);
                        transaction_history_Linear.setVisibility(View.VISIBLE);

                        txnLists = statusList.getUserTxnList();
                        setAdapter();

                    }


                  /*  if (String.valueOf(statusList.getUserTxnList()).length()<=2){

                        Toast.makeText(TransactionHistoryActivity.this, "", Toast.LENGTH_SHORT).show();

                    } else if (statusList.getUserTxnList()==null){

                      *//*  noTransaction_LinearLayout.setVisibility(View.VISIBLE);
                        trasaction_history_show_LinearLayout.setVisibility(View.GONE);*//*

                    } else {*/

                      /*  noTransaction_LinearLayout.setVisibility(View.GONE);
                        trasaction_history_show_LinearLayout.setVisibility(View.VISIBLE);
*/




                } else if (response.code() == 500) {

                    Toast.makeText(TransactionHistoryActivity.this, getResources().getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(TransactionHistoryActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getResources().getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getResources().getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(TransactionHistoryActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else {

                    Toast.makeText(TransactionHistoryActivity.this, getResources().getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TxnDetails> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(TransactionHistoryActivity.this, getResources().getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onListItem(View view, int adapterPosition) {


        String fee, amount, pay_id, order_id, currency, status, method, notes_service, notes_service_id, error_description, receipt,created_at,notes_service_loc;

        amount = txnLists.get(adapterPosition).getAmount();
        pay_id = txnLists.get(adapterPosition).getPayId();
        order_id = txnLists.get(adapterPosition).getOrderId();
        currency = txnLists.get(adapterPosition).getCurrency();
        status = txnLists.get(adapterPosition).getStatus();
        method = txnLists.get(adapterPosition).getMethod();
        notes_service = txnLists.get(adapterPosition).getNotesService();
        notes_service_id = txnLists.get(adapterPosition).getNotesServiceId();
        notes_service_loc = (String)txnLists.get(adapterPosition).getNotesServiceLoc();
        error_description = txnLists.get(adapterPosition).getErrorDescription();
        receipt = txnLists.get(adapterPosition).getReceipt();
        created_at = txnLists.get(adapterPosition).getCreatedAt()+"";
        fee = txnLists.get(adapterPosition).getFee();


        switch (view.getId()) {

            case R.id.transaction_details_cardView:


                Intent intent = new Intent(TransactionHistoryActivity.this,TransactionDescriptionsActivity.class);
                intent.putExtra("amount",amount);
                intent.putExtra("pay_id",pay_id);
                intent.putExtra("order_id",order_id);
                intent.putExtra("currency",currency);
                intent.putExtra("status",status);
                intent.putExtra("method",method);
                intent.putExtra("notes_service",notes_service);
                intent.putExtra("notes_service_id",notes_service_id);
                intent.putExtra("notes_service_loc",notes_service_loc);
                intent.putExtra("error_description",error_description);
                intent.putExtra("receipt",receipt);
                intent.putExtra("created_at",created_at);
                intent.putExtra("fee",fee);
                startActivity(intent);

                break;


        }
    }


    private void selectLanguagePonUpDialog(){
        selectLanguageDialog = new Dialog(TransactionHistoryActivity.this,R.style.Theme_Dialog);
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

            Intent i = new Intent(TransactionHistoryActivity.this, DashboardActivity.class);
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


    }


}
