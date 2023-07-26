package com.nictbills.app.activities.health_id_abdm.dto.banner;
import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class Result {

@SerializedName("entry_datetime")
@Expose
private String entryDatetime;
@SerializedName("app_name")
@Expose
private String appName;
@SerializedName("img_name")
@Expose
private String imgName;
@SerializedName("img_details")
@Expose
private String imgDetails;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("img_source")
@Expose
private String imgSource;
@SerializedName("redirect_to")
@Expose
private String redirectTo;
@SerializedName("view_status")
@Expose
private String viewStatus;

public String getEntryDatetime() {
return entryDatetime;
}

public void setEntryDatetime(String entryDatetime) {
this.entryDatetime = entryDatetime;
}

public String getAppName() {
return appName;
}

public void setAppName(String appName) {
this.appName = appName;
}

public String getImgName() {
return imgName;
}

public void setImgName(String imgName) {
this.imgName = imgName;
}

public String getImgDetails() {
return imgDetails;
}

public void setImgDetails(String imgDetails) {
this.imgDetails = imgDetails;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getImgSource() {
return imgSource;
}

public void setImgSource(String imgSource) {
this.imgSource = imgSource;
}

public String getRedirectTo() {
return redirectTo;
}

public void setRedirectTo(String redirectTo) {
this.redirectTo = redirectTo;
}

public String getViewStatus() {
return viewStatus;
}

public void setViewStatus(String viewStatus) {
this.viewStatus = viewStatus;
}

    @Override
    public String toString() {
        return "Result{" +
                "entryDatetime='" + entryDatetime + '\'' +
                ", appName='" + appName + '\'' +
                ", imgName='" + imgName + '\'' +
                ", imgDetails='" + imgDetails + '\'' +
                ", id=" + id +
                ", imgSource='" + imgSource + '\'' +
                ", redirectTo='" + redirectTo + '\'' +
                ", viewStatus='" + viewStatus + '\'' +
                '}';
    }
}