package com.movie360.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class AboutMovie_Detalis  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.aboutmovie_deatails);
			TextView tv=(TextView) findViewById(R.id.tv1);
			SharedPreferences myPrefs = this.getSharedPreferences("Movie360", 2);
		 	String	LatestMovieId =myPrefs.getString("title", null);
			tv.setText(LatestMovieId);
	}
}
