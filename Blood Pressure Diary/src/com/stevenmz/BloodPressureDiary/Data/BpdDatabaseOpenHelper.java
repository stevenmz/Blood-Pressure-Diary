package com.stevenmz.BloodPressureDiary.Data;

import com.stevenmz.BloodPressureDiary.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BpdDatabaseOpenHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private Context context;

	// Database connection string
	private String DATABASE_CREATE;
	private String TableName;

	public BpdDatabaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, context.getString(R.string.DatabaseName), null,
				DATABASE_VERSION);
		this.context = context;

		TableName = context.getString(R.string.BpDatabaseEntriesTableName);
		this.DATABASE_CREATE = "create table "
				+ TableName
				+ " (_id integer primary key autoincrement, systolic integer, diastolic integer, heart_rate integer, entryDateTime text);";
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		database.execSQL("DROP TABLE IF EXISTS " + TableName);
		onCreate(database);

	}

}
