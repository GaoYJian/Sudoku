package com.example.sudoku;


import java.lang.reflect.Field;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

public class UpdataInfoActivity extends Activity {

	Dialog d;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); 
		setContentView(R.layout.activity_updata_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.updata_info, menu);
		return true;
	}
	
	public void test(View view){
		d = new Dialog(this);
		d.setContentView(R.layout.update);
		reSizeDatePicker();
		d.show();
	}

	public void reSizeDatePicker(){
		Field[] fields = DatePicker.class.getDeclaredFields();
		DatePicker dp = (DatePicker)d.findViewById(R.id.datePicker1);
		View[] np = new View[3];
		int i = 0;
		for(Field field : fields){
			field.setAccessible(true);
			if(field.getType().getSimpleName().equals("NumberPicker")){
				try{
					np[i++] = (View)field.get(dp);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		
		np[0].measure(0,0);
		np[0].getLayoutParams().width = (int) (np[0].getMeasuredWidth() * 0.5f);  
		np[0].getLayoutParams().height = (int) (np[0].getMeasuredHeight() * 0.5f);
		
		np[1].measure(0,0);
		np[1].getLayoutParams().width = (int) (np[0].getMeasuredWidth() * 0.5f);  
		np[1].getLayoutParams().height = (int) (np[0].getMeasuredHeight() * 0.5f);
		
		np[2].measure(0,0);
		np[2].getLayoutParams().width = (int) (np[0].getMeasuredWidth() * 0.5f);  
		np[2].getLayoutParams().height = (int) (np[0].getMeasuredHeight() * 0.5f);
		
	}
	
}
