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
	
	public static PageContent PageParsing(String url){
		PageContent page = new PageContent();
		
        try {
            Document doc = Jsoup.connect(url).get();
            
            /*get title and description*/
            Element content = doc.select("div.ermb_text").first();
            content = content.select("div.content").first();
            
            page.setSourceUrl(url);
            page.setPageTitle(doc.title());
            page.setDescription(content.html());

            /*get the main picture*/
            Elements metaPic = doc.select("meta[property=og:image]");
            for (Element element : metaPic) {
                String s = element.attr("content");
                if (s != null){
                	page.setPicUrl(s);
                }
            }
            
            System.out.println();
            
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
            	
            	System.out.println(thingUrl);
            	System.out.println(thingTodo);
            }
            
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
            	
            	System.out.println(restaurantUrl);
            	System.out.println(restaurantTitle);
            }
            
            page.setRestaurants(restaurantList);
  
            System.out.println(page.getSourceUrl());
            System.out.println(page.getPageTitle());
            System.out.println(page.getDescription());
            System.out.println(page.getPicUrl());
          
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
		return page;
	}
}
