
package com.nictbills.app.activities.farmequipment.model.ordercreate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderCreateResponseModel {

    @SerializedName("order_no")
    @Expose
    private Integer orderNo;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}
