package com.example.sudoku;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class AssetsMove {
	
	String Des_PATH = "/data/data/com.example.sudoku/files/";
	String e = "EasyMode";
	String n = "NormalMode";
	String h = "HardMode";
	String r = "RecordFile";
	String s = "ScoreFile";
	String se = "Setting";
	Context context;
	
	public AssetsMove(Context context){
		this.context = context;
	}
	
	private boolean MoveAssets(String des){
		try{
			InputStream is = context.getAssets().open(des);
			OutputStream os = new FileOutputStream(Des_PATH + des);
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) > 0 ){
				os.write(buffer,0,length);
			}
			os.flush();
			os.close();
			is.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void PepearMove(){
		if(!new File(Des_PATH+e).exists()){
			MoveAssets(e);
			MoveAssets(r);
			MoveAssets(s);
			MoveAssets(n);
			MoveAssets(se);
		}
	}
	
	public void ResetRecord(){
		MoveAssets(r);
		MoveAssets(s);
	}
}
