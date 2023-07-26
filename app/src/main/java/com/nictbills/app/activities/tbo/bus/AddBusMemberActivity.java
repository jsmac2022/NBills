package com.nictbills.app.activities.tbo.bus;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.tbo.bus.adapter.BusPassengerAddList;
import com.nictbills.app.activities.tbo.bus.model.addbuspassengermodel.AddPassenger;
import com.nictbills.app.activities.tbo.bus.model.addbuspassengermodel.PassengerListValue;
import com.nictbills.app.databinding.ActivityAddBusMemberBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddBusMemberActivity extends BaseActivity implements BusPassengerAddList.OnItemClickListener {
    SharedPreferences shared ,sharedPreferences;
    PassengerListValue passengerListValue;
    ArrayList<PassengerListValue> buspassengerlist = new ArrayList<PassengerListValue>();
    int countclick = 0;
    int finalcountclick = 0;
    String getdata = "1";
    int count = 0;
    String MobilePattern = "[0-9]{10}";
    boolean termchecked = false;
    static ArrayList<AddPassenger> addpassenger;
    BusPassengerAddList busPassengerAddList;
    BusPassengerAddList.OnItemClickListener listener;
    AddPassenger addPassengermodels;
    String  mobileNumber;
    ActivityAddBusMemberBinding binding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_bus_member);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        count = shared.getInt("Adultno", 1);
        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mobileNumber = sharedPreferences.getString("cred_2", "");
        addpassenger = new ArrayList<AddPassenger>();
        for (int i = 1; i <= count; i++) {
            passengerListValue = new PassengerListValue();
            passengerListValue.setPassangerno(i + "");
            passengerListValue.setEdtname("");
            passengerListValue.setEdtlname("");
            passengerListValue.setEdtmobile("");
            passengerListValue.setEdtemail("");
            passengerListValue.setEdtage("");
            passengerListValue.setEdtaddress("");
            passengerListValue.setLeadpassengertype(false);
            buspassengerlist.add(passengerListValue);
        }
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.ViewListBus1.setLayoutManager(linearLayoutManager1);
        busPassengerAddList = new BusPassengerAddList(AddBusMemberActivity.this, buspassengerlist, this);
        binding.ViewListBus1.setAdapter(busPassengerAddList);
        click();
    }

    public void click() {
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onItemClick(View v, BusPassengerAddList.MyViewHolder holder, int position, String name, String lname, String gendertype, String mobile, String email, String age, String address) {

        if (name.length() == 0) {
            Log.e("noname", "ty.." + name);
            Toast.makeText(AddBusMemberActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (lname.length() == 0) {
            Log.e("nolastname", "ty.." + name);
            Toast.makeText(AddBusMemberActivity.this, "Enter Your Last Name", Toast.LENGTH_SHORT).show();

        } else if (mobile.length()==0) {
            Toast.makeText(AddBusMemberActivity.this, "Enter  Mobile No", Toast.LENGTH_SHORT).show();

        } else if (email.length() == 0) {
            Toast.makeText(AddBusMemberActivity.this, "Enter Correct Email Id", Toast.LENGTH_SHORT).show();

        } else if (age.length() == 0) {
            Toast.makeText(AddBusMemberActivity.this, "Enter Your Age", Toast.LENGTH_SHORT).show();

        } else if (address.length() == 0) {
            Toast.makeText(AddBusMemberActivity.this, "Enter Your Address", Toast.LENGTH_SHORT).show();

        } else {
            if(mobile.trim().matches(MobilePattern))
            {
                if (isValidEmailId(email))
                {

//                        Toast.makeText(AddBusMemberActivity.this, "correct age", Toast.LENGTH_SHORT).show();
                        addPassengermodels = new AddPassenger();
                        addPassengermodels.setUname(name);
                        addPassengermodels.setUlastname(lname);
                        addPassengermodels.setUmobile(mobile);
                        addPassengermodels.setUemail(email);
                        addPassengermodels.setUage(age);
                        addPassengermodels.setUaddress(address);
                        addPassengermodels.setUgender(gendertype);
                        if(mobile.equals(mobileNumber))
                        {
                            addPassengermodels.setUleadpassenger(String.valueOf(true));
                        }
                        else
                        {
                            addPassengermodels.setUleadpassenger(String.valueOf(false));
                        }
                        addpassenger.add(addPassengermodels);
                        buspassengerlist.remove(position);
                        busPassengerAddList.notifyDataSetChanged();
                        finalcountclick = finalcountclick + 1;

                        if (finalcountclick == count) {

                            Intent i = new Intent(AddBusMemberActivity.this, BusProcessedToBookingActivity.class);
                            AddBusMemberActivity.this.finish();
                            startActivity(i);
                        }
                        else {
//                        Toast.makeText(AddBusMemberActivity.this, "Add Another Passenger Details", Toast.LENGTH_SHORT).show();
                        }



//


                }
                else
                {
                    Toast.makeText(AddBusMemberActivity.this, "Incorrect EmailId", Toast.LENGTH_SHORT).show();
                }

            }else
            {
                Toast.makeText(AddBusMemberActivity.this, "Incorrect Mobile NO digit", Toast.LENGTH_SHORT).show();

            }
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

}