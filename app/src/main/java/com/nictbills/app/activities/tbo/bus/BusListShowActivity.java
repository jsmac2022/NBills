package com.nictbills.app.activities.tbo.bus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.adapter.BusListAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.BusSeatListAdapter;
import com.nictbills.app.activities.tbo.bus.model.bussearchresmodel.BusAdapterResponseModel;
import com.nictbills.app.databinding.ActivityBusListShowBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class BusListShowActivity extends AppCompatActivity  implements BusListAdapter.OnItemClickListener {
ActivityBusListShowBinding binding;
    SharedPreferences shared;
    String getBusToken ,getAgency_id ,getMember_id ,getTrace_id;
    BusListAdapter busListAdapter;
    BusAdapterResponseModel busAdapterResponseModel;
    ArrayList<BusAdapterResponseModel> busllist = new ArrayList<BusAdapterResponseModel>();
    BusSeatActivity busSeatActivity;
    BusSeatListAdapter busSeatListAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_bus_list_show);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        getBusToken = shared.getString("TokenBus", "");
        getAgency_id= shared.getString("Agencyid", "");
        getMember_id = shared.getString("Memberid", "");
        getTrace_id = shared.getString("TraceId", "");
        String jsonbus = shared.getString("Bussearch", "");
        try {

            JSONObject jsonObject = new JSONObject(jsonbus);
            JSONObject busresult = jsonObject.getJSONObject("BusSearchResult");
            JSONArray busresultarray = busresult.getJSONArray("BusResults");

            if (busresultarray != null) {
                for (int i = 0; i < busresultarray.length(); i++) {
                    JSONObject busobj = busresultarray.getJSONObject(i);
                    busAdapterResponseModel= new BusAdapterResponseModel();
                    busAdapterResponseModel.setBus_name(busobj.optString("TravelName"));
                    busAdapterResponseModel.setBus_type(busobj.optString("BusType"));
                    busAdapterResponseModel.setBus_seatavilability(busobj.optString("AvailableSeats"));
                    String daprttime = busobj.optString("DepartureTime");
                    String arrivettime = busobj.optString("ArrivalTime");
                    Log.e("..samtyy","tr"+arrivettime);

                    LocalDateTime daprtdateTime = LocalDateTime.parse(daprttime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    LocalDateTime arrivedateTime = LocalDateTime.parse(arrivettime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    Log.e("..sam","tr"+arrivedateTime);
                    LocalTime senddeparttime = daprtdateTime.toLocalTime();
                    LocalTime sendarrivetime = arrivedateTime.toLocalTime();
                    Log.e("..sam1","tr"+sendarrivetime);

                    busAdapterResponseModel.setBus_arrivetime(String.valueOf(sendarrivetime));
                    busAdapterResponseModel.setBus_departuretime(String.valueOf(senddeparttime));
                    busAdapterResponseModel.setBus_resultindex(busobj.optString("ResultIndex"));
                    JSONObject busprice = busobj.getJSONObject("BusPrice");

//                    String s = String.format("%.1f",busprice.optString("PublishedPrice"));
//                    Log.e("",""+s);


                    busAdapterResponseModel.setBus_published_price(busprice.optString("PublishedPrice"));
                    busAdapterResponseModel.setBus_published_price_roundoff(busprice.optString("PublishedPriceRoundedOff"));
                    busllist.add(busAdapterResponseModel);
//                    busAdapterResponseModel.setBus_published_price(busobj.optString(""));

                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                binding.recyclerViewBuslist.setLayoutManager(linearLayoutManager);
                 busListAdapter = new BusListAdapter(BusListShowActivity.this, busllist, this);
                binding.recyclerViewBuslist.setAdapter(busListAdapter);


            }

        }
        catch (Exception e)
        {

        }
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        binding.etSearchBus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()>=2) {
                    filter(s.toString());
                    binding.recyclerViewBuslist.setVisibility(View.VISIBLE);
                    binding.datanotfound.setVisibility(View.GONE);
                }
                else
                {
                    binding.recyclerViewBuslist.setVisibility(View.GONE);
                    binding.datanotfound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=2) {
                    filter(s.toString());
                    binding.recyclerViewBuslist.setVisibility(View.VISIBLE);
                    binding.datanotfound.setVisibility(View.GONE);
                }
                else
                {
                    binding.recyclerViewBuslist.setVisibility(View.GONE);
                    binding.datanotfound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=2) {
                    filter(s.toString());
                    binding.recyclerViewBuslist.setVisibility(View.VISIBLE);
                    binding.datanotfound.setVisibility(View.GONE);
                }
                else
                {
                    binding.recyclerViewBuslist.setVisibility(View.GONE);
                    binding.datanotfound.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onItemClick(String getbus_index ,String bustavles ,String publishedprice) {

        if( busSeatListAdapter.seatadd!=null &&  busSeatListAdapter.seatadd.size()>0){
            busSeatListAdapter.seatadd.clear();

        }
        else
        {
            System.out.println("Array is not initialized or empty");

        }
        SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("Resultindex",getbus_index);
        editor.putString("BusTravles",bustavles);
        editor.putString("Publishedprice",publishedprice);
        editor.apply();

//        Intent intent = new Intent(BusListShowActivity.this, AddPassangerActivity.class);
//        Intent intent = new Intent(BusListShowActivity.this, BusBordingActivity.class);
        Intent intent = new Intent(BusListShowActivity.this, BusSeatActivity.class);
        startActivity(intent);
    }
    public void filter(String text) {

        Log.e("sa,m", "send" + text);
        ArrayList<BusAdapterResponseModel> temp = new ArrayList<BusAdapterResponseModel>();
        for (BusAdapterResponseModel d : busllist) {

            if (text.length() >= 2) {
                if (d.getBus_name().contains(text)) {
                    temp.add(d);
                }
                else if (d.getBus_name().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_name().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_name().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getBus_name().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_name().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

                //getBus_type

                else if (d.getBus_type().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_type().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_type().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_type().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getBus_type().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_type().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

                //getBus_arrivetime

                else if (d.getBus_arrivetime().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_arrivetime().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_arrivetime().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_arrivetime().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getBus_arrivetime().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_arrivetime().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
//getBus_departuretime
                else if (d.getBus_departuretime().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_departuretime().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_departuretime().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_departuretime().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getBus_departuretime().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_departuretime().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

                //getBus_published_price
                else if (d.getBus_published_price().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_published_price().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_published_price().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_published_price().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getBus_published_price().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_published_price().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

                //getBus_resultindex
                else if (d.getBus_resultindex().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_resultindex().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getBus_resultindex().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_resultindex().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getBus_resultindex().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getBus_resultindex().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

            }
        }
    }

}