package com.example.DataMining;

public class PipleineObject {
	
	private String date;
	private String description;
	private String url;
	
	public PipleineObject(String date, String description, String url){
		this.date = date;
		this.description = description;
		this.url = url;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getdescription(){
		return this.description;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String toString(){
		return date + " " + description + " " + url;
	}

}
