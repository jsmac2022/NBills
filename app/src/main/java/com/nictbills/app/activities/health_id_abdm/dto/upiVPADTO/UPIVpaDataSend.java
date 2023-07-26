package com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO;

public class UPIVpaDataSend {

    private String code;
    private String message;
    private String merchantTranId;
    private String enc_data;
    private String vpa;
    private String service;
    private String useReward;

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

    public String getEnc_data() {
        return enc_data;
    }

    public void setEnc_data(String enc_data) {
        this.enc_data = enc_data;
    }

    public String getVpa() {
        return vpa;
    }

    public void setVpa(String vpa) {
        this.vpa = vpa;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUseReward() {
        return useReward;
    }

    public void setUseReward(String useReward) {
        this.useReward = useReward;
    }
}
