
package com.nictbills.app.activities.tbo.bus.model.busbordingresponsemodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoardingPointsDetail implements Serializable
{

    @SerializedName("CityPointAddress")
    @Expose
    private String cityPointAddress;
    @SerializedName("CityPointContactNumber")
    @Expose
    private String cityPointContactNumber;
    @SerializedName("CityPointIndex")
    @Expose
    private int cityPointIndex;
    @SerializedName("CityPointLandmark")
    @Expose
    private String cityPointLandmark;
    @SerializedName("CityPointLocation")
    @Expose
    private String cityPointLocation;
    @SerializedName("CityPointName")
    @Expose
    private String cityPointName;
    @SerializedName("CityPointTime")
    @Expose
    private String cityPointTime;
    private final static long serialVersionUID = 7825628695978542846L;

    public String getCityPointAddress() {
        return cityPointAddress;
    }

    public void setCityPointAddress(String cityPointAddress) {
        this.cityPointAddress = cityPointAddress;
    }

    public BoardingPointsDetail withCityPointAddress(String cityPointAddress) {
        this.cityPointAddress = cityPointAddress;
        return this;
    }

    public String getCityPointContactNumber() {
        return cityPointContactNumber;
    }

    public void setCityPointContactNumber(String cityPointContactNumber) {
        this.cityPointContactNumber = cityPointContactNumber;
    }

    public BoardingPointsDetail withCityPointContactNumber(String cityPointContactNumber) {
        this.cityPointContactNumber = cityPointContactNumber;
        return this;
    }

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

    public String getCityPointLandmark() {
        return cityPointLandmark;
    }

    public void setCityPointLandmark(String cityPointLandmark) {
        this.cityPointLandmark = cityPointLandmark;
    }

    public BoardingPointsDetail withCityPointLandmark(String cityPointLandmark) {
        this.cityPointLandmark = cityPointLandmark;
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
