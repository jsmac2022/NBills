package com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaniwalaVendorProfileListResponse {

    @SerializedName("Response")
    @Expose
    private String response;
    @SerializedName("Pay_status")
    @Expose
    private String payStatus;
    @SerializedName("msz")
    @Expose
    private String msz;
    @SerializedName("RESULT")
    @Expose
    private List<Result> result = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getMsz() {
        return msz;
    }

    public void setMsz(String msz) {
        this.msz = msz;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }


}
