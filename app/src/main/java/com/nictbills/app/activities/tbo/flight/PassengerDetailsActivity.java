package com.nictbills.app.activities.tbo.flight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.flight.model.farequote.FareQuoteModel;
import com.nictbills.app.activities.tbo.flight.model.farerules.Error;
import com.nictbills.app.activities.tbo.flight.model.farerules.FareRule;
import com.nictbills.app.activities.tbo.flight.model.farerules.FlightFareRules;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;

import com.nictbills.app.utils.Util;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerDetailsActivity extends BaseActivity {

    String[] countrylist = {"India", "America", "japan", "Iran"};
    Spinner spCountry;
    CheckBox checkBoxpusher;
    TextInputEditText editname, editlname, editpush1, usermobile, useremail, useraddress, usercity;
    MaterialButton btnselelctseat, btnbookingreview, btncontinue;
    //DataBindingUtil
    boolean isAllFieldsChecked = false;
    boolean pusherchecked = false;
    RadioGroup rg;
    String gendertype, getname, mobileNumber, getllctype, countryname, gettoken, gettrace, getresultindex, getchild_count, getadult_count;
    SharedPreferences shared, sharedPreferences;
    RetrofitInterface retroFitInterface;
    HashMap<String, String> param;
    HashMap<String, String> paramfarequte;
    TextView farerules_details;
    String typepage = "";
    String MobilePattern = "[0-9]{10}";
    ImageView backimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);
        spCountry = findViewById(R.id.spCountry);
        backimg = findViewById(R.id.backArrow_IMG);

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        checkBoxpusher = findViewById(R.id.check_push);
        editpush1 = findViewById(R.id.pushbooking_no1);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        farerules_details = findViewById(R.id.faredetails_rules);
        btnselelctseat = findViewById(R.id.btnSelectSeats);
        btnbookingreview = findViewById(R.id.BookingRewiew);
        btncontinue = findViewById(R.id.btncontinue);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettoken = shared.getString("Tokenid", "");
        gettrace = shared.getString("TraceId", "");
        getresultindex = shared.getString("ResultIndex", "");
        getadult_count = shared.getString("Adultno", "");
        getchild_count = shared.getString("Childno", "");
        getllctype = shared.getString("LLCType", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");

        Log.e("...", "yyy" + getllctype);
        if (getllctype.equals("false")) {
            btncontinue.setVisibility(View.VISIBLE);
        } else {
            btnselelctseat.setVisibility(View.VISIBLE);

        }
        retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        param = new HashMap<>();
        param.put("EndUserIp", "");
        param.put("TokenId", gettoken);
        param.put("TraceId", gettrace);
        param.put("ResultIndex", getresultindex);

        paramfarequte = new HashMap<>();
        paramfarequte.put("EndUserIp", "");
        paramfarequte.put("TokenId", gettoken);
        paramfarequte.put("TraceId", gettrace);
        paramfarequte.put("ResultIndex", getresultindex);
        paramfarequte.put("mobileNo", mobileNumber);
        getfarerules(param);
//
//
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countrylist);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(ad);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // do something upon option selection
                countryname = countrylist[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        checkBoxpusher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    editpush1.setVisibility(View.VISIBLE);
                    pusherchecked = true;

                } else {
                    editpush1.setVisibility(View.GONE);
                    pusherchecked = true;


                }

            }
        });

        btnselelctseat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pusherchecked == true) {
                    if(editpush1.getText().toString().matches(MobilePattern))
                    {
                        typepage = "seat";
                        getfarequotre(paramfarequte);
                    }
                    else
                    {
                        Toast.makeText(PassengerDetailsActivity.this, "plz Add mobile no", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PassengerDetailsActivity.this, "check pusher and Add Mobile no", Toast.LENGTH_SHORT).show();

                }

            }
        });
