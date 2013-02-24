package com.movie360.ui;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.List;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.Crew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

public class Cast_And_Crew  extends Activity{
	TableLayout tLayout;	
	 AboutTheMovie m;
	 TextView tv1,tv2;
	 Gallery g;
	 Display dis;
	 int width;
	 int height;
	 ProgressDialog dialog;
	 Integer str[]={R.drawable.about,
			 		R.drawable.about_the_movie,
			 		R.drawable.down};
	 Bitmap d[];
	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			 setContentView(R.layout.cast_and_crew);
			 
			 
			   
			 initializeProgressDialog();
			Bundle bundel = getIntent().getExtras();   
            try{   
            	m =  (AboutTheMovie) bundel.get("hashtable");   
                }
            catch(Exception e){
            	Log.i(" Error at bundle " , e.toString());
            }
            
            d= new Bitmap[m.getCrewList().size()];
            Log.i("Movie360","lenth of  drawable array:"+d.length);
			
           
			g=(Gallery) findViewById(R.id.gallery1);
			tv1=		(TextView) findViewById(R.id.textView1);
			tv2=		(TextView) findViewById(R.id.textView2);
			
			
			
//			Rect rect = new Rect(); 
//			g.getHitRect(rect); 
//			int x = rect.centerX()+getWindowManager().getDefaultDisplay().getWidth();
//			int y = rect.centerY(); 
//			MotionEvent event = MotionEvent.obtain(100, 100, MotionEvent.ACTION_DOWN, x, y, 0);
//			
//			g.onDown(event);
//			
//			boolean res = g.onSingleTapUp(null);
			
			GallaryTask gTask= new GallaryTask();
			gTask.execute();
		}
	private class GallaryTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
		
			for(int i=0;i<m.getCrewList().size();i++){
				d[i]= getImageBitmap(m.getCrewList().get(i).getPhoto());		
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			g.setAdapter(new ImageAdapter(Cast_And_Crew.this));

		}
		
	}
	
	private class ImageAdapter extends BaseAdapter{
		Context mContext;
		public ImageAdapter(Cast_And_Crew cast_And_Crew) {
			mContext=cast_And_Crew;
		}
		@Override
		public int getCount() {
			
			
			return d.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
        	tv1.setText(m.getCrewList().get(arg0).getPerson());
        	
        	tv2.setText(m.getCrewList().get(arg0).getRole());
        	Log.i("width",""+width);
        	Log.i("height",""+height);
        	 dis = ((WindowManager) getSystemService(Cast_And_Crew.WINDOW_SERVICE)).getDefaultDisplay();
			   width=dis.getWidth();
			   height=dis.getHeight();
        	if(width==320&&height==480){
				g.setSpacing(50);
			}
			if(height==320&&height==480){
				g.setSpacing(100);
				
			}
        	//tv3.setText(m.getCrewList().get(arg0).getRole());

			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			  
			   		ImageView i = new ImageView(mContext);
			   		i.setImageBitmap(d[arg0]);
			   		i.setScaleType(ImageView.ScaleType.FIT_XY);
		            i.setLayoutParams(new Gallery.LayoutParams(200, 200));
					
				
			return i;
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
