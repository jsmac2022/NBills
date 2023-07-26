package com.nictbills.app.activities.fastag.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.nictbills.app.activities.fastag.MyApplication;
import com.nictbills.app.activities.fastag.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetResult {
    public static MyListener myListener;

    public void callForLogin(Call<JsonObject> call, String callno) {

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e("message", " : " + response.message());
                    Log.e("body", " : " + response.body());
                    Log.e("callno", " : " + callno);
                    myListener.callback(response.body(), callno);

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    myListener.callback(null, callno);

                    call.cancel();
                    t.printStackTrace();
                }
            });

    }
    public interface MyListener {
        // you can define any parameter as per your requirement
        public void callback(JsonObject result, String callNo);
    }
    public void setMyListener(MyListener myListener) {
        GetResult.myListener = myListener;
    }
}
