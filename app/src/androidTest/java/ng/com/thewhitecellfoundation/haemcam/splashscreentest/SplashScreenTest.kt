package ng.com.thewhitecellfoundation.haemcam.splashscreentest

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.SplashScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<SplashScreen> =
        ActivityScenarioRule(SplashScreen::class.java)

    @Test
    fun testSplashScreenLayout() {
        Espresso.onView(ViewMatchers.withId(R.id.splash_screen_parent_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testSplashScreenBackground() {
        Espresso.onView(ViewMatchers.withId(R.id.splash_screen_parent_layout))
            .check(ViewAssertions.matches(EspressoTestsMatchers.withBackground(R.drawable.splash_screen_drawable_bg)))
    }
    @Test
    fun testActivityOnCreate() {
        val scenario = activityRule.scenario
        scenario.moveToState(Lifecycle.State.CREATED)
    }
}
