package com.nictbills.app.activities.household.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HomeData {



	@SerializedName("Main_Data")
	private MainData mainData;

	@SerializedName("Catlist")
	private List<CatlistItem> catlist;

	@SerializedName("Subcat_section")
	private List<SubcatSectionItem> subcatSection;


	public void setMainData(MainData mainData){
		this.mainData = mainData;
	}

	public MainData getMainData(){
		return mainData;
	}


	public void setCatlist(List<CatlistItem> catlist){
		this.catlist = catlist;
	}

	public List<CatlistItem> getCatlist(){
		return catlist;
	}

	public void setSubcatSection(List<SubcatSectionItem> subcatSection){
		this.subcatSection = subcatSection;
	}

	public List<SubcatSectionItem> getSubcatSection(){
		return subcatSection;
	}
}