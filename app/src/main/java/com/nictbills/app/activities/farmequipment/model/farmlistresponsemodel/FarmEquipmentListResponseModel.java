
package com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class   FarmEquipmentListResponseModel implements Serializable {

    @SerializedName("available_location")
    @Expose
    private String availableLocation;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image_path")
    @Expose
    private String imagePath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("rent_amount")
    @Expose
    private String rent_amount;
    public String getRent_amount() {
        return rent_amount;
    }

    public void setRent_amount(String rent_amount) {
        this.rent_amount = rent_amount;
    }




    public String getAvailableLocation() {
        return availableLocation;
    }

    public void setAvailableLocation(String availableLocation) {
        this.availableLocation = availableLocation;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
