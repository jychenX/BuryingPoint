package com.mob.auto.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.ViewGroup;

import com.mob.auto.utils.Log;

public class PointLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

	private InterceptTaskManager interceptTaskManager;

	public PointLifecycleCallbacks(){
		interceptTaskManager = new InterceptTaskManager();
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

	}

	@Override
	public void onActivityStarted(Activity activity) {

	}

	@Override
	public void onActivityResumed(Activity activity) {
		if (activity != null) {
			interceptTaskManager.sendTask(activity);
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
