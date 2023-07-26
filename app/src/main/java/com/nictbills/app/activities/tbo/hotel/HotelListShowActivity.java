package com.nictbills.app.activities.tbo.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelListAdapter;
import com.nictbills.app.activities.tbo.hotel.model.hotellistresponse.HotelListResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse.HotelResult;
import com.nictbills.app.databinding.ActivityHotelListShowBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class HotelListShowActivity extends AppCompatActivity implements HotelListAdapter.OnItemClickListener {
    SharedPreferences shared;
    String gethotelsearchresp, gettrace_id, getcountry_code, getcity_code, gettokenhotel_id;
    ActivityHotelListShowBinding binding;
    ArrayList<HotelListResponseModel> hotellist = new ArrayList<HotelListResponseModel>();
    HotelListResponseModel hotelListResponseModel;
    HotelListAdapter hotelListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hotel_list_show);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_list_show);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettrace_id = shared.getString("TraceId", "");
        gettokenhotel_id = shared.getString("Tokenid_Hotel", "");
        getcountry_code = shared.getString("CountryCode", "");
        getcity_code = shared.getString("CityCode", "");
        String json = shared.getString("Hotelsearch", "");
        Log.e("jat", "1" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("HotelSearchResult");
            JSONArray array = jsonObject1.getJSONArray("HotelResults");
            Log.e("array", "1" + json);

            if (array != null) {

                for (int i = 0; i < array.length(); i++) {

                    JSONObject hotelobj = array.getJSONObject(i);
                    hotelListResponseModel = new HotelListResponseModel();
                    if (hotelobj.optString("HotelName").equals(""))
                    {
                        hotelListResponseModel.setHotel_name("");

                    }
                    else {
                        hotelListResponseModel.setHotel_name(hotelobj.optString("HotelName"));

                    }

                    hotelListResponseModel.setHotel_ratingvalue(hotelobj.optString("StarRating"));
                    hotelListResponseModel.setHotel_img(hotelobj.optString("HotelPicture"));
                    if (hotelobj.optString("HotelAddress").equals("")) {
                        hotelListResponseModel.setHoteladdress("null");
                    } else {
                        hotelListResponseModel.setHoteladdress(hotelobj.optString("HotelAddress"));
                    }
                    hotelListResponseModel.setHotel_index(hotelobj.optString("ResultIndex"));
                    hotelListResponseModel.setHotel_code(hotelobj.optString("HotelCode"));

                    JSONObject hotelprice = hotelobj.getJSONObject("Price");
                    hotelListResponseModel.setHotel_price(hotelprice.optString("PublishedPrice"));


                    if (hotelobj.has("SupplierHotelCodes")) {
                        JSONArray arrayforSupplierHotelCodes = hotelobj.getJSONArray("SupplierHotelCodes");

                        for (int j = 0; j < arrayforSupplierHotelCodes.length(); j++) {
                            JSONObject jsonObjectcate = arrayforSupplierHotelCodes.getJSONObject(j);
                            hotelListResponseModel.setHotle_CategoryId(jsonObjectcate.optString("CategoryId"));


                        }
                    } else {

                        hotelListResponseModel.setHotle_CategoryId("NOCategoryId");


                    }

//
                    hotellist.add(hotelListResponseModel);

                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                binding.recyclerViewHotellist.setLayoutManager(linearLayoutManager);
                hotelListAdapter = new HotelListAdapter(HotelListShowActivity.this, hotellist, this);
                binding.recyclerViewHotellist.setAdapter(hotelListAdapter);


            }


        } catch (Exception e) {
        }
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.etSearchHotel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() >= 2) {
                    filter(s.toString());
                    binding.datanotfound.setVisibility(View.GONE);
                    binding.recyclerViewHotellist.setVisibility(View.VISIBLE);
                } else {
                    binding.datanotfound.setVisibility(View.VISIBLE);
                    binding.recyclerViewHotellist.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    filter(s.toString());
                    binding.datanotfound.setVisibility(View.GONE);
                    binding.recyclerViewHotellist.setVisibility(View.VISIBLE);
                } else {
                    binding.datanotfound.setVisibility(View.VISIBLE);
                    binding.recyclerViewHotellist.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 2) {
                    filter(s.toString());
                    binding.datanotfound.setVisibility(View.GONE);
                    binding.recyclerViewHotellist.setVisibility(View.VISIBLE);
                } else {
                    binding.datanotfound.setVisibility(View.VISIBLE);
                    binding.recyclerViewHotellist.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onItemClick(String gethotel_index, String gethotel_code, String gethotel_name, String catgeory) {
        SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("Hotelindex", gethotel_index);
        editor.putString("Hotelcode", gethotel_code);
        editor.putString("HotelName", gethotel_name);
        editor.putString("catgeoryid", catgeory);
        editor.apply();
        Intent intent = new Intent(HotelListShowActivity.this, HotelInfoActivity.class);
        startActivity(intent);

    }

    public void filter(String text) {
        Log.e("sa,m", "send" + text);
        ArrayList<HotelListResponseModel> temp = new ArrayList<HotelListResponseModel>();
        for (HotelListResponseModel d : hotellist) {

            if (text.length() >= 2) {
                //getHotel_name
                if (d.getHotel_name().contains(text)) {
                    temp.add(d);
                } else if (d.getHotel_name().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_name().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_name().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_name().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getHotel_name().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_name().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

                //getHotel_code

                else if (d.getHotel_code().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_code().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_code().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_code().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getHotel_code().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_code().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

                //getHoteladdress


                else if (d.getHoteladdress().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHoteladdress().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHoteladdress().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHoteladdress().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getHoteladdress().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHoteladdress().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }


                // //getHotel_price

                else if (d.getHotel_price().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_price().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_price().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_price().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getHotel_price().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_price().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }
//getHotel_index

                else if (d.getHotel_index().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_index().toLowerCase().contains(text.toLowerCase())) {
                    temp.add(d);
                } else if (d.getHotel_index().toUpperCase().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_index().contains(text.toLowerCase(Locale.ROOT))) {
                    temp.add(d);
                } else if (d.getHotel_index().contains(text.toUpperCase())) {
                    temp.add(d);
                } else if (d.getHotel_index().contains(text.toUpperCase(Locale.ROOT))) {
                    temp.add(d);
                }

            }

        }
        try {
            hotelListAdapter.updateList(temp);
        } catch (Exception e) {

        }
    }

}