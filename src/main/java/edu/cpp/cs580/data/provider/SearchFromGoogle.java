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
	
	public static String get(String searchInput){
		String returnUrl = "";
		String queryUrl = "https://www.google.com/search?q=" + searchInput + "+tripadvisor";
        try {
        	System.out.print(queryUrl);
            Document doc = Jsoup.connect(queryUrl)
            				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
            				.get();
            Elements links = doc.select("a[href]");
            String linkTmp;
            ArrayList linksHandle = new ArrayList <String>();
            
            for (Element link : links) {
            	linkTmp = link.toString();
            	if(linkTmp.contains("Tourism") && linkTmp.contains("Vacations")){
            		linksHandle.add(linkTmp);
            	}
            }
            
            
            String tmp = (String) linksHandle.get(0);
            System.out.println(tmp);
            
            String pattern = "<a href=\"(.*?)\"";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(tmp);
            while(m.find()){
            	returnUrl = m.group(1);
            }
            
            System.out.print(returnUrl);
            
//            Element content = doc.select("div.ermb_text").first();
//            content = content.select("div.content").first();
//            url contains "Toursim" and "Vacations" -> the one we need
           
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return returnUrl;
	}

}
