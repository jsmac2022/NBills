
package com.nictbills.app.activities.tbo.flight.model.farequote;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response.TaxBreakUp;

public class FareBreakdown implements Serializable
{

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("PassengerType")
    @Expose
    private int passengerType;
    @SerializedName("PassengerCount")
    @Expose
    private int passengerCount;
    @SerializedName("BaseFare")
    @Expose
    private int baseFare;
    @SerializedName("Tax")
    @Expose
    private int tax;
    @SerializedName("TaxBreakUp")
    @Expose
    private List<TaxBreakUp> taxBreakUp = null;
    @SerializedName("YQTax")
    @Expose
    private int yQTax;
    @SerializedName("AdditionalTxnFeeOfrd")
    @Expose
    private int additionalTxnFeeOfrd;
    @SerializedName("AdditionalTxnFeePub")
    @Expose
    private int additionalTxnFeePub;
    @SerializedName("PGCharge")
    @Expose
    private int pGCharge;
    @SerializedName("SupplierReissueCharges")
    @Expose
    private int supplierReissueCharges;
    private final static long serialVersionUID = -8782788633591708949L;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public FareBreakdown withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public int getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(int passengerType) {
        this.passengerType = passengerType;
    }

    public FareBreakdown withPassengerType(int passengerType) {
        this.passengerType = passengerType;
        return this;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public FareBreakdown withPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
        return this;
    }

    public int getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(int baseFare) {
        this.baseFare = baseFare;
    }

    public FareBreakdown withBaseFare(int baseFare) {
        this.baseFare = baseFare;
        return this;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public FareBreakdown withTax(int tax) {
        this.tax = tax;
        return this;
    }

    public List<TaxBreakUp> getTaxBreakUp() {
        return taxBreakUp;
    }

    public void setTaxBreakUp(List<TaxBreakUp> taxBreakUp) {
        this.taxBreakUp = taxBreakUp;
    }

    public FareBreakdown withTaxBreakUp(List<TaxBreakUp> taxBreakUp) {
        this.taxBreakUp = taxBreakUp;
        return this;
    }

    public int getYQTax() {
        return yQTax;
    }

    public void setYQTax(int yQTax) {
        this.yQTax = yQTax;
    }

    public FareBreakdown withYQTax(int yQTax) {
        this.yQTax = yQTax;
        return this;
    }

    public int getAdditionalTxnFeeOfrd() {
        return additionalTxnFeeOfrd;
    }

    public void setAdditionalTxnFeeOfrd(int additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
    }

    public FareBreakdown withAdditionalTxnFeeOfrd(int additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
        return this;
    }

    public int getAdditionalTxnFeePub() {
        return additionalTxnFeePub;
    }

    public void setAdditionalTxnFeePub(int additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
    }

    public FareBreakdown withAdditionalTxnFeePub(int additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
        return this;
    }

    public int getPGCharge() {
        return pGCharge;
    }

    public void setPGCharge(int pGCharge) {
        this.pGCharge = pGCharge;
    }

    public FareBreakdown withPGCharge(int pGCharge) {
        this.pGCharge = pGCharge;
        return this;
    }

    public int getSupplierReissueCharges() {
        return supplierReissueCharges;
    }

    public void setSupplierReissueCharges(int supplierReissueCharges) {
        this.supplierReissueCharges = supplierReissueCharges;
    }

    public FareBreakdown withSupplierReissueCharges(int supplierReissueCharges) {
        this.supplierReissueCharges = supplierReissueCharges;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FareBreakdown.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("passengerType");
        sb.append('=');
        sb.append(this.passengerType);
        sb.append(',');
        sb.append("passengerCount");
        sb.append('=');
        sb.append(this.passengerCount);
        sb.append(',');
        sb.append("baseFare");
        sb.append('=');
        sb.append(this.baseFare);
        sb.append(',');
        sb.append("tax");
        sb.append('=');
        sb.append(this.tax);
        sb.append(',');
        sb.append("taxBreakUp");
        sb.append('=');
        sb.append(((this.taxBreakUp == null)?"<null>":this.taxBreakUp));
        sb.append(',');
        sb.append("yQTax");
        sb.append('=');
        sb.append(this.yQTax);
        sb.append(',');
        sb.append("additionalTxnFeeOfrd");
        sb.append('=');
        sb.append(this.additionalTxnFeeOfrd);
        sb.append(',');
        sb.append("additionalTxnFeePub");
        sb.append('=');
        sb.append(this.additionalTxnFeePub);
        sb.append(',');
        sb.append("pGCharge");
        sb.append('=');
        sb.append(this.pGCharge);
        sb.append(',');
        sb.append("supplierReissueCharges");
        sb.append('=');
        sb.append(this.supplierReissueCharges);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
