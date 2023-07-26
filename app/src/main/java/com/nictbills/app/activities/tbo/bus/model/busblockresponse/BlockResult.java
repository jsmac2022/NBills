
package com.nictbills.app.activities.tbo.bus.model.busblockresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BlockResult {

    @SerializedName("IsPriceChanged")
    @Expose
    private Boolean isPriceChanged;
    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("ArrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("BusType")
    @Expose
    private String busType;
    @SerializedName("DepartureTime")
    @Expose
    private String departureTime;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("TravelName")
    @Expose
    private String travelName;
    @SerializedName("BoardingPointdetails")
    @Expose
    private BoardingPointdetails boardingPointdetails;
    @SerializedName("CancelPolicy")
    @Expose
    private List<CancelPolicy> cancelPolicy = null;
    @SerializedName("Passenger")
    @Expose
    private List<Passenger> passenger = null;

    public Boolean getIsPriceChanged() {
        return isPriceChanged;
    }

    public void setIsPriceChanged(Boolean isPriceChanged) {
        this.isPriceChanged = isPriceChanged;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public BoardingPointdetails getBoardingPointdetails() {
        return boardingPointdetails;
    }

    public void setBoardingPointdetails(BoardingPointdetails boardingPointdetails) {
        this.boardingPointdetails = boardingPointdetails;
    }

    public List<CancelPolicy> getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(List<CancelPolicy> cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }

}
