
package com.nictbills.app.activities.tbo.flight.model.farerules;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FareRule implements Serializable
{

    @SerializedName("Airline")
    @Expose
    private String airline;
    @SerializedName("DepartureTime")
    @Expose
    private String departureTime;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("FareBasisCode")
    @Expose
    private String fareBasisCode;
    @SerializedName("FareRestriction")
    @Expose
    private String fareRestriction;
    @SerializedName("FareRuleDetail")
    @Expose
    private String fareRuleDetail;
    @SerializedName("FlightId")
    @Expose
    private int flightId;
    @SerializedName("Origin")
    @Expose
    private String origin;
    @SerializedName("ReturnDate")
    @Expose
    private String returnDate;
    private final static long serialVersionUID = 5324902581454616946L;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public FareRule withAirline(String airline) {
        this.airline = airline;
        return this;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public FareRule withDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public FareRule withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }

    public FareRule withFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
        return this;
    }

    public String getFareRestriction() {
        return fareRestriction;
    }

    public void setFareRestriction(String fareRestriction) {
        this.fareRestriction = fareRestriction;
    }

    public FareRule withFareRestriction(String fareRestriction) {
        this.fareRestriction = fareRestriction;
        return this;
    }

    public String getFareRuleDetail() {
        return fareRuleDetail;
    }

    public void setFareRuleDetail(String fareRuleDetail) {
        this.fareRuleDetail = fareRuleDetail;
    }

    public FareRule withFareRuleDetail(String fareRuleDetail) {
        this.fareRuleDetail = fareRuleDetail;
        return this;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public FareRule withFlightId(int flightId) {
        this.flightId = flightId;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public FareRule withOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public FareRule withReturnDate(String returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FareRule.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("airline");
        sb.append('=');
        sb.append(((this.airline == null)?"<null>":this.airline));
        sb.append(',');
        sb.append("departureTime");
        sb.append('=');
        sb.append(((this.departureTime == null)?"<null>":this.departureTime));
        sb.append(',');
        sb.append("destination");
        sb.append('=');
        sb.append(((this.destination == null)?"<null>":this.destination));
        sb.append(',');
        sb.append("fareBasisCode");
        sb.append('=');
        sb.append(((this.fareBasisCode == null)?"<null>":this.fareBasisCode));
        sb.append(',');
        sb.append("fareRestriction");
        sb.append('=');
        sb.append(((this.fareRestriction == null)?"<null>":this.fareRestriction));
        sb.append(',');
        sb.append("fareRuleDetail");
        sb.append('=');
        sb.append(((this.fareRuleDetail == null)?"<null>":this.fareRuleDetail));
        sb.append(',');
        sb.append("flightId");
        sb.append('=');
        sb.append(this.flightId);
        sb.append(',');
        sb.append("origin");
        sb.append('=');
        sb.append(((this.origin == null)?"<null>":this.origin));
        sb.append(',');
        sb.append("returnDate");
        sb.append('=');
        sb.append(((this.returnDate == null)?"<null>":this.returnDate));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
