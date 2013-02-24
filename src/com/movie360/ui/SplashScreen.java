package com.movie360.ui;


import com.movie360.dto.Movies;
import com.movie360.service.Movie360Service;
import com.movie360.service.impl.Movie360ServiceImpl;
import android.app.Activity;
import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;


public class SplashScreen extends Activity {
	
	private String 	TAG="Movie360";
	private Resources res;
	private Context splashscreenActivity=this;
	private String initialRequest;
	private Movie360Service service;
	private boolean takeforward;
	private ProgressBar prog;
	public static boolean jsonError;
	private Movies m;
	private TelephonyManager tm;
	private String exeptionDetails; 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        prog=(ProgressBar) findViewById(R.id.progressBar1);
        res=splashscreenActivity.getResources();
        tm=(TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        
        // getting the String from res/values/string.xml
        initialRequest=res.getString(R.string.intialRequest);
        Log.i(TAG,"initial Request:"+initialRequest);
        service= new Movie360ServiceImpl(splashscreenActivity);
        
        SplashTask sTask= new SplashTask();
        sTask.execute();
        
    }
	
private class SplashTask extends AsyncTask<Void, Void, Void>{
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		prog.setVisibility(View.VISIBLE);
	}
	@Override
	protected Void doInBackground(Void... params) {
		/*int simState=tm.getSimState();
		if(simState==TelephonyManager.SIM_STATE_ABSENT){
			updateHandler.sendEmptyMessage(1);
	    }
		else{
			try
			{
				m=service.intialRequest(initialRequest);
				takeforward=true;
			}
			catch (Exception e) {
				takeforward =false;
				exeptionDetails=e.toString();
				Log.i("Movie360",exeptionDetails);
			}
			if(!takeforward){
				updateHandler.sendEmptyMessage(0);
			}
		//}
*/	return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		prog.setVisibility(View.INVISIBLE);
		takeforward=true;
		if(takeforward){
			Intent i= new Intent(splashscreenActivity,MoviesList.class);
			//sending Movie Object To Next  Activity	
			i.putExtra("MovieObject", m); 
			startActivity(i);
			finish();
		}
	}
}
Handler updateHandler= new Handler(){
	public void handleMessage(Message msg) {
		if (msg.what == 0) {
			prog.setVisibility(View.INVISIBLE);
			final AlertDialog alert = new AlertDialog.Builder(splashscreenActivity).create();
			alert.setTitle("Network Error");
			alert.setIcon(R.drawable.fb);
			alert.setMessage("Network not Accessible. Please check your network connection");
			alert.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			alert.dismiss();
			finish();
			}
			});
			alert.setCancelable(false);
			alert.show();
		} 
		else if (msg.what == 1) {
			prog.setVisibility(View.INVISIBLE);
			final AlertDialog alert = new AlertDialog.Builder(splashscreenActivity).create();
			alert.setTitle("Network Error");
			alert.setIcon(R.drawable.fb);
			alert.setMessage("SimCard is not Available. Please insert  your simCard");
			alert.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			alert.dismiss();
			finish();
			}
			});
			alert.setCancelable(false);
			alert.show();
			} 
		else if (msg.what == 2) {
			prog.setVisibility(View.INVISIBLE);
			final AlertDialog alert = new AlertDialog.Builder(splashscreenActivity).create();
			alert.setTitle("Server Error");
			alert.setIcon(R.drawable.fb);
			alert.setMessage("server is busy .please try after some time");
			alert.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			alert.dismiss();
			finish();
			}
			});
			alert.setCancelable(false);
			alert.show();
			} 
		};
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			finish();
		}
	return true;
	}


}