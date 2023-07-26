package com.nictbills.app.activities.fastag.Model;

import com.google.gson.annotations.SerializedName;

public class UserDetails {
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("otp")
    private String otp;
    @SerializedName("otp_reff")
    private String otp_reff;
    @SerializedName("auth_token")
    private String auth_token;
    @SerializedName("otp_count")
    private String otp_count;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtp_reff() {
        return otp_reff;
    }

    public void setOtp_reff(String otp_reff) {
        this.otp_reff = otp_reff;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getOtp_count() {
        return otp_count;
    }

    public void setOtp_count(String otp_count) {
        this.otp_count = otp_count;
    }
}
