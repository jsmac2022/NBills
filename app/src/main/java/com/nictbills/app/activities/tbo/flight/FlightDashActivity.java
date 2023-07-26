package com.nictbills.app.activities.tbo.flight;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;

import com.nictbills.app.activities.tbo.flight.model.air_search.air_search_request.SearchFlightRequest;
import com.nictbills.app.activities.tbo.flight.model.air_search.air_search_request.Segment;
import com.nictbills.app.activities.tbo.flight.model.auth_token.AuthTokenTBORequest;
import com.nictbills.app.activities.tbo.flight.model.auth_token.Error;
import com.nictbills.app.activities.tbo.flight.model.auth_token.auth_request.AuthRequest;
import com.nictbills.app.activities.tbo.flight.model.citylist.CityModel;
import com.nictbills.app.activities.tbo.flight.model.citylist.Data;
import com.nictbills.app.activities.tbo.flight.model.citylist.FlightCityList;
import com.nictbills.app.activities.tbo.flight.model.flightsearch.FlightSearchModel;
import com.nictbills.app.activities.tbo.hotel.HotelDashBoardActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.ApiClients;
import com.nictbills.app.apiclient.RetrofitClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightDashActivity extends BaseActivity {

    private ImageView backArrow_IMG;
    RetrofitInterface retroFitInterface;
    private Button search_BTN, search_vhistory;
    private EditText depart_EDT, origin, destination;
    private EditText return_EDT;
    private Spinner spAnyTime, spReturn, spAdult, spChild, spInfant, spClass;
    private LinearLayout llReturn, llOneWay;
    private TextView tvReturn, tvOneway;
    private RelativeLayout rlReturn, rlOneWay, rlEnterOrigin;
    FlightSearchModel flightSearchModel;
    String[] timinglist = {"anyTime", "Morning", "Afternoon", "Evening", "Night"};
    String[] adultlist = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] childrenlist = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    String[] infantlist = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    String[] classlist = {"any", "Economy", "PremiumEconomy", "Business", "First"};
    private String authToken, departSend;
    private String returndepartSend;
    private String agencyid, endUserIp, getorigin, getdestination, adultCount, children, infant, clas, timing;
    Context context;
    CheckBox directFlit;
    String directCheck = "false";
    private SmartMaterialSpinner spProvince;
    private SmartMaterialSpinner to_spinner;
    //    private List<DataResponse> provinceList;
