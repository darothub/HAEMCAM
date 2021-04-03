package ng.com.thewhitecellfoundation.haemcam.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import ng.com.thewhitecellfoundation.haemcam.R
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateAccountFragmentTest {

    @Before
    fun setup() {
        val scene =
            launchFragmentInContainer<CreateAccountFragment>(themeResId = R.style.Theme_FullScreen_Translucent)
        scene.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testThatWhen_CreateAccountFragment_Is_Launched_Root_Layout_Is_Displayed() {
        onView(withId(R.id.root_layout))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testThatWhen_CreateAccountFragment_Is_Launched_Title_Is_Displayed() {
        onView(
            Matchers.allOf(
                withId(R.id.title),
                withText(R.string.create_an_account)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))
    }
    @Test
    fun testThatWhen_CreateAccountFragment_Is_Launched_Form_Field_Are_Displayed() {
        onView(
            Matchers.allOf(
                withId(R.id.full_name_et),
                withHint(R.string.fullname)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))

        onView(
            Matchers.allOf(
                withId(R.id.email_address_et),
                withHint(R.string.email)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))

        onView(
            Matchers.allOf(
                withId(R.id.password_et),
                withHint(R.string.password)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))

        onView(
            Matchers.allOf(
                withId(R.id.confirm_password_et),
                withHint(R.string.confirm_password)
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))
    }
}
