
package com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Serializable
{

    @SerializedName("Meal")
    @Expose
    private List<Meal> meal = null;
    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("SeatDynamic")
    @Expose
    private List<SeatDynamic> seatDynamic = null;
    private final static long serialVersionUID = -1969753095404705878L;

    public List<Meal> getMeal() {
        return meal;
    }

    public void setMeal(List<Meal> meal) {
        this.meal = meal;
    }

    public Response withMeal(List<Meal> meal) {
        this.meal = meal;
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

    public List<SeatDynamic> getSeatDynamic() {
        return seatDynamic;
    }

    public void setSeatDynamic(List<SeatDynamic> seatDynamic) {
        this.seatDynamic = seatDynamic;
    }

    public Response withSeatDynamic(List<SeatDynamic> seatDynamic) {
        this.seatDynamic = seatDynamic;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Response.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("meal");
        sb.append('=');
        sb.append(((this.meal == null)?"<null>":this.meal));
        sb.append(',');
        sb.append("responseStatus");
        sb.append('=');
        sb.append(this.responseStatus);
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null)?"<null>":this.traceId));
        sb.append(',');
        sb.append("seatDynamic");
        sb.append('=');
        sb.append(((this.seatDynamic == null)?"<null>":this.seatDynamic));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
