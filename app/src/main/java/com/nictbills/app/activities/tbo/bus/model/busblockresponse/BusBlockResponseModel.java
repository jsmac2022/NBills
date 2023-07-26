
package com.nictbills.app.activities.tbo.bus.model.busblockresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusBlockResponseModel {

    @SerializedName("BlockResult")
    @Expose
    private BlockResult blockResult;

    public BlockResult getBlockResult() {
        return blockResult;
    }

    public void setBlockResult(BlockResult blockResult) {
        this.blockResult = blockResult;
    }

}
