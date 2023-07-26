
package com.nictbills.app.activities.tbo.bus.model.busbordingresponsemodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusBordingPointResponseModel implements Serializable
{

    @SerializedName("GetBusRouteDetailResult")
    @Expose
    private GetBusRouteDetailResult getBusRouteDetailResult;
    private final static long serialVersionUID = -1425069156060561824L;

    public GetBusRouteDetailResult getGetBusRouteDetailResult() {
        return getBusRouteDetailResult;
    }

    public void setGetBusRouteDetailResult(GetBusRouteDetailResult getBusRouteDetailResult) {
        this.getBusRouteDetailResult = getBusRouteDetailResult;
    }

    public BusBordingPointResponseModel withGetBusRouteDetailResult(GetBusRouteDetailResult getBusRouteDetailResult) {
        this.getBusRouteDetailResult = getBusRouteDetailResult;
        return this;
    }

}
