package com.nictbills.app.activities.health_id_abdm.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrderCreateResDto {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("entity")
    @Expose
    private String entity;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("amount_paid")
    @Expose
    private Integer amountPaid;
    @SerializedName("amount_due")
    @Expose
    private Integer amountDue;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("attempts")
    @Expose
    private Integer attempts;
    @SerializedName("notes")
    @Expose
    private Notes notes;
    @SerializedName("created_at")
    @Expose
    private Integer createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Integer amountDue) {
        this.amountDue = amountDue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}
