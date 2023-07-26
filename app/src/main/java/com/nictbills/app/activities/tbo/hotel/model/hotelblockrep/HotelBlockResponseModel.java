
package com.nictbills.app.activities.tbo.hotel.model.hotelblockrep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelBlockResponseModel {

    @SerializedName("BlockRoomResult")
    @Expose
    private BlockRoomResult blockRoomResult;

    public BlockRoomResult getBlockRoomResult() {
        return blockRoomResult;
    }

    public void setBlockRoomResult(BlockRoomResult blockRoomResult) {
        this.blockRoomResult = blockRoomResult;
    }

}
