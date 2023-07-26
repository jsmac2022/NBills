package com.nictbills.app.activities.fastag.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Authentication {

    @SerializedName("responseCode")
    private String responseCode;

    @SerializedName("responseStatus")
    private String responseStatus;

    @SerializedName("messages")
    private String messages;

    @SerializedName("data")
    private UserDetails data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
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

    public UserDetails getData() {
        return data;
    }

    public void setData(UserDetails data) {
        this.data = data;
    }
}
