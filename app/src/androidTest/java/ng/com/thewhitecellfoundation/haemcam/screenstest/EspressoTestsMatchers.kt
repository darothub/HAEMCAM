package ng.com.thewhitecellfoundation.haemcam.screenstest

import android.view.View
import org.hamcrest.Matcher

object EspressoTestsMatchers {
    fun withBackground(resourceId: Int): Matcher<View> {
        return BackgroundMatcher(resourceId)
    }

    fun noBackground(): Matcher<View> {
        return BackgroundMatcher(BackgroundMatcher.EMPTY)
    }

    fun hasDrawable(): Matcher<View> {
        return BackgroundMatcher(BackgroundMatcher.ANY)
    }
}
