
package com.nictbills.app.activities.farmequipment.model.bookingconfirm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingConfirmationResponseModel {

    @SerializedName("order_no")
    @Expose
    private Integer orderNo;
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("rental_time")
    @Expose
    private Object rentalTime;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Object getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(Object rentalTime) {
        this.rentalTime = rentalTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
