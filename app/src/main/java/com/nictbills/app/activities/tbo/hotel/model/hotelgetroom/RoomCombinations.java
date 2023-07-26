
package com.nictbills.app.activities.tbo.hotel.model.hotelgetroom;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomCombinations implements Serializable
{

    @SerializedName("InfoSource")
    @Expose
    private String infoSource;
    @SerializedName("IsPolicyPerStay")
    @Expose
    private boolean isPolicyPerStay;
    @SerializedName("RoomCombination")
    @Expose
    private List<RoomCombination> roomCombination = null;
    private final static long serialVersionUID = -2696056435782521454L;

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public RoomCombinations withInfoSource(String infoSource) {
        this.infoSource = infoSource;
        return this;
    }

    public boolean isIsPolicyPerStay() {
        return isPolicyPerStay;
    }

    public void setIsPolicyPerStay(boolean isPolicyPerStay) {
        this.isPolicyPerStay = isPolicyPerStay;
    }

    public RoomCombinations withIsPolicyPerStay(boolean isPolicyPerStay) {
        this.isPolicyPerStay = isPolicyPerStay;
        return this;
    }

    public List<RoomCombination> getRoomCombination() {
        return roomCombination;
    }

    public void setRoomCombination(List<RoomCombination> roomCombination) {
        this.roomCombination = roomCombination;
    }

    public RoomCombinations withRoomCombination(List<RoomCombination> roomCombination) {
        this.roomCombination = roomCombination;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RoomCombinations.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("infoSource");
        sb.append('=');
        sb.append(((this.infoSource == null)?"<null>":this.infoSource));
        sb.append(',');
        sb.append("isPolicyPerStay");
        sb.append('=');
        sb.append(this.isPolicyPerStay);
        sb.append(',');
        sb.append("roomCombination");
        sb.append('=');
        sb.append(((this.roomCombination == null)?"<null>":this.roomCombination));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
