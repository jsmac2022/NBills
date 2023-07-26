package com.nictbills.app.activities.paysprint.mobile_recharge_paysprint;

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
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.activities.PayUsingUPIRechargeActivity;
import com.nictbills.app.adapter.mobile_recharge_paysprint.CirclePaySprintAdapter;
import com.nictbills.app.adapter.mobile_recharge_paysprint.MobilePlanHeaderPaySprintAdapter;
import com.nictbills.app.adapter.mobile_recharge_paysprint.OperatorCirclePaySprintAdapter;
import com.nictbills.app.adapter.mobile_recharge_paysprint.PlanDescriptionPaySprintAdapter;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.Datum;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.GetOperatorPaySprint;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.getRechargePlan.PlanDetailsList;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.getRechargePlan.RechargePlanRequest;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.GetRewardPointsResponse;
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface;
import com.nictbills.app.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargeDashboardPaySprintActivity extends BaseActivity implements OnClickRecyclerViewInterface {

    private EditText recharge_mobile_EditText,operatorEditText,plan_amount_EditText,circle_EditText;
    private Button pay_Button;
    private CheckBox checkbox_reward_balance;
    private RetrofitInterface retroFitInterface;
    private String credential,useReward="No";
    private SharedPreferences sharedPreferences;
    private List<Datum> operatorsList;
    private RecyclerView operator_circle_recycler,circle_recycler,planTitle_recycleView,mobileRechargeRateList_RV;
    private BottomSheetDialog bottomSheetDialogSelectOperatorCircle, viewPlansBottomSheet,bottomSheetDialogSelectCircle;
    private TextView title_bottomSheet;
    private RecyclerView.LayoutManager layoutManager;
    private int operatorValue=-1,rewardPointForward=0,rewardPoints=0;
    private final int open_contact_book=101;
    private ImageView backArrow_IMG;
    private ArrayList<String> circleList;
    private List<PlanDetailsList> planDetails;
    private List<String> planHeaderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge_dashboard_pay_sprint);

        pay_Button = findViewById(R.id.pay_Button);
        recharge_mobile_EditText = findViewById(R.id.recharge_mobile_EditText);
        operatorEditText = findViewById(R.id.operatorEditText);
        plan_amount_EditText = findViewById(R.id.plan_amount_EditText);
        checkbox_reward_balance = findViewById(R.id.checkbox_reward_balance);
        circle_EditText = findViewById(R.id.circle_EditText);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");

        operatorEditText.setInputType(InputType.TYPE_NULL);
        operatorEditText.setFocusable(false);
        operatorEditText.setClickable(true);

        checkbox_reward_balance.setEnabled(false);

        circleList = new ArrayList<>();
        String[] values = new String[] { "Andhra Pradesh Telangana", "Assam", "Bihar Jharkhand", "Chennai", "Delhi NCR", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu Kashmir", "Karnataka", "Kerala", "Kolkata", "Madhya Pradesh Chhattisgarh", "Maharashtra Goa", "Mumbai", "North East", "Orissa", "Punjab", "Rajasthan", "Tamil Nadu", "UP East", "UP West", "West Bengal"};

        for (int i = 0; i < values.length; ++i) {
            circleList.add(values[i]);
        }

        plan_amount_EditText.setInputType(InputType.TYPE_NULL);
        plan_amount_EditText.setFocusable(false);
        plan_amount_EditText.setClickable(true);

        circle_EditText.setInputType(InputType.TYPE_NULL);
        circle_EditText.setFocusable(false);
        circle_EditText.setClickable(true);

        bottomSheetDialogSelectOperatorCircle = new BottomSheetDialog(this);
        bottomSheetDialogSelectOperatorCircle.setContentView(R.layout.select_operator_circle_bottomsheet);

        bottomSheetDialogSelectCircle = new BottomSheetDialog(this);
        bottomSheetDialogSelectCircle.setContentView(R.layout.select_circle_bottom_sheet);


        viewPlansBottomSheet= new BottomSheetDialog(this);
        viewPlansBottomSheet.setContentView(R.layout.view_plans_bottomsheet);

        planTitle_recycleView = viewPlansBottomSheet.findViewById(R.id.planTitle_recycleView);
        mobileRechargeRateList_RV = viewPlansBottomSheet.findViewById(R.id.mobileRechargeRateList_RV);

        planTitle_recycleView.setHasFixedSize(true);
        planTitle_recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false) );
        planTitle_recycleView.setItemAnimator(new DefaultItemAnimator());

        mobileRechargeRateList_RV.setHasFixedSize(true);
        mobileRechargeRateList_RV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false) );
        mobileRechargeRateList_RV.setItemAnimator(new DefaultItemAnimator());


        getRewardPoints();

        operator_circle_recycler = bottomSheetDialogSelectOperatorCircle.findViewById(R.id.operator_circle_recycler);
        title_bottomSheet = bottomSheetDialogSelectOperatorCircle.findViewById(R.id.title_bottomSheet);

        layoutManager = new LinearLayoutManager(MobileRechargeDashboardPaySprintActivity.this);
        operator_circle_recycler.setHasFixedSize(true);
        operator_circle_recycler.setLayoutManager(layoutManager);
        operator_circle_recycler.setItemAnimator(new DefaultItemAnimator());

        circle_recycler = bottomSheetDialogSelectCircle.findViewById(R.id.circle_recycler);
        title_bottomSheet = bottomSheetDialogSelectCircle.findViewById(R.id.title_bottomSheet);

        layoutManager = new LinearLayoutManager(MobileRechargeDashboardPaySprintActivity.this);
        circle_recycler.setHasFixedSize(true);
        circle_recycler.setLayoutManager(layoutManager);
        circle_recycler.setItemAnimator(new DefaultItemAnimator());



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


        circle_EditText.setOnClickListener(new View.OnClickListener() {
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

                    } else {

                        setAdapterGetCirclePaySprint(circleList);
                        //title_bottomSheet.setText(getText(R.string.operator_circle));
                        bottomSheetDialogSelectCircle.show();
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

                    }  else if (isEmpty(circle_EditText)){

                        Snackbar.make(v, getString(R.string.kindly_select_circle_state), Snackbar.LENGTH_SHORT).show();

                    } else if (isEmpty(plan_amount_EditText)){

                        Snackbar.make(v, getString(R.string.enter_recharge_amount), Snackbar.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(MobileRechargeDashboardPaySprintActivity.this, PayUsingUPIRechargeActivity.class);
                        intent.putExtra("PAY_FOR","MOBILE");
                        intent.putExtra("CUSTOMER_ID",recharge_mobile_EditText.getText().toString());
                        intent.putExtra("OPERATOR",operatorValue);
                        intent.putExtra("AMOUNT",plan_amount_EditText.getText().toString());
                        intent.putExtra("REWARD_STATUS",useReward);
                        intent.putExtra("CIRCLE",circle_EditText.getText().toString());
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


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MobileRechargeDashboardPaySprintActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }
        });


        operatorEditText.setOnClickListener(new View.OnClickListener() {
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

                            } else if (isEmpty(circle_EditText)){
                                Snackbar.make(v, getString(R.string.kindly_select_circle_state), Snackbar.LENGTH_SHORT).show();
                            } else {
                              //  planAdapterPosition=0;
                                RechargePlanRequest rechargePlanRequest = new RechargePlanRequest();
                                rechargePlanRequest.setCircle(circle_EditText.getText().toString());
                                rechargePlanRequest.setOp(operatorEditText.getText().toString());
                                getMobilePlansListPaySprint(rechargePlanRequest);
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

    private void setAdapterViewPlansHeader(List<String> planTitleList, List<PlanDetailsList> viewPlansList){
        MobilePlanHeaderPaySprintAdapter adapter = new MobilePlanHeaderPaySprintAdapter(this, planTitleList, viewPlansList,this);
        planTitle_recycleView.setAdapter(adapter);
    }

    private void setAdapterPriceDetails( List<String> planHeaderList,List<PlanDetailsList> filterPlanList){
        PlanDescriptionPaySprintAdapter mobilePlansPriceAdapter = new PlanDescriptionPaySprintAdapter(this,planHeaderList,filterPlanList,this);
        mobileRechargeRateList_RV.setAdapter(mobilePlansPriceAdapter);

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    private void setAdapterGetOperatorsPaySprint(List<Datum> operatorsList){
        OperatorCirclePaySprintAdapter adapter = new OperatorCirclePaySprintAdapter(MobileRechargeDashboardPaySprintActivity.this, operatorsList,this);
        operator_circle_recycler.setAdapter(adapter);
    }

    private void setAdapterGetCirclePaySprint(List<String> circleList){
        CirclePaySprintAdapter adapter = new CirclePaySprintAdapter(MobileRechargeDashboardPaySprintActivity.this, circleList,this);
        circle_recycler.setAdapter(adapter);

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
                    Cursor c = MobileRechargeDashboardPaySprintActivity.this.getContentResolver().query(contactData, projection, null, null, null);
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

    private void getProviderOperatorCircle() {
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(MobileRechargeDashboardPaySprintActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<GetOperatorPaySprint> call = retroFitInterface.getMobileOperatorCirclePaySprint(credential);

        call.enqueue(new Callback<GetOperatorPaySprint>() {
            @Override
            public void onResponse(Call<GetOperatorPaySprint> call, Response<GetOperatorPaySprint> response) {
                progressLog.dismiss();

                GetOperatorPaySprint body = response.body();

                if (response.code() == 200) {
                    operatorsList = new ArrayList<>();
                    for (Datum op : body.getData()){

                        if (op.getCategory().trim().equalsIgnoreCase("Prepaid")){
                            Datum opr = new Datum();
                            opr.setId(op.getId());
                            opr.setName(op.getName());
                            //opr.setName(op.getName());
                            opr.setCategory(op.getCategory());
                            Log.e("mopbilleee++", op.getName());
                            operatorsList.add(opr);
                            // operatorsList.add(operatorList);
                            //operators.set()
                        }

                    }
                    // Log.e("aaaaaa+",operators.toString());
                    // operators = body.getOperators();
                    setAdapterGetOperatorsPaySprint(operatorsList);
                    title_bottomSheet.setText(getText(R.string.circle));
                    bottomSheetDialogSelectOperatorCircle.show();

                } else if (response.code() == 500) {

                    new SweetAlertDialog(MobileRechargeDashboardPaySprintActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MobileRechargeDashboardPaySprintActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(MobileRechargeDashboardPaySprintActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetOperatorPaySprint> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getMobilePlansListPaySprint(RechargePlanRequest rechargePlanRequest) {
       // rechargePlanRequest.setOp("JIO");
       // rechargePlanRequest.setCircle("Madhya Pradesh Chhattisgarh");
        final ProgressDialog progressLog;
        progressLog = new ProgressDialog(MobileRechargeDashboardPaySprintActivity.this);
        progressLog.setTitle(getString(R.string.loading));
        progressLog.setMessage(getString(R.string.one_moment_please));
        progressLog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressLog.setCancelable(false);
        progressLog.show();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(RetrofitInterface.class);

        Call<ResponseBody> call = retroFitInterface.getMobilePlansPaySprint(credential,rechargePlanRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressLog.dismiss();
                Log.e("code ", response.code()+"");
                Log.e("message ", response.message()+"");
               // ResponseBody body = response.body();
                if (response.code() == 200) {
                try {

                        String response_result = response.body().string();
                        Log.e("vinod 1 ", response_result);


                    JSONObject jObj = new JSONObject(response_result);
                    JSONObject infoJsonResponse = jObj.getJSONObject("info");
                    Log.e("info.......", String.valueOf(infoJsonResponse));

                    planHeaderList = new ArrayList<>();
                    planDetails = new ArrayList<>();

                    for (Iterator<String> it = infoJsonResponse.keys(); it.hasNext(); ) {
                        String key = it.next();

                        planHeaderList.add(key);
                        Log.e("key.......", key);



                        JSONArray jr = infoJsonResponse.getJSONArray(key);
                        for(int i=0;i<jr.length();i++)
                        {
                            JSONObject jb1 = jr.getJSONObject(i);
                            PlanDetailsList planDetailsList = new PlanDetailsList();
                            planDetailsList.setDesc(jb1.getString("desc"));
                            planDetailsList.setRs(jb1.getString("rs"));
                            planDetailsList.setValidity(jb1.getString("validity"));
                            planDetailsList.setLast_update(jb1.getString("last_update"));


                            //String rs = jb1.getString("validity");

                            planDetails.add(planDetailsList);
                           // Log.e("RS.......",rs);
                        }

                        Log.e("PlanHeader",planHeaderList.toString());
                        Log.e("PlanHeaderDetails",planDetails.toString());

                        setAdapterViewPlansHeader(planHeaderList,planDetails);

                        setAdapterPriceDetails(planHeaderList,planDetails);

                        viewPlansBottomSheet.show();


                    }

                    //JSONObject jb = new JSONObject(response);
//                    JSONArray jr = infoJsonResponse.getJSONArray("FULLTT");
//                    for(int i=0;i<jr.length();i++)
//                    {
//                        JSONObject jb1 = jr.getJSONObject(i);
//                        String rs = jb1.getString("rs");
//                        Log.e("RS.......",rs);
//                    }
//                        JSONObject obj = new JSONObject(response_result);
//                        Log.e("vinod 2 ", obj.toString());
//                        Log.e("vinod 22 ", String.valueOf(obj.getJSONArray("info")));
//
//                        JSONArray dataArray = obj.getJSONArray("info");
//                        Log.e("vinod 3 ", dataArray.length()+"");
//                        Log.e("vinod 33 ", dataArray.toString());
//
//                        if (dataArray.length() != 0) {
//                            for (int i = 0; i < dataArray.length(); i++) {
//
//                                JSONObject dataobj = dataArray.getJSONObject(i);
//                                String rs = dataobj.getString("rs");
//                                Log.e("vinod rs", rs);
//                            }
//
//                        }else{
//                            Log.e("vinod ", "else 1");
//                        }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

//                if (response.code() == 200) {
//                    JSONObject obj = new JSONObject(response_result);
//                    JSONArray dataArray = obj.getJSONArray("result");
//
//                    JSONObject obj = null;
//                    try {
//                        obj = new JSONObject(response.body().string());
//                        Log.e("ooobbject", String.valueOf(obj));
//                    } catch (JSONException | IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    JSONObject jsonObject = null;
//                    try {
//                        jsonObject = obj.getJSONObject("info");
//
//                        Log.e("newwww", String.valueOf(jsonObject.length()));
//                       // Log.e("tesstt",obj.getJSONArray("info").getJSONArray(0)+"");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    JSONArray jsonArray = new JSONArray();
//
//                    ArrayList<String> planName = new ArrayList<>();
//
//                    if (jsonObject.length() != 0) {
//                        for (int i = 0; i < jsonObject.length(); i++) {
//
//                            JSONObject jsonObject1 = null;
//                            try {
//                                jsonObject1 = jsonObject.getJSONObject()
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            Log.e("listtttt", String.valueOf(jsonObject1));


//                            JSONObject dataobj = null;
//                            try {
//                                dataobj = dataArray.;
//                                Log.e("listtttt",dataobj.getString("rs"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }



                            //UserService us = new UserService();


                         /*   us.setId(dataobj.getInt("id"));
                            us.setUsername(dataobj.getString("username"));
                            us.setService_name(dataobj.getString("service_name"));
                            us.setService_id(dataobj.getString("service_id"));
                            us.setEntry_dt(dataobj.getString("entry_dt"));
                            userServiceArrayList.add(us);*/
//                        }
                     //   retrofitAdapter = new SelectUserServicesRetrofitAdapter(this, userServiceArrayList);
                       // recyclerView.setAdapter(retrofitAdapter);
                       // recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

//                    } else {
//                      //  Snackbar.make(parent_view, "Record not available", Snackbar.LENGTH_SHORT).show();
//                    }

                   /* planDetailsList = new ArrayList<>();

                    for (int i = 0; i < body.length; ++i) {
                        planDetailsList.add(values[i]);
                    }
*/
                //Log.e("aaaaaaList",body.getInfo().getTopup().get(0).);


                /*    viewPlansList = new ArrayList<>();

                    viewPlansList = response.body();*/

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


                 /*   ArrayList<String> filter = new ArrayList<>();

                    for (ViewPlansDto viewPlansDto : viewPlansList) {

                        filter.add(viewPlansDto.getRechargeShortDescription());

                    }



                    HashSet<String> planTitleListHash = new HashSet<String>(filter);



                    planTitleList = new ArrayList<String>(planTitleListHash);


                    planTitleList.add(0,"All");

                    Log.e("planTTTTT",planTitleList.toString());

                    setAdapterViewPlans(planTitleList,viewPlansList);

                    setAdapterPriceDetails(viewPlansList,viewPlansList,"All");
                    viewPlansBottomSheet.show();*/

                } else if (response.code() == 500) {

                    new SweetAlertDialog(MobileRechargeDashboardPaySprintActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getString(R.string.something_went_wrong))
                            .show();

                } else if (response.code() == 401){

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MobileRechargeDashboardPaySprintActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(MobileRechargeDashboardPaySprintActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();

                } else {

                    Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressLog.dismiss();
                Log.e("MSG",t.getMessage());

                Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onListItem(View view, int adapterPosition) {

        // Toast.makeText(this, view.getId()+"", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {

            case R.id.operator_name_TV:

                try {
                    operatorValue = Integer.parseInt(operatorsList.get(adapterPosition).getId());
                }
                catch (NumberFormatException e) {
                    operatorValue = 0;
                }
                 //  Toast.makeText(this, operatorsList.get(adapterPosition).getId()+"", Toast.LENGTH_SHORT).show();
                operatorEditText.setText(operatorsList.get(adapterPosition).getName());
                bottomSheetDialogSelectOperatorCircle.dismiss();

                break;

            case R.id.planTitle_LL:

               /* planAdapterPosition = (adapterPosition);

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
                }*/

                break;

            case R.id.amount_LinearLayout:


                plan_amount_EditText.setText(planDetails.get(adapterPosition).getRs());
                viewPlansBottomSheet.dismiss();

                //  Log.e("aa", planAdapterPosition+"");
                /*if (planTitleList.get(planAdapterPosition).trim().equalsIgnoreCase("All")){
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

                viewPlansBottomSheet.dismiss();*/
                break;

            case R.id.circle_name_TV:

                circle_EditText.setText(circleList.get(adapterPosition).trim());
               // Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, circleList.get(adapterPosition).trim(), Toast.LENGTH_SHORT).show();
                bottomSheetDialogSelectCircle.dismiss();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MobileRechargeDashboardPaySprintActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
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

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MobileRechargeDashboardPaySprintActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
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
                            Intent intent = new Intent(MobileRechargeDashboardPaySprintActivity.this, LoginActivity.class);
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
                        Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(MobileRechargeDashboardPaySprintActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<GetRewardPointsResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(MobileRechargeDashboardPaySprintActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });

    }
}