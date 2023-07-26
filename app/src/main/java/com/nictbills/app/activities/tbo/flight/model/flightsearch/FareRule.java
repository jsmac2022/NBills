
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FareRule implements Serializable
{

    @SerializedName("Origin")
    @Expose
    private String origin;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("Airline")
    @Expose
    private String airline;
    @SerializedName("FareBasisCode")
    @Expose
    private String fareBasisCode;
    @SerializedName("FareRuleDetail")
    @Expose
    private String fareRuleDetail;
    @SerializedName("FareRestriction")
    @Expose
    private String fareRestriction;
    @SerializedName("FareFamilyCode")
    @Expose
    private String fareFamilyCode;
    @SerializedName("FareRuleIndex")
    @Expose
    private String fareRuleIndex;
    private final static long serialVersionUID = 8509037804310930602L;

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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }

    public String getFareRuleDetail() {
        return fareRuleDetail;
    }

    public void setFareRuleDetail(String fareRuleDetail) {
        this.fareRuleDetail = fareRuleDetail;
    }

    public String getFareRestriction() {
        return fareRestriction;
    }

    public void setFareRestriction(String fareRestriction) {
        this.fareRestriction = fareRestriction;
    }

    public String getFareFamilyCode() {
        return fareFamilyCode;
    }

    public void setFareFamilyCode(String fareFamilyCode) {
        this.fareFamilyCode = fareFamilyCode;
    }

    public String getFareRuleIndex() {
        return fareRuleIndex;
    }

    public void setFareRuleIndex(String fareRuleIndex) {
        this.fareRuleIndex = fareRuleIndex;
    }

}
