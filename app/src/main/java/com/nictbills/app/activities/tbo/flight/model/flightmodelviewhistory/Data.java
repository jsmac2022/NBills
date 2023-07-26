
package com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("publishedFare")
    @Expose
    private String publishedFare;
    @SerializedName("extraCharge")
    @Expose
    private String extraCharge;
    @SerializedName("EndUserIp")
    @Expose
    private Object endUserIp;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("ResultIndex")
    @Expose
    private String resultIndex;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("isLCC")
    @Expose
    private Object isLCC;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("bookingId")
    @Expose
    private String bookingId;
    @SerializedName("pnr")
    @Expose
    private String pnr;
    @SerializedName("flight_number")
    @Expose
    private String flightNumber;
    @SerializedName("airline_code")
    @Expose
    private String airlineCode;



    @SerializedName("flightName")
    @Expose
    private String flightname;

    @SerializedName("seat")
    @Expose
    private Object seat;

    public String getPublishedFare() {
        return publishedFare;
    }

    public void setPublishedFare(String publishedFare) {
        this.publishedFare = publishedFare;
    }

    public String getExtraCharge() {
        return extraCharge;
    }

    public void setExtraCharge(String extraCharge) {
        this.extraCharge = extraCharge;
    }

    public Object getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(Object endUserIp) {
        this.endUserIp = endUserIp;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getIsLCC() {
        return isLCC;
    }

    public void setIsLCC(Object isLCC) {
        this.isLCC = isLCC;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Object getSeat() {
        return seat;
    }

    public void setSeat(Object seat) {
        this.seat = seat;
    }
    public String getFlightname() {
        return flightname;
    }

    public void setFlightname(String flightname) {
        this.flightname = flightname;
    }
}
