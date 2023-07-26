package com.nictbills.app.activities.tbo.flight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.flight.adapter.GetPassengerAdapter;
import com.nictbills.app.activities.tbo.flight.model.addpasengermodel.AddPassengerResponsemodel;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.AddPassenger;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.GetPassengerResponsemodel;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.ApiClients;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFlightTicketBinding;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightTicketActivity extends BaseActivity {
    ActivityFlightTicketBinding binding;
    SharedPreferences shared, sharedPreferences;
    boolean termchecked = false;
    boolean paychecked = false;
    JSONObject jsonobjfare;
    String mobileNumber, getcountry, getusermobile, getuemail, getaddress, gettoken, gettrace, getuname, getgender, getlname , getagency_id, getresultindex, getllctype, gettype;
    String getmobile, getpusher, getcity, getemail;
    HashMap<String, String> getpassenger;
    ArrayList<AddPassenger>  getpasengerlist = new ArrayList();
    GetPassengerAdapter getPassengerAdapter;
    int getcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flight_ticket);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight_ticket);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        String getpusher = shared.getString("pusher", "");
        getcountry = shared.getString("Country", "");
        String getflihtno = shared.getString("flightno", "");
        String getorigincode = shared.getString("origincode", "");
        String getdesticode = shared.getString("destinationcode", "");
        String getflightname = shared.getString("flightname", "");
        String getflightdeptdatetime = shared.getString("fulldeptdatetime", "");
        String getflightarrdatetime = shared.getString("fullarrdatetime", "");
        String getseatno = shared.getString("seatno", "");
        String get_finalprice = shared.getString("price", "");
        gettoken = shared.getString("Tokenid", "");
        gettrace = shared.getString("TraceId", "");
        getagency_id = shared.getString("Agencyid", "");
        getresultindex = shared.getString("ResultIndex", "");
        getllctype = shared.getString("LLCType", "");
        gettype = shared.getString("type", "");
        getcount = shared.getInt("seatcount", 1);
//        getuname = shared.getString("uname", "");
//        getlname = shared.getString("userlname", "");
//        getgender = String.valueOf(shared.getInt("gendertype", 1));
//        getaddress = shared.getString("useradress", "");
//        getuemail = shared.getString("useremail", "");
//        getusermobile = shared.getString("usermobile", "");

        String json = shared.getString("FarequoteResponse", "");
        Log.e("sam", "222" + getllctype);
        binding.btncontinue.setVisibility(View.VISIBLE);
        binding.flightno.setText(getflihtno);
        binding.flightname.setText(getflightname);
        binding.origincode.setText(getorigincode);
        binding.destcode.setText(getdesticode);
        binding.deptdate.setText(getflightdeptdatetime);
        binding.arrdate.setText(getflightarrdatetime);
        binding.uname.setText(getuname);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");

        binding.countryname.setText(getcountry);
        binding.tvpusher.setText(getpusher);
        if (gettype.equals("seat")) {
            if (get_finalprice.equalsIgnoreCase("")) {
                binding.layPrice.setVisibility(View.GONE);
            } else {

                binding.tvPriceFlight.setText("Rs." + get_finalprice);

            }
            if (getseatno.equalsIgnoreCase("")) {
                binding.laySeat.setVisibility(View.GONE);
            } else {
                binding.tvseatPref.setText(getorigincode + "-" + getdesticode + ":" + getseatno);
            }
        } else {
            binding.layPrice.setVisibility(View.GONE);
            binding.laySeat.setVisibility(View.GONE);
        }

        onclick();
    }

    public void onclick() {

        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.checkTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    termchecked = true;
                } else {
                    termchecked = false;
                }

            }
        });
        binding.checkPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                    binding.radioGroupPayment.setVisibility(View.VISIBLE);
                } else {
                    binding.radioGroupPayment.setVisibility(View.GONE);
                }
            }
        });
        binding.btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (termchecked == false) {
                    Toast.makeText(FlightTicketActivity.this, "plz check terms and condition", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(FlightTicketActivity.this, FlightPaymentActivity.class);
                    startActivity(intent);
//                    airbook_booking(airTicketRequst);
                }
            }
        });

        getpassenger = new HashMap<>();
        getpassenger.put("mobile_no", mobileNumber);
        getpassenger.put("resultIndex", getresultindex);
        getpassenger.put("traceId", gettrace);
        getpassenger(getpassenger);
    }


    public void getpassenger(HashMap<String, String> getpassenger) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<GetPassengerResponsemodel> call = retroFitInterface.getpassenger(getpassenger);
        call.enqueue(new Callback<GetPassengerResponsemodel>() {
            @Override
            public void onResponse(Call<GetPassengerResponsemodel> call, Response<GetPassengerResponsemodel> response) {
                progressDialogDismiss();
                GetPassengerResponsemodel getPassengerResponsemodel = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code()==200) {

                    if (getPassengerResponsemodel.getResponseCode()==200) {

                        if (getPassengerResponsemodel.getData().getAddPassenger().size() > 0) {
                            for (int j = 0; j < getPassengerResponsemodel.getData().getAddPassenger().size(); j++) {
                                AddPassenger addPassenger= new AddPassenger();
                                addPassenger.setFullName(getPassengerResponsemodel.getData().getAddPassenger().get(j).getFullName());
                                addPassenger.setEmailId(getPassengerResponsemodel.getData().getAddPassenger().get(j).getEmailId());
                                addPassenger.setGender(getPassengerResponsemodel.getData().getAddPassenger().get(j).getGender());
                                getpasengerlist.add(addPassenger);
                            }
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            binding.recyclerViewGetpass.setLayoutManager(linearLayoutManager);
                            GetPassengerAdapter getPassengerAdapter = new GetPassengerAdapter(FlightTicketActivity.this, getpasengerlist);
                            binding.recyclerViewGetpass.setAdapter(getPassengerAdapter);
                        }

                    } else if (getPassengerResponsemodel.getResponseCode() == 404) {

                    }


                } else {

                    Toast.makeText(FlightTicketActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GetPassengerResponsemodel> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(FlightTicketActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());


            }
        });
    }


}