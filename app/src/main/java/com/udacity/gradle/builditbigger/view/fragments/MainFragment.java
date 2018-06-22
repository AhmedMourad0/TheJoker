package com.udacity.gradle.builditbigger.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ekalips.fancybuttonproj.FancyButton;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.background.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.utils.AdUtils;
import com.udacity.gradle.builditbigger.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import inc.ahmedmourad.jokedialog.JokeDialog;

public class MainFragment extends Fragment {

	@SuppressWarnings("WeakerAccess")
	@BindView(R.id.joke_button)
	FancyButton jokeButton;

	private Unbinder unbinder;

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.fragment_main, container, false);

		unbinder = ButterKnife.bind(this, root);

		jokeButton.setOnClickListener(v -> tellJoke(v.getContext().getApplicationContext()));

		AdUtils.initialize(root.getContext());
		AdUtils.showBannerAd(root);

		return root;
	}

	private void tellJoke(final Context context) {

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

				AdUtils.showInterstitialAd(context, () -> {

					if (activity != null)
						activity.setIdleState(true);

				}, () -> {

					if (activity != null && !activity.isFinishing() && !activity.isDestroyed())
						JokeDialog.displayJoke(context, joke);

					setLoading(false);
				});
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
