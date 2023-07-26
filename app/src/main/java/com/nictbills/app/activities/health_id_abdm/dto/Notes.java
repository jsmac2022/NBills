package com.nictbills.app.activities.health_id_abdm.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notes {

    @SerializedName("service_loc")
    @Expose
    private String serviceLoc;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("service_id")
    @Expose
    private String serviceId;

    public String getServiceLoc() {
        return serviceLoc;
    }

    public void setServiceLoc(String serviceLoc) {
        this.serviceLoc = serviceLoc;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

}
