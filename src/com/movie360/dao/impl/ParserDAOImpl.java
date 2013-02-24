package com.movie360.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.movie360.dao.ParserDAO;

public class ParserDAOImpl extends DAOImpl implements ParserDAO  {
	SQLiteDatabase db;
	public ParserDAOImpl(Context ctx) {
		super(ctx);
		db=getWriteDB();
	}
	@Override
	public void insertRow(String str, ContentValues cv) {
		
		db.insert(str, null, cv);
	
	}
		

}
