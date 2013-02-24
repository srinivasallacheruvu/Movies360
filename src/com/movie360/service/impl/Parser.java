package com.movie360.service.impl;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ContentHandler;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.movie360.dao.impl.DBGen;
import com.movie360.dao.impl.ParserDAOImpl;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.movie360.dao.ParserDAO;
import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.Character;
import com.movie360.dto.Crew;
import com.movie360.dto.ImageDTO;
import com.movie360.dto.Meet_Star;
import com.movie360.dto.Movies;
import com.movie360.dto.MusicDTO;
import com.movie360.dto.NewsDTO;
import com.movie360.dto.SMSUpdateAllow;
import com.movie360.dto.Sections;
import com.movie360.dto.ShootingLocation;
import com.movie360.dto.Star_Interview;
import com.movie360.dto.Star_Pictures;
import com.movie360.dto.Star_Videos;
import com.movie360.dto.Trailers;
import com.movie360.dto.VideosDTO;
import com.movie360.ui.Meet_The_Star;
import com.movie360.ui.MoviesList;
import com.movie360.ui.R;
import com.movie360.ui.SplashScreen;
public class Parser {
		SQLiteDatabase db;
		private boolean result;
		private String JSONString;
		private ParserDAO parser;
		private Context context;
		private List<Movies> moviesList;
		private List<String> sectionList;
		private List<Crew> crewList;
		private List<Bitmap> images;
		private List<ShootingLocation> ShootingLocationList;
		private List<Character> characterList;
		private List<Trailers> trailersList;
		private List<SMSUpdateAllow> smsupdateList;
		
		private List<Meet_Star> meetStar;
		private List<Star_Pictures> starpic;
		private List<Star_Videos>starvideo;
		private List<Star_Interview> starinterview;
		
		
		private AboutTheMovie atm;
		
		public Parser(Context context) {
			this.context=context;
			moviesList= new ArrayList<Movies>();
			sectionList= new ArrayList<String>();
			atm= new AboutTheMovie();
			images = new ArrayList<Bitmap>();
			crewList= new ArrayList<Crew>();
			ShootingLocationList= new ArrayList<ShootingLocation>();
			characterList= new ArrayList<Character>();
			trailersList= new ArrayList<Trailers>();
			smsupdateList= new ArrayList<SMSUpdateAllow>();
			starpic= new ArrayList<Star_Pictures>();
			starvideo= new ArrayList<Star_Videos>();
			starinterview= new ArrayList<Star_Interview>();
			meetStar= new ArrayList<Meet_Star>();
		}
		
		public Movies intialRequest(String str)  throws Exception {
			Movies m= new Movies();
			//JSONString=getJSONString(str);	
				JSONString=getJSONString("http://kj.ayansys.com/m360/m360Movies.json");	
			//JSON Parsing for Movie Details	
				JSONObject jobj= new JSONObject(JSONString);
				JSONArray jobj1=jobj.getJSONArray("movies");
					for(int a=0;a<jobj1.length();a++){
							Movies movie= new Movies();
							int movieId= jobj1.getJSONObject(a).getInt("movieId");
							String title=jobj1.getJSONObject(a).getString("title");
							movie.setMovieId(movieId);
							movie.setMovieName(title);
							movie.setImageUrl(jobj1.getJSONObject(a).getString("thumbnailUrl" ));
						moviesList.add(movie);
					}
				m.setMoviesL(moviesList);
		return m;
		}
		
