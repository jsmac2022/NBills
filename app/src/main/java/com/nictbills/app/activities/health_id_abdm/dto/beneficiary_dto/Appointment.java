package com.nictbills.app.activities.health_id_abdm.dto.beneficiary_dto;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
public class Appointment {
    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("center_id")
    @Expose
    private Integer centerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("block_name")
    @Expose
    private String blockName;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("dose")
    @Expose
    private Integer dose;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("slot")
    @Expose
    private String slot;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

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

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }


}
