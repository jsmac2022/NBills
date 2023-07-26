package com.nictbills.app.activities.health_id_abdm.dto.update_order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcquirerData {

    @SerializedName("auth_code")
    @Expose
    private Object authCode;

    public Object getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Object authCode) {
        this.authCode = authCode;
    }

}
