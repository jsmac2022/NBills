package com.nictbills.app.activities.health_id_abdm.dto.lic.bill_fetch_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LicBillsFetchResponse {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duedate")
    @Expose
    private String duedate;
    @SerializedName("bill_fetch")
    @Expose
    private BillFetch billFetch;
    @SerializedName("ad2")
    @Expose
    private Object ad2;
    @SerializedName("ad3")
    @Expose
    private Object ad3;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public BillFetch getBillFetch() {
        return billFetch;
    }

    public void setBillFetch(BillFetch billFetch) {
        this.billFetch = billFetch;
    }

    public Object getAd2() {
        return ad2;
    }

    public void setAd2(Object ad2) {
        this.ad2 = ad2;
    }

    public Object getAd3() {
        return ad3;
    }

    public void setAd3(Object ad3) {
        this.ad3 = ad3;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
