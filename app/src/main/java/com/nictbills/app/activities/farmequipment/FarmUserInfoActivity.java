package com.nictbills.app.activities.farmequipment;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.farmequipment.model.AddUserInfoResponse.AddUserInfoResponsemodel;
import com.nictbills.app.activities.farmequipment.model.farmlocation.FarmLocationCheckResponse;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.databinding.ActivityFarmUserInfoBinding;
import com.nictbills.app.utils.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmUserInfoActivity extends BaseActivity {
    ActivityFarmUserInfoBinding binding;
    SharedPreferences shared, sharedPreferences;
    String mobileNumber, getproduct_id, get_location;
    String MobilePattern = "[0-9]{10}";
    boolean isAllFieldsChecked = false;
    HashMap<String, String> param;
    String gendertype ,getgendertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_farm_user_info);
        shared = this.getSharedPreferences("nict", MODE_PRIVATE);
        shared = getSharedPreferences("nict", MODE_PRIVATE);

        getproduct_id = shared.getString("productid", "");
        get_location = shared.getString("location", "");

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mobileNumber = sharedPreferences.getString("cred_2", "");
        gendertype = ((RadioButton) findViewById(binding.radioGroup.getCheckedRadioButtonId()))
                .getText().toString();
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                gendertype = ((RadioButton) findViewById(binding.radioGroup.getCheckedRadioButtonId())).getText().toString();
            }
        });
        if (gendertype.equals("Male")) {
            getgendertype = "Male";
        } else {
            getgendertype = "Female";
        }
        click();
    }

    public void click() {
        binding.edtMobile.setText(mobileNumber);
        binding.backArrowIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {

                    if (binding.edtMobile.getText().toString().matches(MobilePattern)) {


                        shared = getSharedPreferences("nict", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("Uname", binding.edtUname.getText().toString().trim());
                        editor.putString("Ulastname", binding.edtLname.getText().toString().trim());
                        editor.putString("Umobile", binding.edtMobile.getText().toString().trim());
                        editor.putString("Ucity", binding.edtCity.getText().toString().trim());
                        editor.putString("Uaddress", binding.edtAddress.getText().toString().trim());
                        editor.apply();

                        param = new HashMap<>();
                        param.put("userid", mobileNumber);
                        param.put("name", binding.edtUname.getText().toString().trim());
                        param.put("gender", getgendertype);
                        param.put("email", "");
                        param.put("home_address", binding.edtAddress.getText().toString().trim());
                        param.put("business_address", binding.edtAddress.getText().toString().trim());
                        param.put("city", binding.edtCity.getText().toString().trim());
                        param.put("state", "M.P");
                        param.put("mobile", mobileNumber);
                        param.put("village", binding.edtCity.getText().toString().trim());
                        addusernfo(param);


                    } else {
                        Toast.makeText(FarmUserInfoActivity.this, "Enter Correct Mobile No", Toast.LENGTH_SHORT).show();

                    }


                }

            }
        });
    }

    private boolean CheckAllFields() {
        if (binding.edtUname.getText().toString().length() == 0) {
            binding.edtUname.setError("This field is required");
            return false;
        }

        if (binding.edtLname.getText().toString().length() == 0) {
            binding.edtLname.setError("This field is required");
            return false;
        }

        if (binding.edtMobile.getText().toString().length() == 0) {
            binding.edtMobile.setError("Mobile no is required");
            return false;
        }
//        if (binding.edtEmail.getText().toString().length() == 0) {
//            binding.edtEmail.setError("Email is required");
//            return false;
//
//        }


        if (binding.edtAddress.getText().toString().length() == 0) {
            binding.edtAddress.setError("Fill Address");
            return false;
        }

        if (binding.edtCity.getText().toString().length() == 0) {
            binding.edtCity.setError("Fill City");
            return false;
        }
//        binding.etEmail.getText().toString().matches(emailPattern) && binding.etEmail.getText().toString().length() > 0
        // after all validation return true.
        return true;
    }

    private boolean isValidEmailId(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public void addusernfo(HashMap<String, String> param) {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.FarmEquipment).create(RetrofitInterface.class);
        Call<AddUserInfoResponsemodel> call = retroFitInterface.adduserinfo(param);
        call.enqueue(new Callback<AddUserInfoResponsemodel>() {
            @Override
            public void onResponse(Call<AddUserInfoResponsemodel> call, Response<AddUserInfoResponsemodel> response) {
                progressDialogDismiss();
                AddUserInfoResponsemodel addUserInfoResponsemodel = response.body();
                if (response.code() == 200) {

                    if (addUserInfoResponsemodel.getMessage().equals("Client already exists")) {
                        Toast.makeText(FarmUserInfoActivity.this, "Client already exists", Toast.LENGTH_SHORT).show();
                        checklocation();

                    } else {
                        checklocation();

                    }
                } else if (response.code() == 400) {
                    Toast.makeText(FarmUserInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FarmUserInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddUserInfoResponsemodel> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: " + t.toString());
            }
        });

    }

    public void checklocation() {
        progressDialogShow();
        RetrofitInterface retroFitInterface = RetrofitClient.getClient(Constant.FarmEquipment).create(RetrofitInterface.class);
        Call<FarmLocationCheckResponse> call = retroFitInterface.checklocation(getproduct_id ,binding.edtCity.getText().toString().trim());
        call.enqueue(new Callback<FarmLocationCheckResponse>() {
            @Override
            public void onResponse(Call<FarmLocationCheckResponse> call, Response<FarmLocationCheckResponse> response) {
                progressDialogDismiss();
                FarmLocationCheckResponse farmLocationCheckResponse = response.body();

                if (response.code() == 200)
                {

                 if(farmLocationCheckResponse.getMessage().equals("Equipment is available in your location"))
                 {
                     Intent intent = new Intent(FarmUserInfoActivity.this, FarmOrderShowActivity.class);
                    startActivity(intent);
                 }
                 else
                 {
                     Toast.makeText(FarmUserInfoActivity.this,farmLocationCheckResponse.getMessage(), Toast.LENGTH_SHORT).show();

                 }
//
                } else if (response.code() == 400) {
                    Toast.makeText(FarmUserInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FarmUserInfoActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<FarmLocationCheckResponse> call, Throwable t) {
                progressDialogDismiss();
                Log.e("nictfailur", "onResponse: " + t.toString());

            }
        });
    }

}