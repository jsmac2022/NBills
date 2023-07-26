
package com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelBlockRoomRequestmodel implements Serializable
{

    @SerializedName("ResultIndex")
    @Expose
    private String resultIndex;
    @SerializedName("HotelCode")
    @Expose
    private String hotelCode;
    @SerializedName("HotelName")
    @Expose
    private String hotelName;
    @SerializedName("GuestNationality")
    @Expose
    private String guestNationality;
    @SerializedName("NoOfRooms")
    @Expose
    private String noOfRooms;
    @SerializedName("ClientReferenceNo")
    @Expose
    private String clientReferenceNo;



    @SerializedName("CategoryId")
    @Expose
    private String CategoryId;

    @SerializedName("IsVoucherBooking")
    @Expose
    private String isVoucherBooking;
    @SerializedName("HotelRoomsDetails")
    @Expose
    private List<HotelRoomsDetail> hotelRoomsDetails = null;


    @SerializedName("RoomGuests")
    @Expose
    private List<RoomGuests> roomrequestdeails = null;

    @SerializedName("EndUserIp")
    @Expose
    private String endUserIp;

    @SerializedName("mobileNo")
    @Expose
    private String mobileno;

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("checkInDate")
    @Expose
    private String checkindate;

    @SerializedName("checkOutDate")
    @Expose
    private String checkoutdate;

    @SerializedName("TokenId")
    @Expose
    private String tokenId;

    @SerializedName("TraceId")
    @Expose
    private String traceId;
    private final static long serialVersionUID = -710657928910462340L;

    public String getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
    }

    public HotelBlockRoomRequestmodel withResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
        return this;
    }
    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }


    public HotelBlockRoomRequestmodel withHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
        return this;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }


    public HotelBlockRoomRequestmodel withHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getGuestNationality() {
        return guestNationality;
    }

    public void setGuestNationality(String guestNationality) {
        this.guestNationality = guestNationality;
    }

    public HotelBlockRoomRequestmodel withGuestNationality(String guestNationality) {
        this.guestNationality = guestNationality;
        return this;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public HotelBlockRoomRequestmodel withNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
        return this;
    }

    public String getClientReferenceNo() {
        return clientReferenceNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }


    public void setClientReferenceNo(String clientReferenceNo) {
        this.clientReferenceNo = clientReferenceNo;
    }

    public HotelBlockRoomRequestmodel withClientReferenceNo(String clientReferenceNo) {
        this.clientReferenceNo = clientReferenceNo;
        return this;
    }

    public String getIsVoucherBooking() {
        return isVoucherBooking;
    }

    public void setIsVoucherBooking(String isVoucherBooking) {
        this.isVoucherBooking = isVoucherBooking;
    }

    public HotelBlockRoomRequestmodel withIsVoucherBooking(String isVoucherBooking) {
        this.isVoucherBooking = isVoucherBooking;
        return this;
    }

    public List<HotelRoomsDetail> getHotelRoomsDetails() {
        return hotelRoomsDetails;
    }

    public void setHotelRoomsDetails(List<HotelRoomsDetail> hotelRoomsDetails) {
        this.hotelRoomsDetails = hotelRoomsDetails;
    }


    public List<RoomGuests> getRoomrequestdeails() {
        return roomrequestdeails;
    }

    public void setRoomrequestdeails(List<RoomGuests> roomrequestdeails) {
        this.roomrequestdeails = roomrequestdeails;
    }

    public HotelBlockRoomRequestmodel withHotelRoomsDetails(List<HotelRoomsDetail> hotelRoomsDetails) {
        this.hotelRoomsDetails = hotelRoomsDetails;
        return this;
    }

    public String getEndUserIp() {
        return endUserIp;
    }

    public void setEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
    }


    public HotelBlockRoomRequestmodel withEndUserIp(String endUserIp) {
        this.endUserIp = endUserIp;
        return this;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public HotelBlockRoomRequestmodel withTokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public HotelBlockRoomRequestmodel withTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelBlockRoomRequestmodel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("resultIndex");
        sb.append('=');
        sb.append(((this.resultIndex == null)?"<null>":this.resultIndex));
        sb.append(',');
        sb.append("hotelCode");
        sb.append('=');
        sb.append(((this.hotelCode == null)?"<null>":this.hotelCode));
        sb.append(',');
        sb.append("hotelName");
        sb.append('=');
        sb.append(((this.hotelName == null)?"<null>":this.hotelName));
        sb.append(',');
        sb.append("guestNationality");
        sb.append('=');
        sb.append(((this.guestNationality == null)?"<null>":this.guestNationality));
        sb.append(',');
        sb.append("noOfRooms");
        sb.append('=');
        sb.append(((this.noOfRooms == null)?"<null>":this.noOfRooms));
        sb.append(',');
        sb.append("clientReferenceNo");
        sb.append('=');
        sb.append(((this.clientReferenceNo == null)?"<null>":this.clientReferenceNo));
        sb.append(',');
        sb.append("isVoucherBooking");
        sb.append('=');
        sb.append(((this.isVoucherBooking == null)?"<null>":this.isVoucherBooking));
        sb.append(',');
        sb.append("hotelRoomsDetails");
        sb.append('=');
        sb.append(((this.hotelRoomsDetails == null)?"<null>":this.hotelRoomsDetails));
        sb.append(',');
        sb.append("endUserIp");
        sb.append('=');
        sb.append(((this.endUserIp == null)?"<null>":this.endUserIp));
        sb.append(',');
        sb.append("mobileno");
        sb.append('=');
        sb.append(((this.mobileno == null)?"<null>":this.mobileno));
        sb.append(',');
        sb.append("tokenId");
        sb.append('=');
        sb.append(((this.tokenId == null)?"<null>":this.tokenId));
        sb.append(',');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null)?"<null>":this.traceId));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
