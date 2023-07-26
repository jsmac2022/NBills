package com.nictbills.app.activities.health_id_abdm.dto.dawai4u.access_token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAccessTokenDawai4U {

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
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
