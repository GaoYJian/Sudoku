package com.example.sudoku;

import java.io.FileInputStream;

import org.apache.http.util.EncodingUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class NormalActivity extends Activity {

	TextView[] tv = new TextView[4];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);
		
		tv[0] = (TextView) findViewById(R.id.textView2);
		tv[1] = (TextView) findViewById(R.id.textView3);
		tv[2] = (TextView) findViewById(R.id.textView5);
		tv[3] = (TextView) findViewById(R.id.textView7);

		tv[0].setText(SumActivity.k[2]);
		tv[1].setText(SumActivity.k[3]);
		GetScore();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.normal, menu);
		return true;
	}

	private void GetScore() {
		try {
			FileInputStream fin = openFileInput("ScoreFile");
			byte[] buffer = new byte[44];
			fin.read(buffer);
			String res = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();
			tv[2].setText(res.substring(12, 17));
			tv[3].setText(res.substring(18, 23));
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
