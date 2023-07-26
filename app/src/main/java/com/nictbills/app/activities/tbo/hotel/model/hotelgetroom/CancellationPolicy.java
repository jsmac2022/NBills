
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancellationPolicy implements Serializable
{

    @SerializedName("Charge")
    @Expose
    private int charge;
    @SerializedName("ChargeType")
    @Expose
    private int chargeType;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    private final static long serialVersionUID = 4415343010287804715L;

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public CancellationPolicy withCharge(int charge) {
        this.charge = charge;
        return this;
    }

    public int getChargeType() {
        return chargeType;
    }

    public void setChargeType(int chargeType) {
        this.chargeType = chargeType;
    }

    public CancellationPolicy withChargeType(int chargeType) {
        this.chargeType = chargeType;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CancellationPolicy withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public CancellationPolicy withFromDate(String fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public CancellationPolicy withToDate(String toDate) {
        this.toDate = toDate;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CancellationPolicy.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("charge");
        sb.append('=');
        sb.append(this.charge);
        sb.append(',');
        sb.append("chargeType");
        sb.append('=');
        sb.append(this.chargeType);
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("fromDate");
        sb.append('=');
        sb.append(((this.fromDate == null)?"<null>":this.fromDate));
        sb.append(',');
        sb.append("toDate");
        sb.append('=');
        sb.append(((this.toDate == null)?"<null>":this.toDate));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
