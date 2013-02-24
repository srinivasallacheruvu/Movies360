package com.movie360.ui;

import com.movie360.dto.Meet_Star;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Meet_The_Star_Intro extends Activity {
	private TextView tv;		
	private Meet_Star m;
	@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.intro);
				Bundle b= getIntent().getExtras();
				try{   
		        	m =  (Meet_Star) b.get("raja");   
		            }
		        catch(Exception e){
		        	Log.i(" Error at bundle " , e.toString());
		        }
				tv=(TextView) findViewById(R.id.introText);
	
				tv.setText(" "+m.getCharacterIntro());
	
	}
}
