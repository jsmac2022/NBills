package com.nictbills.app.activities.health_id_abdm.dto;

public class CheckDuplicateBillDto {

    private String service;
    private String service_id;
    private String code;
    private String message;

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
}
