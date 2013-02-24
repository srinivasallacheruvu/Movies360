package com.movie360.dao;
import android.database.sqlite.SQLiteDatabase;

public interface DAO {
	public SQLiteDatabase getReadDB();
	public SQLiteDatabase getWriteDB();
	public void close();
}
