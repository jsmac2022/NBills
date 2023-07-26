package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.abha_profile_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetABHAUserProfileDetails {

    @SerializedName("txnId")
    @Expose
    private String txnId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("mobileLinkedHid")
    @Expose
    private List<MobileLinkedHid> mobileLinkedHid = null;

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<MobileLinkedHid> getMobileLinkedHid() {
        return mobileLinkedHid;
    }

    public void setMobileLinkedHid(List<MobileLinkedHid> mobileLinkedHid) {
        this.mobileLinkedHid = mobileLinkedHid;
    }


}
