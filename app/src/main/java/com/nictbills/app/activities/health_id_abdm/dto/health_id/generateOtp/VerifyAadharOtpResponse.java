package com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyAadharOtpResponse {

    @SerializedName("aadhaar")
    @Expose
    private Long aadhaar;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("careOf")
    @Expose
    private String careOf;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("healthIdNumber")
    @Expose
    private String healthIdNumber;
    @SerializedName("house")
    @Expose
    private String house;
    @SerializedName("jwtResponse")
    @Expose
    private JwtResponse jwtResponse;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private Long phone;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("pincode")
    @Expose
    private Integer pincode;
    @SerializedName("postOffice")
    @Expose
    private String postOffice;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("subDist")
    @Expose
    private String subDist;
    @SerializedName("txnId")
    @Expose
    private String txnId;
    @SerializedName("villageTownCity")
    @Expose
    private String villageTownCity;

    public Long getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(Long aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public JwtResponse getJwtResponse() {
        return jwtResponse;
    }

    public void setJwtResponse(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubDist() {
        return subDist;
    }

    public void setSubDist(String subDist) {
        this.subDist = subDist;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getVillageTownCity() {
        return villageTownCity;
    }

    public void setVillageTownCity(String villageTownCity) {
        this.villageTownCity = villageTownCity;
    }

}
