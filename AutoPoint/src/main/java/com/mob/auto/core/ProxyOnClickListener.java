package com.mob.auto.core;

import android.view.View;

import com.mob.auto.impl.InterceptCallback;
import com.mob.auto.utils.Log;

/**
 * 代理点击监听
 */
public class ProxyOnClickListener implements View.OnClickListener {

	private View.OnClickListener listener;
	private InterceptCallback callback;

	public ProxyOnClickListener(View.OnClickListener listener){
		this.listener = listener;
	}

	public void setInterCeptCallback(InterceptCallback callback){
		this.callback = callback;
	}

	@Override
	public void onClick(View v) {
		if(callback != null){
			callback.beforeCallListener();
		}
		listener.onClick(v);
		Log.show("每个OnClick事件都能看到我");
		if(callback != null){
			callback.afterCallListener();
		}
	}
}
