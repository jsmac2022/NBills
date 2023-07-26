package com.nictbills.app.activities.health_id_abdm.dto.transaction_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TxnDetails {

    @SerializedName("userTxnList")
    @Expose
    private List<UserTxnList> userTxnList = null;

    public List<UserTxnList> getUserTxnList() {
        return userTxnList;
    }

    public void setUserTxnList(List<UserTxnList> userTxnList) {
        this.userTxnList = userTxnList;
    }


}
