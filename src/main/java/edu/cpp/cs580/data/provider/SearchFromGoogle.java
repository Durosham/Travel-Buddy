package edu.cpp.cs580.data.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchFromGoogle {
	
	public static String get(String searchInput, String type){
		String returnUrl = "";
		String queryUrl = "https://www.google.com/search?q=" + searchInput + "+" + type;
        try {
        	System.out.print(queryUrl);
            Document doc = Jsoup.connect(queryUrl)
            				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
            				.get();
            Elements links = doc.select("a[href]");
            String linkTmp;
            ArrayList<String> linksHandle = new ArrayList <String>();
            
            if(type.equals("tripAdvisor")){
                for (Element link : links) {
                	linkTmp = link.toString();
                	if(linkTmp.contains("Tourism") && linkTmp.contains("Vacations")){
                		linksHandle.add(linkTmp);
                	}
                }
                
                String pattern = "<a href=\"(.*?)\"";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(linksHandle.get(0));
                while(m.find()){
                	returnUrl = m.group(1);
                }
                
            }

            else if(type.equals("wikiTravel")){
                for (Element link : links) {
                	linkTmp = link.toString();
                	if(linkTmp.contains("wikitravel.org/en/")){
                		linksHandle.add(linkTmp);
                	}
                }
                
                String pattern = "<a href=\"(.*?)\"";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(linksHandle.get(0));
                while(m.find()){
                	returnUrl = m.group(1);
                }
            }
            
            else if(type.equals("tripAdvisor+things+to+do")){
            	for(Element link : links){
            		link = link.select("a").first();
                	String absHref = link.attr("abs:href");
                	if(absHref.contains("http://www.tripadvisor.com/Attractions")){
                		returnUrl = absHref;
                		break;
                	}
            	}                
            }
            
            else if(type.equals("tripAdvisor+restaurants")){
            	for(Element link : links){
            		link = link.select("a").first();
                	String absHref = link.attr("abs:href");
                	if(absHref.contains("http://www.tripadvisor.com/Restaurants")){
                		returnUrl = absHref;
                		break;
                	}
            	}                
            }

        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return returnUrl;
	}

}
