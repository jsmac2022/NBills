
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fare implements Serializable
{

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("BaseFare")
    @Expose
    private float baseFare;
    @SerializedName("Tax")
    @Expose
    private float tax;
    @SerializedName("TaxBreakup")
    @Expose
    private List<TaxBreakup> taxBreakup = null;
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
    @SerializedName("OtherCharges")
    @Expose
    private float otherCharges;
    @SerializedName("ChargeBU")
    @Expose
    private List<ChargeBU> chargeBU = null;
    @SerializedName("Discount")
    @Expose
    private float discount;
    @SerializedName("PublishedFare")
    @Expose
    private float publishedFare;
    @SerializedName("CommissionEarned")
    @Expose
    private float commissionEarned;
    @SerializedName("PLBEarned")
    @Expose
    private float pLBEarned;
    @SerializedName("IncentiveEarned")
    @Expose
    private float incentiveEarned;
    @SerializedName("OfferedFare")
    @Expose
    private float offeredFare;
    @SerializedName("TdsOnCommission")
    @Expose
    private float tdsOnCommission;
    @SerializedName("TdsOnPLB")
    @Expose
    private float tdsOnPLB;
    @SerializedName("TdsOnIncentive")
    @Expose
    private float tdsOnIncentive;
    @SerializedName("ServiceFee")
    @Expose
    private float serviceFee;
    @SerializedName("TotalBaggageCharges")
    @Expose
    private float totalBaggageCharges;
    @SerializedName("TotalMealCharges")
    @Expose
    private float totalMealCharges;
    @SerializedName("TotalSeatCharges")
    @Expose
    private float totalSeatCharges;
    @SerializedName("TotalSpecialServiceCharges")
    @Expose
    private float totalSpecialServiceCharges;
    private final static long serialVersionUID = 6087735421039125000L;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public List<TaxBreakup> getTaxBreakup() {
        return taxBreakup;
    }

    public void setTaxBreakup(List<TaxBreakup> taxBreakup) {
        this.taxBreakup = taxBreakup;
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

    public float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(float otherCharges) {
        this.otherCharges = otherCharges;
    }

    public List<ChargeBU> getChargeBU() {
        return chargeBU;
    }

    public void setChargeBU(List<ChargeBU> chargeBU) {
        this.chargeBU = chargeBU;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getPublishedFare() {
        return publishedFare;
    }

    public void setPublishedFare(float publishedFare) {
        this.publishedFare = publishedFare;
    }

    public float getCommissionEarned() {
        return commissionEarned;
    }

    public void setCommissionEarned(float commissionEarned) {
        this.commissionEarned = commissionEarned;
    }

    public float getPLBEarned() {
        return pLBEarned;
    }

    public void setPLBEarned(float pLBEarned) {
        this.pLBEarned = pLBEarned;
    }

    public float getIncentiveEarned() {
        return incentiveEarned;
    }

    public void setIncentiveEarned(float incentiveEarned) {
        this.incentiveEarned = incentiveEarned;
    }

    public float getOfferedFare() {
        return offeredFare;
    }

    public void setOfferedFare(float offeredFare) {
        this.offeredFare = offeredFare;
    }

    public float getTdsOnCommission() {
        return tdsOnCommission;
    }

    public void setTdsOnCommission(float tdsOnCommission) {
        this.tdsOnCommission = tdsOnCommission;
    }

    public float getTdsOnPLB() {
        return tdsOnPLB;
    }

    public void setTdsOnPLB(float tdsOnPLB) {
        this.tdsOnPLB = tdsOnPLB;
    }

    public float getTdsOnIncentive() {
        return tdsOnIncentive;
    }

    public void setTdsOnIncentive(float tdsOnIncentive) {
        this.tdsOnIncentive = tdsOnIncentive;
    }

    public float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public float getTotalBaggageCharges() {
        return totalBaggageCharges;
    }

    public void setTotalBaggageCharges(float totalBaggageCharges) {
        this.totalBaggageCharges = totalBaggageCharges;
    }

    public float getTotalMealCharges() {
        return totalMealCharges;
    }

    public void setTotalMealCharges(float totalMealCharges) {
        this.totalMealCharges = totalMealCharges;
    }

    public float getTotalSeatCharges() {
        return totalSeatCharges;
    }

    public void setTotalSeatCharges(float totalSeatCharges) {
        this.totalSeatCharges = totalSeatCharges;
    }

    public float getTotalSpecialServiceCharges() {
        return totalSpecialServiceCharges;
    }

    public void setTotalSpecialServiceCharges(float totalSpecialServiceCharges) {
        this.totalSpecialServiceCharges = totalSpecialServiceCharges;
    }

}
