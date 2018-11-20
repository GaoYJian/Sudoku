package com.example.sudoku;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

	private AssetsMove am;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		am = new AssetsMove(this);
		am.PepearMove();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showIndroduce(View view)
	{
		Intent it = new Intent();
		it.setClass(this, IndroduceActivity.class);
		startActivity(it);
	}

	public void startGame(View view)
	{
		Intent it = new Intent();
		it.setClass(this, ChoiceActivity.class);
		startActivity(it);
	}
	
	public void EndGame(View view)
	{	
		new AlertDialog.Builder(this) 
		.setTitle("退出")
		.setMessage("确定退出吗？")
		.setPositiveButton("是", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				System.exit(0);	
			}
		})
		.setNegativeButton("否", null)
		.show();
	}
	
	

	@Override
	public void onBackPressed() {
		EndGame(null);
	}
	
	
	
	public void Setting(View view){
		Intent it = new Intent();
		it.setClass(this,SettingActivity.class);
		startActivity(it);
	}
	
	/*
	public void WriteTest(View view){
		FileOutputStream fout;
		try {
			fout = openFileOutput("TEST.txt", MODE_PRIVATE);
			Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。 
			t.setToNow(); // 取得系统时间。  
			String time=t.year+"年 "+(t.month+1)+"月 "+t.monthDay+"日 "+t.hour+"h "+t.minute+"m "+t.second;  
			fout.write(time.getBytes());
			fout.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}*/
}

