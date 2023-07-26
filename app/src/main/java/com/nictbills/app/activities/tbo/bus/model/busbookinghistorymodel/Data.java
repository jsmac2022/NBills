
package com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data   {

    @SerializedName("ResultIndex")
    @Expose
    private Integer resultIndex;
    @SerializedName("Passenger")
    @Expose
    private List<Object> passenger = null;
    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("BoardingPointId")
    @Expose
    private Integer boardingPointId;
    @SerializedName("DropingPointId")
    @Expose
    private Integer dropingPointId;
    @SerializedName("busBookingStatus")
    @Expose
    private String busBookingStatus;
    @SerializedName("invoiceAmount")
    @Expose
    private Double invoiceAmount;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("busId")
    @Expose
    private String busId;
    @SerializedName("ticketNo")
    @Expose
    private String ticketNo;
    @SerializedName("travelOperatorPNR")
    @Expose
    private String travelOperatorPNR;
    @SerializedName("busAmount")
    @Expose
    private Double busAmount;
    @SerializedName("busName")
    @Expose
    private Integer busName;
    @SerializedName("busRegNo")
    @Expose
    private Object busRegNo;
    @SerializedName("bookingDate")
    @Expose
    private Object bookingDate;
    @SerializedName("orderId")
    @Expose
    private String orderId;

    @SerializedName("boardingName")
    @Expose
    private String boardingname;

    @SerializedName("droppingName")
    @Expose
    private String droppingname;

    @SerializedName("travellerName")
    @Expose
    private String travellname;

    @SerializedName("bookId")
    @Expose
    private String busbookid;






    @SerializedName("mobileNo")
    @Expose
    private Object mobileNo;

    public String getBusbookid() {
        return busbookid;
    }

    public void setBusbookid(String busbookid) {
        this.busbookid = busbookid;
    }


    public String getBoardingname() {
        return boardingname;
    }

    public void setBoardingname(String boardingname) {
        this.boardingname = boardingname;
    }

    public String getDroppingname() {
        return droppingname;
    }

    public void setDroppingname(String droppingname) {
        this.droppingname = droppingname;
    }

    public String getTravellname() {
        return travellname;
    }

    public void setTravellname(String travellname) {
        this.travellname = travellname;
    }



    public Integer getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
    }

    public List<Object> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<Object> passenger) {
        this.passenger = passenger;
    }

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
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

    public Integer getBoardingPointId() {
        return boardingPointId;
    }

    public void setBoardingPointId(Integer boardingPointId) {
        this.boardingPointId = boardingPointId;
    }

    public Integer getDropingPointId() {
        return dropingPointId;
    }

    public void setDropingPointId(Integer dropingPointId) {
        this.dropingPointId = dropingPointId;
    }

    public String getBusBookingStatus() {
        return busBookingStatus;
    }

    public void setBusBookingStatus(String busBookingStatus) {
        this.busBookingStatus = busBookingStatus;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTravelOperatorPNR() {
        return travelOperatorPNR;
    }

    public void setTravelOperatorPNR(String travelOperatorPNR) {
        this.travelOperatorPNR = travelOperatorPNR;
    }

    public Double getBusAmount() {
        return busAmount;
    }

    public void setBusAmount(Double busAmount) {
        this.busAmount = busAmount;
    }

    public Integer getBusName() {
        return busName;
    }

    public void setBusName(Integer busName) {
        this.busName = busName;
    }

    public Object getBusRegNo() {
        return busRegNo;
    }

    public void setBusRegNo(Object busRegNo) {
        this.busRegNo = busRegNo;
    }

    public Object getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Object bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

}
