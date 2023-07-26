
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenaltyCharges implements Serializable
{

    @SerializedName("ReissueCharge")
    @Expose
    private String reissueCharge;
    private final static long serialVersionUID = 312211143061921200L;

    public String getReissueCharge() {
        return reissueCharge;
    }

    public void setReissueCharge(String reissueCharge) {
        this.reissueCharge = reissueCharge;
    }

}
