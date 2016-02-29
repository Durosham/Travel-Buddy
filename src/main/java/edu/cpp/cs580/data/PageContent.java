package edu.cpp.cs580.data;

import java.util.ArrayList;

public class PageContent {
	
	private String pageTitle;
	private String description;
	private ArrayList<ThingToDo> thingsToDo;
	private ArrayList<Restaurant> restaurants;
	private String picUrl;
	private String sourceUrl;
	
	
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<ThingToDo> getThingsToDo() {
		return thingsToDo;
	}
	public void setThingsToDo(ArrayList<ThingToDo> thingsToDo) {
		this.thingsToDo = thingsToDo;
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(ArrayList<Restaurant> restaurants) {
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
