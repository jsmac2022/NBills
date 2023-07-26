
package com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeatDynamic implements Serializable
{

    @SerializedName("SegmentSeat")
    @Expose
    private List<SegmentSeat> segmentSeat = null;
    private final static long serialVersionUID = -5583941991620971414L;

    public List<SegmentSeat> getSegmentSeat() {
        return segmentSeat;
    }

    public void setSegmentSeat(List<SegmentSeat> segmentSeat) {
        this.segmentSeat = segmentSeat;
    }

    public SeatDynamic withSegmentSeat(List<SegmentSeat> segmentSeat) {
        this.segmentSeat = segmentSeat;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SeatDynamic.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("segmentSeat");
        sb.append('=');
        sb.append(((this.segmentSeat == null)?"<null>":this.segmentSeat));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
