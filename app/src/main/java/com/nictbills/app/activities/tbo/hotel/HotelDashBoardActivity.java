package com.nictbills.app.activities.tbo.hotel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.bus.BusDashBoardActivity;
import com.nictbills.app.activities.tbo.bus.model.authresmodel.BusAuthResponseModel;
import com.nictbills.app.activities.tbo.flight.model.citylist.Data;
import com.nictbills.app.activities.tbo.flight.model.citylist.FlightCityList;
import com.nictbills.app.activities.tbo.hotel.model.adaultchildmodel.AdultChildModel;
import com.nictbills.app.activities.tbo.hotel.model.addroomrequest.RoomRequest;
import com.nictbills.app.activities.tbo.hotel.model.authresponse.HotelAouthModelResponse;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchrequest.HotelSearchRequest;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchrequest.RoomGuest;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse.Error;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse.HotelSearchResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityHotelDashBoardBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDashBoardActivity extends BaseActivity {
    ActivityHotelDashBoardBinding binding;
    RetrofitInterface retroFitInterface;
    String getcityname, checkindate, checkoutdate, agencyid, gettoken, adultCount, children, nationlity_count, star_count;
    SharedPreferences shared;
    String[] roomslist = {"1"};
    String[] natilnalitylist = {"Select", "Indian"};
    String[] starlist = {"Show All", "1Star or Less", "2Star or Less", "3Star or Less", "4Star or Less", "5Star or Less", "6Star or Less"};
    private ArrayList<Data> citylist = new ArrayList<>();
    CityOrginAdapter cityOrginAdapter;
    String getcountrycode, getcity_code;
    HotelSearchResponseModel hotelSearchResponseModel;
    HashMap<String, String> param;
    int childno, roomcountno = 0;
    int roomscount = 0;
    RoomGuest roomGuest;
    AdultChildModel adultChildModel;
    ArrayList<AdultChildModel> adultChildModellist = new ArrayList<AdultChildModel>();
    ArrayList<AdultChildModel> onclickadultadd = new ArrayList<AdultChildModel>();
    ArrayList<AdultChildModel> onclickchildadd = new ArrayList<AdultChildModel>();
    ArrayList<AdultChildModel> ChildModellist = new ArrayList<AdultChildModel>();
    int childspinnercount = 0;
    int adultspinnercount = 1;
    String edtage1, edtage2, edtage3, edtage4;
    ArrayList<AdultChildModel> adultlistspinner;
    RoomRequest requestmodel;
    static ArrayList<RoomRequest> addroomreqlist;
    static ArrayList<RoomGuest> arrayList;
    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hotel_dash_board);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_dash_board);
        retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        shared = getSharedPreferences("nict", MODE_PRIVATE);
        param = new HashMap<>();
        param.put("EndUserIp", "");
        param.put("ClientId", "ApiIntegrationNew");
        param.put("UserName", "Mahendratech");
        param.put("Password", "Mahe@1234");
        binding.edtNight.setText("1");
        date();
        spinner();
        onclick();

    }

    public void date() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String todaydate = dateFormat.format(today);
        String tomorrowdate = dateFormat.format(tomorrow);
        binding.tvCheckindate.setText(todaydate);
        binding.tvCheckoutdate.setText(tomorrowdate);
        binding.tvCheckindate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(HotelDashBoardActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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
                                    binding.tvCheckindate.setText(date + "/" + (finalmonthsum) + "/" + year);
                                    String datechout = "0" + (dayOfMonth + 1);
                                    Log.e("datechout", "datechout" + datechout);
                                    binding.tvCheckoutdate.setText(datechout + "/" + (finalmonthsum) + "/" + year);
                                } else {
                                    binding.tvCheckindate.setText(dayOfMonth + "/" + (finalmonthsum) + "/" + year);
                                    String datechout1 = String.valueOf(dayOfMonth + 1);
                                    Log.e("datechout1", "datechout1" + datechout1);

                                    binding.tvCheckoutdate.setText(datechout1 + "/" + (finalmonthsum) + "/" + year);
                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();

            }
        });
        binding.tvCheckoutdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendarToday = Calendar.getInstance();
                int year = calendarToday.get(Calendar.YEAR);
                int month = calendarToday.get(Calendar.MONTH);
                calendarToday.add(Calendar.DAY_OF_MONTH, 1);
                int day = calendarToday.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(HotelDashBoardActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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
                                    binding.tvCheckoutdate.setText(date + "/" + finalmonthsum + "/" + year);
                                    Log.e("if", "1" + date + "/" + (monthOfYear + 1) + "/" + year);

                                } else {
                                    binding.tvCheckoutdate.setText(dayOfMonth + "/" + finalmonthsum + "/" + year);
                                    Log.e("else", "date" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();


            }
        });
    }

    public void spinner() {
        //room
        ArrayAdapter roomsadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomslist);
        roomsadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.rooms.setAdapter(roomsadapter);
        binding.rooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomscount = Integer.parseInt(roomslist[position]);
                roomcountno = Integer.parseInt(roomslist[position]);
                adultChildModellist.clear();
                ChildModellist.clear();
                for (int i = 0; i < roomscount; i++) {
                    Log.e("", "");
                    adultChildModel = new AdultChildModel();
                    adultChildModel.setAdultchildno(i + "");
                    adultChildModellist.add(adultChildModel);
                    ChildModellist.add(adultChildModel);

                }

                //adult
