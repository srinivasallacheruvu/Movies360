package com.movie360.dto;

import java.io.Serializable;

public class ShootingLocation implements Serializable  {
	private String shootingPhoto;
	private String shootingDetails;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShootingPhoto() {
		return shootingPhoto;
	}
	public void setShootingPhoto(String shootingPhoto) {
		this.shootingPhoto = shootingPhoto;
	}
	public String getShootingDetails() {
		return shootingDetails;
	}
	public void setShootingDetails(String shootingDetails) {
		this.shootingDetails = shootingDetails;
	}
	
}
