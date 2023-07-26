package com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Slot implements Serializable {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("seats")
    @Expose
    private Integer seats;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

}
