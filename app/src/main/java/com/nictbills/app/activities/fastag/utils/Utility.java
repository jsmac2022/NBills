package com.nictbills.app.activities.fastag.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nictbills.app.activities.fastag.MyApplication;

public class Utility {
    public static String paymentId = "0";
    public static int paymentsucsses = 0;
    public static String tragectionID = "0";
    public static int isvarification =-1;
    public static int resedual =0;
    public static String authorization;
    public static boolean changeAddress = false;
    public static SharedPreferences dawai4uShared;


    public static boolean internetChack() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            return true;
        } else {
            return false;
        }
    }

}
