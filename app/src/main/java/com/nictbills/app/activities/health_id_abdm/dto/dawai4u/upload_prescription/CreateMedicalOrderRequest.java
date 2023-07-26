package com.nictbills.app.activities.health_id_abdm.dto.dawai4u.upload_prescription;

public class CreateMedicalOrderRequest {

    private String name,age,gender,mobile_no,addr_name,addr_email,addr_pincode,addr_state,addr_city,address,addr_alt_mobile,file_extension,prescription,addr_id;
    private int store_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAddr_name() {
        return addr_name;
    }

    public void setAddr_name(String addr_name) {
        this.addr_name = addr_name;
    }

    public String getAddr_email() {
        return addr_email;
    }

    public void setAddr_email(String addr_email) {
        this.addr_email = addr_email;
    }

    public String getAddr_pincode() {
        return addr_pincode;
    }

    public void setAddr_pincode(String addr_pincode) {
        this.addr_pincode = addr_pincode;
    }

    public String getAddr_state() {
        return addr_state;
    }

    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddr_alt_mobile() {
        return addr_alt_mobile;
    }

    public void setAddr_alt_mobile(String addr_alt_mobile) {
        this.addr_alt_mobile = addr_alt_mobile;
    }

    public String getFile_extension() {
        return file_extension;
    }

    public void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }
}
