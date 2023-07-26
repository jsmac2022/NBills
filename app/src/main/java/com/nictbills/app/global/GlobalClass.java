package com.nictbills.app.global;

import android.app.Application;

public class GlobalClass extends Application {

    private String orderId;
    private Integer amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
