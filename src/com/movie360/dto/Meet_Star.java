package com.movie360.dto;

import java.io.Serializable;
import java.util.List;


public class Meet_Star  implements Serializable {
	private String person;
	private String character;
	private String characterIntro;
	private String starDiary;
	
	private List<Meet_Star> meetstar;
	public List<Meet_Star> getMeetstar() {
		return meetstar;
	}

	public void setMeetstar(List<Meet_Star> meetstar) {
		this.meetstar = meetstar;
	}

	private List<Star_Pictures> starpic;
	private List<Star_Videos> starvideo;
	private List<Star_Interview> starinterview;

	
	public String getStarDiary() {
		return starDiary;
	}

	public void setStarDiary(String starDiary) {
		this.starDiary = starDiary;
	}

		
	public List<Star_Interview> getStarinterview() {
		return starinterview;
	}

	public void setStarinterview(List<Star_Interview> starinterview) {
		this.starinterview = starinterview;
	}

	public List<Star_Videos> getStarvideo() {
		return starvideo;
	}

	public void setStarvideo(List<Star_Videos> starvideo) {
		this.starvideo = starvideo;
	}

	
	public List<Star_Pictures> getStarpic() {
		return starpic;
	}

	public void setStarpic(List<Star_Pictures> starpic) {
		this.starpic = starpic;
	}

	
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getCharacterIntro() {
		return characterIntro;
	}

	public void setCharacterIntro(String characterIntro) {
		this.characterIntro = characterIntro;
	}

	List<String> subsections;
	
	

	public List<String> getSubsections() {
		return subsections;
	}

	public void setSubsections(List<String> subsections) {
		this.subsections = subsections;
	}
	
}
