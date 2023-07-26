
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private int cabinClass;
    @SerializedName("TripIndicator")
    @Expose
    private int tripIndicator;
    @SerializedName("SegmentIndicator")
    @Expose
    private int segmentIndicator;
    @SerializedName("Airline")
    @Expose
    private Airline airline;
    @SerializedName("Origin")
    @Expose
    private Origin origin;
    @SerializedName("Destination")
    @Expose
    private Destination destination;
    @SerializedName("Duration")
    @Expose
    private int duration;
    @SerializedName("GroundTime")
    @Expose
    private int groundTime;
    @SerializedName("Mile")
    @Expose
    private int mile;
    @SerializedName("StopOver")
    @Expose
    private boolean stopOver;
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
    private boolean isETicketEligible;
    @SerializedName("FlightStatus")
    @Expose
    private String flightStatus;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("FareClassification")
    @Expose
    private FareClassification fareClassification;
    private final static long serialVersionUID = 3264053830648668116L;

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
    }

    public Segment withBaggage(String baggage) {
        this.baggage = baggage;
        return this;
    }

    public String getCabinBaggage() {
        return cabinBaggage;
    }

    public void setCabinBaggage(String cabinBaggage) {
        this.cabinBaggage = cabinBaggage;
    }

    public Segment withCabinBaggage(String cabinBaggage) {
        this.cabinBaggage = cabinBaggage;
        return this;
    }

    public int getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(int cabinClass) {
        this.cabinClass = cabinClass;
    }

    public Segment withCabinClass(int cabinClass) {
        this.cabinClass = cabinClass;
        return this;
    }

    public int getTripIndicator() {
        return tripIndicator;
    }

    public void setTripIndicator(int tripIndicator) {
        this.tripIndicator = tripIndicator;
    }

    public Segment withTripIndicator(int tripIndicator) {
        this.tripIndicator = tripIndicator;
        return this;
    }

    public int getSegmentIndicator() {
        return segmentIndicator;
    }

    public void setSegmentIndicator(int segmentIndicator) {
        this.segmentIndicator = segmentIndicator;
    }

    public Segment withSegmentIndicator(int segmentIndicator) {
        this.segmentIndicator = segmentIndicator;
        return this;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Segment withAirline(Airline airline) {
        this.airline = airline;
        return this;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Segment withOrigin(Origin origin) {
        this.origin = origin;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Segment withDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Segment withDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public int getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(int groundTime) {
        this.groundTime = groundTime;
    }

    public Segment withGroundTime(int groundTime) {
        this.groundTime = groundTime;
        return this;
    }

    public int getMile() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile = mile;
    }

    public Segment withMile(int mile) {
        this.mile = mile;
        return this;
    }

    public boolean isStopOver() {
        return stopOver;
    }

    public void setStopOver(boolean stopOver) {
        this.stopOver = stopOver;
    }

    public Segment withStopOver(boolean stopOver) {
        this.stopOver = stopOver;
        return this;
    }

    public String getFlightInfoIndex() {
        return flightInfoIndex;
    }

    public void setFlightInfoIndex(String flightInfoIndex) {
        this.flightInfoIndex = flightInfoIndex;
    }

    public Segment withFlightInfoIndex(String flightInfoIndex) {
        this.flightInfoIndex = flightInfoIndex;
        return this;
    }

    public String getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(String stopPoint) {
        this.stopPoint = stopPoint;
    }

    public Segment withStopPoint(String stopPoint) {
        this.stopPoint = stopPoint;
        return this;
    }

    public String getStopPointArrivalTime() {
        return stopPointArrivalTime;
    }

    public void setStopPointArrivalTime(String stopPointArrivalTime) {
        this.stopPointArrivalTime = stopPointArrivalTime;
    }

    public Segment withStopPointArrivalTime(String stopPointArrivalTime) {
        this.stopPointArrivalTime = stopPointArrivalTime;
        return this;
    }

    public String getStopPointDepartureTime() {
        return stopPointDepartureTime;
    }

    public void setStopPointDepartureTime(String stopPointDepartureTime) {
        this.stopPointDepartureTime = stopPointDepartureTime;
    }

    public Segment withStopPointDepartureTime(String stopPointDepartureTime) {
        this.stopPointDepartureTime = stopPointDepartureTime;
        return this;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public Segment withCraft(String craft) {
        this.craft = craft;
        return this;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Segment withRemark(Object remark) {
        this.remark = remark;
        return this;
    }

    public boolean isIsETicketEligible() {
        return isETicketEligible;
    }

    public void setIsETicketEligible(boolean isETicketEligible) {
        this.isETicketEligible = isETicketEligible;
    }

    public Segment withIsETicketEligible(boolean isETicketEligible) {
        this.isETicketEligible = isETicketEligible;
        return this;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Segment withFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Segment withStatus(String status) {
        this.status = status;
        return this;
    }

    public FareClassification getFareClassification() {
        return fareClassification;
    }

    public void setFareClassification(FareClassification fareClassification) {
        this.fareClassification = fareClassification;
    }

    public Segment withFareClassification(FareClassification fareClassification) {
        this.fareClassification = fareClassification;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Segment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("baggage");
        sb.append('=');
        sb.append(((this.baggage == null)?"<null>":this.baggage));
        sb.append(',');
        sb.append("cabinBaggage");
        sb.append('=');
        sb.append(((this.cabinBaggage == null)?"<null>":this.cabinBaggage));
        sb.append(',');
        sb.append("cabinClass");
        sb.append('=');
        sb.append(this.cabinClass);
        sb.append(',');
        sb.append("tripIndicator");
        sb.append('=');
        sb.append(this.tripIndicator);
        sb.append(',');
        sb.append("segmentIndicator");
        sb.append('=');
        sb.append(this.segmentIndicator);
        sb.append(',');
        sb.append("airline");
        sb.append('=');
        sb.append(((this.airline == null)?"<null>":this.airline));
        sb.append(',');
        sb.append("origin");
        sb.append('=');
        sb.append(((this.origin == null)?"<null>":this.origin));
        sb.append(',');
        sb.append("destination");
        sb.append('=');
        sb.append(((this.destination == null)?"<null>":this.destination));
        sb.append(',');
        sb.append("duration");
        sb.append('=');
        sb.append(this.duration);
        sb.append(',');
        sb.append("groundTime");
        sb.append('=');
        sb.append(this.groundTime);
        sb.append(',');
        sb.append("mile");
        sb.append('=');
        sb.append(this.mile);
        sb.append(',');
        sb.append("stopOver");
        sb.append('=');
        sb.append(this.stopOver);
        sb.append(',');
        sb.append("flightInfoIndex");
        sb.append('=');
        sb.append(((this.flightInfoIndex == null)?"<null>":this.flightInfoIndex));
        sb.append(',');
        sb.append("stopPoint");
        sb.append('=');
        sb.append(((this.stopPoint == null)?"<null>":this.stopPoint));
        sb.append(',');
        sb.append("stopPointArrivalTime");
        sb.append('=');
        sb.append(((this.stopPointArrivalTime == null)?"<null>":this.stopPointArrivalTime));
        sb.append(',');
        sb.append("stopPointDepartureTime");
        sb.append('=');
        sb.append(((this.stopPointDepartureTime == null)?"<null>":this.stopPointDepartureTime));
        sb.append(',');
        sb.append("craft");
        sb.append('=');
        sb.append(((this.craft == null)?"<null>":this.craft));
        sb.append(',');
        sb.append("remark");
        sb.append('=');
        sb.append(((this.remark == null)?"<null>":this.remark));
        sb.append(',');
        sb.append("isETicketEligible");
        sb.append('=');
        sb.append(this.isETicketEligible);
        sb.append(',');
        sb.append("flightStatus");
        sb.append('=');
        sb.append(((this.flightStatus == null)?"<null>":this.flightStatus));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("fareClassification");
        sb.append('=');
        sb.append(((this.fareClassification == null)?"<null>":this.fareClassification));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
