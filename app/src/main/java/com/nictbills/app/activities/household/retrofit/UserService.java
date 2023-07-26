package com.nictbills.app.activities.household.retrofit;


import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UserService {

    @GET(APIClient.APPEND_URL + "allservices")
    Call<JsonObject> getCategory();

    @GET
    Call<JsonObject> sSubList(@Url String url);

    @GET
    Call<JsonObject> sChildList(@Url String url);

    @GET
    Call<JsonObject> getSearch(@Url String url);




}
