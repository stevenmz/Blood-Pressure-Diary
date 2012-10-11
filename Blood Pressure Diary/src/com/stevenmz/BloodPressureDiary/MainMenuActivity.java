package com.stevenmz.BloodPressureDiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {
	private Button btnEnterNewReading;
	private Button btnViewReadings;
	private Button btnHelpAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		getReferencesToControls();

		setOnClickListerners();
	}

	private void setOnClickListerners() {
		btnHelpAbout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent goToHelpIntent = new Intent(MainMenuActivity.this,
						HelpAboutActivity.class);
				startActivity(goToHelpIntent);
			}
		});

		btnEnterNewReading.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent goToAddEntryIntent = new Intent(MainMenuActivity.this,
						AddNewBpEntryActivity.class);
				startActivity(goToAddEntryIntent);
			}
		});

		btnViewReadings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent goToViewEntries = new Intent(MainMenuActivity.this,
						ViewEntriesMenuActivity.class);
				startActivity(goToViewEntries);
			}
		});
	}

	private void getReferencesToControls() {
		btnEnterNewReading = (Button) findViewById(R.id.btnEnterNewReading);
		btnViewReadings = (Button) findViewById(R.id.btnViewReadings);
		btnHelpAbout = (Button) findViewById(R.id.btnHelpAbout);
	}

}
