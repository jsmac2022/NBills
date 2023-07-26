package com.nictbills.app.activities.tbo.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.bus.BusBookignHistoryActivity;
import com.nictbills.app.activities.tbo.bus.model.buscancelticket.BusCancelModelResponse;
import com.nictbills.app.activities.tbo.flight.FlightBookingHistory;
import com.nictbills.app.activities.tbo.flight.adapter.FlightViewHistoryAdapter;
import com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory.FlightHistoryModelResponse;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.GetPassengerResponsemodel;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelViewHistoryAdapter;
import com.nictbills.app.activities.tbo.hotel.model.hotelcancel.HotelCancelChangeResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel.Data;
import com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel.HotelHistoryModelResponse;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityHotelBookingHistoryBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelBookingHistoryActivity extends BaseActivity  implements HotelViewHistoryAdapter.OnItemClickListener {
ActivityHotelBookingHistoryBinding binding;
    SharedPreferences shared, sharedPreferences;
    String mobileNumber;
    HashMap<String, String> viewhistoryparam;
    ArrayList<Data> viewhisotylist = new ArrayList();
    HashMap<String, String> paramhotelcasncel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hotel_booking_history);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_hotel_booking_history);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        viewhistoryparam = new HashMap<>();
        viewhistoryparam.put("mobileNo", mobileNumber);
        view_history(viewhistoryparam);

        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void view_history(HashMap<String, String> viewhistoryparam) {
        progressDialogShow();

        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelHistoryModelResponse> call = retroFitInterface.hotelhistory(viewhistoryparam);
        call.enqueue(new Callback<HotelHistoryModelResponse>() {
            @Override
            public void onResponse(Call<HotelHistoryModelResponse> call, Response<HotelHistoryModelResponse> response) {
                progressDialogDismiss();
                HotelHistoryModelResponse hotelHistoryModelResponse = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code()==200)
                {
                    if (hotelHistoryModelResponse.getResponseCode()==200) {
                        if (hotelHistoryModelResponse.getData().size() > 0) {
                            for (int j = 0; j < hotelHistoryModelResponse.getData().size(); j++) {
//                                Data data= new Data();
                                Data data= new Data();
                                data.setBookingId(hotelHistoryModelResponse.getData().get(j).getBookingId());
                                data.setOrderId(hotelHistoryModelResponse.getData().get(j).getOrderId());
                                data.setHotelName(hotelHistoryModelResponse.getData().get(j).getHotelName());
                                data.setHotelCode(hotelHistoryModelResponse.getData().get(j).getHotelCode());
                                data.setConfirmationNo(hotelHistoryModelResponse.getData().get(j).getConfirmationNo());
                                data.setBookingReferenceNo(hotelHistoryModelResponse.getData().get(j).getBookingReferenceNo());
                                data.setPublishedPrice(hotelHistoryModelResponse.getData().get(j).getPublishedPrice());
                                data.setHotelBookingStatus(hotelHistoryModelResponse.getData().get(j).getHotelBookingStatus());
                                data.setId(hotelHistoryModelResponse.getData().get(j).getId());
                                data.setIscanceled(hotelHistoryModelResponse.getData().get(j).isIscanceled());
                                data.setTokenid(hotelHistoryModelResponse.getData().get(j).getTokenid());
                                data.setEdnuser_ip(hotelHistoryModelResponse.getData().get(j).getEdnuser_ip());
                                viewhisotylist.add(data);
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            binding.recyclerViewHotelviewhistory.setLayoutManager(linearLayoutManager);
                            HotelViewHistoryAdapter hotelViewHistoryAdapter = new HotelViewHistoryAdapter(HotelBookingHistoryActivity.this, viewhisotylist ,HotelBookingHistoryActivity.this);
                            binding.recyclerViewHotelviewhistory.setAdapter(hotelViewHistoryAdapter);
                        }
                        else
                        {
                            Toast.makeText(HotelBookingHistoryActivity.this, "Data Not Found ", Toast.LENGTH_SHORT).show();

                        }

                    } else if (hotelHistoryModelResponse.getResponseCode() == 404) {
                        Toast.makeText(HotelBookingHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(HotelBookingHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HotelHistoryModelResponse> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(HotelBookingHistoryActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(String bookingid, String id ,String token_id ,String enduser_ip) {

        paramhotelcasncel = new HashMap<>();
        paramhotelcasncel.put("BookingMode", "5");
        paramhotelcasncel.put("RequestType", "4");
        paramhotelcasncel.put("Remarks", "By Mistake");
        paramhotelcasncel.put("BookingId",bookingid);
        paramhotelcasncel.put("id",id);
        paramhotelcasncel.put("EndUserIp", enduser_ip);
        paramhotelcasncel.put("TokenId",token_id);
        hotelcancel(paramhotelcasncel);



    }

    public  void hotelcancel(HashMap<String, String> paramhotelcasncel){
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelCancelChangeResponseModel> call = retroFitInterface.hotelticketcancel(paramhotelcasncel);
        call.enqueue(new Callback<HotelCancelChangeResponseModel>() {
            @Override
            public void onResponse(Call<HotelCancelChangeResponseModel> call, Response<HotelCancelChangeResponseModel> response) {
                progressDialogDismiss();
                HotelCancelChangeResponseModel hotelCancelChangeResponseModel = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code()==200)
                {


                    if(hotelCancelChangeResponseModel.getHotelChangeRequestResult().getResponseStatus()==1)
                    {
                        view_history(viewhistoryparam);

                    }
                    else
                    {
                        Toast.makeText(HotelBookingHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();

                    }



//                    view_history(busviewhistoryparam);
                }
                else
                {
                    Toast.makeText(HotelBookingHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HotelCancelChangeResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(HotelBookingHistoryActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

}