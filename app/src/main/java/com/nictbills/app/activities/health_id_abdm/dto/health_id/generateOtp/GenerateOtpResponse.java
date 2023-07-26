package com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp;

public class GenerateOtpResponse {

    private String mobileNumber,txnId;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
