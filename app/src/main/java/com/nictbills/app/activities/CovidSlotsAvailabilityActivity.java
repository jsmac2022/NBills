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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.SlotAvailabilityByPincodeAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.Center;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.CovidTimeSlotDTO;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidSlotsAvailabilityActivity extends AppCompatActivity implements OnClickRecyclerViewInterface {

    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private String authTok,pincode,date,beneficiaryId="acb",dis_code,dis_name,status,appointmentID="appointment";
    private int dose;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView center_Name_RV;
    private List<Center> centerList;
    private ImageView backArrow_IMG;
    private LinearLayout no_slots_available_LinearLayout;
    private TextView toolbar_title_PIN_TV,toolbar_title_Dose_TV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_slots_availability);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

       /* credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");*/
        authTok = sharedPreferences.getString("cred_vaccine", "");
        center_Name_RV = findViewById(R.id.center_Name_RV);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        no_slots_available_LinearLayout = findViewById(R.id.no_slots_available_LinearLayout);
        toolbar_title_PIN_TV = findViewById(R.id.toolbar_title_PIN_TV);
        toolbar_title_Dose_TV = findViewById(R.id.toolbar_title_Dose_TV);

        Intent intent = getIntent();

        dose = intent.getIntExtra("DOSE", 0);
        pincode = intent.getStringExtra("PIN");
        dis_code = intent.getStringExtra("DIS_CODE");
        dis_name = intent.getStringExtra("DIS_NAME");

        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        toolbar_title_Dose_TV.setText("Dose"+" "+dose);

        status = getIntent().getStringExtra("STATUS");

        Log.e("StatusCovidSlotA",status);


        if (status.equalsIgnoreCase("RESCHEDULE")){

            appointmentID = getIntent().getStringExtra("APPOINTMENT_ID");

        } else {
            beneficiaryId = getIntent().getStringExtra("BENEFICIARY_ID");
        }



        if (pincode.equalsIgnoreCase("PIN")){
            toolbar_title_PIN_TV.setText("Slots For "+ dis_name);
            getSlotAvailabilityDetailsByDistricts();

        } else {
            toolbar_title_PIN_TV.setText("Slots For "+ pincode);
            getSlotAvailabilityDetails();
        }


        layoutManager = new LinearLayoutManager(CovidSlotsAvailabilityActivity.this);
        center_Name_RV.setLayoutManager(layoutManager);
        center_Name_RV.setItemAnimator(new DefaultItemAnimator());

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }



    private void setAdapter() {
        SlotAvailabilityByPincodeAdapter adapter = new SlotAvailabilityByPincodeAdapter(this, centerList,dose,beneficiaryId,status,appointmentID);
        center_Name_RV.setAdapter(adapter);
    }

    private void getSlotAvailabilityDetails() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(CovidSlotsAvailabilityActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidTimeSlotDTO> call = retroFitInterface.getAppointmentListByPincode("Bearer "+authTok,Constant.USER_AGENT,pincode,date);

        call.enqueue(new Callback<CovidTimeSlotDTO>() {
            @Override
            public void onResponse(Call<CovidTimeSlotDTO> call, Response<CovidTimeSlotDTO> response) {
                progressLog.dismiss();

                CovidTimeSlotDTO body = response.body();

                if (response.code() == 200) {

                    if (body.getCenters().size()==0){
                        no_slots_available_LinearLayout.setVisibility(View.VISIBLE);
                        center_Name_RV.setVisibility(View.GONE);

                    } else {
                        no_slots_available_LinearLayout.setVisibility(View.GONE);
                        center_Name_RV.setVisibility(View.VISIBLE);

                        centerList = body.getCenters();
                        setAdapter();
                      /*  filterCenterList = new ArrayList<>();

                        for (int i = 0; i < centerList.size(); i++) {
                            Center center= new Center();
                            center.setAddress(centerList.get(i).getAddress());

                            center.setBlockName(centerList.get(i).getBlockName());
                            center.setDistrictName(centerList.get(i).getDistrictName());
                            center.setCenterId(centerList.get(i).getCenterId());
                            center.setFeeType(centerList.get(i).getFeeType());
                            center.setFrom(centerList.get(i).getFrom());
                            center.setTo(centerList.get(i).getTo());
                            center.setName(centerList.get(i).getName());
                            center.setPincode(centerList.get(i).getPincode());
                            center.setLat(centerList.get(i).getLat());
                            center.setLong(centerList.get(i).getLong());


                            center.setVaccineFees(centerList.get(i).getVaccineFees());


                            center.setStateName(centerList.get(i).getStateName());

                            for (int j = 0; j < centerList.get(i).getSessions().size(); j++) {
                                filterSessionList = new ArrayList<>();

                                if (centerList.get(i).getSessions().get(j).getMinAgeLimit()==15) {
                                    Session session = new Session();
                                    //center.setSessions(centerList.get(j).getSessions());
                                    session.setSessionId(centerList.get(i).getSessions().get(j).getSessionId());
                                    session.setDate(centerList.get(i).getSessions().get(j).getDate());
                                    session.setAvailableCapacity(centerList.get(i).getSessions().get(j).getAvailableCapacity());
                                    session.setMinAgeLimit(centerList.get(i).getSessions().get(j).getMinAgeLimit());
                                    session.setAllowAllAge(centerList.get(i).getSessions().get(j).getAllowAllAge());
                                    session.setVaccine(centerList.get(i).getSessions().get(j).getVaccine());
                                    session.setSlots(centerList.get(i).getSessions().get(j).getSlots());
                                    session.setAvailableCapacityDose1(centerList.get(i).getSessions().get(j).getAvailableCapacityDose1());
                                    session.setAvailableCapacityDose2(centerList.get(i).getSessions().get(j).getAvailableCapacityDose2());


                                    List<VaccineFee> vaccineFees



                                    Log.e("aaaaaa++",centerList.get(j).getSessions().toString());

                                }
                                filterSessionList.add(session)
                            }

                            filterCenterList.add(center);
                        }*/



                    }

                    //Toast.makeText(CovidSlotsAvailabilityActivity.this, "200", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(CovidSlotsAvailabilityActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 403){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(CovidSlotsAvailabilityActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(CovidSlotsAvailabilityActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidTimeSlotDTO> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(CovidSlotsAvailabilityActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void getSlotAvailabilityDetailsByDistricts() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(CovidSlotsAvailabilityActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidTimeSlotDTO> call = retroFitInterface.getAppointmentListByDistricts("Bearer "+authTok,Constant.USER_AGENT,dis_code,date);

        call.enqueue(new Callback<CovidTimeSlotDTO>() {
            @Override
            public void onResponse(Call<CovidTimeSlotDTO> call, Response<CovidTimeSlotDTO> response) {
                progressLog.dismiss();

                CovidTimeSlotDTO body = response.body();

                if (response.code() == 200) {

                    if (body.getCenters().size()==0){
                        no_slots_available_LinearLayout.setVisibility(View.VISIBLE);
                        center_Name_RV.setVisibility(View.GONE);
                    } else {
                        no_slots_available_LinearLayout.setVisibility(View.GONE);
                        center_Name_RV.setVisibility(View.VISIBLE);
                        centerList = body.getCenters();
                        setAdapter();
                    }

                    //Toast.makeText(CovidSlotsAvailabilityActivity.this, "200", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(CovidSlotsAvailabilityActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 403){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(CovidSlotsAvailabilityActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(CovidSlotsAvailabilityActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidTimeSlotDTO> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(CovidSlotsAvailabilityActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onListItem(View view, int adapterPosition) {


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}