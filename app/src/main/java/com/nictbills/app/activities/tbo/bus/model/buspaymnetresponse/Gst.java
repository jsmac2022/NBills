
package com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gst {

    @SerializedName("CessAmount")
    @Expose
    private Integer cessAmount;
    @SerializedName("SGSTRate")
    @Expose
    private Integer sGSTRate;
    @SerializedName("CGSTAmount")
    @Expose
    private Integer cGSTAmount;
    @SerializedName("TaxableAmount")
    @Expose
    private Integer taxableAmount;
    @SerializedName("IGSTRate")
    @Expose
    private Integer iGSTRate;
    @SerializedName("SGSTAmount")
    @Expose
    private Integer sGSTAmount;
    @SerializedName("CessRate")
    @Expose
    private Integer cessRate;
    @SerializedName("IGSTAmount")
    @Expose
    private Integer iGSTAmount;

    public Integer getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(Integer cessAmount) {
        this.cessAmount = cessAmount;
    }

    public Integer getSGSTRate() {
        return sGSTRate;
    }

    public void setSGSTRate(Integer sGSTRate) {
        this.sGSTRate = sGSTRate;
    }

    public Integer getCGSTAmount() {
        return cGSTAmount;
    }

    public void setCGSTAmount(Integer cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public Integer getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Integer taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Integer getIGSTRate() {
        return iGSTRate;
    }

    public void setIGSTRate(Integer iGSTRate) {
        this.iGSTRate = iGSTRate;
    }

    public Integer getSGSTAmount() {
        return sGSTAmount;
    }

    public void setSGSTAmount(Integer sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
    }

    public Integer getCessRate() {
        return cessRate;
    }

    public void setCessRate(Integer cessRate) {
        this.cessRate = cessRate;
    }

    public Integer getIGSTAmount() {
        return iGSTAmount;
    }

    public void setIGSTAmount(Integer iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
    }

}
