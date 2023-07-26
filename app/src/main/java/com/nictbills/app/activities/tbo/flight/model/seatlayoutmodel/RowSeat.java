
package com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RowSeat implements Serializable
{

    @SerializedName("Seats")
    @Expose
    private List<Seat> seats = null;
    private final static long serialVersionUID = 1178324873109201655L;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public RowSeat withSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RowSeat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("seats");
        sb.append('=');
        sb.append(((this.seats == null)?"<null>":this.seats));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
