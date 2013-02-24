package com.movie360.ui;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.Meet_Star;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class Meet_The_Movies_Details  extends TabActivity{
	Meet_Star m;
		Intent intent; 
	   TabHost.TabSpec spec;
	   TabHost tabHost;	
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.meet_the_star_details);
			Bundle b= getIntent().getExtras();
			try{   
	        	m =  (Meet_Star) b.get("MeetTheStar");   
	            }
	        catch(Exception e){
	        	Log.i(" Error at bundle " , e.toString());
	        }
				tabHost=getTabHost();
				intent = new Intent().setClass(this, Meet_The_Star_Intro.class); 
				intent.putExtra("raja"	,m);
		     	spec = tabHost.newTabSpec("Intro").setIndicator("Intro").setContent(intent);    
		      	tabHost.addTab(spec); 
		      	intent = new Intent().setClass(this, Meet_The_Star_Pic.class);    
		      	intent.putExtra("raja"	,m);
		      	spec = tabHost.newTabSpec("pictures").setIndicator("Pictures").setContent(intent);    
		      	tabHost.addTab(spec);    
		      	intent = new Intent().setClass(this, Meet_The_Star_Videos.class);
		      	intent.putExtra("raja"	,m);
		      	spec = tabHost.newTabSpec("Videos").setIndicator("Videos").setContent(intent);
		      	tabHost.addTab(spec);    
		 }
}
