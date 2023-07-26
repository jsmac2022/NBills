package com.nictbills.app.activities.health_id_abdm.dto.covid_registration;

public class CovidRegistrationRequest {

    private String name,birth_year,photo_id_number,comorbidity_ind,consent_version;
    int gender_id,photo_id_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getPhoto_id_number() {
        return photo_id_number;
    }

    public void setPhoto_id_number(String photo_id_number) {
        this.photo_id_number = photo_id_number;
    }

    public String getComorbidity_ind() {
        return comorbidity_ind;
    }

    public void setComorbidity_ind(String comorbidity_ind) {
        this.comorbidity_ind = comorbidity_ind;
    }

    public String getConsent_version() {
        return consent_version;
    }

    public void setConsent_version(String consent_version) {
        this.consent_version = consent_version;
    }

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public int getPhoto_id_type() {
        return photo_id_type;
    }

    public void setPhoto_id_type(int photo_id_type) {
        this.photo_id_type = photo_id_type;
    }
}
