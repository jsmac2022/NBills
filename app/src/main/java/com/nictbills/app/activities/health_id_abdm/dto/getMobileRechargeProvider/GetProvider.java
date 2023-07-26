package com.nictbills.app.activities.health_id_abdm.dto.getMobileRechargeProvider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProvider {

    @SerializedName("operators")
    @Expose
    private List<Operator> operators = null;

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

}
