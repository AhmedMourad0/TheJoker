package com.udacity.gradle.builditbigger.idling;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleIdlingResource implements IdlingResource {

	@Nullable
	private volatile ResourceCallback callback;

	private final AtomicBoolean isIdleNow = new AtomicBoolean(true);

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public boolean isIdleNow() {
		return isIdleNow.get();
	}

	@Override
	public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
		this.callback = callback;
	}

	public void setIdleState(boolean isIdleNow) {

		this.isIdleNow.set(isIdleNow);

		if (callback != null && isIdleNow)
			Objects.requireNonNull(callback).onTransitionToIdle();
	}
}
