package com.nictbills.app.activities.household.model;

import com.google.gson.annotations.SerializedName;

public class ChildcatItem{

	@SerializedName("logo")
	private String logo;

	@SerializedName("child_cat_code")
	private String child_cat_code;

	@SerializedName("child_cat")
	private String child_cat;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getChild_cat_code() {
		return child_cat_code;
	}

	public void setChild_cat_code(String child_cat_code) {
		this.child_cat_code = child_cat_code;
	}

	public String getChild_cat() {
		return child_cat;
	}

	public void setChild_cat(String child_cat) {
		this.child_cat = child_cat;
	}
}