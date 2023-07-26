package com.nictbills.app.activities.health_id_abdm.dto.dawai4u.get_shops;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("owner_mobile_no")
    @Expose
    private String ownerMobileNo;
    @SerializedName("store_mobile_no")
    @Expose
    private String storeMobileNo;
    @SerializedName("license_no")
    @Expose
    private String licenseNo;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("role")
    @Expose
    private Object role;
    @SerializedName("license_id_front")
    @Expose
    private String licenseIdFront;
    @SerializedName("license_id_back")
    @Expose
    private String licenseIdBack;
    @SerializedName("pin_code")
    @Expose
    private String pinCode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("pay_status")
    @Expose
    private String payStatus;
    @SerializedName("pay_amount")
    @Expose
    private String payAmount;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("created_dt")
    @Expose
    private String createdDt;
    @SerializedName("modify_dt")
    @Expose
    private Object modifyDt;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobileNo() {
        return ownerMobileNo;
    }

    public void setOwnerMobileNo(String ownerMobileNo) {
        this.ownerMobileNo = ownerMobileNo;
    }

    public String getStoreMobileNo() {
        return storeMobileNo;
    }

    public void setStoreMobileNo(String storeMobileNo) {
        this.storeMobileNo = storeMobileNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public String getLicenseIdFront() {
        return licenseIdFront;
    }

    public void setLicenseIdFront(String licenseIdFront) {
        this.licenseIdFront = licenseIdFront;
    }

    public String getLicenseIdBack() {
        return licenseIdBack;
    }

    public void setLicenseIdBack(String licenseIdBack) {
        this.licenseIdBack = licenseIdBack;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
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
