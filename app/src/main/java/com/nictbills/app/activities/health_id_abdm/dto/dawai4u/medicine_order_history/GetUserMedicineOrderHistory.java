package com.nictbills.app.activities.health_id_abdm.dto.dawai4u.medicine_order_history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserMedicineOrderHistory {

    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


}
