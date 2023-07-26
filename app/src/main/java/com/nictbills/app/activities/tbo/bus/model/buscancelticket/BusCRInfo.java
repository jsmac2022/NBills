
package com.nictbills.app.activities.tbo.bus.model.buscancelticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusCRInfo {

    @SerializedName("ChangeRequestId")
    @Expose
    private Integer changeRequestId;
    @SerializedName("CreditNoteNo")
    @Expose
    private String creditNoteNo;
    @SerializedName("ChangeRequestStatus")
    @Expose
    private Integer changeRequestStatus;
    @SerializedName("CreditNoteCreatedOn")
    @Expose
    private String creditNoteCreatedOn;
    @SerializedName("TotalPrice")
    @Expose
    private Double totalPrice;
    @SerializedName("RefundedAmount")
    @Expose
    private Double refundedAmount;
    @SerializedName("CancellationCharge")
    @Expose
    private Double cancellationCharge;
    @SerializedName("TotalServiceCharge")
    @Expose
    private Double totalServiceCharge;
    @SerializedName("TotalGSTAmount")
    @Expose
    private Double totalGSTAmount;
    @SerializedName("GST")
    @Expose
    private Gst gst;

    public Integer getChangeRequestId() {
        return changeRequestId;
    }

    public void setChangeRequestId(Integer changeRequestId) {
        this.changeRequestId = changeRequestId;
    }

    public String getCreditNoteNo() {
        return creditNoteNo;
    }

    public void setCreditNoteNo(String creditNoteNo) {
        this.creditNoteNo = creditNoteNo;
    }

    public Integer getChangeRequestStatus() {
        return changeRequestStatus;
    }

    public void setChangeRequestStatus(Integer changeRequestStatus) {
        this.changeRequestStatus = changeRequestStatus;
    }

    public String getCreditNoteCreatedOn() {
        return creditNoteCreatedOn;
    }

    public void setCreditNoteCreatedOn(String creditNoteCreatedOn) {
        this.creditNoteCreatedOn = creditNoteCreatedOn;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Double refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Double getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(Double cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public Double getTotalServiceCharge() {
        return totalServiceCharge;
    }

    public void setTotalServiceCharge(Double totalServiceCharge) {
        this.totalServiceCharge = totalServiceCharge;
    }

    public Double getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(Double totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public Gst getGst() {
        return gst;
    }

    public void setGst(Gst gst) {
        this.gst = gst;
    }

}
