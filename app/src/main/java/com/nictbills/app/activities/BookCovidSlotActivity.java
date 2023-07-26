package com.nictbills.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.DistrictsAdapter;
import com.nictbills.app.adapter.StateAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.CovidDistrictListDTO;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.CovidStatesListDTO;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.District;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.State;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookCovidSlotActivity extends AppCompatActivity {

    private LinearLayout search_by_pi_code_LL,search_by_district_LL,search_by_district_state_show_hide_LL,pincode_show_hide_LL;
    private ImageView backArrow_IMG;
    private EditText pincode_TV;
    private RadioGroup radioDose;
    private int dose=1;
    private Button search_BTN;
    private String beneficiaryId,status,appointmentID;
    private Spinner state_spinner,districts_spinner;
    private String authTok,stateName,sendStateId,sendDistrictId,districtsName;
    private RetrofitInterface retroFitInterface;
    private SharedPreferences sharedPreferences;
    private StateAdapter stateAdapter;
    private DistrictsAdapter districtsAdapter;
    private List<State> stateList;
    private List<District> districtsList;
    private int stateId, districtsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_covid_slot);

        search_by_pi_code_LL = findViewById(R.id.search_by_pi_code_LL);
        search_by_district_LL = findViewById(R.id.search_by_district_LL);
        search_by_district_state_show_hide_LL = findViewById(R.id.search_by_district_state_show_hide_LL);
        pincode_show_hide_LL = findViewById(R.id.pincode_show_hide_LL);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);

        radioDose = findViewById(R.id.radioDose);
        search_BTN = findViewById(R.id.search_BTN);
        pincode_TV = findViewById(R.id.pincode_TV);
        state_spinner = findViewById(R.id.state_spinner);
        districts_spinner = findViewById(R.id.districts_spinner);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        authTok = sharedPreferences.getString("cred_vaccine", "");


        status = getIntent().getStringExtra("STATUS");


        if (status.equalsIgnoreCase("RESCHEDULE")){

            appointmentID = getIntent().getStringExtra("APPOINTMENT_ID");


        } else {
            beneficiaryId = getIntent().getStringExtra("BENEFICIARY_ID");
        }



        search_by_pi_code_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_by_pi_code_LL.setBackgroundResource(R.drawable.white_border_button);
                search_by_district_LL.setBackgroundResource(0);
                search_by_district_LL.setPadding(5, 10, 5, 10);
                search_by_pi_code_LL.setPadding(5, 10, 5, 10);
                search_by_district_state_show_hide_LL.setVisibility(View.GONE);
                pincode_show_hide_LL.setVisibility(View.VISIBLE);
            }
        });

        search_by_district_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_by_district_LL.setBackgroundResource(R.drawable.white_border_button);
                search_by_pi_code_LL.setBackgroundResource(0);
                search_by_district_LL.setPadding(5, 10, 5, 10);
                search_by_pi_code_LL.setPadding(5, 10, 5, 10);
                search_by_district_state_show_hide_LL.setVisibility(View.VISIBLE);
                pincode_show_hide_LL.setVisibility(View.GONE);
            }
        });

        getSlotAvailabilityDetailsByState();

        radioDose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioDose_1:
                        dose=1;
                        // set text North for your textview here
                        break;

                    case R.id.radioDose_2:
                        dose=2;
                        // do something
                        break;
                }

            }
        });


        search_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pincode_show_hide_LL.getVisibility() == View.VISIBLE) {

                    if (isEmpty(pincode_TV)) {

                        Snackbar.make(v, "Pin code is required", Snackbar.LENGTH_SHORT).show();

                    } else {


                        Log.e("aaaa","pincode");

                        if (status.equalsIgnoreCase("RESCHEDULE")){
                            Intent intent = new Intent(BookCovidSlotActivity.this,CovidSlotsAvailabilityActivity.class);
                            intent.putExtra("PIN",pincode_TV.getText().toString());
                            intent.putExtra("DOSE",dose);
                            intent.putExtra("DIS_CODE","DIS_CODE");
                            intent.putExtra("DIS_NAME","DIS_NAME");
                            intent.putExtra("APPOINTMENT_ID",appointmentID);
                            intent.putExtra("STATUS","RESCHEDULE");
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(BookCovidSlotActivity.this,CovidSlotsAvailabilityActivity.class);
                            intent.putExtra("PIN",pincode_TV.getText().toString());
                            intent.putExtra("DOSE",dose);
                            intent.putExtra("DIS_CODE","DIS_CODE");
                            intent.putExtra("DIS_NAME","DIS_NAME");
                            intent.putExtra("STATUS","SCHEDULE");
                            intent.putExtra("BENEFICIARY_ID",beneficiaryId);
                            startActivity(intent);
                        }



                    }
                } else {

                        if (stateId==-1){

                            Snackbar.make(v, "State is required", Snackbar.LENGTH_SHORT).show();

                        }else if (districtsId==-1){
                            Snackbar.make(v, "District is required", Snackbar.LENGTH_SHORT).show();
                        }else {
                            Log.e("bbbbb","state");
                            if (status.equalsIgnoreCase("RESCHEDULE")){
                                Intent intent = new Intent(BookCovidSlotActivity.this,CovidSlotsAvailabilityActivity.class);
                                intent.putExtra("PIN","PIN");
                                intent.putExtra("DOSE",dose);
                                intent.putExtra("DIS_CODE",sendDistrictId);
                                intent.putExtra("DIS_NAME",districtsName);
                                intent.putExtra("APPOINTMENT_ID",appointmentID);
                                intent.putExtra("STATUS","RESCHEDULE");
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(BookCovidSlotActivity.this,CovidSlotsAvailabilityActivity.class);
                                intent.putExtra("PIN","PIN");
                                intent.putExtra("DOSE",dose);
                                intent.putExtra("DIS_CODE",sendDistrictId);
                                intent.putExtra("DIS_NAME",districtsName);
                                intent.putExtra("STATUS","SCHEDULE");
                                intent.putExtra("BENEFICIARY_ID",beneficiaryId);
                                startActivity(intent);
                            }


                          /*  Intent intent = new Intent(BookCovidSlotActivity.this,CovidSlotsAvailabilityActivity.class);
                            intent.putExtra("PIN","PIN");
                            intent.putExtra("DOSE",dose);
                            intent.putExtra("DIS_CODE",sendDistrictId);
                            intent.putExtra("DIS_NAME",districtsName);
                            intent.putExtra("BENEFICIARY_ID",beneficiaryId);
                            startActivity(intent);*/
                        }
                    }

                    // Either gone or invisible


            }
        });


        districts_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                District districtstDTO = (District) districtsAdapter.getItem(position);
                // Here you can do the action you want to...
                districtsId = districtstDTO.getDistrictId();
                districtsName=  districtstDTO.getDistrictName();
                Log.e("Districts++",districtsId+""+"++++"+districtsName);
                sendDistrictId=String.valueOf(districtsId);
                //Toast.makeText(getActivity(), "ID: " + user.getSlot() + "\nName: " + user.getSlot_id(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                State stateListDTO = (State) stateAdapter.getItem(position);
                // Here you can do the action you want to...
                stateId = stateListDTO.getStateId();
                stateName=  stateListDTO.getStateName();
                sendStateId=String.valueOf(stateId);

                if (stateId==-1){
                    districts_spinner.setVisibility(View.GONE);
                } else {
                    getSlotAvailabilityDetailsByDistricts();
                }
                //Toast.makeText(getActivity(), "ID: " + user.getSlot() + "\nName: " + user.getSlot_id(),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookCovidSlotActivity.this,BeneficiaryListActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BookCovidSlotActivity.this,BeneficiaryListActivity.class);
        startActivity(intent);
        finish();
    }


    private void getSlotAvailabilityDetailsByState() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BookCovidSlotActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidStatesListDTO> call = retroFitInterface.getAppointmentListByState("Bearer "+authTok,Constant.USER_AGENT);

        call.enqueue(new Callback<CovidStatesListDTO>() {
            @Override
            public void onResponse(Call<CovidStatesListDTO> call, Response<CovidStatesListDTO> response) {
                progressLog.dismiss();

                CovidStatesListDTO body = response.body();

                if (response.code() == 200) {

                    stateList = body.getStates();

                    stateAdapter = new StateAdapter(BookCovidSlotActivity.this,stateList);

                    state_spinner.setAdapter(stateAdapter);

                    //Toast.makeText(CovidSlotsAvailabilityActivity.this, "200", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BookCovidSlotActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BookCovidSlotActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(BookCovidSlotActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidStatesListDTO> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BookCovidSlotActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void getSlotAvailabilityDetailsByDistricts() {

        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(BookCovidSlotActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidDistrictListDTO> call = retroFitInterface.getAppointmentListByDistricts("Bearer "+authTok,Constant.USER_AGENT,sendStateId);

        call.enqueue(new Callback<CovidDistrictListDTO>() {
            @Override
            public void onResponse(Call<CovidDistrictListDTO> call, Response<CovidDistrictListDTO> response) {
                progressLog.dismiss();

                CovidDistrictListDTO body = response.body();

                if (response.code() == 200) {

                    districtsList = body.getDistricts();

                    if (districtsList.size()==0){

                        Toast.makeText(BookCovidSlotActivity.this, "here", Toast.LENGTH_SHORT).show();

                    }else {
                        districts_spinner.setVisibility(View.VISIBLE);
                        districtsAdapter = new DistrictsAdapter(BookCovidSlotActivity.this,districtsList);
                        districts_spinner.setAdapter(districtsAdapter);
                    }

                    //Toast.makeText(CovidSlotsAvailabilityActivity.this, "200", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(BookCovidSlotActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(BookCovidSlotActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(BookCovidSlotActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidDistrictListDTO> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(BookCovidSlotActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


}