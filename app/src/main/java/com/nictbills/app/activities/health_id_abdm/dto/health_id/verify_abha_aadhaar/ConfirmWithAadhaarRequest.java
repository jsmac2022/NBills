package com.nictbills.app.activities.health_id_abdm.dto.health_id.verify_abha_aadhaar;

public class ConfirmWithAadhaarRequest {

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
