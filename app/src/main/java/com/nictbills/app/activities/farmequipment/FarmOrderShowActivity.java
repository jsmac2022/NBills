package com.nictbills.app.activities.farmequipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.nictbills.app.activities.farmequipment.adapter.FarmEquipmentListAdapter;
import com.nictbills.app.activities.farmequipment.model.bookingconfirm.BookingConfirmationResponseModel;
import com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel.FarmEquipmentListResponseModel;
import com.nictbills.app.activities.farmequipment.model.ordercreate.OrderCreateResponseModel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFarmOrderShowBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmOrderShowActivity extends BaseActivity {
    ActivityFarmOrderShowBinding binding;
    SharedPreferences shared;
    String getorder, gettime, getamount, getproductid, getitle, getuname, getulname, getumobile, getuaddress, getucity;
    HashMap<String, String> param;
    HashMap<String, String> confirmparam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_farm_order_show);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_order_show);
        shared = getSharedPreferences("nict", MODE_PRIVATE);
        getitle = shared.getString("Title", "");
        gettime = shared.getString("Timeselect", "");

        getuname = shared.getString("Uname", "");
        getulname = shared.getString("Ulastname", "");
        getumobile = shared.getString("Umobile", "");
        getucity = shared.getString("Ucity", "");
        getuaddress = shared.getString("Uaddress", "");
        getamount = shared.getString("amount", "");
        getproductid = shared.getString("productid", "");

        param = new HashMap<>();
        param.put("userid",getumobile);
        param.put("product_id",getproductid);

        click();
    }

    public void click() {

        createorder(param);
        binding.tvFarmname.setText(getitle);
        binding.tvTime.setText(gettime);
        binding.tvAmount.setText("Rs." + getamount);
        binding.totalTvprice.setText("Rs." + getamount);
        binding.tvFullname.setText(getuname + "  " + getulname);
        binding.tvFulladdress.setText(getucity + "," + getuaddress + " " + "भारत (मध्य प्रदेश)");
        binding.tvMOBILE.setText("+91" + getumobile);

        binding.addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmparam = new HashMap<>();
                confirmparam.put("userid",getumobile);
                confirmparam.put("order_no",getorder);
                confirmparam.put("rental_time",gettime);
                confirmbooking(confirmparam);

            }
        });

    }

    public void createorder(HashMap<String, String> param) {

        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.FarmEquipment).create(RetrofitInterface.class);
        Call<OrderCreateResponseModel> call = retroFitInterface.getorder(param);
        call.enqueue(new Callback<OrderCreateResponseModel>() {
            @Override
            public void onResponse(Call<OrderCreateResponseModel> call, Response<OrderCreateResponseModel> response) {
                progressDialogDismiss();
                OrderCreateResponseModel orderCreateResponseModels = response.body();
                if (response.code() == 200) {
                    Log.e("getOrderNo", "getOrderNo" + orderCreateResponseModels.getOrderNo());
                    getorder = String.valueOf(orderCreateResponseModels.getOrderNo());

                } else if (response.code() == 400) {
                    Toast.makeText(FarmOrderShowActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FarmOrderShowActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<OrderCreateResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: " + t.toString());

            }
        });

    }

    public void confirmbooking(HashMap<String, String> confirmparam) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.FarmEquipment).create(RetrofitInterface.class);
        Call<BookingConfirmationResponseModel> call = retroFitInterface.bookingconfm(confirmparam);
        call.enqueue(new Callback<BookingConfirmationResponseModel>() {
            @Override
            public void onResponse(Call<BookingConfirmationResponseModel> call, Response<BookingConfirmationResponseModel> response) {
                progressDialogDismiss();
                BookingConfirmationResponseModel bookingConfirmationResponseModel = response.body();
                if (response.code() == 200) {

                    Log.e("..","sarveshbooking"+bookingConfirmationResponseModel.getBookingId());

                    shared = getSharedPreferences("nict", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(bookingConfirmationResponseModel); // myObject - instance of MyObject
                    editor.putString("FarmBooking", json);
                    editor.apply();

                    Intent intent = new Intent(FarmOrderShowActivity.this, FarmBookingConfirmatonActivity.class);
                    startActivity(intent);

                } else if (response.code() == 400) {
                    Toast.makeText(FarmOrderShowActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FarmOrderShowActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<BookingConfirmationResponseModel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: " + t.toString());

            }
        });

    }
}