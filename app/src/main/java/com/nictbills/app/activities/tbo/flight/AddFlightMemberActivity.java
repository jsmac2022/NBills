package com.nictbills.app.activities.tbo.flight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.flight.adapter.FlightAdultMemberAdapter;
import com.nictbills.app.activities.tbo.flight.adapter.FlightChildMemberAdapter;
import com.nictbills.app.activities.tbo.flight.adapter.FlightInfantMemberAdapter;
import com.nictbills.app.activities.tbo.flight.model.AddFlightPassengermodel.FlightPassengerMemberAdd;
import com.nictbills.app.activities.tbo.flight.model.addpasengermodel.AddPassengerResponsemodel;
import com.nictbills.app.activities.tbo.hotel.AddHotelPassengerMenberActivity;
import com.nictbills.app.activities.tbo.hotel.HotelProcessdToBokkingctivity;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelPassengerAdultAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFlightMemberActivity extends BaseActivity implements FlightAdultMemberAdapter.OnItemClickListener, FlightChildMemberAdapter.OnItemClickListener, FlightInfantMemberAdapter.OnItemClickListener {
    ImageView imageViewback;
    RecyclerView reclerviewadult, reclerviewchild, reclerviewinfant;
    SharedPreferences shared;
    String genderinfanttype, genderchildtype, gendertype, getpaaasenger_id, getllctype, gettype, gettoken, gettrace, getresultindex, getchild_count, getadult_count;
    int count = 0, countchild = 0, countinfant = 0;
    FlightPassengerMemberAdd flightPassengerMemberAdd;
    ArrayList<FlightPassengerMemberAdd> adultlist = new ArrayList<FlightPassengerMemberAdd>();
    ArrayList<FlightPassengerMemberAdd> childlist = new ArrayList<FlightPassengerMemberAdd>();
    ArrayList<FlightPassengerMemberAdd> infantlist = new ArrayList<FlightPassengerMemberAdd>();
    String MobilePattern = "[0-9]{10}";
    HashMap<String, String> addpassengerparam;
    int finalcountclick = 0;
    int finalcountclick_child = 0;
    int finalcountclick_infant = 0;
    FlightAdultMemberAdapter  flightAdultMemberAdapter;
    FlightChildMemberAdapter  flightChildMemberAdapter;
    FlightInfantMemberAdapter  flightInfantMemberAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight_member);
        imageViewback = findViewById(R.id.backArrow_IMG);
        reclerviewadult = findViewById(R.id.rview_flightpassenger_Adult);
        reclerviewchild = findViewById(R.id.rview_flightpassenger_child);
        reclerviewinfant = findViewById(R.id.rview_flightpassenger_infant);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettoken = shared.getString("Tokenid", "");
        gettrace = shared.getString("TraceId", "");
        getresultindex = shared.getString("ResultIndex", "");
        getadult_count = shared.getString("Adultno", "");
        getchild_count = shared.getString("Childno", "");
        gettype = shared.getString("type", "");
        getllctype = shared.getString("LLCType", "");
        getpaaasenger_id = shared.getString("passengerid", "");
        count = Integer.parseInt(shared.getString("Adultno", ""));
        countchild = Integer.parseInt(shared.getString("Childno", ""));
        countinfant = Integer.parseInt(shared.getString("Infant", ""));
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);

        for (int i = 1; i <= count; i++) {
            FlightPassengerMemberAdd flightPassengerMemberAdd = new FlightPassengerMemberAdd();
            flightPassengerMemberAdd.setPassengernumberall(i + "");
            flightPassengerMemberAdd.setFullname("");
            flightPassengerMemberAdd.setLastname("");
            flightPassengerMemberAdd.setMobile("");
            flightPassengerMemberAdd.setEmail("");
            flightPassengerMemberAdd.setDob("");
            flightPassengerMemberAdd.setGender("");
            adultlist.add(flightPassengerMemberAdd);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        reclerviewadult.setLayoutManager(linearLayoutManager);
         flightAdultMemberAdapter = new FlightAdultMemberAdapter(AddFlightMemberActivity.this, adultlist, this);
        reclerviewadult.setAdapter(flightAdultMemberAdapter);

        if (countchild > 0) {
            for (int i = 1; i <= countchild; i++) {
                FlightPassengerMemberAdd flightPassengerMemberAdd = new FlightPassengerMemberAdd();
                flightPassengerMemberAdd.setPassengernumberall(i + "");
                flightPassengerMemberAdd.setFullname("");
                flightPassengerMemberAdd.setLastname("");
                flightPassengerMemberAdd.setDob("");
                childlist.add(flightPassengerMemberAdd);
            }
            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            reclerviewchild.setLayoutManager(linearLayoutManager1);
             flightChildMemberAdapter = new FlightChildMemberAdapter(AddFlightMemberActivity.this, childlist, this);
            reclerviewchild.setAdapter(flightChildMemberAdapter);

        }
        if (countinfant > 0) {
            for (int i = 1; i <= countinfant; i++) {
                FlightPassengerMemberAdd flightPassengerMemberAdd = new FlightPassengerMemberAdd();
                flightPassengerMemberAdd.setPassengernumberall(i + "");
                flightPassengerMemberAdd.setFullname("");
                flightPassengerMemberAdd.setLastname("");
                flightPassengerMemberAdd.setDob("");
                infantlist.add(flightPassengerMemberAdd);
            }
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            reclerviewinfant.setLayoutManager(linearLayoutManager2);
             flightInfantMemberAdapter = new FlightInfantMemberAdapter(AddFlightMemberActivity.this, infantlist, this);
            reclerviewinfant.setAdapter(flightInfantMemberAdapter);

        }
        click();
    }

    public void click() {
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onItemClick(String fname, String lname, String mobile, String email, String dob, String gendertype ,int position) {

        if (fname.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();

        } else if (lname.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your LastName", Toast.LENGTH_SHORT).show();

        } else if (mobile.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Mobile NO", Toast.LENGTH_SHORT).show();

        } else if (email.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();

        } else if (dob.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
        } else {
            if (mobile.trim().matches(MobilePattern)) {

                if (isValidEmailId(email)) {

                    addpassengerparam = new HashMap<>();
                    addpassengerparam.put("full_name", fname);
                    addpassengerparam.put("last_name", lname);
                    addpassengerparam.put("passenger_age", dob);
                    addpassengerparam.put("phone",mobile);
                    addpassengerparam.put("email_id", email);
                    addpassengerparam.put("gender", gendertype);
                    addpassengerparam.put("flight_id", getpaaasenger_id);
                    addpassengerparam.put("type", "1");
                    finalcountclick = finalcountclick + 1;

                    addpasenger(addpassengerparam, "adult" ,position);

                } else {
                    Toast.makeText(AddFlightMemberActivity.this, "Incorrect EmailId", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(AddFlightMemberActivity.this, "Incorrect Mobile NO digit", Toast.LENGTH_SHORT).show();

            }


        }

    }

    @Override
    public void onItemClick_child(String fname, String lname, String dob, String getgendertype ,int position) {

        if (fname.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();

        } else if (lname.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your LastName", Toast.LENGTH_SHORT).show();

        } else if (dob.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
        } else {

            addpassengerparam = new HashMap<>();
            addpassengerparam.put("full_name",fname);
            addpassengerparam.put("last_name", lname);
            addpassengerparam.put("passenger_age", dob);
            addpassengerparam.put("phone", "");
            addpassengerparam.put("email_id", "");
            addpassengerparam.put("gender", getgendertype);
            addpassengerparam.put("flight_id", getpaaasenger_id);
            addpassengerparam.put("type", "2");
            finalcountclick_child = finalcountclick_child + 1;
            addpasenger(addpassengerparam, "child" ,position);

        }


    }

    @Override
    public void onItemClick_infant(String fname, String lname, String dob, String getgendertype ,int position) {

        if (fname.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();

        } else if (lname.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your LastName", Toast.LENGTH_SHORT).show();

        } else if (dob.length() == 0) {
            Toast.makeText(AddFlightMemberActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
        } else {

            addpassengerparam = new HashMap<>();
            addpassengerparam.put("full_name", fname);
            addpassengerparam.put("last_name", lname);
            addpassengerparam.put("passenger_age", dob);
            addpassengerparam.put("phone", "");
            addpassengerparam.put("email_id", "");
            addpassengerparam.put("gender", getgendertype);
            addpassengerparam.put("flight_id", getpaaasenger_id);
            addpassengerparam.put("type", "3");
            finalcountclick_infant = finalcountclick_infant + 1;

            addpasenger(addpassengerparam, "infant" ,position);

        }
    }

    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }


    public void addpasenger(HashMap<String, String> addpassengerparam, String passengertype ,int getposition) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL).create(RetrofitInterface.class);
        Call<AddPassengerResponsemodel> call = retroFitInterface.addpassenger(addpassengerparam);
        call.enqueue(new Callback<AddPassengerResponsemodel>() {
            @Override
            public void onResponse(Call<AddPassengerResponsemodel> call, Response<AddPassengerResponsemodel> response) {
                progressDialogDismiss();
                AddPassengerResponsemodel addPassengerResponsemodel = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code() == 200) {
                    Log.e("in success", "onResponse" + addPassengerResponsemodel.getResponseStatus().trim());
                    Log.e("in msg succc", "onResponse" + addPassengerResponsemodel.getMessages().trim());

                    Toast.makeText(AddFlightMemberActivity.this, "Added " + addPassengerResponsemodel.getResponseStatus(), Toast.LENGTH_SHORT).show();

                    if (passengertype.equals("adult")) {
                        adultlist.remove(getposition);
                        flightAdultMemberAdapter.notifyDataSetChanged();
                        sendintent();


                    } else if (passengertype.equals("child")) {
                        childlist.remove(getposition);
                        flightChildMemberAdapter.notifyDataSetChanged();

                        sendintent();



                    } else if (passengertype.equals("infant")){
                        infantlist.remove(getposition);
                        flightInfantMemberAdapter.notifyDataSetChanged();

                        sendintent();

                    }


                } else {
                    Toast.makeText(AddFlightMemberActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddPassengerResponsemodel> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddFlightMemberActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });
    }

    public  void  sendintent()
    {
        if(finalcountclick==count)
        {

            if(countchild==0&&countinfant==0)
            {
                if (gettype.equals("seat"))
                {

                    Intent i = new Intent(AddFlightMemberActivity.this, SeatViewActivity.class);
                    AddFlightMemberActivity.this.finish();
                    startActivity(i);

                } else
                {
                    SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("price", "");
                    editor.apply();

Log.e("1adult","++");

                    Intent i = new Intent(AddFlightMemberActivity.this, FlightTicketActivity.class);
                    AddFlightMemberActivity.this.finish();
                    startActivity(i);

                }
            }
            else
            {

                if(countchild>0)
                {
                    if(countinfant==0)
                    {
                        if(finalcountclick_child==countchild)
                        {



                            if (gettype.equals("seat"))
                            {


                                Intent i = new Intent(AddFlightMemberActivity.this, SeatViewActivity.class);
                                AddFlightMemberActivity.this.finish();
                                startActivity(i);


                            } else
                            {
                                SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("price", "");
                                editor.apply();
                                Log.e("2adult","++");

                                Intent i = new Intent(AddFlightMemberActivity.this, FlightTicketActivity.class);
                                AddFlightMemberActivity.this.finish();
                                startActivity(i);
                            }
                        }
                    }
                    else if(countinfant>0)
                    {
                        if(finalcountclick_child==countchild)
                        {

                            if(finalcountclick_infant==countinfant)
                            {
                                if (gettype.equals("seat"))
                                {


                                    Intent i = new Intent(AddFlightMemberActivity.this, SeatViewActivity.class);
                                    AddFlightMemberActivity.this.finish();
                                    startActivity(i);

                                } else
                                {
                                    SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = shared.edit();
                                    editor.putString("price", "");
                                    editor.apply();

                                    Log.e("3adult","++");

                                    Intent i = new Intent(AddFlightMemberActivity.this, FlightTicketActivity.class);
                                    AddFlightMemberActivity.this.finish();
                                    startActivity(i);

                                }
                            }


                        }
                    }


                }
                else if(countinfant>0)
                {

                    if(countchild==0)
                    {
                        if(finalcountclick_infant==countinfant)
                        {
                            if (gettype.equals("seat"))
                            {


                                Intent i = new Intent(AddFlightMemberActivity.this, SeatViewActivity.class);
                                AddFlightMemberActivity.this.finish();
                                startActivity(i);

                            } else
                            {
                                SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("price", "");
                                editor.apply();

                                Log.e("4adult","++");

                                Intent i = new Intent(AddFlightMemberActivity.this, FlightTicketActivity.class);
                                AddFlightMemberActivity.this.finish();
                                startActivity(i);

                            }
                        }
                    }
                    else if(countchild>0)
                    {
                        if(finalcountclick_infant==countinfant)
                        {
                            if (gettype.equals("seat"))
                            {

                                Intent i = new Intent(AddFlightMemberActivity.this, SeatViewActivity.class);
                                AddFlightMemberActivity.this.finish();
                                startActivity(i);

                            } else
                            {
                                SharedPreferences shared = getSharedPreferences("nict", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("price", "");
                                editor.apply();
                                Log.e("5adult","++");

                                Intent i = new Intent(AddFlightMemberActivity.this, FlightTicketActivity.class);
                                AddFlightMemberActivity.this.finish();
                                startActivity(i);
                            }
                        }
                    }

                }
            }

        }

    }

}