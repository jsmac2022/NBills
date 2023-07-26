package com.nictbills.app.activities.health_id_abdm.dto.update_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PaymentEntity implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("entity")
    @Expose
    private String entity;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("invoice_id")
    @Expose
    private Object invoiceId;
    @SerializedName("international")
    @Expose
    private Boolean international;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("amount_refunded")
    @Expose
    private Integer amountRefunded;
    @SerializedName("refund_status")
    @Expose
    private Object refundStatus;
    @SerializedName("captured")
    @Expose
    private Boolean captured;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("card_id")
    @Expose
    private String cardId;
    @SerializedName("bank")
    @Expose
    private Object bank;
    @SerializedName("wallet")
    @Expose
    private Object wallet;
    @SerializedName("vpa")
    @Expose
    private Object vpa;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("notes")
    @Expose
    private Notes notes;
    @SerializedName("fee")
    @Expose
    private Object fee;
    @SerializedName("tax")
    @Expose
    private Object tax;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("error_description")
    @Expose
    private String errorDescription;
    @SerializedName("error_source")
    @Expose
    private String errorSource;
    @SerializedName("error_step")
    @Expose
    private String errorStep;
    @SerializedName("error_reason")
    @Expose
    private String errorReason;
    @SerializedName("acquirer_data")
    @Expose
    private AcquirerData acquirerData;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Object getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Object invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(Integer amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public Object getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Object refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Boolean getCaptured() {
        return captured;
    }

    public void setCaptured(Boolean captured) {
        this.captured = captured;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Object getBank() {
        return bank;
    }

    public void setBank(Object bank) {
        this.bank = bank;
    }

    public Object getWallet() {
        return wallet;
    }

    public void setWallet(Object wallet) {
        this.wallet = wallet;
    }

    public Object getVpa() {
        return vpa;
    }

    public void setVpa(Object vpa) {
        this.vpa = vpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Object getFee() {
        return fee;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }

    public Object getTax() {
        return tax;
    }

    public void setTax(Object tax) {
        this.tax = tax;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getErrorStep() {
        return errorStep;
    }

    public void setErrorStep(String errorStep) {
        this.errorStep = errorStep;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public AcquirerData getAcquirerData() {
        return acquirerData;
    }

    public void setAcquirerData(AcquirerData acquirerData) {
        this.acquirerData = acquirerData;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }


}
