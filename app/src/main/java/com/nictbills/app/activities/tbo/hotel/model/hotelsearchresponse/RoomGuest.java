
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomGuest {

    @SerializedName("NoOfAdults")
    @Expose
    private Integer noOfAdults;
    @SerializedName("NoOfChild")
    @Expose
    private Integer noOfChild;
    @SerializedName("ChildAge")
    @Expose
    private List<Object> childAge;

    public Integer getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(Integer noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public Integer getNoOfChild() {
        return noOfChild;
    }

    public void setNoOfChild(Integer noOfChild) {
        this.noOfChild = noOfChild;
    }

    public List<Object> getChildAge() {
        return childAge;
    }

    public void setChildAge(List<Object> childAge) {
        this.childAge = childAge;
    }

}
