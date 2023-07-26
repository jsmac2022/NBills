
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightSearchModel implements Serializable
{

    @SerializedName("Response")
    @Expose
    private Response response;
    @SerializedName("RESULT")
    @Expose
    private List<Result> result = null;
    private final static long serialVersionUID = 3415526586437704499L;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
