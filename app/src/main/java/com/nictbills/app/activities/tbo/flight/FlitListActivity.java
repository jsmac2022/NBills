package com.nictbills.app.activities.tbo.flight;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.flight.model.citylist.Data;
import com.nictbills.app.activities.tbo.flight.model.flightsearch.FlightSearchModel;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.AddPassenger;
import com.nictbills.app.activities.tbo.flight.model.search.FlightSearchList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FlitListActivity extends BaseActivity implements FlitListAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3", "Person 4", "Person 5", "Person 6", "Person 7"));
    SharedPreferences shared;
    FlightSearchModel flightSearchModel;
    //    List<Result> asList;
//    ArrayList<Result> searchresponseflight= new ArrayList<Result>();
    JSONArray array;
    FlightSearchList flightSearchList;
    ArrayList<FlightSearchList> flightlist = new ArrayList();
    ImageView backimg;
    EditText edtflight_search;
    FlitListAdapter flitListAdapter;
    TextView datanotfound;
    String json;
    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flit_list);
        recyclerView = findViewById(R.id.recyclerView);
        backimg = findViewById(R.id.backArrow_IMG);
        edtflight_search = findViewById(R.id.et_search_flight);
        datanotfound = findViewById(R.id.datanotfound);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        Gson gson = new Gson();
        if(shared.getString("FlightsearchallResponse", "")!=null){
            json= shared.getString("FlightsearchallResponse", "");
        }

        Log.e("jsondat", "1" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.e("printarray1", "1" + jsonObject);
            JSONObject jsonObject1 = jsonObject.getJSONObject("Response");
            Log.e("printarray2", "1" + jsonObject1);
            JSONArray result = jsonObject1.getJSONArray("Results");
            if (result != null) {
                for (int i = 0; i < result.length(); i++) {
                    JSONArray result1 = result.getJSONArray(i);

                    for (int j = 0; j < result1.length(); j++) {
                        JSONObject jsonObject2 = result1.getJSONObject(j);
                        flightSearchList = new FlightSearchList();

                        flightSearchList.setResulrIndex(jsonObject2.optString("ResultIndex"));
                        flightSearchList.setIsLLC(jsonObject2.optString("IsLCC"));
                        JSONObject objfare = jsonObject2.getJSONObject("Fare");

                        flightSearchList.setPublishFared(objfare.optString("PublishedFare"));
                        flightSearchList.setOfferfared(objfare.optString("OfferedFare"));
                        JSONArray segment = jsonObject2.getJSONArray("Segments");

                        for (int k = 0; k < segment.length(); k++) {
                            JSONArray segment1 = segment.getJSONArray(k);
                            for (int l = 0; l < segment1.length(); l++) {
                                JSONObject objsegment1 = segment1.getJSONObject(l);
                                flightSearchList.setFlighseatavilable(objsegment1.optString("NoOfSeatAvailable"));
                                int durationtime = Integer.parseInt(objsegment1.optString("Duration"));
                                String finalduration = LocalTime.MIN.plus(Duration.ofMinutes(durationtime + L)).toString();
                                flightSearchList.setDuration(finalduration);
                                JSONObject objairline = objsegment1.getJSONObject("Airline");
                                JSONObject objorigin = objsegment1.getJSONObject("Origin");
                                JSONObject objdestination = objsegment1.getJSONObject("Destination");
                                JSONObject objoriginairport = objorigin.getJSONObject("Airport");
                                JSONObject objdestnationairport = objdestination.getJSONObject("Airport");

                                flightSearchList.setAirlineCode(objairline.optString("AirlineCode"));
                                flightSearchList.setFlightname(objairline.optString("AirlineName"));

                                flightSearchList.setFlightno(objairline.optString("FlightNumber"));
                                String daprttime = objorigin.optString("DepTime");
                                String arrivettime = objdestination.optString("ArrTime");
                                LocalDateTime daprtdateTime = LocalDateTime.parse(daprttime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                                LocalDateTime arrivedateTime = LocalDateTime.parse(arrivettime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                                LocalTime senddeparttime = daprtdateTime.toLocalTime();
                                LocalTime sendarrivetime = arrivedateTime.toLocalTime();

//                                flightSearchList.setDepartTime(objorigin.optString("DepTime"));
                                flightSearchList.setDepartTime(String.valueOf(senddeparttime));
                                flightSearchList.setFulldepttime(objorigin.optString("DepTime"));
//                                flightSearchList.setArriveTime(objdestination.optString("ArrTime"));
                                flightSearchList.setArriveTime(String.valueOf(sendarrivetime));
                                flightSearchList.setFullarrtime(objdestination.optString("ArrTime"));
                                flightSearchList.setCityCode_origin(objoriginairport.optString("CityCode"));
                                flightSearchList.setCityCode_destination(objdestnationairport.optString("CityCode"));

                                flightlist.add(flightSearchList);


                            }

                        }


                    }


                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
//        FlitListAdapter customAdapter = new FlitListAdapter(FlitListActivity.this, personNames, this);
                flitListAdapter = new FlitListAdapter(FlitListActivity.this, flightlist, this);
//        FlitListAdapter customAdapter = new FlitListAdapter(FlitListActivity.this, flightSearchModel, this);
                recyclerView.setAdapter(flitListAdapter); // set the Adapter to RecyclerView


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        edtflight_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() >= 2) {
                    datanotfound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    filter(s.toString());

                } else {
                    recyclerView.setVisibility(View.GONE);
                    datanotfound.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    datanotfound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    filter(s.toString());

                } else {
                    recyclerView.setVisibility(View.GONE);
                    datanotfound.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() >= 2) {
                    datanotfound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    filter(s.toString());

                } else {
                    recyclerView.setVisibility(View.GONE);
                    datanotfound.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    public void onItemClick(String publishedfared, String offerfared, String flightname, String origincode, String destinationcode, String seatavilable, String airlinecode, String flightno, String fuldepartdatetime, String fularrdatetime, String resultindex, String getLlcType) {

        SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("flightno", flightno);
        editor.putString("publishedprice", publishedfared);
        editor.putString("origincode", origincode);
        editor.putString("destinationcode", destinationcode);
        editor.putString("flightname", flightname);
        editor.putString("fulldeptdatetime", fuldepartdatetime);
        editor.putString("fullarrdatetime", fularrdatetime);
        editor.putString("ResultIndex", resultindex);
        editor.putString("LLCType", getLlcType);
        editor.putString("Airlinecode", airlinecode);
        editor.apply();
        Intent intent = new Intent(FlitListActivity.this, PassengerDetailsActivity.class);
        startActivity(intent);

    }

    public void filter(String text) {
        Log.e("sa,m", "send" + text);
        ArrayList<FlightSearchList> temp = new ArrayList();

        for (FlightSearchList d : flightlist) {

            if (text.length() >= 2) {
//                Log.e(" QWsa,m","send"+text);
                if (d.getFlightname().contains(text)) {
                    temp.add(d);
                }

                //type

                else if (d.getResulrIndex().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getResulrIndex().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getResulrIndex().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getResulrIndex().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getResulrIndex().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getResulrIndex().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
                //getFlightname
                else if (d.getFlightname().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getFlightname().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getFlightname().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getFlightname().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getFlightname().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getFlightname().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
                //getCityCode_origin

                else if (d.getCityCode_origin().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getCityCode_origin().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getCityCode_origin().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getCityCode_origin().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getCityCode_origin().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getCityCode_origin().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
//getCityCode_destination
                else if (d.getCityCode_destination().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getCityCode_destination().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getCityCode_destination().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getCityCode_destination().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getCityCode_destination().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getCityCode_destination().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

//getAirlineCode
                else if (d.getAirlineCode().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getAirlineCode().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getAirlineCode().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getAirlineCode().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getAirlineCode().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getAirlineCode().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
//getFlightno

                else if (d.getFlightno().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getFlightno().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getFlightno().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getFlightno().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getFlightno().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getFlightno().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getPublishFared().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getPublishFared().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getPublishFared().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getPublishFared().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getPublishFared().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getPublishFared().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                } else {
                    Log.e("else ", "1");
                }

            } else {
                Log.e("else ", "2");
            }

        }
        try {
            flitListAdapter.updateList(temp);
        } catch (Exception e) {

        }
    }


}