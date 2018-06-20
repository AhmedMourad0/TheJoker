package com.udacity.gradle.builditbigger.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.idling.SimpleIdlingResource;

public class MainActivity extends AppCompatActivity {

	@Nullable
	private SimpleIdlingResource idlingResource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void setIdleState(boolean isIdleNow) {
		if (idlingResource != null)
			idlingResource.setIdleState(isIdleNow);
	}

	@VisibleForTesting
	@NonNull
	public IdlingResource getIdlingResource() {

		if (idlingResource == null)
			idlingResource = new SimpleIdlingResource();

		return idlingResource;
	}
}
