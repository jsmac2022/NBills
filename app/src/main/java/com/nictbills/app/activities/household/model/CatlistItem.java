package com.nictbills.app.activities.household.model;

import com.google.gson.annotations.SerializedName;

public class CatlistItem{

	@SerializedName("logo")
	private String logo;

	@SerializedName("main_serv_name")
	private String main_serv_name;

	@SerializedName("msc")
	private String msc;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMain_serv_name() {
		return main_serv_name;
	}

	public void setMain_serv_name(String main_serv_name) {
		this.main_serv_name = main_serv_name;
	}

	public String getMsc() {
		return msc;
	}

	public void setMsc(String msc) {
		this.msc = msc;
	}
}