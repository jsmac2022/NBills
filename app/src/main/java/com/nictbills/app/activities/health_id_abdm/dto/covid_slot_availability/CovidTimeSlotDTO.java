package com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CovidTimeSlotDTO implements Serializable {

    @SerializedName("centers")
    @Expose
    private List<Center> centers = null;

    public List<Center> getCenters() {
        return centers;
    }

    public void setCenters(List<Center> centers) {
        this.centers = centers;
    }
}
