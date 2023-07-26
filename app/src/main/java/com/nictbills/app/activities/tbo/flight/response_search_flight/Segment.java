package com.nictbills.app.activities.tbo.flight.response_search_flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {

    @SerializedName("Baggage")
    @Expose
    private String baggage;
    @SerializedName("CabinBaggage")
    @Expose
    private String cabinBaggage;
    @SerializedName("CabinClass")
    @Expose
    private Integer cabinClass;
    @SerializedName("TripIndicator")
    @Expose
    private Integer tripIndicator;
    @SerializedName("SegmentIndicator")
    @Expose
    private Integer segmentIndicator;
    @SerializedName("Airline")
    @Expose
    private Airline airline;
    @SerializedName("NoOfSeatAvailable")
    @Expose
    private Integer noOfSeatAvailable;
    @SerializedName("Origin")
    @Expose
    private Origin origin;
    @SerializedName("Destination")
    @Expose
    private Destination destination;
    @SerializedName("Duration")
    @Expose
    private Integer duration;
    @SerializedName("GroundTime")
    @Expose
    private Integer groundTime;
    @SerializedName("Mile")
    @Expose
    private Integer mile;
    @SerializedName("StopOver")
    @Expose
    private Boolean stopOver;
    @SerializedName("FlightInfoIndex")
    @Expose
    private String flightInfoIndex;
    @SerializedName("StopPoint")
    @Expose
    private String stopPoint;
    @SerializedName("StopPointArrivalTime")
    @Expose
    private String stopPointArrivalTime;
    @SerializedName("StopPointDepartureTime")
    @Expose
    private String stopPointDepartureTime;
    @SerializedName("Craft")
    @Expose
    private String craft;
    @SerializedName("Remark")
    @Expose
    private Object remark;
    @SerializedName("IsETicketEligible")
    @Expose
    private Boolean isETicketEligible;
    @SerializedName("FlightStatus")
    @Expose
    private String flightStatus;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("FareClassification")
    @Expose
    private FareClassification fareClassification;
    @SerializedName("AccumulatedDuration")
    @Expose
    private Integer accumulatedDuration;

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public String getCabinBaggage() {
        return cabinBaggage;
    }

    public void setCabinBaggage(String cabinBaggage) {
        this.cabinBaggage = cabinBaggage;
    }

    public Integer getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(Integer cabinClass) {
        this.cabinClass = cabinClass;
    }

    public Integer getTripIndicator() {
        return tripIndicator;
    }

    public void setTripIndicator(Integer tripIndicator) {
        this.tripIndicator = tripIndicator;
    }

    public Integer getSegmentIndicator() {
        return segmentIndicator;
    }

    public void setSegmentIndicator(Integer segmentIndicator) {
        this.segmentIndicator = segmentIndicator;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Integer getNoOfSeatAvailable() {
        return noOfSeatAvailable;
    }

    public void setNoOfSeatAvailable(Integer noOfSeatAvailable) {
        this.noOfSeatAvailable = noOfSeatAvailable;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(Integer groundTime) {
        this.groundTime = groundTime;
    }

    public Integer getMile() {
        return mile;
    }

    public void setMile(Integer mile) {
        this.mile = mile;
    }

    public Boolean getStopOver() {
        return stopOver;
    }

    public void setStopOver(Boolean stopOver) {
        this.stopOver = stopOver;
    }

    public String getFlightInfoIndex() {
        return flightInfoIndex;
    }

    public void setFlightInfoIndex(String flightInfoIndex) {
        this.flightInfoIndex = flightInfoIndex;
    }

    public String getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(String stopPoint) {
        this.stopPoint = stopPoint;
    }

    public String getStopPointArrivalTime() {
        return stopPointArrivalTime;
    }

    public void setStopPointArrivalTime(String stopPointArrivalTime) {
        this.stopPointArrivalTime = stopPointArrivalTime;
    }

    public String getStopPointDepartureTime() {
        return stopPointDepartureTime;
    }

    public void setStopPointDepartureTime(String stopPointDepartureTime) {
        this.stopPointDepartureTime = stopPointDepartureTime;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Boolean getIsETicketEligible() {
        return isETicketEligible;
    }

    public void setIsETicketEligible(Boolean isETicketEligible) {
        this.isETicketEligible = isETicketEligible;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FareClassification getFareClassification() {
        return fareClassification;
    }

    public void setFareClassification(FareClassification fareClassification) {
        this.fareClassification = fareClassification;
    }

    public Integer getAccumulatedDuration() {
        return accumulatedDuration;
    }

    public void setAccumulatedDuration(Integer accumulatedDuration) {
        this.accumulatedDuration = accumulatedDuration;
    }

}
