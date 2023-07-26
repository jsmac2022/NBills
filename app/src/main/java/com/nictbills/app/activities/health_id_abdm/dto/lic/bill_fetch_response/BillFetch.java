package com.nictbills.app.activities.health_id_abdm.dto.lic.bill_fetch_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillFetch {

    @SerializedName("billAmount")
    @Expose
    private String billAmount;
    @SerializedName("billnetamount")
    @Expose
    private String billnetamount;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;
    @SerializedName("maxBillAmount")
    @Expose
    private Integer maxBillAmount;
    @SerializedName("acceptPayment")
    @Expose
    private Boolean acceptPayment;
    @SerializedName("acceptPartPay")
    @Expose
    private Boolean acceptPartPay;
    @SerializedName("cellNumber")
    @Expose
    private String cellNumber;
    @SerializedName("userName")
    @Expose
    private String userName;

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillnetamount() {
        return billnetamount;
    }

    public void setBillnetamount(String billnetamount) {
        this.billnetamount = billnetamount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getMaxBillAmount() {
        return maxBillAmount;
    }

    public void setMaxBillAmount(Integer maxBillAmount) {
        this.maxBillAmount = maxBillAmount;
    }

    public Boolean getAcceptPayment() {
        return acceptPayment;
    }

    public void setAcceptPayment(Boolean acceptPayment) {
        this.acceptPayment = acceptPayment;
    }

    public Boolean getAcceptPartPay() {
        return acceptPartPay;
    }

    public void setAcceptPartPay(Boolean acceptPartPay) {
        this.acceptPartPay = acceptPartPay;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
