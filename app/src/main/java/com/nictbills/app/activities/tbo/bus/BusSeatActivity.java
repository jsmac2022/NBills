package com.nictbills.app.activities.tbo.bus;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.bus.adapter.BusBordingPointAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.BusSeatListAdapter;
import com.nictbills.app.activities.tbo.bus.adapter.DropingPointAdapter;
import com.nictbills.app.activities.tbo.bus.fragment.BusBoradingMainActivity;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.BoardingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.bus.model.bordingpointmodel.DropingPointsDetailsModelResponse;
import com.nictbills.app.activities.tbo.bus.model.busseatadd.SeatAdd;
import com.nictbills.app.activities.tbo.bus.model.busseatmodel.BusseatResponsemodel;
import com.nictbills.app.activities.tbo.bus.model.busseatmodel.Error;
import com.nictbills.app.activities.tbo.bus.model.busseatresponsemode.BusSeatResponseListModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityBusSeatBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusSeatActivity extends BaseActivity implements BusSeatListAdapter.OnItemClickListener {
    ActivityBusSeatBinding binding;
    SharedPreferences shared;
    String getdroping_index, getbording_index, getbording_name, getdroping_name, getBusToken, getAgency_id, getMember_id, getTrace_id, getBusIndex;
    HashMap<String, String> paramSeat;
    ArrayList<BusSeatResponseListModel> list = new ArrayList<>();
    Context context;
    BusSeatListAdapter busSeatListAdapter;
    BusSeatResponseListModel busSeatResponseListModel;
    HashMap<String, String> paramBording;
    BoardingPointsDetailsModelResponse boardingPointsDetailsModelResponse;
    ArrayList<BoardingPointsDetailsModelResponse> listbordingpoint = new ArrayList<>();
    DropingPointsDetailsModelResponse dropingPointsDetailsModelResponse;
    ArrayList<DropingPointsDetailsModelResponse> listdropingpoint = new ArrayList<>();
    DropingPointAdapter dropingPointAdapter;
    BusBordingPointAdapter busBordingPointAdapter;
    int position;
    String getfinal_seatindex = "";
    BusseatResponsemodel busseatresponsemodel;
//    static ArrayList<SeatAdd> seatadd;
    //    Set<SeatAdd> seatadd = new HashSet<>(SeatAdd);
//    static LinkedHashSet<SeatAdd> seatadd;
//    Set<SeatAdd> set = new HashSet<>(seatadd);
//    SeatAdd seatAdd = new SeatAdd();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bus_seat);
        context = getApplicationContext();
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        getBusToken = shared.getString("TokenBus", "");
        getAgency_id = shared.getString("Agencyid", "");
        getMember_id = shared.getString("Memberid", "");
        getTrace_id = shared.getString("TraceId", "");
        getBusIndex = shared.getString("Resultindex", "");
        paramSeat = new HashMap<>();
        paramSeat.put("EndUserIp", "");
        paramSeat.put("ResultIndex", getBusIndex);
        paramSeat.put("TraceId", getTrace_id);
        paramSeat.put("TokenId", getBusToken);
        get_BusSeat(paramSeat);
//        seatadd = new ArrayList<SeatAdd>();

        busseatresponsemodel = new BusseatResponsemodel();
        click();
    }

    public void click() {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btncontinu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared = getSharedPreferences("nict", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                Gson gson = new Gson();
                String json = gson.toJson(busseatresponsemodel); // myObject - instance of MyObject
                editor.putString("SeatResponse", json);
                editor.putString("Bordingindex", "");
                editor.putString("Dropingindex", "");
                editor.putString("Dropinname", "");
                editor.putString("originname", "");
                editor.apply();
//                Intent intent = new Intent(BusSeatActivity.this, AddBusMemberActivity.class);
                Intent intent = new Intent(BusSeatActivity.this, BusBoradingMainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void get_BusSeat(HashMap<String, String> paramSeat) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusseatResponsemodel> call = retroFitInterface.getBusSeat(paramSeat);
        call.enqueue(new Callback<BusseatResponsemodel>() {
            @Override
            public void onResponse(Call<BusseatResponsemodel> call, Response<BusseatResponsemodel> response) {
                progressDialogDismiss();
                busseatresponsemodel = response.body();
                Log.e("bus seat  ", "onResponse: ");
                if (response.code() == 200) {
                    Error error = new Error();

                    if (busseatresponsemodel.getGetBusSeatLayOutResult().getError().getErrorCode() == 0) {
                        if (busseatresponsemodel.getGetBusSeatLayOutResult().getResponseStatus() == 1) {
                            Log.e("buseatobj", "onResponse: " + busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().size());
                            for (int j = 0; j < busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().size(); j++) {

                                for (int k = 0; k < busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).size(); k++) {

                                    busSeatResponseListModel = new BusSeatResponseListModel();
                                    busSeatResponseListModel.setColumnNo(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getColumnNo());
                                    busSeatResponseListModel.setHeight(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getHeight()));
                                    busSeatResponseListModel.setLadiesSeat(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getIsLadiesSeat());
                                    busSeatResponseListModel.setMalesSeat(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getIsMalesSeat());
                                    busSeatResponseListModel.setUpper(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getIsUpper());
                                    busSeatResponseListModel.setSeatrowno(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getRowNo());
                                    busSeatResponseListModel.setSeatprice(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getSeatFare()));
                                    busSeatResponseListModel.setSeatindex(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getSeatIndex()));
                                    busSeatResponseListModel.setSeatname(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getSeatName());
                                    busSeatResponseListModel.setSeatstatus(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getSeatStatus());
                                    busSeatResponseListModel.setSeattype(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getSeatType()));
                                    busSeatResponseListModel.setWidth(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getWidth()));
                                    busSeatResponseListModel.setSeatpublishedprice(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getPublishedPrice()));

                                    //price
                                    busSeatResponseListModel.setCurrencyCode(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getCurrencyCode()));
                                    busSeatResponseListModel.setBasePrice(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getBasePrice()));
                                    busSeatResponseListModel.setTax(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getTax()));
                                    busSeatResponseListModel.setOtherCharges(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getOtherCharges()));
                                    busSeatResponseListModel.setDiscount(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getDiscount()));
