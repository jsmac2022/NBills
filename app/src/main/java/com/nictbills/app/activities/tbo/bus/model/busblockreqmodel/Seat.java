
package com.nictbills.app.activities.tbo.bus.model.busblockreqmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat {

    @SerializedName("ColumnNo")
    @Expose
    private String columnNo;
    @SerializedName("Height")
    @Expose
    private Integer height;
    @SerializedName("IsLadiesSeat")
    @Expose
    private Boolean isLadiesSeat;
    @SerializedName("IsMalesSeat")
    @Expose
    private Boolean isMalesSeat;
    @SerializedName("IsUpper")
    @Expose
    private Boolean isUpper;
    @SerializedName("RowNo")
    @Expose
    private String rowNo;
    @SerializedName("SeatIndex")
    @Expose
    private String seatIndex;
    @SerializedName("SeatName")
    @Expose
    private String seatName;
    @SerializedName("SeatStatus")
    @Expose
    private Boolean seatStatus;
    @SerializedName("SeatType")
    @Expose
    private Integer seatType;
    @SerializedName("Width")
    @Expose
    private Integer width;
    @SerializedName("Price")
    @Expose
    private Price price;

    public String getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(String columnNo) {
        this.columnNo = columnNo;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getIsLadiesSeat() {
        return isLadiesSeat;
    }

    public void setIsLadiesSeat(Boolean isLadiesSeat) {
        this.isLadiesSeat = isLadiesSeat;
    }

    public Boolean getIsMalesSeat() {
        return isMalesSeat;
    }

    public void setIsMalesSeat(Boolean isMalesSeat) {
        this.isMalesSeat = isMalesSeat;
    }

    public Boolean getIsUpper() {
        return isUpper;
    }

    public void setIsUpper(Boolean isUpper) {
        this.isUpper = isUpper;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public String getSeatIndex() {
        return seatIndex;
    }

    public void setSeatIndex(String seatIndex) {
        this.seatIndex = seatIndex;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Boolean getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Boolean seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Integer getSeatType() {
        return seatType;
    }

    public void setSeatType(Integer seatType) {
        this.seatType = seatType;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

}
