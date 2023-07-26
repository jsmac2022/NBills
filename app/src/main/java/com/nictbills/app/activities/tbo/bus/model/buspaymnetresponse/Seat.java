
package com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat {

    @SerializedName("SeatIndex")
    @Expose
    private Integer seatIndex;
    @SerializedName("SeatStatus")
    @Expose
    private Boolean seatStatus;
    @SerializedName("IsMalesSeat")
    @Expose
    private Boolean isMalesSeat;
    @SerializedName("SeatName")
    @Expose
    private Integer seatName;
    @SerializedName("Price")
    @Expose
    private Price price;
    @SerializedName("ColumnNo")
    @Expose
    private Integer columnNo;
    @SerializedName("SeatType")
    @Expose
    private Integer seatType;
    @SerializedName("Height")
    @Expose
    private Integer height;
    @SerializedName("RowNo")
    @Expose
    private Integer rowNo;
    @SerializedName("Width")
    @Expose
    private Integer width;
    @SerializedName("IsLadiesSeat")
    @Expose
    private Boolean isLadiesSeat;
    @SerializedName("IsUpper")
    @Expose
    private Boolean isUpper;

    public Integer getSeatIndex() {
        return seatIndex;
    }

    public void setSeatIndex(Integer seatIndex) {
        this.seatIndex = seatIndex;
    }

    public Boolean getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Boolean seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Boolean getIsMalesSeat() {
        return isMalesSeat;
    }

    public void setIsMalesSeat(Boolean isMalesSeat) {
        this.isMalesSeat = isMalesSeat;
    }

    public Integer getSeatName() {
        return seatName;
    }

    public void setSeatName(Integer seatName) {
        this.seatName = seatName;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(Integer columnNo) {
        this.columnNo = columnNo;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getIsLadiesSeat() {
        return isLadiesSeat;
    }

    public void setIsLadiesSeat(Boolean isLadiesSeat) {
        this.isLadiesSeat = isLadiesSeat;
    }

    public Boolean getIsUpper() {
        return isUpper;
    }

    public void setIsUpper(Boolean isUpper) {
        this.isUpper = isUpper;
    }

}
