
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airline implements Serializable
{

    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("AirlineName")
    @Expose
    private String airlineName;
    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("FareClass")
    @Expose
    private String fareClass;
    @SerializedName("OperatingCarrier")
    @Expose
    private String operatingCarrier;
    private final static long serialVersionUID = 207531003583659011L;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFareClass() {
        return fareClass;
    }

    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

}
