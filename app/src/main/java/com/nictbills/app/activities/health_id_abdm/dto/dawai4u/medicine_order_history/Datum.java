package com.nictbills.app.activities.health_id_abdm.dto.dawai4u.medicine_order_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("addr_id")
    @Expose
    private String addrId;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("store_mobile_no")
    @Expose
    private String storeMobileNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("prescription")
    @Expose
    private String prescription;
    @SerializedName("file_extension")
    @Expose
    private Object fileExtension;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("req_status")
    @Expose
    private String reqStatus;
    @SerializedName("addr_name")
    @Expose
    private String addrName;
    @SerializedName("addr_email")
    @Expose
    private String addrEmail;
    @SerializedName("addr_pincode")
    @Expose
    private String addrPincode;
    @SerializedName("addr_state")
    @Expose
    private String addrState;
    @SerializedName("addr_city")
    @Expose
    private String addrCity;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addr_alt_mobile")
    @Expose
    private String addrAltMobile;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("created_dt")
    @Expose
    private String createdDt;
    @SerializedName("modify_dt")
    @Expose
    private Object modifyDt;

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreMobileNo() {
        return storeMobileNo;
    }

    public void setStoreMobileNo(String storeMobileNo) {
        this.storeMobileNo = storeMobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Object getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(Object fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public String getAddrEmail() {
        return addrEmail;
    }

    public void setAddrEmail(String addrEmail) {
        this.addrEmail = addrEmail;
    }

    public String getAddrPincode() {
        return addrPincode;
    }

    public void setAddrPincode(String addrPincode) {
        this.addrPincode = addrPincode;
    }

    public String getAddrState() {
        return addrState;
    }

    public void setAddrState(String addrState) {
        this.addrState = addrState;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddrAltMobile() {
        return addrAltMobile;
    }

    public void setAddrAltMobile(String addrAltMobile) {
        this.addrAltMobile = addrAltMobile;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public Object getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(Object modifyDt) {
        this.modifyDt = modifyDt;
    }


}
