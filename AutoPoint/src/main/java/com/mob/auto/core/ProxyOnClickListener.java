package com.mob.auto.core;

import android.view.View;

import com.mob.auto.utils.Log;

/**
 * 代理点击操作
 */
public class ProxyOnClickListener implements View.OnClickListener {

	private View.OnClickListener listener;
	private int count;

	public ProxyOnClickListener(View.OnClickListener listener, int count){
		this.listener = listener;
		this.count = count;
	}

	@Override
	public void onClick(View v) {
		listener.onClick(v);
		Log.show("每个点击事件都能看到我:" + count);
	}
}
