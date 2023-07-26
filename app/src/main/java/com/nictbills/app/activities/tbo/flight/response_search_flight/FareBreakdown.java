package com.nictbills.app.activities.tbo.flight.response_search_flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response.TaxBreakUp;

import java.util.List;

public class FareBreakdown {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("PassengerType")
    @Expose
    private Integer passengerType;
    @SerializedName("PassengerCount")
    @Expose
    private Integer passengerCount;
    @SerializedName("BaseFare")
    @Expose
    private Integer baseFare;
    @SerializedName("Tax")
    @Expose
    private Integer tax;
    @SerializedName("TaxBreakUp")
    @Expose
    private List<TaxBreakUp> taxBreakUp = null;
    @SerializedName("YQTax")
    @Expose
    private Integer yQTax;
    @SerializedName("AdditionalTxnFeeOfrd")
    @Expose
    private Integer additionalTxnFeeOfrd;
    @SerializedName("AdditionalTxnFeePub")
    @Expose
    private Integer additionalTxnFeePub;
    @SerializedName("PGCharge")
    @Expose
    private Integer pGCharge;
    @SerializedName("SupplierReissueCharges")
    @Expose
    private Integer supplierReissueCharges;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Integer getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Integer baseFare) {
        this.baseFare = baseFare;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public List<TaxBreakUp> getTaxBreakUp() {
        return taxBreakUp;
    }

    public void setTaxBreakUp(List<TaxBreakUp> taxBreakUp) {
        this.taxBreakUp = taxBreakUp;
    }

    public Integer getYQTax() {
        return yQTax;
    }

    public void setYQTax(Integer yQTax) {
        this.yQTax = yQTax;
    }

    public Integer getAdditionalTxnFeeOfrd() {
        return additionalTxnFeeOfrd;
    }

    public void setAdditionalTxnFeeOfrd(Integer additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
    }

    public Integer getAdditionalTxnFeePub() {
        return additionalTxnFeePub;
    }

    public void setAdditionalTxnFeePub(Integer additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
    }

    public Integer getPGCharge() {
        return pGCharge;
    }

    public void setPGCharge(Integer pGCharge) {
        this.pGCharge = pGCharge;
    }

    public Integer getSupplierReissueCharges() {
        return supplierReissueCharges;
    }

    public void setSupplierReissueCharges(Integer supplierReissueCharges) {
        this.supplierReissueCharges = supplierReissueCharges;
    }

}
