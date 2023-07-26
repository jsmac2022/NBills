package com.nictbills.app.activities.health_id_abdm.dto.health_id.verifyABHAAddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchByHealthIdResponse {

    @SerializedName("healthId")
    @Expose
    private String healthId;
    @SerializedName("healthIdNumber")
    @Expose
    private String healthIdNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("authMethods")
    @Expose
    private List<String> authMethods = null;
    @SerializedName("tags")
    @Expose
    private Object tags;

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAuthMethods() {
        return authMethods;
    }

    public void setAuthMethods(List<String> authMethods) {
        this.authMethods = authMethods;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

}
