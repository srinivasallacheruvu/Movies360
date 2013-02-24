package com.movie360.ui;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.ShootingLocation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class ShootingLocations extends Activity{
		private AboutTheMovie m;
		LinearLayout layout;
		ImageBitMap ibm;
		private ProgressDialog dialog;
		private List<Bitmap> bitmapList;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.comman_details);
			initializeProgressDialog();
			bitmapList= new  ArrayList<Bitmap>();
			
			Bundle bundel = getIntent().getExtras();   
				layout=(LinearLayout) findViewById(R.id.tableLayout1);
				try{   
					m =  (AboutTheMovie) bundel.get("hashtable");   
				}
				catch(Exception e){
					Log.i(" Error at bundle " , e.toString());
				}
				ShootingTask sTask = new ShootingTask();
				sTask.execute();
	 	}
private class ShootingTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		dialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			for(int i=0;i<m.getShootingLocationList().size();i++){
				Log.i("Movie360",m.getShootingLocationList().get(i).getShootingPhoto());
				bitmapList.add(getImageBitmap(m.getShootingLocationList().get(i).getShootingPhoto()));}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
		creatView();
		}
} 
private void initializeProgressDialog() {
	dialog = new ProgressDialog(this);
	dialog.setMessage("Processing...");
	dialog.setIndeterminate(true);
	dialog.setCancelable(true);
 }
public void creatView(){
	for(int i=0;i<m.getShootingLocationList().size();i++){
		TableRow tr = new TableRow(this);
		ImageButton iv= new ImageButton(this);
	
		iv.setImageBitmap(bitmapList.get(i));
		tr.addView(iv);
			LinearLayout ll2= new LinearLayout(this);
				ll2.setOrientation(LinearLayout.VERTICAL);
				TextView tv1= new TextView(this);	
				tv1.setPadding(10, 0, 0, 0);
				tv1.setText("Location:  "+ m.getShootingLocationList().get(i).getName());
				tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				ll2.addView(tv1);
				TextView tv2= new TextView(this);	
				tv2.setLines(2);
				tv2.setText("Details:"+m.getShootingLocationList().get(i).getShootingDetails());
				tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				ll2.addView(tv2);
				tr.addView(ll2);
				layout.addView(tr);
				View v = new View(this);
				v.setBackgroundColor(Color.WHITE);
				v.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,2));
				layout.addView(v);	
		}
}
public Bitmap getImageBitmap(String str){
	Bitmap bm = null;
	try{URL	url = new URL(str);
	URLConnection conn = url.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		   bm=BitmapFactory.decodeStream(bis);
	}
	catch (Exception e) {
		e.printStackTrace();
		return BitmapFactory.decodeResource(getResources()	,R.drawable.game);
	}
	return bm; 	
}
}
