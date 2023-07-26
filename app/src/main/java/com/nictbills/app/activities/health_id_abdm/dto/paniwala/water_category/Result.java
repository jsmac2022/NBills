package com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("sub_serv_code")
    @Expose
    private String subServCode;
    @SerializedName("sub_serv")
    @Expose
    private String subServ;

    public String getSubServCode() {
        return subServCode;
    }

    public void setSubServCode(String subServCode) {
        this.subServCode = subServCode;
    }

    public String getSubServ() {
        return subServ;
    }

    public void setSubServ(String subServ) {
        this.subServ = subServ;
    }

}
