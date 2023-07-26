package com.nictbills.app.activities.household.utils;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.nictbills.app.activities.household.MyApplication;

import java.util.List;

public class Utility {
    public static String paymentId = "0";
    public static int paymentsucsses = 0;
    public static String tragectionID = "0";
    public static int isvarification =-1;
    public static int resedual =0;
    public static boolean changeAddress = false;



   /* public static boolean internetChack() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            return true;
        } else {
            return false;
        }
    }*/
}
