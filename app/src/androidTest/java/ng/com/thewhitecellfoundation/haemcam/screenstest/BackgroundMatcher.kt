package ng.com.thewhitecellfoundation.haemcam.screenstest

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class BackgroundMatcher(private val drawable: Int) : TypeSafeMatcher<View>() {
    private var resourceName: String? = null
    companion object {
        const val EMPTY = -1
        const val ANY = -2
    }
    override fun matchesSafely(target: View?): Boolean {
        if (target !is ConstraintLayout) {
            return false
        }
        val layout: ConstraintLayout = target
        if (drawable === EMPTY) {
            return layout.background == null
        }
        if (drawable === ANY) {
            return layout.background != null
        }
        val context = target.context
        val resources: Resources = context.resources
        val expectedDrawable: Drawable? = ContextCompat.getDrawable(context, drawable)
        resourceName = resources.getResourceEntryName(drawable)

        if (expectedDrawable == null) {
            return false
        }

        val bitmap = getBitmap(layout.background)
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap?.sameAs(otherBitmap)!!
    }

    override fun describeTo(description: Description?) {
        description?.appendText("with drawable from resource id: ")
        description?.appendValue(drawable)
        if (resourceName != null) {
            description?.appendText("[")
            description?.appendText(resourceName)
            description?.appendText("]")
        }
    }
    private fun getBitmap(drawable: Drawable): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
