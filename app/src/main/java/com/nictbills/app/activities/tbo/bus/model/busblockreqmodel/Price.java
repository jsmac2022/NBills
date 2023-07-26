
package com.nictbills.app.activities.tbo.bus.model.busblockreqmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("BasePrice")
    @Expose
    private Double basePrice;
    @SerializedName("Tax")
    @Expose
    private Double tax;
    @SerializedName("OtherCharges")
    @Expose
    private Double otherCharges;
    @SerializedName("Discount")
    @Expose
    private Double discount;
    @SerializedName("PublishedPrice")
    @Expose
    private Double publishedPrice;
    @SerializedName("PublishedPriceRoundedOff")
    @Expose
    private Double publishedPriceRoundedOff;
    @SerializedName("OfferedPrice")
    @Expose
    private Double offeredPrice;
    @SerializedName("OfferedPriceRoundedOff")
    @Expose
    private Double offeredPriceRoundedOff;
    @SerializedName("AgentCommission")
    @Expose
    private Double agentCommission;
    @SerializedName("AgentMarkUp")
    @Expose
    private Double agentMarkUp;
    @SerializedName("TDS")
    @Expose
    private Double tds;
    @SerializedName("GST")
    @Expose
    private Gst gst;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(Double publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public Double getPublishedPriceRoundedOff() {
        return publishedPriceRoundedOff;
    }

    public void setPublishedPriceRoundedOff(Double publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
    }

    public Double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Double getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(Double offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public Double getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(Double agentCommission) {
        this.agentCommission = agentCommission;
    }

    public Double getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(Double agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Gst getGst() {
        return gst;
    }

    public void setGst(Gst gst) {
        this.gst = gst;
    }

}
