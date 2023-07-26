package com.nictbills.app.activities.fastag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.fastag.Adapter.CertificateAdapter;
import com.nictbills.app.activities.fastag.Model.FasTagCertificate;
import com.nictbills.app.activities.fastag.Model.FasTagCertificateList;
import com.nictbills.app.activities.fastag.retrofit.APIClient;
import com.nictbills.app.activities.fastag.retrofit.GetResult;
import com.nictbills.app.activities.fastag.utils.CustPrograssbar;
import com.nictbills.app.activities.fastag.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class FasTagCertificateActivity extends BasicActivity implements GetResult.MyListener, CertificateAdapter.RecyclerTouchListener {

    @BindView(R.id.recycler_service)
    RecyclerView recyclerService;
    CustPrograssbar custPrograssbar;
    String sector12="",fastag_type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastagcertificate);
        ButterKnife.bind(this);
       // getSupportActionBar().setTitle("Certificate Details");

        custPrograssbar=new CustPrograssbar();
        sector12=getIntent().getStringExtra("sector");
        fastag_type=getIntent().getStringExtra("certificate_type");

        getData();

    }

    private void getData() {
        custPrograssbar.prograssCreate(this);
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer "+ Utility.authorization);
        Call<JsonObject> call = APIClient.getInterface().getFasTagtCertificateList(APIClient.APPEND_URL + "get_fastag_certificate_type?sector="+sector12,map);
        GetResult getResult = new GetResult();
        getResult.setMyListener(this);
        getResult.callForLogin(call, "1");

    }
    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();

            if(callNo.equalsIgnoreCase("1")){
                Gson gson=new Gson();
                FasTagCertificateList subCat=gson.fromJson(result.toString(),FasTagCertificateList.class);
                if(subCat.getResponseCode().equals("200")){
                    if(subCat.getData()!=null && subCat.getData().size()>0){
                CertificateAdapter itemAdp = new CertificateAdapter( subCat.getData(), this, "viewall");
                recyclerService.setLayoutManager(new GridLayoutManager(this, 1));
                recyclerService.setAdapter(itemAdp);
            }

                }}
        }catch (Exception e){
            Log.e("Error", "" + e.toString());

        }
    }


    @Override
    public void onClickServiceItem(FasTagCertificate dataItem, int position) {

        startActivity(new Intent(this, Corporate.class).putExtra("sector",sector12).putExtra("certificate_type",fastag_type).putExtra("certificate_name",dataItem.getCertificate_name()));
    }
}