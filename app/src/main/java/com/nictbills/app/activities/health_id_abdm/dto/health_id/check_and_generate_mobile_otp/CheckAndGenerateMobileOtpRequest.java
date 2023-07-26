package com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp;

public class CheckAndGenerateMobileOtpRequest {

    private String txnId;
    private String  mobile;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
