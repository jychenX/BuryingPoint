package com.mob.buryingpoint.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static final String tag = "--DY-SP--";

	public static void show(String msg){
		System.out.println(tag + Thread.currentThread().getId() + "--" + msg);
	}

	public static void showStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {
			//ignore
		}
		show("异常信息：" + sw.toString());
	}

	public static String showDebugStackTrace(String tag){
		RuntimeException here = new RuntimeException("System.out " + tag);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		here.fillInStackTrace().printStackTrace(pw);
		pw.close();
		try {
			sw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return sw.toString();
	}

	private static String getTimeStamp(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String timaStamp = simpleDateFormat.format(new Date(System.currentTimeMillis()));
		return timaStamp;
	}
}
