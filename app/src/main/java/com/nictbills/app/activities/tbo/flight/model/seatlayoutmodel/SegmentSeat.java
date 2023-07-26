
package com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SegmentSeat implements Serializable
{

    @SerializedName("RowSeats")
    @Expose
    private List<RowSeat> rowSeats = null;
    private final static long serialVersionUID = 7484451457579569994L;

    public List<RowSeat> getRowSeats() {
        return rowSeats;
    }

    public void setRowSeats(List<RowSeat> rowSeats) {
        this.rowSeats = rowSeats;
    }

    public SegmentSeat withRowSeats(List<RowSeat> rowSeats) {
        this.rowSeats = rowSeats;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SegmentSeat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("rowSeats");
        sb.append('=');
        sb.append(((this.rowSeats == null)?"<null>":this.rowSeats));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
