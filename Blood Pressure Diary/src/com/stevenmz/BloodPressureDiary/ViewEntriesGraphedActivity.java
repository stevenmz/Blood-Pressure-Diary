package com.stevenmz.BloodPressureDiary;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.stevenmz.BloodPressureDiary.Data.BpDatabaseAdapter;

public class ViewEntriesGraphedActivity extends Activity {

	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

	private XYSeries mCurrentSeries;

	private XYSeriesRenderer mSysRenderer;
	private XYSeriesRenderer mDiaRenderer;
	private XYSeriesRenderer mHrRenderer;

	private GraphicalView mChartView;
	private BpDatabaseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_entries_graph);

		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setLegendTextSize(15);
		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		
		mSysRenderer = new XYSeriesRenderer();
		mSysRenderer.setColor(Color.GREEN);
		mSysRenderer.setPointStyle(PointStyle.SQUARE);
		
		mDiaRenderer = new XYSeriesRenderer();
		mDiaRenderer.setColor(Color.YELLOW);
		mDiaRenderer.setPointStyle(PointStyle.SQUARE);
		
		mHrRenderer = new XYSeriesRenderer();
		mHrRenderer.setColor(Color.RED);
		mHrRenderer.setPointStyle(PointStyle.SQUARE);

		// get data series from database. create series
		XYSeries sSystolic = new XYSeries("Systolic");		
		XYSeries sDiastolic = new XYSeries("Diastolic");
		XYSeries sHeartrate = new XYSeries("Heart Rate");
		adapter = new BpDatabaseAdapter(this);
		adapter.open();

		Cursor cursor = adapter.fetchAllBloodPressureEntries();
		startManagingCursor(cursor);
		cursor.moveToFirst();
		int entryNumber = 0;
		while (cursor.isAfterLast() == false) {
			sSystolic.add(entryNumber, cursor.getInt(1));
			sDiastolic.add(entryNumber, cursor.getInt(2));
			sHeartrate.add(entryNumber, cursor.getInt(3));
			cursor.moveToNext();
			entryNumber++;
		}

		mDataset.addSeries(sSystolic);
		mDataset.addSeries(sDiastolic);
		mDataset.addSeries(sHeartrate);

		mRenderer.addSeriesRenderer(mSysRenderer);
		mRenderer.addSeriesRenderer(mDiaRenderer);
		mRenderer.addSeriesRenderer(mHrRenderer);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.llForGraph);
			mChartView = ChartFactory.getLineChartView(this, mDataset,
					mRenderer);
			layout.addView(mChartView, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		} else {
			mChartView.repaint();
		}
	}

}
