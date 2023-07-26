
package com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPassenger {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("blockId")
    @Expose
    private Integer blockId;
    @SerializedName("middleName")
    @Expose
    private Object middleName;
    @SerializedName("phoneno")
    @Expose
    private String phoneno;
    @SerializedName("paxType")
    @Expose
    private String paxType;
    @SerializedName("leadPassenger")
    @Expose
    private String leadPassenger;
    @SerializedName("passportNo")
    @Expose
    private String passportNo;
    @SerializedName("passportDate")
    @Expose
    private String passportDate;
    @SerializedName("passportExpDate")
    @Expose
    private String passportExpDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("panNo")
    @Expose
    private Object panNo;
    @SerializedName("mobile")
    @Expose
    private Object mobile;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("resultIndex")
    @Expose
    private Object resultIndex;
    @SerializedName("traceId")
    @Expose
    private Object traceId;
    @SerializedName("mobileNo")
    @Expose
    private Object mobileNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Object getMiddleName() {
        return middleName;
    }

    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPaxType() {
        return paxType;
    }

    public void setPaxType(String paxType) {
        this.paxType = paxType;
    }

    public String getLeadPassenger() {
        return leadPassenger;
    }

    public void setLeadPassenger(String leadPassenger) {
        this.leadPassenger = leadPassenger;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(String passportDate) {
        this.passportDate = passportDate;
    }

    public String getPassportExpDate() {
        return passportExpDate;
    }

    public void setPassportExpDate(String passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getPanNo() {
        return panNo;
    }

    public void setPanNo(Object panNo) {
        this.panNo = panNo;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Object getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(Object resultIndex) {
        this.resultIndex = resultIndex;
    }

    public Object getTraceId() {
        return traceId;
    }

    public void setTraceId(Object traceId) {
        this.traceId = traceId;
    }

    public Object getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Object mobileNo) {
        this.mobileNo = mobileNo;
    }

}
