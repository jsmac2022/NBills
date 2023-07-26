
package com.nictbills.app.activities.tbo.hotel.model.authresponse;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelAouthModelResponse implements Serializable
{

    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("TokenId")
    @Expose
    private String tokenId;
    @SerializedName("Error")
    @Expose
    private java.lang.Error error;
    @SerializedName("Member")
    @Expose
    private Member member;
    private final static long serialVersionUID = 7533156963283946134L;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HotelAouthModelResponse withStatus(int status) {
        this.status = status;
        return this;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public HotelAouthModelResponse withTokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

    public HotelAouthModelResponse withError(java.lang.Error error) {
        this.error = error;
        return this;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public HotelAouthModelResponse withMember(Member member) {
        this.member = member;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HotelAouthModelResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(this.status);
        sb.append(',');
        sb.append("tokenId");
        sb.append('=');
        sb.append(((this.tokenId == null)?"<null>":this.tokenId));
        sb.append(',');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null)?"<null>":this.error));
        sb.append(',');
        sb.append("member");
        sb.append('=');
        sb.append(((this.member == null)?"<null>":this.member));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
