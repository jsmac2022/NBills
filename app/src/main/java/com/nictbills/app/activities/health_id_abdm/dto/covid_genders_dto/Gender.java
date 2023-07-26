package com.nictbills.app.activities.health_id_abdm.dto.covid_genders_dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gender {

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id")
    @Expose
    private Integer id;


    public Gender(String gender, Integer id) {
        this.gender = gender;
        this.id = id;

    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
