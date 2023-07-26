package com.nictbills.app.activities.health_id_abdm.dto.house_hold;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("serv_address")
    @Expose
    private String servAddress;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("alt_contact")
    @Expose
    private String altContact;
    @SerializedName("loc_ref_1_contact")
    @Expose
    private String locRef1Contact;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("loc_ref_2_contact")
    @Expose
    private String locRef2Contact;
    @SerializedName("work_img")
    @Expose
    private String workImg;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("main_serv")
    @Expose
    private String mainServ;
    @SerializedName("serv_details")
    @Expose
    private String servDetails;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("loc_ref_1")
    @Expose
    private String locRef1;
    @SerializedName("loc_ref_2")
    @Expose
    private String locRef2;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("serv_title")
    @Expose
    private String servTitle;
    @SerializedName("sub_serv")
    @Expose
    private String subServ;
    @SerializedName("vendor_type")
    @Expose
    private String vendorType;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServAddress() {
        return servAddress;
    }

    public void setServAddress(String servAddress) {
        this.servAddress = servAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAltContact() {
        return altContact;
    }

    public void setAltContact(String altContact) {
        this.altContact = altContact;
    }

    public String getLocRef1Contact() {
        return locRef1Contact;
    }

    public void setLocRef1Contact(String locRef1Contact) {
        this.locRef1Contact = locRef1Contact;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLocRef2Contact() {
        return locRef2Contact;
    }

    public void setLocRef2Contact(String locRef2Contact) {
        this.locRef2Contact = locRef2Contact;
    }

    public String getWorkImg() {
        return workImg;
    }

    public void setWorkImg(String workImg) {
        this.workImg = workImg;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getMainServ() {
        return mainServ;
    }

    public void setMainServ(String mainServ) {
        this.mainServ = mainServ;
    }

    public String getServDetails() {
        return servDetails;
    }

    public void setServDetails(String servDetails) {
        this.servDetails = servDetails;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocRef1() {
        return locRef1;
    }

    public void setLocRef1(String locRef1) {
        this.locRef1 = locRef1;
    }

    public String getLocRef2() {
        return locRef2;
    }

    public void setLocRef2(String locRef2) {
        this.locRef2 = locRef2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getServTitle() {
        return servTitle;
    }

    public void setServTitle(String servTitle) {
        this.servTitle = servTitle;
    }

    public String getSubServ() {
        return subServ;
    }

    public void setSubServ(String subServ) {
        this.subServ = subServ;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

}
