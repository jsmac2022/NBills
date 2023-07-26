package com.nictbills.app.activities.health_id_abdm.dto.covidPhotoId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IdPhoto {

    @SerializedName("types")
    @Expose
    private List<Type> types = null;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

}
