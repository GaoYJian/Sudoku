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
		tv.setText("数独（すうどく，Sudoku）是一种运用纸、笔进行演算的逻辑游戏。玩家需要根据9×9盘面上的已知数字，推理出所有剩余空格的数字，并满足每一行、每一列、每一个粗线宫内的数字均含1-9，不重复。 每一道合格的数独谜题都有且仅有唯一答案，推理方法也以此为基础，任何无解或多解的题目都是不合格的。\n");
		tv = (TextView)findViewById(R.id.IndroduceM1);
		tv.setText("基本解题方法：\n1.列摒除方法,数字可填唯一空格在「列」单元,如下图，红色方框内填数字“6”");
		tv = (TextView)findViewById(R.id.IndroduceM2);
		tv.setText("2.行摒除方法,数字可填唯一空格在「行」单元,如下图，红色方框内填数字“6”");
		tv = (TextView)findViewById(R.id.IndroduceM3);
		tv.setText("3.宫摒除方法,数字可填唯一空格在「宫」单元,如下图，红色方框内填数字“6”");
		tv = (TextView)findViewById(R.id.IndroduceM4);
		tv.setText("4.余数法,用格位去找唯一可填数字,如下图，红色方框内填数字“6”");
		tv = (TextView)findViewById(R.id.IndroduceM5);
		tv.setText("上述方法称为基础解法(Basic Techinques)，其他所有的解法称为进阶解法(Advanced Techniques)，是在补基本解法之不足，所以又称辅助解法。进阶解法包括：区块摒除法（Locked Candidates）、数组法（Subset）、四角对角线（X-Wing）、唯一矩形（Unique Rectangle）、全双值坟墓（Bivalue Universal Grave）、单数链(X-Chain)、异数链(XY-Chain)及其他数链的高级技巧等等。已发展出来的方法有近百种之多。");
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
