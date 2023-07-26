package com.nictbills.app.activities.tbo.flight.model.auth_token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MemberId")
    @Expose
    private Integer memberId;
    @SerializedName("AgencyId")
    @Expose
    private Integer agencyId;
    @SerializedName("LoginName")
    @Expose
    private String loginName;
    @SerializedName("LoginDetails")
    @Expose
    private String loginDetails;
    @SerializedName("isPrimaryAgent")
    @Expose
    private Boolean isPrimaryAgent;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(String loginDetails) {
        this.loginDetails = loginDetails;
    }

    public Boolean getIsPrimaryAgent() {
        return isPrimaryAgent;
    }

    public void setIsPrimaryAgent(Boolean isPrimaryAgent) {
        this.isPrimaryAgent = isPrimaryAgent;
    }

}
