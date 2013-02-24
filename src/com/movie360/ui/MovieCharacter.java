package com.movie360.ui;

import java.util.List;

import com.movie360.dto.AboutTheMovie;
import com.movie360.dto.Character;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class MovieCharacter extends Activity {
	LinearLayout ll;
	AboutTheMovie m;
	List<Character> c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comman_details);
		ll=(LinearLayout) findViewById(R.id.tableLayout1);
		Bundle bundel = getIntent().getExtras();   
        try{   
        	m =  (AboutTheMovie) bundel.get("hashtable");   
            }
        catch(Exception e){
        	Log.i(" Error at bundle " , e.toString());
        } 	
		//list of Character Objects
        c=m.getCharacterList();
		Log.i("raja",""+c.size());	
		for(int i=0;i<c.size();i++){
			TableRow tr = new TableRow(this);
			   TextView tv1= new TextView(this);	
				tv1.setTextSize(20);
			   	tv1.setText(c.get(i).getCharacter());
				tr.addView(tv1);
		
				TextView tv2= new TextView(this);	
				tv2.setTextSize(20);
				tv2.setPadding(10, 0, 0, 0);
				tv2.setText("-"+ c.get(i).getCharacter_person());
				
				tr.addView(tv2);
				ll.addView(tr);
				 View v = new View(this);
				 v.setBackgroundColor(Color.WHITE);
				 v.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,2));
				 ll.addView(v);
		}	
	}
}
