package com.nictbills.app.activities.household.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Chiald{

	@SerializedName("RESPONSE")
	private String reponse;


	@SerializedName("RESULT")
	private List<ChildcatItem> result;

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public List<ChildcatItem> getResult() {
		return result;
	}

	public void setResult(List<ChildcatItem> result) {
		this.result = result;
	}
}