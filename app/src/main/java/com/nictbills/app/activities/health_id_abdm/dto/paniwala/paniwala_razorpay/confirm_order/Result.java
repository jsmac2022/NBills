package com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.confirm_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("amount_paid")
    @Expose
    private Integer amountPaid;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;
    @SerializedName("amount_due")
    @Expose
    private Integer amountDue;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("razorpay_payment_id")
    @Expose
    private String razorpayPaymentId;

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }


}
