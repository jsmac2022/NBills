package com.nictbills.app.activities.fastag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.fastag.Model.Authentication;
import com.nictbills.app.activities.fastag.retrofit.APIClient;
import com.nictbills.app.activities.fastag.utils.CustPrograssbar;
import com.nictbills.app.activities.fastag.utils.Utility;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.utils.Util;


import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @BindView(R.id.recycler_fasttagtype)
    RecyclerView recyclerFasttagtype;
    private RetrofitInterface retroFitInterface;
    CustPrograssbar custPrograssbar;
    private SharedPreferences sharedPreferences;
    private String reg_mobile;
    private LinearLayout linerLayout_home,linerLayout_status;
    private Fragment fragment = null;
    private ImageView backArrow_IMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sharedPreferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(MainActivity.this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // credential = sharedPreferences.getString("cred_1", "");
        reg_mobile = sharedPreferences.getString("cred_2", "");
        System.out.println("mobile:"+reg_mobile);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mobile", reg_mobile);
            RequestBody bodyRequest = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());

            Call<Authentication> call = APIClient.getInterface().getAccessToken(bodyRequest);
//            Authentication auth= call.execute().body();
//            if(auth!=null) {
//                Utility.authorization = auth.getData().getAuth_token();
//            }
//                    setContentView(R.layout.activity_main);
//                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                    loadFragment(new HomeFragment());
//                    BottomNavigationView navigation = findViewById(R.id.navigation);
//                    navigation.setOnNavigationItemSelectedListener(this);
//


            call.enqueue(new Callback<Authentication>() {
                @Override
                public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                    Log.e("message", " : " + response.message());
                    Log.e("body", " : " + response.body());
                    if(response.body()!=null){
                        String auth=response.body().getData().getAuth_token();
                        Utility.authorization=auth;

                    }
                    setContentView(R.layout.activity_main_fastag);

                    linerLayout_home= findViewById(R.id.linerLayout_home);
                    linerLayout_status= findViewById(R.id.linerLayout_status);
                    backArrow_IMG= findViewById(R.id.backArrow_IMG);

                   loadFragment(new HomeFragment());

                    linerLayout_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fragment = new com.nictbills.app.activities.fastag.HomeFragment();
                            loadFragment(fragment);
                        }
                    });


                    linerLayout_status.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            fragment = new com.nictbills.app.activities.fastag.FasTagStatus();
                            loadFragment(fragment);
                        }
                    });

                    backArrow_IMG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });


                  /*  BottomNavigationView navigation = findViewById(R.id.navigation);
                    navigation.setOnNavigationItemSelectedListener(MainActivity.this::onNavigationItemSelected);*/
                }

                @Override
                public void onFailure(Call<Authentication> call, Throwable t) {

                    call.cancel();
                    t.printStackTrace();
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }



    }




    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
 /*   @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.page_1:
                fragment = new com.nictbills.app.activities.fastag.HomeFragment();
                break;
            case R.id.page_2:
                fragment = new com.nictbills.app.activities.fastag.FasTagStatus();

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return loadFragment(fragment);
    }*/


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}