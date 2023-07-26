package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Responses {

    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("Origin")
    @Expose
    private String origin;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("Results")
    @Expose
    private List<List<Result>> results = null;

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

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

    public List<List<Result>> getResults() {
        return results;
    }

    public void setResults(List<List<Result>> results) {
        this.results = results;
    }

}
