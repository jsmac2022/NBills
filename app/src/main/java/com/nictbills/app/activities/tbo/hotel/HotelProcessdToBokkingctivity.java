package com.nictbills.app.activities.tbo.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.flight.FlightTicketActivity;
import com.nictbills.app.activities.tbo.flight.adapter.GetPassengerAdapter;
import com.nictbills.app.activities.tbo.flight.model.citylist.Data;
import com.nictbills.app.activities.tbo.flight.model.citylist.FlightCityList;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.GetPassengerResponsemodel;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelPassengerListAdapter;
import com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.AddPassenger;
import com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.HotelGetPassengerListResmodel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelBlockRoomRequestmodel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelRoomsDetail;
import com.nictbills.app.activities.tbo.hotel.model.orderresmodel.HotelOrderResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityHotelProcessdToBokkingctivityBinding;
import com.nictbills.app.utils.Util;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelProcessdToBokkingctivity extends BaseActivity {
    SharedPreferences shared, sharedPreferences;
    String getpassenger_id, getnorms, getpolicy, mobileNumber, gettds, getuserlist, getagentmarlup, getagentcommision, getservicetax, getdiscount, getoffer_roundoffprice, getpublish_roundoffprice, getofferprice, getpublishprice, getother_charge, getchiled_charge, getguestextracharge, gettrace_id, getCurrency_Code, getRoom_price, gettax, getRoom_Desc, getcountry_code, getRoom_PlanName, getRoom_Plancode, getRoom_TypeCode, getRoom_TypeName, gethotel_roomindex, getagency_id, getcity_code, gethotel_index, gethotel_name, gethotel_code, gettokenhotel_id;
    ActivityHotelProcessdToBokkingctivityBinding binding;
    HashMap<String, String> getpassenger;
    ArrayList<AddPassenger> hotelplist = new ArrayList<AddPassenger>();
    HotelPassengerListAdapter hotelPassengerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hotel_processd_to_bokkingctivity);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_processd_to_bokkingctivity);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettrace_id = shared.getString("TraceId", "");
        gethotel_index = shared.getString("Hotelindex", "");
        gethotel_name = shared.getString("HotelName", "");
        getRoom_Desc = shared.getString("Roomdesc", "");
        getRoom_PlanName = shared.getString("RoomPlanName", "");

        getpassenger_id = shared.getString("passengerid", "");
        getnorms = shared.getString("norms", "");
        getpolicy = shared.getString("policy", "");
        String yourString = getnorms;
        String finaldata = Jsoup.parse(yourString).text();
        binding.hotelNorms.setText(finaldata);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        click();
        binding.roomDesc.setText(getRoom_PlanName);
        binding.roomType.setText(getRoom_Desc);
        binding.hotelname.setText(gethotel_name);
        getpassenger = new HashMap<>();
        getpassenger.put("mobileNo", mobileNumber);
        getpassenger.put("resultIndex", gethotel_index);
        getpassenger.put("traceId", gettrace_id);
        getpassenger(getpassenger);

    }

    public void click() {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.addHotelMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HotelProcessdToBokkingctivity.this, HotelPaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getpassenger(HashMap<String, String> getpassenger) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelGetPassengerListResmodel> call = retroFitInterface.gethotelpassenger(getpassenger);
        call.enqueue(new Callback<HotelGetPassengerListResmodel>() {
            @Override
            public void onResponse(Call<HotelGetPassengerListResmodel> call, Response<HotelGetPassengerListResmodel> response) {
                progressDialogDismiss();
                HotelGetPassengerListResmodel hotelGetPassengerListResmodel = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code() == 200) {

                    if (hotelGetPassengerListResmodel.getResponseCode() == 200) {

                        if (hotelGetPassengerListResmodel.getData().getAddPassenger().size() > 0) {
                            for (int j = 0; j < hotelGetPassengerListResmodel.getData().getAddPassenger().size(); j++) {

                                AddPassenger addPassenger = new AddPassenger();
                                addPassenger.setFirstName(hotelGetPassengerListResmodel.getData().getAddPassenger().get(j).getFirstName());
                                addPassenger.setEmail(hotelGetPassengerListResmodel.getData().getAddPassenger().get(j).getEmail());
                                addPassenger.setAge(hotelGetPassengerListResmodel.getData().getAddPassenger().get(j).getAge());
                                hotelplist.add(addPassenger);
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            binding.recyclerViewHoteLPlist.setLayoutManager(linearLayoutManager);
                            hotelPassengerListAdapter = new HotelPassengerListAdapter(HotelProcessdToBokkingctivity.this, hotelplist);
                            binding.recyclerViewHoteLPlist.setAdapter(hotelPassengerListAdapter);
                        } else {
                            Toast.makeText(HotelProcessdToBokkingctivity.this, "Dat Not Found", Toast.LENGTH_SHORT).show();

                        }

                    } else if (hotelGetPassengerListResmodel.getResponseCode() == 404) {

                    }
                } else {

                    Toast.makeText(HotelProcessdToBokkingctivity.this, "Failur Response", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<HotelGetPassengerListResmodel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(HotelProcessdToBokkingctivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());


            }
        });
    }

}