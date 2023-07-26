package com.nictbills.app.activities.health_id_abdm.dto.covid_genders_dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidGendersDetails {

    @SerializedName("genders")
    private List<Gender> genders = null;
    @SerializedName("ttl")
    private Integer ttl;

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
