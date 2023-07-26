
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

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
    private double roomPrice;
    @SerializedName("Tax")
    @Expose
    private double tax;
    @SerializedName("ExtraGuestCharge")
    @Expose
    private double extraGuestCharge;
    @SerializedName("ChildCharge")
    @Expose
    private double childCharge;
    @SerializedName("OtherCharges")
    @Expose
    private double otherCharges;
    @SerializedName("Discount")
    @Expose
    private double discount;
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
    private double agentMarkUp;
    @SerializedName("ServiceTax")
    @Expose
    private double serviceTax;
    @SerializedName("TCS")
    @Expose
    private double tcs;
    @SerializedName("TDS")
    @Expose
    private double tds;
    @SerializedName("ServiceCharge")
    @Expose
    private int serviceCharge;
    @SerializedName("TotalGSTAmount")
    @Expose
    private double totalGSTAmount;
    @SerializedName("GST")
    @Expose
    private Gst gst;
    private final static long serialVersionUID = -4946938235281773788L;

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

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Price withRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
        return this;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Price withTax(double tax) {
        this.tax = tax;
        return this;
    }

    public double getExtraGuestCharge() {
        return extraGuestCharge;
    }

    public void setExtraGuestCharge(double extraGuestCharge) {
        this.extraGuestCharge = extraGuestCharge;
    }

    public Price withExtraGuestCharge(double extraGuestCharge) {
        this.extraGuestCharge = extraGuestCharge;
        return this;
    }

    public double getChildCharge() {
        return childCharge;
    }

    public void setChildCharge(double childCharge) {
        this.childCharge = childCharge;
    }

    public Price withChildCharge(double childCharge) {
        this.childCharge = childCharge;
        return this;
    }

    public double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Price withOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Price withDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public double getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(double publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public Price withPublishedPrice(double publishedPrice) {
        this.publishedPrice = publishedPrice;
        return this;
    }

    public int getPublishedPriceRoundedOff() {
        return publishedPriceRoundedOff;
    }

    public void setPublishedPriceRoundedOff(int publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
    }

    public Price withPublishedPriceRoundedOff(int publishedPriceRoundedOff) {
        this.publishedPriceRoundedOff = publishedPriceRoundedOff;
        return this;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Price withOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
        return this;
    }

    public int getOfferedPriceRoundedOff() {
        return offeredPriceRoundedOff;
    }

    public void setOfferedPriceRoundedOff(int offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
    }

    public Price withOfferedPriceRoundedOff(int offeredPriceRoundedOff) {
        this.offeredPriceRoundedOff = offeredPriceRoundedOff;
        return this;
    }

    public double getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(double agentCommission) {
        this.agentCommission = agentCommission;
    }

    public Price withAgentCommission(double agentCommission) {
        this.agentCommission = agentCommission;
        return this;
    }

    public double getAgentMarkUp() {
        return agentMarkUp;
    }

    public void setAgentMarkUp(double agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
    }

    public Price withAgentMarkUp(double agentMarkUp) {
        this.agentMarkUp = agentMarkUp;
        return this;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public Price withServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
        return this;
    }

    public double getTcs() {
        return tcs;
    }

    public void setTcs(double tcs) {
        this.tcs = tcs;
    }

    public Price withTcs(double tcs) {
        this.tcs = tcs;
        return this;
    }

    public double getTds() {
        return tds;
    }

    public void setTds(double tds) {
        this.tds = tds;
    }

    public Price withTds(double tds) {
        this.tds = tds;
        return this;
    }

    public int getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(int serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Price withServiceCharge(int serviceCharge) {
        this.serviceCharge = serviceCharge;
        return this;
    }

    public double getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(double totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
    }

    public Price withTotalGSTAmount(double totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
        return this;
    }

    public Gst getGst() {
        return gst;
    }

    public void setGst(Gst gst) {
        this.gst = gst;
    }

    public Price withGst(Gst gst) {
        this.gst = gst;
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
        sb.append(this.roomPrice);
        sb.append(',');
        sb.append("tax");
        sb.append('=');
        sb.append(this.tax);
        sb.append(',');
        sb.append("extraGuestCharge");
        sb.append('=');
        sb.append(this.extraGuestCharge);
        sb.append(',');
        sb.append("childCharge");
        sb.append('=');
        sb.append(this.childCharge);
        sb.append(',');
        sb.append("otherCharges");
        sb.append('=');
        sb.append(this.otherCharges);
        sb.append(',');
        sb.append("discount");
        sb.append('=');
        sb.append(this.discount);
        sb.append(',');
        sb.append("publishedPrice");
        sb.append('=');
        sb.append(this.publishedPrice);
        sb.append(',');
        sb.append("publishedPriceRoundedOff");
        sb.append('=');
        sb.append(this.publishedPriceRoundedOff);
        sb.append(',');
        sb.append("offeredPrice");
        sb.append('=');
        sb.append(this.offeredPrice);
        sb.append(',');
        sb.append("offeredPriceRoundedOff");
        sb.append('=');
        sb.append(this.offeredPriceRoundedOff);
        sb.append(',');
        sb.append("agentCommission");
        sb.append('=');
        sb.append(this.agentCommission);
        sb.append(',');
        sb.append("agentMarkUp");
        sb.append('=');
        sb.append(this.agentMarkUp);
        sb.append(',');
        sb.append("serviceTax");
        sb.append('=');
        sb.append(this.serviceTax);
        sb.append(',');
        sb.append("tcs");
        sb.append('=');
        sb.append(this.tcs);
        sb.append(',');
        sb.append("tds");
        sb.append('=');
        sb.append(this.tds);
        sb.append(',');
        sb.append("serviceCharge");
        sb.append('=');
        sb.append(this.serviceCharge);
        sb.append(',');
        sb.append("totalGSTAmount");
        sb.append('=');
        sb.append(this.totalGSTAmount);
        sb.append(',');
        sb.append("gst");
        sb.append('=');
        sb.append(((this.gst == null)?"<null>":this.gst));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
