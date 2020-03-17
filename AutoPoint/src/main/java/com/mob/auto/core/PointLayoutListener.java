package com.mob.auto.core;

import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public class PointLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

	private ViewGroup rootView;

	public void setRootView(ViewGroup rootView){
		this.rootView = rootView;
	}

	@Override
	public void onGlobalLayout() {
		ViewHelper.setPoints(rootView);
	}
}
