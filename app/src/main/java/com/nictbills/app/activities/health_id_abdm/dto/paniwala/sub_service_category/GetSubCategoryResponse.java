package com.nictbills.app.activities.health_id_abdm.dto.paniwala.sub_service_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSubCategoryResponse {

    @SerializedName("RESPONSE")
    @Expose
    private String response;
    @SerializedName("RESULT")
    @Expose
    private List<Result> result = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
