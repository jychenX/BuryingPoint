package com.mob.buryingpoint.application;

import android.app.Application;

import com.mob.auto.core.PointLifecycleCallbacks;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		initPoint();
	}
	
	private void initPoint(){
		PointLifecycleCallbacks callbacks = new PointLifecycleCallbacks();
		registerActivityLifecycleCallbacks(callbacks);
	}

}
