package com.nictbills.app.activities.fastag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.nictbills.app.R;
import com.nictbills.app.activities.fastag.Model.FasTagData;
import com.nictbills.app.activities.fastag.Model.FastagRequest;
import com.nictbills.app.activities.fastag.retrofit.APIClient;
import com.nictbills.app.activities.fastag.retrofit.GetResult;
import com.nictbills.app.activities.fastag.utils.CustPrograssbar;
import com.nictbills.app.activities.fastag.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nictbills.app.utils.Util;

import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;

public class FasTagStatus extends Fragment implements GetResult.MyListener {
    @BindView(R.id.lvl_myorder)
    LinearLayout lvlMyOrder;
    @BindView(R.id.txt_notfound)
    TextView txtNotFound;
    @BindView(R.id.lvl_notfound)
    LinearLayout lvlNotFound;
    Unbinder unbinder;
    CustPrograssbar custPrograssbar;
    private SharedPreferences fastag_Shared;
    private String reg_mobile;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fas_tag_status, container, false);
        unbinder = ButterKnife.bind(this, view);
        custPrograssbar=new CustPrograssbar();

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(getActivity(),"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // credential = sharedPreferences.getString("cred_1", "");
        reg_mobile = sharedPreferences.getString("cred_2", "");
        getNotification();
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    private void setNotiList(LinearLayout lnrView, List<FasTagData> list) {
        lnrView.removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View view = inflater.inflate(R.layout.custome_noti, null);

            TextView txtTitel = view.findViewById(R.id.txt_titel);
            TextView txtDatetime = view.findViewById(R.id.txt_datetime);
            TextView txtDescption = view.findViewById(R.id.txt_descption);
            String date = parseDateToddMMyyyy(list.get(i).getCreated_dt());

            txtTitel.setText("  " + list.get(i).getReq_status() + "  ");
            txtDatetime.setText("" + date);
            String des="Code : "+list.get(i).getRequest_code()+"\n"+"Sector : "+list.get(i).getSector()+"\n"+"Shipping Address : "+list.get(i).getAddr_name()+" "+list.get(i).getAddress()+" "+list.get(i).getAddr_city()+" "+list.get(i).getAddr_state();
            txtDescption.setText("" + des);
            lnrView.addView(view);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void getNotification() {
        custPrograssbar.prograssCreate(getContext());

        JSONObject jsonObject = new JSONObject();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("Authorization", "Bearer "+ Utility.authorization);
            Call<JsonObject> call = APIClient.getInterface().getFasTagtCertificateList(APIClient.APPEND_URL+"get_fastag_request_by_mobile_no?mobile_no="+reg_mobile,map);
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                FastagRequest notification = gson.fromJson(result.toString(), FastagRequest.class);
                if (notification.getResponseCode().equalsIgnoreCase("200")) {
                      if(notification.getData()!=null) {
                          lvlNotFound.setVisibility(View.GONE);
                          setNotiList(lvlMyOrder, notification.getData());
                      }
                } else {
                    lvlNotFound.setVisibility(View.VISIBLE);
                    txtNotFound.setText("" + notification.getResponseStatus());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}