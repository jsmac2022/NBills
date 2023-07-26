
package com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRoomsDetail implements Serializable
{

    @SerializedName("RoomIndex")
    @Expose
    private String roomIndex;
    @SerializedName("RoomTypeCode")
    @Expose
    private String roomTypeCode;
    @SerializedName("RoomTypeName")
    @Expose
    private String roomTypeName;
    @SerializedName("RatePlanCode")
    @Expose
    private String ratePlanCode;
    @SerializedName("BedTypeCode")
    @Expose
    private Object bedTypeCode;
    @SerializedName("SmokingPreference")
    @Expose
    private int smokingPreference;
    @SerializedName("Supplements")
    @Expose
    private Object supplements;
    @SerializedName("Price")
    @Expose
    private Price price;
    private final static long serialVersionUID = 3425386208488889818L;

    public String getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(String roomIndex) {
        this.roomIndex = roomIndex;
    }

    public HotelRoomsDetail withRoomIndex(String roomIndex) {
        this.roomIndex = roomIndex;
        return this;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public HotelRoomsDetail withRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
        return this;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public HotelRoomsDetail withRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
        return this;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public HotelRoomsDetail withRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
        return this;
    }

    public Object getBedTypeCode() {
        return bedTypeCode;
    }

    public void setBedTypeCode(Object bedTypeCode) {
        this.bedTypeCode = bedTypeCode;
    }

    public HotelRoomsDetail withBedTypeCode(Object bedTypeCode) {
        this.bedTypeCode = bedTypeCode;
        return this;
    }

    public int getSmokingPreference() {
        return smokingPreference;
    }

    public void setSmokingPreference(int smokingPreference) {
        this.smokingPreference = smokingPreference;
    }

    public HotelRoomsDetail withSmokingPreference(int smokingPreference) {
        this.smokingPreference = smokingPreference;
        return this;
    }

    public Object getSupplements() {
        return supplements;
    }

    public void setSupplements(Object supplements) {
        this.supplements = supplements;
    }

    public HotelRoomsDetail withSupplements(Object supplements) {
        this.supplements = supplements;
        return this;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public HotelRoomsDetail withPrice(Price price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelRoomsDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("roomIndex");
        sb.append('=');
        sb.append(((this.roomIndex == null)?"<null>":this.roomIndex));
        sb.append(',');
        sb.append("roomTypeCode");
        sb.append('=');
        sb.append(((this.roomTypeCode == null)?"<null>":this.roomTypeCode));
        sb.append(',');
        sb.append("roomTypeName");
        sb.append('=');
        sb.append(((this.roomTypeName == null)?"<null>":this.roomTypeName));
        sb.append(',');
        sb.append("ratePlanCode");
        sb.append('=');
        sb.append(((this.ratePlanCode == null)?"<null>":this.ratePlanCode));
        sb.append(',');
        sb.append("bedTypeCode");
        sb.append('=');
        sb.append(((this.bedTypeCode == null)?"<null>":this.bedTypeCode));
        sb.append(',');
        sb.append("smokingPreference");
        sb.append('=');
        sb.append(this.smokingPreference);
        sb.append(',');
        sb.append("supplements");
        sb.append('=');
        sb.append(((this.supplements == null)?"<null>":this.supplements));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
