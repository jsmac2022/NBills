package com.nictbills.app.activities.household.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubcatDatum {

	@SerializedName("ssc")
	private String ssc;
	@SerializedName("logo")
	private String logo;
	@SerializedName("sub_serv")
	private String sub_serv;


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

	public String getSub_serv() {
		return sub_serv;
	}

	public void setSub_serv(String sub_serv) {
		this.sub_serv = sub_serv;
	}
}