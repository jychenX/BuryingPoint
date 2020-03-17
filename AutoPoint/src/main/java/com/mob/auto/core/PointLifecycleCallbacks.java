package com.mob.auto.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.ViewGroup;

import com.mob.auto.utils.Log;

public class PointLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

	private PointLayoutListener layoutListener;

	public PointLifecycleCallbacks(){
		layoutListener = new PointLayoutListener();
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {
		Log.show("Activity Class Name：" + activity.getClass().getName());
		ViewHelper.doIt(activity);

		if(activity.getWindow() != null && activity.getWindow().getDecorView() != null
				&& activity.getWindow().getDecorView() instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
			layoutListener.setRootView(viewGroup);
			viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
		}
	}

	@Override
	public void onActivityPaused(Activity activity) {

	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {

	}
}
