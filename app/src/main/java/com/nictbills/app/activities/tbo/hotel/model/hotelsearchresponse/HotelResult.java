
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelResult {

    @SerializedName("IsHotDeal")
    @Expose
    private Boolean isHotDeal;
    @SerializedName("ResultIndex")
    @Expose
    private Integer resultIndex;
    @SerializedName("HotelCode")
    @Expose
    private String hotelCode;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("HotelCategory")
    @Expose
    private String hotelCategory;
    @SerializedName("StarRating")
    @Expose
    private Integer starRating;
    @SerializedName("HotelDescription")
    @Expose
    private String hotelDescription;
    @SerializedName("HotelPromotion")
    @Expose
    private String hotelPromotion;
    @SerializedName("HotelPolicy")
    @Expose
    private String hotelPolicy;
    @SerializedName("IsTBOMapped")
    @Expose
    private Boolean isTBOMapped;
    @SerializedName("Price")
    @Expose
    private Price price;
    @SerializedName("SupplierHotelCodes")
    @Expose
    private List<SupplierHotelCode> supplierHotelCodes;
    @SerializedName("HotelPicture")
    @Expose
    private String hotelPicture;
    @SerializedName("HotelAddress")
    @Expose
    private String hotelAddress;
    @SerializedName("HotelContactNo")
    @Expose
    private String hotelContactNo;
    @SerializedName("HotelMap")
    @Expose
    private Object hotelMap;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("HotelLocation")
    @Expose
    private Object hotelLocation;
    @SerializedName("SupplierPrice")
    @Expose
    private Object supplierPrice;
    @SerializedName("RoomDetails")
    @Expose
    private List<Object> roomDetails;

    public Boolean getIsHotDeal() {
        return isHotDeal;
    }

    public void setIsHotDeal(Boolean isHotDeal) {
        this.isHotDeal = isHotDeal;
    }

    public Integer getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Integer resultIndex) {
        this.resultIndex = resultIndex;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(String hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelPromotion() {
        return hotelPromotion;
    }

    public void setHotelPromotion(String hotelPromotion) {
        this.hotelPromotion = hotelPromotion;
    }

    public String getHotelPolicy() {
        return hotelPolicy;
    }

    public void setHotelPolicy(String hotelPolicy) {
        this.hotelPolicy = hotelPolicy;
    }

    public Boolean getIsTBOMapped() {
        return isTBOMapped;
    }

    public void setIsTBOMapped(Boolean isTBOMapped) {
        this.isTBOMapped = isTBOMapped;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<SupplierHotelCode> getSupplierHotelCodes() {
        return supplierHotelCodes;
    }

    public void setSupplierHotelCodes(List<SupplierHotelCode> supplierHotelCodes) {
        this.supplierHotelCodes = supplierHotelCodes;
    }

    public String getHotelPicture() {
        return hotelPicture;
    }

    public void setHotelPicture(String hotelPicture) {
        this.hotelPicture = hotelPicture;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelContactNo() {
        return hotelContactNo;
    }

    public void setHotelContactNo(String hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
    }

    public Object getHotelMap() {
        return hotelMap;
    }

    public void setHotelMap(Object hotelMap) {
        this.hotelMap = hotelMap;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(Object hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public Object getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(Object supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public List<Object> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(List<Object> roomDetails) {
        this.roomDetails = roomDetails;
    }

}
