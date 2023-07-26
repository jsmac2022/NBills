package com.nictbills.app.activities.tbo.bus.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.tbo.bus.adapter.BoardingPointBusAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.BusBordingPointAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.DroppingPointBusAdapter;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.BoardingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.DropingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.bus.model.busbordingresponsemodel.BusBordingPointResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DroppingFragment extends Fragment {
    HashMap<String, String> paramdropping;
    SharedPreferences shared;
    String getdroping_index, getbording_index, getbording_name, getdroping_name, getBusToken, getAgency_id, getMember_id, getTrace_id, getBusIndex;
    Context context;
    BoardingPointsDetailsModelResponse boardingPointsDetailsModelResponse;
    ArrayList<DropingPointsDetailsModelResponse> drooppingpoint = new ArrayList<>();
    DroppingPointBusAdapter droppingPointBusAdapter;
    DropingPointsDetailsModelResponse dropingPointsDetailsModelResponse;
    RecyclerView recyclerView;



    public DroppingFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dropping, container, false);
        context = getActivity();
        recyclerView=view.findViewById(R.id.recyclerView_dropping);

        shared = getActivity().getSharedPreferences("nict", MODE_PRIVATE);
        getBusToken = shared.getString("TokenBus", "");
        getAgency_id = shared.getString("Agencyid", "");
        getMember_id = shared.getString("Memberid", "");
        getTrace_id = shared.getString("TraceId", "");
        getBusIndex = shared.getString("Resultindex", "");
        paramdropping = new HashMap<>();
        paramdropping.put("EndUserIp", "");
        paramdropping.put("ResultIndex", getBusIndex);
        paramdropping.put("TraceId", getTrace_id);
        paramdropping.put("TokenId", getBusToken);
        getdrooping(paramdropping);

        return  view;
    }

    public void getdrooping(HashMap<String, String> paramdropping) {

        ProgressDialog progressDialog= new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusBordingPointResponseModel> call = retroFitInterface.getBusBording(paramdropping);
        call.enqueue(new Callback<BusBordingPointResponseModel>() {
            @Override
            public void onResponse(Call<BusBordingPointResponseModel> call, Response<BusBordingPointResponseModel> response) {
                BusBordingPointResponseModel busAuthResponseModel = response.body();

                progressDialog.dismiss();

                if (response.code() == 200) {
                    Log.e("bus droopping ", "onResponse: ");
                    if (busAuthResponseModel.getGetBusRouteDetailResult().getResponseStatus() == 1) {
                        drooppingpoint.clear();
                        for (int k = 0; k < busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().size(); k++) {
                            dropingPointsDetailsModelResponse = new DropingPointsDetailsModelResponse();
                            dropingPointsDetailsModelResponse.setDroping_pointindex(String.valueOf(busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().get(k).getCityPointIndex()));
                            dropingPointsDetailsModelResponse.setDroping_pointname(busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().get(k).getCityPointName());
                            dropingPointsDetailsModelResponse.setDroping_PointTime(busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().get(k).getCityPointTime());
                            dropingPointsDetailsModelResponse.setDroping_PointLocation(busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().get(k).getCityPointLocation());
                            drooppingpoint.add(dropingPointsDetailsModelResponse);
                        }

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        droppingPointBusAdapter = new DroppingPointBusAdapter(getActivity(), drooppingpoint);
                        recyclerView.setAdapter(droppingPointBusAdapter);



                    } else {

                    }

                } else if (response.code() == 401) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();

                } else if (response.code() == 503) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Service Unavilable", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
//                    Toast.makeText(HotelDashBoardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "server issue", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BusBordingPointResponseModel> call, Throwable t) {
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Internal Server Error", Toast.LENGTH_LONG).show();
                Log.e("nictfailur", "onResponse: ");
                progressDialog.dismiss();

            }
        });

    }

}