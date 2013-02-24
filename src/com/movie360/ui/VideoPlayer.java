package com.movie360.ui;


import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayer  extends Activity{
	VideoView myVideoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoplayer);
		myVideoView =(VideoView) findViewById(R.id.surface_view);
		myVideoView.setMediaController(new MediaController(this));
		myVideoView.setVideoURI(Uri.parse("http://daily3gp.com/vids/747.3gp"));
		myVideoView.start();
		myVideoView.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
			
			}
		});
		myVideoView.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
	
			return false;
			}
		});
		
	}
}
