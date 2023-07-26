package com.nictbills.app.activities.household.fragment;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.service.autofill.AutofillService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.nictbills.app.R;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.household.activity.SearchActivity;
import com.nictbills.app.activities.household.activity.SubCategoryActivity;
import com.nictbills.app.activities.household.activity.SubCategoyTypeActivity;
import com.nictbills.app.activities.household.model.CatlistItem;
import com.nictbills.app.activities.household.model.ServiceDataItem;
import com.nictbills.app.activities.household.model.User;
import com.nictbills.app.activities.household.adepter.CategoryAdapter;

import com.nictbills.app.activities.household.model.Home;
import com.nictbills.app.activities.household.model.SubcatDatum;
import com.nictbills.app.activities.household.retrofit.APIClient;
import com.nictbills.app.activities.household.retrofit.GetResult;
import com.nictbills.app.activities.household.utils.CustPrograssbar;
import com.nictbills.app.activities.household.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class HomeFragment extends Fragment implements CategoryAdapter.RecyclerTouchListener, GetResult.MyListener{
     @BindView(R.id.recycler_category)
    RecyclerView recyclerCategory;
    @BindView(R.id.backArrow_hh_IMG)
     ImageView backArrow_hh_IMG;
    Unbinder unbinder;
    LinearLayoutManager layoutManager;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    User user;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());

      //  User user1=new User();
       // user1.setId("1");
        //sessionManager.setUserDetails("",user1);
        //user = sessionManager.getUserDetails("");

        recyclerCategory.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerCategory.setItemAnimator(new DefaultItemAnimator());

        backArrow_hh_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });


        getHome();

        return view;
    }

    private void getHome() {
        custPrograssbar.prograssCreate(getActivity());
       Call<JsonObject> call = APIClient.getInterface().getCategory();
        GetResult getResult = new GetResult();
        getResult.setMyListener(this);
        getResult.callForLogin(call, "1");

    }


    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                Home home = gson.fromJson(result.toString(), Home.class);
                if (home.getResponse().equalsIgnoreCase("200")) {

                    CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), home.getResult(), this, "single");
                    recyclerCategory.setAdapter(categoryAdapter);


                }
            }
        } catch (Exception e) {
            Log.e("Error", "-->" + e.toString());

        }
    }


    @Override
    public void onClickCategoryItem(CatlistItem catlistItem, int position) {

//        if (catlistItem.getTotalSubcat() == 0) {
//            startActivity(new Intent(getActivity(), SubCategoryActivity.class)
//                    .putExtra("vurl", catlistItem.getCatVideo())
//                    .putExtra("name", catlistItem.getCatName())
//                    .putExtra("named", catlistItem.getCatSubtitle())
//                    .putExtra("cid", catlistItem.getCatId())
//                    .putExtra("sid", "0"));
//
//        } else {
            startActivity(new Intent(getActivity(), SubCategoyTypeActivity.class)
                    .putExtra("cid", catlistItem.getMsc()));

     //   }
    }


    @OnClick({R.id.ed_search})
    public void onClick(View view) {
        if (view.getId() == R.id.ed_search) {
            startActivity(new Intent(getActivity(), SearchActivity.class));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}