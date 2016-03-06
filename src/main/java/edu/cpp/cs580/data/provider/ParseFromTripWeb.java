package edu.cpp.cs580.data.provider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import edu.cpp.cs580.data.PageContent;
import edu.cpp.cs580.data.Restaurant;
import edu.cpp.cs580.data.ThingToDo;

public class ParseFromTripWeb {

	public static String getTitle(Document doc){
		String title = doc.title();
		title = title.replaceAll("travel guide - Wikitravel", "");
		return title;
	}
	
	public static String getDescription(Document doc){
		String description = "";
        Elements sections = doc.select("p");
        for(Element section : sections){

        }
        description = Jsoup.clean(description, Whitelist.simpleText());
        description = description.replaceAll("<\\/?[bi]>", ""); 
        
        return description;

	}
	
	public static String getPicUrl(Document doc){
		String picUrl = "";
        Elements metaPic = doc.select("meta[property=og:image]");
        for (Element element : metaPic) {
            String s = element.attr("content");
            if (s != null){
            	picUrl = s;
            }
        }
        return picUrl;
	}

	
	public static PageContent PageParsing(String tripAdvisorUrl, String wikiTravelUrl){
		PageContent page = new PageContent();
        try {
            Document tripAdvisor = Jsoup.connect(tripAdvisorUrl).get();
            Document wikiTravel = Jsoup.connect(wikiTravelUrl).get();
            
            page.setTripAdvisorUrl(tripAdvisorUrl);
            page.setWikiTravelUrl(wikiTravelUrl);
            
//            System.out.println(page.getTripAdvisorUrl());
//            System.out.println(page.getWikiTravelUrl());
            
            String pageTitle = getTitle(wikiTravel);
            page.setPageTitle(pageTitle);
            
            String description = getDescription(wikiTravel);
            page.setDescription(description);

            String picUrl = getPicUrl(tripAdvisor);
            page.setPicUrl(picUrl);

            
            /*get things to do*/
            Elements thingsTodo = tripAdvisor.select("div[class=col attractions]");
            thingsTodo = thingsTodo.select("div[class=name]");
            ArrayList<ThingToDo> ThingsToDoList = new ArrayList<ThingToDo>();
            
            for (Element element : thingsTodo) {
            	String sourceUrl = element.select("a[class=title]").first().attr("abs:href");
            	String activity = element.text();
            	ThingToDo tmp = new ThingToDo();
            	tmp.setActivity(activity);
            	tmp.setUrl(sourceUrl);
            	ThingsToDoList.add(tmp);
            }
            

            page.setThingsToDo(ThingsToDoList);
            
            
            
            /*get restaurant*/
            Elements restaurants = tripAdvisor.select("div[class=col restaurants]");
            restaurants = restaurants.select("div[class=name]");
            ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
            
            for (Element element : restaurants) {
            	String sourceUrl = element.select("a[class=title]").first().attr("abs:href");
            	Restaurant tmp = new Restaurant();
            	String name = element.text();
            	tmp.setName(name);
            	tmp.setUrl(sourceUrl);
            	restaurantList.add(tmp);
            }
            page.setRestaurants(restaurantList);
          
            if(!ThingsToDoList.isEmpty() && !restaurantList.isEmpty()){
            	page.setSectionTitle("Must do and eat in " + page.getPageTitle());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
		return page;
	}
}
