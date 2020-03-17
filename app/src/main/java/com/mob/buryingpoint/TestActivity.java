package com.mob.buryingpoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main_layout);
		initView();
	}

	private void initView(){
		Button button1 = findViewById(R.id.button1);
		button1.setOnClickListener(this);

		Button button2 = findViewById(R.id.button2);
		button2.setOnClickListener(this);

		Button button3 = findViewById(R.id.button3);
		button3.setOnClickListener(this);

		Button button4 = findViewById(R.id.button4);
		button4.setOnClickListener(this);

		Button button5 = findViewById(R.id.button5);
		button5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getBaseContext(), "显示按钮的文本：" + ((Button)v).getText(), Toast.LENGTH_SHORT).show();
	}
}
