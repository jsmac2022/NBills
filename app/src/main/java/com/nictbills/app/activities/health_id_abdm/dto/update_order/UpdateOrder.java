package com.nictbills.app.activities.health_id_abdm.dto.update_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateOrder {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("payment_entity")
    @Expose
    private List<PaymentEntity> paymentEntity = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<PaymentEntity> getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(List<PaymentEntity> paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

}
