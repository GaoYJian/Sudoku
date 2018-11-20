package com.example.sudoku;

import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Sounds {
	
	private SoundPool soundpool;
	int[] soundId;
	float volume;
	FileInputStream fin;
	
	public Sounds(Context context,FileInputStream fin){
		this.fin = fin;
		volume = getVolume();
		soundId = new int[5];
		soundpool = new SoundPool(10,AudioManager.STREAM_SYSTEM,5);
		soundId[0] = soundpool.load(context,R.raw.tap, 1);
		soundId[1] = soundpool.load(context,R.raw.nine,1);
		soundId[2] = soundpool.load(context,R.raw.cancel,1);
	}
	
	public void TapSound(){
		soundpool.play(soundId[0], volume, volume, 0, 0, 1);
	}
	
	public void NineSound(){
		soundpool.play(soundId[1], volume, volume, 0, 0, 1);
	}
	
	public void CancelSound(){
		soundpool.play(soundId[2], volume, volume, 0, 0, 1);
	}
	
	public float getVolume(){
			byte[] buffer = new byte[8];
			try {
				fin.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int o = buffer[0];
			return (float) ((o*1.0)/100);
	}
}
