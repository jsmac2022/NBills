
package com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("hotelBookingStatus")
    @Expose
    private String hotelBookingStatus;
    @SerializedName("responseErrorMessage")
    @Expose
    private String responseErrorMessage;
    @SerializedName("HotelCode")
    @Expose
    private String hotelCode;
    @SerializedName("HotelRoomsDetails")
    @Expose
    private List<HotelRoomsDetail> hotelRoomsDetails = null;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("GuestNationality")
    @Expose
    private String guestNationality;
    @SerializedName("NoOfRooms")
    @Expose
    private Integer noOfRooms;
    @SerializedName("bookingId")
    @Expose
    private String bookingId;
    @SerializedName("bookingReferenceNo")
    @Expose
    private String bookingReferenceNo;
    @SerializedName("ResultIndex")
    @Expose
    private Integer resultIndex;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("confirmationNo")
    @Expose
    private String confirmationNo;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("IsVoucherBooking")
    @Expose
    private Boolean isVoucherBooking;
    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;

    public String getHotelBookingStatus() {
        return hotelBookingStatus;
    }

    public void setHotelBookingStatus(String hotelBookingStatus) {
        this.hotelBookingStatus = hotelBookingStatus;
    }

    public String getResponseErrorMessage() {
        return responseErrorMessage;
    }

    public void setResponseErrorMessage(String responseErrorMessage) {
        this.responseErrorMessage = responseErrorMessage;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public List<HotelRoomsDetail> getHotelRoomsDetails() {
        return hotelRoomsDetails;
    }

    public void setHotelRoomsDetails(List<HotelRoomsDetail> hotelRoomsDetails) {
        this.hotelRoomsDetails = hotelRoomsDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGuestNationality() {
        return guestNationality;
    }

    public void setGuestNationality(String guestNationality) {
        this.guestNationality = guestNationality;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingReferenceNo() {
        return bookingReferenceNo;
    }

    public void setBookingReferenceNo(String bookingReferenceNo) {
        this.bookingReferenceNo = bookingReferenceNo;
    }

    public Integer getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Boolean getIsVoucherBooking() {
        return isVoucherBooking;
    }

    public void setIsVoucherBooking(Boolean isVoucherBooking) {
        this.isVoucherBooking = isVoucherBooking;
    }

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
    }

}
