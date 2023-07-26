package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login;

public class ResendMobileLoginOtpRequest {

    private String authMethod,txnId;

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
