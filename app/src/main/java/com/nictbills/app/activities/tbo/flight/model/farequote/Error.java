
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private final static long serialVersionUID = 7728045555579797563L;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Error.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("errorCode");
        sb.append('=');
        sb.append(this.errorCode);
        sb.append(',');
        sb.append("errorMessage");
        sb.append('=');
        sb.append(((this.errorMessage == null)?"<null>":this.errorMessage));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
