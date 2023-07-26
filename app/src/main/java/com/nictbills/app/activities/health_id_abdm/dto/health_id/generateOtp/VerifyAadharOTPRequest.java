package com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp;

public class VerifyAadharOTPRequest {

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
