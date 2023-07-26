package com.nictbills.app.activities.tbo.flight.model.air_search.air_search_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchFlightRequest  {

    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("AdultCount")
    @Expose
    private String adultCount;
    @SerializedName("ChildCount")
    @Expose
    private String childCount;
    @SerializedName("InfantCount")
    @Expose
    private String infantCount;
    @SerializedName("DirectFlight")
    @Expose
    private String directFlight;
    @SerializedName("OneStopFlight")
    @Expose
    private String oneStopFlight;
    @SerializedName("JourneyType")
    @Expose
    private String journeyType;

    @SerializedName("PreferredAirlines")
    @Expose
    private Object preferredAirlines;

    @SerializedName("Segments")
    @Expose
    private List<Segment> segments = null;
    @SerializedName("Sources")
    @Expose

    private Object sources;

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(String adultCount) {
        this.adultCount = adultCount;
    }

    public String getChildCount() {
        return childCount;
    }

    public void setChildCount(String childCount) {
        this.childCount = childCount;
    }

    public String getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(String infantCount) {
        this.infantCount = infantCount;
    }

    public String getDirectFlight() {
        return directFlight;
    }

    public void setDirectFlight(String directFlight) {
        this.directFlight = directFlight;
    }

    public String getOneStopFlight() {
        return oneStopFlight;
    }

    public void setOneStopFlight(String oneStopFlight) {
        this.oneStopFlight = oneStopFlight;
    }

    public String getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(String journeyType) {
        this.journeyType = journeyType;
    }

    public Object getPreferredAirlines() {
        return preferredAirlines;
    }

    public void setPreferredAirlines(Object preferredAirlines) {
        this.preferredAirlines = preferredAirlines;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Object getSources() {
        return sources;
    }

    public void setSources(Object sources) {
        this.sources = sources;
    }


}
