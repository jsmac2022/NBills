package com.nictbills.app.activities.health_id_abdm.dto.cancel_beneficiary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidBeneficiaryCancelDTO {

    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("beneficiariesToCancel")
    @Expose
    private List<String> beneficiariesToCancel = null;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public List<String> getBeneficiariesToCancel() {
        return beneficiariesToCancel;
    }

    public void setBeneficiariesToCancel(List<String> beneficiariesToCancel) {
        this.beneficiariesToCancel = beneficiariesToCancel;
    }

}
