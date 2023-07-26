package com.nictbills.app.activities.tbo.flight.model.AddFlightPassengermodel;

public class FlightPassengerMemberAdd {
    String fullname;
    String Lastname;
    String dob;
    String mobile;
    String email;
    String gender;
    String Passengernumberall;

    public String getPassengernumberall() {
        return Passengernumberall;
    }

    public void setPassengernumberall(String passengernumberall) {
        Passengernumberall = passengernumberall;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
