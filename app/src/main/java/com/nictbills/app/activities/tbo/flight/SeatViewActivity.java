package com.nictbills.app.activities.tbo.flight;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;

import com.nictbills.app.activities.tbo.flight.adapter.FlightSeatAdapter;
import com.nictbills.app.activities.tbo.flight.model.AddSeatDeatils;
import com.nictbills.app.activities.tbo.flight.model.FlightSeatModel;
import com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel.SeatLayoutModelResponse;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
//import com.nictbills.app.databinding.ActivitySeatViewBinding;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeatViewActivity extends BaseActivity implements FlightSeatAdapter.OnItemClickListener {
    SharedPreferences shared;
    String gettoken, gettrace, getresultindex;
    RecyclerView recyclerView;
    ArrayList<FlightSeatModel> list;
    //    ArrayList<Seat> seatlist ;
    GridView gridView;
    RetrofitInterface retroFitInterface;
    String jsonText ,getmobile, getpusher, getgender, getaddress, getcity, getuname, getcountry, getemail, getlname;
    JSONObject jsonobjfare;
    String getseatcode_final = "", getfinal_price = "";
    //    ActivitySeatViewBinding binding;
    HashMap<String, String> param;
    Context context;
    FlightSeatModel flightSeatModel;
    int getseatcount;
    Button btn_continue;
    TextView tvseatprice ,tvseatno;
    int sum=0;
   static ArrayList<AddSeatDeatils> arList;
    String getairlinecode,getflihtno,getorigincode,getdesticode,getflightname;
    ImageView backimg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_view);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_seat_view);
        recyclerView = findViewById(R.id.seat_rview);
        backimg = findViewById(R.id.backArrow_IMG);
        btn_continue = findViewById(R.id.btnSelectSeats);
        tvseatprice = findViewById(R.id.seatprice);
        tvseatno = findViewById(R.id.tv_seatno);
        context = getApplicationContext();
        list = new ArrayList<>();
        arList = new ArrayList<AddSeatDeatils>();
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettoken = shared.getString("Tokenid", "");
        gettrace = shared.getString("TraceId", "");
        getresultindex = shared.getString("ResultIndex", "");
        String json = shared.getString("FarequoteResponse", "");
        Log.e("farequote", "1" + json);
        getmobile = shared.getString("mobileuser", "");
        getpusher = shared.getString("pusher", "");
        getgender = shared.getString("gender", "");
        getaddress = shared.getString("Address", "");
        getcity = shared.getString("City", "");
        getcountry = shared.getString("Country", "");
        getuname = shared.getString("name", "");
        getemail = shared.getString("Email", "");
        getlname = shared.getString("lname", "");
        getairlinecode = shared.getString("Airlinecode", "");
        getflihtno = shared.getString("flightno", "");
        getorigincode = shared.getString("origincode", "");
        getdesticode = shared.getString("destinationcode", "");
        getflightname = shared.getString("flightname", "");
        param = new HashMap<>();
        param.put("EndUserIp", "");
        param.put("TokenId", gettoken);
        param.put("TraceId", gettrace);
        param.put("ResultIndex", getresultindex);
        click();
        retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);


        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void click() {
        seatlayout(param);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("seatno","1"+getseatcode_final);
                Log.e("price","2"+getfinal_price);
                SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("seatno", getseatcode_final);
                editor.putString("price", getfinal_price);
                editor.putInt("seatcount", getseatcount);
                editor.apply();
                Intent intent = new Intent(SeatViewActivity.this, FlightTicketActivity.class);
                startActivity(intent);
            }
        });

    }
    public void seatlayout(HashMap<String, String> param) {
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<SeatLayoutModelResponse> call = retroFitInterface.get_air_ssr(param);
        call.enqueue(new Callback<SeatLayoutModelResponse>() {
            @Override
            public void onResponse(Call<SeatLayoutModelResponse> call, Response<SeatLayoutModelResponse> response) {
                progressDialogDismiss();
                SeatLayoutModelResponse seatLayoutModelResponse = response.body();

                if (seatLayoutModelResponse.getResponse().getResponseStatus() == 1)
                {
                    if(seatLayoutModelResponse.getResponse().getSeatDynamic().size()==0)
                    {

                    }
                    else
                    {
                        for (int j = 0; j < seatLayoutModelResponse.getResponse().getSeatDynamic().size(); j++) {
                            for (int k = 0; k < seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().size(); k++) {

                                for (int l = 0; l < seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().size(); l++) {
                                    for (int m = 0; m < seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().size(); m++) {

                                        if (seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().get(m).getAvailablityType() == 0) {

                                        } else {
                                            Log.e("airlinecode ", "finalair" + seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().get(m).getAirlineCode());
                                            Log.e("code ", "fcode" + seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().get(m).getCode());
                                            try {
                                                flightSeatModel = new FlightSeatModel();
                                                flightSeatModel.setSeat_code(seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().get(m).getCode());
                                                flightSeatModel.setAvilability_type(String.valueOf(seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().get(m).getAvailablityType()));
                                                flightSeatModel.setPrice(String.valueOf(seatLayoutModelResponse.getResponse().getSeatDynamic().get(j).getSegmentSeat().get(k).getRowSeats().get(l).getSeats().get(m).getPrice()));
                                                list.add(flightSeatModel);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                    }
                                }

                            }
                        }
                        LinearLayoutManager linearLayoutManager = new GridLayoutManager(context, 6);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        FlightSeatAdapter customAdapter = new FlightSeatAdapter(SeatViewActivity.this, list, SeatViewActivity.this);
                        recyclerView.setAdapter(customAdapter);
                    }


                } else {
                    Toast.makeText(SeatViewActivity.this, "elseseat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SeatLayoutModelResponse> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: ");
                Log.e("nictfarerules", "faile: " + t.toString());
            }
        });
    }

    @Override
    public void onItemClick(String getseatcode, String getprice ,String seatavilability ,int getadult_count ,double finalamount ,String finalseatno ) {
        String finalseatnostr = finalseatno.substring(0, finalseatno.length()-1);
        getseatcode_final = finalseatnostr;
        getfinal_price = String.valueOf(finalamount);
        getseatcount = getadult_count;
        AddSeatDeatils addSeatDeatils= new AddSeatDeatils();
        addSeatDeatils.setSeatno(getseatcode);
        addSeatDeatils.setSeatprice(getprice);
        addSeatDeatils.setSeatavilability(seatavilability);
        arList.add(addSeatDeatils);
        tvseatprice.setText(finalamount+"");
        tvseatno.setText(finalseatnostr);


    }
}