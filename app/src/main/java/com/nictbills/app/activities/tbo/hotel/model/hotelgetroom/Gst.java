
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gst implements Serializable
{

    @SerializedName("CGSTAmount")
    @Expose
    private double cGSTAmount;
    @SerializedName("CGSTRate")
    @Expose
    private int cGSTRate;
    @SerializedName("CessAmount")
    @Expose
    private double cessAmount;
    @SerializedName("CessRate")
    @Expose
    private double cessRate;
    @SerializedName("IGSTAmount")
    @Expose
    private double iGSTAmount;
    @SerializedName("IGSTRate")
    @Expose
    private int iGSTRate;
    @SerializedName("SGSTAmount")
    @Expose
    private double sGSTAmount;
    @SerializedName("SGSTRate")
    @Expose
    private int sGSTRate;
    @SerializedName("TaxableAmount")
    @Expose
    private double taxableAmount;
    private final static long serialVersionUID = -2677107833994839582L;

    public double getCGSTAmount() {
        return cGSTAmount;
    }

    public void setCGSTAmount(double cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public Gst withCGSTAmount(double cGSTAmount) {
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

    public double getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(double cessAmount) {
        this.cessAmount = cessAmount;
    }

    public Gst withCessAmount(double cessAmount) {
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

    public double getIGSTAmount() {
        return iGSTAmount;
    }

    public void setIGSTAmount(double iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
    }

    public Gst withIGSTAmount(double iGSTAmount) {
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

    public double getSGSTAmount() {
        return sGSTAmount;
    }

    public void setSGSTAmount(double sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
    }

    public Gst withSGSTAmount(double sGSTAmount) {
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

    public double getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Gst withTaxableAmount(double taxableAmount) {
        this.taxableAmount = taxableAmount;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Gst.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cGSTAmount");
        sb.append('=');
        sb.append(this.cGSTAmount);
        sb.append(',');
        sb.append("cGSTRate");
        sb.append('=');
        sb.append(this.cGSTRate);
        sb.append(',');
        sb.append("cessAmount");
        sb.append('=');
        sb.append(this.cessAmount);
        sb.append(',');
        sb.append("cessRate");
        sb.append('=');
        sb.append(this.cessRate);
        sb.append(',');
        sb.append("iGSTAmount");
        sb.append('=');
        sb.append(this.iGSTAmount);
        sb.append(',');
        sb.append("iGSTRate");
        sb.append('=');
        sb.append(this.iGSTRate);
        sb.append(',');
        sb.append("sGSTAmount");
        sb.append('=');
        sb.append(this.sGSTAmount);
        sb.append(',');
        sb.append("sGSTRate");
        sb.append('=');
        sb.append(this.sGSTRate);
        sb.append(',');
        sb.append("taxableAmount");
        sb.append('=');
        sb.append(this.taxableAmount);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
