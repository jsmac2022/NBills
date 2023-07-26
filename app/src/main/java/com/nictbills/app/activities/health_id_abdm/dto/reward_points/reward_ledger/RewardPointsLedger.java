package com.nictbills.app.activities.health_id_abdm.dto.reward_points.reward_ledger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RewardPointsLedger {
    @SerializedName("ledger")
    @Expose
    private List<Ledger> ledger = null;

    public List<Ledger> getLedger() {
        return ledger;
    }

    public void setLedger(List<Ledger> ledger) {
        this.ledger = ledger;
    }
}
