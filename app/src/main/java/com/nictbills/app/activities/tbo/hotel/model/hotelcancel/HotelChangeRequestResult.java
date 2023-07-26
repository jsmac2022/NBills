
package com.nictbills.app.activities.tbo.hotel.model.hotelcancel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelChangeRequestResult {
    @SerializedName("B2B2BStatus")
    @Expose
    private Boolean b2B2BStatus;
    @SerializedName("CancellationChargeBreakUp")
    @Expose
    private Object cancellationChargeBreakUp;
    @SerializedName("TotalServiceCharge")
    @Expose
    private Integer totalServiceCharge;
    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("ChangeRequestId")
    @Expose
    private Integer changeRequestId;
    @SerializedName("ChangeRequestStatus")
    @Expose
    private Integer changeRequestStatus;

    public Boolean getB2B2BStatus() {
        return b2B2BStatus;
    }

    public void setB2B2BStatus(Boolean b2B2BStatus) {
        this.b2B2BStatus = b2B2BStatus;
    }

    public Object getCancellationChargeBreakUp() {
        return cancellationChargeBreakUp;
    }

    public void setCancellationChargeBreakUp(Object cancellationChargeBreakUp) {
        this.cancellationChargeBreakUp = cancellationChargeBreakUp;
    }

    public Integer getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Integer totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Integer getChangeRequestId() {
        return changeRequestId;
    }

    public void setChangeRequestId(Integer changeRequestId) {
        this.changeRequestId = changeRequestId;
    }

    public Integer getChangeRequestStatus() {
        return changeRequestStatus;
    }

    public void setChangeRequestStatus(Integer changeRequestStatus) {
        this.changeRequestStatus = changeRequestStatus;
    }

}
