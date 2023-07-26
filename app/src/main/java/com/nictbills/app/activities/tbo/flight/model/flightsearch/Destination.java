
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nictbills.app.activities.tbo.flight.response_search_flight.Airport__1;

public class Destination implements Serializable
{

    @SerializedName("Airport")
    @Expose
    private Airport__1 airport;
    @SerializedName("ArrTime")
    @Expose
    private String arrTime;
    private final static long serialVersionUID = -5454430859149249012L;

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
