package com.nictbills.app.activities.household.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TypeSubCat {

    @SerializedName("RESPONSE")
    private String response;

    @SerializedName("RESULT")
    private List<SubcatDatum> result ;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<SubcatDatum> getResult() {
        return result;
    }

    public void setResult(List<SubcatDatum> result) {
        this.result = result;
    }
}
