package com.movie360.ui;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.Meet_Star;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;
import com.movie360.ui.AboutMovie.Adapter_Demo;
import com.movie360.ui.AboutMovie.Adapter_Demo.ListViewHolder;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class Meet_The_Star  extends Activity{
	Movie360Service service;
	Context context;
	LayoutInflater LInflater;
	ListView starList;
	List<Meet_Star> meetStarList;
	ProgressDialog dialog;
	List<Bitmap> bitmapList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meet_the_star);
		
		initializeProgressDialog();
		
		meetStarList= new ArrayList<Meet_Star>();
		bitmapList = new ArrayList<Bitmap>();
		
		LInflater = LayoutInflater.from(getApplicationContext());
		starList=(ListView) findViewById(R.id.starList);
		service= new Movie360ServiceImpl(context);
		
		
		StarTask sTask = new StarTask();
		sTask.execute();
		
	}
	private class Adapter_Demo extends BaseAdapter{
		@Override
		public int getCount() {
			return meetStarList.size();
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
				arg1 = LInflater.inflate(R.layout.listinflate_livetv, null);
				holder = new ListViewHolder();
				holder.textName = (TextView) arg1.findViewById(R.id.text_name);
				holder.icon = (ImageView) arg1.findViewById(R.id.icon);
				arg1.setTag(holder);
			} else {
				holder = (ListViewHolder) arg1.getTag();
			}
			holder.textName.setText(meetStarList.get(arg0).getPerson());
			holder.icon.setImageBitmap(bitmapList.get(arg0));
			return arg1;
		}
		public class ListViewHolder {
		TextView textName;
		ImageView icon;
	}
	}
	class StarTask extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			dialog.show();
			}
			@Override
			protected Void doInBackground(Void... params) {
				meetStarList=service.getMeetTheStarDetails();
				for(int i=0;i<meetStarList.size();i++)
					bitmapList.add(getImageBitmap(meetStarList.get(i).getStarpic().get(i).getThumbnailUrl()));
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				dialog.dismiss();
				starList.setAdapter(new Adapter_Demo());
				starList.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
						Intent i = new  Intent(Meet_The_Star.this,Meet_The_Movies_Details.class);	
								i.putExtra("MeetTheStar",meetStarList.get(arg2));
						startActivity(i);
						
						
					}
				});
			}
			
	}
	private void initializeProgressDialog() {

		dialog = new ProgressDialog(this);
		dialog.setMessage("Processing...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);

	}
	public Bitmap getImageBitmap(String str){
		Bitmap bm = null;
		try{
			URL	url = new URL(str);
		URLConnection conn = url.openConnection();
 		conn.connect();
 		InputStream is = conn.getInputStream();
 		BufferedInputStream bis = new BufferedInputStream(is);
 		   bm=BitmapFactory.decodeStream(bis);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bm; 	
	}
}
