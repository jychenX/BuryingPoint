package com.mob.buryingpoint.core;

import android.app.Activity;
import android.view.View;

public class ViewHelper {

	public static void doIt(Activity activity){
//		View mainView = activity.getWindow().getDecorView();
		View mainView = activity.findViewById(android.R.id.content);

	}
}
