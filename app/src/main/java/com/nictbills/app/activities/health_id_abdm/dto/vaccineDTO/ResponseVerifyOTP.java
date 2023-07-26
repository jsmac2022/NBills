package com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO;

public class ResponseVerifyOTP {

    private String token,isNewAccount;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsNewAccount() {
        return isNewAccount;
    }

    public void setIsNewAccount(String isNewAccount) {
        this.isNewAccount = isNewAccount;
    }
}
