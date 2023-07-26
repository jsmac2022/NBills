package com.nictbills.app.activities.health_id_abdm.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCircleList {

        @SerializedName("circleList")
        @Expose
        private List<String> circleList = null;

        public List<String> getCircleList() {
            return circleList;
        }

        public void setCircleList(List<String> circleList) {
            this.circleList = circleList;
        }
}
