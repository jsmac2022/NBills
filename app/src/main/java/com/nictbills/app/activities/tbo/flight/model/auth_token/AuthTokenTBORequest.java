package com.nictbills.app.activities.tbo.flight.model.auth_token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthTokenTBORequest {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("Member")
    @Expose
    private Member member;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
