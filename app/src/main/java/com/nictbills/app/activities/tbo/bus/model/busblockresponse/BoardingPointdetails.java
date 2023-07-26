
package com.nictbills.app.activities.tbo.bus.model.busblockresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoardingPointdetails {

    @SerializedName("CityPointAddress")
    @Expose
    private String cityPointAddress;
    @SerializedName("CityPointContactNumber")
    @Expose
    private String cityPointContactNumber;
    @SerializedName("CityPointIndex")
    @Expose
    private Integer cityPointIndex;
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

    public String getCityPointAddress() {
        return cityPointAddress;
    }

    public void setCityPointAddress(String cityPointAddress) {
        this.cityPointAddress = cityPointAddress;
    }

    public String getCityPointContactNumber() {
        return cityPointContactNumber;
    }

    public void setCityPointContactNumber(String cityPointContactNumber) {
        this.cityPointContactNumber = cityPointContactNumber;
    }

    public Integer getCityPointIndex() {
        return cityPointIndex;
    }

    public void setCityPointIndex(Integer cityPointIndex) {
        this.cityPointIndex = cityPointIndex;
    }

    public String getCityPointLandmark() {
        return cityPointLandmark;
    }

    public void setCityPointLandmark(String cityPointLandmark) {
        this.cityPointLandmark = cityPointLandmark;
    }

    public String getCityPointLocation() {
        return cityPointLocation;
    }

    public void setCityPointLocation(String cityPointLocation) {
        this.cityPointLocation = cityPointLocation;
    }

    public String getCityPointName() {
        return cityPointName;
    }

    public void setCityPointName(String cityPointName) {
        this.cityPointName = cityPointName;
    }

    public String getCityPointTime() {
        return cityPointTime;
    }

    public void setCityPointTime(String cityPointTime) {
        this.cityPointTime = cityPointTime;
    }

}
