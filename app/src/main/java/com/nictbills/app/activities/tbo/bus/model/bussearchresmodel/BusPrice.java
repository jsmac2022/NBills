
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusPrice implements Serializable
{

    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("BasePrice")
    @Expose
    private double basePrice;
    @SerializedName("Tax")
    @Expose
    private int tax;
    @SerializedName("OtherCharges")
    @Expose
    private int otherCharges;
    @SerializedName("Discount")
    @Expose
    private int discount;
    @SerializedName("PublishedPrice")
    @Expose
    private double publishedPrice;
    @SerializedName("PublishedPriceRoundedOff")
    @Expose
    private int publishedPriceRoundedOff;
    @SerializedName("OfferedPrice")
    @Expose
    private double offeredPrice;
    @SerializedName("OfferedPriceRoundedOff")
    @Expose
    private int offeredPriceRoundedOff;
    @SerializedName("AgentCommission")
    @Expose
    private double agentCommission;
    @SerializedName("AgentMarkUp")
    @Expose
    private int agentMarkUp;
    @SerializedName("TDS")
    @Expose
    private double tds;
    @SerializedName("GST")
    @Expose
    private Gst gst;
    private final static long serialVersionUID = 2250261563409438128L;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BusPrice withCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public BusPrice withBasePrice(double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public BusPrice withTax(int tax) {
        this.tax = tax;
        return this;
    }

    public int getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(int otherCharges) {
        this.otherCharges = otherCharges;
    }

    public BusPrice withOtherCharges(int otherCharges) {
        this.otherCharges = otherCharges;
        return this;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public BusPrice withDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public double getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(double publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public BusPrice withPublishedPrice(double publishedPrice) {
        this.publishedPrice = publishedPrice;
        return this;
    }

    public int getPublishedPriceRoundedOff() {
        return publishedPriceRoundedOff;
    }

    public void setPublishedPriceRoundedOff(int publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
    }

    public BusPrice withPublishedPriceRoundedOff(int publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
        return this;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public BusPrice withOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
        return this;
    }

    public int getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(int offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public BusPrice withOfferedPriceRoundedOff(int offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
        return this;
    }

    public double getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(double agentCommission) {
        this.agentCommission = agentCommission;
    }

    public BusPrice withAgentCommission(double agentCommission) {
        this.agentCommission = agentCommission;
        return this;
    }

    public int getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(int agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
    }

    public BusPrice withAgentMarkUp(int agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
        return this;
    }

    public double getTds() {
        return tds;
    }

    public void setTds(double tds) {
        this.tds = tds;
    }

    public BusPrice withTds(double tds) {
        this.tds = tds;
        return this;
    }

    public Gst getGst() {
        return gst;
    }

    public void setGst(Gst gst) {
        this.gst = gst;
    }

    public BusPrice withGst(Gst gst) {
        this.gst = gst;
        return this;
    }

}
