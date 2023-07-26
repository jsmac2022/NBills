package com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaniwalaRazarPayPaymentConfirmResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("amount_due")
    @Expose
    private Integer amountDue;
    @SerializedName("amount_paid")
    @Expose
    private Integer amountPaid;
    @SerializedName("amount")
    @Expose
    private Object amount;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("entity")
    @Expose
    private Object entity;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("attempts")
    @Expose
    private Object attempts;
    @SerializedName("offer_id")
    @Expose
    private Object offerId;
    @SerializedName("razorpay_signature")
    @Expose
    private String razorpaySignature;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("razorpay_payment_id")
    @Expose
    private String razorpayPaymentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Integer amountDue) {
        this.amountDue = amountDue;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
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

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public Object getAttempts() {
        return attempts;
    }

    public void setAttempts(Object attempts) {
        this.attempts = attempts;
    }

    public Object getOfferId() {
        return offerId;
    }

    public void setOfferId(Object offerId) {
        this.offerId = offerId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

}
