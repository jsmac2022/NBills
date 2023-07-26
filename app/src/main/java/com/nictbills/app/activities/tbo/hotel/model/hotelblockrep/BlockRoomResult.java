
package com.nictbills.app.activities.tbo.hotel.model.hotelblockrep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BlockRoomResult {
    @SerializedName("TraceId")
    @Expose
    private String traceId;

    @SerializedName("IsPriceChanged")
    @Expose
    private boolean IsPriceChanged;

    @SerializedName("AvailabilityType")
    @Expose
    private String availabilityType;
    @SerializedName("passengerId")
    @Expose
    private String passengerId;
    @SerializedName("HotelNorms")
    @Expose
    private String hotelNorms;
    @SerializedName("HotelPolicyDetail")
    @Expose
    private String hotelPolicyDetail;
    @SerializedName("Error")
    @Expose
    private Error error;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(String availabilityType) {
        this.availabilityType = availabilityType;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getHotelNorms() {
        return hotelNorms;
    }

    public void setHotelNorms(String hotelNorms) {
        this.hotelNorms = hotelNorms;
    }

    public String getHotelPolicyDetail() {
        return hotelPolicyDetail;
    }

    public void setHotelPolicyDetail(String hotelPolicyDetail) {
        this.hotelPolicyDetail = hotelPolicyDetail;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    public boolean isPriceChanged() {
        return IsPriceChanged;
    }
    public void setPriceChanged(boolean priceChanged) {
        IsPriceChanged = priceChanged;
    }
}
