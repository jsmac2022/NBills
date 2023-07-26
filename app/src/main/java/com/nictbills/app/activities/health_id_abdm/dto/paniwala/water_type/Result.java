package com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("main_serv")
    @Expose
    private String mainServ;
    @SerializedName("main_serv_code")
    @Expose
    private String mainServCode;

    @SerializedName("logo")
    @Expose
    private String logo;

    public String getMainServ() {
        return mainServ;
    }

    public void setMainServ(String mainServ) {
        this.mainServ = mainServ;
    }

    public String getMainServCode() {
        return mainServCode;
    }

    public void setMainServCode(String mainServCode) {
        this.mainServCode = mainServCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
