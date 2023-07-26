
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelSearchResponseModel {

    @SerializedName("HotelSearchResult")
    @Expose
    private HotelSearchResult hotelSearchResult;

    public HotelSearchResult getHotelSearchResult() {
        return hotelSearchResult;
    }

    public void setHotelSearchResult(HotelSearchResult hotelSearchResult) {
        this.hotelSearchResult = hotelSearchResult;
    }

}
