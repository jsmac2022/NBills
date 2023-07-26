
package com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("addPassenger")
    @Expose
    private List<AddPassenger> addPassenger = null;

    public List<AddPassenger> getAddPassenger() {
        return addPassenger;
    }

    public void setAddPassenger(List<AddPassenger> addPassenger) {
        this.addPassenger = addPassenger;
    }

}
