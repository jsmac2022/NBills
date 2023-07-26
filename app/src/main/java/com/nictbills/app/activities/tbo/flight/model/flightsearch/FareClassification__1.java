
package com.nictbills.app.activities.tbo.flight.model.flightsearch;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FareClassification__1 implements Serializable
{

    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("Type")
    @Expose
    private String type;
    private final static long serialVersionUID = 2966718073675763947L;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
