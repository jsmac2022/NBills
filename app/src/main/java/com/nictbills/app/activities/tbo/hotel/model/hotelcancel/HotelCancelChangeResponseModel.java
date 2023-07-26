
package com.nictbills.app.activities.tbo.hotel.model.hotelcancel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelCancelChangeResponseModel {

    @SerializedName("HotelChangeRequestResult")
    @Expose
    private HotelChangeRequestResult hotelChangeRequestResult;

    public HotelChangeRequestResult getHotelChangeRequestResult() {
        return hotelChangeRequestResult;
    }

    public void setHotelChangeRequestResult(HotelChangeRequestResult hotelChangeRequestResult) {
        this.hotelChangeRequestResult = hotelChangeRequestResult;
    }

}
