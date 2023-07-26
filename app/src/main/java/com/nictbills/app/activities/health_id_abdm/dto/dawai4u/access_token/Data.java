package com.nictbills.app.activities.health_id_abdm.dto.dawai4u.access_token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("otp")
    @Expose
    private Object otp;
    @SerializedName("otp_reff")
    @Expose
    private Object otpReff;
    @SerializedName("auth_token")
    @Expose
    private String authToken;
    @SerializedName("otp_count")
    @Expose
    private Integer otpCount;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getOtp() {
        return otp;
    }

    public void setOtp(Object otp) {
        this.otp = otp;
    }

    public Object getOtpReff() {
        return otpReff;
    }

    public void setOtpReff(Object otpReff) {
        this.otpReff = otpReff;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Integer getOtpCount() {
        return otpCount;
    }

    public void setOtpCount(Integer otpCount) {
        this.otpCount = otpCount;
    }

}
