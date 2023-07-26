
package com.nictbills.app.activities.tbo.bus.model.busseatmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeatLayout {

    @SerializedName("NoOfColumns")
    @Expose
    private Integer noOfColumns;
    @SerializedName("NoOfRows")
    @Expose
    private Integer noOfRows;
    @SerializedName("SeatDetails")
    @Expose
    private List<List<SeatDetail>> seatDetails = null;

    public Integer getNoOfColumns() {
        return noOfColumns;
    }

    public void setNoOfColumns(Integer noOfColumns) {
        this.noOfColumns = noOfColumns;
    }

    public Integer getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(Integer noOfRows) {
        this.noOfRows = noOfRows;
    }

    public List<List<SeatDetail>> getSeatDetails() {
        return seatDetails;
    }

    public void setSeatDetails(List<List<SeatDetail>> seatDetails) {
        this.seatDetails = seatDetails;
    }

}
