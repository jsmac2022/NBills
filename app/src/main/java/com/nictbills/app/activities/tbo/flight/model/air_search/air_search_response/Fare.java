package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fare {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("BaseFare")
    @Expose
    private Integer baseFare;
    @SerializedName("Tax")
    @Expose
    private Integer tax;
    @SerializedName("TaxBreakup")
    @Expose
    private List<TaxBreakUp> taxBreakup = null;
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
    @SerializedName("OtherCharges")
    @Expose
    private Integer otherCharges;
    @SerializedName("ChargeBU")
    @Expose
    private List<ChargeBU> chargeBU = null;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("PublishedFare")
    @Expose
    private Integer publishedFare;
    @SerializedName("CommissionEarned")
    @Expose
    private Double commissionEarned;
    @SerializedName("PLBEarned")
    @Expose
    private Double pLBEarned;
    @SerializedName("IncentiveEarned")
    @Expose
    private Integer incentiveEarned;
    @SerializedName("OfferedFare")
    @Expose
    private Double offeredFare;
    @SerializedName("TdsOnCommission")
    @Expose
    private Double tdsOnCommission;
    @SerializedName("TdsOnPLB")
    @Expose
    private Double tdsOnPLB;
    @SerializedName("TdsOnIncentive")
    @Expose
    private Integer tdsOnIncentive;
    @SerializedName("ServiceFee")
    @Expose
    private Integer serviceFee;
    @SerializedName("TotalBaggageCharges")
    @Expose
    private Integer totalBaggageCharges;
    @SerializedName("TotalMealCharges")
    @Expose
    private Integer totalMealCharges;
    @SerializedName("TotalSeatCharges")
    @Expose
    private Integer totalSeatCharges;
    @SerializedName("TotalSpecialServiceCharges")
    @Expose
    private Integer totalSpecialServiceCharges;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public List<TaxBreakUp> getTaxBreakup() {
        return taxBreakup;
    }

    public void setTaxBreakup(List<TaxBreakUp> taxBreakup) {
        this.taxBreakup = taxBreakup;
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

    public Integer getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Integer otherCharges) {
        this.otherCharges = otherCharges;
    }

    public List<ChargeBU> getChargeBU() {
        return chargeBU;
    }

    public void setChargeBU(List<ChargeBU> chargeBU) {
        this.chargeBU = chargeBU;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPublishedFare() {
        return publishedFare;
    }

    public void setPublishedFare(Integer publishedFare) {
        this.publishedFare = publishedFare;
    }

    public Double getCommissionEarned() {
        return commissionEarned;
    }

    public void setCommissionEarned(Double commissionEarned) {
        this.commissionEarned = commissionEarned;
    }

    public Double getPLBEarned() {
        return pLBEarned;
    }

    public void setPLBEarned(Double pLBEarned) {
        this.pLBEarned = pLBEarned;
    }

    public Integer getIncentiveEarned() {
        return incentiveEarned;
    }

    public void setIncentiveEarned(Integer incentiveEarned) {
        this.incentiveEarned = incentiveEarned;
    }

    public Double getOfferedFare() {
        return offeredFare;
    }

    public void setOfferedFare(Double offeredFare) {
        this.offeredFare = offeredFare;
    }

    public Double getTdsOnCommission() {
        return tdsOnCommission;
    }

    public void setTdsOnCommission(Double tdsOnCommission) {
        this.tdsOnCommission = tdsOnCommission;
    }

    public Double getTdsOnPLB() {
        return tdsOnPLB;
    }

    public void setTdsOnPLB(Double tdsOnPLB) {
        this.tdsOnPLB = tdsOnPLB;
    }

    public Integer getTdsOnIncentive() {
        return tdsOnIncentive;
    }

    public void setTdsOnIncentive(Integer tdsOnIncentive) {
        this.tdsOnIncentive = tdsOnIncentive;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getTotalBaggageCharges() {
        return totalBaggageCharges;
    }

    public void setTotalBaggageCharges(Integer totalBaggageCharges) {
        this.totalBaggageCharges = totalBaggageCharges;
    }

    public Integer getTotalMealCharges() {
        return totalMealCharges;
    }

    public void setTotalMealCharges(Integer totalMealCharges) {
        this.totalMealCharges = totalMealCharges;
    }

    public Integer getTotalSeatCharges() {
        return totalSeatCharges;
    }

    public void setTotalSeatCharges(Integer totalSeatCharges) {
        this.totalSeatCharges = totalSeatCharges;
    }

    public Integer getTotalSpecialServiceCharges() {
        return totalSpecialServiceCharges;
    }

    public void setTotalSpecialServiceCharges(Integer totalSpecialServiceCharges) {
        this.totalSpecialServiceCharges = totalSpecialServiceCharges;
    }

}
