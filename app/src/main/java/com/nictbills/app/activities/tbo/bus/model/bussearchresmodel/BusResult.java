
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusResult implements Serializable
{

    @SerializedName("ResultIndex")
    @Expose
    private int resultIndex;
    @SerializedName("ArrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("AvailableSeats")
    @Expose
    private int availableSeats;
    @SerializedName("DepartureTime")
    @Expose
    private String departureTime;
    @SerializedName("RouteId")
    @Expose
    private String routeId;
    @SerializedName("BusType")
    @Expose
    private String busType;
    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("TravelName")
    @Expose
    private String travelName;
    @SerializedName("IdProofRequired")
    @Expose
    private boolean idProofRequired;
    @SerializedName("IsDropPointMandatory")
    @Expose
    private boolean isDropPointMandatory;
    @SerializedName("LiveTrackingAvailable")
    @Expose
    private boolean liveTrackingAvailable;
    @SerializedName("MTicketEnabled")
    @Expose
    private boolean mTicketEnabled;
    @SerializedName("MaxSeatsPerTicket")
    @Expose
    private int maxSeatsPerTicket;
    @SerializedName("OperatorId")
    @Expose
    private int operatorId;
    @SerializedName("PartialCancellationAllowed")
    @Expose
    private boolean partialCancellationAllowed;
    @SerializedName("BoardingPointsDetails")
    @Expose
    private List<BoardingPointsDetail> boardingPointsDetails = null;
    @SerializedName("DroppingPointsDetails")
    @Expose
    private List<DroppingPointsDetail> droppingPointsDetails = null;
    @SerializedName("BusPrice")
    @Expose
    private BusPrice busPrice;
    @SerializedName("CancellationPolicies")
    @Expose
    private List<CancellationPolicy> cancellationPolicies = null;
    private final static long serialVersionUID = -4165193159296369702L;

    public int getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(int resultIndex) {
        this.resultIndex = resultIndex;
    }

    public BusResult withResultIndex(int resultIndex) {
        this.resultIndex = resultIndex;
        return this;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BusResult withArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public BusResult withAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
        return this;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public BusResult withDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public BusResult withRouteId(String routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public BusResult withBusType(String busType) {
        this.busType = busType;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BusResult withServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public BusResult withTravelName(String travelName) {
        this.travelName = travelName;
        return this;
    }

    public boolean isIdProofRequired() {
        return idProofRequired;
    }

    public void setIdProofRequired(boolean idProofRequired) {
        this.idProofRequired = idProofRequired;
    }

    public BusResult withIdProofRequired(boolean idProofRequired) {
        this.idProofRequired = idProofRequired;
        return this;
    }

    public boolean isIsDropPointMandatory() {
        return isDropPointMandatory;
    }

    public void setIsDropPointMandatory(boolean isDropPointMandatory) {
        this.isDropPointMandatory = isDropPointMandatory;
    }

    public BusResult withIsDropPointMandatory(boolean isDropPointMandatory) {
        this.isDropPointMandatory = isDropPointMandatory;
        return this;
    }

    public boolean isLiveTrackingAvailable() {
        return liveTrackingAvailable;
    }

    public void setLiveTrackingAvailable(boolean liveTrackingAvailable) {
        this.liveTrackingAvailable = liveTrackingAvailable;
    }

    public BusResult withLiveTrackingAvailable(boolean liveTrackingAvailable) {
        this.liveTrackingAvailable = liveTrackingAvailable;
        return this;
    }

    public boolean isMTicketEnabled() {
        return mTicketEnabled;
    }

    public void setMTicketEnabled(boolean mTicketEnabled) {
        this.mTicketEnabled = mTicketEnabled;
    }

    public BusResult withMTicketEnabled(boolean mTicketEnabled) {
        this.mTicketEnabled = mTicketEnabled;
        return this;
    }

    public int getMaxSeatsPerTicket() {
        return maxSeatsPerTicket;
    }

    public void setMaxSeatsPerTicket(int maxSeatsPerTicket) {
        this.maxSeatsPerTicket = maxSeatsPerTicket;
    }

    public BusResult withMaxSeatsPerTicket(int maxSeatsPerTicket) {
        this.maxSeatsPerTicket = maxSeatsPerTicket;
        return this;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public BusResult withOperatorId(int operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public boolean isPartialCancellationAllowed() {
        return partialCancellationAllowed;
    }

    public void setPartialCancellationAllowed(boolean partialCancellationAllowed) {
        this.partialCancellationAllowed = partialCancellationAllowed;
    }

    public BusResult withPartialCancellationAllowed(boolean partialCancellationAllowed) {
        this.partialCancellationAllowed = partialCancellationAllowed;
        return this;
    }

    public List<BoardingPointsDetail> getBoardingPointsDetails() {
        return boardingPointsDetails;
    }

    public void setBoardingPointsDetails(List<BoardingPointsDetail> boardingPointsDetails) {
        this.boardingPointsDetails = boardingPointsDetails;
    }

    public BusResult withBoardingPointsDetails(List<BoardingPointsDetail> boardingPointsDetails) {
        this.boardingPointsDetails = boardingPointsDetails;
        return this;
    }

    public List<DroppingPointsDetail> getDroppingPointsDetails() {
        return droppingPointsDetails;
    }

    public void setDroppingPointsDetails(List<DroppingPointsDetail> droppingPointsDetails) {
        this.droppingPointsDetails = droppingPointsDetails;
    }

    public BusResult withDroppingPointsDetails(List<DroppingPointsDetail> droppingPointsDetails) {
        this.droppingPointsDetails = droppingPointsDetails;
        return this;
    }

    public BusPrice getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(BusPrice busPrice) {
        this.busPrice = busPrice;
    }

    public BusResult withBusPrice(BusPrice busPrice) {
        this.busPrice = busPrice;
        return this;
    }

    public List<CancellationPolicy> getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(List<CancellationPolicy> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public BusResult withCancellationPolicies(List<CancellationPolicy> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
        return this;
    }

}
