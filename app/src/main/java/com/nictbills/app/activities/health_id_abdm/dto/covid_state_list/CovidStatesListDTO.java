package com.nictbills.app.activities.health_id_abdm.dto.covid_state_list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidStatesListDTO {

    @SerializedName("states")
    @Expose
    private List<State> states = null;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

}
