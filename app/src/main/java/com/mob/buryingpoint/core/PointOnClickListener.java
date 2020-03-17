package com.mob.buryingpoint.core;

import android.view.View;

import com.mob.buryingpoint.utils.Log;

/**
 * 代理点击操作
 */
public class PointOnClickListener implements View.OnClickListener {

	private View.OnClickListener listener;

	public PointOnClickListener(View.OnClickListener listener){
		this.listener = listener;
	}

	@Override
	public void onClick(View v) {
		listener.onClick(v);
		Log.show("每个点击事件都有我");
	}
}
