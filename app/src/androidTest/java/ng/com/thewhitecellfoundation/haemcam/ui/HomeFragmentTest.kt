package ng.com.thewhitecellfoundation.haemcam.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testThatHomeFragment_Is_Displayed_When_Activity_Is_Launched() {
        Espresso.onView(ViewMatchers.withId(R.id.home_frag_root))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testHomeImage_Is_Displayed_When_HomeFragment_Is_Launched() {
        Espresso.onView(ViewMatchers.withId(R.id.home_fragment_iv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testLoginButton_Is_Displayed_When_HomeFragment_Is_Launched() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_pbar),
                ViewMatchers.withText(R.string.login_str)
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testSignUpButton_Is_Displayed_When_HomeFragment_Is_Launched() {
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.sign_up_btn),
                ViewMatchers.withText(R.string.create_an_account)
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
