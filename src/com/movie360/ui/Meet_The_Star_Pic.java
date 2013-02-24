package com.movie360.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.movie360.dto.Meet_Star;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Meet_The_Star_Pic extends Activity implements OnClickListener {
	
	TableLayout ll;
	Meet_Star m;
	TableRow tr;
	ImageButton imageView;
	ProgressDialog  dialog;
	File temp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pic);
		
		initializeProgressDialog();
		
		ll=(TableLayout) findViewById(R.id.picll);
		Bundle b= getIntent().getExtras();
		try{   
        	m =  (Meet_Star) b.get("raja");   
            }
        catch(Exception e){
        	Log.i(" Error at bundle " , e.toString());
        }
		
	    for(int i=0;i<m.getStarpic().size();i++){
        		TableRow tr = new TableRow(this);
        		imageView= new ImageButton(this);
        		imageView.setClickable(false);
        		imageView.setImageResource(R.drawable.game);
        		
        		
        		LinearLayout rl= new LinearLayout(this);
    			imageView.setImageResource(R.drawable.game);
    				rl.addView(imageView);
    				tr.addView(rl);
//    		LinearLayout rl1= new LinearLayout(this);
//    		rl1.setOrientation(LinearLayout.VERTICAL);
//    		//title.setPadding(0, 30, 0, 0);
//    		TextView title= new TextView(this);
//    		title.setText(m.getStarpic().get(i).getName());
//    		title.setTextSize(10);
//    		title.setTextColor(Color.BLUE);
//    		title.setPadding(10, 10, 0, 0);
//    		//title.setPadding(150, 0, 0, 0);
//    		rl1.addView(title);
//    		TextView length = new TextView(this);
//    	//	length.setText(""+m.getStarpic().get(i).getPictureUrl());
//    		length.setTextColor(Color.BLUE);
//    		length.setTextSize(10);
//    		length.setPadding(10, 0, 0, 0);
//    		rl1.addView(length);
//    			
//    		TableRow play= new TableRow(this);
//    			
//    	    	ImageView ib = new ImageView(this);
//    			ib.setImageResource(R.drawable.play);
//    				ib.setOnClickListener(this);
//    			ImageView ib1 = new ImageView(this);
//    			ib1.setImageResource(R.drawable.down);
//    				ib.setOnClickListener(this);
//    		play.addView(ib);
//    		play.addView(ib1);
//    		rl1.addView(play);
//    		tr.addView(rl1);
//    
//    		tr.setId(i);
//    		tr.setOnClickListener(this);
//    		tr.addView(tr);
    		
        	
        	ll.addView(tr);
    		View ruler = new View(this);
    		ruler.setBackgroundColor(Color.BLACK);
            ll.addView(ruler, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 4));
        }
	}
	@Override
	public void onClick(View v) {

			
	
	
	}
	private class DownloadTask extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPreExecute() {
				
				dialog.show();
			
				super.onPreExecute();
			}
			
			@Override
			protected Void doInBackground(Void... params) {
				try{
				
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
			
			}
			
		
	}
	private class StarTask extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPreExecute() {
				
				super.onPreExecute();
			}
			
			@Override
			protected Void doInBackground(Void... params) {
			
				
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
					
				
			super.onPostExecute(result);
			}
		
	}
	
	
	private void initializeProgressDialog() {

		dialog = new ProgressDialog(this);
		dialog.setMessage("Downloading.....");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);

	}
	
	
	private String getDataSource(String path) throws IOException {
			if (!URLUtil.isNetworkUrl(path)) {
				return path;
			} else {
			
			URL url = new URL(path);
			URLConnection cn = url.openConnection();
			cn.connect();
			
			InputStream stream = cn.getInputStream();
			if (stream == null)
				throw new RuntimeException("stream is null");
			 	temp=File.createTempFile("media", "dat");
			 	temp.delete();
			 String tempPath = temp.getAbsolutePath();
			
			 Log.i("FirstLook","paht "+ tempPath);
			 
			 FileOutputStream out = new FileOutputStream(temp);
			 byte buf[] = new byte[128];
			do {
				int numread = stream.read(buf);
				if (numread <= 0)
					break;
				out.write(buf, 0, numread);
			} while (true);
			try {
				stream.close();
			} catch (IOException ex) {
			}
			return tempPath;
		
		}
	}

	

}
