package com.nictbills.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.CovidGendersAdapter;
import com.nictbills.app.adapter.ListOfCovidIdPhotoAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.covidPhotoId.IdPhoto;
import com.nictbills.app.activities.health_id_abdm.dto.covidPhotoId.Type;
import com.nictbills.app.activities.health_id_abdm.dto.covid_genders_dto.CovidGendersDetails;
import com.nictbills.app.activities.health_id_abdm.dto.covid_genders_dto.Gender;
import com.nictbills.app.activities.health_id_abdm.dto.covid_registration.CovidRegistrationRequest;
import com.nictbills.app.activities.health_id_abdm.dto.covid_registration.CovidRegistrationResponse;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewCovidBeneficiaryActivity extends AppCompatActivity implements OnClickRecyclerViewInterface {

    private EditText idPhoto_EditText,proof_id_number_EditText,beneficiary_name_EditText,birth_year_EditText;
    private BottomSheetDialog bottomSheetDialog;
    private RecyclerView.LayoutManager layoutManager;
    private RetrofitInterface retroFitInterface;
    private List<Type> searchResults;
    private List<Gender> searchGenderResults;
    private String authTok,credential,mobileNumber,beneficiary_Id,comeFrom;
    private RecyclerView covid_id_recy;
    private SharedPreferences sharedPreferences;
    private CovidGendersAdapter gendersAdapter;
    private RecyclerView radio_recyclerView;
    private Button submit_BTN;
    private ImageView backArrow_IMG;
    private int genderId=-1,photoId=-1;
    private LinearLayout layout_visibile_LinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_covid_beneficiary);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");
        authTok = sharedPreferences.getString("cred_vaccine", "");

        Log.e("autthhh++1234",authTok);

        Intent intent = getIntent();

        comeFrom = intent.getStringExtra("COME");


        idPhoto_EditText = findViewById(R.id.idPhoto_EditText);
        radio_recyclerView = findViewById(R.id.radio_recyclerView);
        submit_BTN = findViewById(R.id.submit_BTN);
        proof_id_number_EditText = findViewById(R.id.proof_id_number_EditText);
        beneficiary_name_EditText = findViewById(R.id.beneficiary_name_EditText);
        birth_year_EditText = findViewById(R.id.birth_year_EditText);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        layout_visibile_LinearLayout = findViewById(R.id.layout_visibile_LinearLayout);
        //shareLinearLayout = mybottomSheet.findViewById(R.id.shareLinearLayout);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.covid_photoid_bottom_sheet);

        covid_id_recy = bottomSheetDialog.findViewById(R.id.covid_id_recy);
        covid_id_recy.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(AddNewCovidBeneficiaryActivity.this);
        covid_id_recy.setLayoutManager(layoutManager);
        covid_id_recy.setItemAnimator(new DefaultItemAnimator());


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        radio_recyclerView.setLayoutManager(gridLayoutManager);
        getGendersDetails();

        idPhoto_EditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIdPhotoDetails();
            }
        });


        submit_BTN.setOnClickListener(new View.OnClickListener() {
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

                    if (isEmpty(idPhoto_EditText)) {

                        Snackbar.make(v, getString(R.string.kindly_select_your_photo_Id), Snackbar.LENGTH_SHORT).show();

                    } else if (photoId==-1) {

                        Snackbar.make(v, getString(R.string.kindly_select_your_photo_Id), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(proof_id_number_EditText)){

                        Snackbar.make(v, getString(R.string.kindly_enter_your_photo_Id_number), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(beneficiary_name_EditText)){

                        Snackbar.make(v, getString(R.string.kindly_enter_the_name), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(birth_year_EditText)){

                        Snackbar.make(v, getString(R.string.kindly_enter_birth_year), Snackbar.LENGTH_SHORT).show();

                    } else if (!validBirthYear(birth_year_EditText.getText().toString())){

                        Snackbar.make(v, getString(R.string.kindly_enter_correct_birth_year), Snackbar.LENGTH_SHORT).show();

                    }  else if (genderId==-1){

                        Snackbar.make(v, getString(R.string.kindly_select_gender), Snackbar.LENGTH_SHORT).show();

                    } else  {

                        CovidRegistrationRequest covidRegistrationRequest = new CovidRegistrationRequest();
                        covidRegistrationRequest.setPhoto_id_type(photoId);
                        covidRegistrationRequest.setPhoto_id_number(proof_id_number_EditText.getText().toString());
                        covidRegistrationRequest.setName(beneficiary_name_EditText.getText().toString());
                        covidRegistrationRequest.setBirth_year(birth_year_EditText.getText().toString());
                        covidRegistrationRequest.setGender_id(genderId);
                        covidRegistrationRequest.setComorbidity_ind("Y");
                        covidRegistrationRequest.setConsent_version("1");
                        covidNewBeneficiary(covidRegistrationRequest);
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

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

    }

    private boolean validBirthYear(String str){
        return str.trim().length()!=0 && str!=null && str.length()==4 ;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void setAdapter(){
        ListOfCovidIdPhotoAdapter adapter = new ListOfCovidIdPhotoAdapter(AddNewCovidBeneficiaryActivity.this, searchResults,this);
        covid_id_recy.setAdapter(adapter);
    }

    private void getIdPhotoDetails() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(AddNewCovidBeneficiaryActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<IdPhoto> call = retroFitInterface.getCovidIdDetails("Bearer "+authTok,Constant.USER_AGENT);

        call.enqueue(new Callback<IdPhoto>() {
            @Override
            public void onResponse(Call<IdPhoto> call, Response<IdPhoto> response) {
                progressLog.dismiss();

                IdPhoto body = response.body();

                if (response.code() == 200) {

                    searchResults = body.getTypes();
                    setAdapter();
                    bottomSheetDialog.show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(AddNewCovidBeneficiaryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddNewCovidBeneficiaryActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(AddNewCovidBeneficiaryActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<IdPhoto> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void getGendersDetails() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(AddNewCovidBeneficiaryActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidGendersDetails> call = retroFitInterface.getCovidGendersDetails("Bearer "+authTok,Constant.USER_AGENT);

        call.enqueue(new Callback<CovidGendersDetails>() {
            @Override
            public void onResponse(Call<CovidGendersDetails> call, Response<CovidGendersDetails> response) {
                progressLog.dismiss();

                CovidGendersDetails covidGendersDetails = response.body();

                if (response.code() == 200) {

                    searchGenderResults = covidGendersDetails.getGenders();

                    gendersAdapter = new CovidGendersAdapter(AddNewCovidBeneficiaryActivity.this,searchGenderResults,AddNewCovidBeneficiaryActivity.this);

                   // radio_gender.setAdapter(gendersAdapter); // Set the custom adapter to the spinner
                    radio_recyclerView.setAdapter(gendersAdapter);

                } else if (response.code() == 500) {

                    new SweetAlertDialog(AddNewCovidBeneficiaryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddNewCovidBeneficiaryActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(AddNewCovidBeneficiaryActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidGendersDetails> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void covidNewBeneficiary(CovidRegistrationRequest registrationRequest) {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(AddNewCovidBeneficiaryActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.COVID_URL).create(RetrofitInterface.class);

        Call<CovidRegistrationResponse> call = retroFitInterface.newCovidRegistration("Bearer "+authTok,Constant.USER_AGENT,registrationRequest);

        call.enqueue(new Callback<CovidRegistrationResponse>() {
            @Override
            public void onResponse(Call<CovidRegistrationResponse> call, Response<CovidRegistrationResponse> response) {
                progressLog.dismiss();

                CovidRegistrationResponse body = response.body();

                if (response.code() == 200) {

                    layout_visibile_LinearLayout.setVisibility(View.GONE);
                    beneficiary_successful_PonUpDialog();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("is_new", "N");
                    editor.putBoolean("covid_status", true);
                    editor.apply();
                    editor.commit();

                    beneficiary_Id = body.getBeneficiary_reference_id();
                   // Toast.makeText(AddNewCovidBeneficiaryActivity.this, "added", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(AddNewCovidBeneficiaryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("covid_status", false);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(AddNewCovidBeneficiaryActivity.this,VaccineDashboardActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.code() == 409){

                    Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.already_registered_or_cannot_register), Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CovidRegistrationResponse> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(AddNewCovidBeneficiaryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    @Override
    public void onListItem(View view, int adapterPosition) {

       // amount = txnLists.get(adapterPosition).getAmount();
        switch (view.getId()) {

            case R.id.photo_id_name_TV:
                searchResults.get(adapterPosition).getId();
                idPhoto_EditText.setText(searchResults.get(adapterPosition).getType());
                photoId = searchResults.get(adapterPosition).getId();
                //Log.e("l4444",searchResults.get(adapterPosition).getId()+"");
                bottomSheetDialog.dismiss();
                break;

            case R.id.genderRadioButton:
                genderId = searchResults.get(adapterPosition).getId();
                //Log.e("listttt+++",searchGenderResults.get(adapterPosition).getId()+"");
                bottomSheetDialog.dismiss();
                break;

        }
    }

    @Override
    public void onBackPressed() {

        if (comeFrom.equalsIgnoreCase("ADDMORE")){

            Intent intent = new Intent(AddNewCovidBeneficiaryActivity.this,BeneficiaryListActivity.class);
            startActivity(intent);
            finish();

        }else {

            new AlertDialog.Builder(AddNewCovidBeneficiaryActivity.this)
                    //.setTitle("Really Exit?")
                    .setMessage(getString(R.string.are_you_sure_you_want_to_exit))
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(AddNewCovidBeneficiaryActivity.this,DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).create().show();

        }


    }

    private void beneficiary_successful_PonUpDialog() {
        final Dialog successDialog;
        successDialog = new Dialog(AddNewCovidBeneficiaryActivity.this, R.style.Theme_Dialog);
        successDialog.setContentView(R.layout.beneficiary_added_successful_dialog);
        successDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        successDialog.setCancelable(false);

        Button ok_Button = successDialog.findViewById(R.id.ok_Button);

        ok_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                successDialog.dismiss();

                Intent intent = new Intent(AddNewCovidBeneficiaryActivity.this,BeneficiaryListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        successDialog.show();
    }

}