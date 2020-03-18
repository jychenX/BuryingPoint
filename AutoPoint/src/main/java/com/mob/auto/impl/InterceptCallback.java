package com.mob.auto.impl;

public interface InterceptCallback<T> {

	void beforeCallListener(T... t);

	void afterCallListener(T... t);
}
