package com.movie360.ui;

import com.movie360.dto.AboutTheMovie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Synopsis  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutmovie_deatails);
		TextView tv=(TextView) findViewById(R.id.tv1);
	  
	}
}