//                                    busSeatResponseListModel.setSeatpublishedprice(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getPublishedPrice()));
                                    busSeatResponseListModel.setPublishedPriceRoundedOff(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getPublishedPriceRoundedOff()));
                                    busSeatResponseListModel.setOfferedPrice(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getOfferedPrice()));
                                    busSeatResponseListModel.setOfferedPriceRoundedOff(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getOfferedPriceRoundedOff()));
                                    busSeatResponseListModel.setAgentCommission(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getAgentCommission()));
                                    busSeatResponseListModel.setAgentMarkUp(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getAgentMarkUp()));
                                    busSeatResponseListModel.setTDS(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getTds()));
                                    busSeatResponseListModel.setTDS(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getTds()));

                                    //gst
                                    busSeatResponseListModel.setCGSTAmount(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getCGSTAmount()));
                                    busSeatResponseListModel.setCGSTRate(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getCGSTRate()));
                                    busSeatResponseListModel.setCessAmount(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getCessAmount()));
                                    busSeatResponseListModel.setCessRate(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getCessRate()));
                                    busSeatResponseListModel.setIGSTAmount(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getIGSTAmount()));
                                    busSeatResponseListModel.setIGSTRate(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getIGSTRate()));
                                    busSeatResponseListModel.setSGSTAmount(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getSGSTAmount()));
                                    busSeatResponseListModel.setSGSTRate(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getSGSTRate()));
                                    busSeatResponseListModel.setTaxableAmount(String.valueOf(busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getGst().getTaxableAmount()));


                                    double i2 = busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getPublishedPrice() / 60000;
