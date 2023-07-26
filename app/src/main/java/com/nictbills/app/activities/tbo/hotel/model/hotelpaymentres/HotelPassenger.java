
package com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelPassenger {

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Phoneno")
    @Expose
    private String phoneno;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("PassportNo")
    @Expose
    private String passportNo;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PaxType")
    @Expose
    private Integer paxType;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("LeadPassenger")
    @Expose
    private Boolean leadPassenger;
    @SerializedName("MiddleName")
    @Expose
    private String middleName;
    @SerializedName("PAN")
    @Expose
    private String pan;
    @SerializedName("Age")
    @Expose
    private Integer age;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPaxType() {
        return paxType;
    }

    public void setPaxType(Integer paxType) {
        this.paxType = paxType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getLeadPassenger() {
        return leadPassenger;
    }

    public void setLeadPassenger(Boolean leadPassenger) {
        this.leadPassenger = leadPassenger;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
