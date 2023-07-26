package com.nictbills.app.activities.health_id_abdm.dto.health_id.create_health_id_pre_verified;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateHealthIdWithPreVerifiedResponse {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("healthIdNumber")
    @Expose
    private String healthIdNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("yearOfBirth")
    @Expose
    private String yearOfBirth;
    @SerializedName("monthOfBirth")
    @Expose
    private String monthOfBirth;
    @SerializedName("dayOfBirth")
    @Expose
    private String dayOfBirth;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("healthId")
    @Expose
    private Object healthId;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("stateCode")
    @Expose
    private String stateCode;
    @SerializedName("districtCode")
    @Expose
    private String districtCode;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("districtName")
    @Expose
    private String districtName;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("kycPhoto")
    @Expose
    private Object kycPhoto;
    @SerializedName("profilePhoto")
    @Expose
    private String profilePhoto;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("authMethods")
    @Expose
    private List<String> authMethods = null;
    @SerializedName("pincode")
    @Expose
    private Object pincode;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("new")
    @Expose
    private Boolean _new;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getHealthId() {
        return healthId;
    }

    public void setHealthId(Object healthId) {
        this.healthId = healthId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getKycPhoto() {
        return kycPhoto;
    }

    public void setKycPhoto(Object kycPhoto) {
        this.kycPhoto = kycPhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<String> getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(List<String> authMethods) {
        this.authMethods = authMethods;
    }

    public Object getPincode() {
        return pincode;
    }

    public void setPincode(Object pincode) {
        this.pincode = pincode;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Boolean getNew() {
        return _new;
    }

    public void setNew(Boolean _new) {
        this._new = _new;
    }

}
