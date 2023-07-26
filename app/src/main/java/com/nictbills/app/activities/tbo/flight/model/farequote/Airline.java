
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private final static long serialVersionUID = -8262385140890390056L;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Airline withAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Airline withAirlineName(String airlineName) {
        this.airlineName = airlineName;
        return this;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airline withFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public String getFareClass() {
        return fareClass;
    }

    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    public Airline withFareClass(String fareClass) {
        this.fareClass = fareClass;
        return this;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public Airline withOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Airline.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("airlineCode");
        sb.append('=');
        sb.append(((this.airlineCode == null)?"<null>":this.airlineCode));
        sb.append(',');
        sb.append("airlineName");
        sb.append('=');
        sb.append(((this.airlineName == null)?"<null>":this.airlineName));
        sb.append(',');
        sb.append("flightNumber");
        sb.append('=');
        sb.append(((this.flightNumber == null)?"<null>":this.flightNumber));
        sb.append(',');
        sb.append("fareClass");
        sb.append('=');
        sb.append(((this.fareClass == null)?"<null>":this.fareClass));
        sb.append(',');
        sb.append("operatingCarrier");
        sb.append('=');
        sb.append(((this.operatingCarrier == null)?"<null>":this.operatingCarrier));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
