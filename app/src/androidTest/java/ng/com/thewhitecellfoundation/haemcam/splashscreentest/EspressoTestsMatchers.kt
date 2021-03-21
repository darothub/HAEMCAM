package ng.com.thewhitecellfoundation.haemcam.splashscreentest

import android.view.View
import ng.com.thewhitecellfoundation.haemcam.splashscreentest.BackgroundMatcher
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