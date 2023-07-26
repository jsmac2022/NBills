package com.nictbills.app.activities.household.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home{

	@SerializedName("RESPONSE")
	private String response;

	@SerializedName("RESULT")
	private List<CatlistItem> result;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<CatlistItem> getResult() {
		return result;
	}

	public void setResult(List<CatlistItem> result) {
		this.result = result;
	}
}