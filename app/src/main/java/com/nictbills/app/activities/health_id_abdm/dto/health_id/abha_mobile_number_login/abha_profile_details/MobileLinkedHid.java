package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.abha_profile_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileLinkedHid {

    @SerializedName("healthIdNumber")
    @Expose
    private String healthIdNumber;
    @SerializedName("healthId")
    @Expose
    private String healthId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profilePhoto")
    @Expose
    private Object profilePhoto;
    @SerializedName("phrAddress")
    @Expose
    private Object phrAddress;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Object profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Object getPhrAddress() {
        return phrAddress;
    }

    public void setPhrAddress(Object phrAddress) {
        this.phrAddress = phrAddress;
    }

}
