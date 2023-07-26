
package com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price implements Serializable
{
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("RoomPrice")
    @Expose
    private String roomPrice;
    @SerializedName("Tax")
    @Expose
    private String tax;
    @SerializedName("ExtraGuestCharge")
    @Expose
    private String extraGuestCharge;
    @SerializedName("ChildCharge")
    @Expose
    private String childCharge;
    @SerializedName("OtherCharges")
    @Expose
    private String otherCharges;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("PublishedPrice")
    @Expose
    private String publishedPrice;
    @SerializedName("PublishedPriceRoundedOff")
    @Expose
    private String publishedPriceRoundedOff;
    @SerializedName("OfferedPrice")
    @Expose
    private String offeredPrice;
    @SerializedName("OfferedPriceRoundedOff")
    @Expose
    private String offeredPriceRoundedOff;
    @SerializedName("AgentCommission")
    @Expose
    private String agentCommission;
    @SerializedName("AgentMarkUp")
    @Expose
    private String agentMarkUp;

    @SerializedName("ServiceTax")
    @Expose
    private String servicetax;

    @SerializedName("TDS")
    @Expose
    private String tds;
    private final static long serialVersionUID = 1581283775516198692L;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Price withCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Price withRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
        return this;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Price withTax(String tax) {
        this.tax = tax;
        return this;
    }

    public String getExtraGuestCharge() {
        return extraGuestCharge;
    }

    public void setExtraGuestCharge(String extraGuestCharge) {
        this.extraGuestCharge = extraGuestCharge;
    }

    public Price withExtraGuestCharge(String extraGuestCharge) {
        this.extraGuestCharge = extraGuestCharge;
        return this;
    }

    public String getChildCharge() {
        return childCharge;
    }

    public void setChildCharge(String childCharge) {
        this.childCharge = childCharge;
    }

    public Price withChildCharge(String childCharge) {
        this.childCharge = childCharge;
        return this;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Price withOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Price withDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(String publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public Price withPublishedPrice(String publishedPrice) {
        this.publishedPrice = publishedPrice;
        return this;
    }

    public String getPublishedPriceRoundedOff() {
        return publishedPriceRoundedOff;
    }

    public void setPublishedPriceRoundedOff(String publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
    }

    public Price withPublishedPriceRoundedOff(String publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
        return this;
    }

    public String getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(String offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Price withOfferedPrice(String offeredPrice) {
        this.offeredPrice = offeredPrice;
        return this;
    }

    public String getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(String offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public Price withOfferedPriceRoundedOff(String offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
        return this;
    }

    public String getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(String agentCommission) {
        this.agentCommission = agentCommission;
    }

    public Price withAgentCommission(String agentCommission) {
        this.agentCommission = agentCommission;
        return this;
    }

    public String getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(String agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
    }

    public Price withAgentMarkUp(String agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
        return this;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public Price withTds(String tds) {
        this.tds = tds;
        return this;
    }
    public String getServicetax() {
        return servicetax;
    }
    public void setServicetax(String servicetax) {
        this.servicetax = servicetax;
    }
    public Price withServicetax(String servicetax) {
        this.servicetax = servicetax;
        return this;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Price.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currencyCode");
        sb.append('=');
        sb.append(((this.currencyCode == null)?"<null>":this.currencyCode));
        sb.append(',');
        sb.append("roomPrice");
        sb.append('=');
        sb.append(((this.roomPrice == null)?"<null>":this.roomPrice));
        sb.append(',');
        sb.append("tax");
        sb.append('=');
        sb.append(((this.tax == null)?"<null>":this.tax));
        sb.append(',');
        sb.append("extraGuestCharge");
        sb.append('=');
        sb.append(((this.extraGuestCharge == null)?"<null>":this.extraGuestCharge));
        sb.append(',');
        sb.append("childCharge");
        sb.append('=');
        sb.append(((this.childCharge == null)?"<null>":this.childCharge));
        sb.append(',');
        sb.append("otherCharges");
        sb.append('=');
        sb.append(((this.otherCharges == null)?"<null>":this.otherCharges));
        sb.append(',');
        sb.append("discount");
        sb.append('=');
        sb.append(((this.discount == null)?"<null>":this.discount));
        sb.append(',');
        sb.append("publishedPrice");
        sb.append('=');
        sb.append(((this.publishedPrice == null)?"<null>":this.publishedPrice));
        sb.append(',');
        sb.append("publishedPriceRoundedOff");
        sb.append('=');
        sb.append(((this.publishedPriceRoundedOff == null)?"<null>":this.publishedPriceRoundedOff));
        sb.append(',');
        sb.append("offeredPrice");
        sb.append('=');
        sb.append(((this.offeredPrice == null)?"<null>":this.offeredPrice));
        sb.append(',');
        sb.append("offeredPriceRoundedOff");
        sb.append('=');
        sb.append(((this.offeredPriceRoundedOff == null)?"<null>":this.offeredPriceRoundedOff));
        sb.append(',');
        sb.append("agentCommission");
        sb.append('=');
        sb.append(((this.agentCommission == null)?"<null>":this.agentCommission));
        sb.append(',');
        sb.append("agentMarkUp");
        sb.append('=');
        sb.append(((this.agentMarkUp == null)?"<null>":this.agentMarkUp));
        sb.append(',');
        sb.append("tds");
        sb.append('=');
        sb.append(((this.tds == null)?"<null>":this.tds));
        sb.append(',');
        sb.append("servicetax");
        sb.append('=');
        sb.append(((this.servicetax == null)?"<null>":this.servicetax));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
