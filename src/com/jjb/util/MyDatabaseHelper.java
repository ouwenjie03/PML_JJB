package com.jjb.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 15/04/29.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
	private static final String ITEM_TABLE = "item_table";
	private static final int DB_VERSION = 1; // 数据库第一版

	public MyDatabaseHelper(Context context,
			SQLiteDatabase.CursorFactory factory) {
		super(context, ITEM_TABLE, factory, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("create table!");
		db.execSQL("CREATE TABLE `item_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `userid` VARCHAR(255), `name` NVARCHAR(255), `price` DOUBLE, `isout` BIT, `classify` INT, `time` DATE, `isfinish` INT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}