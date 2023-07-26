
package com.nictbills.app.activities.tbo.hotel.model.hotelinfomodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelGetInfoResponse implements Serializable
{

    @SerializedName("HotelInfoResult")
    @Expose
    private HotelInfoResult hotelInfoResult;
    private final static long serialVersionUID = 7335886808836380651L;

    public HotelInfoResult getHotelInfoResult() {
        return hotelInfoResult;
    }

    public void setHotelInfoResult(HotelInfoResult hotelInfoResult) {
        this.hotelInfoResult = hotelInfoResult;
    }

    public HotelGetInfoResponse withHotelInfoResult(HotelInfoResult hotelInfoResult) {
        this.hotelInfoResult = hotelInfoResult;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelGetInfoResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("hotelInfoResult");
        sb.append('=');
        sb.append(((this.hotelInfoResult == null)?"<null>":this.hotelInfoResult));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
