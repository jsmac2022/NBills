
package com.nictbills.app.activities.tbo.bus.model.busblockreqmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gst {

    @SerializedName("CGSTAmount")
    @Expose
    private Double cGSTAmount;
    @SerializedName("CGSTRate")
    @Expose
    private Double cGSTRate;
    @SerializedName("CessAmount")
    @Expose
    private Double cessAmount;
    @SerializedName("CessRate")
    @Expose
    private Double cessRate;
    @SerializedName("IGSTAmount")
    @Expose
    private Double iGSTAmount;
    @SerializedName("IGSTRate")
    @Expose
    private Double iGSTRate;
    @SerializedName("SGSTAmount")
    @Expose
    private Double sGSTAmount;
    @SerializedName("SGSTRate")
    @Expose
    private Double sGSTRate;
    @SerializedName("TaxableAmount")
    @Expose
    private Double taxableAmount;

    public Double getCGSTAmount() {
        return cGSTAmount;
    }

    public void setCGSTAmount(Double cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public Double getCGSTRate() {
        return cGSTRate;
    }

    public void setCGSTRate(Double cGSTRate) {
        this.cGSTRate = cGSTRate;
    }

    public Double getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(Double cessAmount) {
        this.cessAmount = cessAmount;
    }

    public Double getCessRate() {
        return cessRate;
    }

    public void setCessRate(Double cessRate) {
        this.cessRate = cessRate;
    }

    public Double getIGSTAmount() {
        return iGSTAmount;
    }

    public void setIGSTAmount(Double iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
    }

    public Double getIGSTRate() {
        return iGSTRate;
    }

    public void setIGSTRate(Double iGSTRate) {
        this.iGSTRate = iGSTRate;
    }

    public Double getSGSTAmount() {
        return sGSTAmount;
    }

    public void setSGSTAmount(Double sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
    }

    public Double getSGSTRate() {
        return sGSTRate;
    }

    public void setSGSTRate(Double sGSTRate) {
        this.sGSTRate = sGSTRate;
    }

    public Double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

}
