
package com.nictbills.app.activities.tbo.flight.model.citylist;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("cityCodeWord")
    @Expose
    private String cityCodeWord;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("cityCode")
    @Expose
    private String cityCode;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    private final static long serialVersionUID = -4971190708034661522L;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Data withCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getCityCodeWord() {
        return cityCodeWord;
    }

    public void setCityCodeWord(String cityCodeWord) {
        this.cityCodeWord = cityCodeWord;
    }

    public Data withCityCodeWord(String cityCodeWord) {
        this.cityCodeWord = cityCodeWord;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Data withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Data withCityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Data withCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("countryCode");
        sb.append('=');
        sb.append(((this.countryCode == null)?"<null>":this.countryCode));
        sb.append(',');
        sb.append("cityCodeWord");
        sb.append('=');
        sb.append(((this.cityCodeWord == null)?"<null>":this.cityCodeWord));
        sb.append(',');
        sb.append("countryName");
        sb.append('=');
        sb.append(((this.countryName == null)?"<null>":this.countryName));
        sb.append(',');
        sb.append("cityCode");
        sb.append('=');
        sb.append(((this.cityCode == null)?"<null>":this.cityCode));
        sb.append(',');
        sb.append("cityName");
        sb.append('=');
        sb.append(((this.cityName == null)?"<null>":this.cityName));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