//    private List<Data> provinceList;
    private ArrayList<Data> citylist = new ArrayList<>();
    SharedPreferences shared;
    HashMap<String, String> param;
    CityOrginAdapter cityOrginAdapter;
    String layouttype = "", get_final_origin , get_final_destination , get_final_org, get_final_dest;
    String todaydate = "";
    String JOUNEYTYPE = "1";
    int jouneycount = 1;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_dash);
        context = getApplicationContext();
        spProvince = findViewById(R.id.from_spinner);
        to_spinner = findViewById(R.id.to_spinner);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        search_BTN = findViewById(R.id.search_BTN);
        search_vhistory = findViewById(R.id.view_fhistory);
        depart_EDT = findViewById(R.id.depart_EDT);
        origin = findViewById(R.id.origin);
        destination = findViewById(R.id.destination);
        return_EDT = findViewById(R.id.return_EDT);
        spAnyTime = findViewById(R.id.spAnyTime);
        spReturn = findViewById(R.id.spReturn);
        spAdult = findViewById(R.id.spAdult);
        spChild = findViewById(R.id.spChild);
        spInfant = findViewById(R.id.spInfant);
        spClass = findViewById(R.id.spClass);
        llOneWay = findViewById(R.id.llOneWay);
        llReturn = findViewById(R.id.llReturn);
        tvOneway = findViewById(R.id.tvOneway);
        tvReturn = findViewById(R.id.tvReturn);
        rlOneWay = findViewById(R.id.rlOneWay);
        rlReturn = findViewById(R.id.rlReturn);
        directFlit = findViewById(R.id.directFlit);
        shared = getSharedPreferences("nict", MODE_PRIVATE);
        spinner();
        param = new HashMap<>();
        param.put("EndUserIp", "");

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        todaydate = dateFormat.format(today);
        depart_EDT.setText(todaydate);

        clickEvent();
    }

    private void spinner() {
        directFlit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    directCheck = "true";
                } else {
                    directCheck = "false";
                }
            }
        });
        ArrayAdapter spReturnd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timinglist);
        spReturnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAnyTime.setAdapter(spReturnd);
        spAnyTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timing = timinglist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        ArrayAdapter spReturnAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timinglist);
        spReturnAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReturn.setAdapter(spReturnAd);
        spReturn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timing = timinglist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        ArrayAdapter spAdultAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, adultlist);
        spAdultAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAdult.setAdapter(spAdultAd);
        spAdult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adultCount = adultlist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        ArrayAdapter spChildAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, childrenlist);
        spChildAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spChild.setAdapter(spChildAd);
        spChild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                children = childrenlist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        ArrayAdapter spInfantad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, infantlist);
        spInfantad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInfant.setAdapter(spInfantad);
        spInfant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                infant = infantlist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        ArrayAdapter spClassAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, classlist);
        spClassAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClass.setAdapter(spClassAd);
        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clas = classlist[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

    }

    private void clickEvent() {
        getTBOFlight(param);

//        authToken="c096d57b-beee-4bba-8407-57861205f519";
//        agencyid="57237";
//        SharedPreferences.Editor editor = shared.edit();
//        Gson gson = new Gson();
//        editor.putString("Tokenid", "c096d57b-beee-4bba-8407-57861205f519");
//        editor.putString("Agencyid", "57237");
//        editor.apply();

        origin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(FlightDashActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.search_origincity);
                RecyclerView rvshowlist = (RecyclerView) dialog.findViewById(R.id.rv_citylist);
                TextView datanotfund = (TextView) dialog.findViewById(R.id.datanotfound);
                EditText edrsearch = (EditText) dialog.findViewById(R.id.et_search_latpanel);
                dialog.show();
                layouttype = "orgin";
                edrsearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() >= 2) {
                            datanotfund.setVisibility(View.GONE);
                            rvshowlist.setVisibility(View.VISIBLE);
                            filter(s.toString());
                        } else {
                            rvshowlist.setVisibility(View.GONE);
                            datanotfund.setVisibility(View.VISIBLE);
                        }

                        Log.e("1", "on onTextChanged" + s.toString());

                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        Log.e("1", "on beforeTextChanged" + s.toString());
                        if (s.length() >= 2) {
                            datanotfund.setVisibility(View.GONE);
                            rvshowlist.setVisibility(View.VISIBLE);

                            filter(s.toString());
                        } else {
                            rvshowlist.setVisibility(View.GONE);

                            datanotfund.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.e("1", "on afterTextChanged" + s.toString());
                        if (s.length() >= 2) {
                            datanotfund.setVisibility(View.GONE);
                            rvshowlist.setVisibility(View.VISIBLE);

                            filter(s.toString());
                        } else {
                            rvshowlist.setVisibility(View.GONE);
                            datanotfund.setVisibility(View.VISIBLE);
                        }
                    }
                });
                citylist.clear();
                progressDialogShow();
                RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
                Call<FlightCityList> call = retroFitInterface.citiList();
                call.enqueue(new Callback<FlightCityList>() {
                    @Override
                    public void onResponse(Call<FlightCityList> call, Response<FlightCityList> response) {
                        progressDialogDismiss();
                        FlightCityList flightCityList = response.body();

//                        for (int j = 0; j < flightCityList.getData().size(); j++) {
//
//                            Data data = new Data();
//                            data.setCityName(flightCityList.getData().get(j).getCityName());
//                            data.setCityCodeWord(flightCityList.getData().get(j).getCityCodeWord());
//                            data.setCityCode(flightCityList.getData().get(j).getCityCode());
//                            data.setCountryName(flightCityList.getData().get(j).getCountryName());
//                            data.setCountryCode(flightCityList.getData().get(j).getCountryCode());
//
//                            citylist.add(data);
//                        }
//
//                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                        rvshowlist.setLayoutManager(mLayoutManager);
//                        cityOrginAdapter = new CityOrginAdapter(getApplicationContext(), citylist, dialog);
//                        rvshowlist.setAdapter(cityOrginAdapter);

                        if (response.code() == 200) {
                            if (flightCityList.getData() == null) {
                                Toast.makeText(FlightDashActivity.this, flightCityList.getMessages(), Toast.LENGTH_SHORT).show();
                            } else {
                                for (int j = 0; j < flightCityList.getData().size(); j++) {
                                    Data data = new Data();
                                    data.setCityName(flightCityList.getData().get(j).getCityName());
                                    data.setCityCodeWord(flightCityList.getData().get(j).getCityCodeWord());
                                    data.setCityCode(flightCityList.getData().get(j).getCityCode());
                                    data.setCountryName(flightCityList.getData().get(j).getCountryName());
                                    data.setCountryCode(flightCityList.getData().get(j).getCountryCode());
                                    citylist.add(data);
                                }
                                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                rvshowlist.setLayoutManager(mLayoutManager);
                                cityOrginAdapter = new CityOrginAdapter(getApplicationContext(), citylist, dialog);
                                rvshowlist.setAdapter(cityOrginAdapter);
                            }


                        } else if (response.code() == 400) {
                            Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<FlightCityList> call, Throwable t) {
                        progressDialogDismiss();
                        Log.e("nictfailur", "onResponse: " + t.toString());

                    }
                });
            }
        });
        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(FlightDashActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.search_origincity);
                RecyclerView rvshowlist = (RecyclerView) dialog.findViewById(R.id.rv_citylist);
                TextView datanotfund = (TextView) dialog.findViewById(R.id.datanotfound);
                EditText edrsearch = (EditText) dialog.findViewById(R.id.et_search_latpanel);
                dialog.show();
                layouttype = "destination";
                edrsearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() >= 2) {
                            datanotfund.setVisibility(View.GONE);
                            rvshowlist.setVisibility(View.VISIBLE);
                            filter(s.toString());
                        } else {
                            rvshowlist.setVisibility(View.GONE);
                            datanotfund.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        if (s.length() >= 2) {
                            datanotfund.setVisibility(View.GONE);
                            rvshowlist.setVisibility(View.VISIBLE);
                            filter(s.toString());
                        } else {
                            rvshowlist.setVisibility(View.GONE);
                            datanotfund.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() >= 2) {
                            datanotfund.setVisibility(View.GONE);
                            rvshowlist.setVisibility(View.VISIBLE);
                            filter(s.toString());
                        } else {
                            rvshowlist.setVisibility(View.GONE);
                            datanotfund.setVisibility(View.VISIBLE);
                        }

                    }
                });
                citylist.clear();
                progressDialogShow();
                RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
                Call<FlightCityList> call = retroFitInterface.citiList();
                call.enqueue(new Callback<FlightCityList>() {
                    @Override
                    public void onResponse(Call<FlightCityList> call, Response<FlightCityList> response) {
                        progressDialogDismiss();
                        FlightCityList flightCityList = response.body();

                        if (response.code() == 200) {

                            for (int j = 0; j < flightCityList.getData().size(); j++) {
                                Data data = new Data();
                                data.setCityName(flightCityList.getData().get(j).getCityName());
                                data.setCityCodeWord(flightCityList.getData().get(j).getCityCodeWord());
                                data.setCityCode(flightCityList.getData().get(j).getCityCode());
                                data.setCountryName(flightCityList.getData().get(j).getCountryName());
                                data.setCountryCode(flightCityList.getData().get(j).getCountryCode());
                                citylist.add(data);
                            }
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvshowlist.setLayoutManager(mLayoutManager);
                            cityOrginAdapter = new CityOrginAdapter(getApplicationContext(), citylist, dialog);
                            rvshowlist.setAdapter(cityOrginAdapter);


                        } else if (response.code() == 400) {
                            Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<FlightCityList> call, Throwable t) {
                        progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        Log.e("nictfailur", "onResponse: " + t.toString());

                    }
                });
            }
        });
        llOneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JOUNEYTYPE = "1";
                jouneycount = 2;
                return_EDT.setText("");
                llOneWay.setBackgroundResource(R.drawable.border_green);
                llReturn.setBackgroundResource(R.drawable.border_user);
                tvOneway.setTextColor(getColor(R.color.white));
                tvReturn.setTextColor(getColor(R.color.black));
                rlReturn.setVisibility(View.GONE);
            }
        });

        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return_EDT.setText(todaydate);
                JOUNEYTYPE = "2";
                jouneycount = 2;
                llReturn.setBackgroundResource(R.drawable.border_green);
                llOneWay.setBackgroundResource(R.drawable.border_user);
                tvReturn.setTextColor(getColor(R.color.white));
                tvOneway.setTextColor(getColor(R.color.black));
                rlReturn.setVisibility(View.VISIBLE);
            }
        });
        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        search_BTN.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
