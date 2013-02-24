package com.movie360.dto;

import java.io.Serializable;
import java.util.List;

public class VideosDTO  implements Serializable{
	private List<VideosDTO> subSections;
	private String Section;
	private int id;
	private  boolean show;
	
	
	
	
	
	
	public String getSection() {
		return Section;
	}
	public void setSection(String section) {
		Section = section;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public List<VideosDTO> getSubSections() {
		return subSections;
	}
	public void setSubSections(List<VideosDTO> subSections) {
		this.subSections = subSections;
	}
	
}
