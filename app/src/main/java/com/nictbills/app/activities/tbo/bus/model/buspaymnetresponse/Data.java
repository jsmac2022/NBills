
package com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("busId")
    @Expose
    private String busId;
    @SerializedName("BoardingPointId")
    @Expose
    private Integer boardingPointId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("busName")
    @Expose
    private Integer busName;
    @SerializedName("invoiceAmount")
    @Expose
    private Integer invoiceAmount;
    @SerializedName("ticketNo")
    @Expose
    private String ticketNo;
    @SerializedName("DropingPointId")
    @Expose
    private Integer dropingPointId;
    @SerializedName("ResultIndex")
    @Expose
    private Integer resultIndex;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("busBookingStatus")
    @Expose
    private String busBookingStatus;
    @SerializedName("Passenger")
    @Expose
    private List<Passenger> passenger = null;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;
    @SerializedName("travelOperatorPNR")
    @Expose
    private String travelOperatorPNR;


    public String getBordingname() {
        return bordingname;
    }

    public void setBordingname(String bordingname) {
        this.bordingname = bordingname;
    }

    public String getDroppingname() {
        return droppingname;
    }

    public void setDroppingname(String droppingname) {
        this.droppingname = droppingname;
    }

    public String getTravellerName() {
        return travellerName;
    }

    public void setTravellerName(String travellerName) {
        this.travellerName = travellerName;
    }

    @SerializedName("boardingName")
    @Expose
    private String bordingname;

    @SerializedName("droppingName")
    @Expose
    private String droppingname;

    @SerializedName("travellerName")
    @Expose
    private String travellerName;


    @SerializedName("busAmount")
    @Expose
    private Integer busAmount;

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Integer getBoardingPointId() {
        return boardingPointId;
    }

    public void setBoardingPointId(Integer boardingPointId) {
        this.boardingPointId = boardingPointId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getBusName() {
        return busName;
    }

    public void setBusName(Integer busName) {
        this.busName = busName;
    }

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getDropingPointId() {
        return dropingPointId;
    }

    public void setDropingPointId(Integer dropingPointId) {
        this.dropingPointId = dropingPointId;
    }

    public Integer getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getBusBookingStatus() {
        return busBookingStatus;
    }

    public void setBusBookingStatus(String busBookingStatus) {
        this.busBookingStatus = busBookingStatus;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
    }

    public String getTravelOperatorPNR() {
        return travelOperatorPNR;
    }

    public void setTravelOperatorPNR(String travelOperatorPNR) {
        this.travelOperatorPNR = travelOperatorPNR;
    }

    public Integer getBusAmount() {
        return busAmount;
    }

    public void setBusAmount(Integer busAmount) {
        this.busAmount = busAmount;
    }

}
