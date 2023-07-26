package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.SlotDetailsAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.Center;
import com.nictbills.app.activities.health_id_abdm.dto.schedule_dto.ResheduleRequest;
import com.nictbills.app.activities.health_id_abdm.dto.schedule_dto.ScheduleRequest;
import com.nictbills.app.activities.health_id_abdm.dto.schedule_dto.ScheduleResponse;
import com.nictbills.app.interfaces.ParentChildRecView;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookFinalAppointmentActivity extends AppCompatActivity implements ParentChildRecView {

    private Center centerData;
    private RecyclerView slot_details_RV;
    private RecyclerView.LayoutManager layoutManager;
    private Button bookNow_BTN;
    private String hospitalName, hospitalLocation, fee_type,authTok,beneficary_Id,sesionId="",slot="",status,appointmentID;
    private int dose,center_Id;
    List<String> beneficiaryList = new ArrayList<String>();
    private TextView hospital_name_TV,location_TV,toolbar_title,amount_TV;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_final_appointment);

        slot_details_RV = findViewById(R.id.slot_details_RV);
        bookNow_BTN = findViewById(R.id.bookNow_BTN);
        hospital_name_TV = findViewById(R.id.hospital_name_TV);
        location_TV = findViewById(R.id.location_TV);
        toolbar_title = findViewById(R.id.toolbar_title);
        amount_TV = findViewById(R.id.amount_TV);

        Intent intent = getIntent();

            centerData=(Center)getIntent().getSerializableExtra("SLOTS");
           // centerData.getSessions().get(pos).getSlots()
           // Log.e("Center Name++++++", String.valueOf(centerData));

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        authTok = sharedPreferences.getString("cred_vaccine", "");



        hospitalName = intent.getStringExtra("hospital_name");
        hospitalLocation = intent.getStringExtra("hospital_location");
        fee_type = intent.getStringExtra("fee_type");
        dose = intent.getIntExtra("DOSE", 0);

        status = getIntent().getStringExtra("STATUS");

        Log.e("statsuss+",status);

        if (status.equalsIgnoreCase("RESCHEDULE")){

            appointmentID = getIntent().getStringExtra("APPOINTMENT_ID");


        } else {
            beneficary_Id = getIntent().getStringExtra("BENEFICIARY_ID");
            center_Id = getIntent().getIntExtra("CENTER_ID", 0);
        }



        amount_TV.setText(fee_type);
        location_TV.setText(hospitalLocation);
        beneficiaryList.add(beneficary_Id);
        hospital_name_TV.setText(hospitalName);

        toolbar_title.setText("Appointment for dose "+dose+"");


        layoutManager = new LinearLayoutManager(BookFinalAppointmentActivity.this);
        slot_details_RV.setLayoutManager(layoutManager);
        slot_details_RV.setItemAnimator(new DefaultItemAnimator());


        setAdapter();


        bookNow_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (status.equalsIgnoreCase("RESCHEDULE")){


                    if (slot.equalsIgnoreCase("")){

                        Snackbar.make(v, "Kindly select Slot", Snackbar.LENGTH_SHORT).show();

                    } else if (sesionId.equalsIgnoreCase("")){

                        Snackbar.make(v, "Kindly select again", Snackbar.LENGTH_SHORT).show();
                    }else {
                        ResheduleRequest registrationRequest = new ResheduleRequest();

                        registrationRequest.setAppointment_id(appointmentID);
                        registrationRequest.setSession_id(sesionId);
                        registrationRequest.setSlot(slot);
                        reScheduleAppointment(registrationRequest);
                    }

                }else {

                    if (slot.equalsIgnoreCase("")){

                        Snackbar.make(v, "Kindly select Slot", Snackbar.LENGTH_SHORT).show();

                    } else if (sesionId.equalsIgnoreCase("")){

                        Snackbar.make(v, "Kindly select again", Snackbar.LENGTH_SHORT).show();
                    }else {

                        ScheduleRequest scheduleRequest = new ScheduleRequest();

                        scheduleRequest.setDose(dose);
                        scheduleRequest.setBeneficiaries(beneficiaryList);
                        scheduleRequest.setSessionId(sesionId);
                        scheduleRequest.setSlot(slot);
                        scheduleRequest.setCenter_id(center_Id);
                        scheduleAppointment(scheduleRequest);
                    }
                }

            }
        });


    }

    private void setAdapter() {
        SlotDetailsAdapter adapter = new SlotDetailsAdapter(this, centerData.getSessions(),centerData.getVaccineFees(),this,fee_type);
        slot_details_RV.setAdapter(adapter);
    }



    @Override
    public void onListItem(View view,  int parentPosition,int childPosition) {

       // String id = centerData.getSessions().get(parentPosition).getSlots().get(childPosition);

        switch (view.getId()) {

            case R.id.time_slot_BTN:

                sesionId= centerData.getSessions().get(parentPosition).getSessionId();
                slot= centerData.getSessions().get(parentPosition).getSlots().get(childPosition);
                Log.e("Id++++++", slot);
                Log.e("date",centerData.getSessions().get(parentPosition).getSessionId());
                break;

        }
    }


    private void scheduleAppointment(ScheduleRequest registrationRequest) {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BookFinalAppointmentActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<ScheduleResponse> call = retroFitInterface.scheduleAppointment("Bearer "+authTok,Constant.USER_AGENT,registrationRequest);

        call.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                progressLog.dismiss();

                ScheduleResponse body = response.body();

                if (response.code() == 200) {

                    Intent intent = new Intent(BookFinalAppointmentActivity.this,BeneficiaryListActivity.class);
                    startActivity(intent);
                    finish();

                    //Toast.makeText(BookFinalAppointmentActivity.this, "added", Toast.LENGTH_SHORT).show();

                 //   Log.e("bene+++++",body.getAppointment_id());

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BookFinalAppointmentActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BookFinalAppointmentActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.code() == 400){

                    if(!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                            } catch (JSONException e) {
                                Toast.makeText(BookFinalAppointmentActivity.this, "Session id does not exists", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(BookFinalAppointmentActivity.this, "Session id does not exists", Toast.LENGTH_SHORT).show();

                            }
                            String errorCode = jsonObject.getString("error");
                            Toast.makeText(BookFinalAppointmentActivity.this, errorCode, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BookFinalAppointmentActivity.this, "Session id does not exists", Toast.LENGTH_SHORT).show();
                        }
                    }


                } else if (response.code() == 409){

                    Toast.makeText(BookFinalAppointmentActivity.this, "This vaccination center is completely booked for the selected date.", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(BookFinalAppointmentActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BookFinalAppointmentActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void reScheduleAppointment(ResheduleRequest registrationRequest) {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BookFinalAppointmentActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<ScheduleResponse> call = retroFitInterface.scheduleAppointment("Bearer "+authTok,Constant.USER_AGENT,registrationRequest);

        call.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                progressLog.dismiss();

                if (response.code() == 204) {

                    Intent intent = new Intent(BookFinalAppointmentActivity.this,BeneficiaryListActivity.class);
                    startActivity(intent);
                    finish();

                    //Toast.makeText(BookFinalAppointmentActivity.this, "added", Toast.LENGTH_SHORT).show();

                    //   Log.e("bene+++++",body.getAppointment_id());

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BookFinalAppointmentActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BookFinalAppointmentActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.code() == 400){

                    if(!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                            } catch (JSONException e) {
                                Toast.makeText(BookFinalAppointmentActivity.this, "Session id does not exists", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(BookFinalAppointmentActivity.this, "Session id does not exists", Toast.LENGTH_SHORT).show();

                            }
                            String errorCode = jsonObject.getString("error");
                            Toast.makeText(BookFinalAppointmentActivity.this, errorCode, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BookFinalAppointmentActivity.this, "Session id does not exists", Toast.LENGTH_SHORT).show();
                        }
                    }


                } else if (response.code() == 409){

                    Toast.makeText(BookFinalAppointmentActivity.this, "This vaccination center is completely booked for the selected date.", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(BookFinalAppointmentActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BookFinalAppointmentActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



}