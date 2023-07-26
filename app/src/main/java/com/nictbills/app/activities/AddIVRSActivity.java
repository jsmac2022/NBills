package com.nictbills.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.IVRSListAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.AddIVRSDto;
import com.nictbills.app.activities.health_id_abdm.dto.CheckDuplicateBillDto;
import com.nictbills.app.activities.health_id_abdm.dto.IVRSListDto;
import com.nictbills.app.activities.health_id_abdm.dto.IvrsNoList;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;
import com.yariksoffice.lingver.Lingver;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddIVRSActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickRecyclerViewInterface {
    private TextView NavHeader_Mobile,find_ivrs_number_TV;
    private RetrofitInterface retroFitInterface;
    private String selectLanguage,IVRS_NUMBER,enc_data,credential, mobileNumber, ivrsNumber ,ivrsNo,loc_cd,gr_no,rd_no,cons_name,cons_add,circle,net_bill,net_incl_surchage,due_date;
    private Button add_IVRS_Button;
    private EditText ivrs_EditText;
    private SharedPreferences sharedPreferences;
    private LinearLayout add_ivrs_LinearLayout, added_ivrs_LinearLayout,parent_linear_layout,btnScanBarcode;
    private RecyclerView added_ivrs_recycler_view;
    private RecyclerView.LayoutManager layoutManager;
    private List<IvrsNoList> ivrsList;
    private FloatingActionButton add_IVR_floatingButton;
    private FrameLayout scan_View_FrameLayout;
    private CodeScanner mCodeScanner;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private String[] permission = new String[]{Manifest.permission.CAMERA};
    private Dialog selectLanguageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_i_v_r_s);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
        getAddedIVRSList();

        final CodeScannerView scannerView = findViewById(R.id.scanner_view);
        add_IVR_floatingButton = findViewById(R.id.add_IVR_floatingButton);
        add_IVRS_Button = findViewById(R.id.add_IVRS_Button);
        ivrs_EditText = findViewById(R.id.ivrs_EditText);
        add_ivrs_LinearLayout = findViewById(R.id.add_ivrs_LinearLayout);
        added_ivrs_LinearLayout = findViewById(R.id.added_ivrs_LinearLayout);
        added_ivrs_recycler_view = findViewById(R.id.added_ivrs_recycler_view);
        parent_linear_layout = findViewById(R.id.parent_linear_layout);
        find_ivrs_number_TV = findViewById(R.id.find_ivrs_number_TV);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        scan_View_FrameLayout = findViewById(R.id.scan_View_FrameLayout);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        final View hView = navigationView.getHeaderView(0);

        NavHeader_Mobile = (TextView) hView.findViewById(R.id.NavHeader_Mobile);
        NavHeader_Mobile.setText("+91" + mobileNumber);


        find_ivrs_number_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findIVRSNumber();

            }
        });
        

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                AddIVRSActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (result.getText().length()>0){

                            scan_View_FrameLayout.setVisibility(View.GONE);

                            IVRS_NUMBER = "N"+result.getText();
                            AddIVRSDto ivrsDto = new AddIVRSDto();
                            ivrsDto.setIvrsNo("N"+result.getText());
                            scanAndPayIVRSQR(ivrsDto);


                        }else {

                            scan_View_FrameLayout.setVisibility(View.VISIBLE);
                        }

                      //  Toast.makeText(AddIVRSActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(parent_linear_layout.getWindowToken(), 0);

                checkPermission(permission);

            }
        });


        add_IVR_floatingButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
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


                    ivrs_EditText.setText("");

                    Intent intent = new Intent(AddIVRSActivity.this,AddMoreIVRSActivity.class);
                    startActivity(intent);
                    finish();

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

        add_IVRS_Button.setOnClickListener(new View.OnClickListener() {
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

                ivrsNumber = ivrs_EditText.getText().toString();



                    if (ivrsNumber.isEmpty() || ivrsNumber.length() < 0 || ivrsNumber.equals("")) {

                        Snackbar.make(v, getString(R.string.please_enter_IVRS_number), Snackbar.LENGTH_SHORT)
                                .setAction(getString(R.string.action), null).show();

                    } else {

                        AddIVRSDto ivrsDto = new AddIVRSDto();
                        ivrsDto.setIvrsNo(ivrsNumber);
                        addIVRS(ivrsDto);
                    }

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


        added_ivrs_recycler_view.setHasFixedSize(true);

        //   layoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);

        layoutManager = new LinearLayoutManager(AddIVRSActivity.this);
        added_ivrs_recycler_view.setLayoutManager(layoutManager);
        added_ivrs_recycler_view.setItemAnimator(new DefaultItemAnimator());

    }

    private void setAdapter() {
        IVRSListAdapter adapter = new IVRSListAdapter(AddIVRSActivity.this, ivrsList, this);
        added_ivrs_recycler_view.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }else if (scan_View_FrameLayout.getVisibility()==View.VISIBLE){

            mCodeScanner.stopPreview();
            getAddedIVRSList();

        }else {
            Intent intent = new Intent(AddIVRSActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
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

            Intent intent = new Intent(AddIVRSActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

            Intent intent = new Intent(AddIVRSActivity.this,TransactionHistoryActivity.class);
            startActivity(intent);
            finish();

            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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
            imm.hideSoftInputFromWindow(parent_linear_layout.getWindowToken(), 0);

            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
                Intent intent = new Intent(AddIVRSActivity.this,RewardPointsShowActivity.class);
                startActivity(intent);
                finish();

            } else{


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

            Intent intent = new Intent(AddIVRSActivity.this,SupportActivity.class);
            startActivity(intent);
            finish();


            } else {


                connected = false;

                Snackbar snackbar = Snackbar
                        .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


            Intent intent = new Intent(AddIVRSActivity.this,HelpAndDemoActivity.class);
            startActivity(intent);
            finish();

                } else {


                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        //we are connected to a network
                        connected = true;

            Intent intent = new Intent(AddIVRSActivity.this,AboutUsActivity.class);
            startActivity(intent);
            finish();

                    } else {


                        connected = false;

                        Snackbar snackbar = Snackbar
                                .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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


        } else if (id == R.id.nav_termsAndConditions) {

                        boolean connected = false;
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network
                            connected = true;

            Intent intent = new Intent(AddIVRSActivity.this,TermsAndConditionsActivity.class);
            startActivity(intent);
            finish();

                        } else {


                            connected = false;

                            Snackbar snackbar = Snackbar
                                    .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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

            final SweetAlertDialog sad = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.WARNING_TYPE);
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
                    Intent intent= new Intent(AddIVRSActivity.this, LoginActivity.class);
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



    private void addIVRS(AddIVRSDto ivrsDto) {

       progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<AddIVRSDto> call = retroFitInterface.addIVRS(credential, ivrsDto);

        call.enqueue(new Callback<AddIVRSDto>() {
            @Override
            public void onResponse(Call<AddIVRSDto> call, Response<AddIVRSDto> response) {
               progressDialogDismiss();

                AddIVRSDto profileDTO = response.body();

                if (response.code() == 200) {

                    String otpStatus = profileDTO.getCode();

                    if (otpStatus.equalsIgnoreCase("OK")) {

                        ivrs_added_successful_PonUpDialog();
                        add_ivrs_LinearLayout.setVisibility(View.GONE);
                        ivrs_EditText.setText("");

                    } else if (otpStatus.equalsIgnoreCase("ERROR")) {

                        Snackbar.make(parent_linear_layout, profileDTO.getMessage(), Snackbar.LENGTH_SHORT)
                                .setAction(getString(R.string.action), null).show();
                       // Toast.makeText(AddIVRSActivity.this, profileDTO.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<AddIVRSDto> call, Throwable t) {
               progressDialogDismiss();
                Log.e("MSG", t.getMessage());
                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getAddedIVRSList() {
       progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<IVRSListDto> call = retroFitInterface.getIVRSNumber(credential);

        call.enqueue(new Callback<IVRSListDto>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<IVRSListDto> call, Response<IVRSListDto> response) {
               progressDialogDismiss();

                IVRSListDto listDto = response.body();

                if (response.code() == 200) {

                   /* Log.e("aaaaa", String.valueOf(listDto.getIvrsNoList().size()));*/
                    if (listDto.getIvrsNoList().size()<=0 ){

                        add_ivrs_LinearLayout.setVisibility(View.VISIBLE);
                        added_ivrs_LinearLayout.setVisibility(View.GONE);
                        add_IVR_floatingButton.setVisibility(View.GONE);
                        scan_View_FrameLayout.setVisibility(View.GONE);

                    } else {
                        scan_View_FrameLayout.setVisibility(View.GONE);
                        add_ivrs_LinearLayout.setVisibility(View.GONE);
                        added_ivrs_LinearLayout.setVisibility(View.VISIBLE);
                        add_IVR_floatingButton.setVisibility(View.VISIBLE);
                        ivrsList = listDto.getIvrsNoList();
                        setAdapter();

                    }



                } else if (response.code() == 500) {
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<IVRSListDto> call, Throwable t) {
               progressDialogDismiss();
                //Log.e("MSG",t.getMessage());

                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void deleteIVRS(AddIVRSDto ivrsDto) {

       progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<AddIVRSDto> call = retroFitInterface.deleteIVRS(credential, ivrsDto);

        call.enqueue(new Callback<AddIVRSDto>() {
            @Override
            public void onResponse(Call<AddIVRSDto> call, Response<AddIVRSDto> response) {

                progressDialogDismiss();

                AddIVRSDto profileDTO = response.body();

                if (response.code() == 200) {

                    String otpStatus = profileDTO.getCode();

                    if (otpStatus.equalsIgnoreCase("OK")) {

                        getAddedIVRSList();

                    } else {

                        Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<AddIVRSDto> call, Throwable t) {
               progressDialogDismiss();
                Log.e("MSG", t.getMessage());

                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void ivrs_added_successful_PonUpDialog() {
        final Dialog successDialog;
        successDialog = new Dialog(AddIVRSActivity.this, R.style.Theme_Dialog);
        successDialog.setContentView(R.layout.ivrs_added_successful_dialog);
        successDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        successDialog.setCancelable(false);

        Button ok_Button = successDialog.findViewById(R.id.ok_Button);


        ok_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                successDialog.dismiss();
                getAddedIVRSList();

            }
        });

        successDialog.show();
    }


    @Override
    public void onListItem(View view, int adapterPosition) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(parent_linear_layout.getWindowToken(), 0);

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;


        ivrsNo = ivrsList.get(adapterPosition).getIvrsNo();
        loc_cd = ivrsList.get(adapterPosition).getLocCd();
        gr_no = ivrsList.get(adapterPosition).getGrNo();
        rd_no = ivrsList.get(adapterPosition).getRdNo();
        cons_name = ivrsList.get(adapterPosition).getConsName();
        cons_add = ivrsList.get(adapterPosition).getConsAdd();
        circle = ivrsList.get(adapterPosition).getCircle();
        net_bill = ivrsList.get(adapterPosition).getNetBill();
        net_incl_surchage = ivrsList.get(adapterPosition).getNetInclSurchage();
        due_date = ivrsList.get(adapterPosition).getDueDate();
        enc_data = ivrsList.get(adapterPosition).getEnc_data();

       // Log.e("enc_data",enc_data);

        switch (view.getId()) {

            case R.id.pay_now_Button:
                progressDialogShow();
                CheckDuplicateBillDto billDto = new CheckDuplicateBillDto();
                billDto.setService("MPPKVVCL");
                billDto.setService_id(ivrsNo);
                checkDuplicateBill(billDto);

                break;



            case R.id.ivrs_number_card_view:

                ivrsDetailsPonUpDialog();

                break;

            case R.id.delete_ivrs_IV:
                //creating a popup menu
                PopupMenu popup = new PopupMenu(AddIVRSActivity.this, view);
                //inflating menu from xml resource
                popup.inflate(R.menu.ivrsdeletemenu);
                //adding click listener

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mnudelete:
                                //handle menu1 click

                                final SweetAlertDialog sad = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.WARNING_TYPE);
                                sad.setTitleText(getString(R.string.delete));
                                sad.setContentText(getString(R.string.are_you_sure_you_want_to_delete_IVRS));
                                sad.setConfirmText(getString(R.string.exit_yes));
                                sad.setCanceledOnTouchOutside(false);
                                sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();

                                        AddIVRSDto ivrsDto = new AddIVRSDto();
                                        ivrsDto.setIvrsNo(ivrsNo);
                                        deleteIVRS(ivrsDto);

                                    }
                                })
                                        .setCancelButton(getString(R.string.exit_no), new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismiss();
                                            }
                                        })
                                        .show();
                                //Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }



                });
                //displaying the popup
                popup.show();

                break;

        }

        } else {


            connected = false;

            Snackbar snackbar = Snackbar
                    .make(parent_linear_layout, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
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


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void ivrsDetailsPonUpDialog(){
        final Dialog ivrsDialog;
        ivrsDialog = new Dialog(AddIVRSActivity.this,R.style.Theme_Dialog);
        ivrsDialog.setContentView(R.layout.ivrs_details_popup);
        ivrsDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ivrsDialog.setCancelable(false);

        final TextView pop_up_ivrs_no_TV = ivrsDialog.findViewById(R.id.pop_up_ivrs_no_TV);
        TextView pop_up_name_Tv = ivrsDialog.findViewById(R.id.pop_up_name_Tv);
        TextView pop_up_address_TV = ivrsDialog.findViewById(R.id.pop_up_address_TV);
        TextView pop_up_circle_TV = ivrsDialog.findViewById(R.id.pop_up_circle_TV);
        TextView pop_up_amount_TV = ivrsDialog.findViewById(R.id.pop_up_amount_TV);
        TextView pop_up_surcharge_Tv = ivrsDialog.findViewById(R.id.pop_up_surcharge_Tv);
        TextView pop_up_due_date_TV = ivrsDialog.findViewById(R.id.pop_up_due_date_TV);
        ImageView cancel_IMG = ivrsDialog.findViewById(R.id.cancel_IMG);

        pop_up_ivrs_no_TV.setText(ivrsNo);
        pop_up_name_Tv.setText(cons_name);
        pop_up_address_TV.setText(cons_add);
        pop_up_circle_TV.setText(circle);
        pop_up_amount_TV.setText("₹ "+net_bill);
        pop_up_surcharge_Tv.setText("₹ "+net_incl_surchage);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dtStartOK = format.parse(due_date);
            //String stringDate = DateFormat.getDateTimeInstance().format(dtStartOK);
            pop_up_due_date_TV.setText(DateFormat.getDateInstance().format(dtStartOK));
            //   transaction_Date_amount_TV.setText(DateFormat.getTimeInstance().format(dtStartOK));

        } catch (ParseException e) {
            //Handle exception here, most of the time you will just log it.
            e.printStackTrace();

        }



        cancel_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivrsDialog.dismiss();

            }
        });

        ivrsDialog.show();
    }


    private void findIVRSNumber() {
        final Dialog successDialog;
        successDialog = new Dialog(AddIVRSActivity.this, R.style.Theme_Dialog);
        successDialog.setContentView(R.layout.electricity_bill_sample_popup);
        successDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        successDialog.setCancelable(false);

        ImageView cancel_IMG = successDialog.findViewById(R.id.cancel_IMG);

        cancel_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                successDialog.dismiss();
            }
        });

        successDialog.show();
    }


    private void scanAndPayIVRSQR(AddIVRSDto ivrsDto) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<AddIVRSDto> call = retroFitInterface.addIVRS(credential, ivrsDto);

        call.enqueue(new Callback<AddIVRSDto>() {
            @Override
            public void onResponse(Call<AddIVRSDto> call, Response<AddIVRSDto> response) {

                AddIVRSDto body = response.body();

                if (response.code() == 200) {

                    String codeStatus = body.getCode();

                    if (codeStatus.equalsIgnoreCase("OK")) {

                        mCodeScanner.stopPreview();
                        add_ivrs_LinearLayout.setVisibility(View.GONE);

                        getAgainAddedIVRSList();

                    } else if (codeStatus.equalsIgnoreCase("ERROR")) {

                        if (body.getMessage().equalsIgnoreCase("No such IVRS number found")){
                            progressDialogDismiss();
                            Snackbar.make(parent_linear_layout, body.getMessage(), Snackbar.LENGTH_SHORT)
                                    .setAction(getString(R.string.action), null).show();

                        } else if(body.getMessage().equalsIgnoreCase("IVRS number already added.")) {
                            findAddedIVRS();
                        } else {
                            progressDialogDismiss();
                            Toast.makeText(AddIVRSActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        // Toast.makeText(AddIVRSActivity.this, profileDTO.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialogDismiss();
                        Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {

                    progressDialogDismiss();

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<AddIVRSDto> call, Throwable t) {
                progressDialogDismiss();
                Log.e("MSG", t.getMessage());
                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getAgainAddedIVRSList() {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<IVRSListDto> call = retroFitInterface.getIVRSNumber(credential);

        call.enqueue(new Callback<IVRSListDto>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<IVRSListDto> call, Response<IVRSListDto> response) {
             //   progressLog.dismiss();

                IVRSListDto listDto = response.body();

                if (response.code() == 200) {
                    /* Log.e("aaaaa", String.valueOf(listDto.getIvrsNoList().size()));*/
                    if (listDto.getIvrsNoList().size()<=0 ){
                        progressDialogDismiss();
                        add_ivrs_LinearLayout.setVisibility(View.VISIBLE);
                        added_ivrs_LinearLayout.setVisibility(View.GONE);
                        add_IVR_floatingButton.setVisibility(View.GONE);
                        scan_View_FrameLayout.setVisibility(View.GONE);

                    } else {

                        findAddedIVRS();
                        scan_View_FrameLayout.setVisibility(View.GONE);
                        add_ivrs_LinearLayout.setVisibility(View.GONE);
                        added_ivrs_LinearLayout.setVisibility(View.VISIBLE);
                        add_IVR_floatingButton.setVisibility(View.VISIBLE);
                        ivrsList = listDto.getIvrsNoList();
                        setAdapter();

                    }



                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {
                    progressDialogDismiss();
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<IVRSListDto> call, Throwable t) {
                progressDialogDismiss();
                //Log.e("MSG",t.getMessage());

                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void findAddedIVRS() {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<IVRSListDto> call = retroFitInterface.getIVRSNumber(credential);

        call.enqueue(new Callback<IVRSListDto>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onResponse(Call<IVRSListDto> call, Response<IVRSListDto> response) {

                IVRSListDto listDto = response.body();

                if (response.code() == 200) {

                    if (listDto.getIvrsNoList().size()<=0 ){
                        progressDialogDismiss();
                        add_ivrs_LinearLayout.setVisibility(View.VISIBLE);
                        added_ivrs_LinearLayout.setVisibility(View.GONE);
                        add_IVR_floatingButton.setVisibility(View.GONE);

                    } else {

                        add_ivrs_LinearLayout.setVisibility(View.GONE);
                        added_ivrs_LinearLayout.setVisibility(View.VISIBLE);
                        add_IVR_floatingButton.setVisibility(View.VISIBLE);
                        ivrsList = listDto.getIvrsNoList();

                        for (int i = 0; i < ivrsList.size(); i++) {

                            if (ivrsList.get(i).getIvrsNo().equalsIgnoreCase(IVRS_NUMBER)) {

                                ivrsNo = ivrsList.get(i).getIvrsNo();
                                loc_cd=(ivrsList.get(i).getLocCd());
                                gr_no = (ivrsList.get(i).getGrNo());
                                rd_no = (ivrsList.get(i).getRdNo());
                                cons_name=(ivrsList.get(i).getConsName());
                                cons_add=(ivrsList.get(i).getConsAdd());
                                circle=(ivrsList.get(i).getCircle());
                                net_bill=(ivrsList.get(i).getNetBill());
                                net_incl_surchage=(ivrsList.get(i).getNetInclSurchage());
                                due_date=(ivrsList.get(i).getDueDate());
                                enc_data=(ivrsList.get(i).getEnc_data());

                            }

                            CheckDuplicateBillDto billDto = new CheckDuplicateBillDto();
                            billDto.setService("MPPKVVCL");
                            billDto.setService_id(ivrsNo);
                            checkDuplicateBill(billDto);

                        }

                    }

                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {
                    progressDialogDismiss();
                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<IVRSListDto> call, Throwable t) {
                progressDialogDismiss();
                //Log.e("MSG",t.getMessage());

                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void checkDuplicateBill(CheckDuplicateBillDto duplicateBillDto) {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<CheckDuplicateBillDto> call = retroFitInterface.checkDuplicateBill(credential, duplicateBillDto);

        call.enqueue(new Callback<CheckDuplicateBillDto>() {
            @Override
            public void onResponse(Call<CheckDuplicateBillDto> call, Response<CheckDuplicateBillDto> response) {

                progressDialogDismiss();

                CheckDuplicateBillDto body = response.body();

                if (response.code() == 200) {


                    if (body.getCode().equalsIgnoreCase("OK")){


                        Intent intent = new Intent(AddIVRSActivity.this,PayUsingActivity.class);

                        intent.putExtra("ivrsNo",ivrsNo);
                        intent.putExtra("loc_cd",loc_cd);
                        intent.putExtra("gr_no",gr_no);
                        intent.putExtra("rd_no",rd_no);
                        intent.putExtra("cons_name",cons_name);
                        intent.putExtra("cons_add",cons_add);
                        intent.putExtra("circle",circle);
                        intent.putExtra("net_bill",net_bill);
                        intent.putExtra("net_incl_surchage",net_incl_surchage);
                        intent.putExtra("due_date",due_date);
                        intent.putExtra("enc_data",enc_data);
                        startActivity(intent);
                        finish();


                    } else if (body.getCode().equalsIgnoreCase("ERROR")){

                        Snackbar.make(parent_linear_layout, body.getMessage(), Snackbar.LENGTH_LONG).show();

                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddIVRSActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(AddIVRSActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<CheckDuplicateBillDto> call, Throwable t) {

                progressDialogDismiss();
                Log.e("MSG", t.getMessage());

                Toast.makeText(AddIVRSActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("RestrictedApi")
    public void checkPermission(String[] permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> requestpermiosn = new ArrayList<>();
            for (String s : permissions) {

                requestpermiosn.add(s);
            }
            if (!requestpermiosn.isEmpty()) {
                String[] stockArr = new String[requestpermiosn.size()];
                stockArr = requestpermiosn.toArray(stockArr);
                requestPermissions(stockArr, MY_CAMERA_REQUEST_CODE);

            } else {

                add_IVR_floatingButton.setVisibility(View.GONE);
                mCodeScanner.startPreview();
                scan_View_FrameLayout.setVisibility(View.VISIBLE);

            }
        } else {

            add_IVR_floatingButton.setVisibility(View.GONE);
            mCodeScanner.startPreview();
            scan_View_FrameLayout.setVisibility(View.VISIBLE);

        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    add_IVR_floatingButton.setVisibility(View.GONE);
                    mCodeScanner.startPreview();

                    scan_View_FrameLayout.setVisibility(View.VISIBLE);

                } else {

                    cameraPermission();
                }
                break;
        }
    }


    private void cameraPermission() {
        final Dialog permissionDialog;
        permissionDialog = new Dialog(AddIVRSActivity.this, R.style.Theme_Dialog);
        permissionDialog.setContentView(R.layout.permission_allow_for_camera_pop_up);
        permissionDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        permissionDialog.setCancelable(false);

        Button enable_camera_BTN = permissionDialog.findViewById(R.id.enable_camera_BTN);


        enable_camera_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionDialog.dismiss();
                checkPermission(permission);
            }
        });

        permissionDialog.show();
    }


    private void selectLanguagePonUpDialog(){
        selectLanguageDialog = new Dialog(AddIVRSActivity.this,R.style.Theme_Dialog);
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

            Intent i = new Intent(AddIVRSActivity.this, DashboardActivity.class);
            startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


    }


}
