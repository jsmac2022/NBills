package com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Session implements Serializable {

    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("available_capacity")
    @Expose
    private Integer availableCapacity;
    @SerializedName("min_age_limit")
    @Expose
    private Integer minAgeLimit;
    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("slots")
    @Expose
    private List<String> slots = null;
    @SerializedName("available_capacity_dose1")
    @Expose
    private Integer availableCapacityDose1;
    @SerializedName("available_capacity_dose2")
    @Expose
    private Integer availableCapacityDose2;
    @SerializedName("max_age_limit")
    @Expose
    private Integer maxAgeLimit;
    @SerializedName("allow_all_age")
    @Expose
    private Boolean allowAllAge;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Integer getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(Integer minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    public Integer getAvailableCapacityDose1() {
        return availableCapacityDose1;
    }

    public void setAvailableCapacityDose1(Integer availableCapacityDose1) {
        this.availableCapacityDose1 = availableCapacityDose1;
    }

    public Integer getAvailableCapacityDose2() {
        return availableCapacityDose2;
    }

    public void setAvailableCapacityDose2(Integer availableCapacityDose2) {
        this.availableCapacityDose2 = availableCapacityDose2;
    }

    public Integer getMaxAgeLimit() {
        return maxAgeLimit;
    }

    public void setMaxAgeLimit(Integer maxAgeLimit) {
        this.maxAgeLimit = maxAgeLimit;
    }

    public Boolean getAllowAllAge() {
        return allowAllAge;
    }

    public void setAllowAllAge(Boolean allowAllAge) {
        this.allowAllAge = allowAllAge;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", date='" + date + '\'' +
                ", availableCapacity=" + availableCapacity +
                ", minAgeLimit=" + minAgeLimit +
                ", vaccine='" + vaccine + '\'' +
                ", slots=" + slots +
                ", availableCapacityDose1=" + availableCapacityDose1 +
                ", availableCapacityDose2=" + availableCapacityDose2 +
                ", maxAgeLimit=" + maxAgeLimit +
                ", allowAllAge=" + allowAllAge +
                '}';
    }
}
