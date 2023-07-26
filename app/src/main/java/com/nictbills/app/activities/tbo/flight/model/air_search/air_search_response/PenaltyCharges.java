package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenaltyCharges {

    @SerializedName("ReissueCharge")
    @Expose
    private String reissueCharge;
    @SerializedName("CancellationCharge")
    @Expose
    private String cancellationCharge;

    public String getReissueCharge() {
        return reissueCharge;
    }

    public void setReissueCharge(String reissueCharge) {
        this.reissueCharge = reissueCharge;
    }

    public String getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(String cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

}
