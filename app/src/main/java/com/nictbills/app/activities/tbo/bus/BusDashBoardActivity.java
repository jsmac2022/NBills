package com.nictbills.app.activities.tbo.bus;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.bus.model.authresmodel.BusAuthResponseModel;
import com.nictbills.app.activities.tbo.bus.model.buscitylist.BusCity;
import com.nictbills.app.activities.tbo.bus.model.buscitylist.BusCityListResponsemodel;
import com.nictbills.app.activities.tbo.bus.model.bussearchresmodel.BusSearchResponseModel;
import com.nictbills.app.activities.tbo.bus.model.bussearchresmodel.Error;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityBusDashBoardBinding;

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

public class BusDashBoardActivity extends BaseActivity {
    ActivityBusDashBoardBinding binding;
    private ArrayList<BusCity> citylist = new ArrayList<>();
    CityOrginAdapter cityOrginAdapter;
    String getcountrycode, getcity_code, getorigin_cityid, getdesti_cityid;
    SharedPreferences shared;
    String layouttype = "";
    HashMap<String, String> param;
    HashMap<String, String> paramAgency;
    HashMap<String, String> paramsearchbus;
    HashMap<String, String> parambuscity;
    String checkindate, getmemberid, agencyid, gettoken, adultCount, children, roomscount, nationlity_count, star_count;
    RecyclerView rvshowlist;
    String[] passenger = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bus_dash_board);
        shared = getSharedPreferences("nict", MODE_PRIVATE);
        param = new HashMap<>();
        param.put("EndUserIp", "");
        param.put("ClientId", "ApiIntegrationNew");
        param.put("UserName", "Mahendratech");
        param.put("Password", "Mahe@1234");
        date();
        click();
        spinner();
    }

    public void spinner()
    {
        ArrayAdapter spAdultAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, passenger);
        spAdultAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spAdult.setAdapter(spAdultAd);
        binding.spAdult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adultCount = passenger[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // can leave this empty
            }
        });
    }
    public void date() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todaydate = dateFormat.format(today);
        binding.tvBusddate.setText(todaydate);
    }

    public void click() {
        get_Busauth_token(param);
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        binding.imgViewhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusDashBoardActivity.this, BusBookignHistoryActivity.class);
                startActivity(intent);
            }
        });

        binding.tvBusddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(BusDashBoardActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = "1";
//
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
                                    Log.e("if monthOfYear", "" + finalmonth);
//                                    binding.tvBusddate.setText(date + "/" + (monthOfYear + 1) + "/" + year);
                                    binding.tvBusddate.setText(year + "-" + finalmonthsum + "-" + date);

                                } else {
//                                    binding.tvBusddate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                                    binding.tvBusddate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                    Log.e("if monthOfYear else ", "" + finalmonth);

                                    binding.tvBusddate.setText(year + "-" + finalmonthsum + "-" + dayOfMonth);

                                }
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.show();
            }
        });
        binding.tvbusOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(BusDashBoardActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.search_origincity);
                rvshowlist = (RecyclerView) dialog.findViewById(R.id.rv_citylist);
                TextView datanotfund = (TextView) dialog.findViewById(R.id.datanotfound);
                EditText edrsearch = (EditText) dialog.findViewById(R.id.et_search_latpanel);
                dialog.show();
                layouttype = "orgin";
                edrsearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() >0) {
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

//                        Log.e("1", "on beforeTextChanged" + s.toString());
//                        if (s.length() >= 2) {
//                            datanotfund.setVisibility(View.GONE);
//                            rvshowlist.setVisibility(View.VISIBLE);
//                            filter(s.toString());
//                        } else {
//                            rvshowlist.setVisibility(View.GONE);
//                            datanotfund.setVisibility(View.VISIBLE);
//                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.e("1", "on afterTextChanged" + s.toString());
                        if (s.length() >=0) {
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
                parambuscity = new HashMap<>();
                parambuscity.put("TokenId", gettoken);
                parambuscity.put("IpAddress", "");
                parambuscity.put("ClientId", "ApiIntegrationNew");
                Log.e("gettoken", "1" + gettoken);
                progressDialogShow();

                RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
                Call<BusCityListResponsemodel> call = retroFitInterface.getbuscity(parambuscity);
                call.enqueue(new Callback<BusCityListResponsemodel>() {
                    @Override
                    public void onResponse(Call<BusCityListResponsemodel> call, Response<BusCityListResponsemodel> response) {
                        progressDialogDismiss();
                        BusCityListResponsemodel busCityListResponsemodel = response.body();
                        if (response.code() == 200) {
                            for (int j = 0; j < busCityListResponsemodel.getBusCities().size(); j++) {
                                BusCity busCity = new BusCity();
//                                data.setCityName(flightCityList.getData().get(j).getCityName());
//                                data.setCityCodeWord(flightCityList.getData().get(j).getCityCodeWord());
//                                data.setCityCode(flightCityList.getData().get(j).getCityCode());
                                busCity.setCityId(busCityListResponsemodel.getBusCities().get(j).getCityId());
                                busCity.setCityName(busCityListResponsemodel.getBusCities().get(j).getCityName());
                                citylist.add(busCity);
                            }
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvshowlist.setLayoutManager(mLayoutManager);
                            cityOrginAdapter = new BusDashBoardActivity.CityOrginAdapter(getApplicationContext(), citylist, dialog);
                            rvshowlist.setAdapter(cityOrginAdapter);
                        } else if (response.code() == 400) {
                            Toast.makeText(BusDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BusDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<BusCityListResponsemodel> call, Throwable t) {
                        progressDialogDismiss();
                        Log.e("nictfailur", "onResponse: " + t.toString());

                    }
                });


            }
        });
        binding.tvbusDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(BusDashBoardActivity.this);
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
                        if (s.length() >=0) {
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
                        if (s.length() >=0) {
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
                        if (s.length() >0) {
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
                parambuscity = new HashMap<>();
                parambuscity.put("TokenId", gettoken);
                parambuscity.put("IpAddress", "");
                parambuscity.put("ClientId", "ApiIntegrationNew");
                Log.e("gettoken34", "1" + gettoken);
                progressDialogShow();
                RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
                Call<BusCityListResponsemodel> call = retroFitInterface.getbuscity(parambuscity);
                call.enqueue(new Callback<BusCityListResponsemodel>() {
                    @Override
                    public void onResponse(Call<BusCityListResponsemodel> call, Response<BusCityListResponsemodel> response) {
                        progressDialogDismiss();
                        BusCityListResponsemodel busCityListResponsemodel = response.body();

                        if (response.code() == 200) {

                            for (int j = 0; j < busCityListResponsemodel.getBusCities().size(); j++) {
                                BusCity busCity = new BusCity();
//                                data.setCityName(flightCityList.getData().get(j).getCityName());
//                                data.setCityCodeWord(flightCityList.getData().get(j).getCityCodeWord());
//                                data.setCityCode(flightCityList.getData().get(j).getCityCode());
//                                data.setCountryName(flightCityList.getData().get(j).getCountryName());
//                                data.setCountryCode(flightCityList.getData().get(j).getCountryCode());
                                busCity.setCityId(busCityListResponsemodel.getBusCities().get(j).getCityId());
                                busCity.setCityName(busCityListResponsemodel.getBusCities().get(j).getCityName());
                                citylist.add(busCity);
                            }
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvshowlist.setLayoutManager(mLayoutManager);
                            cityOrginAdapter = new BusDashBoardActivity.CityOrginAdapter(getApplicationContext(), citylist, dialog);
                            rvshowlist.setAdapter(cityOrginAdapter);
                        } else if (response.code() == 400) {
                            Toast.makeText(BusDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BusDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BusCityListResponsemodel> call, Throwable t) {
                        progressDialogDismiss();
                        Log.e("nictfailur", "onResponse: " + t.toString());

                    }
                });

            }
        });
        binding.btnBussearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( binding.tvbusOrigin.getText().toString().trim()==null&& binding.tvbusDest.getText().toString().trim()==null)
                {
                    Toast.makeText(getApplicationContext(),"Enter Origin and Destination Location",Toast.LENGTH_SHORT).show();
                }
                else if( binding.tvbusOrigin.getText().toString().trim().isEmpty()&& binding.tvbusDest.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Origin and Destination Location",Toast.LENGTH_SHORT).show();

                }
                else if( binding.tvbusOrigin.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Origin..",Toast.LENGTH_SHORT).show();

                }
                else if( binding.tvbusDest.getText().toString().trim().isEmpty())
                {
//                    destination.setError("Enter Destinaiion");
                    Toast.makeText(getApplicationContext(),"Enter Destinaiion..",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    paramsearchbus = new HashMap<>();
                    paramsearchbus.put("DateOfJourney", binding.tvBusddate.getText().toString().trim());
                    paramsearchbus.put("DestinationId", getdesti_cityid);
//                    paramsearchbus.put("DestinationId", "9771");
                    paramsearchbus.put("EndUserIp", "");                 Log.e("",""+getdesti_cityid);
                    paramsearchbus.put("OriginId", getorigin_cityid);
//                    paramsearchbus.put("OriginId", "3534");
                    paramsearchbus.put("TokenId", gettoken);
                    paramsearchbus.put("PreferredCurrency", "INR");
                    Log.e("getorigin","1"+getorigin_cityid);
                    Log.e("getdesti_cityid","2"+getdesti_cityid);

                    Bus_search(paramsearchbus);
                }




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
//                        Toast.makeText(HotelDashBoardActivity.this, "gettoken", Toast.LENGTH_SHORT).show();
                        gettoken = response.body().getTokenId();
                        agencyid = String.valueOf(busAuthResponseModel.getMember().getAgencyId());
                        getmemberid = String.valueOf(busAuthResponseModel.getMember().getMemberId());

                        shared = getSharedPreferences("nict", MODE_PRIVATE);
                        Log.e("busauth", "token: " + gettoken);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("TokenBus", gettoken);
                        editor.putString("Agencyid", agencyid);
                        editor.putString("Memberid", getmemberid);
                        editor.apply();
                        paramAgency = new HashMap<>();
                        paramAgency.put("EndUserIp", "");
                        paramAgency.put("ClientId", "ApiIntegrationNew");
                        paramAgency.put("TokenAgencyId", agencyid);
                        paramAgency.put("TokenMemberId", getmemberid);
                        paramAgency.put("TokenId", gettoken);

//                        parambuscity = new HashMap<>();
//                        parambuscity.put("TokenId",gettoken);
//                        parambuscity.put("IpAddress","");
//                        parambuscity.put("ClientId","ApiIntegrationNew");
//                        getcity(param);
//                        get_AgencyBalance(paramAgency);
                    } else if (response.body().getStatus() == 2) {
                        Toast.makeText(BusDashBoardActivity.this, "IP Address is not in correct format", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BusDashBoardActivity.this, "Response Technical Isuue", Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(BusDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } else if (response.code() == 503) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(BusDashBoardActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BusAuthResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(BusDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                Log.e("nictfailur", "onResponse: ");
                onBackPressed();

            }
        });
    }

//    public void get_AgencyBalance(HashMap<String, String> paramAgency) {
//        progressDialogShow();
//        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
//        Call<BusAgencyResponseModel> call = retroFitInterface.getBus_AgencyBalance(paramAgency);
//        call.enqueue(new Callback<BusAgencyResponseModel>() {
//            @Override
//            public void onResponse(Call<BusAgencyResponseModel> call, Response<BusAgencyResponseModel> response) {
//                progressDialogDismiss();
//                BusAgencyResponseModel busAgencyResponseModel = response.body();
//                Log.e("agenccall", "onResponse: ");
//
//                if (response.code() == 200) {
//
//                    if (response.body().getStatus() == 1) {
//
//                        Log.e("agencysttaus", "1" + busAgencyResponseModel.getStatus());
//                    } else if (response.body().getStatus() == 2) {
//                        Toast.makeText(BusDashBoardActivity.this, "IP Address is not in correct format", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(BusDashBoardActivity.this, "Response Technical Isuue", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } else if (response.code() == 401) {
//                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();
//
//                } else if (response.code() == 500) {
//                    Toast.makeText(BusDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
//                    onBackPressed();
//
//                } else if (response.code() == 503) {
//                    Toast.makeText(BusDashBoardActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();
//
//                } else if (response.code() == 400) {
//                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BusAgencyResponseModel> call, Throwable t) {
//                progressDialogDismiss();
//                Toast.makeText(BusDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
//                Log.e("agecnyfailue", "onResponse: ");
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BusDashBoardActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }


    public class CityOrginAdapter extends RecyclerView.Adapter<BusDashBoardActivity.CityOrginAdapter.UserViewHolder> {
        Context context;
        private ArrayList<BusCity> citylist_adapter = new ArrayList<>();
        Dialog dialog;

        public CityOrginAdapter(Context context, ArrayList<BusCity> citylist_adapter, Dialog dialog) {
            this.citylist_adapter = citylist_adapter;
            this.context = context;
            this.dialog = dialog;
        }

        @Override
        public BusDashBoardActivity.CityOrginAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchcity, parent, false);
            return new BusDashBoardActivity.CityOrginAdapter.UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BusDashBoardActivity.CityOrginAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            BusCity datamodel = citylist_adapter.get(position);

            holder.city_name.setText(citylist_adapter.get(position).getCityName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (layouttype.equalsIgnoreCase("orgin")) {
                        shared = getSharedPreferences("nict", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("CityCode", String.valueOf(citylist_adapter.get(position).getCityId()));
                        editor.apply();
                        binding.tvbusOrigin.setText(citylist_adapter.get(position).getCityName());
                        Log.e("cityid", "1" + citylist_adapter.get(position).getCityId());
                        getorigin_cityid = String.valueOf(citylist_adapter.get(position).getCityId());
                        dialog.dismiss();
                    } else if (layouttype.equalsIgnoreCase("destination")) {
                        shared = getSharedPreferences("nict", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("CityCode", String.valueOf(citylist_adapter.get(position).getCityId()));
                        editor.apply();
                        binding.tvbusDest.setText(citylist_adapter.get(position).getCityName());
                        getdesti_cityid = String.valueOf(citylist_adapter.get(position).getCityId());
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

        public void updateList(ArrayList<BusCity> list) {
            citylist_adapter = list;
            notifyDataSetChanged();
        }

    }
    public void filter(String text) {
        ArrayList<BusCity> temp = new ArrayList();
        for (BusCity d : citylist) {
            if (text.length() >= 1) {
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

    public void Bus_search(HashMap<String, String> paramsearchbus) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusSearchResponseModel> call = retroFitInterface.bussearch(paramsearchbus);
        call.enqueue(new Callback<BusSearchResponseModel>() {
            @Override
            public void onResponse(Call<BusSearchResponseModel> call, Response<BusSearchResponseModel> response) {
                progressDialogDismiss();
                BusSearchResponseModel busSearchResponseModel = response.body();
                Log.e("bussearch", "onResponse: ");
                if (response.code() == 200) {
                    Log.e("bussearchworking", "onResponse: ");
                    if (busSearchResponseModel.getBusSearchResult().getResponseStatus() == 1)
                    {
                        if (busSearchResponseModel.getBusSearchResult().getError().getErrorCode()==0) {
                            shared = getSharedPreferences("nict", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(busSearchResponseModel); // myObject - instance of MyObject
                            editor.putString("Bussearch", json);
                            editor.putString("TraceId", busSearchResponseModel.getBusSearchResult().getTraceId());
//                            editor.putString("Adultno", adultCount);
                            editor.apply();
                            Intent intent = new Intent(BusDashBoardActivity.this, BusListShowActivity.class);
                            startActivity(intent);

                        }
                        else if(busSearchResponseModel.getBusSearchResult().getError().getErrorCode()==25)
                        {
                            Toast.makeText(BusDashBoardActivity.this, busSearchResponseModel.getBusSearchResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(BusDashBoardActivity.this, busSearchResponseModel.getBusSearchResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                        Toast.makeText(BusDashBoardActivity.this, busSearchResponseModel.getBusSearchResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else if (response.code() == 401) {
                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
                    Toast.makeText(BusDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } else if (response.code() == 503) {
                    Toast.makeText(BusDashBoardActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 404) {
                    Toast.makeText(BusDashBoardActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BusSearchResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(BusDashBoardActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                Log.e("agecnyfailue", "onResponse: ");

            }
        });
    }
}