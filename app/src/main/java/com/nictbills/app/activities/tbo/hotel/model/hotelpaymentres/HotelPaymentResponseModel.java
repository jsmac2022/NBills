
package com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelPaymentResponseModel {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("ErrorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("status")
    @Expose
    private String status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
