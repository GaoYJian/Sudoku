package com.example.sudoku;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class VolumeLayout extends LinearLayout{
	
	private TextView volumeTextView;
	private SeekBar volumeSeekBar;
	private SeekBar.OnSeekBarChangeListener onseekbarchangelistener;
	
	public VolumeLayout(Context context){
		super(context);
	}
	
	public VolumeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.volume, this);
        volumeTextView = (TextView)findViewById(R.id.volumeTextView);
        volumeSeekBar = (SeekBar)findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setProgress(30);
		onseekbarchangelistener = new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				volumeTextView.setText("“Ù–ß£∫"+volumeSeekBar.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		
		volumeSeekBar.setOnSeekBarChangeListener(onseekbarchangelistener);
	}
	
	public int getProcess(){
		return volumeSeekBar.getProgress();
	}
	
	public void setProcess(int i){
		volumeSeekBar.setProgress(i);
	}
}
