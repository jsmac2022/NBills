package com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto.create_vpa_order;

public class RechargeVAPOrderRequestDTO {

    private String operator;
    private String amount;
    private String vpa;
    private String number;
    private String useReward;


    public String getUseReward() {
        return useReward;
    }

    public void setUseReward(String useReward) {
        this.useReward = useReward;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVpa() {
        return vpa;
    }

    public void setVpa(String vpa) {
        this.vpa = vpa;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
