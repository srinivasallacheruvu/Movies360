package com.movie360.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CharacterIntroduction  extends Activity{
	RelativeLayout relativeLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.character_introduction);
			relativeLayout=(RelativeLayout) findViewById(R.id.ciLayout);
			
			TextView tv = new  TextView(this);
			tv.setText("");
			
	}
}
