package com.nictbills.app.activities.fastag.Model;

import com.google.gson.annotations.SerializedName;

public class FasTagData {
    @SerializedName("ftr_id")
    private String ftr_id;
    @SerializedName("request_code")
    private String request_code;
    @SerializedName("addr_name")
    private String addr_name;
    @SerializedName("addr_email")
    private String addr_email;
    @SerializedName("addr_pincode")
    private String addr_pincode;
    @SerializedName("addr_state")
    private String addr_state;
    @SerializedName("addr_city")
    private String addr_city;
    @SerializedName("address")
    private String address;
    @SerializedName("addr_alt_mobile")
    private String addr_alt_mobile;
    @SerializedName("req_status")
    private String req_status;
    @SerializedName("created_dt")
    private String created_dt;
    @SerializedName("sector")
    private String sector;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public String getFtr_id() {
        return ftr_id;
    }

    public void setFtr_id(String ftr_id) {
        this.ftr_id = ftr_id;
    }

    public String getRequest_code() {
        return request_code;
    }

    public void setRequest_code(String request_code) {
        this.request_code = request_code;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getAddr_email() {
        return addr_email;
    }

    public void setAddr_email(String addr_email) {
        this.addr_email = addr_email;
    }

    public String getAddr_pincode() {
        return addr_pincode;
    }

    public void setAddr_pincode(String addr_pincode) {
        this.addr_pincode = addr_pincode;
    }

    public String getAddr_state() {
        return addr_state;
    }

    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddr_alt_mobile() {
        return addr_alt_mobile;
    }

    public void setAddr_alt_mobile(String addr_alt_mobile) {
        this.addr_alt_mobile = addr_alt_mobile;
    }

    public String getReq_status() {
        return req_status;
    }

    public void setReq_status(String req_status) {
        this.req_status = req_status;
    }
}
