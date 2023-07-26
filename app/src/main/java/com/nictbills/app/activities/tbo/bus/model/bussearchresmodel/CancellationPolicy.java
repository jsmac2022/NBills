
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancellationPolicy implements Serializable
{

    @SerializedName("CancellationCharge")
    @Expose
    private int cancellationCharge;
    @SerializedName("CancellationChargeType")
    @Expose
    private int cancellationChargeType;
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
    private final static long serialVersionUID = -5906662145707753912L;

    public int getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(int cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public CancellationPolicy withCancellationCharge(int cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
        return this;
    }

    public int getCancellationChargeType() {
        return cancellationChargeType;
    }

    public void setCancellationChargeType(int cancellationChargeType) {
        this.cancellationChargeType = cancellationChargeType;
    }

    public CancellationPolicy withCancellationChargeType(int cancellationChargeType) {
        this.cancellationChargeType = cancellationChargeType;
        return this;
    }

    public String getPolicyString() {
        return policyString;
    }

    public void setPolicyString(String policyString) {
        this.policyString = policyString;
    }

    public CancellationPolicy withPolicyString(String policyString) {
        this.policyString = policyString;
        return this;
    }

    public String getTimeBeforeDept() {
        return timeBeforeDept;
    }

    public void setTimeBeforeDept(String timeBeforeDept) {
        this.timeBeforeDept = timeBeforeDept;
    }

    public CancellationPolicy withTimeBeforeDept(String timeBeforeDept) {
        this.timeBeforeDept = timeBeforeDept;
        return this;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public CancellationPolicy withFromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public CancellationPolicy withToDate(String toDate) {
        this.toDate = toDate;
        return this;
    }

}
