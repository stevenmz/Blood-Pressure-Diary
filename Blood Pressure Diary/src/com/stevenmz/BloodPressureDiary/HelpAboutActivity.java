package com.stevenmz.BloodPressureDiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpAboutActivity extends Activity {

	Button btnBloodPressureInformation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_menu);

		GetReferences();

		SetListeners();
	}

	private void SetListeners() {
		btnBloodPressureInformation
				.setOnClickListener(new View.OnClickListener() {

					public void onClick(View arg0) {
						Intent intent = new Intent(HelpAboutActivity.this,
								BpInformationActivity.class);
						startActivity(intent);
					}
				});

	}

	private void GetReferences() {
		btnBloodPressureInformation = (Button) findViewById(R.id.btnBloodPressureInformation);

	}
}
