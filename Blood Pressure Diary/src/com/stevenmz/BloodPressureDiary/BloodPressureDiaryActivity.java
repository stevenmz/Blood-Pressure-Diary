package com.stevenmz.BloodPressureDiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.stevenmz.BloodPressureDiary.Data.BpDatabaseAdapter;

/**
 * This Activity will serve as the initialization process for the program.
 * 
 * @author Steven Magana-Zook
 * 
 */
public class BloodPressureDiaryActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		textView.setText("Initializing...");
		setContentView(textView);

		try {
			// Health check. Make sure database is created before program starts
			BpDatabaseAdapter adapter = new BpDatabaseAdapter(this);

			adapter.open();			
			adapter.close();
		} catch (Exception e) {
			textView.setText("Error with database");
			finish();
		}

		Intent goToMainMenu = new Intent(BloodPressureDiaryActivity.this,
				MainMenuActivity.class);

		startActivity(goToMainMenu);

		this.finish();

	}
}