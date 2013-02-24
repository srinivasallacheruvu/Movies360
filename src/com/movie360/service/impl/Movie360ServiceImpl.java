package com.movie360.service.impl;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.ImageDTO;
import com.movie360.dto.Meet_Star;
import com.movie360.dto.Movies;
import com.movie360.dto.MusicDTO;
import com.movie360.dto.NewsDTO;
import com.movie360.dto.VideosDTO;
import com.movie360.service.Movie360Service;
import com.movie360.ui.Meet_The_Star;

public class Movie360ServiceImpl  implements Movie360Service{
	private Context context;
	Parser parser;
	private boolean result;
	private List<Movies> moviesList;
	
	public Movie360ServiceImpl(Context context) {
	this.context=context;
	}

	@Override
	public boolean parseData() {
		
		return false;
	}

	@Override
	public Movies intialRequest(String str) throws Exception {
		parser=new Parser(context);
		return parser.intialRequest(str);
	}

	@Override
	public List<Movies> getMoviesList() {
		
		return moviesList;
	}

	@Override
	public List<String> getSection(String sectionRequest) {
		Log.i("Movie360","section request@service");
		parser=new Parser(context);
		
		return parser.getSection(sectionRequest);
	}

	@Override
	public AboutTheMovie getAboutmovieDetails() {
	
		parser=new Parser(context);
		return parser.getSectionDetails();
	}

	@Override
	public List<Meet_Star> getMeetTheStarDetails() {
		
		parser=new Parser(context);
		return parser.getMeetTheStarDetails();
	}

	@Override
	public ImageDTO getImageDetails() {
	
		parser=new Parser(context);
		return parser.getImageDetails();
		
	}

	@Override
	public MusicDTO getMusicDetails() {
		parser=new Parser(context);
		return parser.getMusicDetails();
	}

	@Override
	public VideosDTO getVideosDetails() {
		parser=new Parser(context);
		return parser.getVideosDetails();
	}

	@Override
	public NewsDTO getNewsDetails() {
		parser=new Parser(context);
		return parser.getEventDetails();

	}

}
