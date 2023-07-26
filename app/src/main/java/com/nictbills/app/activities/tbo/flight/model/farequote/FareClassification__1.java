
package com.nictbills.app.activities.tbo.flight.model.farequote;

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
    private final static long serialVersionUID = -8489413596915485261L;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public FareClassification__1 withColor(String color) {
        this.color = color;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FareClassification__1 withType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FareClassification__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("color");
        sb.append('=');
        sb.append(((this.color == null)?"<null>":this.color));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
