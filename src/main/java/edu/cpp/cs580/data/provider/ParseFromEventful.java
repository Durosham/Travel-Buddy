package edu.cpp.cs580.data.provider;


import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.cpp.cs580.data.Events;
import edu.cpp.cs580.data.PageContent;
import edu.cpp.cs580.data.ThingToDo;

public class ParseFromEventful {

	public static String getTitle(Document doc){
		return doc.title();
	}
	
	public static PageContent PageParsing(String url){
		PageContent page = new PageContent();
		
        try {
            Document doc = Jsoup.connect(url).get();
            
  
            /*get things to do*/
            Elements events = doc.select("div[class=body]");
            events = events.select("div[class=event card]");
            ArrayList<Events> EventsList = new ArrayList<Events>();
            
            for (Element element : events) {
            	String sourceUrl = element.select("a[class=title]").first().attr("abs:href");
            	String activity = element.text();
            	String eventName = element.select("a[data-lid=hottest_tickets]").first().attr("abs:href");
            	Events tmp = new Events();
            	tmp.setEventName(eventName);
            	tmp.setUrl(sourceUrl);
            	EventsList.add(tmp);

            }
            
            //ArrayList<ThingsToDo> = getThingsTodo(doc);
            
            page.setEvents(EventsList);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
		return page;
	}
}
