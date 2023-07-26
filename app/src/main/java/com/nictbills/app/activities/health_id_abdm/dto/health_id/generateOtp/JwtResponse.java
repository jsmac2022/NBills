package com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JwtResponse {

    @SerializedName("expiresIn")
    @Expose
    private Integer expiresIn;
    @SerializedName("refreshExpiresIn")
    @Expose
    private Integer refreshExpiresIn;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(Integer refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
