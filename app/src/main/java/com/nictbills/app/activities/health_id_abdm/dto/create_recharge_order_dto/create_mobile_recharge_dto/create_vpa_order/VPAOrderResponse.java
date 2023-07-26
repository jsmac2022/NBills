package com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto.create_vpa_order;

public class VPAOrderResponse {

    private String code;
    private String message;
    private String merchantTranId;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMerchantTranId() {
        return merchantTranId;
    }

    public void setMerchantTranId(String merchantTranId) {
        this.merchantTranId = merchantTranId;
    }
}
