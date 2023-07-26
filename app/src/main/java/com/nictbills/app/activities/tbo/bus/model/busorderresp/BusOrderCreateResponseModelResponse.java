
package com.nictbills.app.activities.tbo.bus.model.busorderresp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusOrderCreateResponseModelResponse {

    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("OrderId")
    @Expose
    private String orderId;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
