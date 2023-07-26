package com.nictbills.app.activities.tbo.flight.model.auth_token.auth_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthRequest {

    @SerializedName("EndUserIp")
    @Expose
    private String EndUserIp;

    public String getEndUserIp() {
        return EndUserIp;
    }
    public void setEndUserIp(String endUserIp) {
        EndUserIp = endUserIp;
    }
}
