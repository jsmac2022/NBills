package com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp;

public class CheckAndGenerateMobileResponse {

    private String txnId;
    private boolean mobileLinked;


    public boolean isMobileLinked() {
        return mobileLinked;
    }

    public void setMobileLinked(boolean mobileLinked) {
        this.mobileLinked = mobileLinked;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }
}
