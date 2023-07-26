
package com.nictbills.app.activities.tbo.flight.model.farerules;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenaltyCharges implements Serializable
{

    @SerializedName("ReissueCharge")
    @Expose
    private String reissueCharge;
    @SerializedName("CancellationCharge")
    @Expose
    private String cancellationCharge;
    private final static long serialVersionUID = -6233181319952711503L;

    public String getReissueCharge() {
        return reissueCharge;
    }

    public void setReissueCharge(String reissueCharge) {
        this.reissueCharge = reissueCharge;
    }

    public PenaltyCharges withReissueCharge(String reissueCharge) {
        this.reissueCharge = reissueCharge;
        return this;
    }

    public String getCancellationCharge() {
        return cancellationCharge;
    }

    public void setCancellationCharge(String cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
    }

    public PenaltyCharges withCancellationCharge(String cancellationCharge) {
        this.cancellationCharge = cancellationCharge;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PenaltyCharges.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("reissueCharge");
        sb.append('=');
        sb.append(((this.reissueCharge == null)?"<null>":this.reissueCharge));
        sb.append(',');
        sb.append("cancellationCharge");
        sb.append('=');
        sb.append(((this.cancellationCharge == null)?"<null>":this.cancellationCharge));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
