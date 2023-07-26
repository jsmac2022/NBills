
package com.nictbills.app.activities.tbo.bus.model.buscancelticket;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendChangeRequestResult {

    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("BusCRInfo")
    @Expose
    private List<BusCRInfo> busCRInfo;

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public List<BusCRInfo> getBusCRInfo() {
        return busCRInfo;
    }

    public void setBusCRInfo(List<BusCRInfo> busCRInfo) {
        this.busCRInfo = busCRInfo;
    }

}
