package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserIndividualProfileDetails {

   private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