//                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//                binding.adultRView.setLayoutManager(linearLayoutManager1);
//                AdultSpinerAdapter adultSpinerAdapter = new AdultSpinerAdapter(HotelDashBoardActivity.this, adultChildModellist);
//                binding.adultRView.setAdapter(adultSpinerAdapter);

                //child

//                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//                binding.childRView.setLayoutManager(linearLayoutManager2);
//                ChildSpinerAdapter childSpinerAdapter = new ChildSpinerAdapter(HotelDashBoardActivity.this, ChildModellist);
//                binding.childRView.setAdapter(childSpinerAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });

        ArrayAdapter nationality_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, natilnalitylist);
        nationality_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerNat.setAdapter(nationality_adapter);
        binding.spinnerNat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nationlity_count = natilnalitylist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });
        ArrayAdapter starrating_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, starlist);
        starrating_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerAllrating.setAdapter(starrating_adapter);
        binding.spinnerAllrating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                star_count = starlist[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });


    }

    public void get_Hotelauth_token(HashMap<String, String> param) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelAouthModelResponse> call = retroFitInterface.gethotelauth_token(param);
        call.enqueue(new Callback<HotelAouthModelResponse>() {
            @Override
            public void onResponse(Call<HotelAouthModelResponse> call, Response<HotelAouthModelResponse> response) {
                progressDialogDismiss();
                HotelAouthModelResponse hotelAouthModelResponse = response.body();
                Log.e("hotelauth", "onResponse: ");
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
//                        Toast.makeText(HotelDashBoardActivity.this, "gettoken", Toast.LENGTH_SHORT).show();
                        gettoken = response.body().getTokenId();
                        agencyid = String.valueOf(hotelAouthModelResponse.getMember().getAgencyId());
                        Log.e("hotelauth", "token: " + gettoken);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("Tokenid_Hotel", response.body().getTokenId());
                        editor.putString("Agencyid", agencyid);
                        editor.apply();
                    } else if (response.body().getStatus() == 2) {
                        Toast.makeText(HotelDashBoardActivity.this, "IP Address is not in correct format", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HotelDashBoardActivity.this, "Response Technical Isuue", Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 401) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } else if (response.code() == 503) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<HotelAouthModelResponse> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(HotelDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();

                Log.e("nictfailur", "onResponse: ");
                onBackPressed();
            }
        });


    }

    public void get_Busauth_token(HashMap<String, String> param) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusAuthResponseModel> call = retroFitInterface.getBus_AuthToken(param);
        call.enqueue(new Callback<BusAuthResponseModel>() {
            @Override
            public void onResponse(Call<BusAuthResponseModel> call, Response<BusAuthResponseModel> response) {
                progressDialogDismiss();
                BusAuthResponseModel busAuthResponseModel = response.body();
                Log.e("hotelauth", "onResponse: ");

                if (response.code() == 200) {

                    if (response.body().getStatus() == 1) {

                        gettoken = response.body().getTokenId();
                        agencyid = String.valueOf(busAuthResponseModel.getMember().getAgencyId());
                        Log.e("hotelauth", "token: " + gettoken);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("Tokenid_Hotel", response.body().getTokenId());
                        editor.putString("Agencyid", agencyid);
                        editor.apply();
                    } else if (response.body().getStatus() == 2) {
                        Toast.makeText(HotelDashBoardActivity.this, "IP Address is not in correct format", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(HotelDashBoardActivity.this, "Response Technical Isuue", Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } else if (response.code() == 503) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(HotelDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BusAuthResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(HotelDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                Log.e("nictfailur", "onResponse: ");
                onBackPressed();

            }
        });
    }


    public void onclick() {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        get_Hotelauth_token(param);
        get_Busauth_token(param);
        binding.searchCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(HotelDashBoardActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.search_origincity);
                RecyclerView rvshowlist = (RecyclerView) dialog.findViewById(R.id.rv_citylist);
                TextView datanotfund = (TextView) dialog.findViewById(R.id.datanotfound);

                EditText edrsearch = (EditText) dialog.findViewById(R.id.et_search_latpanel);
                dialog.show();
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
                RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
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
                            cityOrginAdapter = new HotelDashBoardActivity.CityOrginAdapter(getApplicationContext(), citylist, dialog);
                            rvshowlist.setAdapter(cityOrginAdapter);
                        } else if (response.code() == 400) {
                            Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

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

        //editage
//        edt_childroom1
        binding.edtAdultroom1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().trim() == null) {
//                    Toast.makeText(HotelDashBoardActivity.this, "Add ddat leat one", Toast.LENGTH_SHORT).show();

                } else if (s.toString().trim().equals("")) {
//                    Toast.makeText(HotelDashBoardActivity.this, "Add ddxcxcat leat one", Toast.LENGTH_SHORT).show();

                } else {
//                    Toast.makeText(HotelDashBoardActivity.this, "Add Adult Count", Toast.LENGTH_SHORT).show();

                }

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.toString().trim() != null) {
                        int length = Integer.valueOf(s.toString().trim());
                        if (length >= 9) {
                            Toast.makeText(HotelDashBoardActivity.this, "Only Allow Less than 9", Toast.LENGTH_SHORT).show();
                            binding.edtAdultroom1.getText().clear();

                        }
                        else if(s.toString().equals("0"))
                        {
                            Toast.makeText(HotelDashBoardActivity.this, "Add Adult minimum1 ", Toast.LENGTH_SHORT).show();
                            binding.edtAdultroom1.getText().clear();

                        }
                        else {


                        }

                    }
                } catch (Exception E) {

                }


            }

            @Override
            public void afterTextChanged(Editable s) {
//                Toast.makeText(HotelDashBoardActivity.this, "Only Allow Less than 5", Toast.LENGTH_SHORT).show();


            }
        });

        binding.edtChildroom1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("beforeTextChanged", "beforeTextChanged" + s);
                if (s.toString().trim() == null) {
//                    Toast.makeText(HotelDashBoardActivity.this, "Add ddat leat one", Toast.LENGTH_SHORT).show();
                } else if (s.toString().trim().equals("")) {
//                    Toast.makeText(HotelDashBoardActivity.this, "Add ddxcxcat leat one", Toast.LENGTH_SHORT).show();

                } else {
//                    Toast.makeText(HotelDashBoardActivity.this, "Add Child Count", Toast.LENGTH_SHORT).show();
                    binding.edtChildroom1age1.setVisibility(View.GONE);
                    binding.edtChildroom1age2.setVisibility(View.GONE);
                    binding.edtChildroom1age3.setVisibility(View.GONE);
                    binding.edtChildroom1age4.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (s.toString().trim() != null) {
                        int length = Integer.valueOf(s.toString().trim());
                        if (length >= 5) {
                            Toast.makeText(HotelDashBoardActivity.this, "Only Allow Less than 5", Toast.LENGTH_SHORT).show();
                            binding.edtChildroom1.getText().clear();

                            binding.edtChildroom1age1.setVisibility(View.GONE);
                            binding.edtChildroom1age2.setVisibility(View.GONE);
                            binding.edtChildroom1age3.setVisibility(View.GONE);
                            binding.edtChildroom1age4.setVisibility(View.GONE);

                        } else {

                            if (s.toString().trim().equals("0")) {

                                binding.edtChildroom1age1.setVisibility(View.GONE);
                                binding.edtChildroom1age2.setVisibility(View.GONE);
                                binding.edtChildroom1age3.setVisibility(View.GONE);
                                binding.edtChildroom1age4.setVisibility(View.GONE);
                            } else if (s.toString().trim().equals("1")) {
                                binding.edtChildroom1age1.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age2.setVisibility(View.GONE);
                                binding.edtChildroom1age3.setVisibility(View.GONE);
                                binding.edtChildroom1age4.setVisibility(View.GONE);

                            } else if (s.toString().trim().equals("2")) {
                                binding.edtChildroom1age1.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age2.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age3.setVisibility(View.GONE);
                                binding.edtChildroom1age4.setVisibility(View.GONE);

                            } else if (s.toString().trim().equals("3")) {
                                binding.edtChildroom1age1.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age2.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age3.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age4.setVisibility(View.GONE);


                            } else if (s.toString().trim().equals("4")) {
                                binding.edtChildroom1age1.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age2.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age3.setVisibility(View.VISIBLE);
                                binding.edtChildroom1age4.setVisibility(View.VISIBLE);

                            } else if (s.toString().trim() == null) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add ddat leat one", Toast.LENGTH_SHORT).show();

                            } else {
                                Log.e("agee", "agee" + s);

                            }
                        }


                    }
                } catch (Exception E) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//                Toast.makeText(HotelDashBoardActivity.this, "Only Allow Less than 5", Toast.LENGTH_SHORT).show();


            }
        });

        binding.btnHotelsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.searchCity.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(HotelDashBoardActivity.this, "Please Select City", Toast.LENGTH_SHORT).show();

                } else if (binding.edtNight.getText().toString().trim().length() == 0) {
                    Toast.makeText(HotelDashBoardActivity.this, "Please Add No. of night", Toast.LENGTH_SHORT).show();

                } else {
                    if (binding.edtAdultroom1.getText().toString().trim().length() == 0) {
                        Toast.makeText(HotelDashBoardActivity.this, "Add Adult Count", Toast.LENGTH_SHORT).show();

                    } else if (binding.edtChildroom1.getText().toString().trim().length() == 0) {
                        Toast.makeText(HotelDashBoardActivity.this, "Add Child Count", Toast.LENGTH_SHORT).show();

                    } else {

                        if (binding.edtChildroom1.getText().toString().trim().equals("1")) {
                            if (binding.edtChildroom1age1.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child1 Age", Toast.LENGTH_SHORT).show();
                            } else {
                                if (Integer.valueOf(binding.edtChildroom1age1.getText().toString()) >= 18) {
                                    Toast.makeText(HotelDashBoardActivity.this, "Add  Age Below 18", Toast.LENGTH_SHORT).show();
                                } else {
                                    callhotelsearch();

                                }
                            }

                        } else if (binding.edtChildroom1.getText().toString().trim().equals("2")) {
                            if (binding.edtChildroom1age1.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child1 Age", Toast.LENGTH_SHORT).show();
                            } else if (binding.edtChildroom1age2.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child2 Age", Toast.LENGTH_SHORT).show();
                            } else {
                                if (Integer.valueOf(binding.edtChildroom1age1.getText().toString()) >= 18 || Integer.valueOf(binding.edtChildroom1age2.getText().toString()) >= 18) {
                                    Toast.makeText(HotelDashBoardActivity.this, "Add  Age Below 18", Toast.LENGTH_SHORT).show();
                                } else {
                                    callhotelsearch();
                                }

                            }
                        } else if (binding.edtChildroom1.getText().toString().trim().equals("3")) {
                            if (binding.edtChildroom1age1.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child1 Age", Toast.LENGTH_SHORT).show();
                            } else if (binding.edtChildroom1age2.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child2 Age", Toast.LENGTH_SHORT).show();

                            } else if (binding.edtChildroom1age3.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child3 Age", Toast.LENGTH_SHORT).show();
                            } else {


                                if (Integer.valueOf(binding.edtChildroom1age1.getText().toString()) >= 18 || Integer.valueOf(binding.edtChildroom1age2.getText().toString()) >= 18 || Integer.valueOf(binding.edtChildroom1age3.getText().toString()) >= 18) {
                                    Toast.makeText(HotelDashBoardActivity.this, "Add  Age Below 18", Toast.LENGTH_SHORT).show();
                                } else {
                                    callhotelsearch();

                                }
                            }
                        } else if (binding.edtChildroom1.getText().toString().trim().equals("4")) {
                            if (binding.edtChildroom1age1.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child1 Age", Toast.LENGTH_SHORT).show();
                            } else if (binding.edtChildroom1age2.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child2 Age", Toast.LENGTH_SHORT).show();

                            } else if (binding.edtChildroom1age3.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child3 Age", Toast.LENGTH_SHORT).show();

                            } else if (binding.edtChildroom1age4.getText().toString().trim().equals("")) {
                                Toast.makeText(HotelDashBoardActivity.this, "Add Child4 Age", Toast.LENGTH_SHORT).show();

                            } else {

                                if (Integer.valueOf(binding.edtChildroom1age1.getText().toString()) >= 18||Integer.valueOf(binding.edtChildroom1age2.getText().toString()) >= 18||Integer.valueOf(binding.edtChildroom1age3.getText().toString()) >= 18||Integer.valueOf(binding.edtChildroom1age4.getText().toString()) >= 18) {
                                    Toast.makeText(HotelDashBoardActivity.this, "Add  Age Below 18", Toast.LENGTH_SHORT).show();
                                } else {
                                    callhotelsearch();

                                }
                            }
                        } else {
                            callhotelsearch();
                        }
                    }

                }

            }
        });
        binding.viewHhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelDashBoardActivity.this, HotelBookingHistoryActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HotelDashBoardActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public class CityOrginAdapter extends RecyclerView.Adapter<HotelDashBoardActivity.CityOrginAdapter.UserViewHolder> {
        Context context;
        private ArrayList<Data> citylist_adapter = new ArrayList<>();

        Dialog dialog;

        public CityOrginAdapter(Context context, ArrayList<Data> citylist_adapter, Dialog dialog) {
            this.citylist_adapter = citylist_adapter;
            this.context = context;
            this.dialog = dialog;
        }

        @Override
        public HotelDashBoardActivity.CityOrginAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchcity, parent, false);
            return new HotelDashBoardActivity.CityOrginAdapter.UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HotelDashBoardActivity.CityOrginAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            Data datamodel = citylist_adapter.get(position);

            holder.city_name.setText(citylist_adapter.get(position).getCityName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shared = getSharedPreferences("nict", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("CountryCode", citylist_adapter.get(position).getCountryCode());
                    editor.putString("CityCode", citylist_adapter.get(position).getCityCode());
                    Log.e("sam.cityid...", "tyyy" + citylist_adapter.get(position).getCityCode());
                    getcityname = citylist_adapter.get(position).getCityName();
                    editor.apply();
                    binding.searchCity.setText(citylist_adapter.get(position).getCityName());
                    getcountrycode = citylist_adapter.get(position).getCountryCode();
                    getcity_code = citylist_adapter.get(position).getCityCode();
                    dialog.dismiss();
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

//    public class AdultSpinerAdapter extends RecyclerView.Adapter<AdultSpinerAdapter.UserViewHolder> {
//        String[] adultlist = {"1", "2", "3", "4", "5", "6", "7", "8"};
//        Context context;
//        ArrayList<AdultChildModel> adultlistspinner = new ArrayList<AdultChildModel>();
//
//        public AdultSpinerAdapter(Context context, ArrayList<AdultChildModel> adultlistspinner) {
//            this.adultlistspinner = adultlistspinner;
//            this.context = context;
//        }
//
//        @Override
//        public AdultSpinerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adult_spinner_layadapter, parent, false);
//            return new AdultSpinerAdapter.UserViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull AdultSpinerAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//            AdultChildModel adultChildModel = adultlistspinner.get(position);
//            ArrayAdapter spAdultAd = new ArrayAdapter(context, android.R.layout.simple_spinner_item, adultlist);
//            spAdultAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            holder.spinneradult.setAdapter(spAdultAd);
//            holder.spinneradult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    adultspinnercount = Integer.parseInt(adultlist[position]);
//                    Log.e("adultcount", "in" + adultspinnercount);
//                    AdultChildModel adultChildModel = new AdultChildModel();
//                    adultChildModel.setAdultchildno(String.valueOf(adultspinnercount));
//                    onclickadultadd.add(adultChildModel);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // can leave this empty
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return adultlistspinner.size();
//        }
//
//        public class UserViewHolder extends RecyclerView.ViewHolder {
//            Spinner spinneradult;
//
//            public UserViewHolder(View itemView) {
//                super(itemView);
//                spinneradult = itemView.findViewById(R.id.adapter_spAdult);
//            }
//        }
//
//    }

//    public class ChildSpinerAdapter extends RecyclerView.Adapter<ChildSpinerAdapter.UserViewHolder> {
//        String[] childlist = {"0", "1", "2", "3", "4"};
//        Context context;
//        ArrayList<AdultChildModel> childlistspinner = new ArrayList<AdultChildModel>();
//
//        public ChildSpinerAdapter(Context context, ArrayList<AdultChildModel> childlistspinner) {
//            this.childlistspinner = childlistspinner;
//            this.context = context;
//        }
//
//        @Override
//        public ChildSpinerAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_spinner_alyadapte, parent, false);
//            return new ChildSpinerAdapter.UserViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ChildSpinerAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//            AdultChildModel adultChildModel = childlistspinner.get(position);
////
//            ArrayAdapter spchild = new ArrayAdapter(context, android.R.layout.simple_spinner_item, childlist);
//            spchild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            holder.spinnerchild.setAdapter(spchild);
//            holder.spinnerchild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    childspinnercount = Integer.parseInt(childlist[position]);
//                    Log.e("childcountcount", "in" + childspinnercount);
//                    AdultChildModel adultChildModel = new AdultChildModel();
//                    adultChildModel.setAdultchildno(String.valueOf(childspinnercount));
//                    onclickchildadd.add(adultChildModel);
//
//
//                    if (childspinnercount == 0) {
//                        holder.edtchildage1.setVisibility(View.GONE);
//                        holder.edtchildage2.setVisibility(View.GONE);
//                        holder.edtchildage3.setVisibility(View.GONE);
//                        holder.edtchildage4.setVisibility(View.GONE);
//                    } else if (childspinnercount == 1) {
//                        holder.edtchildage1.setVisibility(View.VISIBLE);
//                        holder.edtchildage2.setVisibility(View.GONE);
//                        holder.edtchildage3.setVisibility(View.GONE);
//                        holder.edtchildage4.setVisibility(View.GONE);
//
//                    } else if (childspinnercount == 2) {
//                        holder.edtchildage1.setVisibility(View.VISIBLE);
//                        holder.edtchildage2.setVisibility(View.VISIBLE);
//                        holder.edtchildage3.setVisibility(View.GONE);
//                        holder.edtchildage4.setVisibility(View.GONE);
//                    } else if (childspinnercount == 3) {
//                        holder.edtchildage1.setVisibility(View.VISIBLE);
//                        holder.edtchildage2.setVisibility(View.VISIBLE);
//                        holder.edtchildage3.setVisibility(View.VISIBLE);
//                        holder.edtchildage4.setVisibility(View.GONE);
//
//                    } else if (childspinnercount == 4) {
//                        holder.edtchildage1.setVisibility(View.VISIBLE);
//                        holder.edtchildage2.setVisibility(View.VISIBLE);
//                        holder.edtchildage3.setVisibility(View.VISIBLE);
//                        holder.edtchildage4.setVisibility(View.VISIBLE);
//                    }
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // can leave this empty
//                }
//            });
//
//
//            holder.edtchildage1.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    edtage1 = s.toString();
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    edtage1 = s.toString();
//
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
//
//            holder.edtchildage2.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    edtage2 = s.toString();
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    edtage2 = s.toString();
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
//            holder.edtchildage3.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    edtage3 = s.toString();
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    edtage3 = s.toString();
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
//
//            holder.edtchildage4.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                    edtage4 = s.toString();
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    edtage4 = s.toString();
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return childlistspinner.size();
//        }
//
//        public class UserViewHolder extends RecyclerView.ViewHolder {
//
//            Spinner spinnerchild;
//            EditText edtchildage1, edtchildage2, edtchildage3, edtchildage4;
//
//            public UserViewHolder(View itemView) {
//                super(itemView);
//                spinnerchild = itemView.findViewById(R.id.adapter_spChild);
//                edtchildage1 = itemView.findViewById(R.id.edt_childage1);
//                edtchildage2 = itemView.findViewById(R.id.edt_childage2);
//                edtchildage3 = itemView.findViewById(R.id.edt_childage3);
//                edtchildage4 = itemView.findViewById(R.id.edt_childage4);
//
//            }
//        }
//    }

    public void callhotelsearch() {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setCheckInDate(binding.tvCheckindate.getText().toString().trim());
        hotelSearchRequest.setNoOfNights(binding.edtNight.getText().toString().trim());
        hotelSearchRequest.setCountryCode(getcountrycode);
        hotelSearchRequest.setCityId(getcity_code);
        Log.e("1..", "city" + getcity_code);
        hotelSearchRequest.setResultCount(3);
        hotelSearchRequest.setPreferredCurrency("INR");
        hotelSearchRequest.setGuestNationality(getcountrycode);
        hotelSearchRequest.setNoOfRooms(String.valueOf(roomscount));
        roomGuest = new RoomGuest();
        requestmodel = new RoomRequest();
        arrayList = new ArrayList<>();
        addroomreqlist = new ArrayList<RoomRequest>();

        //my

//        if (roomcountno == 1) {
//
//            if (childspinnercount == 0) {
//                //CASE1  search
//                roomGuest.setNoOfAdults(adultspinnercount);
//                roomGuest.setNoOfChild(childspinnercount);
//
//                //my
//                requestmodel.setNoOfAdults(adultspinnercount);
//                requestmodel.setNoOfChild(childspinnercount);
//                Integer[] strAr4 = {null};
//                requestmodel.setChildAge(strAr4);
//
//                arrayList.add(roomGuest);
//                addroomreqlist.add(requestmodel);
//            } else {
//                //CASE2
//                roomGuest.setNoOfAdults(adultspinnercount);
//                roomGuest.setNoOfChild(childspinnercount);
//
//                //my
//                requestmodel.setNoOfAdults(adultspinnercount);
//                requestmodel.setNoOfChild(childspinnercount);
//
//
//                if (childspinnercount == 1) {
//                    Integer[] strAr4 = {Integer.valueOf(edtage1)};
//                    roomGuest.setChildAge(strAr4);
//
//                    //my
//                    requestmodel.setChildAge(strAr4);
//                } else if (childspinnercount == 2) {
//                    Integer[] strAr4 = {Integer.valueOf(edtage1), Integer.parseInt(edtage2)};
//                    roomGuest.setChildAge(strAr4);
//
//                    //my
//                    requestmodel.setChildAge(strAr4);
//
//                } else if (childspinnercount == 3) {
//                    Integer[] strAr4 = {Integer.valueOf(edtage1), Integer.valueOf(edtage2), Integer.valueOf(edtage3)};
//                    roomGuest.setChildAge(strAr4);
//
//                    //my
//                    requestmodel.setChildAge(strAr4);
//
//                } else if (childspinnercount == 4) {
//                    Integer[] strAr4 = {Integer.valueOf(edtage1), Integer.valueOf(edtage2), Integer.valueOf(edtage3), Integer.valueOf(edtage4)};
//                    roomGuest.setChildAge(strAr4);
//                    //my
//                    requestmodel.setChildAge(strAr4);
//
//                }
//
//                arrayList.add(roomGuest);
//                addroomreqlist.add(requestmodel);
//            }
//
//        } else {
//            for (int i = 0; i < roomcountno; i++) {
//
//                if (childspinnercount == 0) {
//                    //CASE3
//                    roomGuest.setNoOfAdults(adultspinnercount);
//                    roomGuest.setNoOfChild(childspinnercount);
//                    //my
//                    requestmodel.setNoOfAdults(adultspinnercount);
//                    requestmodel.setNoOfChild(childspinnercount);
//
//                    Integer[] strAr4 = {null};
//                    requestmodel.setChildAge(strAr4);
//
//
//                } else if (childspinnercount == 1 && adultspinnercount == 1) {
//                    //case5
//                    roomGuest.setNoOfAdults(adultspinnercount);
//                    roomGuest.setNoOfChild(childspinnercount);
//                    Integer[] strAr4 = {Integer.valueOf(edtage1)};
//                    roomGuest.setChildAge(strAr4);
//                    //my
//                    requestmodel.setNoOfAdults(adultspinnercount);
//                    requestmodel.setNoOfChild(childspinnercount);
//
//                    Integer[] strAr5 = {Integer.valueOf(edtage1)};
//                    requestmodel.setChildAge(strAr5);
//
//                } else {
//                }
//                arrayList.add(roomGuest);
//                addroomreqlist.add(requestmodel);
//
//
//            }
//
//        }

//sarvesh
        if (roomcountno == 1) {
            roomGuest.setNoOfAdults(Integer.valueOf(binding.edtAdultroom1.getText().toString().trim()));
            roomGuest.setNoOfChild(Integer.valueOf(binding.edtChildroom1.getText().toString().trim()));
            //
            requestmodel.setNoOfAdults(Integer.valueOf(binding.edtAdultroom1.getText().toString().trim()));
            requestmodel.setNoOfChild(Integer.valueOf(binding.edtChildroom1.getText().toString().trim()));

            if (binding.edtChildroom1.getText().toString().trim().equals("0")) {
                Integer[] strAr4 = {null};
                requestmodel.setChildAge(strAr4);
            } else if (binding.edtChildroom1.getText().toString().trim().equals("1")) {
                Integer[] strAr4 = {Integer.valueOf(binding.edtChildroom1age1.getText().toString().trim())};
                roomGuest.setChildAge(strAr4);
                requestmodel.setChildAge(strAr4);

            } else if (binding.edtChildroom1.getText().toString().trim().equals("2")) {
                Integer[] strAr4 = {Integer.valueOf(binding.edtChildroom1age1.getText().toString().trim()), Integer.valueOf(binding.edtChildroom1age2.getText().toString().trim())};
                roomGuest.setChildAge(strAr4);
                requestmodel.setChildAge(strAr4);

            } else if (binding.edtChildroom1.getText().toString().trim().equals("3")) {

                Integer[] strAr4 = {Integer.valueOf(binding.edtChildroom1age1.getText().toString().trim()), Integer.valueOf(binding.edtChildroom1age2.getText().toString().trim()), Integer.valueOf(binding.edtChildroom1age3.getText().toString().trim())};
                roomGuest.setChildAge(strAr4);
                requestmodel.setChildAge(strAr4);

            } else if (binding.edtChildroom1.getText().toString().trim().equals("4")) {
                Integer[] strAr4 = {Integer.valueOf(binding.edtChildroom1age1.getText().toString().trim()), Integer.valueOf(binding.edtChildroom1age2.getText().toString().trim()), Integer.valueOf(binding.edtChildroom1age3.getText().toString().trim()), Integer.valueOf(binding.edtChildroom1age4.getText().toString().trim())};
                roomGuest.setChildAge(strAr4);
                requestmodel.setChildAge(strAr4);
            }

            arrayList.add(roomGuest);
            addroomreqlist.add(requestmodel);


        } else {

        }
        hotelSearchRequest.setMaxRating(5);
        hotelSearchRequest.setMinRating(0);
        hotelSearchRequest.setReviewScore(3);
        hotelSearchRequest.setIsNearBySearchAllowed(false);
        hotelSearchRequest.setEndUserIp("");
        hotelSearchRequest.setTokenId(gettoken);
        hotelSearchRequest.setIsmapped("true");
        hotelSearchRequest.setRoomGuests(arrayList);
        callhotel_search(hotelSearchRequest);
    }

    public void callhotel_search(HotelSearchRequest hotelSearchRequest) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelSearchResponseModel> responseBodyCall = retroFitInterface.hotelsearch(hotelSearchRequest);
        try {
            responseBodyCall.enqueue(new Callback<HotelSearchResponseModel>() {
                @Override
                public void onResponse(Call<HotelSearchResponseModel> call, Response<HotelSearchResponseModel> response) {
                    progressDialogDismiss();
                    if (response.code() == 200) {
                        hotelSearchResponseModel = response.body();
                        if (hotelSearchResponseModel.getHotelSearchResult().getResponseStatus() == 1) {
                            Error error = new Error();

                            if (error.getErrorCode() == 0) {
                                shared = getSharedPreferences("nict", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(hotelSearchResponseModel); // myObject - instance of MyObject
                                editor.putString("Hotelsearch", json);
                                editor.putString("TraceId", hotelSearchResponseModel.getHotelSearchResult().getTraceId());


                                    editor.putString("Adultno", binding.edtAdultroom1.getText().toString().trim());
                                    editor.putString("childno",  binding.edtChildroom1.getText().toString().trim());
                                    Log.e("Adultno","dash"+binding.edtAdultroom1.getText().toString().trim());
                                    Log.e("childno","dash"+binding.edtChildroom1.getText().toString().trim());


                                editor.putString("roomnocount", String.valueOf(roomscount));
                                editor.putString("NOOFNIGHT", binding.edtNight.getText().toString().trim());
                                editor.putString("checkindate", binding.tvCheckindate.getText().toString().trim());
                                editor.putString("checkoutdate", binding.tvCheckoutdate.getText().toString().trim());
                                editor.putString("cityname", getcityname);
                                editor.apply();
                                Intent intent = new Intent(HotelDashBoardActivity.this, HotelListShowActivity.class);
                                startActivity(intent);
                            } else if (error.getErrorCode() == 2) {
                                Toast.makeText(HotelDashBoardActivity.this, error.getErrorCode(), Toast.LENGTH_SHORT).show();

                            } else if (error.getErrorCode() == 3) {
                                Toast.makeText(HotelDashBoardActivity.this, error.getErrorCode(), Toast.LENGTH_SHORT).show();

                            }

                        } else if (hotelSearchResponseModel.getHotelSearchResult().getResponseStatus() == 2) {
                            Toast.makeText(HotelDashBoardActivity.this, "No Result Found", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(HotelDashBoardActivity.this, "No Response", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(HotelDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<HotelSearchResponseModel> call, Throwable t) {
                    progressDialogDismiss();
                    Log.e("reeorr+", t.getMessage());
                    Log.e("reeorr+", t.toString());
                    Log.e("reeorr+", call.toString());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}