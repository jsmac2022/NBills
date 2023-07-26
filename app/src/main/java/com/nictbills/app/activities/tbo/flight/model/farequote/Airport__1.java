
package com.nictbills.app.activities.tbo.flight.model.farequote;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airport__1 implements Serializable
{

    @SerializedName("AirportCode")
    @Expose
    private String airportCode;
    @SerializedName("AirportName")
    @Expose
    private String airportName;
    @SerializedName("Terminal")
    @Expose
    private String terminal;
    @SerializedName("CityCode")
    @Expose
    private String cityCode;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    private final static long serialVersionUID = 2489816353643893726L;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Airport__1 withAirportCode(String airportCode) {
        this.airportCode = airportCode;
        return this;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Airport__1 withAirportName(String airportName) {
        this.airportName = airportName;
        return this;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Airport__1 withTerminal(String terminal) {
        this.terminal = terminal;
        return this;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Airport__1 withCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Airport__1 withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Airport__1 withCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Airport__1 withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Airport__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("airportCode");
        sb.append('=');
        sb.append(((this.airportCode == null)?"<null>":this.airportCode));
        sb.append(',');
        sb.append("airportName");
        sb.append('=');
        sb.append(((this.airportName == null)?"<null>":this.airportName));
        sb.append(',');
        sb.append("terminal");
        sb.append('=');
        sb.append(((this.terminal == null)?"<null>":this.terminal));
        sb.append(',');
        sb.append("cityCode");
        sb.append('=');
        sb.append(((this.cityCode == null)?"<null>":this.cityCode));
        sb.append(',');
        sb.append("cityName");
        sb.append('=');
        sb.append(((this.cityName == null)?"<null>":this.cityName));
        sb.append(',');
        sb.append("countryCode");
        sb.append('=');
        sb.append(((this.countryCode == null)?"<null>":this.countryCode));
        sb.append(',');
        sb.append("countryName");
        sb.append('=');
        sb.append(((this.countryName == null)?"<null>":this.countryName));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
