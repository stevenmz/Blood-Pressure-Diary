package com.stevenmz.BloodPressureDiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewEntriesMenuActivity extends Activity {
	private Button btnViewList, btnViewGraph;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_entries_menu);

		getReferencesToControls();

		setOnClickListerners();
	}

	private void setOnClickListerners() {
		btnViewList.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(ViewEntriesMenuActivity.this,
						ViewEntriesListActivity.class);
				startActivity(intent);
			}
		});
		
		btnViewGraph.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(ViewEntriesMenuActivity.this,
						ViewEntriesGraphedActivity.class);
				startActivity(intent);
			}
		});

	}

	private void getReferencesToControls() {
		btnViewList = (Button) findViewById(R.id.btnViewEntriesAsList);
		btnViewGraph = (Button) findViewById(R.id.btnViewEntriesAsGraph);

	}
}
