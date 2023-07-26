
package com.nictbills.app.activities.tbo.bus.model.busblockreqmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passenger {

    @SerializedName("LeadPassenger")
    @Expose
    private Boolean leadPassenger;
    @SerializedName("PassengerId")
    @Expose
    private Integer passengerId;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Address")
    @Expose
    private Object address;
    @SerializedName("Age")
    @Expose
    private Integer age;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("Gender")
    @Expose
    private Integer gender;
    @SerializedName("IdNumber")
    @Expose
    private Object idNumber;
    @SerializedName("IdType")
    @Expose
    private Object idType;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Phoneno")
    @Expose
    private String phoneno;
    @SerializedName("Seat")
    @Expose
    private Seat seat;

    public Boolean getLeadPassenger() {
        return leadPassenger;
    }

    public void setLeadPassenger(Boolean leadPassenger) {
        this.leadPassenger = leadPassenger;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Object getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Object idNumber) {
        this.idNumber = idNumber;
    }

    public Object getIdType() {
        return idType;
    }

    public void setIdType(Object idType) {
        this.idType = idType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
