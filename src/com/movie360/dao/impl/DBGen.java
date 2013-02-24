package com.movie360.dao.impl;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBGen {
		Context context;
		//A String to use in Log statements
		String tag="DBGEN";
		// DataBase Name
		private final String My_DATABASE= "MOVIE360";
		 public static final String AS_KEY="KEY";
		    public static final String AS_VALUE="VALUE";
		// DataBase Table Name's
		public final static String My_MOVIE_TABLE= "MOVIE";
		
		
		
		//constants
		public final static int MODE_READ=0;
		public final static int MODE_WRITE=1;
		//SQLite Database Name
		SQLiteDatabase myDB = null;
	
		//Constants for each field of MOVIE Table
	
		public static final String MOVIE_ID= "MOVIE_ID";
		public static final String MOVIE_NAME="TITLE";
		public static final String MOVIE_IMAGE="THUMBNAILURL";
		
		//Constants for each field of VIDEO Table
 		
		//Queries to create the database tables 
		
	    String MOVIE_TABLE_CREATE="CREATE TABLE IF NOT EXISTS MOVIE (MOVIE_ID INT NOT NULL,TITLE VARCHAR,THUMBNAILURL VARCHAR)";
	  	
		
		//Queries to drop  the database tables 
		
		
		String MOVIE_DROP="DROP TABLE IF EXISTS MOVIE";
		DBHelper dbHelper;
		
		private static final String LOG_TAG = "DBGEN";
		
		private static boolean isClosed = false;
		
		private static DBGen dbGen = null;
		public DBGen(Context context)
		{
			this.context=context;
			dbHelper = new DBHelper(context, My_DATABASE, null, 1);
			
		}
		public static DBGen getInstance(Context ctx)
		{
			
			if(dbGen == null || isClosed)
			{
				//Log.i(LOG_TAG, "Creating new Instance");
				dbGen = new DBGen(ctx);
				isClosed = false;
			}
			
			return dbGen;
		}
		public SQLiteDatabase open(int mode)
		{
			// To close the connection before opening a new one.
			if(myDB != null)
			{
				close();
			}
		  	if(mode == MODE_WRITE)
		  	{
		       myDB = dbHelper.getWritableDatabase();
		  	}
		  	else
		  	{
		  		myDB = dbHelper.getReadableDatabase();
		  	}
	      
		  return myDB;
		}
		public SQLiteDatabase open()
		{
			if((myDB == null)|| (!myDB.isOpen()))
			{
				myDB = dbHelper.getWritableDatabase();
			}
			
			return myDB;
		}
		public boolean isDBExists()
		{
			boolean isDBExist=false;
			
			try
			{
				File dbPath=context.getDatabasePath(My_DATABASE);
				isDBExist = dbPath.exists();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.i("DBGEN","ERROR DELETING THE FILE",e);
			}
			
			return isDBExist;
		}
		
		public static void closeCursor(Cursor c)
		{
			
			try
			{
				if(c!=null && !c.isClosed())
				{
					c.close();
				}
			}
			catch(Exception exp)
			{
				Log.e("DBGen","Error Closing Cursor", exp);
			}
		}
		public boolean removeDB()
		{
			boolean isDBClosed=removeHardDB();
			
			return isDBClosed;
		}
		private boolean removeHardDB()
		{
			boolean isDBdeleted = false;
			
			try
			{
				File dbFilePath=context.getDatabasePath(My_DATABASE);
				
				close();
				
				isDBdeleted=dbFilePath.delete();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.e("DBGEN","EXCEPTION RAISED IN DELETING DB");
			}
			
			return isDBdeleted;
		}
		public void close()
		{
			isClosed = true;
			try
			{
				myDB.close();
			}
			catch(SQLException sqlex)
			{
				Log.e("DBGen",sqlex.getMessage(),sqlex);
			}
			try
			{
				dbHelper.close();
			}
			catch(SQLException sqlex)
			{
				Log.e("DBGen",sqlex.getMessage(),sqlex);
			}
		}
		class DBHelper extends SQLiteOpenHelper
		{

			public DBHelper(Context context, String name, CursorFactory factory,
					int version) {
				super(context, name, factory, version);
				
			}

			@Override
			public void onCreate(SQLiteDatabase db) 
			{
	          
				db.execSQL(MOVIE_TABLE_CREATE);
				
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				//Drop Tables
				db.execSQL(MOVIE_DROP);
		}
			
		}
}
