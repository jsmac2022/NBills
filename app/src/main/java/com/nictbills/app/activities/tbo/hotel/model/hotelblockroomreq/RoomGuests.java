package com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RoomGuests implements Serializable {

    @SerializedName("NoOfAdults")
    @Expose
    private Integer NoOfAdults;

    @SerializedName("NoOfChild")
    @Expose
    private Integer NoOfChild;

    @SerializedName("ChildAge")
    @Expose

    private Integer[] childAge = null;

    public Integer getNoOfAdults() {
        return NoOfAdults;
    }

    public void setNoOfAdults(Integer noOfAdults) {
        NoOfAdults = noOfAdults;
    }

    public Integer getNoOfChild() {
        return NoOfChild;
    }

    public void setNoOfChild(Integer noOfChild) {
        NoOfChild = noOfChild;
    }

    public Integer[] getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer[] childAge) {
        this.childAge = childAge;
    }


}
