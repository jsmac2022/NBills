package com.nictbills.app.activities.farmequipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
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
import com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel.FarmEquipmentListResponseModel;
import com.nictbills.app.activities.tbo.bus.BusDashBoardActivity;
import com.nictbills.app.activities.tbo.bus.model.buscitylist.BusCity;
import com.nictbills.app.activities.tbo.bus.model.buscitylist.BusCityListResponsemodel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFarmEquipmentListBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmEquipmentListActivity extends BaseActivity implements FarmEquipmentListAdapter.OnItemClickListener{
    ActivityFarmEquipmentListBinding binding;
    ArrayList<FarmEquipmentListResponseModel> farmlist = new ArrayList<FarmEquipmentListResponseModel>();
    FarmEquipmentListAdapter farmEquipmentListAdapter;
    Context context;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farm_equipment_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_equipment_list);
        click();
    }

    public void click() {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        callmethod();

    }

    public void callmethod() {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.FarmEquipment).create(RetrofitInterface.class);
        Call<List<FarmEquipmentListResponseModel>> call = retroFitInterface.getalleqip();
        call.enqueue(new Callback<List<FarmEquipmentListResponseModel>>() {
            @Override
            public void onResponse(Call<List<FarmEquipmentListResponseModel>> call, Response<List<FarmEquipmentListResponseModel>> response) {
                progressDialogDismiss();
                List<FarmEquipmentListResponseModel> busCityListResponsemodel = response.body();
                if (response.code() == 200) {
                    Log.e("1","resp"+response.toString());


                    for (int j = 0; j <busCityListResponsemodel.size(); j++) {

                        FarmEquipmentListResponseModel farmEquipmentListResponseModel=new FarmEquipmentListResponseModel();

                        farmEquipmentListResponseModel.setAvailableLocation(busCityListResponsemodel.get(j).getAvailableLocation());
                        farmEquipmentListResponseModel.setName(busCityListResponsemodel.get(j).getName());
                        farmEquipmentListResponseModel.setDescription(busCityListResponsemodel.get(j).getDescription());
                        farmEquipmentListResponseModel.setType(busCityListResponsemodel.get(j).getType());
                        farmEquipmentListResponseModel.setRent_amount(busCityListResponsemodel.get(j).getRent_amount());
                        farmEquipmentListResponseModel.setId(busCityListResponsemodel.get(j).getId());
                        farmEquipmentListResponseModel.setImagePath(busCityListResponsemodel.get(j).getImagePath());
                        farmlist.add(farmEquipmentListResponseModel);

                    }

                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.rviewFarmlist.setLayoutManager(mLayoutManager);
                    farmEquipmentListAdapter = new FarmEquipmentListAdapter(getApplicationContext(), farmlist ,FarmEquipmentListActivity.this);
                    binding.rviewFarmlist.setAdapter(farmEquipmentListAdapter);
                } else if (response.code() == 400) {
                    Toast.makeText(FarmEquipmentListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FarmEquipmentListActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<FarmEquipmentListResponseModel>> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: " + t.toString());

            }
        });

    }

    @Override
    public void onItemClick(String title ,String description,String rent_amount ,String productid ,String img ,String location) {

        shared = getSharedPreferences("nict", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("Title", title);
        editor.putString("Description", description);
        editor.putString("amount", rent_amount);
        editor.putString("productid", productid);
        editor.putString("img", img);
        editor.putString("location", location);
        editor.apply();
        Intent intent = new Intent(FarmEquipmentListActivity.this, FarmEquipmentDetails.class);
        startActivity(intent);


    }
}