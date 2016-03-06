package edu.cpp.cs580.data;

public class ThingToDo {
	private String activity;
	private String url;
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public ThingToDo(){
		activity = "Let's Go!";
		url = "#";
	}
	
}
