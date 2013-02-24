package com.movie360.service;

import java.util.HashMap;
import java.util.List;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.ImageDTO;
import com.movie360.dto.Meet_Star;
import com.movie360.dto.Movies;
import com.movie360.dto.MusicDTO;
import com.movie360.dto.NewsDTO;
import com.movie360.dto.Sections;
import com.movie360.dto.VideosDTO;
import com.movie360.ui.Meet_The_Star;

public interface Movie360Service {
	boolean parseData();
	Movies intialRequest(String str) throws Exception;
	List<Movies> getMoviesList();
	List<String> getSection(String sectionRequest);
	
	AboutTheMovie getAboutmovieDetails();
	List<Meet_Star> getMeetTheStarDetails();
	ImageDTO getImageDetails();
	MusicDTO getMusicDetails();
	VideosDTO getVideosDetails();
	NewsDTO getNewsDetails();
	
}
