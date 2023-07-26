
package com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passenger {

    @SerializedName("Seat")
    @Expose
    private Seat seat;
    @SerializedName("PassengerId")
    @Expose
    private Integer passengerId;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("IdNumber")
    @Expose
    private String idNumber;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("LeadPassenger")
    @Expose
    private Boolean leadPassenger;
    @SerializedName("Gender")
    @Expose
    private Integer gender;
    @SerializedName("IdType")
    @Expose
    private String idType;
    @SerializedName("Phoneno")
    @Expose
    private String phoneno;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Age")
    @Expose
    private Integer age;

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getLeadPassenger() {
        return leadPassenger;
    }

    public void setLeadPassenger(Boolean leadPassenger) {
        this.leadPassenger = leadPassenger;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
