
package com.nictbills.app.activities.tbo.bus.model.busseatmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusseatResponsemodel {

    @SerializedName("GetBusSeatLayOutResult")
    @Expose
    private GetBusSeatLayOutResult getBusSeatLayOutResult;

    public GetBusSeatLayOutResult getGetBusSeatLayOutResult() {
        return getBusSeatLayOutResult;
    }

    public void setGetBusSeatLayOutResult(GetBusSeatLayOutResult getBusSeatLayOutResult) {
        this.getBusSeatLayOutResult = getBusSeatLayOutResult;
    }

}
