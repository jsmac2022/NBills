package com.nictbills.app.activities.tbo.flight;

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
import com.nictbills.app.activities.tbo.flight.adapter.FlightViewHistoryAdapter;
import com.nictbills.app.activities.tbo.flight.adapter.GetPassengerAdapter;
import com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory.Data;
import com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory.FlightHistoryModelResponse;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.AddPassenger;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.GetPassengerResponsemodel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFlightBookingHistoryBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightBookingHistory extends BaseActivity {

ActivityFlightBookingHistoryBinding binding;
    SharedPreferences shared, sharedPreferences;
    String mobileNumber;
    HashMap<String, String> viewhistoryparam;
    FlightViewHistoryAdapter  flightViewHistoryAdapter;

    ArrayList<Data> viewhisotylist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flight_booking_history);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_flight_booking_history);
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
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<FlightHistoryModelResponse> call = retroFitInterface.flighthistory(viewhistoryparam);
        call.enqueue(new Callback<FlightHistoryModelResponse>() {
            @Override
            public void onResponse(Call<FlightHistoryModelResponse> call, Response<FlightHistoryModelResponse> response) {
                progressDialogDismiss();
                FlightHistoryModelResponse flightHistoryModelResponse = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code()==200)
                {
                    if (flightHistoryModelResponse.getResponseCode()==200) {
                        if (flightHistoryModelResponse.getData().size() > 0) {
                            for (int j = 0; j < flightHistoryModelResponse.getData().size(); j++) {
                                Data data= new Data();
                                data.setBookingId(flightHistoryModelResponse.getData().get(j).getBookingId());
                                data.setFlightNumber(flightHistoryModelResponse.getData().get(j).getFlightNumber());
                                data.setOrderId(flightHistoryModelResponse.getData().get(j).getOrderId());
                                data.setPnr(flightHistoryModelResponse.getData().get(j).getPnr());
                                data.setPublishedFare(flightHistoryModelResponse.getData().get(j).getPublishedFare());
                                data.setAirlineCode(flightHistoryModelResponse.getData().get(j).getAirlineCode());
                                data.setFlightname(flightHistoryModelResponse.getData().get(j).getFlightname());
                                viewhisotylist.add(data);
                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            binding.recyclerViewViewhistory.setLayoutManager(linearLayoutManager);
                            FlightViewHistoryAdapter flightViewHistoryAdapter = new FlightViewHistoryAdapter(FlightBookingHistory.this, viewhisotylist);
                            binding.recyclerViewViewhistory.setAdapter(flightViewHistoryAdapter);
                        }
                        else
                        {
                            Toast.makeText(FlightBookingHistory.this, "Data Not Found ", Toast.LENGTH_SHORT).show();

                        }

                    } else if (flightHistoryModelResponse.getResponseCode() == 404) {
                        Toast.makeText(FlightBookingHistory.this, "Failur Response", Toast.LENGTH_SHORT).show();

                    }


                }
                else
                {
                    Toast.makeText(FlightBookingHistory.this, "Failur Response", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<FlightHistoryModelResponse> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(FlightBookingHistory.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });
    }


}