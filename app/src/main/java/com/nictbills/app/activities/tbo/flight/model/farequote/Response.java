
package com.nictbills.app.activities.tbo.flight.model.farequote;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Serializable
{

    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("FlightDetailChangeInfo")
    @Expose
    private Object flightDetailChangeInfo;
    @SerializedName("IsPriceChanged")
    @Expose
    private boolean isPriceChanged;
    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;



    @SerializedName("passenger_id")
    @Expose
    private int passenger_id;

    @SerializedName("Results")
    @Expose
    private Results results;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    private final static long serialVersionUID = 744125585005169891L;

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

    public Object getFlightDetailChangeInfo() {
        return flightDetailChangeInfo;
    }

    public void setFlightDetailChangeInfo(Object flightDetailChangeInfo) {
        this.flightDetailChangeInfo = flightDetailChangeInfo;
    }

    public Response withFlightDetailChangeInfo(Object flightDetailChangeInfo) {
        this.flightDetailChangeInfo = flightDetailChangeInfo;
        return this;
    }

    public boolean isIsPriceChanged() {
        return isPriceChanged;
    }

    public void setIsPriceChanged(boolean isPriceChanged) {
        this.isPriceChanged = isPriceChanged;
    }

    public Response withIsPriceChanged(boolean isPriceChanged) {
        this.isPriceChanged = isPriceChanged;
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

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }
    public Response withpassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
        return this;
    }
//    passenger_id

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Response withResults(Results results) {
        this.results = results;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Response.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("flightDetailChangeInfo");
        sb.append('=');
        sb.append(((this.flightDetailChangeInfo == null)?"<null>":this.flightDetailChangeInfo));
        sb.append(',');
        sb.append("isPriceChanged");
        sb.append('=');
        sb.append(this.isPriceChanged);
        sb.append(',');
        sb.append("responseStatus");
        sb.append('=');
        sb.append(this.responseStatus);
        sb.append(',');
        sb.append("results");
        sb.append('=');
        sb.append(((this.results == null)?"<null>":this.results));
        sb.append(',');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null)?"<null>":this.traceId));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
