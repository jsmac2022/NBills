package com.nictbills.app.activities.fastag.Model;

import com.google.gson.annotations.SerializedName;

public class FasTagType {
    @SerializedName("ft_id")
    private String ft_id;

    @SerializedName("type_name")
    private String type_name;

    @SerializedName("sector")
    private String sector;

    @SerializedName("status")
    private String status;

    public String getFt_id() {
        return ft_id;
    }

    public void setFt_id(String ft_id) {
        this.ft_id = ft_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
