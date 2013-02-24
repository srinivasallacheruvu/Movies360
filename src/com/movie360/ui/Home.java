package com.movie360.ui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.movie360.dto.Sections;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	private GridView grid;
	private Context HomeActivity=this;
	
	private List<Bitmap> images;
	private List<String> sectionList;
	private int  movieID;
	private ProgressDialog myProgressDialog;
	private Movie360Service service;
	private Context homeActivity=this;
	private String sectionRequest;
	private Resources resource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_demo);
		service=new Movie360ServiceImpl(homeActivity); 
		images= new ArrayList<Bitmap>();	
		sectionList= new ArrayList<String>();
		resource = this.getResources();
		
		initializeProgressDialog();
		
		//getting the String from values/string.xml
		sectionRequest= resource.getString(R.string.sectionRequest);
		SharedPreferences myPrefs = this.getSharedPreferences("movie360", 2);
		//reading the Movie Id From SharedPreferances
		movieID =myPrefs.getInt("MovieID",0);
		// adding the Movie Id to our Request
		sectionRequest+=movieID;
	
		HomeTask hTask= new HomeTask();
		hTask.execute();
		
		grid=(GridView) findViewById(R.id.gv);
		grid.setOnItemClickListener(new  OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int i, long arg3) {
				if(sectionList.get(i).equals("About the movie"))
					startActivity(new Intent(HomeActivity,AboutMovie.class));
				else if(sectionList.get(i).equals("Meet the stars"))
					startActivity(new Intent(HomeActivity,Meet_The_Star.class));
				else if(sectionList.get(i).equals("Images"))
					startActivity(new Intent(HomeActivity,Images.class));
				else if(sectionList.get(i).equals("Music"))
					startActivity(new Intent(HomeActivity,Music.class)); 
				else if(sectionList.get(i).equals("Videos"))
					startActivity(new Intent(HomeActivity,Videos.class)); 
				else if(sectionList.get(i).equals("Games & Contests"))
					startActivity(new Intent(HomeActivity,Games_And_Contest.class));
				else if(sectionList.get(i).equals("News & Events"))
					startActivity(new Intent(HomeActivity,Event_And_News.class));
				else if(sectionList.get(i).equals("release"))
					startActivity(new Intent(HomeActivity,MovieRelease.class));
			}
		});
	}
	//adapter class
	private class GridAdapter extends BaseAdapter {
		private Context context;
		public GridAdapter(Context context) {
			this.context=context;
		}	
		@Override
		public int getCount() {
		return images.size();
		}
		@Override
		public Object getItem(int position) {
		return position;
		}
		@Override
		public long getItemId(int position) {
		return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			if(convertView==null){
				LayoutInflater ll=getLayoutInflater();
				v=ll.inflate(R.layout.home_list, null);
				TextView tv = (TextView)v.findViewById(R.id.textView1);  
				tv.setText(""+sectionList.get(position));  
				tv.setPadding(5, 0, 0, 0);
				tv.setTextColor(Color.WHITE);
				tv.setTextSize(15);
				ImageView iv = (ImageView)v.findViewById(R.id.imageView1);  
				iv.setImageBitmap(images.get(position));
			}
			else{
				v=convertView;
			}
		return v;
	 }
}	
private class HomeTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			myProgressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
					sectionList=service.getSection(sectionRequest);
				}
			catch (Exception e) {
					
				}
			  for(int i=0;i<sectionList.size();i++){
						if(sectionList.get(i).equals("About the movie"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.about_the_movie));
						else if(sectionList.get(i).equals("Meet the stars"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.meet_the_stars));
						else if(sectionList.get(i).equals("Images"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.image));
						else if(sectionList.get(i).equals("Music"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.music));
						else if(sectionList.get(i).equals("Videos"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.video));
						else if(sectionList.get(i).equals("Games & Contests"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.game));
						else if(sectionList.get(i).equals("Events And News"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.news));
						else if(sectionList.get(i).equals("release"))
							images.add(BitmapFactory.decodeResource(getResources(), R.drawable.release));
				}
		return null;
		}
		@Override
		protected void onPostExecute(Void result) {
		super.onPostExecute(result);
			myProgressDialog.dismiss();
			grid.setAdapter(new GridAdapter(HomeActivity) );
		}
}
private void initializeProgressDialog() {
	myProgressDialog = new ProgressDialog(this);
	myProgressDialog.setMessage("Processing...");
	myProgressDialog.setIndeterminate(true);
	myProgressDialog.setCancelable(true);
}
}
