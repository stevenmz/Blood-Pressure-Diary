package com.stevenmz.BloodPressureDiary;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.stevenmz.BloodPressureDiary.Data.BpDatabaseAdapter;

public class AddNewBpEntryActivity extends Activity {
	private EditText txtSystolic, txtDiastolic, txtHeartRate;
	private TextView txtEntryDate, txtEntryTime;
	private Button btnAddEntry, btnChangeEntryDate, btnChangeEntryTime;
	private int mMonth;
	private int mDay;
	private int mYear;
	private int mHour;
	private int mMinute;
	private DatePickerDialog.OnDateSetListener mDateSetListener;
	private TimePickerDialog.OnTimeSetListener mTimeSetListener;
	private TextView txtStatus;
	private static final int DATE_DIALOG_ID = 0;
	private static final int TIME_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_bp_entry);

		getReferencesToControls();

		setCallBacks();

		setInitialValues();
	}

	private void setInitialValues() {
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getDefault());
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR);
		mMinute = c.get(Calendar.MINUTE);

		updateDisplay();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// return super.onCreateDialog(id);

		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);
		default:
			break;
		}

		return null;
	}

	private void saveEntryToDatabase() {
		BpDatabaseAdapter adapter;
		try {
			txtStatus.setText(this.getString(R.string.bp_saving_entry));
			adapter = new BpDatabaseAdapter(this);

			adapter.open();

			Date entryDate = new Date(mYear, mMonth, mDay, mHour, mMinute);
			int systolic = Integer.parseInt(txtSystolic.getText().toString());
			int diastolic = Integer.parseInt(txtDiastolic.getText().toString());
			int heartRate = Integer.parseInt(txtHeartRate.getText().toString());

			adapter.createBloodPressureEntry(systolic, diastolic, heartRate,
					entryDate);

			adapter.close();

			txtStatus.setText(this.getString(R.string.bp_entry_saved));
		} catch (Exception e) {
			txtStatus.setText(this.getString(R.string.bp_entry_error));
		}

	}

	private void setCallBacks() {
		btnAddEntry.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveEntryToDatabase();
			}

		});

		btnChangeEntryDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);

			}
		});

		btnChangeEntryTime.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		mDateSetListener = new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear;
				mDay = dayOfMonth;
				updateDisplay();
			}

		};

		mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mHour = hourOfDay;
				mMinute = minute;

				updateDisplay();
			}
		};
	}

	private void updateDisplay() {
		txtEntryDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("/").append(mDay).append("/")
				.append(mYear).append(" "));

		txtEntryTime.setText(new StringBuilder().append(mHour).append(":")
				.append(pad(mMinute)));
	}

	private static String pad(int number) {
		if (number < 10) {
			return "0" + number;
		} else
			return "" + number;
	}

	private void getReferencesToControls() {
		btnAddEntry = (Button) findViewById(R.id.btnAddEntry);
		btnChangeEntryDate = (Button) findViewById(R.id.btnChangeEntryDate);
		btnChangeEntryTime = (Button) findViewById(R.id.btnChangeEntryTime);

		txtEntryDate = (TextView) findViewById(R.id.txtEntryDate);
		txtEntryTime = (TextView) findViewById(R.id.txtEntryTime);// txtStatus
		txtStatus = (TextView) findViewById(R.id.txtStatus);

		txtSystolic = (EditText) findViewById(R.id.txtSystolic);
		txtDiastolic = (EditText) findViewById(R.id.txtDiastolic);
		txtHeartRate = (EditText) findViewById(R.id.txtHeartRate);
	}
}
