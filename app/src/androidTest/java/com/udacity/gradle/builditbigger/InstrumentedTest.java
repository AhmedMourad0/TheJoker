package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.view.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

	private IdlingResource idlingResource;

	@Before
	public void registerIdlingResource() {
		idlingResource = activityRule.getActivity().getIdlingResource();
		IdlingRegistry.getInstance().register(idlingResource);
	}

	@Test
	public void clickButton_dialogAppears() {
		onView(withText(R.string.button_text)).perform(click());
		onView(allOf(withContentDescription("Interstitial close button"), isDisplayed())).perform(click());
		onView(withId(R.id.dialog_joke_textview)).check(matches(isDisplayed())).check(matches(not(withText(""))));
	}

	@After
	public void unregisterIdlingResource() {
		if (idlingResource != null)
			IdlingRegistry.getInstance().unregister(idlingResource);
	}
}
