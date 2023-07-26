package com.nictbills.app.activities.household.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Search{

	@SerializedName("RESPONSE")
	private String response;


	@SerializedName("RESULT")
	private List<SearchChildcatdataItem> result;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<SearchChildcatdataItem> getResult() {
		return result;
	}

	public void setResult(List<SearchChildcatdataItem> result) {
		this.result = result;
	}
}