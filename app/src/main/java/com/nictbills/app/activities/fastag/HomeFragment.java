package com.nictbills.app.activities.fastag;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.activities.fastag.Adapter.FastTagTypeAdapter;
import com.nictbills.app.activities.fastag.Model.FasTagType;
import com.nictbills.app.activities.fastag.Model.FasTagTypeList;
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
import butterknife.Unbinder;
import retrofit2.Call;

public class HomeFragment extends Fragment implements FastTagTypeAdapter.RecyclerTouchListener, GetResult.MyListener {
    @BindView(R.id.recycler_fasttagtype)
    RecyclerView recyclerFasttagtype;
    CustPrograssbar custPrograssbar;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("Home Fragement");
        View view = inflater.inflate(R.layout.fragment_home_fastag, container, false);
        unbinder = ButterKnife.bind(this, view);
        custPrograssbar = new CustPrograssbar();
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        recyclerFasttagtype.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerFasttagtype.setItemAnimator(new DefaultItemAnimator());


        getHome();

        return view;
    }

    private void getHome() {
        custPrograssbar.prograssCreate(getContext());
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer "+Utility.authorization);
        Call<JsonObject> call = APIClient.getInterface().getFasTagtypeList(map);
        GetResult getResult = new GetResult();
        getResult.setMyListener(this);
        getResult.callForLogin(call, "1");

    }

    @Override
    public void onClickFastTagTypeItem(FasTagType item, int position) {
        if(item.getSector().equals("Individual")){
            startActivity(new Intent(getContext(), Individual.class).putExtra("sector", item.getSector()).putExtra("certificate_type", item.getType_name()));;
        }else if(item.getSector().equals("Proprietorship")){
            startActivity(new Intent(getContext(), Proprietor.class).putExtra("sector", item.getSector()).putExtra("certificate_type", item.getType_name()));
        }else{
        startActivity(new Intent(getContext(), FasTagCertificateActivity.class).putExtra("sector", item.getSector()).putExtra("certificate_type", item.getType_name()));
    }}
    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                FasTagTypeList home = gson.fromJson(result.toString(), FasTagTypeList.class);

                if (home.getResponseCode().equalsIgnoreCase("200")) {
                    FastTagTypeAdapter categoryAdapter = new FastTagTypeAdapter(getContext(), home.getData(), this, "single");
                    recyclerFasttagtype.setAdapter(categoryAdapter);
                }
            }
        } catch (Exception e) {
            Log.e("Error", "-->" + e.toString());

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}