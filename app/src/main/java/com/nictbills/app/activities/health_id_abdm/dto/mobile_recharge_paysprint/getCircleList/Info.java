package com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.getCircleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("circle")
    @Expose
    private String circle;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

}
