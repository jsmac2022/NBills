package com.nictbills.app.activities.tbo.bus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.tbo.bus.adapter.BusSeatListAdapter;
import com.nictbills.app.activities.tbo.bus.model.busblockreqmodel.BusBlockRequestModel;
import com.nictbills.app.activities.tbo.bus.model.busblockreqmodel.Gst;
import com.nictbills.app.activities.tbo.bus.model.busblockreqmodel.Passenger;
import com.nictbills.app.activities.tbo.bus.model.busblockreqmodel.Price;
import com.nictbills.app.activities.tbo.bus.model.busblockreqmodel.Seat;
import com.nictbills.app.activities.tbo.bus.model.busblockresponse.BusBlockResponseModel;
import com.nictbills.app.activities.tbo.flight.model.air_search.air_search_request.Segment;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelPassengerListAdapter;
import com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.AddPassenger;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityBusProcessedToBookingBinding;
import com.nictbills.app.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusProcessedToBookingActivity extends BaseActivity {
    SharedPreferences shared, sharedPreferences;
//    busSeatListAdapter busSeatListAdapter;
    ActivityBusProcessedToBookingBinding binding;
    String getfinal_finalseat , getfinal_finalamount ,mobileNumber, getMember_id, getresultindex, getTrace_id, getBusToken, getAgency_id, gettravlesname, getoriginname, getdropingname, getdroping_index, getbording_index;
    AddBusMemberActivity addBusMemberActivity;
    ArrayList<AddPassenger> busplist = new ArrayList<AddPassenger>();
    HotelPassengerListAdapter hotelPassengerListAdapter;
    JSONObject studentsObj;
    String sendbusblockreq;
    BusBlockResponseModel busBlockResponseModel;
    BusSeatListAdapter busSeatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_processed_to_booking);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bus_processed_to_booking);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettravlesname = shared.getString("BusTravles", "");
        getoriginname = shared.getString("originname", "");
        getdropingname = shared.getString("Dropinname", "");
        getbording_index = shared.getString("Bordingindex", "");
        getdroping_index = shared.getString("Dropingindex", "");
        getBusToken = shared.getString("TokenBus", "");
        getAgency_id = shared.getString("Agencyid", "");
        getMember_id = shared.getString("Memberid", "");
        getTrace_id = shared.getString("TraceId", "");
        getresultindex = shared.getString("Resultindex", "");
        getfinal_finalseat = shared.getString("finalseat", "");
        getfinal_finalamount = shared.getString("finalamount", "");
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        click();
        binding.busname.setText(gettravlesname);
        binding.tvOrigin.setText(getoriginname);
        binding.tvDestination.setText(getdropingname);
        binding.tvSeatno.setText(getfinal_finalseat);
        binding.tvSeatprice.setText("Rs."+getfinal_finalamount);
        JSONArray jsonarrayseat = new JSONArray();
        for (int i = 0; i < addBusMemberActivity.addpassenger.size(); i++)
        {
            JSONObject passenger = new JSONObject();
            try {
                passenger.put("LeadPassenger", addBusMemberActivity.addpassenger.get(i).getUleadpassenger());
                passenger.put("PassengerId", "0");
                passenger.put("Title", "Mr.");
                passenger.put("Address", addBusMemberActivity.addpassenger.get(i).getUaddress());
                passenger.put("Age", addBusMemberActivity.addpassenger.get(i).getUage());
                passenger.put("Email", addBusMemberActivity.addpassenger.get(i).getUemail());
                passenger.put("FirstName", addBusMemberActivity.addpassenger.get(i).getUname());
                passenger.put("Gender", addBusMemberActivity.addpassenger.get(i).getUgender());
                passenger.put("IdNumber", "");
                passenger.put("IdType", "");
                passenger.put("LastName", addBusMemberActivity.addpassenger.get(i).getUlastname());
                passenger.put("Phoneno", addBusMemberActivity.addpassenger.get(i).getUmobile());

                AddPassenger addPassenger = new AddPassenger();
                addPassenger.setFirstName(addBusMemberActivity.addpassenger.get(i).getUname());
                addPassenger.setEmail(addBusMemberActivity.addpassenger.get(i).getUemail());
                addPassenger.setAge(addBusMemberActivity.addpassenger.get(i).getUage());
                busplist.add(addPassenger);

                for (int j = 0; j < busSeatListAdapter.seatadd.size(); j++) {

                    if (i == j)
                    {

                        JSONObject seatobj   = new JSONObject();
                        JSONObject  priceobj = new JSONObject();
                        JSONObject  gstobj = new JSONObject();

                        seatobj.put("ColumnNo", busSeatListAdapter.seatadd.get(j).getColumnNo());
                        seatobj.put("Height", busSeatListAdapter.seatadd.get(j).getHeight());
                        seatobj.put("IsLadiesSeat", busSeatListAdapter.seatadd.get(j).getIsLadiesSeat());
                        seatobj.put("IsMalesSeat", busSeatListAdapter.seatadd.get(j).getIsMalesSeat());
                        seatobj.put("IsUpper", busSeatListAdapter.seatadd.get(j).getIsUpper());
                        seatobj.put("RowNo", busSeatListAdapter.seatadd.get(j).getRowNo());
                        seatobj.put("SeatIndex", busSeatListAdapter.seatadd.get(j).getSeatIndex());
                        seatobj.put("SeatName", busSeatListAdapter.seatadd.get(j).getSeatName());
                        seatobj.put("SeatStatus", busSeatListAdapter.seatadd.get(j).getSeatStatus());
                        seatobj.put("SeatType", busSeatListAdapter.seatadd.get(j).getSeatType());
                        seatobj.put("Width", busSeatListAdapter.seatadd.get(j).getWidth());

                        //price
                        priceobj.put("CurrencyCode", busSeatListAdapter.seatadd.get(j).getCurrencyCode());
                        priceobj.put("BasePrice", busSeatListAdapter.seatadd.get(j).getBasePrice());
                        priceobj.put("Tax", busSeatListAdapter.seatadd.get(j).getTax());
                        priceobj.put("OtherCharges", busSeatListAdapter.seatadd.get(j).getOtherCharges());
                        priceobj.put("Discount", busSeatListAdapter.seatadd.get(j).getDiscount());
                        priceobj.put("PublishedPrice", busSeatListAdapter.seatadd.get(j).getPublishedPrice());
                        priceobj.put("PublishedPriceRoundedOff", busSeatListAdapter.seatadd.get(j).getPublishedPriceRoundedOff());
                        priceobj.put("OfferedPrice", busSeatListAdapter.seatadd.get(j).getOfferedPrice());
                        priceobj.put("OfferedPriceRoundedOff", busSeatListAdapter.seatadd.get(j).getOfferedPriceRoundedOff());
                        priceobj.put("AgentCommission", busSeatListAdapter.seatadd.get(j).getOfferedPriceRoundedOff());
                        priceobj.put("AgentCommission", busSeatListAdapter.seatadd.get(j).getAgentCommission());
                        priceobj.put("AgentMarkUp", busSeatListAdapter.seatadd.get(j).getAgentMarkUp());
                        priceobj.put("TDS", busSeatListAdapter.seatadd.get(j).getTDS());
                        priceobj.put("GST", gstobj);
                        //gst
                        gstobj.put("CGSTAmount", busSeatListAdapter.seatadd.get(j).getCGSTAmount());
                        gstobj.put("CGSTRate", busSeatListAdapter.seatadd.get(j).getCGSTRate());
                        gstobj.put("CessAmount", busSeatListAdapter.seatadd.get(j).getCessAmount());
                        gstobj.put("CessRate", busSeatListAdapter.seatadd.get(j).getCessRate());
                        gstobj.put("IGSTAmount", busSeatListAdapter.seatadd.get(j).getIGSTAmount());
                        gstobj.put("IGSTRate", busSeatListAdapter.seatadd.get(j).getIGSTRate());
                        gstobj.put("SGSTAmount", busSeatListAdapter.seatadd.get(j).getSGSTAmount());
                        gstobj.put("SGSTRate", busSeatListAdapter.seatadd.get(j).getSGSTRate());
                        gstobj.put("TaxableAmount", busSeatListAdapter.seatadd.get(j).getTaxableAmount());
                        seatobj.put("Price", priceobj);
                        passenger.put("Seat", seatobj);
                    }


                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonarrayseat.put(passenger);
             studentsObj = new JSONObject();

            try {
                studentsObj.put("EndUserIp", "");
                studentsObj.put("mobileNo", mobileNumber);
                studentsObj.put("ResultIndex", getresultindex);
                studentsObj.put("TraceId", getTrace_id);
                studentsObj.put("TokenId", getBusToken);
                studentsObj.put("BoardingPointId", getbording_index);
                studentsObj.put("DroppingPointId", getdroping_index);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (addBusMemberActivity.addpassenger.size() == 0)
        {


        } else {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            binding.recyclerViewBusPlist.setLayoutManager(linearLayoutManager);
            hotelPassengerListAdapter = new HotelPassengerListAdapter(BusProcessedToBookingActivity.this, busplist);
            binding.recyclerViewBusPlist.setAdapter(hotelPassengerListAdapter);
        }

    }

    public void click() {

        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnbusbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusBlockRequestModel busBlockRequestModel = new BusBlockRequestModel();
                busBlockRequestModel.setEndUserIp("");
                busBlockRequestModel.setBordingname(getoriginname);
                busBlockRequestModel.setDroppingname(getdropingname);
                busBlockRequestModel.setTravellername(gettravlesname);
                busBlockRequestModel.setMobileNo(mobileNumber);
                busBlockRequestModel.setTokenId(getBusToken);
                busBlockRequestModel.setTraceId(getTrace_id);
                busBlockRequestModel.setResultIndex(getresultindex);
                busBlockRequestModel.setBoardingPointId(Integer.valueOf(getbording_index));
                busBlockRequestModel.setDroppingPointId(Integer.valueOf(getdroping_index));

                Log.e("rt","111"+addBusMemberActivity.addpassenger.size());
                ArrayList<Passenger> arraylistpassenger = new ArrayList<>();
                ArrayList<String> NEWLIST = new ArrayList<>();
                Passenger passengerbus;

                for (int i = 0; i < addBusMemberActivity.addpassenger.size(); i++) {
                    passengerbus = new Passenger();
                    passengerbus.setLeadPassenger(Boolean.valueOf(addBusMemberActivity.addpassenger.get(i).getUleadpassenger()));
                    passengerbus.setPassengerId(Integer.valueOf("0"));
                    passengerbus.setTitle("Mr.");
                    passengerbus.setAddress(addBusMemberActivity.addpassenger.get(i).getUaddress());
                    passengerbus.setAge(Integer.valueOf(addBusMemberActivity.addpassenger.get(i).getUage()));
                    passengerbus.setEmail(addBusMemberActivity.addpassenger.get(i).getUemail());
                    passengerbus.setFirstName(addBusMemberActivity.addpassenger.get(i).getUname());
                    passengerbus.setGender(Integer.valueOf(addBusMemberActivity.addpassenger.get(i).getUgender()));
                    passengerbus.setIdNumber("");
                    passengerbus.setIdType("");
                    passengerbus.setLastName(addBusMemberActivity.addpassenger.get(i).getUlastname());
                    passengerbus.setPhoneno(addBusMemberActivity.addpassenger.get(i).getUmobile());

                    for (int j = 0; j < busSeatListAdapter.seatadd.size(); j++) {
                        if (i == j)
                        {
                            Seat seatbus = new Seat();
                            Price pricebus = new Price();
                            Gst gstbus = new Gst();
//sea
                            seatbus.setColumnNo(busSeatListAdapter.seatadd.get(j).getColumnNo());
                            seatbus.setHeight(Integer.valueOf(busSeatListAdapter.seatadd.get(j).getHeight()));
                            seatbus.setIsLadiesSeat(Boolean.valueOf(busSeatListAdapter.seatadd.get(j).getIsLadiesSeat()));
                            seatbus.setIsMalesSeat(Boolean.valueOf(busSeatListAdapter.seatadd.get(j).getIsMalesSeat()));
                            seatbus.setIsUpper(Boolean.valueOf(busSeatListAdapter.seatadd.get(j).getIsUpper()));
                            seatbus.setRowNo(busSeatListAdapter.seatadd.get(j).getRowNo());
                            seatbus.setSeatIndex(busSeatListAdapter.seatadd.get(j).getSeatIndex());
                            seatbus.setSeatName(busSeatListAdapter.seatadd.get(j).getSeatName());
                            Log.e("on req","send"+busSeatListAdapter.seatadd.get(j).getSeatName());
                            seatbus.setSeatStatus(Boolean.valueOf(busSeatListAdapter.seatadd.get(j).getSeatStatus()));
                            seatbus.setSeatType(Integer.valueOf(busSeatListAdapter.seatadd.get(j).getSeatType()));
                            seatbus.setWidth(Integer.valueOf(busSeatListAdapter.seatadd.get(j).getWidth()));

                            //price

                            pricebus.setCurrencyCode(busSeatListAdapter.seatadd.get(j).getCurrencyCode());
                            pricebus.setBasePrice(Double.valueOf(busSeatListAdapter.seatadd.get(j).getBasePrice()));
                            pricebus.setTax(Double.valueOf(busSeatListAdapter.seatadd.get(j).getTax()));
                            pricebus.setOtherCharges(Double.valueOf(busSeatListAdapter.seatadd.get(j).getOtherCharges()));
                            pricebus.setDiscount(Double.valueOf(busSeatListAdapter.seatadd.get(j).getDiscount()));
                            pricebus.setPublishedPrice(Double.valueOf(busSeatListAdapter.seatadd.get(j).getPublishedPrice()));
                            pricebus.setPublishedPriceRoundedOff(Double.valueOf(busSeatListAdapter.seatadd.get(j).getPublishedPriceRoundedOff()));
                            pricebus.setOfferedPrice(Double.valueOf(busSeatListAdapter.seatadd.get(j).getOfferedPrice()));
                            pricebus.setOfferedPriceRoundedOff(Double.valueOf(busSeatListAdapter.seatadd.get(j).getOfferedPriceRoundedOff()));
                            pricebus.setAgentCommission(Double.valueOf(busSeatListAdapter.seatadd.get(j).getAgentCommission()));
                            pricebus.setAgentMarkUp(Double.valueOf(busSeatListAdapter.seatadd.get(j).getAgentMarkUp()));
                            pricebus.setTds(Double.valueOf(busSeatListAdapter.seatadd.get(j).getTDS()));

                            gstbus.setCGSTAmount(Double.valueOf(busSeatListAdapter.seatadd.get(j).getCGSTAmount()));
                            gstbus.setCessRate(Double.valueOf(busSeatListAdapter.seatadd.get(j).getCGSTRate()));
                            gstbus.setCessAmount(Double.valueOf(busSeatListAdapter.seatadd.get(j).getCessAmount()));
                            gstbus.setCessRate(Double.valueOf(busSeatListAdapter.seatadd.get(j).getCessRate()));
                            gstbus.setIGSTAmount(Double.valueOf(busSeatListAdapter.seatadd.get(j).getIGSTAmount()));
                            gstbus.setIGSTRate(Double.valueOf(busSeatListAdapter.seatadd.get(j).getIGSTRate()));
                            gstbus.setSGSTAmount(Double.valueOf(busSeatListAdapter.seatadd.get(j).getSGSTAmount()));
                            gstbus.setSGSTRate(Double.valueOf(busSeatListAdapter.seatadd.get(j).getSGSTRate()));
                            gstbus.setTaxableAmount(Double.valueOf(busSeatListAdapter.seatadd.get(j).getTaxableAmount()));
                            pricebus.setGst(gstbus);
                            seatbus.setPrice(pricebus);
                            passengerbus.setSeat(seatbus);

                        }

                    }

                    arraylistpassenger.add(passengerbus);
                }

                busBlockRequestModel.setPassenger(arraylistpassenger);
                busblock(busBlockRequestModel);
                Log.e("on click bution", "t.getMessage()"+busBlockRequestModel.toString());
            }
        });
    }

    private void busblock(BusBlockRequestModel busBlockRequestModel) {
        Log.e("api call in", "t.getMessage()"+busBlockRequestModel.toString());
        progressDialogShow();
        RetrofitInterface retrofitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_BUS).create(RetrofitInterface.class);
        Call<BusBlockResponseModel> responseBodyCall = retrofitInterface.busblock(busBlockRequestModel);
        responseBodyCall.enqueue(new Callback<BusBlockResponseModel>() {
            @Override
            public void onResponse(Call<BusBlockResponseModel> call, Response<BusBlockResponseModel> response) {
                progressDialogDismiss();

                if (response.code() == 200) {
                    busBlockResponseModel = response.body();

                    if (busBlockResponseModel.getBlockResult().getIsPriceChanged()==true)
                    {
                        Toast.makeText(BusProcessedToBookingActivity.this, "Price Change Search Again ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BusProcessedToBookingActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Log.e("yessbusblock+11", "t.getMessage()");
                        Log.e("yessbusblock+11", "t.getMessage()" + response.body());

                        if(busBlockResponseModel.getBlockResult().getError().getErrorCode()==0)
                        {
                            Intent intent = new Intent(BusProcessedToBookingActivity.this, BusPaymentActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(BusProcessedToBookingActivity.this, busBlockResponseModel.getBlockResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }


                } else {
                    Log.e("ELSE  +11", "t.getMessage()");
                    Toast.makeText(BusProcessedToBookingActivity.this, "Api Response Issue", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<BusBlockResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Toast.makeText(BusProcessedToBookingActivity.this, "Invalid Token", Toast.LENGTH_SHORT).show();
                Log.e("reeorr+", t.getMessage());
                Log.e("reeorr+", t.toString());
            }
        });

    }

}