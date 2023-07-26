package com.nictbills.app.activities.health_id_abdm.dto.beneficiary_dto;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Keep
public class CovidBeneficiaryListDTO {

    @SerializedName("beneficiaries")
    @Expose
    private List<Beneficiary> beneficiaries = null;

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

}
