package com.mob.auto.core;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ViewHelper {

	private static int count = 0;

	public static void doIt(Activity activity){
		ArrayList<ViewGroup> viewGroups = new ArrayList<>();
		viewGroups.add((ViewGroup) activity.getWindow().getDecorView());
//		viewGroups.add((ViewGroup) activity.getWindow().getDecorView().getRootView());
//		viewGroups.add((ViewGroup) activity.findViewById(android.R.id.content));
//		viewGroups.add((ViewGroup) activity.findViewById(android.R.id.content).getRootView());
//		viewGroups.add((ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content));
//		viewGroups.add((ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content).getRootView());
		for(ViewGroup group : viewGroups) {
			if (group != null && group instanceof ViewGroup) {
				setPoints(group);
			}
		}
	}


	/**
	 * 遍历寻找有用的view
	 * @param viewGroup
	 */
	public static void setPoints(ViewGroup viewGroup){
		for(int i = 0; i < viewGroup.getChildCount(); i++){
			View childView = viewGroup.getChildAt(i);
			if(childView != null) {
				if (childView instanceof ViewGroup) {
					setPoints((ViewGroup) childView);
				} else if (childView instanceof Button) {
					Button button = (Button) childView;
					View.OnClickListener listener = getButtonNewListener(button);
					if(listener != null && !(listener instanceof ProxyOnClickListener)){
						ProxyOnClickListener myListener = new ProxyOnClickListener(listener, count);
						count ++;
						button.setOnClickListener(myListener);
					}
				}
			}
		}
	}

	private static View.OnClickListener getButtonNewListener(Button button){
		Class<?> viewClass = null;
		try {
			viewClass = Class.forName("android.view.View");
			if(button.hasOnClickListeners()){//如果绑定了点击事件
				//Way1
				Method method = viewClass.getDeclaredMethod("getListenerInfo");
				method.setAccessible(true);
				Object listenerInfoObj = method.invoke(button);

				//Way2

				if(listenerInfoObj != null) {
					Class<?> listenerInfo = Class.forName("android.view.View$ListenerInfo");
					Field onClickListenerField = listenerInfo.getDeclaredField("mOnClickListener");
					onClickListenerField.setAccessible(true);
					Object onClickListener = onClickListenerField.get(listenerInfoObj);
					if (onClickListener != null && onClickListener instanceof View.OnClickListener) {
						return (View.OnClickListener) onClickListener;
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
}
