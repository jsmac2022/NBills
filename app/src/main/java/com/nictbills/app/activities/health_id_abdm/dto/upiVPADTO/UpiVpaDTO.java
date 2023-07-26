package com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO;

public class UpiVpaDTO {

    private String merchantTranId;
    private String billSaveStatus;
    private String txnStatus;
    private String amount;
    private String service;
    private String service_id;
    private String service_loc;
    private String txnDate;

    public String getMerchantTranId() {
        return merchantTranId;
    }

    public void setMerchantTranId(String merchantTranId) {
        this.merchantTranId = merchantTranId;
    }

    public String getBillSaveStatus() {
        return billSaveStatus;
    }

    public void setBillSaveStatus(String billSaveStatus) {
        this.billSaveStatus = billSaveStatus;
    }


    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_loc() {
        return service_loc;
    }

    public void setService_loc(String service_loc) {
        this.service_loc = service_loc;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }
}
