package com.udacity.gradle.builditbigger.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ekalips.fancybuttonproj.FancyButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.background.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.utils.AdUtils;
import com.udacity.gradle.builditbigger.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import inc.ahmedmourad.jokedialog.JokeDialog;

public class MainFragment extends Fragment {

	@BindView(R.id.joke_button)
	FancyButton jokeButton;

	private Unbinder unbinder;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_main, container, false);

		unbinder = ButterKnife.bind(this, root);

		jokeButton.setOnClickListener(v -> tellJoke(v.getContext().getApplicationContext()));

		AdUtils.showAds((RelativeLayout) root);

		return root;
	}

	public void tellJoke(final Context context) {

		final MainActivity activity = (MainActivity) getActivity();

		if (activity != null)
			activity.setIdleState(false);

		new EndpointsAsyncTask().execute(new AsyncTaskCallback() {
			@Override
			public void setLoading(final boolean loading) {
				if (activity != null && !activity.isFinishing()) {
					activity.runOnUiThread(() -> {
						if (loading) {
							jokeButton.setClickable(false);
							jokeButton.collapse();
						} else {
							jokeButton.setClickable(true);
							jokeButton.expand();
						}
					});
				}
			}

			@Override
			public void displayJoke(String joke) {

				if (activity != null && !activity.isFinishing() && !activity.isDestroyed())
					JokeDialog.displayJoke(context, joke);

				if (activity != null)
					activity.setIdleState(true);
			}
		});
	}

	@Override
	public void onDestroy() {
		unbinder.unbind();
		super.onDestroy();
	}

	public interface AsyncTaskCallback {
		void setLoading(final boolean loading);
		void displayJoke(final String joke);
	}
}
