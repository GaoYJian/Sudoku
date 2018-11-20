package com.example.sudoku;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SumActivity extends Activity {

	TextView[] tv = new TextView[8];

	public static String[] k =new String[6];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sum);
		tv[0] = (TextView) findViewById(R.id.textView2);
		tv[1] = (TextView) findViewById(R.id.textView5);
		tv[2] = (TextView) findViewById(R.id.textView8);
		tv[3] = (TextView) findViewById(R.id.textView11);
		tv[4] = (TextView) findViewById(R.id.textView3);
		tv[5] = (TextView) findViewById(R.id.textView6);
		tv[6] = (TextView) findViewById(R.id.textView9);
		tv[7] = (TextView) findViewById(R.id.textView12);
		ReadRecord();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.sum, menu);
		return true;
	}

	private boolean ReadRecord() {
		try {
			int[][] count = new int[3][3];
			FileInputStream fin = openFileInput("RecordFile");
			InputStream in = new BufferedInputStream(fin);
			;
			byte[] b = new byte[46];
			in.read(b);
			if (b[0] == 0) {
				return false;
			}
			count[0][0] = (b[0] - '0') * 100 + (b[1] - '0') * 10 + (b[2] - '0');
			count[0][1] = (b[4] - '0') * 10 + (b[5] - '0');
			count[0][2] = (b[7] - '0') * 10 + (b[8] - '0');

			count[1][0] = (b[10] - '0') * 100 + (b[11] - '0') * 10
					+ (b[12] - '0');
			count[1][1] = (b[14] - '0') * 10 + (b[15] - '0');
			count[1][2] = (b[17] - '0') * 10 + (b[18] - '0');

			count[2][0] = (b[20] - '0') * 100 + (b[21] - '0') * 10
					+ (b[22] - '0');
			count[2][1] = (b[24] - '0') * 10 + (b[25] - '0');
			count[2][2] = (b[27] - '0') * 10 + (b[28] - '0');

			int sumSec = count[0][2] + count[1][2] + count[2][2];
			int sumMin = count[0][1] + count[1][1] + count[2][1];
			int sumHou = count[0][0] + count[1][0] + count[2][0];

			String Sec = String.format("%02d", sumSec % 60);
			String Min = String.format("%02d", (sumMin + sumSec / 60) % 60);
			String Hou = String.format("%03d",
					(sumHou + (sumMin + sumSec / 60) / 60) % 60);

			tv[0].setText(Hou + ":" + Min + ":" + Sec);
			tv[1].setText(String.format("%03d", count[0][0]) + ":"
					+ String.format("%02d", count[0][1]) + ":"
					+ String.format("%02d", count[0][2]));
			tv[2].setText(String.format("%03d", count[1][0]) + ":"
					+ String.format("%02d", count[1][1]) + ":"
					+ String.format("%02d", count[1][2]));
			tv[3].setText(String.format("%03d", count[2][0]) + ":"
					+ String.format("%02d", count[2][1]) + ":"
					+ String.format("%02d", count[2][2]));

			int ea = (b[30] - '0') * 1000 + (b[31] - '0') * 100 + (b[32] - '0')
					* 10 + (b[33] - '0');
			int no = (b[35] - '0') * 1000 + (b[36] - '0') * 100 + (b[37] - '0')
					* 10 + (b[38] - '0');
			int ha = (b[40] - '0') * 1000 + (b[41] - '0') * 100 + (b[42] - '0')
					* 10 + (b[43] - '0');

			tv[4].setText(String.format("%04d", ea + no + ha));
			tv[5].setText(String.format("%04d", ea));
			tv[6].setText(String.format("%04d", no));
			tv[7].setText(String.format("%04d", ha));

			in.close();
			fin.close();
			k[0] = tv[1].getText().toString();
			k[1] = tv[5].getText().toString();
			k[2] = tv[2].getText().toString();
			k[3] = tv[6].getText().toString();
			k[4] = tv[3].getText().toString();
			k[5] = tv[7].getText().toString();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		/*
		 * File file = new File("read.txt"); if (!file.exists()) { return false;
		 * } else { try { Scanner input = new Scanner(file); for (int i = 0; i <
		 * n; i++) { input.nextLine(); } for (int i = 0; i < 9; i++) { for (int
		 * j = 0; j < 9; j++) { su[i][j] = input.nextInt(); if (su[i][j] != 0) {
		 * suT[i][j] = true; } } } return true; } catch (FileNotFoundException
		 * ex) { Logger.getLogger(basic.class.getName()).log(Level.SEVERE, null,
		 * ex); return false; } }
		 */
	}

}
