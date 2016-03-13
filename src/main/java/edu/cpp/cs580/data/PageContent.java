package edu.cpp.cs580.data;

import java.util.ArrayList;

public class PageContent {
	
	private String pageTitle;
	private String countryName;
	private String description;
	private String picUrl;
	private String tripAdvisorUrl;
	private String wikiTravelUrl;
	private String sectionTitle;
	private String eventsUrl;

	private ArrayList<ThingToDo> thingsToDo;
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Place> places;
	private ArrayList<Events> events;
	private String sourceUrl;
	
	public PageContent() {
		pageTitle = "Hello!";
		description = "Ready To Go A New Place?";
		picUrl = "./8.jpeg";
		tripAdvisorUrl = "#";
		wikiTravelUrl = "#";
	}
	
	
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

	public ArrayList<Events> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<Events>events) {
		this.events = events;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getWikiTravelUrl() {
		return wikiTravelUrl;
	}

	public void setWikiTravelUrl(String wikiTravelUrl) {
		this.wikiTravelUrl = wikiTravelUrl;
	}


	public String getTripAdvisorUrl() {
		return tripAdvisorUrl;
	}

	public void setTripAdvisorUrl(String tripAdvisorUrl) {
		this.tripAdvisorUrl = tripAdvisorUrl;
	}
	
	public String getEventsUrl() {
		return eventsUrl;
	}

	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl= eventsUrl;
	}
	
	public String getSectionTitle() {
		return sectionTitle;
	}


	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}


	public ArrayList<Place> getPlaces() {
		return places;
	}


	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
	

	
}
