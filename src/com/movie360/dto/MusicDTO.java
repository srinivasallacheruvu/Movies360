package com.movie360.dto;

import java.io.Serializable;
import java.util.List;

public class MusicDTO  implements Serializable {
	private List<MusicDTO> subSections;
	private String title;
	private String info;
	private int id;
    private String thumbnailUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<MusicDTO> getSubSections() {
			
		
		return subSections;
	}

	public void setSubSections(List<MusicDTO> subSections) {
		this.subSections = subSections;
	}

	
}
