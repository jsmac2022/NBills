package com.nictbills.app.activities.household.activity

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar
import com.nictbills.app.Constant
import com.nictbills.app.R
import com.nictbills.app.activities.BaseActivity
import com.nictbills.app.activities.LoginActivity
import com.nictbills.app.activities.UserProfileActivity
import com.nictbills.app.adapter.house_hold_services.HouseHoldVendorProfileAdapter
import com.nictbills.app.apiInterface.RetrofitInterface
import com.nictbills.app.apiclient.RetrofitClient
import com.nictbills.app.activities.health_id_abdm.dto.house_hold.HouseHoldVendorResponse
import com.nictbills.app.activities.health_id_abdm.dto.house_hold.Result
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaCreateOrderRequest
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.PaniwalaVendorProfileRequest
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.get_user_profile.GetUserProfile
import com.nictbills.app.interfaces.OnClickRecyclerViewInterface
import com.nictbills.app.utils.Util
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.security.GeneralSecurityException

class HouseHoldVendorActivity : BaseActivity(), OnClickRecyclerViewInterface  {

    lateinit var retroFitInterface: RetrofitInterface;
    lateinit var credential: String; lateinit var mainServiceCode: String; lateinit var subServiceCode: String; lateinit var reg_mobile: String;
    lateinit var msc: String; lateinit var ssc: String
    lateinit var no_vendor_available_LL: LinearLayout; lateinit var vendor_available_LL:LinearLayout
    lateinit var vendorProfileResult:List<Result>
    lateinit var houseHold_vendor_RV:RecyclerView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var paymentStatusDialog: Dialog
     lateinit var backArrow_IMG: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_hold_vendor)

        no_vendor_available_LL = findViewById(R.id.no_vendor_available_LL)
        vendor_available_LL = findViewById(R.id.vendor_available_LL)
        houseHold_vendor_RV = findViewById(R.id.houseHold_vendor_RV)
        backArrow_IMG = findViewById(R.id.backArrow_IMG)

        var bundle :Bundle ?=intent.extras
        msc = bundle!!.getString("msc").toString() // 1
        ssc = bundle!!.getString("ssc").toString() // 1

       // val sharedPreferences: SharedPreferences = this.getSharedPreferences("BillsDATA",Context.MODE_PRIVATE)


