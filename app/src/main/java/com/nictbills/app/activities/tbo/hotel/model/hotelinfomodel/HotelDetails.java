
package com.nictbills.app.activities.tbo.hotel.model.hotelinfomodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetails implements Serializable
{

    @SerializedName("HotelCode")
    @Expose
    private String hotelCode;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("StarRating")
    @Expose
    private int starRating;
    @SerializedName("HotelURL")
    @Expose
    private Object hotelURL;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Attractions")
    @Expose
    private List<Attraction> attractions = null;
    @SerializedName("HotelFacilities")
    @Expose
    private List<String> hotelFacilities = null;
    @SerializedName("HotelPolicy")
    @Expose
    private Object hotelPolicy;
    @SerializedName("SpecialInstructions")
    @Expose
    private Object specialInstructions;
    @SerializedName("HotelPicture")
    @Expose
    private Object hotelPicture;
    @SerializedName("Images")
    @Expose
    private List<String> images = null;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("PinCode")
    @Expose
    private String pinCode;
    @SerializedName("HotelContactNo")
    @Expose
    private String hotelContactNo;
    @SerializedName("FaxNumber")
    @Expose
    private String faxNumber;
    @SerializedName("Email")
    @Expose
    private Object email;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("RoomData")
    @Expose
    private Object roomData;
    @SerializedName("RoomFacilities")
    @Expose
    private Object roomFacilities;
    @SerializedName("Services")
    @Expose
    private Object services;
    private final static long serialVersionUID = -3366760385520323044L;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public HotelDetails withHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public HotelDetails withHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public HotelDetails withStarRating(int starRating) {
        this.starRating = starRating;
        return this;
    }

    public Object getHotelURL() {
        return hotelURL;
    }

    public void setHotelURL(Object hotelURL) {
        this.hotelURL = hotelURL;
    }

    public HotelDetails withHotelURL(Object hotelURL) {
        this.hotelURL = hotelURL;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HotelDetails withDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public HotelDetails withAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
        return this;
    }

    public List<String> getHotelFacilities() {
        return hotelFacilities;
    }

    public void setHotelFacilities(List<String> hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
    }

    public HotelDetails withHotelFacilities(List<String> hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
        return this;
    }

    public Object getHotelPolicy() {
        return hotelPolicy;
    }

    public void setHotelPolicy(Object hotelPolicy) {
        this.hotelPolicy = hotelPolicy;
    }

    public HotelDetails withHotelPolicy(Object hotelPolicy) {
        this.hotelPolicy = hotelPolicy;
        return this;
    }

    public Object getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(Object specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public HotelDetails withSpecialInstructions(Object specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    public Object getHotelPicture() {
        return hotelPicture;
    }

    public void setHotelPicture(Object hotelPicture) {
        this.hotelPicture = hotelPicture;
    }

    public HotelDetails withHotelPicture(Object hotelPicture) {
        this.hotelPicture = hotelPicture;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public HotelDetails withImages(List<String> images) {
        this.images = images;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelDetails withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public HotelDetails withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public HotelDetails withPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    public String getHotelContactNo() {
        return hotelContactNo;
    }

    public void setHotelContactNo(String hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
    }

    public HotelDetails withHotelContactNo(String hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
        return this;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public HotelDetails withFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
        return this;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public HotelDetails withEmail(Object email) {
        this.email = email;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public HotelDetails withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public HotelDetails withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public Object getRoomData() {
        return roomData;
    }

    public void setRoomData(Object roomData) {
        this.roomData = roomData;
    }

    public HotelDetails withRoomData(Object roomData) {
        this.roomData = roomData;
        return this;
    }

    public Object getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(Object roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public HotelDetails withRoomFacilities(Object roomFacilities) {
        this.roomFacilities = roomFacilities;
        return this;
    }

    public Object getServices() {
        return services;
    }

    public void setServices(Object services) {
        this.services = services;
    }

    public HotelDetails withServices(Object services) {
        this.services = services;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("hotelCode");
        sb.append('=');
        sb.append(((this.hotelCode == null)?"<null>":this.hotelCode));
        sb.append(',');
        sb.append("hotelName");
        sb.append('=');
        sb.append(((this.hotelName == null)?"<null>":this.hotelName));
        sb.append(',');
        sb.append("starRating");
        sb.append('=');
        sb.append(this.starRating);
        sb.append(',');
        sb.append("hotelURL");
        sb.append('=');
        sb.append(((this.hotelURL == null)?"<null>":this.hotelURL));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("attractions");
        sb.append('=');
        sb.append(((this.attractions == null)?"<null>":this.attractions));
        sb.append(',');
        sb.append("hotelFacilities");
        sb.append('=');
        sb.append(((this.hotelFacilities == null)?"<null>":this.hotelFacilities));
        sb.append(',');
        sb.append("hotelPolicy");
        sb.append('=');
        sb.append(((this.hotelPolicy == null)?"<null>":this.hotelPolicy));
        sb.append(',');
        sb.append("specialInstructions");
        sb.append('=');
        sb.append(((this.specialInstructions == null)?"<null>":this.specialInstructions));
        sb.append(',');
        sb.append("hotelPicture");
        sb.append('=');
        sb.append(((this.hotelPicture == null)?"<null>":this.hotelPicture));
        sb.append(',');
        sb.append("images");
        sb.append('=');
        sb.append(((this.images == null)?"<null>":this.images));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("countryName");
        sb.append('=');
        sb.append(((this.countryName == null)?"<null>":this.countryName));
        sb.append(',');
        sb.append("pinCode");
        sb.append('=');
        sb.append(((this.pinCode == null)?"<null>":this.pinCode));
        sb.append(',');
        sb.append("hotelContactNo");
        sb.append('=');
        sb.append(((this.hotelContactNo == null)?"<null>":this.hotelContactNo));
        sb.append(',');
        sb.append("faxNumber");
        sb.append('=');
        sb.append(((this.faxNumber == null)?"<null>":this.faxNumber));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("latitude");
        sb.append('=');
        sb.append(((this.latitude == null)?"<null>":this.latitude));
        sb.append(',');
        sb.append("longitude");
        sb.append('=');
        sb.append(((this.longitude == null)?"<null>":this.longitude));
        sb.append(',');
        sb.append("roomData");
        sb.append('=');
        sb.append(((this.roomData == null)?"<null>":this.roomData));
        sb.append(',');
        sb.append("roomFacilities");
        sb.append('=');
        sb.append(((this.roomFacilities == null)?"<null>":this.roomFacilities));
        sb.append(',');
        sb.append("services");
        sb.append('=');
        sb.append(((this.services == null)?"<null>":this.services));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
