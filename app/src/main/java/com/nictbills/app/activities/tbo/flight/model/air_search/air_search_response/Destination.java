package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destination {

    @SerializedName("Airport")
    @Expose
    private Airport__1 airport;
    @SerializedName("ArrTime")
    @Expose
    private String arrTime;

    public Airport__1 getAirport() {
        return airport;
    }

    public void setAirport(Airport__1 airport) {
        this.airport = airport;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }


}
