
package com.nictbills.app.activities.tbo.bus.model.authresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member implements Serializable
{

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
    private int memberId;
    @SerializedName("AgencyId")
    @Expose
    private int agencyId;
    @SerializedName("LoginName")
    @Expose
    private String loginName;
    @SerializedName("LoginDetails")
    @Expose
    private String loginDetails;
    @SerializedName("isPrimaryAgent")
    @Expose
    private boolean isPrimaryAgent;
    private final static long serialVersionUID = -219759803597130475L;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Member withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Member withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Member withEmail(String email) {
        this.email = email;
        return this;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Member withMemberId(int memberId) {
        this.memberId = memberId;
        return this;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public Member withAgencyId(int agencyId) {
        this.agencyId = agencyId;
        return this;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Member withLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    public String getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(String loginDetails) {
        this.loginDetails = loginDetails;
    }

    public Member withLoginDetails(String loginDetails) {
        this.loginDetails = loginDetails;
        return this;
    }

    public boolean isIsPrimaryAgent() {
        return isPrimaryAgent;
    }

    public void setIsPrimaryAgent(boolean isPrimaryAgent) {
        this.isPrimaryAgent = isPrimaryAgent;
    }

    public Member withIsPrimaryAgent(boolean isPrimaryAgent) {
        this.isPrimaryAgent = isPrimaryAgent;
        return this;
    }

}
