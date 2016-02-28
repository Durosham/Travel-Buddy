package edu.cpp.cs580.data.provider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SearchFromGoogle {
	
	public static String get(String searchInput){
		String returnUrl = "";
		String queryUrl = "https://www.google.com/search?q=" + searchInput + "+tripadvisor";
        try {
        	System.out.print(queryUrl);
            Document doc = Jsoup.connect(queryUrl)
            				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
            				.get();
//            Element content = doc.select("div.ermb_text").first();
//            content = content.select("div.content").first();
        
        } catch (IOException e) {
            e.printStackTrace();
        }
		return returnUrl;
	}

}
