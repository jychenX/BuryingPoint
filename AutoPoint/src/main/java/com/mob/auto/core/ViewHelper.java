package com.mob.auto.core;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ViewHelper {

	//我们需要监听的接口列表
	private static ArrayList<Class<?>> interfacesList = new ArrayList<>();
	//我们需要监听的接口列表对应View类下面设置监听时使用的参数（Class<?>）和方法（Method）
	private static HashMap<Class<?>, Method> methodMap = new HashMap<>();
	//遍历View下面已经注册（不为空）的监听
	private static HashMap<Class<?>, Object> registeredListeners = new HashMap<>();

	static{
		initData();
	}

	private static void initData(){
		ArrayList<Class<?>> list = new ArrayList(Arrays.asList(ProxyViewAllListener.class.getInterfaces()));
		for(Class<?> interfaces : list){
			try {
				interfacesList.add(interfaces);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		Class<?> view = View.class;
		Method[] methods = view.getDeclaredMethods();
		for(Method method : methods){
			//如果只有一个参数，且这个参数是我们需要监听的回调
			if(method.getParameterTypes().length == 1 && interfacesList.contains(method.getParameterTypes()[0])){
				methodMap.put(method.getParameterTypes()[0], method);
			}
		}
	}

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
//				setPoints(group);
				setPoints2(group);
			}
		}
	}

	/**
	 * 遍历寻找有用的view(文本检索，控件属性，替换监听，拦截动态修改控件，增加布局，移除布局)
	 * @param viewGroup
	 */
	public static void setPoints(ViewGroup viewGroup){
		for(int i = 0; i < viewGroup.getChildCount(); i++){
			View childView = viewGroup.getChildAt(i);
			if(childView != null) {
				if (childView instanceof ViewGroup) {
					setPoints((ViewGroup) childView);
				} else if (childView instanceof Button) {//目前只针对Button
					View.OnClickListener listener = getOnClickListener(childView);
					if(listener != null && !(listener instanceof ProxyOnClickListener)){
						ProxyOnClickListener myListener = new ProxyOnClickListener(listener);
						childView.setOnClickListener(myListener);
					}
				}
			}
		}
	}

	/**
	 * 通用
	 * @param viewGroup
	 */
	public static void setPoints2(ViewGroup viewGroup){
		for(int i = 0; i < viewGroup.getChildCount(); i++){
			View childView = viewGroup.getChildAt(i);
			if(childView != null) {
				if (childView instanceof ViewGroup) {
					setPoints2((ViewGroup) childView);
				} else {
					commonPoint(childView);
				}
			}
		}
	}

	/**
	 * AccessibilityDelegate方式
	 * @param viewGroup
	 */
	public static void setPoints3(ViewGroup viewGroup){
		for(int i = 0; i < viewGroup.getChildCount(); i++){
			View childView = viewGroup.getChildAt(i);
			if(childView != null) {
				if (childView instanceof ViewGroup) {
					setPoints2((ViewGroup) childView);
				} else {
					View.AccessibilityDelegate accessibilityDelegate = childView.getAccessibilityDelegate();
					if(accessibilityDelegate != null){
						PointAccessibilityDelegate pointAccessibilityDelegate = new PointAccessibilityDelegate(accessibilityDelegate);
						childView.setAccessibilityDelegate(pointAccessibilityDelegate);
					}
				}
			}
		}
	}

	private static void commonPoint(View childView){
		registeredListeners = getAllListener(childView);
		if(registeredListeners != null && registeredListeners.size() > 0) {
			for (Class<?> listenerClass : registeredListeners.keySet()) {
				//如果是我们需要的监听的监听器
				if (interfacesList.contains(listenerClass)) {
					//保存原先的监听并建立新的监听代理
					ProxyViewAllListener myListener = new ProxyViewAllListener(registeredListeners.get(listenerClass));
					Method method = methodMap.get(listenerClass);
					method.setAccessible(true);
					try {
						method.invoke(childView, myListener);
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 针对OnClickListener
	 * @param view
	 * @return
	 */
	private static View.OnClickListener getOnClickListener(View view){
		Class<?> viewClass = View.class;
		try {
			if(view.hasOnClickListeners()){
				//Way1
				Method method = viewClass.getDeclaredMethod("getListenerInfo");
				method.setAccessible(true);
				Object listenerInfoObj = method.invoke(view);

				//Way2
//				Field field = viewClass.getDeclaredField("mListenerInfo");
//				field.setAccessible(true);
//				Object listenerInfoObj = field.get(button);

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

	/**
	 * 获取 View$ListenerInfo下面不为空的全部监听器对象
	 * @param view
	 * @return
	 */
	private static HashMap<Class<?>, Object> getAllListener(View view){
		try {
			Class<?> viewClass = View.class;
			//Way1
			Method method = viewClass.getDeclaredMethod("getListenerInfo");
			method.setAccessible(true);
			Object listenerInfoObj = method.invoke(view);

			//Way2
//			Field field = viewClass.getDeclaredField("mListenerInfo");
//			field.setAccessible(true);
//			Object listenerInfoObj = field.get(view);

			if(listenerInfoObj != null) {
				Class<?> listenerInfo = Class.forName("android.view.View$ListenerInfo");
				HashMap<Class<?>, Object> listeners = new HashMap<>();
				Field[] fields = listenerInfo.getDeclaredFields();
				for (Field f : fields) {
					f.setAccessible(true);
					Object obj = f.get(listenerInfoObj);
					if(obj != null) {
						listeners.put(f.getType(), obj);
					}
				}
				if (listeners.size() > 0) {
					return listeners;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
}
