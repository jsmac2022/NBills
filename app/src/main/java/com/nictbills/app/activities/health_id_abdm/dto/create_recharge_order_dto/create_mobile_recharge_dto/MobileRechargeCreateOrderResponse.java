package com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto;

public class MobileRechargeCreateOrderResponse {

    private String code;
    private String message;
    private String merchantTranId;
    private String refId;
    private String qrString;


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

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getQrString() {
        return qrString;
    }

    public void setQrString(String qrString) {
        this.qrString = qrString;
    }
}
