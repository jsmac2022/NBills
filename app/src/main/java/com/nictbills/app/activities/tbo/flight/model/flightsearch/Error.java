
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error implements Serializable
{

    @SerializedName("ErrorCode")
    @Expose
    private float errorCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    private final static long serialVersionUID = 2047597672591377971L;

    public float getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(float errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
