package com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp;

public class ABHAMobileNumberVerifyRequest {

    private String otp,txnId;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
