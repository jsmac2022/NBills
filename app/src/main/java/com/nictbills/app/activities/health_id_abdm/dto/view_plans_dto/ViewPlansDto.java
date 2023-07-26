package com.nictbills.app.activities.health_id_abdm.dto.view_plans_dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewPlansDto {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("recharge_talktime")
    @Expose
    private String rechargeTalktime;
    @SerializedName("recharge_short_description")
    @Expose
    private String rechargeShortDescription;
    @SerializedName("recharge_description")
    @Expose
    private String rechargeDescription;
    @SerializedName("recharge_validity")
    @Expose
    private String rechargeValidity;
    @SerializedName("recharge_value")
    @Expose
    private String rechargeValue;
    @SerializedName("sp_circle")
    @Expose
    private String spCircle;
    @SerializedName("sp_key")
    @Expose
    private String spKey;
    @SerializedName("last_updated_dt")
    @Expose
    private String lastUpdatedDt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRechargeTalktime() {
        return rechargeTalktime;
    }

    public void setRechargeTalktime(String rechargeTalktime) {
        this.rechargeTalktime = rechargeTalktime;
    }

    public String getRechargeShortDescription() {
        return rechargeShortDescription;
    }

    public void setRechargeShortDescription(String rechargeShortDescription) {
        this.rechargeShortDescription = rechargeShortDescription;
    }

    public String getRechargeDescription() {
        return rechargeDescription;
    }

    public void setRechargeDescription(String rechargeDescription) {
        this.rechargeDescription = rechargeDescription;
    }

    public String getRechargeValidity() {
        return rechargeValidity;
    }

    public void setRechargeValidity(String rechargeValidity) {
        this.rechargeValidity = rechargeValidity;
    }

    public String getRechargeValue() {
        return rechargeValue;
    }

    public void setRechargeValue(String rechargeValue) {
        this.rechargeValue = rechargeValue;
    }

    public String getSpCircle() {
        return spCircle;
    }

    public void setSpCircle(String spCircle) {
        this.spCircle = spCircle;
    }

    public String getSpKey() {
        return spKey;
    }

    public void setSpKey(String spKey) {
        this.spKey = spKey;
    }

    public String getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(String lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }

    @Override
    public String toString() {
        return "ViewPlansDto{" +
                "id='" + id + '\'' +
                ", rechargeTalktime='" + rechargeTalktime + '\'' +
                ", rechargeShortDescription='" + rechargeShortDescription + '\'' +
                ", rechargeDescription='" + rechargeDescription + '\'' +
                ", rechargeValidity='" + rechargeValidity + '\'' +
                ", rechargeValue='" + rechargeValue + '\'' +
                ", spCircle='" + spCircle + '\'' +
                ", spKey='" + spKey + '\'' +
                ", lastUpdatedDt='" + lastUpdatedDt + '\'' +
                '}';
    }
}
