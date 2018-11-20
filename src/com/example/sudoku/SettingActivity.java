package com.example.sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class SettingActivity extends Activity {

	private VolumeLayout vl;
	AssetsMove am;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		vl = (VolumeLayout)findViewById(R.id.volumeLayout1);
		onLoad();
		am = new AssetsMove(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	public void saveVolume(){
		try {
			FileOutputStream fos = openFileOutput("Setting",MODE_PRIVATE);
			int e = vl.getProcess();
			fos.write(e);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onBackPressed(){
		saveVolume();
		super.onBackPressed();
	}
	
	public void onLoad(){
		byte[] buffer = new byte[8];
		try {
			FileInputStream fin = openFileInput("Setting");
			fin.read(buffer);
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		vl.setProcess(buffer[0]);
	}
	
	public void ReSet(View view){
		new AlertDialog.Builder(this) 
		.setTitle("重设")
		.setMessage("确定重设数据吗？")
		.setPositiveButton("是", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				am.ResetRecord();
			}
		})
		.setNegativeButton("否", null)
		.show();	
	}
	
	public void Statics(View view)
	{
		Intent it = new Intent();
		it.setClass(this, StatisticsActivity.class);
		startActivity(it);
	}

	public void UpdateInfo(View view){
		Intent it = new Intent();
		it.setClass(this, UpdataInfoActivity.class);
		startActivity(it);
	}
}
