package com.nictbills.app.apiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String base_URL = "";

    public static Retrofit getClient(String baseURL) {
        base_URL = baseURL;

//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .build();
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.readTimeout(180, TimeUnit.SECONDS)
//                .connectTimeout(180, TimeUnit.SECONDS);
//
//        retrofit = new Retrofit.Builder()
//               // .baseUrl("http://103.9.13.166:8383/b2c_uatapi/")
//                .baseUrl(base_URL)
//               // .baseUrl("http://192.168.5.115:8080/nictbills_b2c_api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
        .addInterceptor(new CurlLoggerInterceptor());
        httpClient.addInterceptor(logging);
        httpClient.readTimeout(240, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .connectTimeout(240, TimeUnit.SECONDS);

//            httpClient.networkInterceptors().add(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request.Builder requestBuilder = chain.request().newBuilder();
//                    requestBuilder.header("Content-Type", "application/json");
//                    if (pref.getLoginData()!=null){
//                       // requestBuilder.addHeader(ApiKeys.API_TOKEN,pref.getLoginData().getApiToken());
//                        requestBuilder.addHeader(ApiKeys.CUSTOMER_ID, String.valueOf(pref.getLoginData().getId()));
//                    }
//                    return chain.proceed(requestBuilder.build());
//                }
//            });

        httpClient.addInterceptor(logging);
        retrofit = new Retrofit.Builder()
                .baseUrl(base_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        return retrofit;
    }

}
