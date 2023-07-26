
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gst implements Serializable
{

    @SerializedName("CGSTAmount")
    @Expose
    private int cGSTAmount;
    @SerializedName("CGSTRate")
    @Expose
    private int cGSTRate;
    @SerializedName("CessAmount")
    @Expose
    private int cessAmount;
    @SerializedName("CessRate")
    @Expose
    private double cessRate;
    @SerializedName("IGSTAmount")
    @Expose
    private int iGSTAmount;
    @SerializedName("IGSTRate")
    @Expose
    private int iGSTRate;
    @SerializedName("SGSTAmount")
    @Expose
    private int sGSTAmount;
    @SerializedName("SGSTRate")
    @Expose
    private int sGSTRate;
    @SerializedName("TaxableAmount")
    @Expose
    private int taxableAmount;
    private final static long serialVersionUID = -1289942238969249872L;

    public int getCGSTAmount() {
        return cGSTAmount;
    }

    public void setCGSTAmount(int cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public Gst withCGSTAmount(int cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
        return this;
    }

    public int getCGSTRate() {
        return cGSTRate;
    }

    public void setCGSTRate(int cGSTRate) {
        this.cGSTRate = cGSTRate;
    }

    public Gst withCGSTRate(int cGSTRate) {
        this.cGSTRate = cGSTRate;
        return this;
    }

    public int getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(int cessAmount) {
        this.cessAmount = cessAmount;
    }

    public Gst withCessAmount(int cessAmount) {
        this.cessAmount = cessAmount;
        return this;
    }

    public double getCessRate() {
        return cessRate;
    }

    public void setCessRate(double cessRate) {
        this.cessRate = cessRate;
    }

    public Gst withCessRate(double cessRate) {
        this.cessRate = cessRate;
        return this;
    }

    public int getIGSTAmount() {
        return iGSTAmount;
    }

    public void setIGSTAmount(int iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
    }

    public Gst withIGSTAmount(int iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
        return this;
    }

    public int getIGSTRate() {
        return iGSTRate;
    }

    public void setIGSTRate(int iGSTRate) {
        this.iGSTRate = iGSTRate;
    }

    public Gst withIGSTRate(int iGSTRate) {
        this.iGSTRate = iGSTRate;
        return this;
    }

    public int getSGSTAmount() {
        return sGSTAmount;
    }

    public void setSGSTAmount(int sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
    }

    public Gst withSGSTAmount(int sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
        return this;
    }

    public int getSGSTRate() {
        return sGSTRate;
    }

    public void setSGSTRate(int sGSTRate) {
        this.sGSTRate = sGSTRate;
    }

    public Gst withSGSTRate(int sGSTRate) {
        this.sGSTRate = sGSTRate;
        return this;
    }

    public int getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(int taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Gst withTaxableAmount(int taxableAmount) {
        this.taxableAmount = taxableAmount;
        return this;
    }

}
