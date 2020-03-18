package com.mob.auto.core;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import com.mob.auto.impl.InterceptCallback;
import com.mob.auto.utils.Log;

public class PointAccessibilityDelegate extends View.AccessibilityDelegate {

	private View.AccessibilityDelegate accessibilityDelegate;
	private InterceptCallback callback;

	public PointAccessibilityDelegate(View.AccessibilityDelegate accessibilityDelegate){
		this.accessibilityDelegate = accessibilityDelegate;
	}

	public void setInterCeptCallback(InterceptCallback callback){
		this.callback = callback;
	}

	@Override
	public void sendAccessibilityEvent(View host, int eventType) {
		if(accessibilityDelegate != null){
			accessibilityDelegate.sendAccessibilityEvent(host, eventType);
		}
		int[] test = new int[]{AccessibilityEvent.TYPE_VIEW_CLICKED,
				AccessibilityEvent.TYPE_VIEW_SCROLLED,
				AccessibilityEvent.TYPE_WINDOWS_CHANGED, AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED};

		if(eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
			if(callback != null){
				callback.beforeCallListener();
			}
			Log.show("AccessibilityDelegate");
			if(callback != null){
				callback.afterCallListener();
			}
		}
	}
}
