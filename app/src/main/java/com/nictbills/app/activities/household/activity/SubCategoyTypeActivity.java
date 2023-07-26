package com.nictbills.app.activities.household.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.household.model.User;
import com.nictbills.app.activities.household.retrofit.APIClient;
import com.nictbills.app.activities.household.retrofit.GetResult;

import com.nictbills.app.activities.household.adepter.ServiceAdapter;
import com.nictbills.app.activities.household.model.SubcatDatum;
import com.nictbills.app.activities.household.model.TypeSubCat;
import com.nictbills.app.activities.household.utils.CustPrograssbar;
import com.nictbills.app.activities.household.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class SubCategoyTypeActivity extends BasicActivity implements GetResult.MyListener, ServiceAdapter.RecyclerTouchListener {

    @BindView(R.id.recycler_service)
    RecyclerView recyclerService;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    ImageView backArrow_IMG;
    User user;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategorytype);
        ButterKnife.bind(this);
        backArrow_IMG=findViewById(R.id.backArrow_IMG);
        //getSupportActionBar().setTitle("View All");
        cid =getIntent().getStringExtra("cid");

        custPrograssbar=new CustPrograssbar();
        sessionManager=new SessionManager(this);
       // user=sessionManager.getUserDetails("");
        getData();

        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SubCategoyTypeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getData() {
        custPrograssbar.prograssCreate(this);
        Call<JsonObject> call = APIClient.getInterface().sSubList(APIClient.APPEND_URL + "subserviceslist?main_serv_code="+cid);
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
                TypeSubCat subCat=gson.fromJson(result.toString(),TypeSubCat.class);
                ServiceAdapter itemAdp = new ServiceAdapter( this,subCat.getResult(), this, "viewall");
                recyclerService.setLayoutManager(new GridLayoutManager(this, 1));
                recyclerService.setAdapter(itemAdp);
            }
        }catch (Exception e){
            Log.e("Error", "" + e.toString());

        }
    }


    @Override
    public void onClickServiceItem(SubcatDatum dataItem, int position) {
        startActivity(new Intent(this, SubCategoryActivity.class)
                .putExtra("vurl","")
                .putExtra("name", dataItem.getSub_serv())
                .putExtra("named", "")
                .putExtra("cid", cid)
                .putExtra("sid",dataItem.getSsc()));
    }
}