//
                if (origin.getText().toString().trim() == null && destination.getText().toString().trim() == null) {
                    Toast.makeText(getApplicationContext(), "Enter Origin and Destination Location", Toast.LENGTH_SHORT).show();

                } else if (origin.getText().toString().trim().isEmpty() && destination.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Origin and Destination Location", Toast.LENGTH_SHORT).show();

                } else if (origin.getText().toString().trim().isEmpty()) {
//                    origin.setError("Enter Origin");
                    Toast.makeText(getApplicationContext(), "Enter Origin..", Toast.LENGTH_SHORT).show();

                } else if (destination.getText().toString().trim().isEmpty()) {
//                    destination.setError("Enter Destinaiion");
                    Toast.makeText(getApplicationContext(), "Enter Destinaiion..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SearchFlightRequest searchFlightRequest = new SearchFlightRequest();
                    ArrayList<Segment> arrayList = new ArrayList<>();
                        Segment segment = new Segment();
                        Segment segment1 = new Segment();

                        if (jouneycount == 1) {

                            segment.setOrigin(get_final_origin);
                            segment.setDestination(get_final_destination);
                            segment.setFlightCabinClass(String.valueOf(1));
                            segment.setPreferredDepartureTime(depart_EDT.getText().toString().trim());
                            segment.setPreferredArrivalTime(return_EDT.getText().toString().trim());
                            arrayList.add(segment);

                        }
                        if (jouneycount == 2) {
                            segment.setOrigin(get_final_origin);
                            segment.setDestination(get_final_destination);
                            segment.setFlightCabinClass(String.valueOf(1));
                            segment.setPreferredDepartureTime(depart_EDT.getText().toString().trim());
                            segment.setPreferredArrivalTime(depart_EDT.getText().toString().trim());
//
                            segment1.setOrigin(get_final_destination);
                            segment1.setDestination(get_final_origin);
                            segment1.setFlightCabinClass(String.valueOf(1));
                            segment1.setPreferredDepartureTime(return_EDT.getText().toString().trim());
                            segment1.setPreferredArrivalTime(return_EDT.getText().toString().trim());

                            arrayList.add(segment);
                            arrayList.add(segment1);

                        }


                    searchFlightRequest.setEndUserIp("");
                    searchFlightRequest.setTokenId(authToken);
                    searchFlightRequest.setAdultCount(adultCount);
                    searchFlightRequest.setChildCount(children);
                    searchFlightRequest.setInfantCount(infant);
                    searchFlightRequest.setDirectFlight(directCheck);
                    searchFlightRequest.setOneStopFlight("false");
                    searchFlightRequest.setJourneyType(JOUNEYTYPE);
                    searchFlightRequest.setPreferredAirlines(null);
                    searchFlightRequest.setSources(null);
                    searchFlightRequest.setSegments(arrayList);
                    airSearch(searchFlightRequest);
                }

//                SharedPreferences.Editor editor = shared.edit();
//                editor.putString("TraceId", "0846da60-e834-4146-815e-8affa2270aa8");
//                editor.putString("Adultno", adultCount);
//                editor.putString("Childno", children);
//                editor.putString("Infant", infant);
//                editor.putString("FlightClass", clas);
//                editor.apply();
//                Intent intent = new Intent(FlightDashActivity.this, FlitListActivity.class);
//                startActivity(intent);

            }
        });
        depart_EDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(FlightDashActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                depart_EDT.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                departSend = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                String date = "1";
                                int finalmonth = monthOfYear + 1;
                                String finalmonthsum;
                                if (finalmonth < 10) {
                                    finalmonthsum = "0" + finalmonth;
                                    Log.e("final month e", "" + finalmonthsum + "");

                                } else {
                                    finalmonthsum = String.valueOf(finalmonth);
                                    Log.e("final month ELESE", "" + finalmonthsum + "");

                                }

                                if (dayOfMonth < 10) {
                                    date = "0" + dayOfMonth;
//                                    depart_EDT.setText(date + "/" + (monthOfYear + 1) + "/" + year);
                                    depart_EDT.setText(year + "-" + finalmonthsum + "-" + date);

                                } else {
                                    depart_EDT.setText(year + "-" + finalmonthsum + "-" + dayOfMonth);

                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();
            }
        });
        return_EDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(FlightDashActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                return_EDT.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                returndepartSend = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                String date = "1";
                                int finalmonth = monthOfYear + 1;
                                String finalmonthsum;
                                if (finalmonth < 10) {
                                    finalmonthsum = "0" + finalmonth;
                                    Log.e("final month e", "" + finalmonthsum + "");

                                } else {
                                    finalmonthsum = String.valueOf(finalmonth);
                                    Log.e("final month ELESE", "" + finalmonthsum + "");

                                }

                                if (dayOfMonth < 10) {
                                    date = "0" + dayOfMonth;
                                    return_EDT.setText(year + "-" + finalmonthsum + "-" + date);

                                } else {
                                    return_EDT.setText(year + "-" + finalmonthsum + "-" + dayOfMonth);
                                }

                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();
            }
        });
        search_vhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FlightDashActivity.this, FlightBookingHistory.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FlightDashActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    private void airSearch(SearchFlightRequest searchFlightRequest) {
        Log.e("senddata", "onResponse: " + searchFlightRequest.toString());

        progressDialogShow();
//        RetrofitInterface retrofitInterface = ApiClients.getInstance().create(RetrofitInterface.class);
        RetrofitInterface retrofitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<FlightSearchModel> responseBodyCall = retrofitInterface.airSearch(searchFlightRequest);
        responseBodyCall.enqueue(new Callback<FlightSearchModel>() {
            @Override
            public void onResponse(Call<FlightSearchModel> call, Response<FlightSearchModel> response) {
                progressDialogDismiss();
                flightSearchModel = response.body();
                if (response.code() == 200) {
                    if (flightSearchModel.getResponse().getResponseStatus() == 1) {
                        shared = getSharedPreferences("nict", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(flightSearchModel); // myObject - instance of MyObject
                        Log.e("on clicksearch","do"+json);
                        editor.putString("FlightsearchallResponse", json);
                        editor.putString("TraceId", flightSearchModel.getResponse().getTraceId());
                        Log.e("trace","idonclick"+flightSearchModel.getResponse().getTraceId());
                        editor.putString("Adultno", adultCount);
                        editor.putString("Childno", children);
                        editor.putString("Infant", infant);
                        editor.putString("FlightClass", clas);
                        editor.apply();
                        Intent intent = new Intent(FlightDashActivity.this, FlitListActivity.class);
                        startActivity(intent);

                    } else if (flightSearchModel.getResponse().getResponseStatus() == 3) {
                        Toast.makeText(FlightDashActivity.this, "Departure Date can not less than today date", Toast.LENGTH_SHORT).show();
                    } else if (flightSearchModel.getResponse().getResponseStatus() == 2) {
                        Toast.makeText(FlightDashActivity.this, "No result found due to some technical reason", Toast.LENGTH_SHORT).show();
                    } else if (flightSearchModel.getResponse().getResponseStatus() == 4) {
                        Toast.makeText(FlightDashActivity.this, "Invalid Token Try Again", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }
                }

            }

            @Override
            public void onFailure(Call<FlightSearchModel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(FlightDashActivity.this, "Invalid Token", Toast.LENGTH_SHORT).show();
                Log.e("reeorr+", t.getMessage());
                Log.e("reeorr+", t.toString());
            }
        });

    }

    private void getTBOFlight(HashMap<String, String> param) {
        Log.e("nict", "onResponse: ");
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<AuthTokenTBORequest> call = retroFitInterface.getFlightToken(param);
        call.enqueue(new Callback<AuthTokenTBORequest>() {
            @Override
            public void onResponse(Call<AuthTokenTBORequest> call, Response<AuthTokenTBORequest> response) {
                progressDialogDismiss();
                AuthTokenTBORequest updateOrder = response.body();
                Log.e("nict in body", "onResponse");
                if (response.code() == 200) {

                    if (updateOrder.getStatus() == 1) {
//                            Error error= new Error();
//                    Log.e("nict error body", "error"+error.getErrorCode());
                        authToken = response.body().getTokenId();
                        endUserIp = updateOrder.getMember().getLoginDetails();
                        agencyid = String.valueOf(updateOrder.getMember().getAgencyId());
                        SharedPreferences.Editor editor = shared.edit();
                        Gson gson = new Gson();
                        editor.putString("Tokenid", response.body().getTokenId());
                        editor.putString("Agencyid", agencyid);
                        editor.apply();
                        Log.e("TAGtoken", "onResponse: " + authToken);
                        Log.e("agencyid", "agencyid: " + agencyid);

                    } else if (updateOrder.getStatus() == 2) {
                        Toast.makeText(FlightDashActivity.this, "TBO Api Response issue", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(FlightDashActivity.this, "Technical Issue", Toast.LENGTH_SHORT).show();

                    }


                } else if (response.code() == 401) {
                    Toast.makeText(FlightDashActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
//                    Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(FlightDashActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 503) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(FlightDashActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
//                    Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(FlightDashActivity.this, "server issue", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthTokenTBORequest> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(FlightDashActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailurauth", "onResponse: " + t.getMessage());
                onBackPressed();
            }
        });

    }

    public class CityOrginAdapter extends RecyclerView.Adapter<CityOrginAdapter.UserViewHolder> {
        Context context;
        private ArrayList<Data> citylist_adapter = new ArrayList<>();

        Dialog dialog;

        public CityOrginAdapter(Context context, ArrayList<Data> citylist_adapter, Dialog dialog) {
            this.citylist_adapter = citylist_adapter;
            this.context = context;
            this.dialog = dialog;
        }

        @Override
        public CityOrginAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchcity, parent, false);
            return new CityOrginAdapter.UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CityOrginAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            Data datamodel = citylist_adapter.get(position);
            holder.city_name.setText(citylist_adapter.get(position).getCityName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (layouttype.equalsIgnoreCase("orgin")) {
                        origin.setText(citylist_adapter.get(position).getCityName());
                        get_final_org = citylist_adapter.get(position).getCityCodeWord();
                        get_final_origin = get_final_org.toUpperCase();
                        dialog.dismiss();

                    } else if (layouttype.equalsIgnoreCase("destination")) {
                        destination.setText(citylist_adapter.get(position).getCityName());
                        get_final_dest = citylist_adapter.get(position).getCityCodeWord();
                        get_final_destination = get_final_dest.toUpperCase();
                        dialog.dismiss();
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return citylist_adapter.size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder {
            TextView city_name, sku_code_desc, skuqunty, sku_packid, sku_bachid, sku_mfgdate, sku_mfgexpdate;

            public UserViewHolder(View itemView) {
                super(itemView);
                city_name = itemView.findViewById(R.id.city_name);

            }

        }


        public void updateList(ArrayList<Data> list) {
            citylist_adapter = list;
            notifyDataSetChanged();
        }


    }

    public void filter(String text) {
        ArrayList<Data> temp = new ArrayList();
        for (Data d : citylist) {

            if (text.length() >= 2) {
                if (d.getCityName().contains(text)) {
                    temp.add(d);
                } else if (d.getCityName().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getCityName().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getCityName().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getCityName().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getCityName().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getCityName().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
            }

        }

        try {
            cityOrginAdapter.updateList(temp);
        } catch (Exception e) {

        }
    }

    private boolean validationDetails() {

        if (origin.getText().toString().isEmpty()) {
            origin.setError("Enter Patient Name");
            return false;
        }
        if (destination.getText().toString().isEmpty()) {
            destination.setError("Enter Age ");
            return false;
        }

        return true;
    }

}