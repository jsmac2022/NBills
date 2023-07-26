
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private final static long serialVersionUID = -9213657313882931305L;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Fare withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public float getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(float baseFare) {
        this.baseFare = baseFare;
    }

    public Fare withBaseFare(float baseFare) {
        this.baseFare = baseFare;
        return this;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public Fare withTax(float tax) {
        this.tax = tax;
        return this;
    }

    public List<TaxBreakup> getTaxBreakup() {
        return taxBreakup;
    }

    public void setTaxBreakup(List<TaxBreakup> taxBreakup) {
        this.taxBreakup = taxBreakup;
    }

    public Fare withTaxBreakup(List<TaxBreakup> taxBreakup) {
        this.taxBreakup = taxBreakup;
        return this;
    }

    public float getYQTax() {
        return yQTax;
    }

    public void setYQTax(float yQTax) {
        this.yQTax = yQTax;
    }

    public Fare withYQTax(float yQTax) {
        this.yQTax = yQTax;
        return this;
    }

    public float getAdditionalTxnFeeOfrd() {
        return additionalTxnFeeOfrd;
    }

    public void setAdditionalTxnFeeOfrd(float additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
    }

    public Fare withAdditionalTxnFeeOfrd(float additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
        return this;
    }

    public float getAdditionalTxnFeePub() {
        return additionalTxnFeePub;
    }

    public void setAdditionalTxnFeePub(float additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
    }

    public Fare withAdditionalTxnFeePub(float additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
        return this;
    }

    public float getPGCharge() {
        return pGCharge;
    }

    public void setPGCharge(float pGCharge) {
        this.pGCharge = pGCharge;
    }

    public Fare withPGCharge(float pGCharge) {
        this.pGCharge = pGCharge;
        return this;
    }

    public float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(float otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Fare withOtherCharges(float otherCharges) {
        this.otherCharges = otherCharges;
        return this;
    }

    public List<ChargeBU> getChargeBU() {
        return chargeBU;
    }

    public void setChargeBU(List<ChargeBU> chargeBU) {
        this.chargeBU = chargeBU;
    }

    public Fare withChargeBU(List<ChargeBU> chargeBU) {
        this.chargeBU = chargeBU;
        return this;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Fare withDiscount(float discount) {
        this.discount = discount;
        return this;
    }

    public float getPublishedFare() {
        return publishedFare;
    }

    public void setPublishedFare(float publishedFare) {
        this.publishedFare = publishedFare;
    }

    public Fare withPublishedFare(float publishedFare) {
        this.publishedFare = publishedFare;
        return this;
    }

    public float getCommissionEarned() {
        return commissionEarned;
    }

    public void setCommissionEarned(float commissionEarned) {
        this.commissionEarned = commissionEarned;
    }

    public Fare withCommissionEarned(float commissionEarned) {
        this.commissionEarned = commissionEarned;
        return this;
    }

    public float getPLBEarned() {
        return pLBEarned;
    }

    public void setPLBEarned(float pLBEarned) {
        this.pLBEarned = pLBEarned;
    }

    public Fare withPLBEarned(float pLBEarned) {
        this.pLBEarned = pLBEarned;
        return this;
    }

    public float getIncentiveEarned() {
        return incentiveEarned;
    }

    public void setIncentiveEarned(float incentiveEarned) {
        this.incentiveEarned = incentiveEarned;
    }

    public Fare withIncentiveEarned(float incentiveEarned) {
        this.incentiveEarned = incentiveEarned;
        return this;
    }

    public float getOfferedFare() {
        return offeredFare;
    }

    public void setOfferedFare(float offeredFare) {
        this.offeredFare = offeredFare;
    }

    public Fare withOfferedFare(float offeredFare) {
        this.offeredFare = offeredFare;
        return this;
    }

    public float getTdsOnCommission() {
        return tdsOnCommission;
    }

    public void setTdsOnCommission(float tdsOnCommission) {
        this.tdsOnCommission = tdsOnCommission;
    }

    public Fare withTdsOnCommission(float tdsOnCommission) {
        this.tdsOnCommission = tdsOnCommission;
        return this;
    }

    public float getTdsOnPLB() {
        return tdsOnPLB;
    }

    public void setTdsOnPLB(float tdsOnPLB) {
        this.tdsOnPLB = tdsOnPLB;
    }

    public Fare withTdsOnPLB(float tdsOnPLB) {
        this.tdsOnPLB = tdsOnPLB;
        return this;
    }

    public float getTdsOnIncentive() {
        return tdsOnIncentive;
    }

    public void setTdsOnIncentive(float tdsOnIncentive) {
        this.tdsOnIncentive = tdsOnIncentive;
    }

    public Fare withTdsOnIncentive(float tdsOnIncentive) {
        this.tdsOnIncentive = tdsOnIncentive;
        return this;
    }

    public float getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Fare withServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public float getTotalBaggageCharges() {
        return totalBaggageCharges;
    }

    public void setTotalBaggageCharges(float totalBaggageCharges) {
        this.totalBaggageCharges = totalBaggageCharges;
    }

    public Fare withTotalBaggageCharges(float totalBaggageCharges) {
        this.totalBaggageCharges = totalBaggageCharges;
        return this;
    }

    public float getTotalMealCharges() {
        return totalMealCharges;
    }

    public void setTotalMealCharges(float totalMealCharges) {
        this.totalMealCharges = totalMealCharges;
    }

    public Fare withTotalMealCharges(float totalMealCharges) {
        this.totalMealCharges = totalMealCharges;
        return this;
    }

    public float getTotalSeatCharges() {
        return totalSeatCharges;
    }

    public void setTotalSeatCharges(float totalSeatCharges) {
        this.totalSeatCharges = totalSeatCharges;
    }

    public Fare withTotalSeatCharges(float totalSeatCharges) {
        this.totalSeatCharges = totalSeatCharges;
        return this;
    }

    public float getTotalSpecialServiceCharges() {
        return totalSpecialServiceCharges;
    }

    public void setTotalSpecialServiceCharges(float totalSpecialServiceCharges) {
        this.totalSpecialServiceCharges = totalSpecialServiceCharges;
    }

    public Fare withTotalSpecialServiceCharges(float totalSpecialServiceCharges) {
        this.totalSpecialServiceCharges = totalSpecialServiceCharges;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Fare.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("baseFare");
        sb.append('=');
        sb.append(this.baseFare);
        sb.append(',');
        sb.append("tax");
        sb.append('=');
        sb.append(this.tax);
        sb.append(',');
        sb.append("taxBreakup");
        sb.append('=');
        sb.append(((this.taxBreakup == null)?"<null>":this.taxBreakup));
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
        sb.append("otherCharges");
        sb.append('=');
        sb.append(this.otherCharges);
        sb.append(',');
        sb.append("chargeBU");
        sb.append('=');
        sb.append(((this.chargeBU == null)?"<null>":this.chargeBU));
        sb.append(',');
        sb.append("discount");
        sb.append('=');
        sb.append(this.discount);
        sb.append(',');
        sb.append("publishedFare");
        sb.append('=');
        sb.append(this.publishedFare);
        sb.append(',');
        sb.append("commissionEarned");
        sb.append('=');
        sb.append(this.commissionEarned);
        sb.append(',');
        sb.append("pLBEarned");
        sb.append('=');
        sb.append(this.pLBEarned);
        sb.append(',');
        sb.append("incentiveEarned");
        sb.append('=');
        sb.append(this.incentiveEarned);
        sb.append(',');
        sb.append("offeredFare");
        sb.append('=');
        sb.append(this.offeredFare);
        sb.append(',');
        sb.append("tdsOnCommission");
        sb.append('=');
        sb.append(this.tdsOnCommission);
        sb.append(',');
        sb.append("tdsOnPLB");
        sb.append('=');
        sb.append(this.tdsOnPLB);
        sb.append(',');
        sb.append("tdsOnIncentive");
        sb.append('=');
        sb.append(this.tdsOnIncentive);
        sb.append(',');
        sb.append("serviceFee");
        sb.append('=');
        sb.append(this.serviceFee);
        sb.append(',');
        sb.append("totalBaggageCharges");
        sb.append('=');
        sb.append(this.totalBaggageCharges);
        sb.append(',');
        sb.append("totalMealCharges");
        sb.append('=');
        sb.append(this.totalMealCharges);
        sb.append(',');
        sb.append("totalSeatCharges");
        sb.append('=');
        sb.append(this.totalSeatCharges);
        sb.append(',');
        sb.append("totalSpecialServiceCharges");
        sb.append('=');
        sb.append(this.totalSpecialServiceCharges);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