		public Bitmap getImageUrl(String str){
			Bitmap bm=null;
			try{
				URL	url = new URL(str);
				URLConnection conn = url.openConnection();
				conn.connect();
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				 bm=BitmapFactory.decodeStream(bis);
			}catch (Exception e) {
				e.printStackTrace();
			}
		return bm;	
		}
		public List<Movies> getMList(){
			
		return moviesList;
		} 
		private String	getJSONString(String str){
			Log.i("Movie360","At reading the Data");
			String temp="";
			int ch;
			char ch1;
			try{
				URL url=new URL(str);
				URLConnection con=url.openConnection();
				con.connect();
				InputStream is= con.getInputStream();
				if(is==null){
					Log.i("Movie360","Error :URL is not available");
					// 
					result=false;
				}
				else{
					BufferedReader br= new BufferedReader(new InputStreamReader(is));
					while((ch=br.read())!=-1){
						ch1=(char) ch;
					temp+=ch1;
					}
				}
			}
			catch (Exception e) {
				result=false;
				e.printStackTrace();
			}
			
			
		return temp;
		}
		public List<String> getSection(String sectionRequest){
			try{
			//JSONString=getJSONString(sectionRequest);
			JSONString=getJSONString("http://kj.ayansys.com/~ayansys/android/sections.json");
			JSONObject jobj= new JSONObject(JSONString);
			JSONArray jobj1=jobj.getJSONArray("sections");
			for(int a=0;a<jobj1.length();a++){
				
				Sections section= new Sections();
				String sectionName= jobj1.getJSONObject(a).getString("section");
				boolean show=jobj1.getJSONObject(a).getBoolean("show");
				if(show==true){
					section.setSectionName(sectionName);
					sectionList.add(sectionName);
					}
		Log.i("Movie360","@Parser"+sectionList.size());		
			}
			
			}catch (Exception e) {
			e.printStackTrace();
			}
		return sectionList;
		}
		public AboutTheMovie getSectionDetails(){
		
			List<String> l= new ArrayList<String>();
			try{
				
				
				
				 JSONString=getJSONString("http://kj.ayansys.com/~ayansys/Blackberry/aboutTheMovie.json");
				
				//JSONString=getJSONString("http://kj.ayansys.com/m360/about-the-movie.json");
				JSONObject jobj= new JSONObject(JSONString);
				 String synopsis= jobj.getString("synopsis");
				 l.add("synopsis");
				atm.setSynopsis(synopsis);
				//aboutMovies.put("synopsis",atm);
				
					
				String storyLine= jobj.getString("storyLine");
				atm.setStoryLine(storyLine);
				//aboutMovies.put("storyline",atm);
				 l.add("storyline");
				JSONArray jobj1=jobj.getJSONArray("crew");
				 l.add("crew");
				//aboutMovies.put("crew",atm);
				for(int i=0;i<jobj1.length();i++){
				       Crew c= new Crew();
				       		c.setBiodata(jobj1.getJSONObject(i).getString("biodata").toString());
				       		c.setPerson(jobj1.getJSONObject(i).getString("person" ).toString());
				       		c.setPhoto(jobj1.getJSONObject(i).getString("photo" ).toString());
				       		c.setRole(jobj1.getJSONObject(i).getString("role" ).toString());
				       		crewList.add(c);
				}
				atm.setCrewList(crewList);
				
				JSONArray jobj2=jobj.getJSONArray("shootingLocations");
				
				//aboutMovies.put("shootingLocations",atm);
				 l.add("shootingLocations");
				for(int i=0;i<jobj2.length();i++){
						ShootingLocation sl = new ShootingLocation();
							sl.setName(jobj2.getJSONObject(i).getString("name").toString());
							sl.setShootingPhoto(jobj2.getJSONObject(i).getString("photo").toString());
							sl.setShootingDetails(jobj2.getJSONObject(i).getString("details" ).toString());
							ShootingLocationList.add(sl);
				}
				atm.setShootingLocationList(ShootingLocationList);
				
				JSONArray jobj3=jobj.getJSONArray("characters");
				//aboutMovies.put("Characters",atm);
				 l.add("Characters");
				for(int i=0;i<jobj3.length();i++){
						Character c= new Character();		
							c.setCharacter(jobj3.getJSONObject(i).getString("character").toString());
							c.setCharacter_person(jobj3.getJSONObject(i).getString("person").toString());
						characterList.add(c);
					}	
					
					atm.setCharacterList(characterList);
					JSONArray jobj4=jobj.getJSONArray("trailers");
					
					//aboutMovies.put("Trailers",atm);
					 l.add("Trailers");
					for(int i=0;i<jobj4.length();i++){
								Trailers t= new Trailers();
							t.setTrailerName(jobj4.getJSONObject(i).getString("name").toString());
							t.setTrailerVideoUrl(jobj4.getJSONObject(i).getString("videoUrl").toString());
							t.setTrailerThumbnailUrl(jobj4.getJSONObject(i).getString("thumbnailUrl").toString());
							t.setTrailerDownloadable(jobj4.getJSONObject(i).getString("downloadable").toString());
							t.setTrailerPrice(jobj4.getJSONObject(i).getString("price").toString());
						trailersList.add(t);
					}
					atm.setTrailersList(trailersList);
					
					JSONObject jobj5=jobj.getJSONObject("dailySMSUpdates");
					//aboutMovies.put("dailySMSUpdates",atm);
					 l.add("dailySMSUpdates");
								jobj5.getString("allowed");
								JSONArray jobj6=jobj5.getJSONArray("packages");
					for(int i=0;i<jobj6.length();i++){
								jobj6.getJSONObject(i).get("packageId");
								jobj6.getJSONObject(i).get("price");
								jobj6.getJSONObject(i).get("duration");
					}
				
				}
			catch (Exception e) {
				e.printStackTrace();
			}
			atm.setSubSections(l);
		return atm;
		}

