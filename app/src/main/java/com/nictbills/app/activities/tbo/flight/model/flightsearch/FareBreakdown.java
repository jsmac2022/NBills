
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

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
    private float passengerType;
    @SerializedName("PassengerCount")
    @Expose
    private float passengerCount;
    @SerializedName("BaseFare")
    @Expose
    private float baseFare;
    @SerializedName("Tax")
    @Expose
    private float tax;
    @SerializedName("TaxBreakUp")
    @Expose
    private List<TaxBreakUp> taxBreakUp = null;
    @SerializedName("YQTax")
    @Expose
    private float yQTax;
    @SerializedName("AdditionalTxnFeeOfrd")
    @Expose
    private float additionalTxnFeeOfrd;
    @SerializedName("AdditionalTxnFeePub")
    @Expose
    private float additionalTxnFeePub;
    @SerializedName("PGCharge")
    @Expose
    private float pGCharge;
    @SerializedName("SupplierReissueCharges")
    @Expose
    private float supplierReissueCharges;
    private final static long serialVersionUID = -2274332892666407805L;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(float passengerType) {
        this.passengerType = passengerType;
    }

    public float getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(float passengerCount) {
        this.passengerCount = passengerCount;
    }

    public float getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(float baseFare) {
        this.baseFare = baseFare;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public List<TaxBreakUp> getTaxBreakUp() {
        return taxBreakUp;
    }

    public void setTaxBreakUp(List<TaxBreakUp> taxBreakUp) {
        this.taxBreakUp = taxBreakUp;
    }

    public float getYQTax() {
        return yQTax;
    }

    public void setYQTax(float yQTax) {
        this.yQTax = yQTax;
    }

    public float getAdditionalTxnFeeOfrd() {
        return additionalTxnFeeOfrd;
    }

    public void setAdditionalTxnFeeOfrd(float additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
    }

    public float getAdditionalTxnFeePub() {
        return additionalTxnFeePub;
    }

    public void setAdditionalTxnFeePub(float additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
    }

    public float getPGCharge() {
        return pGCharge;
    }

    public void setPGCharge(float pGCharge) {
        this.pGCharge = pGCharge;
    }

    public float getSupplierReissueCharges() {
        return supplierReissueCharges;
    }

    public void setSupplierReissueCharges(float supplierReissueCharges) {
        this.supplierReissueCharges = supplierReissueCharges;
    }

}
