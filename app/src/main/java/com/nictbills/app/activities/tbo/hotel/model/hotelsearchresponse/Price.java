
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("RoomPrice")
    @Expose
    private Double roomPrice;
    @SerializedName("Tax")
    @Expose
    private Double tax;
    @SerializedName("ExtraGuestCharge")
    @Expose
    private Double extraGuestCharge;
    @SerializedName("ChildCharge")
    @Expose
    private Double childCharge;
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
    private Double agentMarkUp;
    @SerializedName("ServiceTax")
    @Expose
    private Double serviceTax;
    @SerializedName("TCS")
    @Expose
    private Double tcs;
    @SerializedName("TDS")
    @Expose
    private Double tds;
    @SerializedName("ServiceCharge")
    @Expose
    private Integer serviceCharge;
    @SerializedName("TotalGSTAmount")
    @Expose
    private Double totalGSTAmount;
    @SerializedName("GST")
    @Expose
    private Gst gst;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getExtraGuestCharge() {
        return extraGuestCharge;
    }

    public void setExtraGuestCharge(Double extraGuestCharge) {
        this.extraGuestCharge = extraGuestCharge;
    }

    public Double getChildCharge() {
        return childCharge;
    }

    public void setChildCharge(Double childCharge) {
        this.childCharge = childCharge;
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

    public Double getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(Double agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
    }

    public Double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(Double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public Double getTcs() {
        return tcs;
    }

    public void setTcs(Double tcs) {
        this.tcs = tcs;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Integer getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Integer serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(Double totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public Gst getGst() {
        return gst;
    }

    public void setGst(Gst gst) {
        this.gst = gst;
    }

}
