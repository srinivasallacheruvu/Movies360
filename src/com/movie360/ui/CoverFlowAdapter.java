package com.movie360.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CoverFlowAdapter extends BaseAdapter {
	int width=0;
	int height=0;
	private List<Bitmap> images = new ArrayList<Bitmap>();
	private Context context;
	private TextView t;
	private List<String > lName= new ArrayList<String>();
	public CoverFlowAdapter(Context context,List<Bitmap> images,TextView t,List<String > lName){
		 
		this.width=150;
		this.height=150;
		this.images=images;
		this.context=context;
		this.t=t;
		this.lName=lName;
		
	}
	
	public CoverFlowAdapter(Context context,List<Bitmap> images,int width,int height){
		this.width=width;
		this.height=height;
		this.images=images;
		this.context=context;
	}
	
	public int getCount() {
		return this.images.size();
	}

	public Bitmap getItem(int position) {
		return this.images.get(position);
	}

	public long getItemId(int position) {
		  t.setText(""+lName.get(position));
		  t.setTextSize(30);
		return position;
	}

	public ImageView getView(int position, View convertView, ViewGroup parent) {
		Bitmap img=createReflectedImages(position);
		ImageView i = new ImageView(context);
        i.setImageBitmap(img);
        i.setLayoutParams(new CoverFlow.LayoutParams(width, height));
        i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        drawable.setAntiAlias(true);
        return i;
	}
	
	public float getScale(boolean focused, int offset) { 
        return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
    } 

	public Bitmap createReflectedImages(int position) {
        //The gap we want between the reflection and the original image
        final int reflectionGap = 0;

        Bitmap originalImage = getItem(position);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
   
        //This will not scale but will flip on the Y axis
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
         
        //Create a Bitmap with the flip matrix applied to it.
        //We only want the bottom half of the image
       Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);
        //Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height, width, height, matrix, false);
         
             
        //Create a new bitmap with same width but taller to fit reflection
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height/2), Config.ARGB_8888);
       
        //Create a new Canvas with the bitmap that's big enough for
        //the image plus gap plus reflection
        Canvas canvas = new Canvas(bitmapWithReflection);
        //Draw in the original image
        canvas.drawBitmap(originalImage, 0, 0, null);
        //Draw in the gap
        Paint deafaultPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
        //Draw in the reflection
        canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);
        
        //Create a shader that is a linear gradient that covers the reflection
        Paint paint = new Paint(); 
        LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP); 
        //Set the paint to use this shader (linear gradient)
        paint.setShader(shader); 
        //Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
        //Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint); 
        
        return bitmapWithReflection;
	}
	
}
