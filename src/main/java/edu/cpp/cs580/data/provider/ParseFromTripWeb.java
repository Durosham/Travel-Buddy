package edu.cpp.cs580.data.provider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import edu.cpp.cs580.data.PageContent;
import edu.cpp.cs580.data.Place;
import edu.cpp.cs580.data.Restaurant;
import edu.cpp.cs580.data.ThingToDo;

public class ParseFromTripWeb {

	public static String getTitle(Document wikiTravel){
		String title = wikiTravel.title();
		title = title.replaceAll("travel guide - Wikitravel", "");
		title = title.replaceAll("phrasebook - Wikitravel", "");
		return title;
	}
	
	public static String getCountry(Document tripAdvisor){
		String countryName = "";
		Elements geoInfo = tripAdvisor.select("div[class=breadCrumbContainer scopedSearch]");

		geoInfo = geoInfo.select("span[itemprop]");

		
		if(geoInfo.size() == 0){
			countryName = "";
		}
		else if(geoInfo.size() == 1){
			countryName = geoInfo.get(0).text().toString();

			 if( geoInfo.get(0).text().toString().equals("Asia") || 
				 geoInfo.get(0).text().toString().equals("Europe") || 
				 geoInfo.get(0).text().toString().equals("South Pacific") || 
				 geoInfo.get(0).text().toString().equals("South America") || 
				 geoInfo.get(0).text().toString().equals("Central America") || 
				 geoInfo.get(0).text().toString().equals("Africa"))
			 {
				 countryName = "";
			 }
			 else{
				 countryName = geoInfo.get(0).text().toString();
			 }	
		}
		else{
			 if( 
				 !geoInfo.get(0).text().toString().equals("Asia") && 
				 !geoInfo.get(0).text().toString().equals("Europe") && 
				 !geoInfo.get(0).text().toString().equals("South Pacific") && 
				 !geoInfo.get(0).text().toString().equals("South America") && 
				 !geoInfo.get(0).text().toString().equals("Central America") && 
				 !geoInfo.get(0).text().toString().equals("Africa"))
				 {
					 countryName = geoInfo.get(0).text().toString();
				 }
			 else{
				 countryName = geoInfo.get(1).text().toString();
			 }
			
		}
		return countryName;
	}
	
	public static String getDescription(Document doc){
		String description = "";
        Elements sections = doc.select("p");

        String tmp;
        for(int i=0; i<20; ++i){
        	if(!sections.get(i).toString().isEmpty()){
                tmp = Jsoup.clean(sections.get(i).toString(), Whitelist.simpleText());
                tmp = tmp.replaceAll("<\\/?[bi]>", "");
                if(!tmp.isEmpty() && tmp.length() >= 80){
                	description = tmp;
                	break;
                }	
        	}
        }
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

	
	public static ArrayList<Place> getPlaces(Document tripAdvisor){
		ArrayList<Place> places = new ArrayList<Place>();
		Elements placesToGo = tripAdvisor.select("div[class=popularCities]");
		Elements links = placesToGo.select("a");
		for(Element link : links){
			String url = link.attr("abs:href");
			String cityName = link.select("span.name").text().toString();
			Place tmp = new Place();
			tmp.setName(cityName);
			tmp.setUrl(url);
			places.add(tmp);
		}
		return places;
	}
	
	public static ArrayList<Restaurant> getRestaurants(Document tripAdvisor){
        Elements restaurants = tripAdvisor.select("div[class=col restaurants]");
        restaurants = restaurants.select("div[class=name]");
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        
        for (Element element : restaurants) {
        	String url = element.select("a[class=title]").first().attr("abs:href");
        	Restaurant tmp = new Restaurant();
        	String name = element.text();
        	tmp.setName(name);
        	tmp.setUrl(url);
        	restaurantList.add(tmp);
        }
		return restaurantList;
	}
	
	public static ArrayList<ThingToDo> getThingsToDo(Document tripAdvisor){
        Elements thingsTodo = tripAdvisor.select("div[class=col attractions]");
        thingsTodo = thingsTodo.select("div[class=name]");
        ArrayList<ThingToDo> ThingsToDoList = new ArrayList<ThingToDo>();
        
        for (Element element : thingsTodo) {
        	String url = element.select("a[class=title]").first().attr("abs:href");
        	String activity = element.text();
        	ThingToDo tmp = new ThingToDo();
        	tmp.setActivity(activity);
        	tmp.setUrl(url);
        	ThingsToDoList.add(tmp);
        }
        
        return ThingsToDoList;
	}
	
	
	public static PageContent PageParsing(String tripAdvisorUrl, String wikiTravelUrl){
		PageContent page = new PageContent();
        try {
            Document tripAdvisor = Jsoup.connect(tripAdvisorUrl).timeout(5000).get();
            Document wikiTravel = Jsoup.connect(wikiTravelUrl).timeout(5000).get();
            
            page.setTripAdvisorUrl(tripAdvisorUrl);
            page.setWikiTravelUrl(wikiTravelUrl);
            
            String pageTitle = getTitle(wikiTravel);
            page.setPageTitle(pageTitle);
            
            String countryName = getCountry(tripAdvisor);
            page.setCountryName(countryName);
            
            String description = getDescription(wikiTravel);
            page.setDescription(description);

            String picUrl = getPicUrl(tripAdvisor);
            page.setPicUrl(picUrl);

            
            /*get things to do*/
            ArrayList<ThingToDo> ThingsToDoList = new ArrayList<ThingToDo>();
            ThingsToDoList = getThingsToDo(tripAdvisor);
            page.setThingsToDo(ThingsToDoList);
                        
            /*get restaurant*/
            ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
            restaurantList = getRestaurants(tripAdvisor);
            page.setRestaurants(restaurantList);
          
            if(!ThingsToDoList.isEmpty() && !restaurantList.isEmpty()){
            	page.setSectionTitle("Must do and eat in " + page.getPageTitle());
            }
            else{
            	ArrayList<Place> places = new ArrayList<Place>();
            	places = getPlaces(tripAdvisor);
            	page.setSectionTitle("Nice places you must go in " + page.getPageTitle() + "!");
            	page.setPlaces(places);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
		return page;
	}
}
