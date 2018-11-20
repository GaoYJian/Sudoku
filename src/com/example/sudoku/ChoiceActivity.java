package com.example.sudoku;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ChoiceActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.sudoku.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_choice);
	}

	@Override
	public void onBackPressed() {
		returnTitle(null);
	}

	public void returnTitle(View view) {
		NavUtils.navigateUpFromSameTask(this);
	}

	public void NewGame(View view) {
		Button btn = (Button) findViewById(view.getId());
		Intent it = new Intent();
		if (btn == (Button) findViewById(R.id.easyButton)) {
			it.putExtra(EXTRA_MESSAGE, 1);
		} else if (btn == (Button) findViewById(R.id.mediumButton)) {
			it.putExtra(EXTRA_MESSAGE, 2);
		} else if (btn == (Button) findViewById(R.id.hardButton)) {
			it.putExtra(EXTRA_MESSAGE, 3);
		}
		it.setClass(this, SolveActivity.class);
		startActivity(it);
	}
}
