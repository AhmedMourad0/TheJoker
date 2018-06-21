package com.udacity.gradle.builditbigger.background;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.view.fragments.MainFragment;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<MainFragment.AsyncTaskCallback, Void, String> {

	private static MyApi apiService = null;
	private MainFragment.AsyncTaskCallback callback = null;

	@Override
	protected final String doInBackground(MainFragment.AsyncTaskCallback... params) {

		callback = params[0];

		callback.setLoading(true);

		if (apiService == null)
			apiService = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
					.setRootUrl("http://10.0.2.2:8080/_ah/api/")
					.setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true))
					.build();

		try {
			return apiService.getJokeBean().execute().getData();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	@Override
	protected void onPostExecute(String result) {

		if (result.toLowerCase().contains("failed to connect"))
			result = "Something went wrong!\nplease try again later!";

		callback.displayJoke(result);
	}
}
