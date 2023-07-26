package com.nictbills.app.activities.notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.AddIVRSActivity;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.activities.PayUsingActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.AddIVRSDto;
import com.nictbills.app.activities.health_id_abdm.dto.CheckDuplicateBillDto;
import com.nictbills.app.activities.health_id_abdm.dto.IVRSListDto;
import com.nictbills.app.activities.health_id_abdm.dto.IvrsNoList;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationDisplayActivity extends BaseActivity {

    private TextView tv_Topic_title,tv_topic_body_message,tv_title, tv_bill_month, tv_service_name, tv_cosumer_name, tv_cosumer_address, tv_circle, tv_amount, tv_with_surcharge, tv_due_date, tv_body_message;
    private Button pay_now_btn;
    private RetrofitInterface retroFitInterface;
    private LinearLayout parent_linear_layout,notification_topics_LL,nict_electricity_bill_LL;
    private String ivrsNo,credential,mobileNumber,loc_cd,gr_no,rd_no,cons_name,cons_add,circle,net_bill,net_incl_surchage,due_date,enc_data;
    private SharedPreferences sharedPreferences;
    private List<IvrsNoList> ivrsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_display);

        tv_title = findViewById(R.id.tv_title);
        tv_bill_month = findViewById(R.id.tv_bill_month);
        tv_service_name = findViewById(R.id.tv_service_name);
        tv_cosumer_name = findViewById(R.id.tv_cosumer_name);
        tv_cosumer_address = findViewById(R.id.tv_cosumer_address);
        tv_circle = findViewById(R.id.tv_circle);
        tv_amount = findViewById(R.id.tv_amount);
        tv_with_surcharge = findViewById(R.id.tv_with_surcharge);
        tv_due_date = findViewById(R.id.tv_due_date);
        tv_body_message = findViewById(R.id.tv_body_message);
        pay_now_btn = findViewById(R.id.pay_now_btn);
        parent_linear_layout = findViewById(R.id.parent_linear_layout);
        tv_Topic_title = findViewById(R.id.tv_Topic_title);
        tv_topic_body_message = findViewById(R.id.tv_topic_body_message);
        notification_topics_LL = findViewById(R.id.notification_topics_LL);
        nict_electricity_bill_LL = findViewById(R.id.nict_electricity_bill_LL);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");

        Bundle extras = getIntent().getExtras();
       String notify_title = null, notify_body = null, ivrs = null, amount = null, consumer_name = null, jsonStrData = null;


       boolean foundflag = false;
        ConsumerJsonData consumerJsonData = null;
        if (extras != null) {

            for (String key : extras.keySet()) {
                if(key.equalsIgnoreCase("notify_title")){
                    notify_title = (String) extras.get("notify_title");
                    tv_title.setText(notify_title);
                }

                if(key.equalsIgnoreCase("notify_body")){
                    notify_body = (String) extras.get("notify_body");
                    tv_body_message.setText(notify_body);
                }

                if(key.equalsIgnoreCase("ivrs")){
                    ivrs = (String) extras.get("ivrs");
                }

                if(key.equalsIgnoreCase("amount")){
                    amount = (String) extras.get("amount");
                    tv_amount.setText(amount);
                }

                if(key.equalsIgnoreCase("consumer_name")){
                    consumer_name = (String) extras.get("consumer_name");
                    tv_cosumer_name.setText(consumer_name);
                }

                if(key.equalsIgnoreCase("foundflag")){
                    foundflag = extras.getBoolean("foundflag");
                }

                if(foundflag){
                    notification_topics_LL.setVisibility(View.GONE);
                    nict_electricity_bill_LL.setVisibility(View.VISIBLE);

                    if(key.equalsIgnoreCase("jsonStrData")){
                        jsonStrData = (String) extras.get("jsonStrData");
                        Gson gson = new Gson();
                        consumerJsonData = gson.fromJson( jsonStrData, ConsumerJsonData.class );
                        Log.e("vinod consumerJsonData", gson.toJson(consumerJsonData) );

                        tv_bill_month.setText(consumerJsonData.getBillmonth());

                        tv_service_name.setText(consumerJsonData.getIvrs());
                        ivrsNo= consumerJsonData.getIvrs();
                        tv_cosumer_name.setText(consumerJsonData.getConsname());
                        tv_cosumer_address.setText(consumerJsonData.getAddress());
                        tv_circle.setText("");
                        tv_amount.setText(consumerJsonData.getAmtwithoutsurcharge());
                        tv_with_surcharge.setText(String.valueOf(Double.parseDouble(consumerJsonData.getAmtwithoutsurcharge())+Double.parseDouble(consumerJsonData.getSurcharge())));

                        try {
                            tv_due_date.setText(Util.getUserFormatDate(consumerJsonData.getCashduedate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tv_body_message.setText(notify_body);


                    }
                } else if (!foundflag){

                    notification_topics_LL.setVisibility(View.VISIBLE);
                    nict_electricity_bill_LL.setVisibility(View.GONE);


                    tv_Topic_title.setText(notify_title);
                    tv_topic_body_message.setText(notify_body);


                }


            }

            Log.e("extra data", "Notify Title : "+notify_title);
            Log.e("extra data", "notify_body : "+notify_body);
            Log.e("extra data", "ivrs : "+ivrs);
            Log.e("extra data", "amount : "+amount);
            Log.e("extra data", "consumer_name : "+consumer_name);
            Log.e("extra data", "foundflag : "+foundflag+"");
            Log.e("extra data", "jsonStrData : "+jsonStrData+"");

               // Glide.with(this).load(data_image_uri).into(firebase_image);

        }else{
            Log.e("vinod","record not available");
        }

        pay_now_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialogShow();
                AddIVRSDto ivrsDto = new AddIVRSDto();
                ivrsDto.setIvrsNo(ivrsNo);
                scanAndPayIVRSQR(ivrsDto);
            }
        });

    }



    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
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

                       Intent intent = new Intent(NotificationDisplayActivity.this,AddIVRSActivity.class);
                       startActivity(intent);

                    } else if (codeStatus.equalsIgnoreCase("ERROR")) {

                        if (body.getMessage().equalsIgnoreCase("No such IVRS number found")){
                            progressDialogDismiss();
                            Snackbar.make(parent_linear_layout, body.getMessage(), Snackbar.LENGTH_SHORT)
                                    .setAction(getString(R.string.action), null).show();

                        } else if(body.getMessage().equalsIgnoreCase("IVRS number already added.")) {
                            findAddedIVRS();
                        } else {
                            progressDialogDismiss();
                            Toast.makeText(NotificationDisplayActivity.this, body.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        // Toast.makeText(AddIVRSActivity.this, profileDTO.getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialogDismiss();
                        Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {

                    progressDialogDismiss();

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(NotificationDisplayActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(NotificationDisplayActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<AddIVRSDto> call, Throwable t) {
                progressDialogDismiss();
                Log.e("MSG", t.getMessage());
                Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
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

                        Intent intent = new Intent(NotificationDisplayActivity.this,AddIVRSActivity.class);
                        startActivity(intent);

                    } else {

                        ivrsList = listDto.getIvrsNoList();

                        for (int i = 0; i < ivrsList.size(); i++) {

                            if (ivrsList.get(i).getIvrsNo().equalsIgnoreCase(ivrsNo)) {

                                ivrsNo = ivrsList.get(i).getIvrsNo();
                                loc_cd =(ivrsList.get(i).getLocCd());
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
                    Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(NotificationDisplayActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(NotificationDisplayActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {
                    progressDialogDismiss();
                    Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<IVRSListDto> call, Throwable t) {
                progressDialogDismiss();
                //Log.e("MSG",t.getMessage());

                Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
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


                        Intent intent = new Intent(NotificationDisplayActivity.this, PayUsingActivity.class);

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

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(NotificationDisplayActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(NotificationDisplayActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {

                    Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<CheckDuplicateBillDto> call, Throwable t) {

                progressDialogDismiss();
                Log.e("MSG", t.getMessage());

                Toast.makeText(NotificationDisplayActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }



}