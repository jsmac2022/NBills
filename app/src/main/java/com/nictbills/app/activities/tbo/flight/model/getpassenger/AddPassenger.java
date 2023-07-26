
package com.nictbills.app.activities.tbo.flight.model.getpassenger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPassenger {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("passenger_age")
    @Expose
    private String passengerAge;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("leadPassenger")
    @Expose
    private Object leadPassenger;
    @SerializedName("mobile_no")
    @Expose
    private Object mobileNo;
    @SerializedName("resultIndex")
    @Expose
    private Object resultIndex;
    @SerializedName("flight_id")
    @Expose
    private Integer flightId;
    @SerializedName("traceId")
    @Expose
    private Object traceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(String passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Object getLeadPassenger() {
        return leadPassenger;
    }

    public void setLeadPassenger(Object leadPassenger) {
        this.leadPassenger = leadPassenger;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Object resultIndex) {
        this.resultIndex = resultIndex;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Object getTraceId() {
        return traceId;
    }

    public void setTraceId(Object traceId) {
        this.traceId = traceId;
    }

}