		public List<Meet_Star> getMeetTheStarDetails(){
			//JSONString=getJSONString("http://kj.ayansys.com/~ayansys/android/meet-the-stars.json");
			JSONString=getJSONString("http://kj.ayansys.com/m360/m360MeetTheStars.json");
			try{
			JSONObject jb= new JSONObject(JSONString);	
			JSONArray jsonObj= jb.getJSONArray("stars");
					for(int i=0;i<jsonObj.length();i++)
					{
						Meet_Star ms1= new Meet_Star();
					
						ms1.setPerson(jsonObj.getJSONObject(i).getString("person"));
						ms1.setCharacter(jsonObj.getJSONObject(i).getString("character"));
						ms1.setCharacterIntro(jsonObj.getJSONObject(i).getString("characterIntro"));
					
						JSONArray jobj1= new JSONArray(jsonObj.getJSONObject(i).getString("pictures"));
								for(int j=0;j<jobj1.length();j++){
									Star_Pictures sp= new Star_Pictures();
									sp.setId(jobj1.getJSONObject(i).getInt("id"));
									sp.setName(jobj1.getJSONObject(i).getString("name"));
									sp.setThumbnailUrl(jobj1.getJSONObject(i).getString("thumbnailUrl"));
									starpic.add(sp);	
								}
							ms1.setStarpic(starpic);
							
							JSONArray jobj3= new JSONArray(jsonObj.getJSONObject(i).getString("videos"));
							for(int j=0;j<jobj3.length();j++){
								Star_Videos sv= new Star_Videos();
								sv.setId(jobj3.getJSONObject(i).getInt("id"));
								sv.setName(jobj3.getJSONObject(i).getString("name"));
								sv.setThumbnailUrl(jobj3.getJSONObject(i).getString("thumbnailUrl"));
								starvideo.add(sv);
								
							}
							JSONArray jobj5= new JSONArray(jsonObj.getJSONObject(i).getString("interviews"));
							for(int j=0;j<jobj5.length();j++){
									Star_Interview si= new Star_Interview();
										si.setId(jobj5.getJSONObject(i).getInt("id"));
										si.setThumbnailUrl(jobj5.getJSONObject(i).getString("thumbnailUrl"));
										si.setTitle(jobj5.getJSONObject(i).getString("title"));
										si.setDetails(jobj5.getJSONObject(i).getString("details"));
							starinterview.add(si);
						}
							ms1.setStarinterview(starinterview);
							ms1.setStarDiary(jsonObj.getJSONObject(i).getString("starDiaryId"));
							meetStar.add(ms1);
					}
			
			}catch (Exception e) {
		
				e.printStackTrace();
			
			}
			
			return meetStar;
		}
		public MusicDTO getMusicDetails(){
			List<MusicDTO> md= new ArrayList<MusicDTO>();
			
			MusicDTO music= new MusicDTO();
			 JSONString=getJSONString("http://kj.ayansys.com/m360/m360Music.json");
			 	try{
			 	JSONObject jobj= new JSONObject(JSONString);
			 				JSONArray jobj1=jobj.getJSONArray("music");
			 					for(int i=0;i<jobj1.length();i++){
			 						MusicDTO music1= new MusicDTO();
			 						music1.setTitle(jobj1.getJSONObject(i).getString("title"));
			 						music1.setInfo(jobj1.getJSONObject(i).getString("info"));
			 						music1.setThumbnailUrl(jobj1.getJSONObject(i).getString("thumbnailUrl"));
			 						music1.setId(jobj1.getJSONObject(i).getInt("id"));
			 						 md.add(music1);	
			 		}
			 		music.setSubSections(md);
			 	}catch (Exception e) {

			 	}
		return music;
		};
		public ImageDTO getImageDetails(){

			ImageDTO image= new ImageDTO();
					List<ImageDTO> iList= new ArrayList<ImageDTO>();
			 JSONString=getJSONString("http://kj.ayansys.com/m360/m360Images.json");
			 	try{
			 			JSONObject jobj= new JSONObject(JSONString);
			 				JSONArray jobj1=jobj.getJSONArray("images");
			 					for(int i=0;i<jobj1.length();i++){
			 						if(jobj1.getJSONObject(i).getBoolean("show")==true){
			 							ImageDTO image1= new ImageDTO();
			 							image1.setSection(jobj1.getJSONObject(i).getString("section"));
			 							image1.setId(jobj1.getJSONObject(i).getInt("id"));
			 							image1.setShow(jobj1.getJSONObject(i).getBoolean("show"));
			 							iList.add(image1);
			 						}
			 						
			 					 }
			 					image.setSubSections(iList);   
	 					Log.i("Videos",""+iList.size());	
			 	}catch (Exception e) {
			 		e.printStackTrace();
			 	}
		
			
			return image;
		};
		public VideosDTO getVideosDetails(){
			VideosDTO video= new VideosDTO();
					List<VideosDTO> vList= new ArrayList<VideosDTO>();
			 JSONString=getJSONString("http://kj.ayansys.com/m360/m360Videos.json");
			 	try{
			 			JSONObject jobj= new JSONObject(JSONString);
			 				JSONArray jobj1=jobj.getJSONArray("videos");
			 					for(int i=0;i<jobj1.length();i++){
			 						if(jobj1.getJSONObject(i).getBoolean("show")==true){
			 						VideosDTO video1= new VideosDTO();
			 						video1.setSection(jobj1.getJSONObject(i).getString("section"));
			 						video1.setId(jobj1.getJSONObject(i).getInt("id"));
			 						video1.setShow(jobj1.getJSONObject(i).getBoolean("show"));
			 						vList.add(video1);
			 						}
			 						
			 					 }
			 					 video.setSubSections(vList);   
			 					Log.i("Videos",""+vList.size());	
			 	}catch (Exception e) {

			 	}
			return video;
		}



		public NewsDTO getEventDetails(){
			
			return null;
		}

}
