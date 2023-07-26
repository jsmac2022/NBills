
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRoomsDetail implements Serializable
{



    @SerializedName("CategoryId")
    @Expose
    private String CategoryId;

    @SerializedName("AvailabilityType")
    @Expose
    private String availabilityType;
    @SerializedName("ChildCount")
    @Expose
    private int childCount;
    @SerializedName("RequireAllPaxDetails")
    @Expose
    private boolean requireAllPaxDetails;
    @SerializedName("RoomId")
    @Expose
    private int roomId;
    @SerializedName("RoomStatus")
    @Expose
    private int roomStatus;
    @SerializedName("RoomIndex")
    @Expose
    private int roomIndex;
    @SerializedName("RoomTypeCode")
    @Expose
    private String roomTypeCode;
    @SerializedName("RoomDescription")
    @Expose
    private String roomDescription;
    @SerializedName("RoomTypeName")
    @Expose
    private String roomTypeName;
    @SerializedName("RatePlanCode")
    @Expose
    private String ratePlanCode;
    @SerializedName("RatePlan")
    @Expose
    private int ratePlan;
    @SerializedName("RatePlanName")
    @Expose
    private String ratePlanName;
    @SerializedName("InfoSource")
    @Expose
    private String infoSource;
    @SerializedName("SequenceNo")
    @Expose
    private String sequenceNo;
    @SerializedName("DayRates")
    @Expose
    private List<DayRate> dayRates = null;
    @SerializedName("IsPerStay")
    @Expose
    private boolean isPerStay;
    @SerializedName("SupplierPrice")
    @Expose
    private Object supplierPrice;
    @SerializedName("Price")
    @Expose
    private Price price;
    @SerializedName("RoomPromotion")
    @Expose
    private String roomPromotion;
    @SerializedName("Amenities")
    @Expose
    private List<String> amenities = null;
    @SerializedName("Amenity")
    @Expose
    private List<String> amenity = null;
    @SerializedName("SmokingPreference")
    @Expose
    private String smokingPreference;
    @SerializedName("BedTypes")
    @Expose
    private List<Object> bedTypes = null;
    @SerializedName("HotelSupplements")
    @Expose
    private List<Object> hotelSupplements = null;
    @SerializedName("LastCancellationDate")
    @Expose
    private String lastCancellationDate;
    @SerializedName("CancellationPolicies")
    @Expose
    private List<CancellationPolicy> cancellationPolicies = null;
    @SerializedName("LastVoucherDate")
    @Expose
    private String lastVoucherDate;
    @SerializedName("CancellationPolicy")
    @Expose
    private String cancellationPolicy;
    @SerializedName("Inclusion")
    @Expose
    private List<String> inclusion = null;
    @SerializedName("IsPassportMandatory")
    @Expose
    private boolean isPassportMandatory;
    @SerializedName("IsPANMandatory")
    @Expose
    private boolean isPANMandatory;
    private final static long serialVersionUID = -3204265396815239263L;

    public String getAvailabilityType() {
        return availabilityType;
    }

    public void setAvailabilityType(String availabilityType) {
        this.availabilityType = availabilityType;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
    public HotelRoomsDetail withAvailabilityType(String availabilityType) {
        this.availabilityType = availabilityType;
        return this;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public HotelRoomsDetail withChildCount(int childCount) {
        this.childCount = childCount;
        return this;
    }

    public boolean isRequireAllPaxDetails() {
        return requireAllPaxDetails;
    }

    public void setRequireAllPaxDetails(boolean requireAllPaxDetails) {
        this.requireAllPaxDetails = requireAllPaxDetails;
    }

    public HotelRoomsDetail withRequireAllPaxDetails(boolean requireAllPaxDetails) {
        this.requireAllPaxDetails = requireAllPaxDetails;
        return this;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public HotelRoomsDetail withRoomId(int roomId) {
        this.roomId = roomId;
        return this;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public HotelRoomsDetail withRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
        return this;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public HotelRoomsDetail withRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
        return this;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public HotelRoomsDetail withRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
        return this;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public HotelRoomsDetail withRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
        return this;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public HotelRoomsDetail withRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
        return this;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public HotelRoomsDetail withRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
        return this;
    }

    public int getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(int ratePlan) {
        this.ratePlan = ratePlan;
    }

    public HotelRoomsDetail withRatePlan(int ratePlan) {
        this.ratePlan = ratePlan;
        return this;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public HotelRoomsDetail withRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
        return this;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public HotelRoomsDetail withInfoSource(String infoSource) {
        this.infoSource = infoSource;
        return this;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public HotelRoomsDetail withSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
        return this;
    }

    public List<DayRate> getDayRates() {
        return dayRates;
    }

    public void setDayRates(List<DayRate> dayRates) {
        this.dayRates = dayRates;
    }

    public HotelRoomsDetail withDayRates(List<DayRate> dayRates) {
        this.dayRates = dayRates;
        return this;
    }

    public boolean isIsPerStay() {
        return isPerStay;
    }

    public void setIsPerStay(boolean isPerStay) {
        this.isPerStay = isPerStay;
    }

    public HotelRoomsDetail withIsPerStay(boolean isPerStay) {
        this.isPerStay = isPerStay;
        return this;
    }

    public Object getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(Object supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public HotelRoomsDetail withSupplierPrice(Object supplierPrice) {
        this.supplierPrice = supplierPrice;
        return this;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public HotelRoomsDetail withPrice(Price price) {
        this.price = price;
        return this;
    }

    public String getRoomPromotion() {
        return roomPromotion;
    }

    public void setRoomPromotion(String roomPromotion) {
        this.roomPromotion = roomPromotion;
    }

    public HotelRoomsDetail withRoomPromotion(String roomPromotion) {
        this.roomPromotion = roomPromotion;
        return this;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public HotelRoomsDetail withAmenities(List<String> amenities) {
        this.amenities = amenities;
        return this;
    }

    public List<String> getAmenity() {
        return amenity;
    }

    public void setAmenity(List<String> amenity) {
        this.amenity = amenity;
    }

    public HotelRoomsDetail withAmenity(List<String> amenity) {
        this.amenity = amenity;
        return this;
    }

    public String getSmokingPreference() {
        return smokingPreference;
    }

    public void setSmokingPreference(String smokingPreference) {
        this.smokingPreference = smokingPreference;
    }

    public HotelRoomsDetail withSmokingPreference(String smokingPreference) {
        this.smokingPreference = smokingPreference;
        return this;
    }

    public List<Object> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(List<Object> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public HotelRoomsDetail withBedTypes(List<Object> bedTypes) {
        this.bedTypes = bedTypes;
        return this;
    }

    public List<Object> getHotelSupplements() {
        return hotelSupplements;
    }

    public void setHotelSupplements(List<Object> hotelSupplements) {
        this.hotelSupplements = hotelSupplements;
    }

    public HotelRoomsDetail withHotelSupplements(List<Object> hotelSupplements) {
        this.hotelSupplements = hotelSupplements;
        return this;
    }

    public String getLastCancellationDate() {
        return lastCancellationDate;
    }

    public void setLastCancellationDate(String lastCancellationDate) {
        this.lastCancellationDate = lastCancellationDate;
    }

    public HotelRoomsDetail withLastCancellationDate(String lastCancellationDate) {
        this.lastCancellationDate = lastCancellationDate;
        return this;
    }

    public List<CancellationPolicy> getCancellationPolicies() {
        return cancellationPolicies;
    }

    public void setCancellationPolicies(List<CancellationPolicy> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
    }

    public HotelRoomsDetail withCancellationPolicies(List<CancellationPolicy> cancellationPolicies) {
        this.cancellationPolicies = cancellationPolicies;
        return this;
    }

    public String getLastVoucherDate() {
        return lastVoucherDate;
    }

    public void setLastVoucherDate(String lastVoucherDate) {
        this.lastVoucherDate = lastVoucherDate;
    }

    public HotelRoomsDetail withLastVoucherDate(String lastVoucherDate) {
        this.lastVoucherDate = lastVoucherDate;
        return this;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public HotelRoomsDetail withCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
        return this;
    }

    public List<String> getInclusion() {
        return inclusion;
    }

    public void setInclusion(List<String> inclusion) {
        this.inclusion = inclusion;
    }

    public HotelRoomsDetail withInclusion(List<String> inclusion) {
        this.inclusion = inclusion;
        return this;
    }

    public boolean isIsPassportMandatory() {
        return isPassportMandatory;
    }

    public void setIsPassportMandatory(boolean isPassportMandatory) {
        this.isPassportMandatory = isPassportMandatory;
    }

    public HotelRoomsDetail withIsPassportMandatory(boolean isPassportMandatory) {
        this.isPassportMandatory = isPassportMandatory;
        return this;
    }

    public boolean isIsPANMandatory() {
        return isPANMandatory;
    }

    public void setIsPANMandatory(boolean isPANMandatory) {
        this.isPANMandatory = isPANMandatory;
    }

    public HotelRoomsDetail withIsPANMandatory(boolean isPANMandatory) {
        this.isPANMandatory = isPANMandatory;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelRoomsDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("availabilityType");
        sb.append('=');
        sb.append(((this.availabilityType == null)?"<null>":this.availabilityType));

        sb.append(HotelRoomsDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("CategoryId");
        sb.append('=');
        sb.append(((this.CategoryId == null)?"<null>":this.CategoryId));
        sb.append(',');
        sb.append("childCount");
        sb.append('=');
        sb.append(this.childCount);
        sb.append(',');
        sb.append("requireAllPaxDetails");
        sb.append('=');
        sb.append(this.requireAllPaxDetails);
        sb.append(',');
        sb.append("roomId");
        sb.append('=');
        sb.append(this.roomId);
        sb.append(',');
        sb.append("roomStatus");
        sb.append('=');
        sb.append(this.roomStatus);
        sb.append(',');
        sb.append("roomIndex");
        sb.append('=');
        sb.append(this.roomIndex);
        sb.append(',');
        sb.append("roomTypeCode");
        sb.append('=');
        sb.append(((this.roomTypeCode == null)?"<null>":this.roomTypeCode));
        sb.append(',');
        sb.append("roomDescription");
        sb.append('=');
        sb.append(((this.roomDescription == null)?"<null>":this.roomDescription));
        sb.append(',');
        sb.append("roomTypeName");
        sb.append('=');
        sb.append(((this.roomTypeName == null)?"<null>":this.roomTypeName));
        sb.append(',');
        sb.append("ratePlanCode");
        sb.append('=');
        sb.append(((this.ratePlanCode == null)?"<null>":this.ratePlanCode));
        sb.append(',');
        sb.append("ratePlan");
        sb.append('=');
        sb.append(this.ratePlan);
        sb.append(',');
        sb.append("ratePlanName");
        sb.append('=');
        sb.append(((this.ratePlanName == null)?"<null>":this.ratePlanName));
        sb.append(',');
        sb.append("infoSource");
        sb.append('=');
        sb.append(((this.infoSource == null)?"<null>":this.infoSource));
        sb.append(',');
        sb.append("sequenceNo");
        sb.append('=');
        sb.append(((this.sequenceNo == null)?"<null>":this.sequenceNo));
        sb.append(',');
        sb.append("dayRates");
        sb.append('=');
        sb.append(((this.dayRates == null)?"<null>":this.dayRates));
        sb.append(',');
        sb.append("isPerStay");
        sb.append('=');
        sb.append(this.isPerStay);
        sb.append(',');
        sb.append("supplierPrice");
        sb.append('=');
        sb.append(((this.supplierPrice == null)?"<null>":this.supplierPrice));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(((this.price == null)?"<null>":this.price));
        sb.append(',');
        sb.append("roomPromotion");
        sb.append('=');
        sb.append(((this.roomPromotion == null)?"<null>":this.roomPromotion));
        sb.append(',');
        sb.append("amenities");
        sb.append('=');
        sb.append(((this.amenities == null)?"<null>":this.amenities));
        sb.append(',');
        sb.append("amenity");
        sb.append('=');
        sb.append(((this.amenity == null)?"<null>":this.amenity));
        sb.append(',');
        sb.append("smokingPreference");
        sb.append('=');
        sb.append(((this.smokingPreference == null)?"<null>":this.smokingPreference));
        sb.append(',');
        sb.append("bedTypes");
        sb.append('=');
        sb.append(((this.bedTypes == null)?"<null>":this.bedTypes));
        sb.append(',');
        sb.append("hotelSupplements");
        sb.append('=');
        sb.append(((this.hotelSupplements == null)?"<null>":this.hotelSupplements));
        sb.append(',');
        sb.append("lastCancellationDate");
        sb.append('=');
        sb.append(((this.lastCancellationDate == null)?"<null>":this.lastCancellationDate));
        sb.append(',');
        sb.append("cancellationPolicies");
        sb.append('=');
        sb.append(((this.cancellationPolicies == null)?"<null>":this.cancellationPolicies));
        sb.append(',');
        sb.append("lastVoucherDate");
        sb.append('=');
        sb.append(((this.lastVoucherDate == null)?"<null>":this.lastVoucherDate));
        sb.append(',');
        sb.append("cancellationPolicy");
        sb.append('=');
        sb.append(((this.cancellationPolicy == null)?"<null>":this.cancellationPolicy));
        sb.append(',');
        sb.append("inclusion");
        sb.append('=');
        sb.append(((this.inclusion == null)?"<null>":this.inclusion));
        sb.append(',');
        sb.append("isPassportMandatory");
        sb.append('=');
        sb.append(this.isPassportMandatory);
        sb.append(',');
        sb.append("isPANMandatory");
        sb.append('=');
        sb.append(this.isPANMandatory);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
