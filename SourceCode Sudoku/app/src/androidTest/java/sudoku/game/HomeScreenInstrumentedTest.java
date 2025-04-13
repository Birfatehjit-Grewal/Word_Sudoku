package sudoku.game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.widget.Button;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;import org.junit.runner.RunWith;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HomeScreenInstrumentedTest {

    @Rule
    public ActivityScenarioRule<HomeScreen> activityRule =
            new ActivityScenarioRule(HomeScreen.class);

    @Test
    public void test_isViewDisplayed() {
        onView(withId(R.id.homeScreen)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isPlayButtonDisplayed() {
        onView(withId(R.id.play)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isRulesButtonDisplayed() {
        onView(withId(R.id.rules)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isTitleTextCorrect() {
        onView(withId(R.id.homeScreenTitle)).check(matches(withText("Word Sudoku")));
    }

    @Test
    public void test_playButtonNavWorking() {
        onView(withId(R.id.play))
                .check(matches(isDisplayed()))
                .perform(click());
         onView(withId(R.id.gameMode)).check(matches(isDisplayed()));
    }

    @Test
    public void test_ruleButtonNavWorking() {
        onView(withId(R.id.rules))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.gameRules)).check(matches(isDisplayed()));
    }

}