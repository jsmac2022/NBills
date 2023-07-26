
package com.nictbills.app.activities.tbo.bus.model.busbordingresponsemodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class GetBusRouteDetailResult implements Serializable
{

    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("BoardingPointsDetails")
    @Expose
    private List<BoardingPointsDetail> boardingPointsDetails = null;
    @SerializedName("DroppingPointsDetails")
    @Expose
    private List<DroppingPointsDetail> droppingPointsDetails = null;
    private final static long serialVersionUID = -3315473939654867473L;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public GetBusRouteDetailResult withResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public GetBusRouteDetailResult withError(java.lang.Error error) {
        this.error = error;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public GetBusRouteDetailResult withTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public List<BoardingPointsDetail> getBoardingPointsDetails() {
        return boardingPointsDetails;
    }

    public void setBoardingPointsDetails(List<BoardingPointsDetail> boardingPointsDetails) {
        this.boardingPointsDetails = boardingPointsDetails;
    }

    public GetBusRouteDetailResult withBoardingPointsDetails(List<BoardingPointsDetail> boardingPointsDetails) {
        this.boardingPointsDetails = boardingPointsDetails;
        return this;
    }

    public List<DroppingPointsDetail> getDroppingPointsDetails() {
        return droppingPointsDetails;
    }

    public void setDroppingPointsDetails(List<DroppingPointsDetail> droppingPointsDetails) {
        this.droppingPointsDetails = droppingPointsDetails;
    }

    public GetBusRouteDetailResult withDroppingPointsDetails(List<DroppingPointsDetail> droppingPointsDetails) {
        this.droppingPointsDetails = droppingPointsDetails;
        return this;
    }

}
