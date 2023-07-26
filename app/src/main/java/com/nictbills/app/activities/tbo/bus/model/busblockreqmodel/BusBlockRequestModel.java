
package com.nictbills.app.activities.tbo.bus.model.busblockreqmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusBlockRequestModel {

    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;

    public String getBordingname() {
        return bordingname;
    }

    public void setBordingname(String bordingname) {
        this.bordingname = bordingname;
    }

    public String getDroppingname() {
        return droppingname;
    }

    public void setDroppingname(String droppingname) {
        this.droppingname = droppingname;
    }

    public String getTravellername() {
        return travellername;
    }

    public void setTravellername(String travellername) {
        this.travellername = travellername;
    }

    @SerializedName("boardingName")
    @Expose
    private String bordingname;

    @SerializedName("droppingName")
    @Expose
    private String droppingname;

    @SerializedName("travellerName")
    @Expose
    private String travellername;


    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;


    @SerializedName("ResultIndex")
    @Expose
    private String resultIndex;
    @SerializedName("TraceId")
    @Expose
    private String traceId;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("BoardingPointId")
    @Expose
    private Integer boardingPointId;
    @SerializedName("DroppingPointId")
    @Expose
    private Integer droppingPointId;
    @SerializedName("Passenger")
    @Expose
    private List<Passenger> passenger = null;

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
    }
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getBoardingPointId() {
        return boardingPointId;
    }

    public void setBoardingPointId(Integer boardingPointId) {
        this.boardingPointId = boardingPointId;
    }

    public Integer getDroppingPointId() {
        return droppingPointId;
    }

    public void setDroppingPointId(Integer droppingPointId) {
        this.droppingPointId = droppingPointId;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }

}
