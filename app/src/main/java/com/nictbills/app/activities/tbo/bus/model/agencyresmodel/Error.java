
package com.nictbills.app.activities.tbo.bus.model.agencyresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error implements Serializable
{

    @SerializedName("ErrorCode")
    @Expose
    private int errorCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    private final static long serialVersionUID = 2372394317500951736L;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Error withErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

}
