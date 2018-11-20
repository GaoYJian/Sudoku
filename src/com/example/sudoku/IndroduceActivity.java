package com.example.sudoku;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class IndroduceActivity extends Activity {

	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_indroduce);
		
		tv = (TextView)findViewById(R.id.Indroduce1);
		tv.setText("�����������ɤ���Sudoku����һ������ֽ���ʽ���������߼���Ϸ�������Ҫ����9��9�����ϵ���֪���֣����������ʣ��ո�����֣�������ÿһ�С�ÿһ�С�ÿһ�����߹��ڵ����־���1-9�����ظ��� ÿһ���ϸ���������ⶼ���ҽ���Ψһ�𰸣�������Ҳ�Դ�Ϊ�������κ��޽�������Ŀ���ǲ��ϸ�ġ�\n");
		tv = (TextView)findViewById(R.id.IndroduceM1);
		tv.setText("�������ⷽ����\n1.���������,���ֿ���Ψһ�ո��ڡ��С���Ԫ,����ͼ����ɫ�����������֡�6��");
		tv = (TextView)findViewById(R.id.IndroduceM2);
		tv.setText("2.���������,���ֿ���Ψһ�ո��ڡ��С���Ԫ,����ͼ����ɫ�����������֡�6��");
		tv = (TextView)findViewById(R.id.IndroduceM3);
		tv.setText("3.���������,���ֿ���Ψһ�ո��ڡ�������Ԫ,����ͼ����ɫ�����������֡�6��");
		tv = (TextView)findViewById(R.id.IndroduceM4);
		tv.setText("4.������,�ø�λȥ��Ψһ��������,����ͼ����ɫ�����������֡�6��");
		tv = (TextView)findViewById(R.id.IndroduceM5);
		tv.setText("����������Ϊ�����ⷨ(Basic Techinques)���������еĽⷨ��Ϊ���׽ⷨ(Advanced Techniques)�����ڲ������֮ⷨ���㣬�����ֳƸ����ⷨ�����׽ⷨ�����������������Locked Candidates�������鷨��Subset�����ĽǶԽ��ߣ�X-Wing����Ψһ���Σ�Unique Rectangle����ȫ˫ֵ��Ĺ��Bivalue Universal Grave����������(X-Chain)��������(XY-Chain)�����������ĸ߼����ɵȵȡ��ѷ�չ�����ķ����н�����֮�ࡣ");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void returnTitle(View view)
	{
		NavUtils.navigateUpFromSameTask(this);
	}
	
	@Override
	public void onBackPressed() {
		returnTitle(null);
	}
}
