
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomCombination implements Serializable
{

    @SerializedName("RoomIndex")
    @Expose
    private List<Integer> roomIndex = null;
    private final static long serialVersionUID = 3425292482852984022L;

    public List<Integer> getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(List<Integer> roomIndex) {
        this.roomIndex = roomIndex;
    }

    public RoomCombination withRoomIndex(List<Integer> roomIndex) {
        this.roomIndex = roomIndex;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RoomCombination.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("roomIndex");
        sb.append('=');
        sb.append(((this.roomIndex == null)?"<null>":this.roomIndex));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
