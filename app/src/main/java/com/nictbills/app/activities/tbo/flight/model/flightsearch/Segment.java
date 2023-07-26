
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment implements Serializable
{

    @SerializedName("Baggage")
    @Expose
    private String baggage;
    @SerializedName("CabinBaggage")
    @Expose
    private String cabinBaggage;
    @SerializedName("CabinClass")
    @Expose
    private float cabinClass;
    @SerializedName("TripIndicator")
    @Expose
    private float tripIndicator;
    @SerializedName("SegmentIndicator")
    @Expose
    private float segmentIndicator;
    @SerializedName("Airline")
    @Expose
    private Airline airline;
    @SerializedName("NoOfSeatAvailable")
    @Expose
    private String noOfSeatAvailable;
    @SerializedName("Origin")
    @Expose
    private Origin origin;
    @SerializedName("Destination")
    @Expose
    private Destination destination;
    @SerializedName("Duration")
    @Expose
    private String duration;
    @SerializedName("GroundTime")
    @Expose
    private float groundTime;
    @SerializedName("Mile")
    @Expose
    private float mile;
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
    private Object stopPointArrivalTime;
    @SerializedName("StopPointDepartureTime")
    @Expose
    private Object stopPointDepartureTime;
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
    private final static long serialVersionUID = -8237654095626564098L;

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

    public float getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(float cabinClass) {
        this.cabinClass = cabinClass;
    }

    public float getTripIndicator() {
        return tripIndicator;
    }

    public void setTripIndicator(float tripIndicator) {
        this.tripIndicator = tripIndicator;
    }

    public float getSegmentIndicator() {
        return segmentIndicator;
    }

    public void setSegmentIndicator(float segmentIndicator) {
        this.segmentIndicator = segmentIndicator;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getNoOfSeatAvailable() {
        return noOfSeatAvailable;
    }

    public void setNoOfSeatAvailable(String noOfSeatAvailable) {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(float groundTime) {
        this.groundTime = groundTime;
    }

    public float getMile() {
        return mile;
    }

    public void setMile(float mile) {
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

    public Object getStopPointArrivalTime() {
        return stopPointArrivalTime;
    }

    public void setStopPointArrivalTime(Object stopPointArrivalTime) {
        this.stopPointArrivalTime = stopPointArrivalTime;
    }

    public Object getStopPointDepartureTime() {
        return stopPointDepartureTime;
    }

    public void setStopPointDepartureTime(Object stopPointDepartureTime) {
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

}
