
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MiniFareRule implements Serializable
{

    @SerializedName("JourneyPoints")
    @Expose
    private String journeyPoints;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("From")
    @Expose
    private String from;
    @SerializedName("To")
    @Expose
    private String to;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("Details")
    @Expose
    private String details;
    private final static long serialVersionUID = -7942550308187804072L;

    public String getJourneyPoints() {
        return journeyPoints;
    }

    public void setJourneyPoints(String journeyPoints) {
        this.journeyPoints = journeyPoints;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
