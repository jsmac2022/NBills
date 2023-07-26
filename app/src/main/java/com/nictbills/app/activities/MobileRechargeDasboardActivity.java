package com.nictbills.app.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.adapter.MobilePlansPriceAdapter;
import com.nictbills.app.adapter.OperatorCircleAdapter;
import com.nictbills.app.adapter.ViewMobilePlansAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.getMobileRechargeProvider.GetProvider;
import com.nictbills.app.activities.health_id_abdm.dto.getMobileRechargeProvider.Operator;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.GetRewardPointsResponse;
import com.nictbills.app.activities.health_id_abdm.dto.view_plans_dto.ViewPlansDto;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargeDasboardActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private ImageView backArrow_IMG;
    private Button pay_Button;
    private EditText recharge_mobile_EditText,operator_circle_EditText,plan_amount_EditText;
    private final int open_contact_book=101;
    private BottomSheetDialog bottomSheetDialogSelectOperatorCircle, viewPlansBottomSheet;
    private RecyclerView operator_circle_recycler, planTitle_recycleView,mobileRechargeRateList_RV;
    private RecyclerView.LayoutManager layoutManager, layoutManagerMobilePlanView;
    private RetrofitInterface retroFitInterface;
    private String credential,useReward="No";
    private SharedPreferences sharedPreferences;
    private List<Operator> operatorsList;
    private List<ViewPlansDto> viewPlansList;
    private List<String> planTitleList;
    private List<ViewPlansDto> filteredPriceList;
    private int planAdapterPosition=0,rewardPoints=0,rewardPointForward=0,operatorValue=-1;
    private TextView title_bottomSheet;
    private CheckBox checkbox_reward_balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge_dasboard);

        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        pay_Button = findViewById(R.id.pay_Button);
        recharge_mobile_EditText = findViewById(R.id.recharge_mobile_EditText);
        plan_amount_EditText = findViewById(R.id.plan_amount_EditText);
        checkbox_reward_balance = findViewById(R.id.checkbox_reward_balance);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");

        operator_circle_EditText = findViewById(R.id.operator_circle_EditText);
        bottomSheetDialogSelectOperatorCircle = new BottomSheetDialog(this);
        bottomSheetDialogSelectOperatorCircle.setContentView(R.layout.select_operator_circle_bottomsheet);
        getRewardPoints();

        viewPlansBottomSheet= new BottomSheetDialog(this);
        viewPlansBottomSheet.setContentView(R.layout.view_plans_bottomsheet);

        operator_circle_recycler = bottomSheetDialogSelectOperatorCircle.findViewById(R.id.operator_circle_recycler);
        title_bottomSheet = bottomSheetDialogSelectOperatorCircle.findViewById(R.id.title_bottomSheet);

        planTitle_recycleView = viewPlansBottomSheet.findViewById(R.id.planTitle_recycleView);
        mobileRechargeRateList_RV = viewPlansBottomSheet.findViewById(R.id.mobileRechargeRateList_RV);

        layoutManager = new LinearLayoutManager(MobileRechargeDasboardActivity.this);
        layoutManagerMobilePlanView = new LinearLayoutManager(MobileRechargeDasboardActivity.this);

        operator_circle_recycler.setHasFixedSize(true);
        operator_circle_recycler.setLayoutManager(layoutManager);
        operator_circle_recycler.setItemAnimator(new DefaultItemAnimator());

        planTitle_recycleView.setHasFixedSize(true);
        planTitle_recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false) );
        planTitle_recycleView.setItemAnimator(new DefaultItemAnimator());


        operator_circle_EditText.setInputType(InputType.TYPE_NULL);
        operator_circle_EditText.setFocusable(false);
        operator_circle_EditText.setClickable(true);

        checkbox_reward_balance.setEnabled(false);

        plan_amount_EditText.setInputType(InputType.TYPE_NULL);
        plan_amount_EditText.setFocusable(false);
        plan_amount_EditText.setClickable(true);


        mobileRechargeRateList_RV.setHasFixedSize(true);
        mobileRechargeRateList_RV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false) );
        mobileRechargeRateList_RV.setItemAnimator(new DefaultItemAnimator());


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MobileRechargeDasboardActivity.this,DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });

        pay_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


             if (isEmpty(recharge_mobile_EditText)){

                 Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

             } else if(!Util.isValidMobileNumber(recharge_mobile_EditText.getText().toString())){

                 Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

             } else if (operatorValue==-1){

                 Snackbar.make(v, getString(R.string.select_operator_circle), Snackbar.LENGTH_SHORT).show();

             } else if (isEmpty(plan_amount_EditText)){

                 Snackbar.make(v, getString(R.string.enter_recharge_amount), Snackbar.LENGTH_SHORT).show();
             } else {
                 Intent intent = new Intent(MobileRechargeDasboardActivity.this,PayUsingUPIRechargeActivity.class);
                 intent.putExtra("PAY_FOR","MOBILE");
                 intent.putExtra("CUSTOMER_ID",recharge_mobile_EditText.getText().toString());
                 intent.putExtra("OPERATOR",operatorValue);
                 intent.putExtra("AMOUNT",plan_amount_EditText.getText().toString());
                 intent.putExtra("REWARD_STATUS",useReward);
                 intent.putExtra("REWARD_POINT",rewardPointForward);
                 startActivity(intent);

             }
                } else {

                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }
            }
        });


        checkbox_reward_balance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //checked
                    useReward="Yes";
                    rewardPointForward = rewardPoints;
                    //Toast.makeText(PayUsingActivity.this, "check", Toast.LENGTH_SHORT).show();
                } else {
                    useReward="No";
                    rewardPointForward=0;
                    //not checked
                    //Toast.makeText(PayUsingActivity.this, "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });





        recharge_mobile_EditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (recharge_mobile_EditText.getRight() - recharge_mobile_EditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        // Show only contacts with phone numbers
                        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                        // Start the Contacts activity
                        startActivityForResult(intent, open_contact_book);
                        return true;
                    }
                }
                return false;
            }
        });


        operator_circle_EditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;


                    if (isEmpty(recharge_mobile_EditText)){

                        Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                    } else if(!Util.isValidMobileNumber(recharge_mobile_EditText.getText().toString())){

                        Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                    } /*else if (operatorValue==-1){

                        Snackbar.make(v, getString(R.string.select_operator_circle), Snackbar.LENGTH_SHORT).show();

                    }*/else {

                        getProviderOperatorCircle();
                    }
                } else {

                    connected = false;

                    Snackbar snackbar = Snackbar
                            .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                            .setAction(getString(R.string.retry), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    snackbar.show();
                }
            }
        });



        plan_amount_EditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (plan_amount_EditText.getRight() - plan_amount_EditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        boolean connected = false;
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network
                            connected = true;


                            if (isEmpty(recharge_mobile_EditText)){

                                Snackbar.make(v, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                            } else if(!Util.isValidMobileNumber(recharge_mobile_EditText.getText().toString())){

                                Snackbar.make(v, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                            } else if (operatorValue==-1){

                                Snackbar.make(v, getString(R.string.select_operator_circle), Snackbar.LENGTH_SHORT).show();

                            } else {
                                planAdapterPosition=0;
                                getMobilePlansList();
                            }
                        } else {

                            connected = false;

                            Snackbar snackbar = Snackbar
                                    .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                                    .setAction(getString(R.string.retry), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    });

// Changing message text color
                            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                            View sbView = snackbar.getView();
                            snackbar.show();
                        }



                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MobileRechargeDasboardActivity.this,DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case open_contact_book :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI};
                    Cursor c = MobileRechargeDasboardActivity.this.getContentResolver().query(contactData, projection, null, null, null);
                    c.moveToFirst();
                    int nameIdx = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    int phoneNumberIdx = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int photoIdx = c.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_THUMBNAIL_URI);
                    String name = c.getString(nameIdx);
                    String phoneNumber = c.getString(phoneNumberIdx).trim();
                    String photo = c.getString(photoIdx);
                    if (photo == null) {
                        // If no photo then substitute a dummy image
                     //   mContactImage.setImageResource(R.drawable.ic_contact_picture);
                    } else {
                        // Display the contact photo
                        final Uri imageUri = Uri.parse(photo);
                       // mContactImage.setImageURI(imageUri);
                    }
                    if (name == null) {
                        name = "No Name";
                    }
                   // mName.setText(name);
                    //getValidMobileNumber(phoneNumber.trim());
                    recharge_mobile_EditText.setText(getValidMobileNumber(phoneNumber.trim()));
                    //Log.e("mobile number", phoneNumber.trim().replace(" ",""));
                   /* if(phoneNumber.startsWith("+91")){

                        String phone=(phoneNumber.trim().replace(" ",""));
                        recharge_mobile_EditText.setText(phone.substring(3));

                    }else {

                        recharge_mobile_EditText.setText(phoneNumber.trim().replace(" ",""));
                    }
*/

                    c.close();

                    // Now you have the phone number

                }
                break;
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    public static String getValidMobileNumber(String mobileNumber) {

        if(mobileNumber.trim().replace(" ", "").startsWith("+91") && mobileNumber.trim().replace(" ", "").length()==13) {
            return mobileNumber.trim().replace(" ", "").substring(3);

        }else if(mobileNumber.trim().replace(" ", "").startsWith("0") && mobileNumber.trim().replace(" ", "").length()==11) {
            return mobileNumber.trim().replace(" ", "").substring(1);

        }else if(mobileNumber.trim().replace(" ", "").length()==10) {
            return mobileNumber.trim().replace(" ", "");

        }
        return mobileNumber;

    }

    private void setAdapterGetOperators(List<Operator> operatorsList){
        OperatorCircleAdapter adapter = new OperatorCircleAdapter(MobileRechargeDasboardActivity.this, operatorsList,this);
        operator_circle_recycler.setAdapter(adapter);
    }


    private void setAdapterViewPlans(List<String> planTitleList, List<ViewPlansDto> viewPlansList){
        ViewMobilePlansAdapter adapter = new ViewMobilePlansAdapter(MobileRechargeDasboardActivity.this, planTitleList, viewPlansList,this);
        planTitle_recycleView.setAdapter(adapter);
    }

    private void setAdapterPriceDetails( List<ViewPlansDto> allPlanList,List<ViewPlansDto> filterPlanList,String listType){

        MobilePlansPriceAdapter mobilePlansPriceAdapter = new MobilePlansPriceAdapter(this,listType,allPlanList,filterPlanList,this);
        mobileRechargeRateList_RV.setAdapter(mobilePlansPriceAdapter);


    }


    private void getProviderOperatorCircle() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(MobileRechargeDasboardActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetProvider> call = retroFitInterface.getMobileOperatorCircle(credential);

        call.enqueue(new Callback<GetProvider>() {
            @Override
            public void onResponse(Call<GetProvider> call, Response<GetProvider> response) {
                progressLog.dismiss();

                GetProvider body = response.body();

                if (response.code() == 200) {
                    operatorsList = new ArrayList<>();
                    for (Operator op : body.getOperators()){

                       if (op.getType().trim().equalsIgnoreCase("mobile")){
                           Operator opr = new Operator();
                           opr.setId(op.getId());
                           if (op.getName().equalsIgnoreCase("JIORECH")){
                               opr.setName("JIO");
                           }else{
                               opr.setName(op.getName());
                           }
                           //opr.setName(op.getName());
                           opr.setType(op.getType());
                           operatorsList.add(opr);
                          // operatorsList.add(operatorList);
                           //operators.set()
                       }

                    }
                   // Log.e("aaaaaa+",operators.toString());
                   // operators = body.getOperators();
                    setAdapterGetOperators(operatorsList);
                    title_bottomSheet.setText(getText(R.string.operator_circle));
                    bottomSheetDialogSelectOperatorCircle.show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(MobileRechargeDasboardActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MobileRechargeDasboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(MobileRechargeDasboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(MobileRechargeDasboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetProvider> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(MobileRechargeDasboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }



    private void getMobilePlansList() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(MobileRechargeDasboardActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<List<ViewPlansDto>> call = retroFitInterface.getMobilePlans(credential,operatorValue);

        call.enqueue(new Callback<List<ViewPlansDto>>() {
            @Override
            public void onResponse(Call<List<ViewPlansDto>> call, Response<List<ViewPlansDto>> response) {
                progressLog.dismiss();

               // ViewPlansDto body = response.body();

                if (response.code() == 200) {

                  viewPlansList = new ArrayList<>();

                    viewPlansList = response.body();

                   /* for (ViewPlansDto op : viewPlansList){

                       *//* if (op.getType().trim().equalsIgnoreCase("mobile")){

                            Log.e("INNN",op.getType());
                            Operator opr = new Operator();
                            opr.setId(op.getId());
                            if (op.getName().equalsIgnoreCase("JIORECH")){
                                opr.setName("JIO");
                            }else{
                                opr.setName(op.getName());
                            }
                            //opr.setName(op.getName());
                            opr.setType(op.getType());
                            operatorsList.add(opr);
                            // operatorsList.add(operatorList);
                            //operators.set()
                        }*//*

                    }*/
                    // Log.e("aaaaaa+",operators.toString());
                    // operators = body.getOperators();


                    ArrayList<String> filter = new ArrayList<>();

                    for (ViewPlansDto viewPlansDto : viewPlansList) {

                        filter.add(viewPlansDto.getRechargeShortDescription());

                    }



                    HashSet<String> planTitleListHash = new HashSet<String>(filter);



                    planTitleList = new ArrayList<String>(planTitleListHash);


                        planTitleList.add(0,"All");

                    Log.e("planTTTTT",planTitleList.toString());

                    setAdapterViewPlans(planTitleList,viewPlansList);

                    setAdapterPriceDetails(viewPlansList,viewPlansList,"All");
                    viewPlansBottomSheet.show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(MobileRechargeDasboardActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MobileRechargeDasboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("APP_DATA", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(MobileRechargeDasboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(MobileRechargeDasboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ViewPlansDto>> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(MobileRechargeDasboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onListItem(View view, int adapterPosition) {

       // Toast.makeText(this, view.getId()+"", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {

            case R.id.operator_name_TV:

                operatorValue = operatorsList.get(adapterPosition).getId();

          //   Toast.makeText(this, operatorsList.get(adapterPosition).getId()+"", Toast.LENGTH_SHORT).show();
                operator_circle_EditText.setText(operatorsList.get(adapterPosition).getName());
                bottomSheetDialogSelectOperatorCircle.dismiss();

                break;

            case R.id.planTitle_LL:

                 planAdapterPosition = (adapterPosition);

             //  Toast.makeText(this, planTitleList.get(adapterPosition)+"", Toast.LENGTH_SHORT).show();
                filteredPriceList = new ArrayList<>();

              if (planTitleList.get(adapterPosition).trim().equalsIgnoreCase("All")){
                  setAdapterPriceDetails(viewPlansList,filteredPriceList,"All");
              }else{
                  for (ViewPlansDto vp : viewPlansList){

                      if(planTitleList.get(adapterPosition).equalsIgnoreCase(vp.getRechargeShortDescription())){
                          ViewPlansDto vpd = new ViewPlansDto();
                          vpd.setId(vp.getId());
                          vpd.setRechargeTalktime(vp.getRechargeTalktime());
                          vpd.setRechargeDescription(vp.getRechargeDescription());
                          vpd.setRechargeValidity(vp.getRechargeValidity());
                          vpd.setRechargeValue(vp.getRechargeValue());
                          vpd.setSpCircle(vp.getSpCircle());
                          vpd.setSpKey(vp.getSpKey());
                          vpd.setLastUpdatedDt(vp.getLastUpdatedDt());
                          filteredPriceList.add(vpd);
                      }
                      setAdapterPriceDetails(viewPlansList,filteredPriceList,"Filter");
                  }
              }

                break;

            case R.id.amount_LinearLayout:

              //  Log.e("aa", planAdapterPosition+"");
                if (planTitleList.get(planAdapterPosition).trim().equalsIgnoreCase("All")){
                  //  Log.e("aa", adapterPosition+"");
                //    Log.e("aa", viewPlansList+"");
                 //   Log.e("bbref", filteredPriceList+"");
                   String rechargeValue = viewPlansList.get(adapterPosition).getRechargeValue();
                   plan_amount_EditText.setText(rechargeValue+"");
               //     Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
                }else{
                //    Log.e("bb", adapterPosition+"");
                //    Log.e("bb", filteredPriceList+"");
                //    Log.e("aaref", viewPlansList+"");
                   String rechargeValue = filteredPriceList.get(adapterPosition).getRechargeValue();
                   plan_amount_EditText.setText(rechargeValue+"");
                   // Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
                }

                viewPlansBottomSheet.dismiss();
                break;
        }
    }


    private void getRewardPoints() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetRewardPointsResponse> call = retroFitInterface.getRewardPoints(credential);

        call.enqueue(new Callback<GetRewardPointsResponse>() {
            @Override
            public void onResponse(Call<GetRewardPointsResponse> call, Response<GetRewardPointsResponse> response) {
                progressDialogDismiss();

                GetRewardPointsResponse body = response.body();

                if (response.code() == 200) {

                    rewardPoints = body.getBalance();
                    checkbox_reward_balance.setText(getString(R.string.total_balance)+" : "+rewardPoints);

                    if (rewardPoints==0){
                        checkbox_reward_balance.setEnabled(false);
                    }else {
                        checkbox_reward_balance.setEnabled(true);
                    }



                } else if (response.code() == 401) {

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MobileRechargeDasboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(MobileRechargeDasboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(MobileRechargeDasboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MobileRechargeDasboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(MobileRechargeDasboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<GetRewardPointsResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(MobileRechargeDasboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }

}