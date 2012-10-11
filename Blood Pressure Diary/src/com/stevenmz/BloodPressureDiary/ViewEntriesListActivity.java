package com.stevenmz.BloodPressureDiary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

import com.stevenmz.BloodPressureDiary.Data.BpDatabaseAdapter;

public class ViewEntriesListActivity extends ListActivity {
	private BpDatabaseAdapter adapter;

	static final ViewBinder viewBinder = new ViewBinder() {

		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			if (view.getId() != R.id.txtEntryListDate) {
				return false;
			}

			((TextView) view).setText(cursor.getString(columnIndex));

			return true;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_entries_list);

		adapter = new BpDatabaseAdapter(this);
		adapter.open();

		Cursor cursor = adapter.fetchAllBloodPressureEntries();
		startManagingCursor(cursor);

		// The columns to be displayed
		String[] columns = new String[] { "systolic", "diastolic",
				"heart_rate", "entryDateTime" };
		int[] to = new int[] { R.id.txtEntryListSystolic,
				R.id.txtEntryListDiastolic, R.id.txtEntryListHeartRate,
				R.id.txtEntryListDate };
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,
				R.layout.entries_list_item, cursor, columns, to);

		mAdapter.setViewBinder(viewBinder);

		this.setListAdapter(mAdapter);
	}
}
