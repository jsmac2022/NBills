
package com.nictbills.app.activities.tbo.bus.model.busblockresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gst {

    @SerializedName("CGSTAmount")
    @Expose
    private Integer cGSTAmount;
    @SerializedName("CGSTRate")
    @Expose
    private Integer cGSTRate;
    @SerializedName("CessAmount")
    @Expose
    private Integer cessAmount;
    @SerializedName("CessRate")
    @Expose
    private Double cessRate;
    @SerializedName("IGSTAmount")
    @Expose
    private Integer iGSTAmount;
    @SerializedName("IGSTRate")
    @Expose
    private Integer iGSTRate;
    @SerializedName("SGSTAmount")
    @Expose
    private Integer sGSTAmount;
    @SerializedName("SGSTRate")
    @Expose
    private Integer sGSTRate;
    @SerializedName("TaxableAmount")
    @Expose
    private Integer taxableAmount;

    public Integer getCGSTAmount() {
        return cGSTAmount;
    }

    public void setCGSTAmount(Integer cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public Integer getCGSTRate() {
        return cGSTRate;
    }

    public void setCGSTRate(Integer cGSTRate) {
        this.cGSTRate = cGSTRate;
    }

    public Integer getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(Integer cessAmount) {
        this.cessAmount = cessAmount;
    }

    public Double getCessRate() {
        return cessRate;
    }

    public void setCessRate(Double cessRate) {
        this.cessRate = cessRate;
    }

    public Integer getIGSTAmount() {
        return iGSTAmount;
    }

    public void setIGSTAmount(Integer iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
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

    public Integer getSGSTRate() {
        return sGSTRate;
    }

    public void setSGSTRate(Integer sGSTRate) {
        this.sGSTRate = sGSTRate;
    }

    public Integer getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Integer taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

}
