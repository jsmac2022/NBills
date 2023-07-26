
package com.nictbills.app.activities.tbo.flight.model.farequote;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Origin implements Serializable
{

    @SerializedName("Airport")
    @Expose
    private Airport airport;
    @SerializedName("DepTime")
    @Expose
    private String depTime;
    private final static long serialVersionUID = -5980154784182415019L;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Origin withAirport(Airport airport) {
        this.airport = airport;
        return this;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public Origin withDepTime(String depTime) {
        this.depTime = depTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Origin.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("airport");
        sb.append('=');
        sb.append(((this.airport == null)?"<null>":this.airport));
        sb.append(',');
        sb.append("depTime");
        sb.append('=');
        sb.append(((this.depTime == null)?"<null>":this.depTime));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
