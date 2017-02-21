package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe(); 
	}

	void addResult(String username, int score) {
		db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score
				+ ");");
	}

	ArrayList<Result> getAllResults() {

		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS;", null);
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}
	int count(){
		Cursor cursor = db.rawQuery("SELECT COUNT(*)  FROM RESULTS;", null);
		cursor.moveToFirst();
		int c = cursor.getInt(0);
		return c;
	}
	int sum(){
		Cursor cursor = db.rawQuery("SELECT SUM(RESULTS.SCORE)   FROM RESULTS;", null);
		cursor.moveToFirst();
		int s = cursor.getInt(0);
		return s;
	}
	int max(){
		Cursor cursor = db.rawQuery("SELECT MAX(RESULTS.SCORE)   FROM RESULTS;", null);
		cursor.moveToFirst();
		int m = cursor.getInt(0);
		return m;
	}
	int dbln(){
		Cursor cursor = db.rawQuery("SELECT COUNT(DISTINCT(RESULTS.USERNAME))   FROM RESULTS;", null);
		cursor.moveToFirst();
		int d = cursor.getInt(0);
		return d;
	}
	int even(){
		Cursor cursor = db.rawQuery("SELECT COUNT(RESULTS.SCORE)    FROM RESULTS WHERE RESULTS.SCORE%2=0;", null);
		cursor.moveToFirst();
		int e = cursor.getInt(0);
		return e;
	}
	int odd(){
		Cursor cursor = db.rawQuery("SELECT COUNT(RESULTS.SCORE)    FROM RESULTS WHERE RESULTS.SCORE%2=1;", null);
		cursor.moveToFirst();
		int o = cursor.getInt(0);
		return o;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}

	private boolean dbExist() {
		File dbFile = context.getDatabasePath(DB_NAME);
		return dbFile.exists();
	}

}
