
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelGeRoomResponse implements Serializable
{

    @SerializedName("GetHotelRoomResult")
    @Expose
    private GetHotelRoomResult getHotelRoomResult;
    private final static long serialVersionUID = 5760804116714298789L;

    public GetHotelRoomResult getGetHotelRoomResult() {
        return getHotelRoomResult;
    }

    public void setGetHotelRoomResult(GetHotelRoomResult getHotelRoomResult) {
        this.getHotelRoomResult = getHotelRoomResult;
    }

    public HotelGeRoomResponse withGetHotelRoomResult(GetHotelRoomResult getHotelRoomResult) {
        this.getHotelRoomResult = getHotelRoomResult;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelGeRoomResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("getHotelRoomResult");
        sb.append('=');
        sb.append(((this.getHotelRoomResult == null)?"<null>":this.getHotelRoomResult));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
