
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nictbills.app.activities.tbo.flight.response_search_flight.Airport;

public class Origin implements Serializable
{

    @SerializedName("Airport")
    @Expose
    private Airport airport;
    @SerializedName("DepTime")
    @Expose
    private String depTime;
    private final static long serialVersionUID = -5651937718180176644L;

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
