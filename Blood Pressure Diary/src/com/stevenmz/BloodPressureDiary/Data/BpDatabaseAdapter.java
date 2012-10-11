package com.stevenmz.BloodPressureDiary.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stevenmz.BloodPressureDiary.R;

public class BpDatabaseAdapter {

	// Database fields
	public  String KEY_ROWID = "_id";
	public  String KEY_systolic = "systolic";
	public  String KEY_diastolic = "diastolic";
	public  String KEY_heart_rate = "heart_rate";
	public  String KEY_entryDateTime = "entryDateTime";
	private  String DATABASE_TABLE;
	private Context context;
	private SQLiteDatabase database;
	private BpdDatabaseOpenHelper dbHelper;

	public BpDatabaseAdapter(Context context) {

		this.context = context;
		DATABASE_TABLE = this.context.getString(R.string.BpDatabaseEntriesTableName);
	}

	public BpDatabaseAdapter open() throws SQLException {
		dbHelper = new BpdDatabaseOpenHelper(context,"",null,1);
		try
		{
			database = dbHelper.getWritableDatabase();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Create a new bloodPressureEntry. If the bloodPressureEntry is successfully created return the new
	 * rowId, otherwise return a -1 to indicate failure.
	 */
	public long createBloodPressureEntry(int systolic, int diastolic, int heartRate, Date entryDate) {
		ContentValues initialValues = createContentValues(systolic, diastolic, heartRate, entryDate);

		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Update the bloodPressureEntry
	 */
	public boolean updateBloodPressureEntry(long rowId, int systolic, int diastolic, int heartRate, Date entryDate) {
		ContentValues updateValues = createContentValues(systolic, diastolic, heartRate, entryDate);

		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	/**
	 * Deletes BloodPressureEntry
	 */
	public boolean deleteBloodPressureEntry(long rowId) {
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all BloodPressureEntry in the database
	 * 
	 * @return Cursor over all BloodPressureEntry
	 */
	public Cursor fetchAllBloodPressureEntries() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_systolic, KEY_diastolic, KEY_heart_rate, KEY_entryDateTime}, null, null, null,
				null, null);
	}

	/**
	 * Return a Cursor positioned at the defined BloodPressureEntry
	 */
	public Cursor fetchBloodPressureEntry(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
				KEY_systolic, KEY_diastolic, KEY_heart_rate, KEY_entryDateTime},
				KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(int systolic, int diastolic, int heartRate, Date entryDate) {
		ContentValues values = new ContentValues();
		String entryDateString = DateFormat.getDateTimeInstance(
	            DateFormat.SHORT, DateFormat.SHORT).format(entryDate);
		values.put(KEY_systolic, systolic);
		values.put(KEY_diastolic, diastolic);
		values.put(KEY_heart_rate, heartRate);
		values.put(KEY_entryDateTime, entryDateString);
		return values;
	}
}
