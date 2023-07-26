package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainResponce {

    @SerializedName("Response")
    @Expose
    private Responses response;

    public Responses getResponse() {
        return response;
    }

    public void setResponse(Responses response) {
        this.response = response;
    }

}
