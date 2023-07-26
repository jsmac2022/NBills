package com.nictbills.app.activities.tbo.flight.model.auth_token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nictbills.app.activities.tbo.hotel.model.authresponse.HotelAouthModelResponse;

import java.io.Serializable;

public class Error implements Serializable {

    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;

    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelAouthModelResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ErrorCode");
        sb.append('=');
        sb.append(this.errorCode);
        sb.append(',');

        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
