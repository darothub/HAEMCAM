package ng.com.thewhitecellfoundation.haemcam.ui

import android.view.InputDevice
import android.view.MotionEvent
import android.widget.EditText
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.screenstest.EspressoTestsMatchers
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @Before
    fun setup() {
        val scene =
            launchFragmentInContainer<LoginFragment>(themeResId = R.style.Theme_FullScreen_Translucent)
        scene.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testThatLoginFragment_Is_Displayed_When_Activity_Is_Launched() {
        onView(withId(R.id.login_frag_root))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginButton_Is_Displayed_When_LoginFragment_Is_Launched() {
        onView(allOf(withId(R.id.login_btn), withText("Login")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testEmailEditText_Is_Displayed_When_LoginFragment_Is_Launched() {
        onView(allOf(withId(R.id.email_address_et), withHint(R.string.email_address_str)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testEmailEditText_Is_Displayed_Has_PhoneIcon_Drawable() {
        onView(withId(R.id.email_address_et))
            .check(matches(EspressoTestsMatchers.hasDrawable<EditText>(R.drawable.splash_screen_drawable_bg)))
    }

    @Test
    fun testPhoneEditText_Is_Displayed_PhoneIcon_Is_Clicked() {
        onView(withId(R.id.email_address_et))
            .perform(touchDrawableRight())
        // Check that the text was changed.
        onView(withId(R.id.login_phone_number_et))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testEmailEditText_Is_Displayed_EmailIcon_Is_Clicked() {
        onView(withId(R.id.email_address_et))
            .perform(typeText("sample@email.com"), closeSoftKeyboard(), touchDrawableRight())
        onView(withId(R.id.login_phone_number_et))
            .perform(typeText("08060098266"), closeSoftKeyboard(), touchDrawableRight())
        onView(withId(R.id.email_address_et))
            .check(matches(isDisplayed()))
    }
}

fun touchDrawableRight(): ViewAction {
    return actionWithAssertions(
        GeneralClickAction(
            Tap.SINGLE,
            GeneralLocation.BOTTOM_RIGHT,
            Press.FINGER,
            InputDevice.SOURCE_UNKNOWN,
            MotionEvent.BUTTON_PRIMARY
        )
    )
}
