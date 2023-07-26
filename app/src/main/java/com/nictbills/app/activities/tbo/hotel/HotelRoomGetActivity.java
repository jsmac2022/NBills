package com.nictbills.app.activities.tbo.hotel;

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
import com.nictbills.app.activities.tbo.hotel.adapter.HotelRoomListAdapter;
import com.nictbills.app.activities.tbo.hotel.model.addroomdetails.HotelRoomsDetailsAdd;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockrep.Error;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockrep.HotelBlockResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelBlockRoomRequestmodel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelRoomsDetail;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.RoomGuests;
import com.nictbills.app.activities.tbo.hotel.model.hotelgetroom.HotelGeRoomResponse;
import com.nictbills.app.activities.tbo.hotel.model.hotelroomlistresponse.HotelRoomListResponse;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityHotelRoomGetBinding;
import com.nictbills.app.utils.Util;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelRoomGetActivity extends BaseActivity {
    ActivityHotelRoomGetBinding binding;
    SharedPreferences shared, sharedPreferences;
    String gethotel_checkin, roomcount, gethotel_categoryid, gethotel_cityname, gethotel_checkout, mobileNumber, gettrace_id, gethotel_name, getcountry_code, getcity_code, gethotel_index, gethotel_code, gettokenhotel_id;
    HashMap<String, String> param;
    HotelRoomListAdapter hotelRoomListAdapter;
    HotelRoomListResponse hotelRoomListResponse;
    ArrayList<HotelRoomListResponse> hotel_roomlist = new ArrayList<HotelRoomListResponse>();
    static ArrayList<HotelRoomsDetailsAdd> addhoteldetails = new ArrayList<HotelRoomsDetailsAdd>();
    HotelRoomListAdapter.OnItemClickListener listener;
    com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelRoomsDetail hotelRoomsDetails;
    com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.Price price;
    HotelRoomsDetailsAdd hotelRoomsDetailsAdd;
    HotelBlockRoomRequestmodel hotelBlockRoomRequestmodel;
    RoomGuests roomGuests;
    int finalroomcount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hotel_room_get);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettrace_id = shared.getString("TraceId", "");
        roomcount = shared.getString("roomnocount", "");
        finalroomcount = Integer.parseInt(roomcount);
        gettokenhotel_id = shared.getString("Tokenid_Hotel", "");
        getcountry_code = shared.getString("CountryCode", "");
        getcity_code = shared.getString("CityCode", "");
        gethotel_index = shared.getString("Hotelindex", "");
        gethotel_code = shared.getString("Hotelcode", "");
        gethotel_name = shared.getString("HotelName", "");
        gethotel_checkin = shared.getString("checkindate", "");
        gethotel_checkout = shared.getString("checkoutdate", "");
        gethotel_cityname = shared.getString("cityname", "");
        gethotel_categoryid = shared.getString("catgeoryid", "");
        param = new HashMap<>();
        param.put("EndUserIp", "");
        param.put("ResultIndex", gethotel_index);
        param.put("HotelCode", gethotel_code);
        param.put("TokenId", gettokenhotel_id);
        param.put("TraceId", gettrace_id);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        listener = new HotelRoomListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String roomdesc, String getroom_index, String roomtypecode, String roomtype_name, String room_planname, String room_plancode, String getcurrencycode, String getroomprice, String gettax, String guestextracharge, String childcharge, String othercharge, String publishprice, String offerprice, String publishroundoffprice, String offerroundoffprice, String servicetax, String discount, String agentcommision, String agentmarkup, String tds, int clickcount, String checkstatus) {


                binding.recyclerViewHoteLRoomlist.post(new Runnable() {
                    @Override
                    public void run() {

                        if (getroom_index.equals("")) {
                            binding.btnContinueviewbook.setVisibility(View.GONE);
                        } else {
                            if (clickcount == finalroomcount) {
                                hotelRoomListAdapter.notifyDataSetChanged();
                                binding.btnContinueviewbook.setVisibility(View.VISIBLE);

                            } else {
                                hotelRoomListAdapter.notifyDataSetChanged();
                            }

                        }

                        if (finalroomcount == 1) {
                            addhoteldetails.clear();
                            SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("RoomTypecode", roomtypecode);
                            editor.putString("Roomindex", getroom_index);
                            editor.putString("Roomdesc", roomdesc);
                            editor.putString("RoomTypeName", roomtype_name);
                            editor.putString("RoomPlanCode", room_plancode);
                            editor.putString("RoomPlanName", room_planname);
                            editor.putString("CurremcyCode", getcurrencycode);
                            editor.putString("RoomPrice", getroomprice);
                            editor.putString("RoomTax", gettax);
                            editor.putString("RoomGuestExtraCharge", guestextracharge);
                            editor.putString("RoomChiledCharge", childcharge);
                            editor.putString("Othercharge", othercharge);
                            editor.putString("publishprice", publishprice);
                            Log.e("publishprice", "publishpriceonclick" + publishprice);
                            Log.e("getroomprice", "publishpriceonclick" + getroomprice);
                            editor.putString("offerprice", offerprice);
                            editor.putString("publishroundoffprice", publishroundoffprice);
                            editor.putString("offerroundoffprice", offerroundoffprice);
                            editor.putString("Servicetax", servicetax);
                            editor.putString("Discount", discount);
                            editor.putString("AgentCommision", agentcommision);
                            editor.putString("AgentMarkup", agentmarkup);
                            editor.putString("TDS", tds);
                            editor.apply();
                            hotelRoomsDetailsAdd = new HotelRoomsDetailsAdd();
                            hotelRoomsDetailsAdd.setRoomIndex(getroom_index);
                            hotelRoomsDetailsAdd.setRoomTypeCode(roomtypecode);
                            hotelRoomsDetailsAdd.setRoomTypeName(roomtype_name);
                            hotelRoomsDetailsAdd.setRatePlanCode(room_plancode);
                            hotelRoomsDetailsAdd.setBedTypeCode(null);
                            hotelRoomsDetailsAdd.setSmokingPreference(String.valueOf(0));
                            hotelRoomsDetailsAdd.setSupplements(null);
                            hotelRoomsDetailsAdd.setCurrencyCode(getcurrencycode);
                            hotelRoomsDetailsAdd.setRoomPrice(getroomprice);
                            hotelRoomsDetailsAdd.setTax(gettax);
                            hotelRoomsDetailsAdd.setExtraGuestCharge(guestextracharge);
                            hotelRoomsDetailsAdd.setChildCharge(childcharge);
                            hotelRoomsDetailsAdd.setOtherCharges(othercharge);
                            hotelRoomsDetailsAdd.setDiscount(discount);
                            hotelRoomsDetailsAdd.setOfferedPrice(offerprice);
                            hotelRoomsDetailsAdd.setPublishedPrice(publishprice);
                            hotelRoomsDetailsAdd.setPublishedPriceRoundedOff(publishroundoffprice);
                            hotelRoomsDetailsAdd.setOfferedPriceRoundedOff(offerroundoffprice);
                            hotelRoomsDetailsAdd.setAgentCommission(agentcommision);
                            hotelRoomsDetailsAdd.setAgentMarkUp(agentmarkup);
                            hotelRoomsDetailsAdd.setTDS(tds);
                            hotelRoomsDetailsAdd.setServicetax(servicetax);
                            addhoteldetails.add(hotelRoomsDetailsAdd);


                        } else {


                            SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("RoomTypecode", roomtypecode);
                            editor.putString("Roomindex", getroom_index);
                            editor.putString("Roomdesc", roomdesc);
                            editor.putString("RoomTypeName", roomtype_name);
                            editor.putString("RoomPlanCode", room_plancode);
                            editor.putString("RoomPlanName", room_planname);
                            editor.putString("CurremcyCode", getcurrencycode);
                            editor.putString("RoomPrice", getroomprice);
                            editor.putString("RoomTax", gettax);
                            editor.putString("RoomGuestExtraCharge", guestextracharge);
                            editor.putString("RoomChiledCharge", childcharge);
                            editor.putString("Othercharge", othercharge);
                            editor.putString("publishprice", publishprice);
                            Log.e("publishprice", "publishpriceonclick" + publishprice);
                            Log.e("getroomprice", "publishpriceonclick" + getroomprice);
                            editor.putString("offerprice", offerprice);
                            editor.putString("publishroundoffprice", publishroundoffprice);
                            editor.putString("offerroundoffprice", offerroundoffprice);
                            editor.putString("Servicetax", servicetax);
                            editor.putString("Discount", discount);
                            editor.putString("AgentCommision", agentcommision);
                            editor.putString("AgentMarkup", agentmarkup);
                            editor.putString("TDS", tds);
                            editor.apply();
                            hotelRoomsDetailsAdd = new HotelRoomsDetailsAdd();
                            hotelRoomsDetailsAdd.setRoomIndex(getroom_index);
                            hotelRoomsDetailsAdd.setRoomTypeCode(roomtypecode);
                            hotelRoomsDetailsAdd.setRoomTypeName(roomtype_name);
                            hotelRoomsDetailsAdd.setRatePlanCode(room_plancode);
                            hotelRoomsDetailsAdd.setBedTypeCode(null);
                            hotelRoomsDetailsAdd.setSmokingPreference(String.valueOf(0));
                            hotelRoomsDetailsAdd.setSupplements(null);
                            hotelRoomsDetailsAdd.setCurrencyCode(getcurrencycode);
                            hotelRoomsDetailsAdd.setRoomPrice(getroomprice);
                            hotelRoomsDetailsAdd.setTax(gettax);
                            hotelRoomsDetailsAdd.setExtraGuestCharge(guestextracharge);
                            hotelRoomsDetailsAdd.setChildCharge(childcharge);
                            hotelRoomsDetailsAdd.setOtherCharges(othercharge);
                            hotelRoomsDetailsAdd.setDiscount(discount);
                            hotelRoomsDetailsAdd.setOfferedPrice(offerprice);
                            hotelRoomsDetailsAdd.setPublishedPrice(publishprice);
                            hotelRoomsDetailsAdd.setPublishedPriceRoundedOff(publishroundoffprice);
                            hotelRoomsDetailsAdd.setOfferedPriceRoundedOff(offerroundoffprice);
                            hotelRoomsDetailsAdd.setAgentCommission(agentcommision);
                            hotelRoomsDetailsAdd.setAgentMarkUp(agentmarkup);
                            hotelRoomsDetailsAdd.setTDS(tds);
                            hotelRoomsDetailsAdd.setServicetax(servicetax);
                            addhoteldetails.add(hotelRoomsDetailsAdd);

                        }


                    }
                });

            }

        };
        onclick();
    }

    public void onclick() {
        get_HotelRoom(param);
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnContinueviewbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotelBlockRoomRequestmodel = new HotelBlockRoomRequestmodel();
                hotelBlockRoomRequestmodel.setResultIndex(gethotel_index);
                hotelBlockRoomRequestmodel.setHotelCode(gethotel_code);
                hotelBlockRoomRequestmodel.setHotelName(gethotel_name);
                hotelBlockRoomRequestmodel.setGuestNationality("IN");
                hotelBlockRoomRequestmodel.setNoOfRooms(roomcount);
                hotelBlockRoomRequestmodel.setClientReferenceNo("0");
                hotelBlockRoomRequestmodel.setIsVoucherBooking(String.valueOf(true));
                hotelBlockRoomRequestmodel.setCategoryId(gethotel_categoryid);
                hotelBlockRoomRequestmodel.setEndUserIp("");
                hotelBlockRoomRequestmodel.setMobileno(mobileNumber);
                hotelBlockRoomRequestmodel.setCity(gethotel_cityname);
                hotelBlockRoomRequestmodel.setCheckindate(gethotel_checkin);
                hotelBlockRoomRequestmodel.setCheckoutdate(gethotel_checkout);
                hotelBlockRoomRequestmodel.setTokenId(gettokenhotel_id);
                hotelBlockRoomRequestmodel.setTraceId(gettrace_id);
                ArrayList<HotelRoomsDetail> hotelroomarrayList = new ArrayList<>();
                ArrayList<RoomGuests> roomguestList = new ArrayList<>();

                for (int j = 0; j < addhoteldetails.size(); j++) {
                    hotelRoomsDetails = new com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelRoomsDetail();
                    price = new com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.Price();

                    hotelRoomsDetails.setRoomIndex(addhoteldetails.get(j).getRoomIndex());
                    hotelRoomsDetails.setRoomTypeCode(addhoteldetails.get(j).getRoomTypeCode());
                    hotelRoomsDetails.setRoomTypeName(addhoteldetails.get(j).getRoomTypeName());
                    hotelRoomsDetails.setRatePlanCode(addhoteldetails.get(j).getRatePlanCode());
                    hotelRoomsDetails.setBedTypeCode(null);
                    hotelRoomsDetails.setSmokingPreference(0);
                    hotelRoomsDetails.setSupplements(null);
                    price.setCurrencyCode(addhoteldetails.get(j).getCurrencyCode());
                    price.setRoomPrice(addhoteldetails.get(j).getRoomPrice());
                    price.setTax(addhoteldetails.get(j).getTax());
                    price.setExtraGuestCharge(addhoteldetails.get(j).getExtraGuestCharge());
                    price.setChildCharge(addhoteldetails.get(j).getChildCharge());
                    price.setOtherCharges(addhoteldetails.get(j).getOtherCharges());
                    Log.e("ther", "info" + addhoteldetails.get(j).getOtherCharges());
                    Log.e("getRoomPrice()", "info" + addhoteldetails.get(j).getRoomPrice());

                    price.setDiscount(addhoteldetails.get(j).getDiscount());
                    price.setPublishedPrice(addhoteldetails.get(j).getPublishedPrice());
                    price.setPublishedPriceRoundedOff(addhoteldetails.get(j).getPublishedPriceRoundedOff());
                    price.setOfferedPrice(addhoteldetails.get(j).getOfferedPrice());
                    price.setOfferedPriceRoundedOff(addhoteldetails.get(j).getOfferedPriceRoundedOff());
                    price.setAgentCommission(addhoteldetails.get(j).getAgentCommission());
                    price.setAgentMarkUp(addhoteldetails.get(j).getAgentMarkUp());
                    price.setTds(addhoteldetails.get(j).getTDS());
                    price.setServicetax(addhoteldetails.get(j).getServicetax());
                    hotelRoomsDetails.setPrice(price);
                    hotelroomarrayList.add(hotelRoomsDetails);
                }

                for (int j = 0; j < HotelDashBoardActivity.addroomreqlist.size(); j++) {
                    roomGuests = new RoomGuests();
                    roomGuests.setNoOfAdults(HotelDashBoardActivity.addroomreqlist.get(j).getNoOfAdults());
                    roomGuests.setNoOfChild(HotelDashBoardActivity.addroomreqlist.get(j).getNoOfChild());
                    if (HotelDashBoardActivity.addroomreqlist.get(j).getNoOfChild() == 0) {

                    } else {
                        roomGuests.setChildAge(HotelDashBoardActivity.addroomreqlist.get(j).getChildAge());

                    }


                    roomguestList.add(roomGuests);
                }
                hotelBlockRoomRequestmodel.setHotelRoomsDetails(hotelroomarrayList);
                hotelBlockRoomRequestmodel.setRoomrequestdeails(roomguestList);
                hotel_blockroom_boook(hotelBlockRoomRequestmodel);

            }
        });
    }

    public void get_HotelRoom(HashMap<String, String> param) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelGeRoomResponse> call = retroFitInterface.getroom(param);
        call.enqueue(new Callback<HotelGeRoomResponse>() {
            @Override
            public void onResponse(Call<HotelGeRoomResponse> call, Response<HotelGeRoomResponse> response) {
                progressDialogDismiss();
                HotelGeRoomResponse hotelGeRoomResponse = response.body();
                Log.e("hotel room", "onResponse: ");
                if (response.code() == 200) {
                    Log.e("hotel room", "onResponse: ");


                    if (hotelGeRoomResponse.getGetHotelRoomResult().getResponseStatus() == 1) {
                        if (hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails() != null) {
                            for (int i = 0; i < hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().size(); i++) {

                                if (gethotel_categoryid.equals(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getCategoryId())) {
                                    hotelRoomListResponse = new HotelRoomListResponse();
                                    Log.e("cate", "rt" + hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getCategoryId());
                                    Log.e("cate..sam", "rt" + gethotel_categoryid);
                                    hotelRoomListResponse.setRoomTypeCode(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomTypeCode());
                                    hotelRoomListResponse.setRoomIndex(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomIndex()));

                                    if (hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomDescription().equals("")) {
                                        hotelRoomListResponse.setRoomDescription("null");

                                    } else {
                                        String yourString = String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomDescription());
                                        String finaldata = Jsoup.parse(yourString).text();
                                        hotelRoomListResponse.setRoomDescription(finaldata);

                                    }

                                    hotelRoomListResponse.setRoomTypeName(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomTypeName());

                                    if (hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomTypeName().equals("")) {
                                        hotelRoomListResponse.setRatePlanName(null);

                                    } else {
                                        hotelRoomListResponse.setRatePlanName(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRoomTypeName());

                                    }

                                    hotelRoomListResponse.setRatePlanCode(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getRatePlanCode());
                                    hotelRoomListResponse.setAvailabilityType(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getAvailabilityType());
                                    hotelRoomListResponse.setCurrencyCode(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getCurrencyCode());
                                    hotelRoomListResponse.setRoomPrice(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getRoomPrice()));
                                    hotelRoomListResponse.setTax(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getTax()));
                                    hotelRoomListResponse.setExtraGuestCharge(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getExtraGuestCharge()));
                                    hotelRoomListResponse.setChildCharge(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getChildCharge()));
                                    hotelRoomListResponse.setOtherCharges(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getOtherCharges()));
                                    hotelRoomListResponse.setServiceTax(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getServiceTax()));
                                    hotelRoomListResponse.setDiscount(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getDiscount()));
                                    hotelRoomListResponse.setPublishedPriceRoundedOff(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getOfferedPriceRoundedOff()));
                                    hotelRoomListResponse.setOfferedPrice(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getOfferedPrice()));
                                    hotelRoomListResponse.setOfferedPriceRoundedOff(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getOfferedPriceRoundedOff()));
                                    hotelRoomListResponse.setAgentCommission(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getAgentCommission()));
                                    hotelRoomListResponse.setAgentMarkUp(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getAgentMarkUp()));
                                    hotelRoomListResponse.setTDS(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getTds()));
                                    hotelRoomListResponse.setHotel_room_price(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getPublishedPrice()));
                                    hotelRoomListResponse.setPublishedPrice(String.valueOf(hotelGeRoomResponse.getGetHotelRoomResult().getHotelRoomsDetails().get(i).getPrice().getPublishedPrice()));
                                    hotel_roomlist.add(hotelRoomListResponse);
                                } else {

                                }

                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            binding.recyclerViewHoteLRoomlist.setLayoutManager(linearLayoutManager);
                            hotelRoomListAdapter = new HotelRoomListAdapter(HotelRoomGetActivity.this, hotel_roomlist, listener);
                            binding.recyclerViewHoteLRoomlist.setAdapter(hotelRoomListAdapter);

                        }

                    } else {
                        Toast.makeText(HotelRoomGetActivity.this, "Api  Technical Response issue", Toast.LENGTH_SHORT).show();
                    }


                } else if (response.code() == 401) {
                    Toast.makeText(HotelRoomGetActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 500) {
                    Toast.makeText(HotelRoomGetActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    Toast.makeText(HotelRoomGetActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HotelGeRoomResponse> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: ");

            }
        });
    }

    public void hotel_blockroom_boook(HotelBlockRoomRequestmodel hotelBlockRoomRequestmodel) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelBlockResponseModel> responseBodyCall = retroFitInterface.hotel_blockroom(hotelBlockRoomRequestmodel);

        try {
            responseBodyCall.enqueue(new Callback<HotelBlockResponseModel>() {
                @Override
                public void onResponse(Call<HotelBlockResponseModel> call, Response<HotelBlockResponseModel> response) {
                    progressDialogDismiss();
                    Log.e("hotelblockroom", "booklick1");
                    HotelBlockResponseModel hotelBlockRoomResponsemodel = response.body();
                    if (response.code() == 200) {
                        if (hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorCode() == 0) {
                            if (hotelBlockRoomResponsemodel.getBlockRoomResult().getAvailabilityType().equals("Confirm")) {

                                if (hotelBlockRoomResponsemodel.getBlockRoomResult().isPriceChanged() == false) {
                                    Error error1 = new Error();
                                    if (hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorCode() == 0) {

                                        SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = shared.edit();
                                        editor.putString("passengerid", hotelBlockRoomResponsemodel.getBlockRoomResult().getPassengerId());
                                        editor.putString("norms", hotelBlockRoomResponsemodel.getBlockRoomResult().getHotelNorms());
                                        editor.putString("policy", hotelBlockRoomResponsemodel.getBlockRoomResult().getHotelPolicyDetail());
                                        editor.apply();
                                        Intent intent = new Intent(HotelRoomGetActivity.this, AddHotelPassengerMenberActivity.class);
                                        startActivity(intent);
                                    } else if (hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorCode() == 3) {
                                        Toast.makeText(HotelRoomGetActivity.this, hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(HotelRoomGetActivity.this, hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    Toast.makeText(HotelRoomGetActivity.this, "Price Change", Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                Toast.makeText(HotelRoomGetActivity.this, "UnAvilable", Toast.LENGTH_SHORT).show();

                            }
                        } else if (hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorCode() == 2) {
                            Toast.makeText(HotelRoomGetActivity.this, hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                        } else if (hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorCode() == 3) {
                            Toast.makeText(HotelRoomGetActivity.this, hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(HotelRoomGetActivity.this, hotelBlockRoomResponsemodel.getBlockRoomResult().getError().getErrorMessage(), Toast.LENGTH_SHORT).show();

                        }


                    } else {
                        Toast.makeText(HotelRoomGetActivity.this, "server issue", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<HotelBlockResponseModel> call, Throwable t) {
                    progressDialogDismiss();
                    Log.e("reeorr+", t.getMessage());
                    Log.e("reeorr+", t.toString());
                    Log.e("reeorr+", call.toString());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}