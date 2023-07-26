
package com.nictbills.app.activities.tbo.bus.model.buscitylist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusCityListResponsemodel {

    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Error")
    @Expose
    private Object error;
    @SerializedName("BusCities")
    @Expose
    private List<BusCity> busCities = null;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<BusCity> getBusCities() {
        return busCities;
    }

    public void setBusCities(List<BusCity> busCities) {
        this.busCities = busCities;
    }

}
