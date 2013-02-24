package com.movie360.ui;

import com.movie360.dto.Meet_Star;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Meet_The_Star_Videos extends Activity implements OnClickListener {
	LinearLayout ll;
	Meet_Star m;
	TableRow tr;
	ImageButton imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.meet_the_star_intro);
//		ll=(LinearLayout) findViewById(R.id.mLL);
		
		setContentView(R.layout.video);
		ll=(LinearLayout) findViewById(R.id.llTrailer);
		Bundle b= getIntent().getExtras();
		try{   
        	m =  (Meet_Star) b.get("raja");   
            }
        catch(Exception e){
        	Log.i(" Error at bundle " , e.toString());
        }
	
        for(int i=0;i<m.getStarpic().size();i++){
        	tr = new TableRow(this);
        	imageView= new ImageButton(this);
         	imageView.setClickable(false);
        	
         	LinearLayout rl= new LinearLayout(this);
    		imageView.setImageResource(R.drawable.game);
    		rl.addView(imageView);
    		tr.addView(rl);
    	
    		LinearLayout rl1= new LinearLayout(this);
    		rl1.setOrientation(LinearLayout.VERTICAL);
    		//title.setPadding(0, 30, 0, 0);
    		TextView title= new TextView(this);
    	//	title.setText(m.getStarpic().get(i).getPackageId());
    		title.setTextSize(10);
    		title.setTextColor(Color.BLUE);
    		title.setPadding(10, 10, 0, 0);
    		//title.setPadding(150, 0, 0, 0);
    		rl1.addView(title);
    		TextView length = new TextView(this);
    	//	length.setText(""+m.getStarpic().get(i).getPictureUrl());
    		length.setTextColor(Color.BLUE);
    		length.setTextSize(10);
    		length.setPadding(10, 0, 0, 0);
    		rl1.addView(length);
    		
    		ImageView ib = new ImageView(this);
			ib.setImageResource(R.drawable.play);
			
			ImageView ib1 = new ImageView(this);
			ib1.setImageResource(R.drawable.down);
			
		rl1.addView(ib);
		rl1.addView(ib1);
    		tr.addView(rl1);
    		tr.setId(i);
    		tr.setOnClickListener(this);
    		//tr.addView(tr);
    		ll.addView(tr);
    		View ruler = new View(this);
    		ruler.setBackgroundColor(Color.BLACK);
            ll.addView(ruler, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 4));
    	
    		
    		
        }
	
		
		
	
     	
		
		
	
	
	
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
