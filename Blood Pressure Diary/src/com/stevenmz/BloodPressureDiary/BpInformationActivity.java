package com.stevenmz.BloodPressureDiary;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class BpInformationActivity extends Activity {
	TextView txtViewSeeMore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bp_information);
		
		txtViewSeeMore = (TextView)findViewById(R.id.txtHelpSeeMore);
		txtViewSeeMore.setText(
				Html.fromHtml(getString(R.string.help_see_more_info) + "<a href=\""+getString(R.string.bpMoreInformationLink)+"\">Mayo Clinic</a>"));
		
	}
}
