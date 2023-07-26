package com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto;

public class SendMobileRechargeDto {

    private String operator;
    private String amount;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
