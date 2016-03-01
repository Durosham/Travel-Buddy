package edu.cpp.cs580.data.provider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.cpp.cs580.data.PageContent;
import edu.cpp.cs580.data.Restaurant;
import edu.cpp.cs580.data.ThingToDo;

public class ParseFromTripWeb {
	
	public static String getTitle(Document doc){
		return doc.title();
	}
	
	public static String getDescription(Document doc){
        Element content = doc.select("div.ermb_text").first();
        content = content.select("div.content").first();
        return content.html();
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

	
	public static PageContent PageParsing(String url){
		PageContent page = new PageContent();
		
        try {
            Document doc = Jsoup.connect(url).get();
            
            page.setSourceUrl(url);
            
            String pageTitle = getTitle(doc);
            page.setPageTitle(pageTitle);
            
            String description = getDescription(doc);
            page.setDescription(description);

            String picUrl = getPicUrl(doc);
            page.setPicUrl(picUrl);

            
            /*get things to do*/
            Elements thingsTodo = doc.select("div[class=col attractions]");
            thingsTodo = thingsTodo.select("div[class=name]");
            ArrayList<ThingToDo> ThingsToDoList = new ArrayList<ThingToDo>();
            
            for (Element element : thingsTodo) {
            	String thingUrl = element.select("a[class=title]").first().attr("abs:href");
            	String thingTodo = element.text();
            	ThingToDo tmp = new ThingToDo();
            	tmp.setThingToDo(thingTodo);
            	tmp.setThingToDoUrl(thingUrl);
            	ThingsToDoList.add(tmp);

            }
            
            //ArrayList<ThingsToDo> = getThingsTodo(doc);
            
            page.setThingsToDo(ThingsToDoList);
            
            /*get restaurant*/
            Elements restaurants = doc.select("div[class=col restaurants]");
            restaurants = restaurants.select("div[class=name]");
            ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
            
            for (Element element : restaurants) {
            	String restaurantUrl = element.select("a[class=title]").first().attr("abs:href");
            	Restaurant tmp = new Restaurant();
            	String restaurantTitle = element.text();
            	tmp.setRestaurantTitle(restaurantTitle);
            	tmp.setRestaurantUrl(restaurantUrl);
            	restaurantList.add(tmp);
            }
            
            page.setRestaurants(restaurantList);
          
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
		return page;
	}
}
