package com.movie360.dto;

import java.io.Serializable;

public class Character  implements Serializable{

	private String character;
	private String Character_person;
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getCharacter_person() {
		return Character_person;
	}
	public void setCharacter_person(String character_person) {
		Character_person = character_person;
	}
	
}
