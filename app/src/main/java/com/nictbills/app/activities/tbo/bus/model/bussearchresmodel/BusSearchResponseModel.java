
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusSearchResponseModel implements Serializable
{

    @SerializedName("BusSearchResult")
    @Expose
    private BusSearchResult busSearchResult;
    private final static long serialVersionUID = 574829764340322915L;

    public BusSearchResult getBusSearchResult() {
        return busSearchResult;
    }

    public void setBusSearchResult(BusSearchResult busSearchResult) {
        this.busSearchResult = busSearchResult;
    }

    public BusSearchResponseModel withBusSearchResult(BusSearchResult busSearchResult) {
        this.busSearchResult = busSearchResult;
        return this;
    }

}
