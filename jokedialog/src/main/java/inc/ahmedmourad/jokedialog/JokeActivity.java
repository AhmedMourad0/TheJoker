package inc.ahmedmourad.jokedialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

	public static final String EXTRA_JOKE = "jd_j";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_joke);

		final Intent intent = getIntent();

		if (intent == null) {
			finish();
			return;
		}

		final Bundle extras = intent.getExtras();

		if (extras == null) {
			finish();
			return;
		}

		final String joke = extras.getString(EXTRA_JOKE);

		if (joke == null) {
			finish();
			return;
		}

		((TextView) findViewById(R.id.dialog_joke_textview)).setText(joke);
	}
}
