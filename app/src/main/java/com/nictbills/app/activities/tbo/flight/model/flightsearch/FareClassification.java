
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FareClassification implements Serializable
{

    @SerializedName("Type")
    @Expose
    private String type;
    private final static long serialVersionUID = 4134968498274102209L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
