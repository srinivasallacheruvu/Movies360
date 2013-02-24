package com.movie360.dto;

import java.io.Serializable;

public class Trailers  implements Serializable{
	private String trailerName;
	private String trailerVideoUrl;
	private String trailerThumbnailUrl;
	private String trailerDownloadable;
	private String trailerPrice;
	public String getTrailerName() {
		return trailerName;
	}
	public void setTrailerName(String trailerName) {
		this.trailerName = trailerName;
	}
	public String getTrailerVideoUrl() {
		return trailerVideoUrl;
	}
	public void setTrailerVideoUrl(String trailerVideoUrl) {
		this.trailerVideoUrl = trailerVideoUrl;
	}
	public String getTrailerThumbnailUrl() {
		return trailerThumbnailUrl;
	}
	public void setTrailerThumbnailUrl(String trailerThumbnailUrl) {
		this.trailerThumbnailUrl = trailerThumbnailUrl;
	}
	public String getTrailerDownloadable() {
		return trailerDownloadable;
	}
	public void setTrailerDownloadable(String trailerDownloadable) {
		this.trailerDownloadable = trailerDownloadable;
	}
	public String getTrailerPrice() {
		return trailerPrice;
	}
	public void setTrailerPrice(String trailerPrice) {
		this.trailerPrice = trailerPrice;
	}
	
}
