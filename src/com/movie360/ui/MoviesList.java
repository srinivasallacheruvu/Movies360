package com.movie360.ui;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.Movies;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MoviesList extends Activity implements OnItemClickListener  {
	private TextView tv;
	private List<Bitmap> images;
	private CoverFlow coverFlow;
	private Context movieListActivity=this;
	private List<Movies> moviesL= new ArrayList<Movies>();
	private List<String> lName;
	private Movies movie;
	private final int WORLD_WRITABLE=2;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// getting the Movie Object from Bundle
		Bundle b = getIntent().getExtras();
		movie =  (Movies)b.get("MovieObject"); 
		
		setContentView(R.layout.movies_list);
		
		tv=(TextView) findViewById(R.id.textView1);
		coverFlow = (CoverFlow) findViewById(R.id.coverflow); 
		images=new ArrayList<Bitmap>();  
		lName= new ArrayList<String>();
		
		MoviesListTask mTask= new MoviesListTask();
		mTask.execute();
	}
private class MoviesListTask extends AsyncTask<Void, Void, Void>{
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	@Override
	protected Void doInBackground(Void... params) {
		//getting the Movies List;
		moviesL=movie.getMoviesL(); 
		for(int i=0;i<moviesL.size();i++){
			//adding the Movie Name to array List
			lName.add(moviesL.get(i).getMovieName());
			// adding the images to image(bitMap type) array List
			ImageBitMap ibm= new ImageBitMap();
			images.add(ibm.getImageBitmap(moviesL.get(i).getImageUrl())); 
			
	 	}
	return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		
		super.onPostExecute(result);
		//  passing the images list and movie List 
		CoverFlowAdapter coverFlowAdapter=	new CoverFlowAdapter(movieListActivity, images, tv,lName);
		coverFlow.setAdapter(coverFlowAdapter);
		coverFlow.setSpacing(-25);
		coverFlow.setSelection(images.size()-1, true);
		coverFlow.setAnimationDuration(1000);
		coverFlow.setOnItemClickListener(MoviesList.this);
	}	
}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		SharedPreferences myPrefs = MoviesList.this.getSharedPreferences("movie360",WORLD_WRITABLE ); // 2 for world_Writable 
     	SharedPreferences.Editor prefsEditor = myPrefs.edit();
     	// storing the Movie ID in Shared Preferance
     	prefsEditor.putInt("MovieID",moviesL.get(arg2).getMovieId());
        prefsEditor.commit();
        Intent i = new Intent(movieListActivity,Home.class);
		startActivity(i);
		
	}
	
}
