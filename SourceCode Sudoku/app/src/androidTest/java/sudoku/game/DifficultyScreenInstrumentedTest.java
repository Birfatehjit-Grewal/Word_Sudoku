package sudoku.game;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;import org.junit.runner.RunWith;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DifficultyScreenInstrumentedTest {

    @Rule
    public ActivityScenarioRule<DifficultyScreen> activityRule =
            new ActivityScenarioRule(DifficultyScreen.class);

    @Test
    public void test_isViewDisplayed() {
        onView(withId(R.id.difficultyScreen)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isBeginnerDifficultyButtonDisplayed() {
        onView(withId(R.id.beginner)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isIntermediateDifficultyButtonDisplayed() {
        onView(withId(R.id.intermediate)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isAdvancedDifficultyButtonDisplayed() {
        onView(withId(R.id.advanced)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isCustomDifficultyButtonDisplayed() {
        onView(withId(R.id.custom)).check(matches(isDisplayed()));
    }

    @Test
    public void test_customButtonNavWorking() {
        onView(withId(R.id.custom))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.customScreen)).check(matches(isDisplayed()));
    }

    @Test
    public void test_isTitleTextCorrect() {
        onView(withId(R.id.homeScreenTitle)).check(matches(withText("Word Sudoku")));
    }

    @Test
    public void test_isSubTitleTextCorrect() {
        onView(withId(R.id.setDifficulty)).check(matches(withText("Set Difficulty")));
    }

    @Test
    public void test_beginnerButtonGoesToSudokuBoard() {
        onView(withId(R.id.beginner))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.gridSelection)).check(matches(isDisplayed()));
    }

    @Test
    public void test_intermediateButtonGoesToSudokuBoard() {
        onView(withId(R.id.intermediate))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.gridSelection)).check(matches(isDisplayed()));
    }

    @Test
    public void test_advancedButtonGoesToSudokuBoard() {
        onView(withId(R.id.advanced))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.gridSelection)).check(matches(isDisplayed()));
    }

}