//        sharedPreferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
        try {
            sharedPreferences =
                Util.getSharedPreferenceInstance(this, "BillsDATA", Util.getMasterKey())
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }


        houseHold_vendor_RV.layoutManager = LinearLayoutManager(this)

         credential = sharedPreferences.getString("cred_1","").toString()
         reg_mobile = sharedPreferences.getString("cred_2","").toString()

        getUserProfile();

        backArrow_IMG.setOnClickListener{
            onBackPressed()
        }


    }

    private fun getUserProfile() {
        progressDialogShow()
        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL).create(
            RetrofitInterface::class.java
        )
        val call: Call<GetUserProfile> = retroFitInterface.getUserProfile(credential)
        call.enqueue(object : Callback<GetUserProfile?> {
            override fun onResponse(
                call: Call<GetUserProfile?>,
                response: Response<GetUserProfile?>
            ) {
                val body = response.body()
                if (response.code() == 200) {
                    if (body!!.found.equals("yes", ignoreCase = true)) {

                        var paniwalaVendorProfileRequest=
                            PaniwalaVendorProfileRequest()
                        paniwalaVendorProfileRequest.pincode=body.userProfile.pincode
                        paniwalaVendorProfileRequest.msc= msc
                        paniwalaVendorProfileRequest.ssc= ssc
                        paniwalaVendorProfileRequest.contact=reg_mobile
                        getHouseHoldVendorList(paniwalaVendorProfileRequest)

                        /*  val intent = Intent(this@HouseHoldVendorActivity, SelectWaterTypeCategoryActivity::class.java)
                          intent.putExtra("MAIN_SERVICE_ID", mainServiceCode)
                          intent.putExtra("SUB_SERVICE_ID", subServiceCode)
                          intent.putExtra("PINCODE", body.userProfile.pincode)
                          startActivity(intent)*/
                    } else {
                        progressDialogDismiss()
                        val intent = Intent(
                            this@HouseHoldVendorActivity,
                            UserProfileActivity::class.java
                        )
                        intent.putExtra("FLAG", "HOUSEHOLD")
                        startActivity(intent)
                    }
                } else if (response.code() == 401) {
                    progressDialogDismiss()
                    val sweetAlertDialog = SweetAlertDialog(
                        this@HouseHoldVendorActivity,
                        SweetAlertDialog.CUSTOM_IMAGE_TYPE
                    )
                    sweetAlertDialog.contentText = getString(R.string.unable_to_connect_to_server)
                    sweetAlertDialog.confirmText = getString(R.string.login_again)
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo)
                    sweetAlertDialog.setCanceledOnTouchOutside(false)
                    sweetAlertDialog.setConfirmClickListener { sDialog ->
                        val preferences =
                            getSharedPreferences("BillsDATA", MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.clear()
                        editor.apply()
                        val intent =
                            Intent(this@HouseHoldVendorActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        sDialog.dismissWithAnimation()
                    }
                        .show()
                } else if (response.code() == 500) {
                    progressDialogDismiss()
                    try {
                        val errorBody = response.errorBody()!!.string()
                        val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                        jsonObject.getString("error")
                        Log.e("error", jsonObject.getString("error"))
                        Toast.makeText(
                            this@HouseHoldVendorActivity,
                            jsonObject.getString("error"),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@HouseHoldVendorActivity,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@HouseHoldVendorActivity,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else if (response.code() == 400) {
                    progressDialogDismiss()
                    Toast.makeText(
                        this@HouseHoldVendorActivity,
                        getString(R.string.something_went_wrong_please_try_again_later),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<GetUserProfile?>, t: Throwable) {
                progressDialogDismiss()
                Toast.makeText(
                    this@HouseHoldVendorActivity,
                    getString(R.string.something_went_wrong_please_try_again_later),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    private fun setAdapter(payStatus: String) {
        val adapter = HouseHoldVendorProfileAdapter(this@HouseHoldVendorActivity,
            vendorProfileResult, payStatus, this)
        houseHold_vendor_RV.adapter= adapter
    }

    private fun getHouseHoldVendorList(paniwalaVendorProfileRequest: PaniwalaVendorProfileRequest) {
        progressDialogShow()
        retroFitInterface = RetrofitClient.getClient(Constant.HOUSEHOLD_URL).create(
            RetrofitInterface::class.java
        )
        val call = retroFitInterface.getHouseHoldVendorProfileList(paniwalaVendorProfileRequest)
        call.enqueue(object : Callback<HouseHoldVendorResponse?> {
            override fun onResponse(
                call: Call<HouseHoldVendorResponse?>,
                response: Response<HouseHoldVendorResponse?>
            ) {
                progressDialogDismiss()
                val body = response.body()
                if (response.code() == 200) {
                    if (body!!.result == null) {

                        no_vendor_available_LL.setVisibility(View.VISIBLE)
                        vendor_available_LL.setVisibility(View.GONE)
                        //  no_reward_history_Linear.setVisibility(View.VISIBLE);
                        //  reward_history_Linear.setVisibility(View.GONE);
                    } else {

                        //  no_reward_history_Linear.setVisibility(View.GONE);
                        //  reward_history_Linear.setVisibility(View.VISIBLE);
                        no_vendor_available_LL.setVisibility(View.GONE)
                        vendor_available_LL.setVisibility(View.VISIBLE)
                        vendorProfileResult = body.result
                        setAdapter(body.payStatus)
                    }
                } else if (response.code() == 401) {
                    val sweetAlertDialog = SweetAlertDialog(
                        this@HouseHoldVendorActivity,
                        SweetAlertDialog.CUSTOM_IMAGE_TYPE
                    )
                    sweetAlertDialog.contentText = getString(R.string.unable_to_connect_to_server)
                    sweetAlertDialog.confirmText = getString(R.string.login_again)
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo)
                    sweetAlertDialog.setCanceledOnTouchOutside(false)
                    sweetAlertDialog.setConfirmClickListener { sDialog ->
                        val preferences =
                            getSharedPreferences("BillsDATA", MODE_PRIVATE)
                        val editor = preferences.edit()
                        editor.clear()
                        editor.apply()
                        val intent =
                            Intent(this@HouseHoldVendorActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        sDialog.dismissWithAnimation()
                    }
                        .show()
                } else if (response.code() == 500) {
                    try {
                        val errorBody = response.errorBody()!!.string()
                        val jsonObject = JSONObject(errorBody.trim { it <= ' ' })
                        jsonObject.getString("error")
                        Log.e("error", jsonObject.getString("error"))
                        Toast.makeText(
                            this@HouseHoldVendorActivity,
                            jsonObject.getString("error"),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@HouseHoldVendorActivity,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@HouseHoldVendorActivity,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(
                        this@HouseHoldVendorActivity,
                        getString(R.string.something_went_wrong_please_try_again_later),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<HouseHoldVendorResponse?>, t: Throwable) {
                progressDialogDismiss()
                Toast.makeText(
                    this@HouseHoldVendorActivity,
                    getString(R.string.something_went_wrong_please_try_again_later),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onListItem(view: View?, adapterPosition: Int) {

        when (view!!.id) {R.id.vendor_house_hold_more_details_LL -> {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                var connected = false
                val connectivityManager =
                    getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
                ) {
                    //we are connected to a network
                    connected = true
                    offerDialog()


/*
                            PaniwalaCreateOrderRequest paniwalaCreateOrderRequest  = new PaniwalaCreateOrderRequest();
                            paniwalaCreateOrderRequest.setAmount("30");
                            paniwalaCreateOrderRequest.setContact(reg_mobile);
                            createOrderPaniwalaPackage( paniwalaCreateOrderRequest);*/
                } else {
                    connected = false
                    val snackbar = Snackbar
                        .make(
                            view!!,
                            getString(R.string.no_internet_connection),
                            Snackbar.LENGTH_LONG
                        )
                        .setAction(getString(R.string.retry)) { }

// Changing message text color
                    snackbar.setActionTextColor(Color.RED)

// Changing action button text color
                    val sbView = snackbar.view
                    snackbar.show()
                }
            }
            R.id.call_vendor_LL -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + vendorProfileResult[adapterPosition].contact)
                startActivity(intent)
            }
        }

    }

    private fun offerDialog() {
        paymentStatusDialog = Dialog(this@HouseHoldVendorActivity)
        paymentStatusDialog.setContentView(R.layout.paniwala_package_info)
        paymentStatusDialog.getWindow()?.setBackgroundDrawableResource(android.R.color.transparent)
        paymentStatusDialog.setCancelable(false)
        val pay_BTN: Button = paymentStatusDialog.findViewById<Button>(R.id.pay_BTN)
        val cancel_button: Button = paymentStatusDialog.findViewById<Button>(R.id.cancel_button)
        // email_EditText = confirmDialog.findViewById(R.id.email_EditText);
        pay_BTN.setOnClickListener { v ->
            paymentStatusDialog.dismiss()
            val imm =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            var connected = false
            val connectivityManager =
                getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
            ) {
                //we are connected to a network
                connected = true
                val paniwalaCreateOrderRequest =
                    PaniwalaCreateOrderRequest()
                paniwalaCreateOrderRequest.amount = "30"
                paniwalaCreateOrderRequest.contact = reg_mobile
               // createOrderPaniwalaPackage(paniwalaCreateOrderRequest)
            } else {
                connected = false
                val snackbar = Snackbar
                    .make(v, getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry)) { }

// Changing message text color
                snackbar.setActionTextColor(Color.RED)

// Changing action button text color
                val sbView = snackbar.view
                snackbar.show()
            }
        }
        cancel_button.setOnClickListener { paymentStatusDialog.dismiss() }
        paymentStatusDialog.show()
    }

}