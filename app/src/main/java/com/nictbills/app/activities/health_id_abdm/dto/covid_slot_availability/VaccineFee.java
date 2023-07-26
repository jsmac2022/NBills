package com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VaccineFee implements Serializable {

    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("fee")
    @Expose
    private String fee;

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }


    @Override
    public String toString() {
        return "VaccineFee{" +
                "vaccine='" + vaccine + '\'' +
                ", fee='" + fee + '\'' +
                '}';
    }
}
