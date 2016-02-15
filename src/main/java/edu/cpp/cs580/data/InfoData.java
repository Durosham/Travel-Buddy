package edu.cpp.cs580.data;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class InfoData {
	private String title;
	private String content;
	
	public void setTitle(String T){
		this.title = T;
	}
	
	public void setDescription(String C){
		this.content = C;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void getFromWeb(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            Element content = doc.select("div.ermb_text").first();
            content = content.select("div.content").first();
            this.setTitle(doc.title());
            this.setDescription(content.html());            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void printAll(){
		System.out.println("Webpage Title:\n" + this.getTitle());
		System.out.println("Description:\n" + this.getContent());
	} 
	
}
