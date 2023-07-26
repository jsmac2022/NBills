
package com.nictbills.app.activities.farmequipment.model.orderhistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryFarmEquipment {

    @SerializedName("Order_no")
    @Expose
    private String orderNo;

    @SerializedName("Booking_id")
    @Expose
    private String Booking_id;

    @SerializedName("Product_id")
    @Expose
    private String product_id;

    @SerializedName("Userid")
    @Expose
    private String user_id;

    @SerializedName("Submit_date")
    @Expose
    private String submit_date;

    @SerializedName("Rental_time")
    @Expose
    private String rental_time;

    @SerializedName("Pickup_date")
    @Expose
    private String pickup_date;

    @SerializedName("Bill_amount")
    @Expose
    private String bill_amount;

    @SerializedName("Order_status")
    @Expose
    private String Order_status;

    @SerializedName("Order_date")
    @Expose
    private String Order_date;
    @SerializedName("Equipment_Name")
    @Expose
    private String equipment_name;


    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBooking_id() {
        return Booking_id;
    }

    public void setBooking_id(String booking_id) {
        Booking_id = booking_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getRental_time() {
        return rental_time;
    }

    public void setRental_time(String rental_time) {
        this.rental_time = rental_time;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(String bill_amount) {
        this.bill_amount = bill_amount;
    }

    public String getOrder_status() {
        return Order_status;
    }

    public void setOrder_status(String order_status) {
        Order_status = order_status;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }








}
