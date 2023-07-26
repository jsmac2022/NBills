package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {


    /*"Origin": "DEL",
"Destination": "BOM",
        "FlightCabinClass": "1",
"PreferredDepartureTime": "2022-11-06T00: 00: 00",
"PreferredArrivalTime": "2022-11-06T00: 00: 00"*/

    @SerializedName("Origin")
    @Expose
    private String origin;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("FlightCabinClass")
    @Expose
    private String flightCabinClass;
    @SerializedName("PreferredDepartureTime")
    @Expose
    private String preferredDepartureTime;
    @SerializedName("PreferredArrivalTime")
    @Expose
    private String preferredArrivalTime;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightCabinClass() {
        return flightCabinClass;
    }

    public void setFlightCabinClass(String flightCabinClass) {
        this.flightCabinClass = flightCabinClass;
    }

    public String getPreferredDepartureTime() {
        return preferredDepartureTime;
    }

    public void setPreferredDepartureTime(String preferredDepartureTime) {
        this.preferredDepartureTime = preferredDepartureTime;
    }

    public String getPreferredArrivalTime() {
        return preferredArrivalTime;
    }

    public void setPreferredArrivalTime(String preferredArrivalTime) {
        this.preferredArrivalTime = preferredArrivalTime;
    }

}
