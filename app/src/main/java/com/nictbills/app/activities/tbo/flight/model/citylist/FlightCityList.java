
package com.nictbills.app.activities.tbo.flight.model.citylist;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightCityList implements Serializable
{

    @SerializedName("responseCode")
    @Expose
    private int responseCode;
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;
    @SerializedName("messages")
    @Expose
    private String messages;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    private final static long serialVersionUID = -1617209643504810493L;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public FlightCityList withResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public FlightCityList withResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public FlightCityList withMessages(String messages) {
        this.messages = messages;
        return this;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public FlightCityList withData(List<Data> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FlightCityList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("responseCode");
        sb.append('=');
        sb.append(this.responseCode);
        sb.append(',');
        sb.append("responseStatus");
        sb.append('=');
        sb.append(((this.responseStatus == null)?"<null>":this.responseStatus));
        sb.append(',');
        sb.append("messages");
        sb.append('=');
        sb.append(((this.messages == null)?"<null>":this.messages));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
