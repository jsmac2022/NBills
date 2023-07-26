package com.nictbills.app.activities.health_id_abdm.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IvrsNoList {

    @SerializedName("ivrsNo")
    @Expose
    private String ivrsNo;
    @SerializedName("loc_cd")
    @Expose
    private String locCd;
    @SerializedName("gr_no")
    @Expose
    private String grNo;
    @SerializedName("rd_no")
    @Expose
    private String rdNo;
    @SerializedName("cons_name")
    @Expose
    private String consName;
    @SerializedName("cons_add")
    @Expose
    private String consAdd;
    @SerializedName("circle")
    @Expose
    private String circle;
    @SerializedName("net_bill")
    @Expose
    private String netBill;
    @SerializedName("net_incl_surchage")
    @Expose
    private String netInclSurchage;
    @SerializedName("due_date")
    @Expose
    private String dueDate;

    private String enc_data;
    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEnc_data() {
        return enc_data;
    }

    public void setEnc_data(String enc_data) {
        this.enc_data = enc_data;
    }

    public String getIvrsNo() {
        return ivrsNo;
    }

    public void setIvrsNo(String ivrsNo) {
        this.ivrsNo = ivrsNo;
    }

    public String getLocCd() {
        return locCd;
    }

    public void setLocCd(String locCd) {
        this.locCd = locCd;
    }

    public String getGrNo() {
        return grNo;
    }

    public void setGrNo(String grNo) {
        this.grNo = grNo;
    }

    public String getRdNo() {
        return rdNo;
    }

    public void setRdNo(String rdNo) {
        this.rdNo = rdNo;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getConsAdd() {
        return consAdd;
    }

    public void setConsAdd(String consAdd) {
        this.consAdd = consAdd;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getNetBill() {
        return netBill;
    }

    public void setNetBill(String netBill) {
        this.netBill = netBill;
    }

    public String getNetInclSurchage() {
        return netInclSurchage;
    }

    public void setNetInclSurchage(String netInclSurchage) {
        this.netInclSurchage = netInclSurchage;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
