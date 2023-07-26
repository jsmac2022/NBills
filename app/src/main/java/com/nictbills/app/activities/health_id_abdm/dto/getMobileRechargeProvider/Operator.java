package com.nictbills.app.activities.health_id_abdm.dto.getMobileRechargeProvider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Operator {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("api")
    @Expose
    private Object api;

   /* public Operator(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Object getApi() {
        return api;
    }

    public void setApi(Object api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", api=" + api +
                '}';
    }
}
