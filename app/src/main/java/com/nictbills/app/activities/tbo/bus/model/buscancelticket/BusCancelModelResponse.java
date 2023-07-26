
package com.nictbills.app.activities.tbo.bus.model.buscancelticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusCancelModelResponse {

    @SerializedName("SendChangeRequestResult")
    @Expose
    private SendChangeRequestResult sendChangeRequestResult;

    public SendChangeRequestResult getSendChangeRequestResult() {
        return sendChangeRequestResult;
    }

    public void setSendChangeRequestResult(SendChangeRequestResult sendChangeRequestResult) {
        this.sendChangeRequestResult = sendChangeRequestResult;
    }

}