//        btnbookingreview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                isAllFieldsChecked = CheckAllFields();
////                if (isAllFieldsChecked) {
////
////                }
//
//                if (pusherchecked==true)
//                {
////
//                    if (editpush1.getText().toString().trim().length()==0)
//                    {
//                        Toast.makeText(PassengerDetailsActivity.this, "plz Add mobile no", Toast.LENGTH_SHORT).show();
//
//                    }
//                    else
//                    {
//
//                        SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = shared.edit();
//                        editor.putString("pusher",editpush1.getText().toString().trim());
//                        editor.putString("Country",countryname);
//                        editor.apply();
//                        Intent intent = new Intent(PassengerDetailsActivity.this, BookingReviewActivity.class);
//                        startActivity(intent);
//
//                    }
//
//                }
//                else
//                {
//                    Toast.makeText(PassengerDetailsActivity.this, "check pusher and Add Mobile no", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pusherchecked == true) {


                    if(editpush1.getText().toString().matches(MobilePattern))
                    {
                        typepage = "book";
                        getfarequotre(paramfarequte);
                    }
                    else
                    {
                        Toast.makeText(PassengerDetailsActivity.this, "plz Add mobile no", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(PassengerDetailsActivity.this, "check pusher and Add Mobile no", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void getfarerules(HashMap<String, String> param) {
        Log.e("nictfarerules", "onResponse: ");
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<FlightFareRules> call = retroFitInterface.getFarerules(param);
        call.enqueue(new Callback<FlightFareRules>() {
            @Override
            public void onResponse(Call<FlightFareRules> call, Response<FlightFareRules> response) {
                progressDialogDismiss();
                Error error= new Error();
                if (error.getErrorCode()==0)
                {
                    FlightFareRules flightFareRules = response.body();

                    if (flightFareRules.getResponse().getFareRules().size() > 0)
                    {
                        for (int i = 0; i < flightFareRules.getResponse().getFareRules().size(); i++) {

                            flightFareRules.getResponse().getFareRules().get(i).getFareRuleDetail();

//                        String lineSep = System.getProperty("line.separator");
//                        String yourString= flightFareRules.getResponse().getFareRules().get(i).getFareRuleDetail();
//                        yourString= yourString.replaceAll("<br />", lineSep);

                            String yourString = flightFareRules.getResponse().getFareRules().get(i).getFareRuleDetail();
                            Log.e("farterulesresultbb", "onResponse: " + flightFareRules.getResponse().getFareRules().get(i).getFareRuleDetail());
                            String finaldata = Jsoup.parse(yourString).text();
                            farerules_details.setText(finaldata);

                        }
                    } else {
                        Toast.makeText(PassengerDetailsActivity.this, "data not found", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<FlightFareRules> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: ");
                Log.e("nictfarerules", "faile: " + t.toString());
            }
        });
    }

    public void getfarequotre(HashMap<String, String> paramfarequte) {
        Log.e("nictfarequote", "onResponse: ");
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<FareQuoteModel> call = retroFitInterface.getFareQuote(paramfarequte);
        call.enqueue(new Callback<FareQuoteModel>() {
            @Override
            public void onResponse(Call<FareQuoteModel> call, Response<FareQuoteModel> response) {
                progressDialogDismiss();
                FareQuoteModel fareQuoteModel = response.body();
//                com.nictbills.app.activities.health_id_abdm.dto.tbo.farequote.Error error = new com.nictbills.app.activities.health_id_abdm.dto.tbo.farequote.Error();
                com.nictbills.app.activities.tbo.flight.model.farequote.Error  error= new  com.nictbills.app.activities.tbo.flight.model.farequote.Error();
                if (error.getErrorCode() == 0) {
//                    Toast.makeText(PassengerDetailsActivity.this, "CLICKQUOTE", Toast.LENGTH_SHORT).show();

                    Log.e("getpassenger", "id.." + fareQuoteModel.getResponse().getPassenger_id());
                    SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    Gson gson = new Gson();
                    editor.putString("pusher", editpush1.getText().toString().trim());
                    editor.putString("Country", countryname);
                    editor.putString("passengerid", String.valueOf(fareQuoteModel.getResponse().getPassenger_id()));
                    String json = gson.toJson(fareQuoteModel); // myObject - instance of MyObject
                    editor.putString("FarequoteResponse", json);

                    if (typepage.equals("seat")) {
                        editor.putString("type", "seat");

//                        Intent intent = new Intent(PassengerDetailsActivity.this, SeatViewActivity.class);
//                        startActivity(intent);
//                        Intent intent = new Intent(PassengerDetailsActivity.this, AddPassangerActivity.class);
                        Intent intent = new Intent(PassengerDetailsActivity.this, AddFlightMemberActivity.class);
                        startActivity(intent);
                    } else {
                        editor.putString("type", "book");
//                        Intent intent = new Intent(PassengerDetailsActivity.this, FlightTicketActivity.class);
//                        startActivity(intent);
//                        Intent intent = new Intent(PassengerDetailsActivity.this, AddPassangerActivity.class);
                        Intent intent = new Intent(PassengerDetailsActivity.this, AddFlightMemberActivity.class);
                        startActivity(intent);
                    }

                    editor.apply();
                } else {

                }


            }

            @Override
            public void onFailure(Call<FareQuoteModel> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.toString());

            }
        });
    }

}