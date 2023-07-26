
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchrequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelSearchRequest {

    @SerializedName("CheckInDate")
    @Expose
    private String checkInDate;
    @SerializedName("NoOfNights")
    @Expose
    private String noOfNights;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("CityId")
    @Expose
    private String cityId;

    public String getIsmapped() {
        return ismapped;
    }

    public void setIsmapped(String ismapped) {
        this.ismapped = ismapped;
    }

    @SerializedName("IsTBOMapped")
    @Expose
    private String ismapped;

    @SerializedName("ResultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("PreferredCurrency")
    @Expose
    private String preferredCurrency;
    @SerializedName("GuestNationality")
    @Expose
    private String guestNationality;
    @SerializedName("NoOfRooms")
    @Expose
    private String noOfRooms;
    @SerializedName("RoomGuests")
    @Expose
    private List<RoomGuest> roomGuests = null;
    @SerializedName("MaxRating")
    @Expose
    private Integer maxRating;
    @SerializedName("MinRating")
    @Expose
    private Integer minRating;
    @SerializedName("ReviewScore")
    @Expose
    private Integer reviewScore;
    @SerializedName("IsNearBySearchAllowed")
    @Expose
    private Boolean isNearBySearchAllowed;
    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(String noOfNights) {
        this.noOfNights = noOfNights;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public String getPreferredCurrency() {
        return preferredCurrency;
    }

    public void setPreferredCurrency(String preferredCurrency) {
        this.preferredCurrency = preferredCurrency;
    }

    public String getGuestNationality() {
        return guestNationality;
    }

    public void setGuestNationality(String guestNationality) {
        this.guestNationality = guestNationality;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public List<RoomGuest> getRoomGuests() {
        return roomGuests;
    }

    public void setRoomGuests(List<RoomGuest> roomGuests) {
        this.roomGuests = roomGuests;
    }

    public Integer getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Integer maxRating) {
        this.maxRating = maxRating;
    }

    public Integer getMinRating() {
        return minRating;
    }

    public void setMinRating(Integer minRating) {
        this.minRating = minRating;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Boolean getIsNearBySearchAllowed() {
        return isNearBySearchAllowed;
    }

    public void setIsNearBySearchAllowed(Boolean isNearBySearchAllowed) {
        this.isNearBySearchAllowed = isNearBySearchAllowed;
    }

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

}
