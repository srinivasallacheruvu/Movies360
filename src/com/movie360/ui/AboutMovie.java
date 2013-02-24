package com.movie360.ui;

import java.io.BufferedInputStream;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.AboutTheMovie;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author rajashekhar.r
 *
 */


public class AboutMovie  extends Activity{
	private ListView ll;
	private  LayoutInflater mInflater;
	private  Context context=this;
	/**
	 * service to expose the data
	 */
	private  Movie360Service service;
	private  AboutTheMovie sectionMap;
	private ProgressDialog dialog;
	
	private  List<String> subSection;
	private SharedPreferences myPrefs;
	private SharedPreferences.Editor prefsEditor;
	private  final int WORLD_WRITABLE=2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.about_movie);
			initializeProgressDialog();
			
			myPrefs = AboutMovie.this.getSharedPreferences("Movie360",WORLD_WRITABLE); // 2 for world_Writable 
			prefsEditor = myPrefs.edit();
			mInflater = LayoutInflater.from(getApplicationContext());
			ll=(ListView) findViewById(R.id.listView1);
			service= new Movie360ServiceImpl(context);
			sectionMap= new AboutTheMovie();
			subSection= new ArrayList<String>();
			AboutMovieTask movieTask= new AboutMovieTask();
			movieTask.execute();
				
	}	
class Adapter_Demo extends BaseAdapter{
	@Override
	public int getCount() {
		return subSection.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ListViewHolder holder=null;
		if (arg1 == null) {
			arg1 = mInflater.inflate(R.layout.listinflate_livetv, null);
			holder = new ListViewHolder();
			holder.textName = (TextView) arg1.findViewById(R.id.text_name);
			holder.icon = (ImageView) arg1.findViewById(R.id.icon);
			arg1.setTag(holder);
		} else {
			holder = (ListViewHolder) arg1.getTag();
		}
		holder.textName.setText(subSection.get(arg0));
		holder.textName.setTextSize(20);
		holder.icon.setImageResource(R.drawable.about_the_movie);
	return arg1;
	}
	public class ListViewHolder {
	TextView textName;
	ImageView icon;
}
}
class AboutMovieTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		dialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			try{
			sectionMap=	service.getAboutmovieDetails();
			subSection=sectionMap.getSubSections();
			}
			catch (Exception e) {
			e.printStackTrace();
			}
		return null;
		}
		@Override
		protected void onPostExecute(Void result) {
		super.onPostExecute(result);
			dialog.dismiss();
			ll.setAdapter(new Adapter_Demo());
		    ll.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					switch (arg2) {
					case 0:	prefsEditor.putString("title",sectionMap.getSynopsis());
					        prefsEditor.commit();
						    startActivity(new Intent(AboutMovie.this,AboutMovie_Detalis.class)) ;	break;
					case 1: prefsEditor.putString("title", sectionMap.getStoryLine());
					        prefsEditor.commit();
					        startActivity(new Intent(AboutMovie.this,StoryLine.class)) ; 			break;
					case 2: Intent i = new Intent(AboutMovie.this,Cast_And_Crew.class);
							i.putExtra("hashtable", sectionMap);
							startActivity(i);                             			    			break;
					case 3: Intent sl = new Intent(AboutMovie.this,ShootingLocations.class);
							sl.putExtra("hashtable", sectionMap);
							startActivity(sl);                             			    			break;
					case 4: Intent i2 = new Intent(AboutMovie.this,MovieCharacter.class);
							i2.putExtra("hashtable", sectionMap);
							startActivity(i2); 											    		break;
			    	case 5: Intent i1 = new Intent(AboutMovie.this,Trailers.class);
							i1.putExtra("hashtable", sectionMap);
							startActivity(i1);														break;
					case 6:	
						Intent i3 = new Intent(AboutMovie.this,DialySMSUpdates.class);
						i3.putExtra("hashtable", sectionMap);
				    	startActivity(i3);													    	break;
					}
				}
			})	;
		}
}
private void initializeProgressDialog() {
	dialog = new ProgressDialog(this);
	dialog.setMessage("Processing...");
	dialog.setIndeterminate(true);
	dialog.setCancelable(true);
 }
}
