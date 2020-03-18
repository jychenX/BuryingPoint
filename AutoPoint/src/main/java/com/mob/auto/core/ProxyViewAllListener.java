package com.mob.auto.core;

import android.view.MotionEvent;
import android.view.View;

import com.mob.auto.impl.InterceptCallback;
import com.mob.auto.utils.Log;

public class ProxyViewAllListener implements View.OnClickListener,
		View.OnLongClickListener, View.OnFocusChangeListener, View.OnTouchListener {

	private Object listener;
	private InterceptCallback callback;

	public ProxyViewAllListener(Object listener){
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
		((View.OnClickListener)listener).onClick(v);
		Log.show("View通用方式埋点");
		if(callback != null){
			callback.afterCallListener();
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if(callback != null){
			callback.beforeCallListener();
		}
		((View.OnFocusChangeListener)listener).onFocusChange(v, hasFocus);
		if(callback != null){
			callback.afterCallListener();
		}
	}

	//TODO 缺点一：带返回值的无法捕捉执行后的回调
	@Override
	public boolean onLongClick(View v) {
		if(callback != null){
			callback.beforeCallListener();
		}
//		if(callback != null){
//			callback.afterCallListener();
//		}
		return ((View.OnLongClickListener)listener).onLongClick(v);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(callback != null){
			callback.beforeCallListener();
		}
//		if(callback != null){
//			callback.afterCallListener();
//		}
		return ((View.OnTouchListener)listener).onTouch(v, event);
	}
}
