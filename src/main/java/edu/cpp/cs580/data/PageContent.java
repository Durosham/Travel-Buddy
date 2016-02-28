package edu.cpp.cs580.data;

import java.util.ArrayList;

public class PageContent {
	
	private String pageTitle;
	private ArrayList<String> thingsToDo;
	private ArrayList<String> restaurants;
	private String picUrl;
	private String sourceUrl;
	
	
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	public ArrayList<String> getThingsToDo() {
		return thingsToDo;
	}
	public void setThingsToDo(ArrayList<String> thingsToDo) {
		this.thingsToDo = thingsToDo;
	}
	public ArrayList<String> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(ArrayList<String> restaurants) {
		this.restaurants = restaurants;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	
}
