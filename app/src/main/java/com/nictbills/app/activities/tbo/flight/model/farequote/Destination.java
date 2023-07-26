
package com.nictbills.app.activities.tbo.flight.model.farequote;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destination implements Serializable
{

    @SerializedName("Airport")
    @Expose
    private Airport__1 airport;
    @SerializedName("ArrTime")
    @Expose
    private String arrTime;
    private final static long serialVersionUID = 7274171611514669704L;

    public Airport__1 getAirport() {
        return airport;
    }

    public void setAirport(Airport__1 airport) {
        this.airport = airport;
    }

    public Destination withAirport(Airport__1 airport) {
        this.airport = airport;
        return this;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public Destination withArrTime(String arrTime) {
        this.arrTime = arrTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Destination.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("airport");
        sb.append('=');
        sb.append(((this.airport == null)?"<null>":this.airport));
        sb.append(',');
        sb.append("arrTime");
        sb.append('=');
        sb.append(((this.arrTime == null)?"<null>":this.arrTime));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
