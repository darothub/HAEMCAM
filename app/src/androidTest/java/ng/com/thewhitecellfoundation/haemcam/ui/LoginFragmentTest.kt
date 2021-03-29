package ng.com.thewhitecellfoundation.haemcam.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testThatLoginFragment_Is_Displayed_When_Activity_Is_Launched() {
        Espresso.onView(withId(R.id.login_frag_root))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testLoginButton_Is_Displayed_When_LoginFragment_Is_Launched() {
        Espresso.onView(allOf(withId(R.id.login_btn), withText("Login")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun testEditText_Is_Displayed_When_LoginFragment_Is_Launched() {
        Espresso.onView(allOf(withId(R.id.email_address_et), withHint(R.string.email_address_str)))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}
