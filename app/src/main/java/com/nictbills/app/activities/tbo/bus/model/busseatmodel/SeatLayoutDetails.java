
package com.nictbills.app.activities.tbo.bus.model.busseatmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeatLayoutDetails {

    @SerializedName("AvailableSeats")
    @Expose
    private String availableSeats;
    @SerializedName("HTMLLayout")
    @Expose
    private String hTMLLayout;
    @SerializedName("SeatLayout")
    @Expose
    private SeatLayout seatLayout;

    public String getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(String availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getHTMLLayout() {
        return hTMLLayout;
    }

    public void setHTMLLayout(String hTMLLayout) {
        this.hTMLLayout = hTMLLayout;
    }

    public SeatLayout getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(SeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }

}
