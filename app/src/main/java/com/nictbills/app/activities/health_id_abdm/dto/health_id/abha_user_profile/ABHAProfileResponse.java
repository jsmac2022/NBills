package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ABHAProfileResponse {

    @SerializedName("healthIdNumber")
    @Expose
    private String healthIdNumber;
    @SerializedName("healthId")
    @Expose
    private String healthId;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("yearOfBirth")
    @Expose
    private String yearOfBirth;
    @SerializedName("dayOfBirth")
    @Expose
    private String dayOfBirth;
    @SerializedName("monthOfBirth")
    @Expose
    private String monthOfBirth;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("profilePhoto")
    @Expose
    private String profilePhoto;
    @SerializedName("stateCode")
    @Expose
    private String stateCode;
    @SerializedName("districtCode")
    @Expose
    private String districtCode;
    @SerializedName("subDistrictCode")
    @Expose
    private Object subDistrictCode;
    @SerializedName("villageCode")
    @Expose
    private Object villageCode;
    @SerializedName("townCode")
    @Expose
    private Object townCode;
    @SerializedName("wardCode")
    @Expose
    private Object wardCode;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("kycPhoto")
    @Expose
    private Object kycPhoto;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("districtName")
    @Expose
    private String districtName;
    @SerializedName("subdistrictName")
    @Expose
    private Object subdistrictName;
    @SerializedName("villageName")
    @Expose
    private Object villageName;
    @SerializedName("townName")
    @Expose
    private String townName;
    @SerializedName("wardName")
    @Expose
    private Object wardName;
    @SerializedName("authMethods")
    @Expose
    private List<String> authMethods = null;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("kycVerified")
    @Expose
    private Boolean kycVerified;
    @SerializedName("verificationStatus")
    @Expose
    private Object verificationStatus;
    @SerializedName("verificationType")
    @Expose
    private Object verificationType;
    @SerializedName("phrAddress")
    @Expose
    private List<String> phrAddress = null;
    @SerializedName("new")
    @Expose
    private Boolean _new;
    @SerializedName("emailVerified")
    @Expose
    private Boolean emailVerified;

    public String getHealthIdNumber() {
        return healthIdNumber;
    }

    public void setHealthIdNumber(String healthIdNumber) {
        this.healthIdNumber = healthIdNumber;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
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

    public Object getSubDistrictCode() {
        return subDistrictCode;
    }

    public void setSubDistrictCode(Object subDistrictCode) {
        this.subDistrictCode = subDistrictCode;
    }

    public Object getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(Object villageCode) {
        this.villageCode = villageCode;
    }

    public Object getTownCode() {
        return townCode;
    }

    public void setTownCode(Object townCode) {
        this.townCode = townCode;
    }

    public Object getWardCode() {
        return wardCode;
    }

    public void setWardCode(Object wardCode) {
        this.wardCode = wardCode;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getKycPhoto() {
        return kycPhoto;
    }

    public void setKycPhoto(Object kycPhoto) {
        this.kycPhoto = kycPhoto;
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

    public Object getSubdistrictName() {
        return subdistrictName;
    }

    public void setSubdistrictName(Object subdistrictName) {
        this.subdistrictName = subdistrictName;
    }

    public Object getVillageName() {
        return villageName;
    }

    public void setVillageName(Object villageName) {
        this.villageName = villageName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Object getWardName() {
        return wardName;
    }

    public void setWardName(Object wardName) {
        this.wardName = wardName;
    }

    public List<String> getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(List<String> authMethods) {
        this.authMethods = authMethods;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Boolean getKycVerified() {
        return kycVerified;
    }

    public void setKycVerified(Boolean kycVerified) {
        this.kycVerified = kycVerified;
    }

    public Object getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(Object verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Object getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(Object verificationType) {
        this.verificationType = verificationType;
    }

    public List<String> getPhrAddress() {
        return phrAddress;
    }

    public void setPhrAddress(List<String> phrAddress) {
        this.phrAddress = phrAddress;
    }

    public Boolean getNew() {
        return _new;
    }

    public void setNew(Boolean _new) {
        this._new = _new;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }


}
