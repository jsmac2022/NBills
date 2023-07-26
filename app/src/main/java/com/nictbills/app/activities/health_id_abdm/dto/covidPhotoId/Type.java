package com.nictbills.app.activities.health_id_abdm.dto.covidPhotoId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("doc_type")
    @Expose
    private Object docType;
    @SerializedName("flag")
    @Expose
    private Integer flag;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getDocType() {
        return docType;
    }

    public void setDocType(Object docType) {
        this.docType = docType;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


}
