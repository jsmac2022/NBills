
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelRoomResult implements Serializable
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
    @SerializedName("IsUnderCancellationAllowed")
    @Expose
    private boolean isUnderCancellationAllowed;
    @SerializedName("IsPolicyPerStay")
    @Expose
    private boolean isPolicyPerStay;
    @SerializedName("HotelRoomsDetails")
    @Expose
    private List<HotelRoomsDetail> hotelRoomsDetails = null;
    @SerializedName("RoomCombinations")
    @Expose
    private RoomCombinations roomCombinations;
    private final static long serialVersionUID = 8275421726939460365L;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public GetHotelRoomResult withResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public GetHotelRoomResult withError(java.lang.Error error) {
        this.error = error;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public GetHotelRoomResult withTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public boolean isIsUnderCancellationAllowed() {
        return isUnderCancellationAllowed;
    }

    public void setIsUnderCancellationAllowed(boolean isUnderCancellationAllowed) {
        this.isUnderCancellationAllowed = isUnderCancellationAllowed;
    }

    public GetHotelRoomResult withIsUnderCancellationAllowed(boolean isUnderCancellationAllowed) {
        this.isUnderCancellationAllowed = isUnderCancellationAllowed;
        return this;
    }

    public boolean isIsPolicyPerStay() {
        return isPolicyPerStay;
    }

    public void setIsPolicyPerStay(boolean isPolicyPerStay) {
        this.isPolicyPerStay = isPolicyPerStay;
    }

    public GetHotelRoomResult withIsPolicyPerStay(boolean isPolicyPerStay) {
        this.isPolicyPerStay = isPolicyPerStay;
        return this;
    }

    public List<HotelRoomsDetail> getHotelRoomsDetails() {
        return hotelRoomsDetails;
    }

    public void setHotelRoomsDetails(List<HotelRoomsDetail> hotelRoomsDetails) {
        this.hotelRoomsDetails = hotelRoomsDetails;
    }

    public GetHotelRoomResult withHotelRoomsDetails(List<HotelRoomsDetail> hotelRoomsDetails) {
        this.hotelRoomsDetails = hotelRoomsDetails;
        return this;
    }

    public RoomCombinations getRoomCombinations() {
        return roomCombinations;
    }

    public void setRoomCombinations(RoomCombinations roomCombinations) {
        this.roomCombinations = roomCombinations;
    }

    public GetHotelRoomResult withRoomCombinations(RoomCombinations roomCombinations) {
        this.roomCombinations = roomCombinations;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GetHotelRoomResult.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("responseStatus");
        sb.append('=');
        sb.append(this.responseStatus);
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null)?"<null>":this.traceId));
        sb.append(',');
        sb.append("isUnderCancellationAllowed");
        sb.append('=');
        sb.append(this.isUnderCancellationAllowed);
        sb.append(',');
        sb.append("isPolicyPerStay");
        sb.append('=');
        sb.append(this.isPolicyPerStay);
        sb.append(',');
        sb.append("hotelRoomsDetails");
        sb.append('=');
        sb.append(((this.hotelRoomsDetails == null)?"<null>":this.hotelRoomsDetails));
        sb.append(',');
        sb.append("roomCombinations");
        sb.append('=');
        sb.append(((this.roomCombinations == null)?"<null>":this.roomCombinations));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
