package com.nictbills.app.activities.tbo.flight.response_search_flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Origin {

    @SerializedName("Airport")
    @Expose
    private Airport airport;
    @SerializedName("DepTime")
    @Expose
    private String depTime;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

}
