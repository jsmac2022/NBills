package com.nictbills.app.activities.health_id_abdm.dto.lic.create_lic_order;

public class CreateQrRequest {

    private String email,amount,canumber,useReward;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCanumber() {
        return canumber;
    }

    public void setCanumber(String canumber) {
        this.canumber = canumber;
    }

    public String getUseReward() {
        return useReward;
    }

    public void setUseReward(String useReward) {
        this.useReward = useReward;
    }
}
