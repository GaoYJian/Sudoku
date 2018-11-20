package com.example.sudoku;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class StatisticsActivity extends TabActivity {

	private TabHost th;
	private Intent SumInfo;
	private Intent EasyInfo;
	private Intent NormalInfo;
	private Intent HardInfo;
	private GestureDetector gestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_statistics);
		th = getTabHost();
		initIntent();
		addSpec();
		gestureDetector = new GestureDetector(this,onGestureListener);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initIntent() {
		SumInfo = new Intent(this, SumActivity.class);
		EasyInfo = new Intent(this, EasyActivity.class);
		NormalInfo = new Intent(this, NormalActivity.class);
		HardInfo = new Intent(this, HardActivity.class);
	}
	
	private void addSpec() {
	    th.addTab(this.buildTagSpec("tab_sum",R.string.title_activity_sum,R.drawable.actionbar_background, SumInfo));
	    th.addTab(this.buildTagSpec("tab_easy",R.string.title_activity_easy,R.drawable.actionbar_background, EasyInfo));
	    th.addTab(this.buildTagSpec("tab_normal",R.string.title_activity_normal,R.drawable.actionbar_background, NormalInfo));
	    th.addTab(this.buildTagSpec("tab_hard", R.string.title_activity_hard,R.drawable.actionbar_background,HardInfo));
	}
	
	private TabHost.TabSpec buildTagSpec(String tagName, int tagLable,
	           int icon, Intent content) {
	       return th
	              .newTabSpec(tagName)
	              .setIndicator(getResources().getString(tagLable),
	                     getResources().getDrawable(icon)).setContent(content);
	    }
	
	private GestureDetector.OnGestureListener onGestureListener = 
			new GestureDetector.SimpleOnGestureListener(){
		@Override
		public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
			float x = e2.getX() - e1.getX();
			float y = e2.getY() - e1.getY();
			
			if(x > 0){
				doResult(0);
			}else if(x<0){
				doResult(1);
			}
			return true;
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event){
		return gestureDetector.onTouchEvent(event);
		//return this.getCurrentActivity().onTouchEvent(event);
	}

	public void doResult(int action){
		int i = th.getCurrentTab();
		switch(action){
		case 1:
			th.setCurrentTab((i+1)%4);
			break;
		case 0:
			th.setCurrentTab(((i-1)%4)>=0?(i-1):3);
			break;
		}
	}

	
}