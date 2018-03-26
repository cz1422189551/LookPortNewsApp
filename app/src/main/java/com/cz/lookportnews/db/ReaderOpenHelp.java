package com.cz.lookportnews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ReaderOpenHelp extends SQLiteOpenHelper {

	private static final String News="create  table  news( "
			+ "id  integer primary key   ,"
			+ " title          text,  "
			+ " time      text,"
			+ " imgUrl      text,"
			+ " pageSource      text,"
			+ " editor      text,"
			+ " content     text, "
			+ "orign      text)";

	private static final String readed="create  table  readed( "
			+ "id  integer primary key   ,"
			+ " title          text,  "
			+ " time          integer,  "
			+ "orign      text)";


	public ReaderOpenHelp(Context context, String name, CursorFactory factory,
                          int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(News);
		db.execSQL(readed);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
