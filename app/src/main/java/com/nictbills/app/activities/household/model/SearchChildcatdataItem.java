package com.nictbills.app.activities.household.model;

import com.google.gson.annotations.SerializedName;

public class SearchChildcatdataItem{

	@SerializedName("ssc")
	private String ssc;

	@SerializedName("logo")
	private String logo;

	@SerializedName("childserv_code")
	private String childserv_code;

	@SerializedName("msc")
	private String msc;

	@SerializedName("childserv")
	private String childserv;

	public String getSsc() {
		return ssc;
	}

	public void setSsc(String ssc) {
		this.ssc = ssc;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getChildserv_code() {
		return childserv_code;
	}

	public void setChildserv_code(String childserv_code) {
		this.childserv_code = childserv_code;
	}

	public String getMsc() {
		return msc;
	}

	public void setMsc(String msc) {
		this.msc = msc;
	}

	public String getChildserv() {
		return childserv;
	}

	public void setChildserv(String childserv) {
		this.childserv = childserv;
	}
}