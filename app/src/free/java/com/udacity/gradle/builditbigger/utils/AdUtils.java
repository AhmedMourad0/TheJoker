package com.udacity.gradle.builditbigger.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.R;

public final class AdUtils {

	private static InterstitialAd interstitialAd;

	public static void initialize(final Context context) {
		MobileAds.initialize(context, context.getString(R.string.app_ad_id));
	}

	public static void showBannerAd(final View root) {

		if (root != null) {

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

			adView.loadAd(getAdRequest());

			((ViewGroup) root).addView(adView);
		}
	}

	public static void showInterstitialAd(final Context context, final Runnable onAdOpened, final Runnable onAdClosed) {

		if (interstitialAd == null) {

			interstitialAd = new InterstitialAd(context);
			interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));

			interstitialAd.setAdListener(new AdListener() {
				@Override
				public void onAdLoaded() {
					super.onAdLoaded();
					interstitialAd.show();
				}

				@Override
				public void onAdOpened() {
					super.onAdOpened();
					onAdOpened.run();
				}

				@Override
				public void onAdClosed() {
					onAdClosed.run();
				}
			});
		}

		if (!interstitialAd.isLoaded())
			interstitialAd.loadAd(getAdRequest());
	}

	@NonNull
	private static AdRequest getAdRequest() {
		return new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();
	}
}
