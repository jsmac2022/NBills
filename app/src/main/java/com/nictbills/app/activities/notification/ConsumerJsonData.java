package com.nictbills.app.activities.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ConsumerJsonData {

@SerializedName("foundflag")
@Expose
private Boolean foundflag;
@SerializedName("loccd")
@Expose
private String loccd;
@SerializedName("grno")
@Expose
private String grno;
@SerializedName("rdno")
@Expose
private String rdno;
@SerializedName("consno")
@Expose
private String consno;
@SerializedName("serviceno")
@Expose
private String serviceno;
@SerializedName("ivrs")
@Expose
private String ivrs;
@SerializedName("cashduedate")
@Expose
private String cashduedate;
@SerializedName("chequeduedate")
@Expose
private String chequeduedate;
@SerializedName("netbilltopay")
@Expose
private String netbilltopay;
@SerializedName("surcharge")
@Expose
private String surcharge;
@SerializedName("amtaftersurcharge")
@Expose
private String amtaftersurcharge;
@SerializedName("consname")
@Expose
private String consname;
@SerializedName("address")
@Expose
private String address;
@SerializedName("mobileno")
@Expose
private String mobileno;
@SerializedName("constype")
@Expose
private String constype;
@SerializedName("billmonth")
@Expose
private String billmonth;
@SerializedName("amtwithoutsurcharge")
@Expose
private String amtwithoutsurcharge;
@SerializedName("raocode")
@Expose
private String raocode;
@SerializedName("billdate")
@Expose
private String billdate;
@SerializedName("msg")
@Expose
private String msg;
@SerializedName("paytrnid")
@Expose
private String paytrnid;
@SerializedName("rapdrporderid")
@Expose
private String rapdrporderid;
@SerializedName("arrear")
@Expose
private Integer arrear;
@SerializedName("ngbflag")
@Expose
private Boolean ngbflag;
@SerializedName("ngbmsg")
@Expose
private String ngbmsg;

public Boolean getFoundflag() {
return foundflag;
}

public void setFoundflag(Boolean foundflag) {
this.foundflag = foundflag;
}

public String getLoccd() {
return loccd;
}

public void setLoccd(String loccd) {
this.loccd = loccd;
}

public String getGrno() {
return grno;
}

public void setGrno(String grno) {
this.grno = grno;
}

public String getRdno() {
return rdno;
}

public void setRdno(String rdno) {
this.rdno = rdno;
}

public String getConsno() {
return consno;
}

public void setConsno(String consno) {
this.consno = consno;
}

public String getServiceno() {
return serviceno;
}

public void setServiceno(String serviceno) {
this.serviceno = serviceno;
}

public String getIvrs() {
return ivrs;
}

public void setIvrs(String ivrs) {
this.ivrs = ivrs;
}

public String getCashduedate() {
return cashduedate;
}

public void setCashduedate(String cashduedate) {
this.cashduedate = cashduedate;
}

public String getChequeduedate() {
return chequeduedate;
}

public void setChequeduedate(String chequeduedate) {
this.chequeduedate = chequeduedate;
}

public String getNetbilltopay() {
return netbilltopay;
}

public void setNetbilltopay(String netbilltopay) {
this.netbilltopay = netbilltopay;
}

public String getSurcharge() {
return surcharge;
}

public void setSurcharge(String surcharge) {
this.surcharge = surcharge;
}

public String getAmtaftersurcharge() {
return amtaftersurcharge;
}

public void setAmtaftersurcharge(String amtaftersurcharge) {
this.amtaftersurcharge = amtaftersurcharge;
}

public String getConsname() {
return consname;
}

public void setConsname(String consname) {
this.consname = consname;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getMobileno() {
return mobileno;
}

public void setMobileno(String mobileno) {
this.mobileno = mobileno;
}

public String getConstype() {
return constype;
}

public void setConstype(String constype) {
this.constype = constype;
}

public String getBillmonth() {
return billmonth;
}

public void setBillmonth(String billmonth) {
this.billmonth = billmonth;
}

public String getAmtwithoutsurcharge() {
return amtwithoutsurcharge;
}

public void setAmtwithoutsurcharge(String amtwithoutsurcharge) {
this.amtwithoutsurcharge = amtwithoutsurcharge;
}

public String getRaocode() {
return raocode;
}

public void setRaocode(String raocode) {
this.raocode = raocode;
}

public String getBilldate() {
return billdate;
}

public void setBilldate(String billdate) {
this.billdate = billdate;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public String getPaytrnid() {
return paytrnid;
}

public void setPaytrnid(String paytrnid) {
this.paytrnid = paytrnid;
}

public String getRapdrporderid() {
return rapdrporderid;
}

public void setRapdrporderid(String rapdrporderid) {
this.rapdrporderid = rapdrporderid;
}

public Integer getArrear() {
return arrear;
}

public void setArrear(Integer arrear) {
this.arrear = arrear;
}

public Boolean getNgbflag() {
return ngbflag;
}

public void setNgbflag(Boolean ngbflag) {
this.ngbflag = ngbflag;
}

public String getNgbmsg() {
return ngbmsg;
}

public void setNgbmsg(String ngbmsg) {
this.ngbmsg = ngbmsg;
}

}