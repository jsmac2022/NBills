package com.nictbills.app.activities.health_id_abdm.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IVRSListDto {

        @SerializedName("ivrsNoList")
        @Expose
        private List<IvrsNoList> ivrsNoList = null;

        public List<IvrsNoList> getIvrsNoList() {
            return ivrsNoList;
        }

        public void setIvrsNoList(List<IvrsNoList> ivrsNoList) {
            this.ivrsNoList = ivrsNoList;
        }


}
