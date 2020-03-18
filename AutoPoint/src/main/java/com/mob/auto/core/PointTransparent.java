package com.mob.auto.core;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PointTransparent {

	public static void addTransparentLayout(Activity activity){
		ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
		LinearLayout layout = new LinearLayout(activity.getBaseContext());
	}

}
