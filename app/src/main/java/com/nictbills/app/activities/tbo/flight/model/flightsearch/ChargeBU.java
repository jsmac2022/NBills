
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChargeBU implements Serializable
{

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private float value;
    private final static long serialVersionUID = -4832433184516233284L;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
