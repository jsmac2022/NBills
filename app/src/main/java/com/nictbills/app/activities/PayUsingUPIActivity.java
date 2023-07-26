package com.nictbills.app.activities;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO.GetQRCodeResponseDto;
import com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO.UPIVpaDataSend;
import com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO.UpiVpaDTO;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayUsingUPIActivity extends BaseActivity implements MyBottomSheetDialog.CallBack {


    private ListView app_listView;
    private LinearLayout pay_now_UPI_LinearLayout, upi_id_click_linear_layout,parent_id_UPI;
    private String useReward,service,merchantTranId,billSaveStatus,service_id,txnDate,amount,UPI,CUST_UPI_ID,credential,loc_cd, gr_no,enc_data,ivrsNo,cons_name,net_bill,rd_no,cons_add,circle,net_incl_surchage,due_date;
    private EditText upi_id_EDT;
    private TextView total_amount_TV,amount_Tv;
    private ImageView backArrow_IMG;
    private SharedPreferences sharedPreferences;
    private RetrofitInterface retroFitInterface;
    private MyBottomSheetDialog bottomSheet;
    private Handler VPAhandler = new Handler(),QRHandler= new Handler();
    private ComponentName name;
    private CountDownTimer countDownTimer;
    private boolean isHandlerIniliaze=false,isVPAHandlerIniliaze=false;
    private int reward_point=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_using_u_p_i);

        app_listView = findViewById(R.id.app_listView);
        upi_id_EDT = findViewById(R.id.upi_id_EDT);
        pay_now_UPI_LinearLayout = findViewById(R.id.pay_now_UPI_LinearLayout);
        upi_id_click_linear_layout = findViewById(R.id.upi_id_click_linear_layout);
        total_amount_TV = findViewById(R.id.total_amount_TV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        parent_id_UPI = findViewById(R.id.parent_id_UPI);
        amount_Tv = findViewById(R.id.amount_Tv);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        credential = sharedPreferences.getString("cred_1", "");
        getUPIString();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(UPI));

        PackageManager pm = getPackageManager();

        final List<ResolveInfo> launchables = pm.queryIntentActivities(intent, 0);

        AppAdapter adapter = new AppAdapter(pm, launchables);
        app_listView.setAdapter(adapter);

        Intent intentGet = getIntent();
        ivrsNo = intentGet.getStringExtra("ivrsNo");
        loc_cd = intentGet.getStringExtra("loc_cd");
        gr_no = intentGet.getStringExtra("gr_no");
        rd_no = intentGet.getStringExtra("rd_no");
        cons_name = intentGet.getStringExtra("cons_name");
        cons_add = intentGet.getStringExtra("cons_add");
        circle = intentGet.getStringExtra("circle");
        net_bill = intentGet.getStringExtra("net_bill");
        net_incl_surchage = intentGet.getStringExtra("net_incl_surchage");
        due_date = intentGet.getStringExtra("due_date");
        enc_data = intentGet.getStringExtra("enc_data");
        useReward = intentGet.getStringExtra("useReward");
        reward_point = intentGet.getIntExtra("REWARD_POINT",0);

        //double y = Integer.parseInt(net_bill);
        double y = Double.parseDouble(net_bill);
        double z = y - reward_point;

        amount_Tv.setText("₹ "+ z);


        total_amount_TV.setText(getString(R.string.pay)+" "+"₹ "+ z);

        app_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ActivityInfo activity = launchables.get(position).activityInfo;
                 name = new ComponentName(activity.packageName, activity.name);
               /* Intent intent = new Intent();
                intent.setComponent(name);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(UPI));
                startActivity(intent);*/


               UPIVpaDataSend dataSend = new UPIVpaDataSend();
               dataSend.setEnc_data(enc_data);
               dataSend.setService("mppkvvcl");
               dataSend.setUseReward(useReward);
               createUPIQRorder(dataSend);

            }
        });


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (VPAhandler!=null){
                    VPAhandler.removeCallbacksAndMessages(null);
                }

                if (QRHandler!=null){
                    QRHandler.removeCallbacksAndMessages(null);
                }

                Intent intentUPI= new Intent(PayUsingUPIActivity.this, PayUsingActivity.class);
                intentUPI.putExtra("ivrsNo",ivrsNo);
                intentUPI.putExtra("loc_cd",loc_cd);
                intentUPI.putExtra("gr_no",gr_no);
                intentUPI.putExtra("rd_no",rd_no);
                intentUPI.putExtra("cons_name",cons_name);
                intentUPI.putExtra("cons_add",cons_add);
                intentUPI.putExtra("circle",circle);
                intentUPI.putExtra("net_bill",net_bill);
                intentUPI.putExtra("net_incl_surchage",net_incl_surchage);
                intentUPI.putExtra("due_date",due_date);
                intentUPI.putExtra("enc_data",enc_data);
                startActivity(intentUPI);
                finish();

            }
        });


        upi_id_click_linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                upi_id_EDT.setVisibility(View.VISIBLE);
                upi_id_EDT.requestFocus();


            }
        });


        upi_id_EDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                pay_now_UPI_LinearLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isEmpty(upi_id_EDT)) {

                    pay_now_UPI_LinearLayout.setVisibility(View.GONE);

                } else {

                    pay_now_UPI_LinearLayout.setVisibility(View.VISIBLE);
                }


            }
        });


        pay_now_UPI_LinearLayout.setOnClickListener(new View.OnClickListener() {
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

                    CUST_UPI_ID = upi_id_EDT.getText().toString();

                    if (isEmpty(upi_id_EDT)) {

                        Snackbar.make(v, getString(R.string.kindly_fill_UPI_Id), Snackbar.LENGTH_SHORT).show();

                    } else {

                        UPIVpaDataSend dataSend = new UPIVpaDataSend();
                        dataSend.setEnc_data(enc_data);
                        dataSend.setVpa(CUST_UPI_ID);
                        dataSend.setService("mppkvvcl");
                        dataSend.setUseReward(useReward);
                        createUPIVPAorder(dataSend);
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

    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private String getUPIString() {

      // UPI = "upi://pay?pa=" + "ffffff@paytm" + "&pn=" + "shub" + "&mc=" + 5411 + "&tr=" + "EZV2020111814275500019437" + "&am=" + 1 + "&cu=" + "INR";

        UPI = "upi://pay";

        return UPI.replace(" ", "+");

    }

    @Override
    public void cancelBottomView() {

                if( VPAhandler!=null){
                    VPAhandler.removeCallbacksAndMessages(null);
                }
    }


    class AppAdapter extends ArrayAdapter<ResolveInfo> {
        private PackageManager pm = null;
        private List<ResolveInfo> apps = null;

        AppAdapter(PackageManager pm, List<ResolveInfo> apps) {
            super(PayUsingUPIActivity.this, R.layout.row, apps);
            this.pm = pm;
            this.apps = apps;
        }


        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            if (convertView == null) {
                convertView = newView(parent);
            }

            bindView(position, convertView);

            return (convertView);
        }

        private View newView(ViewGroup parent) {
            return (getLayoutInflater().inflate(R.layout.row, parent, false));
        }

        private void bindView(int position, View row) {
            TextView app_name_TV = (TextView) row.findViewById(R.id.app_name_TV);

            app_name_TV.setText(getItem(position).loadLabel(pm));

            ImageView app_icon_IMG = (ImageView) row.findViewById(R.id.app_icon_IMG);

            app_icon_IMG.setImageDrawable(getItem(position).loadIcon(pm));
        }
    }


    @Override
    public void onBackPressed() {

        if (VPAhandler!=null){
            VPAhandler.removeCallbacksAndMessages(null);
        }

        if (QRHandler!=null){
            QRHandler.removeCallbacksAndMessages(null);
        }

        Intent intentUPI= new Intent(PayUsingUPIActivity.this, PayUsingActivity.class);
        intentUPI.putExtra("ivrsNo",ivrsNo);
        intentUPI.putExtra("loc_cd",loc_cd);
        intentUPI.putExtra("gr_no",gr_no);
        intentUPI.putExtra("rd_no",rd_no);
        intentUPI.putExtra("cons_name",cons_name);
        intentUPI.putExtra("cons_add",cons_add);
        intentUPI.putExtra("circle",circle);
        intentUPI.putExtra("net_bill",net_bill);
        intentUPI.putExtra("net_incl_surchage",net_incl_surchage);
        intentUPI.putExtra("due_date",due_date);
        intentUPI.putExtra("enc_data",enc_data);
        startActivity(intentUPI);
        finish();

    }


    private void verifyVPATxn(UpiVpaDTO vpaDTO) {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<UpiVpaDTO> call = retroFitInterface.VPARequest(credential, vpaDTO);

        call.enqueue(new Callback<UpiVpaDTO>() {
            @Override
            public void onResponse(Call<UpiVpaDTO> call, Response<UpiVpaDTO> response) {


                UpiVpaDTO body = response.body();

                if (response.code() == 200) {

                    merchantTranId = body.getMerchantTranId();
                    amount = body.getAmount();
                    billSaveStatus = body.getBillSaveStatus();
                    service_id = body.getService_id();
                    service = body.getService();


                    if (body.getTxnStatus().equalsIgnoreCase("SUCCESS")){

                        if (bottomSheet!=null){

                            try {

                                bottomSheet.dismiss();

                            } catch (IllegalStateException e) {

                                Toast.makeText(PayUsingUPIActivity.this, "Payment done successfully", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (VPAhandler!=null){
                            VPAhandler.removeCallbacksAndMessages(null);
                        }

                        if (body.getBillSaveStatus().equalsIgnoreCase("SUCCESS")){

                            txnDate = body.getTxnDate();
                            Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                            intent.putExtra("paymentReceipt",merchantTranId);
                            intent.putExtra("service",service);
                            intent.putExtra("service_id",service_id);
                            intent.putExtra("PayUSING","UPI_VPA");
                            intent.putExtra("txnDate",txnDate);
                            intent.putExtra("amount",amount);
                            intent.putExtra("STATUS","SUCCESS");
                            startActivity(intent);
                            finish();


                        } else if (body.getBillSaveStatus().equalsIgnoreCase("PENDING")){

                            Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                            intent.putExtra("paymentReceipt",merchantTranId);
                            intent.putExtra("service",service);
                            intent.putExtra("service_id",service_id);
                            intent.putExtra("PayUSING","UPI_VPA");
                            intent.putExtra("amount",amount);
                            intent.putExtra("STATUS","PENDING");
                            startActivity(intent);
                            finish();

                        }


                    } else if (body.getTxnStatus().toLowerCase().equalsIgnoreCase("fail")){


                        if (bottomSheet!=null){

                            try {

                                bottomSheet.dismiss();

                            } catch (IllegalStateException e) {

                                Toast.makeText(PayUsingUPIActivity.this, "Payment failed", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (VPAhandler!=null){

                            VPAhandler.removeCallbacksAndMessages(null);
                        }

                        Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                        intent.putExtra("paymentReceipt",merchantTranId);
                        intent.putExtra("service",service);
                        intent.putExtra("service_id",service_id);
                        intent.putExtra("PayUSING","UPI_VPA");
                        intent.putExtra("txnDate",txnDate);
                        intent.putExtra("amount",amount);
                        intent.putExtra("STATUS","FAILED");
                        startActivity(intent);
                        finish();


                    } else if (body.getTxnStatus().equalsIgnoreCase("PENDING")){

                        if (!isVPAHandlerIniliaze)
                        vPAHandler();

                      /*  VPAhandler =  new Handler();
                         myRunnableVPA = new Runnable() {
                            public void run() {

                                UpiVpaDTO dto = new UpiVpaDTO();
                                dto.setMerchantTranId(merchantTranId);
                                verifyVPATxn(dto);

                            }
                        };

                        VPAhandler.postDelayed(myRunnableVPA, 5000);

*/
                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingUPIActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PayUsingUPIActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<UpiVpaDTO> call, Throwable t) {

                Log.e("MSG", t.getMessage());

                Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void createUPIVPAorder(UPIVpaDataSend upiVpaDataSend) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(PayUsingUPIActivity.this);
        progressLog.setTitle(getString(R.string.please_wait));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<UPIVpaDataSend> call = retroFitInterface.createUPIVPAOrder(credential, upiVpaDataSend);

        call.enqueue(new Callback<UPIVpaDataSend>() {
            @Override
            public void onResponse(Call<UPIVpaDataSend> call, Response<UPIVpaDataSend> response) {
                progressLog.dismiss();

                UPIVpaDataSend dataSend = response.body();

                if (response.code() == 200) {

                    merchantTranId = dataSend.getMerchantTranId();


                    if (dataSend.getCode().equalsIgnoreCase("OK")){
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        bottomSheet = new MyBottomSheetDialog();
                        bottomSheet.setCallBack(PayUsingUPIActivity.this);
                        bottomSheet.setCancelable(false);
//                        bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                        transaction.add(bottomSheet, "ModalBottomSheet"); // New Add
                        transaction.commitAllowingStateLoss();


                        UpiVpaDTO dto = new UpiVpaDTO();
                        dto.setMerchantTranId(merchantTranId);
                        verifyVPATxn(dto);

                    } else if (dataSend.getCode().equalsIgnoreCase("ERROR")){

                        Snackbar.make(parent_id_UPI, dataSend.getMessage(), Snackbar.LENGTH_SHORT).show();


                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingUPIActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PayUsingUPIActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<UPIVpaDataSend> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG", t.getMessage());

                Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void createUPIQRorder(UPIVpaDataSend upiVpaDataSend) {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(PayUsingUPIActivity.this);
        progressLog.setTitle(getString(R.string.please_wait));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetQRCodeResponseDto> call = retroFitInterface.createQROrder(credential, upiVpaDataSend);

        call.enqueue(new Callback<GetQRCodeResponseDto>() {
            @Override
            public void onResponse(Call<GetQRCodeResponseDto> call, Response<GetQRCodeResponseDto> response) {
                progressLog.dismiss();

                GetQRCodeResponseDto dtoGet = response.body();

                if (response.code() == 200) {

                    merchantTranId = dtoGet.getMerchantTranId();


                    if (dtoGet.getCode().equalsIgnoreCase("OK")){

                        Intent intent = new Intent();
                        intent.setComponent(name);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(dtoGet.getQrString()));
                       // intent.setData(Uri.parse(UPI));

                        startActivityForResult(intent,123);

                    } else{

                        Snackbar.make(parent_id_UPI, getString(R.string.something_went_wrong), Snackbar.LENGTH_SHORT).show();

                    }


                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingUPIActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(PayUsingUPIActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<GetQRCodeResponseDto> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG", t.getMessage());

                Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        myTimer();
        UpiVpaDTO vpaDTO = new UpiVpaDTO();
        vpaDTO.setMerchantTranId(merchantTranId);
        verifyQRTxn(vpaDTO);

     /*  //String txn = data.getStringExtra("response");
        Log.e("aabbcc", String.valueOf(data));
        Log.e("aassdddd", String.valueOf(requestCode));
        Log.e("aaaaa", String.valueOf(resultCode));
      //  Log.e("txn", txn);
*/
    }


    private void verifyQRTxn(UpiVpaDTO vpaDTO) {

        progressCallBackDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<UpiVpaDTO> call = retroFitInterface.verifyQRTxn(credential, vpaDTO);

        call.enqueue(new Callback<UpiVpaDTO>() {
            @Override
            public void onResponse(Call<UpiVpaDTO> call, Response<UpiVpaDTO> response) {

                UpiVpaDTO body = response.body();

                if (response.code() == 200) {

                    merchantTranId = body.getMerchantTranId();
                    amount = body.getAmount();
                    billSaveStatus = body.getBillSaveStatus();
                    service_id = body.getService_id();
                    service = body.getService();


                    if (body.getTxnStatus().equalsIgnoreCase("SUCCESS")){

                        progressCallBackDialogDismiss();

                        if (QRHandler!=null){

                            QRHandler.removeCallbacksAndMessages(null);

                        }

                        countDownTimer.cancel();

                        if (body.getBillSaveStatus().equalsIgnoreCase("SUCCESS")){

                            txnDate = body.getTxnDate();

                            Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                            intent.putExtra("paymentReceipt",merchantTranId);
                            intent.putExtra("service",service);
                            intent.putExtra("service_id",service_id);
                            intent.putExtra("PayUSING","UPI_VPA");
                            intent.putExtra("txnDate",txnDate);
                            intent.putExtra("amount",amount);
                            intent.putExtra("STATUS","SUCCESS");
                            startActivity(intent);
                            finish();


                        } else if (body.getBillSaveStatus().equalsIgnoreCase("PENDING")){

                            Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                            intent.putExtra("paymentReceipt",merchantTranId);
                            intent.putExtra("service",service);
                            intent.putExtra("service_id",service_id);
                            intent.putExtra("PayUSING","UPI_VPA");
                            intent.putExtra("amount",amount);
                            intent.putExtra("STATUS","PENDING");
                            startActivity(intent);
                            finish();

                        }


                    } else if (body.getTxnStatus().toLowerCase().startsWith("fail")){

                        if (QRHandler!=null){

                            QRHandler.removeCallbacksAndMessages(null);

                        }

                        countDownTimer.cancel();
                        progressCallBackDialogDismiss();

                        Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                        intent.putExtra("paymentReceipt",merchantTranId);
                        intent.putExtra("service",service);
                        intent.putExtra("service_id",service_id);
                        intent.putExtra("PayUSING","UPI_VPA");
                        intent.putExtra("txnDate",txnDate);
                        intent.putExtra("amount",amount);
                        intent.putExtra("STATUS","FAILED");
                        startActivity(intent);
                        finish();


                    } else if (body.getTxnStatus().toLowerCase().equalsIgnoreCase("pending")){


                        if (!isHandlerIniliaze)

                            qRHandler();


                    }


                } else if (response.code() == 401) {

                    progressCallBackDialogDismiss();

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayUsingUPIActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText("Unable to connect to server");
                    sweetAlertDialog.setConfirmText("Login Again");
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(PayUsingUPIActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressCallBackDialogDismiss();
                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
                    progressCallBackDialogDismiss();

                    Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<UpiVpaDTO> call, Throwable t) {
                progressCallBackDialogDismiss();
                Log.e("MSG", t.getMessage());

                Toast.makeText(PayUsingUPIActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void vPAHandler() {

        isVPAHandlerIniliaze = true;

        VPAhandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                UpiVpaDTO dto = new UpiVpaDTO();
                dto.setMerchantTranId(merchantTranId);
                verifyVPATxn(dto);
                VPAhandler.postDelayed(this, 5000);
            }
        }, 500);
    }

    private void qRHandler() {

        isHandlerIniliaze = true;

        QRHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                UpiVpaDTO vpaDTO = new UpiVpaDTO();
                vpaDTO.setMerchantTranId(merchantTranId);
                verifyQRTxn(vpaDTO);

                QRHandler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    private void myTimer(){

        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

               // counttime_TV.setText(String.valueOf(counter));
                progressCallBackDialogShow();

            }

            @Override
            public void onFinish() {

                     progressCallBackDialogDismiss();

                   if (QRHandler!=null){
                       QRHandler.removeCallbacksAndMessages(null);
                   }


                Intent intent = new Intent(PayUsingUPIActivity.this,PaymentStatusActivity.class);
                intent.putExtra("paymentReceipt",merchantTranId);
                intent.putExtra("service",service);
                intent.putExtra("service_id",service_id);
                intent.putExtra("PayUSING","UPI_VPA");
                intent.putExtra("amount",amount);
                intent.putExtra("STATUS","PENDING");
                startActivity(intent);
                finish();

            }
        }.start();

    }

}
