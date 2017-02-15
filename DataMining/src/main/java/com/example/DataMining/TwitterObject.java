package com.example.DataMining;

import java.util.Date;

public class TwitterObject {
	
	private String username;
	private String status;
	private String url;
	private Date time;
	
	public TwitterObject(String username, String status, String url, Date time){
		this.username = username;
		this.status = status;
		this.url = url;
		this.time = time;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setTime(Date time){
		this.time = time;
	}
	
	public Date getTime(){
		return this.time;
	}
	
	public String toString(){
		return username + " " + status + " " + url + " " + time;
	}


}
