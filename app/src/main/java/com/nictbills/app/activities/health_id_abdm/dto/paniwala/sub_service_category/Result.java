package com.nictbills.app.activities.health_id_abdm.dto.paniwala.sub_service_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("sub_serv")
    @Expose
    private String subServ;
    @SerializedName("capacity")
    @Expose
    private String capacity;

    public String getSubServ() {
        return subServ;
    }

    public void setSubServ(String subServ) {
        this.subServ = subServ;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

}
