package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MiniFareRule {

    @SerializedName("JourneyPoints")
    @Expose
    private String journeyPoints;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("From")
    @Expose
    private Object from;
    @SerializedName("To")
    @Expose
    private Object to;
    @SerializedName("Unit")
    @Expose
    private Object unit;
    @SerializedName("Details")
    @Expose
    private String details;

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

    public Object getFrom() {
        return from;
    }

    public void setFrom(Object from) {
        this.from = from;
    }

    public Object getTo() {
        return to;
    }

    public void setTo(Object to) {
        this.to = to;
    }

    public Object getUnit() {
        return unit;
    }

    public void setUnit(Object unit) {
        this.unit = unit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
