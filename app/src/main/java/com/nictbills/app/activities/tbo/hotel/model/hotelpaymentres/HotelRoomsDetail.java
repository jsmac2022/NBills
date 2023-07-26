
package com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRoomsDetail {

    @SerializedName("HotelPassenger")
    @Expose
    private List<HotelPassenger> hotelPassenger = null;
    @SerializedName("RoomTypeName")
    @Expose
    private String roomTypeName;
    @SerializedName("Price")
    @Expose
    private Price price;
    @SerializedName("RoomTypeCode")
    @Expose
    private String roomTypeCode;
    @SerializedName("RoomIndex")
    @Expose
    private Integer roomIndex;
    @SerializedName("SmokingPreference")
    @Expose
    private Integer smokingPreference;
    @SerializedName("RatePlanCode")
    @Expose
    private String ratePlanCode;

    public List<HotelPassenger> getHotelPassenger() {
        return hotelPassenger;
    }

    public void setHotelPassenger(List<HotelPassenger> hotelPassenger) {
        this.hotelPassenger = hotelPassenger;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public Integer getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(Integer roomIndex) {
        this.roomIndex = roomIndex;
    }

    public Integer getSmokingPreference() {
        return smokingPreference;
    }

    public void setSmokingPreference(Integer smokingPreference) {
        this.smokingPreference = smokingPreference;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

}
