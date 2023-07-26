
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private final static long serialVersionUID = -8184632889450937911L;

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

    public String getFareFamilyCode() {
        return fareFamilyCode;
    }

    public void setFareFamilyCode(String fareFamilyCode) {
        this.fareFamilyCode = fareFamilyCode;
    }

    public FareRule withFareFamilyCode(String fareFamilyCode) {
        this.fareFamilyCode = fareFamilyCode;
        return this;
    }

    public String getFareRuleIndex() {
        return fareRuleIndex;
    }

    public void setFareRuleIndex(String fareRuleIndex) {
        this.fareRuleIndex = fareRuleIndex;
    }

    public FareRule withFareRuleIndex(String fareRuleIndex) {
        this.fareRuleIndex = fareRuleIndex;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FareRule.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("origin");
        sb.append('=');
        sb.append(((this.origin == null)?"<null>":this.origin));
        sb.append(',');
        sb.append("destination");
        sb.append('=');
        sb.append(((this.destination == null)?"<null>":this.destination));
        sb.append(',');
        sb.append("airline");
        sb.append('=');
        sb.append(((this.airline == null)?"<null>":this.airline));
        sb.append(',');
        sb.append("fareBasisCode");
        sb.append('=');
        sb.append(((this.fareBasisCode == null)?"<null>":this.fareBasisCode));
        sb.append(',');
        sb.append("fareRuleDetail");
        sb.append('=');
        sb.append(((this.fareRuleDetail == null)?"<null>":this.fareRuleDetail));
        sb.append(',');
        sb.append("fareRestriction");
        sb.append('=');
        sb.append(((this.fareRestriction == null)?"<null>":this.fareRestriction));
        sb.append(',');
        sb.append("fareFamilyCode");
        sb.append('=');
        sb.append(((this.fareFamilyCode == null)?"<null>":this.fareFamilyCode));
        sb.append(',');
        sb.append("fareRuleIndex");
        sb.append('=');
        sb.append(((this.fareRuleIndex == null)?"<null>":this.fareRuleIndex));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
