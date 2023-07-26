package com.nictbills.app.activities.health_id_abdm.dto.transaction_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTxnList {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("pay_id")
    @Expose
    private String payId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("notes_service")
    @Expose
    private String notesService;
    @SerializedName("notes_service_id")
    @Expose
    private String notesServiceId;
    @SerializedName("notes_service_loc")
    @Expose
    private Object notesServiceLoc;
    @SerializedName("error_description")
    @Expose
    private String errorDescription;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;

    private String fee;
    private String tax;

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNotesService() {
        return notesService;
    }

    public void setNotesService(String notesService) {
        this.notesService = notesService;
    }

    public String getNotesServiceId() {
        return notesServiceId;
    }

    public void setNotesServiceId(String notesServiceId) {
        this.notesServiceId = notesServiceId;
    }

    public Object getNotesServiceLoc() {
        return notesServiceLoc;
    }

    public void setNotesServiceLoc(Object notesServiceLoc) {
        this.notesServiceLoc = notesServiceLoc;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

}
