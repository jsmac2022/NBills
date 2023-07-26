package com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CovidTimeSlotRequest  implements Serializable {

    @SerializedName("dose")
    @Expose
    private Integer dose;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("slot")
    @Expose
    private String slot;
    @SerializedName("beneficiaries")
    @Expose
    private List<String> beneficiaries = null;

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

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public List<String> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<String> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

}
