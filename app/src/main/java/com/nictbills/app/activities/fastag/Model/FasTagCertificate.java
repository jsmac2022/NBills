package com.nictbills.app.activities.fastag.Model;

import com.google.gson.annotations.SerializedName;

public class FasTagCertificate {
    @SerializedName("ftct_id")
    private String ftct_id;

    @SerializedName("certificate_name")
    private String certificate_name;

    @SerializedName("sector")
    private String sector;

    public String getFtct_id() {
        return ftct_id;
    }

    public void setFtct_id(String ftct_id) {
        this.ftct_id = ftct_id;
    }

    public String getCertificate_name() {
        return certificate_name;
    }

    public void setCertificate_name(String certificate_name) {
        this.certificate_name = certificate_name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
