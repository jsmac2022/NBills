
package com.nictbills.app.activities.tbo.bus.model.busblockresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelPolicy {

    @SerializedName("CancellationCharge")
    @Expose
    private Integer cancellationCharge;
    @SerializedName("CancellationChargeType")
    @Expose
    private Integer cancellationChargeType;
    @SerializedName("PolicyString")
    @Expose
    private String policyString;
    @SerializedName("TimeBeforeDept")
    @Expose
    private String timeBeforeDept;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;

    public Integer getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(Integer cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public Integer getCancellationChargeType() {
        return cancellationChargeType;
    }

    public void setCancellationChargeType(Integer cancellationChargeType) {
        this.cancellationChargeType = cancellationChargeType;
    }

    public String getPolicyString() {
        return policyString;
    }

    public void setPolicyString(String policyString) {
        this.policyString = policyString;
    }

    public String getTimeBeforeDept() {
        return timeBeforeDept;
    }

    public void setTimeBeforeDept(String timeBeforeDept) {
        this.timeBeforeDept = timeBeforeDept;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}
