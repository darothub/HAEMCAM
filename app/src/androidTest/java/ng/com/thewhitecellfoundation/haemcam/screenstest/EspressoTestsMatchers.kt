package ng.com.thewhitecellfoundation.haemcam.screenstest

import android.view.View
import ng.com.thewhitecellfoundation.haemcam.ui.DrawableMatcher
import org.hamcrest.Matcher

object EspressoTestsMatchers {
    fun <T> withBackground(resourceId: Int): Matcher<View> {
        return BackgroundMatcher<T>(resourceId)
    }

    fun <T> noBackground(): Matcher<View> {
        return BackgroundMatcher<T>(BackgroundMatcher.EMPTY)
    }

    fun <T> hasDrawable(resourceId: Int): Matcher<View> {
        return DrawableMatcher<T>(resourceId)
    }
}
