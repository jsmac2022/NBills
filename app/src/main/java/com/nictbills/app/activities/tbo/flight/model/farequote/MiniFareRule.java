
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private final static long serialVersionUID = 1571761970546035007L;

    public String getJourneyPoints() {
        return journeyPoints;
    }

    public void setJourneyPoints(String journeyPoints) {
        this.journeyPoints = journeyPoints;
    }

    public MiniFareRule withJourneyPoints(String journeyPoints) {
        this.journeyPoints = journeyPoints;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MiniFareRule withType(String type) {
        this.type = type;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public MiniFareRule withFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MiniFareRule withTo(String to) {
        this.to = to;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public MiniFareRule withUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public MiniFareRule withDetails(String details) {
        this.details = details;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MiniFareRule.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("journeyPoints");
        sb.append('=');
        sb.append(((this.journeyPoints == null)?"<null>":this.journeyPoints));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("from");
        sb.append('=');
        sb.append(((this.from == null)?"<null>":this.from));
        sb.append(',');
        sb.append("to");
        sb.append('=');
        sb.append(((this.to == null)?"<null>":this.to));
        sb.append(',');
        sb.append("unit");
        sb.append('=');
        sb.append(((this.unit == null)?"<null>":this.unit));
        sb.append(',');
        sb.append("details");
        sb.append('=');
        sb.append(((this.details == null)?"<null>":this.details));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
