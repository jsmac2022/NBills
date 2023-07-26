package com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.getCircleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCirclePaySprint {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
