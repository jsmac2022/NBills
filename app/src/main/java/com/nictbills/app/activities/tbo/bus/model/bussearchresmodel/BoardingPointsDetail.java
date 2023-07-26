
package com.nictbills.app.activities.tbo.bus.model.bussearchresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoardingPointsDetail implements Serializable
{

    @SerializedName("CityPointIndex")
    @Expose
    private int cityPointIndex;
    @SerializedName("CityPointLocation")
    @Expose
    private String cityPointLocation;
    @SerializedName("CityPointName")
    @Expose
    private String cityPointName;
    @SerializedName("CityPointTime")
    @Expose
    private String cityPointTime;
    private final static long serialVersionUID = 2065829422282970461L;

    public int getCityPointIndex() {
        return cityPointIndex;
    }

    public void setCityPointIndex(int cityPointIndex) {
        this.cityPointIndex = cityPointIndex;
    }

    public BoardingPointsDetail withCityPointIndex(int cityPointIndex) {
        this.cityPointIndex = cityPointIndex;
        return this;
    }

    public String getCityPointLocation() {
        return cityPointLocation;
    }

    public void setCityPointLocation(String cityPointLocation) {
        this.cityPointLocation = cityPointLocation;
    }

    public BoardingPointsDetail withCityPointLocation(String cityPointLocation) {
        this.cityPointLocation = cityPointLocation;
        return this;
    }

    public String getCityPointName() {
        return cityPointName;
    }

    public void setCityPointName(String cityPointName) {
        this.cityPointName = cityPointName;
    }

    public BoardingPointsDetail withCityPointName(String cityPointName) {
        this.cityPointName = cityPointName;
        return this;
    }

    public String getCityPointTime() {
        return cityPointTime;
    }

    public void setCityPointTime(String cityPointTime) {
        this.cityPointTime = cityPointTime;
    }

    public BoardingPointsDetail withCityPointTime(String cityPointTime) {
        this.cityPointTime = cityPointTime;
        return this;
    }

}
