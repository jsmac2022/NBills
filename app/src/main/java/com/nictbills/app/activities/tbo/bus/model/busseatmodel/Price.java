
package com.nictbills.app.activities.tbo.bus.model.busseatmodel;

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
    private Integer tax;
    @SerializedName("OtherCharges")
    @Expose
    private Integer otherCharges;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("PublishedPrice")
    @Expose
    private Double publishedPrice;
    @SerializedName("PublishedPriceRoundedOff")
    @Expose
    private Integer publishedPriceRoundedOff;
    @SerializedName("OfferedPrice")
    @Expose
    private Double offeredPrice;
    @SerializedName("OfferedPriceRoundedOff")
    @Expose
    private Integer offeredPriceRoundedOff;
    @SerializedName("AgentCommission")
    @Expose
    private Double agentCommission;
    @SerializedName("AgentMarkUp")
    @Expose
    private Integer agentMarkUp;
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

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Integer otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(Double publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public Integer getPublishedPriceRoundedOff() {
        return publishedPriceRoundedOff;
    }

    public void setPublishedPriceRoundedOff(Integer publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
    }

    public Double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Integer getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(Integer offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public Double getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(Double agentCommission) {
        this.agentCommission = agentCommission;
    }

    public Integer getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(Integer agentMarkUp) {
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
