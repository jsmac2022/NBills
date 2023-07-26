package com.nictbills.app.activities.tbo.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.bus.adapter.BusBookingHistoryAdapter;
import com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.BusBookingHistoryModel;
import com.nictbills.app.activities.tbo.bus.model.buscancelticket.BusCancelModelResponse;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityBusBookignHistoryBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusBookignHistoryActivity extends BaseActivity implements BusBookingHistoryAdapter.OnItemClickListener   {

    ActivityBusBookignHistoryBinding binding;
    SharedPreferences shared, sharedPreferences;
    String mobileNumber ,getagency_id ,gettoken ,gettrace_id;
    HashMap<String, String> busviewhistoryparam;
    ArrayList<com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.Data> viewhisotylist = new ArrayList();
    HashMap<String, String> parambuscasncel;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bus_bookign_history);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bus_bookign_history);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        getagency_id= shared.getString("Agencyid", "");
        gettoken= shared.getString("TokenBus", "");
        gettrace_id= shared.getString("TraceId", "");
        busviewhistoryparam = new HashMap<>();
        busviewhistoryparam.put("mobileNo", mobileNumber);
        view_history(busviewhistoryparam);
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void view_history(HashMap<String, String> viewhistoryparam) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusBookingHistoryModel> call = retroFitInterface.bushistory(viewhistoryparam);
        call.enqueue(new Callback<BusBookingHistoryModel>() {
            @Override
            public void onResponse(Call<BusBookingHistoryModel> call, Response<BusBookingHistoryModel> response) {
                progressDialogDismiss();
                viewhisotylist.clear();
                BusBookingHistoryModel hotelHistoryModelResponse = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code()==200)
                {
                    if (hotelHistoryModelResponse.getResponseCode()==200) {
                        if (hotelHistoryModelResponse.getData().size() > 0) {
                            for (int j = 0; j < hotelHistoryModelResponse.getData().size(); j++) {

                                com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.Data data = new com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.Data();

                                data.setBusId(hotelHistoryModelResponse.getData().get(j).getBusId());
                                data.setOrderId(hotelHistoryModelResponse.getData().get(j).getOrderId());
                                data.setBusName(hotelHistoryModelResponse.getData().get(j).getBusName());
                                data.setBookingDate(hotelHistoryModelResponse.getData().get(j).getBookingDate());
                                data.setInvoiceNumber(hotelHistoryModelResponse.getData().get(j).getInvoiceNumber());
                                data.setTicketNo(hotelHistoryModelResponse.getData().get(j).getTicketNo());
                                data.setTravelOperatorPNR(hotelHistoryModelResponse.getData().get(j).getTravelOperatorPNR());
                                data.setBusAmount(hotelHistoryModelResponse.getData().get(j).getBusAmount());
                                data.setBusBookingStatus(hotelHistoryModelResponse.getData().get(j).getBusBookingStatus());
                                data.setDroppingname(hotelHistoryModelResponse.getData().get(j).getDroppingname());
                                data.setBoardingname(hotelHistoryModelResponse.getData().get(j).getBoardingname());
                                data.setTravellname(hotelHistoryModelResponse.getData().get(j).getTravellname());
                                data.setBusbookid(hotelHistoryModelResponse.getData().get(j).getBusbookid());
                                data.setTraceId(hotelHistoryModelResponse.getData().get(j).getTraceId());
                                data.setTokenId(hotelHistoryModelResponse.getData().get(j).getTraceId());
//
                                viewhisotylist.add(data);
                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            binding.recyclerViewBusviewhistory.setLayoutManager(linearLayoutManager);
                            BusBookingHistoryAdapter  busBookingHistoryAdapter = new BusBookingHistoryAdapter(context, viewhisotylist  ,BusBookignHistoryActivity.this);
                            binding.recyclerViewBusviewhistory.setAdapter(busBookingHistoryAdapter);
                        }
                        else
                        {
                            Toast.makeText(BusBookignHistoryActivity.this, "Data Not Found ", Toast.LENGTH_SHORT).show();
                            binding.datanotfound.setVisibility(View.VISIBLE);
                            binding.recyclerViewBusviewhistory.setVisibility(View.GONE);
                        }

                    } else if (hotelHistoryModelResponse.getResponseCode() == 404) {
                        Toast.makeText(BusBookignHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {
                    Toast.makeText(BusBookignHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BusBookingHistoryModel> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(BusBookignHistoryActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });
    }



    @Override
    public void onItemClick(String busid ,String  bookid ,String tokenid, String traceid) {
        parambuscasncel = new HashMap<>();
        parambuscasncel.put("EndUserIp", "");
        parambuscasncel.put("AgencyId", getagency_id);
        parambuscasncel.put("TraceId", traceid);
        parambuscasncel.put("TokenId", tokenid);
        parambuscasncel.put("BusId", busid);
        parambuscasncel.put("bookId",bookid);
        parambuscasncel.put("RequestType", "11");
        parambuscasncel.put("Remarks", "By Mistake");
        parambuscasncel.put("BookingMode", String.valueOf(1));
        buscancel(parambuscasncel);

        Log.e("omclick","idfinal"+busid);


    }

    public  void buscancel(HashMap<String, String> parambuscasncel){

        progressDialogShow();

        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusCancelModelResponse> call = retroFitInterface.busticketcancel(parambuscasncel);
        call.enqueue(new Callback<BusCancelModelResponse>() {
            @Override
            public void onResponse(Call<BusCancelModelResponse> call, Response<BusCancelModelResponse> response) {
                progressDialogDismiss();
                BusCancelModelResponse hotelHistoryModelResponse = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code()==200)
                {

                    view_history(busviewhistoryparam);
                }
                else
                {
                    Toast.makeText(BusBookignHistoryActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BusCancelModelResponse> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(BusBookignHistoryActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });

    }

}