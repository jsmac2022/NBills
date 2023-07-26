
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusSearchResult implements Serializable
{

    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("Origin")
    @Expose
    private String origin;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("BusResults")
    @Expose
    private List<BusResult> busResults = null;
    private final static long serialVersionUID = 6193911703944680717L;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public BusSearchResult withResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public BusSearchResult withError(Error error) {
        this.error = error;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BusSearchResult withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BusSearchResult withOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public BusSearchResult withTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public List<BusResult> getBusResults() {
        return busResults;
    }

    public void setBusResults(List<BusResult> busResults) {
        this.busResults = busResults;
    }

    public BusSearchResult withBusResults(List<BusResult> busResults) {
        this.busResults = busResults;
        return this;
    }

}
