package com.movie360.dto;

import java.io.Serializable;
import java.util.List;

public class AboutTheMovie  implements Serializable{
	private String storyLine;
	private String synopsis;
	
	private List<String> subSections;
	public List<String> getSubSections() {
		return subSections;
	}
	public void setSubSections(List<String> subSections) {
		this.subSections = subSections;
	}

	private List<Crew> crewList;
	private List<ShootingLocation> ShootingLocationList;
	private List<Character> characterList;
	private List<Trailers> trailersList;
	private List<SMSUpdateAllow> smsupdateList;
	
	public List<Crew> getCrewList() {
		return crewList;
	}
	public void setCrewList(List<Crew> crewList) {
		this.crewList = crewList;
	}
	
	public List<ShootingLocation> getShootingLocationList() {
		return ShootingLocationList;
	}
	public void setShootingLocationList(List<ShootingLocation> shootingLocationList) {
		ShootingLocationList = shootingLocationList;
	}
	public List<Character> getCharacterList() {
		return characterList;
	}
	public void setCharacterList(List<Character> characterList) {
		this.characterList = characterList;
	}
	public List<Trailers> getTrailersList() {
		return trailersList;
	}
	public void setTrailersList(List<Trailers> trailersList) {
		this.trailersList = trailersList;
	}
	public List<SMSUpdateAllow> getSmsupdateList() {
		return smsupdateList;
	}
	public void setSmsupdateList(List<SMSUpdateAllow> smsupdateList) {
		this.smsupdateList = smsupdateList;
	}

	private String shootingNname;
	public String getShootingNname() {
		return shootingNname;
	}
	public void setShootingNname(String shootingNname) {
		this.shootingNname = shootingNname;
	}
	
	public String getStoryLine() {
		return storyLine;
	}
	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	
}
