package com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOperatorPaySprint {

    @SerializedName("responsecode")
    @Expose
    private Integer responsecode;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(Integer responsecode) {
        this.responsecode = responsecode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
