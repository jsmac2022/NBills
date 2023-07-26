
package com.nictbills.app.activities.tbo.hotel.model.hotelinfomodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelInfoResult implements Serializable
{

    @SerializedName("ResponseStatus")
    @Expose
    private int responseStatus;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("HotelDetails")
    @Expose
    private HotelDetails hotelDetails;
    private final static long serialVersionUID = 1868011444932080224L;

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public HotelInfoResult withResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public HotelInfoResult withError(java.lang.Error error) {
        this.error = error;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public HotelInfoResult withTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public HotelDetails getHotelDetails() {
        return hotelDetails;
    }

    public void setHotelDetails(HotelDetails hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    public HotelInfoResult withHotelDetails(HotelDetails hotelDetails) {
        this.hotelDetails = hotelDetails;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelInfoResult.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("responseStatus");
        sb.append('=');
        sb.append(this.responseStatus);
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null)?"<null>":this.traceId));
        sb.append(',');
        sb.append("hotelDetails");
        sb.append('=');
        sb.append(((this.hotelDetails == null)?"<null>":this.hotelDetails));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
