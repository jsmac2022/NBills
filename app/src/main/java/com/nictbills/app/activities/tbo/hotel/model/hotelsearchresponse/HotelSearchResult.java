
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelSearchResult {

    @SerializedName("ResponseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("CityId")
    @Expose
    private String cityId;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("CheckInDate")
    @Expose
    private String checkInDate;
    @SerializedName("CheckOutDate")
    @Expose
    private String checkOutDate;
    @SerializedName("PreferredCurrency")
    @Expose
    private String preferredCurrency;
    @SerializedName("NoOfRooms")
    @Expose
    private Integer noOfRooms;
    @SerializedName("RoomGuests")
    @Expose
    private List<RoomGuest> roomGuests;
    @SerializedName("HotelResults")
    @Expose
    private List<HotelResult> hotelResults;

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public List<RoomGuest> getRoomGuests() {
        return roomGuests;
    }

    public void setRoomGuests(List<RoomGuest> roomGuests) {
        this.roomGuests = roomGuests;
    }

    public List<HotelResult> getHotelResults() {
        return hotelResults;
    }

    public void setHotelResults(List<HotelResult> hotelResults) {
        this.hotelResults = hotelResults;
    }

}
