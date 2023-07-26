
package com.nictbills.app.activities.tbo.bus.model.authresmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusAuthResponseModel implements Serializable
{

    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("Member")
    @Expose
    private Member member;
    private final static long serialVersionUID = 4941598875662336534L;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BusAuthResponseModel withStatus(int status) {
        this.status = status;
        return this;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public BusAuthResponseModel withTokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public BusAuthResponseModel withError(Error error) {
        this.error = error;
        return this;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public BusAuthResponseModel withMember(Member member) {
        this.member = member;
        return this;
    }

}
