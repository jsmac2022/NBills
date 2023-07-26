
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class TaxBreakup implements Serializable
{

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private float value;
    private final static long serialVersionUID = -8615587673537567417L;

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
