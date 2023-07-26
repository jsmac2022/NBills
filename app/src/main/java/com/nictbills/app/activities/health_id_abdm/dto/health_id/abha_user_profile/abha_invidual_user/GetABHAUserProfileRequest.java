package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user;

public class GetABHAUserProfileRequest {

    private String healthId,txnId;

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
