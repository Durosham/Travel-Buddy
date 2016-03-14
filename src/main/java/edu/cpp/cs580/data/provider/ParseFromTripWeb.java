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
import edu.cpp.cs580.data.Events;

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
	
	public static ArrayList<Restaurant> getRestaurants(Document tripAdvisorRestaurant){
		
		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		Elements restaurants = tripAdvisorRestaurant.select("div[class=shortSellDetails]");
		
		int restaurantCount = 0;
		for(Element element : restaurants){
			if(restaurantCount >= 10) break;
			element = element.select("a").first();
			String name = element.text();
			String url = element.attr("href");
			Restaurant tmp = new Restaurant();
        	tmp.setName(name);
        	tmp.setUrl("http://www.tripadvisor.com"+url);
        	restaurantList.add(tmp);
        	restaurantCount ++;
		}
		return restaurantList;
	}
	
	public static ArrayList<ThingToDo> getThingsToDo(Document tripAdvisorThingsToDo){
//        Elements thingsTodo = tripAdvisorThingsToDo.select("div[class=col attractions]");
//        thingsTodo = thingsTodo.select("div[class=name]");
        ArrayList<ThingToDo> ThingsToDoList = new ArrayList<ThingToDo>();
        Elements thingsToDo = tripAdvisorThingsToDo.select("div[class=property_title]");
     //   System.out.println(thingsToDo);
        
		int count = 0;
		for(Element element : thingsToDo){
			if(count >= 10) break;
			element = element.select("a").first();
			String activity = element.text();
			String url = element.attr("href");
			ThingToDo tmp = new ThingToDo();
        	tmp.setActivity(activity);
        	tmp.setUrl("http://www.tripadvisor.com"+url);
        	ThingsToDoList.add(tmp);
        	count ++;
		}

        return ThingsToDoList;
	}
	
	public static ArrayList<Events> getEvents(Document eventfulEvents){

      ArrayList<Events> Events = new ArrayList<Events>();
      Elements events = eventfulEvents.select("li[class=clearfix]");
      Elements eventsnames =eventfulEvents.select("span[itemprop=name]");
      Elements eventsdates =eventfulEvents.select("strong[itemprop=startDate]");
  //    Elements eventspics =eventfulEvents.select("");

      Events tmp = new Events();
      System.out.println(events);
      System.out.println(eventsnames);
      	int count = 1;
      	// getting the event url, pic url
     		for(Element element : events){
					if(count >= 6) break;
					element = element.select("a").first();
					String url = element.attr("href");
					element = element.select("img").first();
					String picUrl = element.attr("src");
				//	String eventName = element.attr("h4 > span");
					//String eventName = element.attr("h4");
				//	element2 = element2.select("h4").first();
					//String = element2.text();
					//String date = element.text();
					System.out.println("Test: URL");
				//	System.out.println(eventName);
					System.out.println(url);
					//System.out.println(date);
					System.out.println("Test: PIC URL");
					System.out.println(picUrl);

				//	Events tmp = new Events();
					//tmp.setEventName(eventName);
					tmp.setUrl(url);
			      //	tmp.setDate(date);
			      	tmp.setpicUrl(picUrl);
			      	
			      	count ++;
		}
     			count=1;
     			//getting the event name
     			for (Element element2 : eventsnames)
     			{	
     				//count=1;
					if(count >= 10) break;
				//	String eventName = element2.ownText();
					System.out.println("count="+ count);
					if(count % 2 != 0)
					{
					String eventName = element2.ownText();
					tmp.setEventName(eventName);
					System.out.println("Test : Event Name");
					System.out.println(eventName);
					}
					count++;

     			}
     			count=1;
     			//getting the event date
     			for (Element element3 : eventsdates)
     			{	
     				
					if(count >= 6) break;
					String date = element3.ownText();
					tmp.setDate(date);
					System.out.println("Test : Date");
					System.out.println(date);
					count++;
     			}
     			Events.add(tmp);	
     		 return Events;
     		}
     
	
	public static PageContent PageParsing(String tripAdvisorUrl, String wikiTravelUrl, String thingsToDoUrl, String restaurantsUrl, String eventsUrl){
		PageContent page = new PageContent();
        try {
            Document tripAdvisor = Jsoup.connect(tripAdvisorUrl).timeout(3000).get();
            Document wikiTravel = Jsoup.connect(wikiTravelUrl).timeout(3000).get();
            Document tripAdvisorThingsToDo = Jsoup.connect(thingsToDoUrl).timeout(3000).get();
            Document tripAdvisorRestaurants = Jsoup.connect(restaurantsUrl).timeout(3000).get();
            Document eventfulEvents = Jsoup.connect(eventsUrl).timeout(3000).get();

            page.setTripAdvisorUrl(tripAdvisorUrl);
            page.setWikiTravelUrl(wikiTravelUrl);
            page.setEventsUrl(eventsUrl);
            
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
            ThingsToDoList = getThingsToDo(tripAdvisorThingsToDo);
            page.setThingsToDo(ThingsToDoList);
                        
            /*get restaurant*/
            ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
            restaurantList = getRestaurants(tripAdvisorRestaurants);
            page.setRestaurants(restaurantList);
            
            /*get events*/
            ArrayList<Events> events = new ArrayList<Events>();
            events = getEvents(eventfulEvents);
            page.setEvents(events);
            
//          
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
