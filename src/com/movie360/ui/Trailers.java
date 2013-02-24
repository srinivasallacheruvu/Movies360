package com.movie360.ui;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.movie360.dto.AboutTheMovie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

public class Trailers extends Activity implements OnClickListener {
	LinearLayout layout;
	AboutTheMovie m;
	List<Bitmap> bitmapList;
	private ProgressDialog dialog;
	List<com.movie360.dto.Trailers> t= new ArrayList<com.movie360.dto.Trailers>();
	int  j;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trailers);
		initializeProgressDialog();
		bitmapList= new ArrayList<Bitmap>();
		layout=(LinearLayout) findViewById(R.id.tableLayout1);
		Bundle bundel = getIntent().getExtras();   
       try{   
        	m =  (AboutTheMovie) bundel.get("hashtable");   
            }
        catch(Exception e){
        	Log.i(" Error at bundle " , e.toString());
        } 
        
        TrailersTask tTask= new TrailersTask();
        tTask.execute();
	
	}
	
	
	@Override
	public void onClick(View v) {
		int x= v.getId();
			
		Toast.makeText(getApplicationContext(), ""+x, 500).show();
		
		
	}	  
	private class TrailersTask extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			 t=m.getTrailersList();
			
			 for(int i=0;i<t.size();i++)
				 {
				 bitmapList.add(getImageBitmap(t.get(i).getTrailerThumbnailUrl()));
				 
				 }
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			createTable();
		}
		
		
	} 
	
	public void createTable(){
		for(int i=0;i<t.size();i++){
	    	t.get(i).getTrailerVideoUrl();
	    		TableRow tr = new TableRow(this);
	    				tr.setId(i);
	    				tr.setOnClickListener(this);
						ImageView iv= new ImageView(this);
						//tv.setText(cList.get(i).getPhoto()); t.get(i).getTrailerThumbnailUrl()
						//iv.setImageResource(R.drawable.game);	
						iv.setPadding(0, 10, 0, 0);
						
						iv.setLayoutParams(new LayoutParams(75,75));
						iv.setImageBitmap(bitmapList.get(i));
						tr.addView(iv);
						TableLayout tl = new TableLayout(this);
							TableRow namerow= new TableRow(this);
								TextView tv1= new TextView(this);	
								tv1.setPadding(10, 0, 0, 0);
								tv1.setText("Name:  "+ t.get(i).getTrailerName());
								tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							namerow.addView(tv1);	
							tl.addView(namerow);
							
							TableRow pricerow= new TableRow(this);
								TextView tv2= new TextView(this);	
								tv2.setPadding(10, 0, 0, 0);
								tv2.setText("Price:@Rs"+t.get(i).getTrailerPrice());
								tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
							pricerow.addView(tv2);	
							tl.addView(pricerow);
							
							TableRow pdrow= new TableRow(this);
									LinearLayout playL= new LinearLayout(this);
										playL.setOrientation(LinearLayout.VERTICAL);
										playL.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
											ImageView playI= new ImageView(this);
													playI.setImageResource(R.drawable.play);
													playI.setPadding(10, 0, 0, 0);
													playI.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
											playL.addView(playI);
											TextView tv4= new TextView(this);	
												tv4.setPadding(10, 0, 0, 0);
												tv4.setText("Play");
												tv4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
											 playL.addView(tv4);	
									
									pdrow.addView(playL);
		
//							if(Boolean.parseBoolean(t.get(i).getTrailerDownloadable())==true){
//								LinearLayout downloadL= new LinearLayout(this);
//							downloadL.setOrientation(LinearLayout.VERTICAL);
//							downloadL.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
//								ImageView downI= new ImageView(this);
//								downI.setImageResource(R.drawable.down);
//								downI.setPadding(0, 0, 50, 0);
//								downI.setId(i+j);
//								downI.setOnClickListener(this);
//								
//								j++;
//								downI.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
//										downloadL.addView(downI);
//								TextView tv5= new TextView(this);	
//									tv5.setPadding(0, 0, 70, 0);
//									tv5.setOnClickListener(this);
//									tv5.setText("download");
//									tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
//									downloadL.addView(tv5);	
//									downloadL.setOnClickListener(this);
//									pdrow.addView(downloadL);
//							}	
									tl.addView(pdrow);	
							
					tr.addView(tl);
					layout.addView(tr);
					View v = new View(this);
			    v.setBackgroundColor(Color.WHITE);
				v.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,2));
				layout.addView(v);	
				
        }
		
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
	private void initializeProgressDialog() {
		dialog = new ProgressDialog(this);
		dialog.setMessage("Processing...");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);

	}

}
