package com.nictbills.app.activities.farmequipment;

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
import com.nictbills.app.activities.farmequipment.adapter.FarmEquipmentListAdapter;
import com.nictbills.app.activities.farmequipment.adapter.FarmOrderHistoryAdapter;
import com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel.FarmEquipmentListResponseModel;
import com.nictbills.app.activities.farmequipment.model.farmlocation.FarmLocationCheckResponse;
import com.nictbills.app.activities.farmequipment.model.orderhistory.OrderHistoryFarmEquipment;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFarmEquipmentHistoryBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmEquipmentHistoryActivity  extends BaseActivity implements FarmOrderHistoryAdapter.OnItemClickListener {
ActivityFarmEquipmentHistoryBinding binding;
    SharedPreferences shared, sharedPreferences;
    String  mobileNumber;
    ArrayList<OrderHistoryFarmEquipment> orderlist = new ArrayList<OrderHistoryFarmEquipment>();
    FarmOrderHistoryAdapter farmOrderHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farm_equipment_history);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_farm_equipment_history);
        shared = getSharedPreferences("nict", MODE_PRIVATE);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        click();
    }
    public void click() {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        orderhistory();
    }

    public void orderhistory() {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.FarmEquipment).create(RetrofitInterface.class);
        Call<List<OrderHistoryFarmEquipment>> call = retroFitInterface.orderhsitory(mobileNumber);
        call.enqueue(new Callback<List<OrderHistoryFarmEquipment>>() {
            @Override
            public void onResponse(Call<List<OrderHistoryFarmEquipment>> call, Response<List<OrderHistoryFarmEquipment>> response) {
                progressDialogDismiss();
                List<OrderHistoryFarmEquipment> orderHistoryFarmEquipments = response.body();

                if (response.code() == 200)
                {

                    for (int j = 0; j <orderHistoryFarmEquipments.size(); j++)
                    {
                        OrderHistoryFarmEquipment orderHistoryFarmEquipment= new OrderHistoryFarmEquipment();
                        orderHistoryFarmEquipment.setOrderNo(orderHistoryFarmEquipments.get(j).getOrderNo());
                        orderHistoryFarmEquipment.setBooking_id(orderHistoryFarmEquipments.get(j).getBooking_id());
                        orderHistoryFarmEquipment.setProduct_id(orderHistoryFarmEquipments.get(j).getProduct_id());
                        orderHistoryFarmEquipment.setRental_time(orderHistoryFarmEquipments.get(j).getRental_time());
                        orderHistoryFarmEquipment.setOrder_date(orderHistoryFarmEquipments.get(j).getOrder_date());
                        orderHistoryFarmEquipment.setOrder_status(orderHistoryFarmEquipments.get(j).getOrder_status());
                        orderHistoryFarmEquipment.setEquipment_name(orderHistoryFarmEquipments.get(j).getEquipment_name());
                        orderlist.add(orderHistoryFarmEquipment);
                    }

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.rviewOrderlist.setLayoutManager(mLayoutManager);
                    farmOrderHistoryAdapter = new FarmOrderHistoryAdapter(getApplicationContext(), orderlist ,FarmEquipmentHistoryActivity.this);
                    binding.rviewOrderlist.setAdapter(farmOrderHistoryAdapter);


//
                } else if (response.code() == 400) {
                    Toast.makeText(FarmEquipmentHistoryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FarmEquipmentHistoryActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<OrderHistoryFarmEquipment>> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: " + t.toString());

            }
        });
    }

    @Override
    public void onItemClick() {

    }
}