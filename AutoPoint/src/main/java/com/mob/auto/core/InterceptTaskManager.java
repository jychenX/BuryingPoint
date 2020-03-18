package com.mob.auto.core;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.view.Window;

import com.mob.auto.utils.Log;
import com.mob.tools.MobHandlerThread;

public class InterceptTaskManager implements android.os.Handler.Callback {

	private PointLayoutListener layoutListener;
	private Handler handler;

	public InterceptTaskManager(){
		handler = MobHandlerThread.newHandler(this);
		layoutListener = new PointLayoutListener();
	}

	public void sendTask(Activity activity){
		Message msg = new Message();
		msg.obj = activity;
		handler.sendMessage(msg);
	}

	@Override
	public boolean handleMessage(Message msg) {
		Activity activity = (Activity) msg.obj;
		doInterceptJob(activity);
		return false;
	}

	private void doInterceptJob(Activity activity){
		//案例一
		Log.show("Activity Class Name：" + activity.getClass().getName());
		ViewHelper.doIt(activity);

		//案例二：
//		if(activity.getWindow() != null && activity.getWindow().getDecorView() != null
//				&& activity.getWindow().getDecorView() instanceof ViewGroup) {
//			ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
//			layoutListener.setRootView(viewGroup);
//			viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
//		}

		//案例三：
//		Window window = activity.getWindow();
//		Window.Callback callback = window.getCallback();
//		ProxyWindowCallback proxyWindowCallback = new ProxyWindowCallback(callback);
//		window.setCallback(proxyWindowCallback);
	}
}
