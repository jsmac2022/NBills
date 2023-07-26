package com.nictbills.app.activities.tbo.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.bus.AddBusMemberActivity;
import com.nictbills.app.activities.tbo.bus.BusProcessedToBookingActivity;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelPassengerAdultAdapter;
import com.nictbills.app.activities.tbo.hotel.adapter.HotelPassengerChildAdapter;
import com.nictbills.app.activities.tbo.hotel.model.addhotelmemberlist.AddHotelPassengerModel;
import com.nictbills.app.activities.tbo.hotel.model.addpassenger.HotelAddPassengerResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.RoomGuests;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityAddBusMemberDetailsBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHotelPassengerMenberActivity extends BaseActivity implements HotelPassengerAdultAdapter.OnItemClickListener, HotelPassengerChildAdapter.OnItemClickListener {
    SharedPreferences shared, sharedPreferences;
    String mobileNumber, getpanno, childgendertype, gendertype, childgetgendertype, getgendertype, gettrace_id, gettokenhotel_id, getpassenger_id, getpolicy, getnorms;
    int count = 0, childcount = 0;
    RecyclerView reclerviewadult, reclerviewchild;
    ImageView imageViewback;
    AddHotelPassengerModel addHotelPassengerModel;
    ArrayList<AddHotelPassengerModel> addpassengerlisthotel = new ArrayList<AddHotelPassengerModel>();
    ArrayList<AddHotelPassengerModel> addpassengerlisthotel_child = new ArrayList<AddHotelPassengerModel>();
    HotelPassengerAdultAdapter hotelPassengerAdultAdapter;
    HashMap<String, String> addpassengerparam;
    HotelPassengerChildAdapter hotelPassengerChildAdapter;
    String MobilePattern = "[0-9]{10}";
    final String pan_pattern = "(([A-Za-z]{5})([0-9]{4})([a-zA-Z]))";
    int finalcountclick = 0;
    int finalcountclick_child = 0;
    int getage = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel_passenger_menber);
        imageViewback = findViewById(R.id.backArrow_IMG);
        reclerviewadult = findViewById(R.id.rview_passenger_Adult);
        reclerviewchild = findViewById(R.id.rview_passenger_child);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        gettrace_id = shared.getString("TraceId", "");
        gettokenhotel_id = shared.getString("Tokenid_Hotel", "");
        getpassenger_id = shared.getString("passengerid", "");
        getnorms = shared.getString("norms", "");
        getpolicy = shared.getString("policy", "");
        count = Integer.parseInt(shared.getString("Adultno", ""));
        childcount = Integer.parseInt(shared.getString("childno", ""));
        Log.e("aduk","anuu"+count);
        Log.e("child","fff"+childcount);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        for (int i = 1; i <= count; i++) {
            addHotelPassengerModel = new AddHotelPassengerModel();
            addHotelPassengerModel.setPassengernumberall(i + "");
            addHotelPassengerModel.setTitle("");
            addHotelPassengerModel.setFname("");
            addHotelPassengerModel.setLname("");
            addHotelPassengerModel.setMiddlename("");
            addHotelPassengerModel.setAge("");
            addHotelPassengerModel.setGender("");
            addHotelPassengerModel.setMobile("");
            addHotelPassengerModel.setEmail("");
            addHotelPassengerModel.setLeadPassenger("");
            addHotelPassengerModel.setPaxtype("");
            addHotelPassengerModel.setPancard("");
            addHotelPassengerModel.setPassportNo("");
            addHotelPassengerModel.setPassportDate("");
            addHotelPassengerModel.setPassportExpDate("");
            addpassengerlisthotel.add(addHotelPassengerModel);
        }

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        reclerviewadult.setLayoutManager(linearLayoutManager1);
        hotelPassengerAdultAdapter = new HotelPassengerAdultAdapter(AddHotelPassengerMenberActivity.this, addpassengerlisthotel, AddHotelPassengerMenberActivity.this);
        reclerviewadult.setAdapter(hotelPassengerAdultAdapter);


        //child
        if ((childcount > 0)) {
            for (int i = 1; i <= childcount; i++) {

                addHotelPassengerModel = new AddHotelPassengerModel();
                addHotelPassengerModel.setPassengernumberall(i + "");
                addHotelPassengerModel.setTitle("");
                addHotelPassengerModel.setFname("");
                addHotelPassengerModel.setLname("");
                addHotelPassengerModel.setMiddlename("");
                addHotelPassengerModel.setAge("");
                addHotelPassengerModel.setGender("");
                addHotelPassengerModel.setMobile("");
                addHotelPassengerModel.setEmail("");
                addHotelPassengerModel.setLeadPassenger("");
                addHotelPassengerModel.setPaxtype("");
                addHotelPassengerModel.setPancard("");
                addHotelPassengerModel.setPassportNo("");
                addHotelPassengerModel.setPassportDate("");
                addHotelPassengerModel.setPassportExpDate("");
                addpassengerlisthotel_child.add(addHotelPassengerModel);
            }

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            reclerviewchild.setLayoutManager(linearLayoutManager);
            hotelPassengerChildAdapter = new HotelPassengerChildAdapter(AddHotelPassengerMenberActivity.this, addpassengerlisthotel_child, AddHotelPassengerMenberActivity.this);
            reclerviewchild.setAdapter(hotelPassengerChildAdapter);

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
    public void onItemClick(String fname, String lname, String mobile, String email, String age, String pancard, String gender, int position) {

        if (fname.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();

        } else if (lname.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your LastName", Toast.LENGTH_SHORT).show();

        } else if (mobile.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your Mobile NO", Toast.LENGTH_SHORT).show();

        } else if (email.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();

        } else if (age.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();
        } else if (pancard.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your PANNO.", Toast.LENGTH_SHORT).show();
        } else {

            if (mobile.trim().matches(MobilePattern)) {

                if (isValidEmailId(email))
                {

                    Pattern r = Pattern.compile(pan_pattern);
                    if (!regex_matcher(r, pancard)) {
                        Toast.makeText(AddHotelPassengerMenberActivity.this, "Invalid PAN number", Toast.LENGTH_SHORT).show();

                    } else {

                        addpassengerparam = new HashMap<>();
                        addpassengerparam.put("title", "Mr.");
                        addpassengerparam.put("firstName", fname);
                        addpassengerparam.put("middleName", "");
                        addpassengerparam.put("lastName", lname);
                        addpassengerparam.put("leadPassenger", "1");
                        addpassengerparam.put("phoneno", mobile);
                        addpassengerparam.put("email", email);
                        addpassengerparam.put("paxType", String.valueOf(1));
                        addpassengerparam.put("age", age);
                        addpassengerparam.put("passportNo", "");
                        addpassengerparam.put("passportDate", "");
                        addpassengerparam.put("passportExpDate", "");
                        addpassengerparam.put("PanNo", pancard);
                        getpanno = pancard;
                        addpassengerparam.put("blockId", getpassenger_id);
                        addpasenger(addpassengerparam, "adult", position);
                        finalcountclick = finalcountclick + 1;



                    }

                } else {
                    Toast.makeText(AddHotelPassengerMenberActivity.this, "Incorrect EmailId", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(AddHotelPassengerMenberActivity.this, "Incorrect Mobile NO digit", Toast.LENGTH_SHORT).show();

            }

        }


    }

    public void addpasenger(HashMap<String, String> addpassengerparam, String passengertype, int positionadult) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelAddPassengerResponseModel> call = retroFitInterface.addpassenger_hotel(addpassengerparam);
        call.enqueue(new Callback<HotelAddPassengerResponseModel>() {
            @Override
            public void onResponse(Call<HotelAddPassengerResponseModel> call, Response<HotelAddPassengerResponseModel> response) {
                progressDialogDismiss();
                HotelAddPassengerResponseModel addPassengerResponsemodel = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code() == 200) {
                    Log.e("in hotelsuccess", "onResponse" + addPassengerResponsemodel.getResponseStatus().trim());
                    Log.e("in msg  hoel succc", "onResponse" + addPassengerResponsemodel.getMessages().trim());
                    Toast.makeText(AddHotelPassengerMenberActivity.this, "Added " + addPassengerResponsemodel.getResponseStatus(), Toast.LENGTH_SHORT).show();
                    addpassengerlisthotel.remove(positionadult);
                    hotelPassengerAdultAdapter.notifyDataSetChanged();

                    if (finalcountclick == count) {

                        if (childcount == 0) {


                            Intent i = new Intent(AddHotelPassengerMenberActivity.this, HotelProcessdToBokkingctivity.class);
                            AddHotelPassengerMenberActivity.this.finish();
                            startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(AddHotelPassengerMenberActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HotelAddPassengerResponseModel> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddHotelPassengerMenberActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick_child(String child_fname, String child_lname, int position) {


        if (child_fname.length() == 0) {
            Toast.makeText(AddHotelPassengerMenberActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();

        } else if (child_lname.length() == 0) {

        } else {

            for (int j = 0; j < HotelDashBoardActivity.addroomreqlist.size(); j++) {

                Log.e("sam", "jathotel" + HotelDashBoardActivity.addroomreqlist.get(j).getChildAge());
                Log.e("sastrAr4mage", "jathotel" + HotelDashBoardActivity.addroomreqlist.get(j).getChildAge());
                Integer[][] strAr4 = {HotelDashBoardActivity.addroomreqlist.get(j).getChildAge()};
                Log.e("strAr4", "jathotel" + strAr4.toString());

                for (Integer str : HotelDashBoardActivity.addroomreqlist.get(j).getChildAge()) {
                    Log.e("samage", "ageget" + str);
                    getage = str;
                }

            }
            addpassengerparam = new HashMap<>();
            addpassengerparam.put("title", "Mr.");
            addpassengerparam.put("firstName", child_fname);
            addpassengerparam.put("middleName", "");
            addpassengerparam.put("lastName", child_lname);
            addpassengerparam.put("leadPassenger", "0");
            addpassengerparam.put("phoneno", "");
            addpassengerparam.put("email", "");
            addpassengerparam.put("paxType", String.valueOf(2));
            addpassengerparam.put("age", String.valueOf(getage));
            addpassengerparam.put("passportNo", "");
            addpassengerparam.put("passportDate", "");
            addpassengerparam.put("passportExpDate", "");
            addpassengerparam.put("PanNo", getpanno);
            addpassengerparam.put("blockId", getpassenger_id);
            addpasenger_child(addpassengerparam, "child", position);
            finalcountclick_child = finalcountclick_child + 1;

        }


    }

    public void addpasenger_child(HashMap<String, String> addpassengerparam, String passengertype, int positionchild) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.TBO_BASE_URL_HOTEL).create(RetrofitInterface.class);
        Call<HotelAddPassengerResponseModel> call = retroFitInterface.addpassenger_hotel(addpassengerparam);
        call.enqueue(new Callback<HotelAddPassengerResponseModel>() {
            @Override
            public void onResponse(Call<HotelAddPassengerResponseModel> call, Response<HotelAddPassengerResponseModel> response) {
                progressDialogDismiss();
                HotelAddPassengerResponseModel addPassengerResponsemodel = response.body();
                Log.e("nict in add passenger", "onResponse");
                if (response.code() == 200) {
                    Toast.makeText(AddHotelPassengerMenberActivity.this, "Added " + addPassengerResponsemodel.getResponseStatus(), Toast.LENGTH_SHORT).show();
                    addpassengerlisthotel_child.remove(positionchild);
                    hotelPassengerChildAdapter.notifyDataSetChanged();

                    if (finalcountclick == count) {

                        if (childcount>0) {

                            if (finalcountclick_child == childcount) {

                                Intent i = new Intent(AddHotelPassengerMenberActivity.this, HotelProcessdToBokkingctivity.class);
                                AddHotelPassengerMenberActivity.this.finish();
                                startActivity(i);
                            }
                        }


                    }


                } else {
                    Toast.makeText(AddHotelPassengerMenberActivity.this, "Failur Response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HotelAddPassengerResponseModel> call, Throwable t) {
                progressDialogDismiss();
//                Toast.makeText(FlightDashActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddHotelPassengerMenberActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                Log.e("nictfailur", "onResponse: " + t.getMessage());
            }
        });
    }

    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private boolean regex_matcher(Pattern pattern, String string) {
        Matcher m = pattern.matcher(string);
        return m.find() && (m.group(0) != null);
    }

}