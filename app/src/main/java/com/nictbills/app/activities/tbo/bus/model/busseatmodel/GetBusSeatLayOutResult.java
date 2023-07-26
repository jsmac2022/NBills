
package com.nictbills.app.activities.tbo.bus.model.busseatmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBusSeatLayOutResult {

    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("SeatLayoutDetails")
    @Expose
    private SeatLayoutDetails seatLayoutDetails;

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

    public SeatLayoutDetails getSeatLayoutDetails() {
        return seatLayoutDetails;
    }

    public void setSeatLayoutDetails(SeatLayoutDetails seatLayoutDetails) {
        this.seatLayoutDetails = seatLayoutDetails;
    }

}
