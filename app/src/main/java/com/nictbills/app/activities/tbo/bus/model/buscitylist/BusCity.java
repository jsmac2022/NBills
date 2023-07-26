
package com.nictbills.app.activities.tbo.bus.model.buscitylist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusCity {

    @SerializedName("CityId")
    @Expose
    private Integer cityId;
    @SerializedName("CityName")
    @Expose
    private String cityName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
