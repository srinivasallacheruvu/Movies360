package com.movie360.dto;

import java.util.List;

public class NewsDTO {
	
	private List<NewsDTO> subSections;
	private List<News> newsList;
	private List<Events> evetsList; 
			
	
	
	public List<NewsDTO> getSubSections() {
		return subSections;
	}
	public void setSubSections(List<NewsDTO> subSections) {
		this.subSections = subSections;
	}
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	public List<Events> getEvetsList() {
		return evetsList;
	}
	public void setEvetsList(List<Events> evetsList) {
		this.evetsList = evetsList;
	}
	
		
}
