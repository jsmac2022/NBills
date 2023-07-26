
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable
{

    @SerializedName("IsHoldAllowedWithSSR")
    @Expose
    private Boolean isHoldAllowedWithSSR;
    @SerializedName("ResultIndex")
    @Expose
    private String resultIndex;
    @SerializedName("Source")
    @Expose
    private float source;
    @SerializedName("IsLCC")
    @Expose
    private Boolean isLCC;
    @SerializedName("IsRefundable")
    @Expose
    private Boolean isRefundable;
    @SerializedName("IsPanRequiredAtBook")
    @Expose
    private Boolean isPanRequiredAtBook;
    @SerializedName("IsPanRequiredAtTicket")
    @Expose
    private Boolean isPanRequiredAtTicket;
    @SerializedName("IsPassportRequiredAtBook")
    @Expose
    private Boolean isPassportRequiredAtBook;
    @SerializedName("IsPassportRequiredAtTicket")
    @Expose
    private Boolean isPassportRequiredAtTicket;
    @SerializedName("GSTAllowed")
    @Expose
    private Boolean gSTAllowed;
    @SerializedName("IsCouponAppilcable")
    @Expose
    private Boolean isCouponAppilcable;
    @SerializedName("IsGSTMandatory")
    @Expose
    private Boolean isGSTMandatory;
    @SerializedName("AirlineRemark")
    @Expose
    private String airlineRemark;
    @SerializedName("ResultFareType")
    @Expose
    private String resultFareType;
    @SerializedName("Fare")
    @Expose
    private Fare fare;
    @SerializedName("FareBreakdown")
    @Expose
    private List<FareBreakdown> fareBreakdown = null;
    @SerializedName("Segments")
    @Expose
    private List<List<Segment>> segments = null;
    @SerializedName("LastTicketDate")
    @Expose
    private String lastTicketDate;
    @SerializedName("TicketAdvisory")
    @Expose
    private String ticketAdvisory;
    @SerializedName("FareRules")
    @Expose
    private List<FareRule> fareRules = null;
    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("MiniFareRules")
    @Expose
    private List<List<MiniFareRule>> miniFareRules = null;
    @SerializedName("ValidatingAirline")
    @Expose
    private String validatingAirline;
    @SerializedName("FareClassification")
    @Expose
    private FareClassification__1 fareClassification;
    @SerializedName("IsUpsellAllowed")
    @Expose
    private Boolean isUpsellAllowed;
    @SerializedName("PenaltyCharges")
    @Expose
    private PenaltyCharges penaltyCharges;
    private final static long serialVersionUID = -4360176179505739764L;

    public Boolean getIsHoldAllowedWithSSR() {
        return isHoldAllowedWithSSR;
    }

    public void setIsHoldAllowedWithSSR(Boolean isHoldAllowedWithSSR) {
        this.isHoldAllowedWithSSR = isHoldAllowedWithSSR;
    }

    public String getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
    }

    public float getSource() {
        return source;
    }

    public void setSource(float source) {
        this.source = source;
    }

    public Boolean getIsLCC() {
        return isLCC;
    }

    public void setIsLCC(Boolean isLCC) {
        this.isLCC = isLCC;
    }

    public Boolean getIsRefundable() {
        return isRefundable;
    }

    public void setIsRefundable(Boolean isRefundable) {
        this.isRefundable = isRefundable;
    }

    public Boolean getIsPanRequiredAtBook() {
        return isPanRequiredAtBook;
    }

    public void setIsPanRequiredAtBook(Boolean isPanRequiredAtBook) {
        this.isPanRequiredAtBook = isPanRequiredAtBook;
    }

    public Boolean getIsPanRequiredAtTicket() {
        return isPanRequiredAtTicket;
    }

    public void setIsPanRequiredAtTicket(Boolean isPanRequiredAtTicket) {
        this.isPanRequiredAtTicket = isPanRequiredAtTicket;
    }

    public Boolean getIsPassportRequiredAtBook() {
        return isPassportRequiredAtBook;
    }

    public void setIsPassportRequiredAtBook(Boolean isPassportRequiredAtBook) {
        this.isPassportRequiredAtBook = isPassportRequiredAtBook;
    }

    public Boolean getIsPassportRequiredAtTicket() {
        return isPassportRequiredAtTicket;
    }

    public void setIsPassportRequiredAtTicket(Boolean isPassportRequiredAtTicket) {
        this.isPassportRequiredAtTicket = isPassportRequiredAtTicket;
    }

    public Boolean getGSTAllowed() {
        return gSTAllowed;
    }

    public void setGSTAllowed(Boolean gSTAllowed) {
        this.gSTAllowed = gSTAllowed;
    }

    public Boolean getIsCouponAppilcable() {
        return isCouponAppilcable;
    }

    public void setIsCouponAppilcable(Boolean isCouponAppilcable) {
        this.isCouponAppilcable = isCouponAppilcable;
    }

    public Boolean getIsGSTMandatory() {
        return isGSTMandatory;
    }

    public void setIsGSTMandatory(Boolean isGSTMandatory) {
        this.isGSTMandatory = isGSTMandatory;
    }

    public String getAirlineRemark() {
        return airlineRemark;
    }

    public void setAirlineRemark(String airlineRemark) {
        this.airlineRemark = airlineRemark;
    }

    public String getResultFareType() {
        return resultFareType;
    }

    public void setResultFareType(String resultFareType) {
        this.resultFareType = resultFareType;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public List<FareBreakdown> getFareBreakdown() {
        return fareBreakdown;
    }

    public void setFareBreakdown(List<FareBreakdown> fareBreakdown) {
        this.fareBreakdown = fareBreakdown;
    }

    public List<List<Segment>> getSegments() {
        return segments;
    }

    public void setSegments(List<List<Segment>> segments) {
        this.segments = segments;
    }

    public String getLastTicketDate() {
        return lastTicketDate;
    }

    public void setLastTicketDate(String lastTicketDate) {
        this.lastTicketDate = lastTicketDate;
    }

    public String getTicketAdvisory() {
        return ticketAdvisory;
    }

    public void setTicketAdvisory(String ticketAdvisory) {
        this.ticketAdvisory = ticketAdvisory;
    }

    public List<FareRule> getFareRules() {
        return fareRules;
    }

    public void setFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public List<List<MiniFareRule>> getMiniFareRules() {
        return miniFareRules;
    }

    public void setMiniFareRules(List<List<MiniFareRule>> miniFareRules) {
        this.miniFareRules = miniFareRules;
    }

    public String getValidatingAirline() {
        return validatingAirline;
    }

    public void setValidatingAirline(String validatingAirline) {
        this.validatingAirline = validatingAirline;
    }

    public FareClassification__1 getFareClassification() {
        return fareClassification;
    }

    public void setFareClassification(FareClassification__1 fareClassification) {
        this.fareClassification = fareClassification;
    }

    public Boolean getIsUpsellAllowed() {
        return isUpsellAllowed;
    }

    public void setIsUpsellAllowed(Boolean isUpsellAllowed) {
        this.isUpsellAllowed = isUpsellAllowed;
    }

    public PenaltyCharges getPenaltyCharges() {
        return penaltyCharges;
    }

    public void setPenaltyCharges(PenaltyCharges penaltyCharges) {
        this.penaltyCharges = penaltyCharges;
    }

}
