package com.udacity.gradle.builditbigger.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.R;

public class AdUtils {

	public static void showAds(final View root) {

		if (root != null) {

			AdRequest adRequest = new AdRequest.Builder()
					.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
					.build();

			AdView adView = new AdView(root.getContext());

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT
			);

			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			params.addRule(RelativeLayout.CENTER_HORIZONTAL);

			adView.setLayoutParams(params);

			adView.setAdSize(AdSize.BANNER);
			adView.setAdUnitId(root.getContext().getString(R.string.banner_ad_unit_id));

			adView.loadAd(adRequest);

			((ViewGroup) root).addView(adView);
		}
	}
}
