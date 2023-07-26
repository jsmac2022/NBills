
package com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("PublishedPrice")
    @Expose
    private Integer publishedPrice;
    @SerializedName("BasePrice")
    @Expose
    private Integer basePrice;
    @SerializedName("TDS")
    @Expose
    private Integer tds;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("PublishedPriceRoundedOff")
    @Expose
    private Integer publishedPriceRoundedOff;
    @SerializedName("GST")
    @Expose
    private Gst gst;
    @SerializedName("Tax")
    @Expose
    private Integer tax;
    @SerializedName("AgentCommission")
    @Expose
    private Integer agentCommission;
    @SerializedName("AgentMarkUp")
    @Expose
    private Integer agentMarkUp;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("OtherCharges")
    @Expose
    private Integer otherCharges;
    @SerializedName("OfferedPriceRoundedOff")
    @Expose
    private Integer offeredPriceRoundedOff;
    @SerializedName("OfferedPrice")
    @Expose
    private Integer offeredPrice;

    public Integer getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(Integer publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getTds() {
        return tds;
    }

    public void setTds(Integer tds) {
        this.tds = tds;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPublishedPriceRoundedOff() {
        return publishedPriceRoundedOff;
    }

    public void setPublishedPriceRoundedOff(Integer publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
    }

    public Gst getGst() {
        return gst;
    }

    public void setGst(Gst gst) {
        this.gst = gst;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(Integer agentCommission) {
        this.agentCommission = agentCommission;
    }

    public Integer getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(Integer agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Integer otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Integer getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(Integer offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public Integer getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Integer offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

}
