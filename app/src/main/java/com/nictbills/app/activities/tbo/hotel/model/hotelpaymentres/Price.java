
package com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("PublishedPrice")
    @Expose
    private Double publishedPrice;
    @SerializedName("TDS")
    @Expose
    private Double tds;
    @SerializedName("Discount")
    @Expose
    private Integer discount;
    @SerializedName("PublishedPriceRoundedOff")
    @Expose
    private Integer publishedPriceRoundedOff;
    @SerializedName("Tax")
    @Expose
    private Double tax;
    @SerializedName("AgentCommission")
    @Expose
    private Double agentCommission;
    @SerializedName("AgentMarkUp")
    @Expose
    private Integer agentMarkUp;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("RoomPrice")
    @Expose
    private Double roomPrice;
    @SerializedName("ServiceTax")
    @Expose
    private Double serviceTax;
    @SerializedName("ExtraGuestCharge")
    @Expose
    private Integer extraGuestCharge;
    @SerializedName("OtherCharges")
    @Expose
    private Double otherCharges;
    @SerializedName("ChildCharge")
    @Expose
    private Integer childCharge;
    @SerializedName("OfferedPriceRoundedOff")
    @Expose
    private Integer offeredPriceRoundedOff;
    @SerializedName("OfferedPrice")
    @Expose
    private Double offeredPrice;

    public Double getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(Double publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
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

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
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

    public Double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(Double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public Integer getExtraGuestCharge() {
        return extraGuestCharge;
    }

    public void setExtraGuestCharge(Integer extraGuestCharge) {
        this.extraGuestCharge = extraGuestCharge;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Integer getChildCharge() {
        return childCharge;
    }

    public void setChildCharge(Integer childCharge) {
        this.childCharge = childCharge;
    }

    public Integer getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(Integer offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public Double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

}
