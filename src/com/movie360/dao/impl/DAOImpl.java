package com.movie360.dao.impl;

import com.movie360.dao.DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class DAOImpl  implements DAO{
	
	Context ctx;
DBGen dbGen = null;
public DAOImpl(Context ctx)
{
	this.ctx = ctx;
	dbGen = DBGen.getInstance(ctx);
}

public SQLiteDatabase getReadDB()
{
	return dbGen.open();
}

public SQLiteDatabase getWriteDB()
{	
	return dbGen.open();
	
}

public void close()
{
	try
	{
		dbGen.close();
	}
	catch(SQLException sqlEx)
	{
		Log.e(DAOImpl.class.getName(), "Exception while closing connection",sqlEx);
	}
}}
