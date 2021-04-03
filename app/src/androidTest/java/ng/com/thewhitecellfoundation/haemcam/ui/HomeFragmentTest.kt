package ng.com.thewhitecellfoundation.haemcam.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @Before
    fun setup() {
        val scene =
            launchFragmentInContainer<HomeFragment>(themeResId = R.style.Theme_FullScreen_Translucent)
        scene.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testThatHomeFragment_Is_Displayed_When_Activity_Is_Launched() {
        Espresso.onView(withId(R.id.home_frag_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testHomeImage_Is_Displayed_When_HomeFragment_Is_Launched() {
        Espresso.onView(withId(R.id.home_fragment_iv))
            .check(matches(isDisplayed()))
    }
    @Test
    fun testLoginButton_Is_Displayed_When_HomeFragment_Is_Launched() {
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.login_btn),
                withText(R.string.login_str)
            )
        )
            .check(matches(isDisplayed()))
    }
    @Test
    fun testSignUpButton_Is_Displayed_When_HomeFragment_Is_Launched() {
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.sign_up_btn),
                withText(R.string.create_an_account)
            )
        )
            .check(matches(isDisplayed()))
    }
}
