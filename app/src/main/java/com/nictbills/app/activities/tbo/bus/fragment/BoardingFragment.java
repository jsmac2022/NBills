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
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.bus.BusListShowActivity;
import com.nictbills.app.activities.tbo.bus.BusSeatActivity;
import com.nictbills.app.activities.tbo.bus.adapter.BoardingPointBusAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.BusBordingPointAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.BusListAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.DropingPointAdapter;
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


public class BoardingFragment extends Fragment  {

    HashMap<String, String> paramBording;
    SharedPreferences shared;
    String getdroping_index, getbording_index, getbording_name, getdroping_name, getBusToken, getAgency_id, getMember_id, getTrace_id, getBusIndex;
    Context context;
    BoardingPointsDetailsModelResponse boardingPointsDetailsModelResponse;
    ArrayList<BoardingPointsDetailsModelResponse> listbordingpoint = new ArrayList<>();
    BoardingPointBusAdapter boardingPointBusAdapter;

    DropingPointsDetailsModelResponse dropingPointsDetailsModelResponse;
    RecyclerView recyclerView;

    public BoardingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_borading, container, false);
        shared = getActivity().getSharedPreferences("nict", MODE_PRIVATE);

        recyclerView=view.findViewById(R.id.recyclerView_boarding);
        getBusToken = shared.getString("TokenBus", "");
        getAgency_id = shared.getString("Agencyid", "");
        getMember_id = shared.getString("Memberid", "");
        getTrace_id = shared.getString("TraceId", "");
        getBusIndex = shared.getString("Resultindex", "");
        paramBording = new HashMap<>();
        paramBording.put("EndUserIp", "");
        paramBording.put("ResultIndex", getBusIndex);
        paramBording.put("TraceId", getTrace_id);
        paramBording.put("TokenId", getBusToken);
        getBusBording(paramBording);
        return  view;
    }

    public void getBusBording(HashMap<String, String> paramBording) {

         ProgressDialog  progressDialog= new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusBordingPointResponseModel> call = retroFitInterface.getBusBording(paramBording);
        call.enqueue(new Callback<BusBordingPointResponseModel>() {
            @Override
            public void onResponse(Call<BusBordingPointResponseModel> call, Response<BusBordingPointResponseModel> response) {
                BusBordingPointResponseModel busAuthResponseModel = response.body();

                progressDialog.dismiss();

                if (response.code() == 200) {
                    Log.e("bus BusBording ", "onResponse: ");
                    listbordingpoint.clear();

                    if (busAuthResponseModel.getGetBusRouteDetailResult().getResponseStatus() == 1) {

                        for (int j = 0; j < busAuthResponseModel.getGetBusRouteDetailResult().getBoardingPointsDetails().size(); j++) {
                            boardingPointsDetailsModelResponse = new BoardingPointsDetailsModelResponse();
                            boardingPointsDetailsModelResponse.setBoarding_pointindex(String.valueOf(busAuthResponseModel.getGetBusRouteDetailResult().getBoardingPointsDetails().get(j).getCityPointIndex()));
                            boardingPointsDetailsModelResponse.setBoarding_pointname(busAuthResponseModel.getGetBusRouteDetailResult().getBoardingPointsDetails().get(j).getCityPointName());
                            boardingPointsDetailsModelResponse.setBoarding_pointcontactno(busAuthResponseModel.getGetBusRouteDetailResult().getBoardingPointsDetails().get(j).getCityPointContactNumber());
                            boardingPointsDetailsModelResponse.setBoarding_pointtime(busAuthResponseModel.getGetBusRouteDetailResult().getBoardingPointsDetails().get(j).getCityPointTime());
                            boardingPointsDetailsModelResponse.setBoarding_PointLocation(busAuthResponseModel.getGetBusRouteDetailResult().getBoardingPointsDetails().get(j).getCityPointLocation());
                            listbordingpoint.add(boardingPointsDetailsModelResponse);

                            Log.e("bustgt BusBording ", "listbordingpoint: "+listbordingpoint.size());

                        }
                        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        BoardingPointBusAdapter  boardingPointBusAdapter = new BoardingPointBusAdapter(getActivity(), listbordingpoint);
                        recyclerView.setAdapter(boardingPointBusAdapter);




//                        busBordingPointAdapter = new BusBordingPointAdapter(BusSeatActivity.this, android.R.layout.simple_spinner_dropdown_item, listbordingpoint);
//                        binding.spBprdingpoint.setAdapter(busBordingPointAdapter);
//                        getbording_name = listbordingpoint.get(position).getBoarding_pointname();
//
//                        for (int k = 0; k < busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().size(); k++) {
//                            dropingPointsDetailsModelResponse = new DropingPointsDetailsModelResponse();
//                            dropingPointsDetailsModelResponse.setDroping_pointindex(String.valueOf(busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().get(k).getCityPointIndex()));
//                            dropingPointsDetailsModelResponse.setDroping_pointname(busAuthResponseModel.getGetBusRouteDetailResult().getDroppingPointsDetails().get(k).getCityPointName());
//                            listdropingpoint.add(dropingPointsDetailsModelResponse);
//                        }
//                        dropingPointAdapter = new DropingPointAdapter(BusSeatActivity.this, android.R.layout.simple_spinner_dropdown_item, listdropingpoint);
//                        binding.spDropingpoint.setAdapter(dropingPointAdapter);
//                        getdroping_name = listdropingpoint.get(position).getDroping_pointname();

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