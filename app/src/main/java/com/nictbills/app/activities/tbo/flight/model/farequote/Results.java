
package com.nictbills.app.activities.tbo.flight.model.farequote;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results implements Serializable
{

    @SerializedName("IsHoldAllowedWithSSR")
    @Expose
    private boolean isHoldAllowedWithSSR;
    @SerializedName("ResultIndex")
    @Expose
    private String resultIndex;
    @SerializedName("Source")
    @Expose
    private int source;
    @SerializedName("IsLCC")
    @Expose
    private boolean isLCC;
    @SerializedName("IsRefundable")
    @Expose
    private boolean isRefundable;
    @SerializedName("IsPanRequiredAtBook")
    @Expose
    private boolean isPanRequiredAtBook;
    @SerializedName("IsPanRequiredAtTicket")
    @Expose
    private boolean isPanRequiredAtTicket;
    @SerializedName("IsPassportRequiredAtBook")
    @Expose
    private boolean isPassportRequiredAtBook;
    @SerializedName("IsPassportRequiredAtTicket")
    @Expose
    private boolean isPassportRequiredAtTicket;
    @SerializedName("GSTAllowed")
    @Expose
    private boolean gSTAllowed;
    @SerializedName("IsCouponAppilcable")
    @Expose
    private boolean isCouponAppilcable;
    @SerializedName("IsGSTMandatory")
    @Expose
    private boolean isGSTMandatory;
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
    private Object lastTicketDate;
    @SerializedName("TicketAdvisory")
    @Expose
    private Object ticketAdvisory;
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
    private final static long serialVersionUID = -5535821043897234293L;

    public boolean isIsHoldAllowedWithSSR() {
        return isHoldAllowedWithSSR;
    }

    public void setIsHoldAllowedWithSSR(boolean isHoldAllowedWithSSR) {
        this.isHoldAllowedWithSSR = isHoldAllowedWithSSR;
    }

    public Results withIsHoldAllowedWithSSR(boolean isHoldAllowedWithSSR) {
        this.isHoldAllowedWithSSR = isHoldAllowedWithSSR;
        return this;
    }

    public String getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
    }

    public Results withResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
        return this;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Results withSource(int source) {
        this.source = source;
        return this;
    }

    public boolean isIsLCC() {
        return isLCC;
    }

    public void setIsLCC(boolean isLCC) {
        this.isLCC = isLCC;
    }

    public Results withIsLCC(boolean isLCC) {
        this.isLCC = isLCC;
        return this;
    }

    public boolean isIsRefundable() {
        return isRefundable;
    }

    public void setIsRefundable(boolean isRefundable) {
        this.isRefundable = isRefundable;
    }

    public Results withIsRefundable(boolean isRefundable) {
        this.isRefundable = isRefundable;
        return this;
    }

    public boolean isIsPanRequiredAtBook() {
        return isPanRequiredAtBook;
    }

    public void setIsPanRequiredAtBook(boolean isPanRequiredAtBook) {
        this.isPanRequiredAtBook = isPanRequiredAtBook;
    }

    public Results withIsPanRequiredAtBook(boolean isPanRequiredAtBook) {
        this.isPanRequiredAtBook = isPanRequiredAtBook;
        return this;
    }

    public boolean isIsPanRequiredAtTicket() {
        return isPanRequiredAtTicket;
    }

    public void setIsPanRequiredAtTicket(boolean isPanRequiredAtTicket) {
        this.isPanRequiredAtTicket = isPanRequiredAtTicket;
    }

    public Results withIsPanRequiredAtTicket(boolean isPanRequiredAtTicket) {
        this.isPanRequiredAtTicket = isPanRequiredAtTicket;
        return this;
    }

    public boolean isIsPassportRequiredAtBook() {
        return isPassportRequiredAtBook;
    }

    public void setIsPassportRequiredAtBook(boolean isPassportRequiredAtBook) {
        this.isPassportRequiredAtBook = isPassportRequiredAtBook;
    }

    public Results withIsPassportRequiredAtBook(boolean isPassportRequiredAtBook) {
        this.isPassportRequiredAtBook = isPassportRequiredAtBook;
        return this;
    }

    public boolean isIsPassportRequiredAtTicket() {
        return isPassportRequiredAtTicket;
    }

    public void setIsPassportRequiredAtTicket(boolean isPassportRequiredAtTicket) {
        this.isPassportRequiredAtTicket = isPassportRequiredAtTicket;
    }

    public Results withIsPassportRequiredAtTicket(boolean isPassportRequiredAtTicket) {
        this.isPassportRequiredAtTicket = isPassportRequiredAtTicket;
        return this;
    }

    public boolean isGSTAllowed() {
        return gSTAllowed;
    }

    public void setGSTAllowed(boolean gSTAllowed) {
        this.gSTAllowed = gSTAllowed;
    }

    public Results withGSTAllowed(boolean gSTAllowed) {
        this.gSTAllowed = gSTAllowed;
        return this;
    }

    public boolean isIsCouponAppilcable() {
        return isCouponAppilcable;
    }

    public void setIsCouponAppilcable(boolean isCouponAppilcable) {
        this.isCouponAppilcable = isCouponAppilcable;
    }

    public Results withIsCouponAppilcable(boolean isCouponAppilcable) {
        this.isCouponAppilcable = isCouponAppilcable;
        return this;
    }

    public boolean isIsGSTMandatory() {
        return isGSTMandatory;
    }

    public void setIsGSTMandatory(boolean isGSTMandatory) {
        this.isGSTMandatory = isGSTMandatory;
    }

    public Results withIsGSTMandatory(boolean isGSTMandatory) {
        this.isGSTMandatory = isGSTMandatory;
        return this;
    }

    public String getAirlineRemark() {
        return airlineRemark;
    }

    public void setAirlineRemark(String airlineRemark) {
        this.airlineRemark = airlineRemark;
    }

    public Results withAirlineRemark(String airlineRemark) {
        this.airlineRemark = airlineRemark;
        return this;
    }

    public String getResultFareType() {
        return resultFareType;
    }

    public void setResultFareType(String resultFareType) {
        this.resultFareType = resultFareType;
    }

    public Results withResultFareType(String resultFareType) {
        this.resultFareType = resultFareType;
        return this;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public Results withFare(Fare fare) {
        this.fare = fare;
        return this;
    }

    public List<FareBreakdown> getFareBreakdown() {
        return fareBreakdown;
    }

    public void setFareBreakdown(List<FareBreakdown> fareBreakdown) {
        this.fareBreakdown = fareBreakdown;
    }

    public Results withFareBreakdown(List<FareBreakdown> fareBreakdown) {
        this.fareBreakdown = fareBreakdown;
        return this;
    }

    public List<List<Segment>> getSegments() {
        return segments;
    }

    public void setSegments(List<List<Segment>> segments) {
        this.segments = segments;
    }

    public Results withSegments(List<List<Segment>> segments) {
        this.segments = segments;
        return this;
    }

    public Object getLastTicketDate() {
        return lastTicketDate;
    }

    public void setLastTicketDate(Object lastTicketDate) {
        this.lastTicketDate = lastTicketDate;
    }

    public Results withLastTicketDate(Object lastTicketDate) {
        this.lastTicketDate = lastTicketDate;
        return this;
    }

    public Object getTicketAdvisory() {
        return ticketAdvisory;
    }

    public void setTicketAdvisory(Object ticketAdvisory) {
        this.ticketAdvisory = ticketAdvisory;
    }

    public Results withTicketAdvisory(Object ticketAdvisory) {
        this.ticketAdvisory = ticketAdvisory;
        return this;
    }

    public List<FareRule> getFareRules() {
        return fareRules;
    }

    public void setFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
    }

    public Results withFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
        return this;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Results withAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    public List<List<MiniFareRule>> getMiniFareRules() {
        return miniFareRules;
    }

    public void setMiniFareRules(List<List<MiniFareRule>> miniFareRules) {
        this.miniFareRules = miniFareRules;
    }

    public Results withMiniFareRules(List<List<MiniFareRule>> miniFareRules) {
        this.miniFareRules = miniFareRules;
        return this;
    }

    public String getValidatingAirline() {
        return validatingAirline;
    }

    public void setValidatingAirline(String validatingAirline) {
        this.validatingAirline = validatingAirline;
    }

    public Results withValidatingAirline(String validatingAirline) {
        this.validatingAirline = validatingAirline;
        return this;
    }

    public FareClassification__1 getFareClassification() {
        return fareClassification;
    }

    public void setFareClassification(FareClassification__1 fareClassification) {
        this.fareClassification = fareClassification;
    }

    public Results withFareClassification(FareClassification__1 fareClassification) {
        this.fareClassification = fareClassification;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Results.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("isHoldAllowedWithSSR");
        sb.append('=');
        sb.append(this.isHoldAllowedWithSSR);
        sb.append(',');
        sb.append("resultIndex");
        sb.append('=');
        sb.append(((this.resultIndex == null)?"<null>":this.resultIndex));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(this.source);
        sb.append(',');
        sb.append("isLCC");
        sb.append('=');
        sb.append(this.isLCC);
        sb.append(',');
        sb.append("isRefundable");
        sb.append('=');
        sb.append(this.isRefundable);
        sb.append(',');
        sb.append("isPanRequiredAtBook");
        sb.append('=');
        sb.append(this.isPanRequiredAtBook);
        sb.append(',');
        sb.append("isPanRequiredAtTicket");
        sb.append('=');
        sb.append(this.isPanRequiredAtTicket);
        sb.append(',');
        sb.append("isPassportRequiredAtBook");
        sb.append('=');
        sb.append(this.isPassportRequiredAtBook);
        sb.append(',');
        sb.append("isPassportRequiredAtTicket");
        sb.append('=');
        sb.append(this.isPassportRequiredAtTicket);
        sb.append(',');
        sb.append("gSTAllowed");
        sb.append('=');
        sb.append(this.gSTAllowed);
        sb.append(',');
        sb.append("isCouponAppilcable");
        sb.append('=');
        sb.append(this.isCouponAppilcable);
        sb.append(',');
        sb.append("isGSTMandatory");
        sb.append('=');
        sb.append(this.isGSTMandatory);
        sb.append(',');
        sb.append("airlineRemark");
        sb.append('=');
        sb.append(((this.airlineRemark == null)?"<null>":this.airlineRemark));
        sb.append(',');
        sb.append("resultFareType");
        sb.append('=');
        sb.append(((this.resultFareType == null)?"<null>":this.resultFareType));
        sb.append(',');
        sb.append("fare");
        sb.append('=');
        sb.append(((this.fare == null)?"<null>":this.fare));
        sb.append(',');
        sb.append("fareBreakdown");
        sb.append('=');
        sb.append(((this.fareBreakdown == null)?"<null>":this.fareBreakdown));
        sb.append(',');
        sb.append("segments");
        sb.append('=');
        sb.append(((this.segments == null)?"<null>":this.segments));
        sb.append(',');
        sb.append("lastTicketDate");
        sb.append('=');
        sb.append(((this.lastTicketDate == null)?"<null>":this.lastTicketDate));
        sb.append(',');
        sb.append("ticketAdvisory");
        sb.append('=');
        sb.append(((this.ticketAdvisory == null)?"<null>":this.ticketAdvisory));
        sb.append(',');
        sb.append("fareRules");
        sb.append('=');
        sb.append(((this.fareRules == null)?"<null>":this.fareRules));
        sb.append(',');
        sb.append("airlineCode");
        sb.append('=');
        sb.append(((this.airlineCode == null)?"<null>":this.airlineCode));
        sb.append(',');
        sb.append("miniFareRules");
        sb.append('=');
        sb.append(((this.miniFareRules == null)?"<null>":this.miniFareRules));
        sb.append(',');
        sb.append("validatingAirline");
        sb.append('=');
        sb.append(((this.validatingAirline == null)?"<null>":this.validatingAirline));
        sb.append(',');
        sb.append("fareClassification");
        sb.append('=');
        sb.append(((this.fareClassification == null)?"<null>":this.fareClassification));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
