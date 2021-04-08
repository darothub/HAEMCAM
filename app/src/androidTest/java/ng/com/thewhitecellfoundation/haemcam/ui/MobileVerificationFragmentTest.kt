package ng.com.thewhitecellfoundation.haemcam.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.ui.main.MobileVerificationFragment
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MobileVerificationFragmentTest {

    @Before
    fun setup() {
        val scene =
            launchFragmentInContainer<MobileVerificationFragment>(themeResId = R.style.Theme_FullScreen_Translucent)
        scene.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testThatWhen_MobileVerificationFragment_Is_Launched_Root_Layout_Is_Displayed() {
        Espresso.onView(withId(R.id.root_layout))
            .check(ViewAssertions.matches(isDisplayed()))
    }
    @Test
    fun testThatWhen_MobileVerificationFragment_Is_Launched_Title_Is_Displayed() {
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.title),
                withText(R.string.mobile_verification)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))
    }
    @Test
    fun testThatWhen_MobileVerificationFragment_Is_Launched_Form_Field_Are_Displayed() {
        Espresso.onView(
            Matchers.allOf(
                withId(R.id.phone_number_et),
                withHint(R.string.phone_number)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(
            withId(R.id.ccp)
        )
            .check(ViewAssertions.matches(isDisplayed()))
    }
}