//                                    tv.setText(new DecimalFormat("##.##").format(i2));
                                    Log.e("seat", "rrr" + new DecimalFormat("##.##").format(i2));
                                    Log.e("seat1", "rrr1" + busseatresponsemodel.getGetBusSeatLayOutResult().getSeatLayoutDetails().getSeatLayout().getSeatDetails().get(j).get(k).getPrice().getPublishedPrice());
                                    list.add(busSeatResponseListModel);


                                }


                            }

                            paramBording = new HashMap<>();
                            paramBording.put("EndUserIp", "");
                            paramBording.put("ResultIndex", getBusIndex);
                            paramBording.put("TraceId", getTrace_id);
                            paramBording.put("TokenId", getBusToken);
//                            getBusBording(paramBording);
                            LinearLayoutManager linearLayoutManager = new GridLayoutManager(context, 4);
                            binding.seatRviewBus.setLayoutManager(linearLayoutManager);
                            busSeatListAdapter = new BusSeatListAdapter(BusSeatActivity.this, list, BusSeatActivity.this);
                            binding.seatRviewBus.setAdapter(busSeatListAdapter);


                        } else {
                            Toast.makeText(BusSeatActivity.this, "Response change", Toast.LENGTH_SHORT).show();
                        }
                    } else if (busseatresponsemodel.getGetBusSeatLayOutResult().getError().getErrorCode() == 2) {
                        Toast.makeText(BusSeatActivity.this, busseatresponsemodel.getGetBusSeatLayOutResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BusSeatActivity.this, busseatresponsemodel.getGetBusSeatLayOutResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else if (response.code() == 401) {
                    Toast.makeText(BusSeatActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
                    Toast.makeText(BusSeatActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } else if (response.code() == 503) {
                    Toast.makeText(BusSeatActivity.this, "Service Unavilable", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 400) {
                    Toast.makeText(BusSeatActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<BusseatResponsemodel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(BusSeatActivity.this, "Internal Server Error", Toast.LENGTH_LONG).show();
                Log.e("bus seat", "onResponse: ");
            }
        });
    }

//    @Override
//    public void onItemClick(double finalamount, double finalseatno, int noofseat, String columnno, String seatheight, boolean isladis, boolean ismale, boolean isupper, String seatrow, String seatprice, String seatindex, String seatname, boolean seatstatus, String seattype, String seatwidth, String currencycode, String baseprice, String tax, String othercharge, String discount, String publishedprice, String publishedroundoff, String offerprice, String opfferroundof, String agentcommission, String agentmarkup, String tds, String cgstamount, String cgstrate, String cessamount, String cessrate, String igstamount, String igstrate, String sgsamount, String sgstrate, String amounttaxable) {
//        binding.btncontinu.setVisibility(View.VISIBLE);
//        binding.layBottam.setVisibility(View.VISIBLE);
////        String  finalseatdata= String.valueOf(finalseatno);
//
//        binding.tvseatno.setText(finalseatno+"");
//        binding.tvSeatprce.setText(finalamount + "");
//
////        String finalseatnostr = finalseatno.substring(0, finalseatno.length() - 1);
////        shared = getSharedPreferences("nict", MODE_PRIVATE);
////        Log.e("count seat","seat"+noofseat);
////        SharedPreferences.Editor editor = shared.edit();
////        editor.putString("seatindex", seatindex);
////        editor.putString("seatname", seatname);
////        editor.putString("columnno", columnno);
////        editor.putString("seatrow", seatrow);
////        editor.putBoolean("seatstatus", seatstatus);
////        editor.putString("seattype", seattype);
//////        editor.putString("published", getpublished);
////        editor.putInt("Adultno", noofseat);
////        editor.putString("finalseat", finalseatnostr);
////        editor.putString("finalamount", finalamount + "");
////        editor.apply();
////
////        binding.tvseatno.setText(finalseatnostr);
////        binding.tvSeatprce.setText(finalamount + "");
////        SeatAdd seatAdd = new SeatAdd();
////        seatAdd.setColumnNo(columnno);
////        seatAdd.setHeight(seatheight);
////        seatAdd.setIsLadiesSeat(String.valueOf(isladis));
////        seatAdd.setIsMalesSeat(String.valueOf(ismale));
////        seatAdd.setIsUpper(String.valueOf(isupper));
////        seatAdd.setRowNo(seatrow);
////        seatAdd.setSeatprice(seatprice);
////        seatAdd.setSeatName(seatname);
////        seatAdd.setSeatIndex(seatindex);
////        seatAdd.setSeatStatus(String.valueOf(seatstatus));
////        seatAdd.setSeatType(seattype);
////        seatAdd.setWidth(seatwidth);
////
////        //price
////        seatAdd.setCurrencyCode(currencycode);
////        seatAdd.setBasePrice(baseprice);
////        seatAdd.setTax(tax);
////        seatAdd.setOtherCharges(othercharge);
////        seatAdd.setDiscount(discount);
////        seatAdd.setPublishedPrice(publishedprice);
////        seatAdd.setPublishedPriceRoundedOff(publishedroundoff);
////        seatAdd.setOfferedPrice(offerprice);
////        seatAdd.setOfferedPriceRoundedOff(opfferroundof);
////        seatAdd.setAgentCommission(agentcommission);
////        seatAdd.setAgentMarkUp(agentmarkup);
////        seatAdd.setTDS(tds);
////
////        //gst
////        seatAdd.setCGSTAmount(cgstamount);
////        seatAdd.setCGSTRate(cgstrate);
////        seatAdd.setCessAmount(cessamount);
////        seatAdd.setCessRate(cessrate);
////        seatAdd.setIGSTAmount(igstamount);
////        seatAdd.setIGSTRate(igstrate);
////        seatAdd.setSGSTAmount(sgsamount);
////        seatAdd.setSGSTRate(sgstrate);
////        seatAdd.setTaxableAmount(amounttaxable);
////        seatadd.add(seatAdd);
//    }

    @Override
    public void onItemClick(double finalamount, String finalseatno, int noofseat, String columnno, String seatheight, boolean isladis, boolean ismale, boolean isupper, String seatrow, String seatprice, String seatindex, String seatname, boolean seatstatus, String seattype, String seatwidth, String currencycode, String baseprice, String tax, String othercharge, String discount, String publishedprice, String publishedroundoff, String offerprice, String opfferroundof, String agentcommission, String agentmarkup, String tds, String cgstamount, String cgstrate, String cessamount, String cessrate, String igstamount, String igstrate, String sgsamount, String sgstrate, String amounttaxable, String addtype, int finalpostion) {

        if (addtype.equals("ADD")) {
            binding.btncontinu.setVisibility(View.VISIBLE);
            binding.layBottam.setVisibility(View.VISIBLE);
            String finalseatnostr = finalseatno.substring(0, finalseatno.length() - 1);
            shared = getSharedPreferences("nict", MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putInt("Adultno", noofseat);
            editor.putString("finalseat", finalseatnostr);
            editor.putString("finalamount", finalamount + "");
            editor.apply();
            binding.tvseatno.setText(finalseatnostr);
            binding.tvSeatprce.setText(finalamount + "");

//            seatAdd.setColumnNo(columnno);
//            seatAdd.setHeight(seatheight);
//            seatAdd.setIsLadiesSeat(String.valueOf(isladis));
//            seatAdd.setIsMalesSeat(String.valueOf(ismale));
//            seatAdd.setIsUpper(String.valueOf(isupper));
//            seatAdd.setRowNo(seatrow);
//            seatAdd.setSeatprice(seatprice);
//            seatAdd.setSeatName(seatname);
//            seatAdd.setSeatIndex(seatindex);
//            seatAdd.setSeatStatus(String.valueOf(seatstatus));
//            seatAdd.setSeatType(seattype);
//            seatAdd.setWidth(seatwidth);
//            Log.e("seatname", "add" + seatname);
//
//            //price
//            seatAdd.setCurrencyCode(currencycode);
//            seatAdd.setBasePrice(baseprice);
//            seatAdd.setTax(tax);
//            seatAdd.setOtherCharges(othercharge);
//            seatAdd.setDiscount(discount);
//            seatAdd.setPublishedPrice(publishedprice);
//            seatAdd.setPublishedPriceRoundedOff(publishedroundoff);
//            seatAdd.setOfferedPrice(offerprice);
//            seatAdd.setOfferedPriceRoundedOff(opfferroundof);
//            seatAdd.setAgentCommission(agentcommission);
//            seatAdd.setAgentMarkUp(agentmarkup);
//            seatAdd.setTDS(tds);
//
//            //gst
//            seatAdd.setCGSTAmount(cgstamount);
//            seatAdd.setCGSTRate(cgstrate);
//            seatAdd.setCessAmount(cessamount);
//            seatAdd.setCessRate(cessrate);
//            seatAdd.setIGSTAmount(igstamount);
//            seatAdd.setIGSTRate(igstrate);
//            seatAdd.setSGSTAmount(sgsamount);
//            seatAdd.setSGSTRate(sgstrate);
//            seatAdd.setTaxableAmount(amounttaxable);
//            seatadd.add(seatAdd);
//            hashSet.addAll(seatadd);

        } else {
            if(finalseatno.equals(""))
            {
                binding.btncontinu.setVisibility(View.GONE);
                binding.layBottam.setVisibility(View.GONE);

            }
            else
            {
                binding.btncontinu.setVisibility(View.VISIBLE);
                binding.layBottam.setVisibility(View.VISIBLE);

            }
            shared = getSharedPreferences("nict", MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putInt("Adultno", noofseat);
            editor.putString("finalseat", finalseatno);
            editor.putString("finalamount", finalamount + "");
            editor.apply();
//
            binding.tvseatno.setText(finalseatno);
            binding.tvSeatprce.setText(finalamount + "");



//            seatAdd.setColumnNo(columnno);
//            seatAdd.setHeight(seatheight);
//            seatAdd.setIsLadiesSeat(String.valueOf(isladis));
//            seatAdd.setIsMalesSeat(String.valueOf(ismale));
//            seatAdd.setIsUpper(String.valueOf(isupper));
//            seatAdd.setRowNo(seatrow);
//            seatAdd.setSeatprice(seatprice);
//            seatAdd.setSeatName(seatname);
//            seatAdd.setSeatIndex(seatindex);
//            Log.e("seatname","remove"+seatname);
//
//            seatAdd.setSeatStatus(String.valueOf(seatstatus));
//            seatAdd.setSeatType(seattype);
//            seatAdd.setWidth(seatwidth);
//
//            //price
//            seatAdd.setCurrencyCode(currencycode);
//            seatAdd.setBasePrice(baseprice);
//            seatAdd.setTax(tax);
//            seatAdd.setOtherCharges(othercharge);
//            seatAdd.setDiscount(discount);
//            seatAdd.setPublishedPrice(publishedprice);
//            seatAdd.setPublishedPriceRoundedOff(publishedroundoff);
//            seatAdd.setOfferedPrice(offerprice);
//            seatAdd.setOfferedPriceRoundedOff(opfferroundof);
//            seatAdd.setAgentCommission(agentcommission);
//            seatAdd.setAgentMarkUp(agentmarkup);
//            seatAdd.setTDS(tds);
//
//            //gst
//            seatAdd.setCGSTAmount(cgstamount);
//            seatAdd.setCGSTRate(cgstrate);
//            seatAdd.setCessAmount(cessamount);
//            seatAdd.setCessRate(cessrate);
//            seatAdd.setIGSTAmount(igstamount);
//            seatAdd.setIGSTRate(igstrate);
//            seatAdd.setSGSTAmount(sgsamount);
//            seatAdd.setSGSTRate(sgstrate);
//            seatAdd.setTaxableAmount(amounttaxable);
//            seatadd.remove(seatAdd);
//            seatadd.clear();
//            seatadd.addAll(set);

//            seatadd.addAll(hashSet);
//            yourList.clear();
//            yourList.addAll(set);


        }


    }

    @Override
    public void onItemClick1(double finalamount, String finalseatno) {


//        if()
//        {
//
//        }
//        else
//        {
//
//        }
//
//        binding.tvSeatprce.setText(finalamount + "");
//        binding.tvseatno.setText(finalseatno + "");

    }
}