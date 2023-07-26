package com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    @SerializedName("additionalProp1")
    @Expose
    private String additionalProp1;
    @SerializedName("additionalProp2")
    @Expose
    private String additionalProp2;
    @SerializedName("additionalProp3")
    @Expose
    private String additionalProp3;

    public String getAdditionalProp1() {
        return additionalProp1;
    }

    public void setAdditionalProp1(String additionalProp1) {
        this.additionalProp1 = additionalProp1;
    }

    public String getAdditionalProp2() {
        return additionalProp2;
    }

    public void setAdditionalProp2(String additionalProp2) {
        this.additionalProp2 = additionalProp2;
    }

    public String getAdditionalProp3() {
        return additionalProp3;
    }

    public void setAdditionalProp3(String additionalProp3) {
        this.additionalProp3 = additionalProp3;
    }
}
