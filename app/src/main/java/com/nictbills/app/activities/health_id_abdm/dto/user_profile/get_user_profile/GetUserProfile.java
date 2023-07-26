package com.nictbills.app.activities.health_id_abdm.dto.user_profile.get_user_profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserProfile {


    @SerializedName("found")
    @Expose
    private String found;
    @SerializedName("userProfile")
    @Expose
    private UserProfile userProfile;

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

}
