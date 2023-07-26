
package com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("hotelBookingStatus")
    @Expose
    private String hotelBookingStatus;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("bookingReferenceNo")
    @Expose
    private String bookingReferenceNo;
    @SerializedName("confirmationNo")
    @Expose
    private String confirmationNo;
    @SerializedName("hotelCode")
    @Expose
    private String hotelCode;
    @SerializedName("hotelName")
    @Expose
    private String hotelName;
    @SerializedName("publishedPrice")
    @Expose
    private String publishedPrice;
    @SerializedName("bookingId")
    @Expose
    private String bookingId;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_cancelled")
    @Expose
    private boolean iscanceled;

    @SerializedName("token_id")
    @Expose
    private String tokenid;

    @SerializedName("end_user_ip")
    @Expose
    private String ednuser_ip;

    public String getHotelBookingStatus() {
        return hotelBookingStatus;
    }

    public void setHotelBookingStatus(String hotelBookingStatus) {
        this.hotelBookingStatus = hotelBookingStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBookingReferenceNo() {
        return bookingReferenceNo;
    }

    public void setBookingReferenceNo(String bookingReferenceNo) {
        this.bookingReferenceNo = bookingReferenceNo;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPublishedPrice() {
        return publishedPrice;
    }

    public void setPublishedPrice(String publishedPrice) {
        this.publishedPrice = publishedPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIscanceled() {
        return iscanceled;
    }

    public void setIscanceled(boolean iscanceled) {
        this.iscanceled = iscanceled;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getEdnuser_ip() {
        return ednuser_ip;
    }
    public void setEdnuser_ip(String ednuser_ip) {
        this.ednuser_ip = ednuser_ip;
    }
}
