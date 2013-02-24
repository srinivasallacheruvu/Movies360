package com.movie360.dto;

import java.io.Serializable;

public class SMSUpdateAllow implements Serializable {
	private int packageID;
	private String price;
	private int duration;
	private boolean SMSUpdateAllow;
	public boolean isSMSUpdateAllow() {
		return SMSUpdateAllow;
	}
	public void setSMSUpdateAllow(boolean sMSUpdateAllow) {
		SMSUpdateAllow = sMSUpdateAllow;
	}
	public int getPackageID() {
		return packageID;
	}
	public void setPackageID(int packageID) {
		this.packageID = packageID;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
