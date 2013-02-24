package com.movie360.dto;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;

public class Movies  implements Serializable {
	 private int movieId;
	 private String MovieName;
	 private String imageUrl;
	 private List<Movies> moviesL;
		
	 
	
	
	public  List<Movies> getMoviesL() {
		return moviesL;
	}
	public void setMoviesL(List<Movies> moviesL) {
		this.moviesL = moviesL;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
