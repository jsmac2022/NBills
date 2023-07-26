
package com.nictbills.app.activities.farmequipment.model.AddUserInfoResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddUserInfoResponsemodel {
    @SerializedName("Message")
    @Expose
    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }






}
