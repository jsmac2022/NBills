package com.nictbills.app.activities.health_id_abdm.dto.reward_points.reward_ledger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ledger {

    @SerializedName("refno")
    @Expose
    private String refno;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("descr")
    @Expose
    private String descr;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("ttype")
    @Expose
    private String ttype;
    @SerializedName("edt")
    @Expose
    private String edt;

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getEdt() {
        return edt;
    }

    public void setEdt(String edt) {
        this.edt = edt;
    }
}
