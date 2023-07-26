package com.nictbills.app.apiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClients {

    public static final String BASE_URL = "http://192.168.5.197:8080/tbo-api/api/flight_booking/";
    public static Retrofit retrofit = null;



    public static OkHttpClient okhttp = new OkHttpClient.Builder()
                    .readTimeout(120,TimeUnit.SECONDS)
                    .writeTimeout(120,TimeUnit.SECONDS)
                    .connectTimeout(120,TimeUnit.SECONDS)
                    .build();


    public static Retrofit getInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okhttp)
                    .build();
        }
        return retrofit;
    }
}
