
package com.nictbills.app.activities.tbo.flight.model.farerules;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Serializable
{

    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("FareRules")
    @Expose
    private List<FareRule> fareRules = null;
    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("PenaltyCharges")
    @Expose
    private PenaltyCharges penaltyCharges;
    private final static long serialVersionUID = 7649327531197894592L;

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public Response withError(java.lang.Error error) {
        this.error = error;
        return this;
    }

    public List<FareRule> getFareRules() {
        return fareRules;
    }

    public void setFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
    }

    public Response withFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
        return this;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Response withResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Response withTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public PenaltyCharges getPenaltyCharges() {
        return penaltyCharges;
    }

    public void setPenaltyCharges(PenaltyCharges penaltyCharges) {
        this.penaltyCharges = penaltyCharges;
    }

    public Response withPenaltyCharges(PenaltyCharges penaltyCharges) {
        this.penaltyCharges = penaltyCharges;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Response.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("fareRules");
        sb.append('=');
        sb.append(((this.fareRules == null)?"<null>":this.fareRules));
        sb.append(',');
        sb.append("responseStatus");
        sb.append('=');
        sb.append(this.responseStatus);
        sb.append(',');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null)?"<null>":this.traceId));
        sb.append(',');
        sb.append("penaltyCharges");
        sb.append('=');
        sb.append(((this.penaltyCharges == null)?"<null>":this.penaltyCharges));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
