
package com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierHotelCode {

    @SerializedName("CategoryId")
    @Expose
    private String categoryId;
    @SerializedName("CategoryIndex")
    @Expose
    private Integer categoryIndex;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(Integer categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

}
