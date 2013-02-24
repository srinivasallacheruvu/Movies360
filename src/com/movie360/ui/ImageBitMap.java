package com.movie360.ui;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageBitMap {
	Resources res;
	public Bitmap getImageBitmap(String str){
		Bitmap bm = null;
		try{URL	url = new URL(str);
		URLConnection conn = url.openConnection();
 		conn.connect();
 		InputStream is = conn.getInputStream();
 		BufferedInputStream bis = new BufferedInputStream(is);
 		   bm=BitmapFactory.decodeStream(bis);
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return bm; 	
	}
}
