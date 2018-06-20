package inc.ahmedmourad.jokedialog;

import android.content.Context;
import android.content.Intent;

public final class JokeDialog {
	public static void displayJoke(final Context context, final String joke) {
		Intent intent = new Intent(context, JokeActivity.class);
		intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
		context.startActivity(intent);
	}
}
