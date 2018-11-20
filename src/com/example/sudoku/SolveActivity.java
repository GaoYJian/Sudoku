package com.example.sudoku;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NavUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SolveActivity extends Activity {

	private TextView[][] ArraySudoku = new TextView[9][9];

	private Button[] ArrayButton = new Button[10];

	private basic b = new basic();
	
	private Sounds sound;
	
	int lev = 0;

	long t1,t2;
	
	boolean isPause,isHidden;
	
	private GestureDetector mGestureDetector;

	private Chronometer ch;

	int[] Active = new int[2];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		lev = intent.getIntExtra(ChoiceActivity.EXTRA_MESSAGE, 0);

		//ab = getActionBar();
		//SetTitle(ab, lev);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_solve);
		InitializeArray();
		InitializeFile(lev);
		AcitveButton();
		ch = (Chronometer) findViewById(R.id.chronometer1);
		t1 = SystemClock.elapsedRealtime();
		ch.start();
		
		Active[0] = -1;
		Active[1] = -1;//choseen grid
		isPause = false;
		isHidden = true;
		mGestureDetector = new GestureDetector(this,otl);
		try {
			FileInputStream fin = openFileInput("Setting");
			sound = new Sounds(this,fin);
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	protected void onPause(){
		super.onPause();
		Pause(null);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solve, menu);
		return true;
	}

	public void ReturnAbove(View view) {
		if (!b.checkOver()) {
			new AlertDialog.Builder(this).setTitle("确认")
					.setMessage("退出将不保存到现在的游戏记录，确定么？")
					.setPositiveButton("是", new OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							UpForm();
						}

					}).setNegativeButton("否", null).show();
		} else {
			UpForm();
		}
	}

	private void UpForm() {
		NavUtils.navigateUpFromSameTask(this);
	}

	private void InitializeArray() {
		ArraySudoku[0][0] = (TextView) findViewById(R.id.R1L1);
		ArraySudoku[0][1] = (TextView) findViewById(R.id.R1L2);
		ArraySudoku[0][2] = (TextView) findViewById(R.id.R1L3);
		ArraySudoku[0][3] = (TextView) findViewById(R.id.R1L4);
		ArraySudoku[0][4] = (TextView) findViewById(R.id.R1L5);
		ArraySudoku[0][5] = (TextView) findViewById(R.id.R1L6);
		ArraySudoku[0][6] = (TextView) findViewById(R.id.R1L7);
		ArraySudoku[0][7] = (TextView) findViewById(R.id.R1L8);
		ArraySudoku[0][8] = (TextView) findViewById(R.id.R1L9);

		ArraySudoku[1][0] = (TextView) findViewById(R.id.R2L1);
		ArraySudoku[1][1] = (TextView) findViewById(R.id.R2L2);
		ArraySudoku[1][2] = (TextView) findViewById(R.id.R2L3);
		ArraySudoku[1][3] = (TextView) findViewById(R.id.R2L4);
		ArraySudoku[1][4] = (TextView) findViewById(R.id.R2L5);
		ArraySudoku[1][5] = (TextView) findViewById(R.id.R2L6);
		ArraySudoku[1][6] = (TextView) findViewById(R.id.R2L7);
		ArraySudoku[1][7] = (TextView) findViewById(R.id.R2L8);
		ArraySudoku[1][8] = (TextView) findViewById(R.id.R2L9);

		ArraySudoku[2][0] = (TextView) findViewById(R.id.R3L1);
		ArraySudoku[2][1] = (TextView) findViewById(R.id.R3L2);
		ArraySudoku[2][2] = (TextView) findViewById(R.id.R3L3);
		ArraySudoku[2][3] = (TextView) findViewById(R.id.R3L4);
		ArraySudoku[2][4] = (TextView) findViewById(R.id.R3L5);
		ArraySudoku[2][5] = (TextView) findViewById(R.id.R3L6);
		ArraySudoku[2][6] = (TextView) findViewById(R.id.R3L7);
		ArraySudoku[2][7] = (TextView) findViewById(R.id.R3L8);
		ArraySudoku[2][8] = (TextView) findViewById(R.id.R3L9);

		ArraySudoku[3][0] = (TextView) findViewById(R.id.R4L1);
		ArraySudoku[3][1] = (TextView) findViewById(R.id.R4L2);
		ArraySudoku[3][2] = (TextView) findViewById(R.id.R4L3);
		ArraySudoku[3][3] = (TextView) findViewById(R.id.R4L4);
		ArraySudoku[3][4] = (TextView) findViewById(R.id.R4L5);
		ArraySudoku[3][5] = (TextView) findViewById(R.id.R4L6);
		ArraySudoku[3][6] = (TextView) findViewById(R.id.R4L7);
		ArraySudoku[3][7] = (TextView) findViewById(R.id.R4L8);
		ArraySudoku[3][8] = (TextView) findViewById(R.id.R4L9);

		ArraySudoku[4][0] = (TextView) findViewById(R.id.R5L1);
		ArraySudoku[4][1] = (TextView) findViewById(R.id.R5L2);
		ArraySudoku[4][2] = (TextView) findViewById(R.id.R5L3);
		ArraySudoku[4][3] = (TextView) findViewById(R.id.R5L4);
		ArraySudoku[4][4] = (TextView) findViewById(R.id.R5L5);
		ArraySudoku[4][5] = (TextView) findViewById(R.id.R5L6);
		ArraySudoku[4][6] = (TextView) findViewById(R.id.R5L7);
		ArraySudoku[4][7] = (TextView) findViewById(R.id.R5L8);
		ArraySudoku[4][8] = (TextView) findViewById(R.id.R5L9);

		ArraySudoku[5][0] = (TextView) findViewById(R.id.R6L1);
		ArraySudoku[5][1] = (TextView) findViewById(R.id.R6L2);
		ArraySudoku[5][2] = (TextView) findViewById(R.id.R6L3);
		ArraySudoku[5][3] = (TextView) findViewById(R.id.R6L4);
		ArraySudoku[5][4] = (TextView) findViewById(R.id.R6L5);
		ArraySudoku[5][5] = (TextView) findViewById(R.id.R6L6);
		ArraySudoku[5][6] = (TextView) findViewById(R.id.R6L7);
		ArraySudoku[5][7] = (TextView) findViewById(R.id.R6L8);
		ArraySudoku[5][8] = (TextView) findViewById(R.id.R6L9);

		ArraySudoku[6][0] = (TextView) findViewById(R.id.R7L1);
		ArraySudoku[6][1] = (TextView) findViewById(R.id.R7L2);
		ArraySudoku[6][2] = (TextView) findViewById(R.id.R7L3);
		ArraySudoku[6][3] = (TextView) findViewById(R.id.R7L4);
		ArraySudoku[6][4] = (TextView) findViewById(R.id.R7L5);
		ArraySudoku[6][5] = (TextView) findViewById(R.id.R7L6);
		ArraySudoku[6][6] = (TextView) findViewById(R.id.R7L7);
		ArraySudoku[6][7] = (TextView) findViewById(R.id.R7L8);
		ArraySudoku[6][8] = (TextView) findViewById(R.id.R7L9);

		ArraySudoku[7][0] = (TextView) findViewById(R.id.R8L1);
		ArraySudoku[7][1] = (TextView) findViewById(R.id.R8L2);
		ArraySudoku[7][2] = (TextView) findViewById(R.id.R8L3);
		ArraySudoku[7][3] = (TextView) findViewById(R.id.R8L4);
		ArraySudoku[7][4] = (TextView) findViewById(R.id.R8L5);
		ArraySudoku[7][5] = (TextView) findViewById(R.id.R8L6);
		ArraySudoku[7][6] = (TextView) findViewById(R.id.R8L7);
		ArraySudoku[7][7] = (TextView) findViewById(R.id.R8L8);
		ArraySudoku[7][8] = (TextView) findViewById(R.id.R8L9);

		ArraySudoku[8][0] = (TextView) findViewById(R.id.R9L1);
		ArraySudoku[8][1] = (TextView) findViewById(R.id.R9L2);
		ArraySudoku[8][2] = (TextView) findViewById(R.id.R9L3);
		ArraySudoku[8][3] = (TextView) findViewById(R.id.R9L4);
		ArraySudoku[8][4] = (TextView) findViewById(R.id.R9L5);
		ArraySudoku[8][5] = (TextView) findViewById(R.id.R9L6);
		ArraySudoku[8][6] = (TextView) findViewById(R.id.R9L7);
		ArraySudoku[8][7] = (TextView) findViewById(R.id.R9L8);
		ArraySudoku[8][8] = (TextView) findViewById(R.id.R9L9);

		ArrayButton[0] = (Button) findViewById(R.id.btn0);
		ArrayButton[1] = (Button) findViewById(R.id.btn1);
		ArrayButton[2] = (Button) findViewById(R.id.btn2);
		ArrayButton[3] = (Button) findViewById(R.id.btn3);
		ArrayButton[4] = (Button) findViewById(R.id.btn4);
		ArrayButton[5] = (Button) findViewById(R.id.btn5);
		ArrayButton[6] = (Button) findViewById(R.id.btn6);
		ArrayButton[7] = (Button) findViewById(R.id.btn7);
		ArrayButton[8] = (Button) findViewById(R.id.btn8);
		ArrayButton[9] = (Button) findViewById(R.id.btn9);
	}

	private void InitializeFile(int n) {
		try {
			FileInputStream fin = null;
			InputStream in = null;
			switch (n) {
			case 1:
				// in = getResources().getAssets().open("EasyMode");
				fin = openFileInput("EasyMode");
				break;
			case 2:
				// in = getResources().getAssets().open("NormalMode");
				fin = openFileInput("NormalMode");
				break;
			case 3:
				// in = getResources().getAssets().open("HardMode");
				fin = openFileInput("HardMode");
				break;
			}
			Random r = new Random();
			in = new BufferedInputStream(fin);
			while (!b.read(in, r.nextInt(10)));
			setProblem();
			in.close();
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setProblem(){
		int[][] op = b.getSu();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (op[i][j] != 0) {
					ArraySudoku[i][j].setText(String.valueOf(op[i][j]));
					ArraySudoku[i][j].setClickable(false);
				}
				else{
					ArraySudoku[i][j].setText("");
					ArraySudoku[i][j].setClickable(true);
				}
				ArraySudoku[i][j].setTextColor(Color.BLACK);
			}
		}
	}
	
	public void Focus(View view) {
		if (Active[0] >= 0 && Active[1] >= 0) {
			if ((Active[0] + Active[1]) % 2 == 1) {
				ArraySudoku[Active[0]][Active[1]].setBackgroundColor(Color.rgb(
						195, 195, 195));
			} else {
				ArraySudoku[Active[0]][Active[1]].setBackgroundColor(Color.rgb(
						255, 255, 255));
			}
		}
		TextView FocusOn = (TextView) findViewById(view.getId());
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (FocusOn == ArraySudoku[i][j]) {
					Active[0] = i;
					Active[1] = j;
					break;
				}
			}
		}
		if (!b.getSuT()[Active[0]][Active[1]]) {
			b.setActive(Active);
			FocusOn.setBackgroundColor(Color.rgb(113, 208, 255));
		}

	}

	public void NumberClick(View view) {
		if (Active[0] >= 0 && Active[1] >= 0) {
			Button bt = (Button) findViewById(view.getId());
			int k = -1;
			for (int i = 0; i < 10; i++) {
				if (bt == ArrayButton[i]) {
					k = i;
					break;
				}
			}
			if (k == 0) {
				sound.CancelSound();
				ArraySudoku[Active[0]][Active[1]].setText("");
				b.setSu(Active[0], Active[1], 0);
				boolean[][] tmp = b.getSuT();
				int[] tmpa = b.getActive();
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (!tmp[i][j]) {
							b.setActive(new int[] { i, j });
							if (b.checkSudoku()) {
								ArraySudoku[i][j].setTextColor(Color.BLUE);
							}
						}
					}
				}
				b.setActive(tmpa);
			} else {
				sound.TapSound();
				if (b.checkSudoku(Active[0], Active[1], k)) {
					ArraySudoku[Active[0]][Active[1]].setTextColor(Color.BLUE);
					ArraySudoku[Active[0]][Active[1]]
							.setText(String.valueOf(k));
				} else {
					ArraySudoku[Active[0]][Active[1]].setTextColor(Color.RED);
					ArraySudoku[Active[0]][Active[1]]
							.setText(String.valueOf(k));
				}
				b.setSu(Active[0], Active[1], k);
				if(b.checkRowO(Active[0])||b.checkColO(Active[1])||b.checkGridO(Active[0], Active[1])){
					sound.NineSound();
				}
				if (b.checkOver()) {
					LockButton();
					//ab.setTitle("游戏结束，请返回");
					Success();
					Success2();
					ch.stop();
					new AlertDialog.Builder(this)
							.setTitle("恭喜你！")
							.setMessage("完成游戏，耗时" + ch.getText().toString())
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialoginterface,
												int i) {ReturnAbove(null);
										}
									}).show();	
				}
				
			}
		}
	}

	public void SolveProblem(View view) {
		new AlertDialog.Builder(this).setTitle("确认")
				.setMessage("直接解题将不会记录入成绩，确定么？")
				.setPositiveButton("是", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						Sou();
						//ab.setTitle("游戏结束，请返回");
						// Success();
					}

				}).setNegativeButton("否", null).show();
	}

	private void Sou() {
		int[][] d = b.getSu();
		boolean[][] dt = b.getSuT();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!dt[i][j]) {
					b.setSu(i, j, 0);
				}
			}
		}
		b.solution(0, 0);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!dt[i][j]) {
					ArraySudoku[i][j].setTextColor(Color.BLUE);
					ArraySudoku[i][j].setText(String.valueOf(d[i][j]));
				} else {
					ArraySudoku[i][j].setTextColor(Color.BLACK);
					ArraySudoku[i][j].setText(String.valueOf(d[i][j]));
				}
			}
		}
		if (b.checkOver()) {
			new AlertDialog.Builder(this).setTitle("确认")
					.setMessage("游戏已完成！请返回").setPositiveButton("确定", null)
					.show();

		}
		ch.stop();
		LockButton();
	}

	private void LockButton() {
		for (int i = 0; i < ArrayButton.length; i++) {
			ArrayButton[i].setClickable(false);
		}
		Button b = (Button) findViewById(R.id.btnS);
		b.setClickable(false);
	}

	private void AcitveButton() {
		for (int i = 0; i < ArrayButton.length; i++) {
			ArrayButton[i].setClickable(true);
		}
		Button b = (Button) findViewById(R.id.btnS);
		b.setClickable(true);
	}

	private void Success() {
		String res="";
		try {
			FileInputStream fin = openFileInput("RecordFile");
			byte[] buffer = new byte[44];
			fin.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
			int[] AddData = new int[2];
			String output = "";
			String[] e = ch.getText().toString().split(":");
			int sumSec, sumMin, sumHou;
			String Sec, Min, Hou, count;

			AddData[0] = Integer.parseInt(e[0]);
			AddData[1] = Integer.parseInt(e[1]);

			int[] setData = new int[4];
			switch (lev) {
			case 1:
				setData[0] = Integer.parseInt(res.substring(0, 3));
				setData[1] = Integer.parseInt(res.substring(4, 6));
				setData[2] = Integer.parseInt(res.substring(7, 9));
				setData[3] = Integer.parseInt(res.substring(30, 34));

				sumSec = setData[2] + AddData[1];
				sumMin = setData[1] + AddData[0];
				sumHou = setData[0];

				Sec = String.format("%02d", sumSec % 60);
				Min = String.format("%02d", (sumMin + sumSec / 60) % 60);
				Hou = String.format("%03d",
						(sumHou + (sumMin + sumSec / 60) / 60) % 60);
				count = String.format("%04d", setData[3] + 1);

				output += Hou + ":" + Min + ":" + Sec + res.substring(9, 30)
						+ count + res.substring(34, 44);

				break;
			case 2:
				setData[0] = Integer.parseInt(res.substring(10, 13));
				setData[1] = Integer.parseInt(res.substring(14, 16));
				setData[2] = Integer.parseInt(res.substring(17, 19));
				setData[3] = Integer.parseInt(res.substring(35, 39));

				sumSec = setData[2] + AddData[1];
				sumMin = setData[1] + AddData[0];
				sumHou = setData[0];

				Sec = String.format("%02d", sumSec % 60);
				Min = String.format("%02d", (sumMin + sumSec / 60) % 60);
				Hou = String.format("%03d",
						(sumHou + (sumMin + sumSec / 60) / 60) % 60);
				count = String.format("%04d", setData[3] + 1);

				output += res.substring(0, 10) + Hou + ":" + Min + ":" + Sec
						+ res.substring(19, 35) + count + res.substring(39, 44);

				break;
			case 3:
				setData[0] = Integer.parseInt(res.substring(20, 23));
				setData[1] = Integer.parseInt(res.substring(24, 26));
				setData[2] = Integer.parseInt(res.substring(27, 29));
				setData[3] = Integer.parseInt(res.substring(40, 44));

				sumSec = setData[2] + AddData[1];
				sumMin = setData[1] + AddData[0];
				sumHou = setData[0];

				Sec = String.format("%02d", sumSec % 60);
				Min = String.format("%02d", (sumMin + sumSec / 60) % 60);
				Hou = String.format("%03d",
						(sumHou + (sumMin + sumSec / 60) / 60) % 60);
				count = String.format("%04d", setData[3] + 1);

				output += res.substring(0, 20) + Hou + ":" + Min + ":" + Sec
						+ res.substring(29, 40) + count + res.substring(44, 44);

				break;
			}
		try{
			FileOutputStream fout = openFileOutput("RecordFile", MODE_PRIVATE);
			//byte[] bytes = output.getBytes();
			fout.write(output.getBytes());
			fout.close();
			}catch (Exception f) {
			f.printStackTrace();
		}
	}

	private void Success2() {
		try {
			FileInputStream fin = openFileInput("ScoreFile");
			byte[] buffer = new byte[35];
			fin.read(buffer);
			String res = EncodingUtils.getString(buffer, "UTF-8");
			fin.close();

			int min, max;
			String output = "";
			String[] e = ch.getText().toString().split(":");
			String[] so = res.split(" ");
			int ActiveTime = Integer.parseInt(e[0]) * 60
					+ Integer.parseInt(e[1]);
			String fs = formatTime(ch.getText().toString());
			switch (lev) {
			case 1:
				min = Integer.parseInt(so[0].split(":")[0]) * 60
						+ Integer.parseInt(so[0].split(":")[1]);
				max = Integer.parseInt(so[1].split(":")[0]) * 60
						+ Integer.parseInt(so[1].split(":")[1]);
				
				if (min > max) {
					output += fs + " " + fs + res.substring(11, 35);
					break;
				}
				if (ActiveTime < min) {
					output += fs + res.substring(5, 35);
					break;
				}
				if (ActiveTime > max) {
					output += res.substring(0, 6) + fs + res.substring(11, 35);
					break;
				}
			case 2:
				min = Integer.parseInt(so[2].split(":")[0]) * 60
						+ Integer.parseInt(so[2].split(":")[1]);
				max = Integer.parseInt(so[3].split(":")[0]) * 60
						+ Integer.parseInt(so[3].split(":")[1]);
				if (min > max) {
					output += res.substring(0, 12) + fs + " " + fs + res.substring(23, 35);
					break;
				}
				if (ActiveTime < min) {
					output += res.substring(0, 12) + fs + res.substring(17, 35);
					break;
				}
				if (ActiveTime > max) {
					output += res.substring(0, 18) + fs + res.substring(23, 35);
					break;
				}
			case 3:
				min = Integer.parseInt(so[4].split(":")[0]) * 60
						+ Integer.parseInt(so[4].split(":")[1]);
				max = Integer.parseInt(so[5].split(":")[0]) * 60
						+ Integer.parseInt(so[5].split(":")[1]);
				if (min > max) {
					output += res.substring(0, 24) + fs + " " + fs;
					break;
				}
				if (ActiveTime < min) {
					output += res.substring(0, 24) + fs
							+ res.substring(30, 35);
					break;
				}
				if (ActiveTime > max) {
					output += res.substring(0, 30) + fs;
					break;
				}
			}
			FileOutputStream fout = openFileOutput("ScoreFile", MODE_PRIVATE);
			byte[] bytes = output.getBytes();
			fout.write(bytes);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		ReturnAbove(null);
	}
	
	public String formatTime(String s){
		String[]k = s.split(":");
		String re = "";
		if(k[0].length() == 1 ){
			re += "0" + k[0] + ":";
		}else{
			re += k[0] + ":";
		}
		if(k[1].length() == 1 ){
			re += "0" + k[1];
		}else{
			re += k[1];
		}return re;
	}

	public void Pause(View view){		
		String string = "Pause\nDouble tap screen to continue";
		SpannableString builder = new SpannableString(string);
		builder.setSpan(new AbsoluteSizeSpan(40), 6, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		TextView pt = (TextView)findViewById(R.id.PauseTest);
		pt.setText(builder);
		t2 = SystemClock.elapsedRealtime();
		ch.stop();
		isPause = true;
		LockButton();
		findViewById(R.id.tableLayout1).setVisibility(view.INVISIBLE);
		findViewById(R.id.tableLayout2).setVisibility(view.INVISIBLE);
		findViewById(R.id.BtnLayout).setClickable(false);
		showLayout(null);
	}
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event){ 
		if(KeyEvent.KEYCODE_BACK==keyCode && isPause){ 
			return false ;
		}
		return super.onKeyDown(keyCode, event); 
	 }
	
	private GestureDetector.OnGestureListener otl = new GestureDetector.SimpleOnGestureListener(){
		@Override
		public boolean onDoubleTap(MotionEvent arg0) {
			if(isPause){
				GridLayout gl = (GridLayout)findViewById(R.id.PauseLayout);
				gl.setBackgroundColor(Color.TRANSPARENT);
				TextView tv = (TextView)findViewById(R.id.PauseTest);
				t1 = SystemClock.elapsedRealtime()-(t2-t1);
				ch.setBase(t1);
				ch.start();
				tv.setText("");
				isPause = false;
				AcitveButton();
				findViewById(R.id.tableLayout1).setVisibility(View.VISIBLE);
				findViewById(R.id.tableLayout2).setVisibility(View.VISIBLE);
				findViewById(R.id.BtnLayout).setClickable(true);
			}
			return false;
		}
	};
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		return mGestureDetector.onTouchEvent(event);
		//return this.getCurrentActivity().onTouchEvent(event);
	}
	
	public void showLayout(View view){
		LinearLayout ll = (LinearLayout)findViewById(R.id.BottomLayout);
		if(isHidden){
			ll.setVisibility(view.VISIBLE);
			isHidden = false;
		}else{
			ll.setVisibility(view.INVISIBLE);
			isHidden = true;
		}
	}
	
	public void Reset(View view){
		new AlertDialog.Builder(this).setTitle("重置")
		         .setMessage("放弃当前答题重置题目？")
			     .setPositiveButton("重置本题",new OnClickListener() {      
			      @Override
			      public void onClick(DialogInterface dialog, int which) {
			    	  b.retSu();
			  		  setProblem();
			  		  showLayout(null);
			      }
			     })
			     .setNeutralButton("新题目", new OnClickListener() {    
			    	 @Override
			         public void onClick(DialogInterface dialog, int which) {
			    		 if (Active[0] >= 0 && Active[1] >= 0) {
								if ((Active[0] + Active[1]) % 2 == 1) {
									ArraySudoku[Active[0]][Active[1]].setBackgroundColor(Color.rgb(
											195, 195, 195));
								} else {
									ArraySudoku[Active[0]][Active[1]].setBackgroundColor(Color.rgb(
											255, 255, 255));
								}
							}
							
							InitializeFile(lev);
							t1 = SystemClock.elapsedRealtime();
							ch.setBase(t1);
							
							Active[0] = -1;
							Active[1] = -1;
							isPause = false;
							//isHidden = true;
							showLayout(null);
			    		 
			    }
			   }).setNegativeButton("否", null).show();
	}
	
	public void Hint(View view){
		
	}
	
}

