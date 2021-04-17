package ng.com.thewhitecellfoundation.common.views

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.skydoves.powerspinner.PowerSpinnerView

@SuppressLint("ClickableViewAccessibility")
fun TextView.customOnDrawableRightListener(action: () -> Unit) {
    setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= right - totalPaddingRight) {
                action.invoke()
                true
            }
        }
        true
    }
}

fun pressedEvent(
    view: Button,
    color: Int,
    p: String
): Boolean {
    view.apply {
        background?.colorFilter = PorterDuffColorFilter(
            ContextCompat.getColor(
                view.context,
                color
            ),
            PorterDuff.Mode.SRC_IN
        )
        text = p
    }
    return true
}

/**
 * Hide view
 *
 */
fun View.hide(): Boolean {
    if (this.visibility == View.VISIBLE || this.visibility == View.INVISIBLE) {
        this.visibility = View.GONE
        return true
    }
    return false
}

/**
 * Show view
 *
 */
fun View.show(): Boolean {
    if (this.visibility == View.INVISIBLE || this.visibility == View.GONE) {
        this.visibility = View.VISIBLE
        return true
    }
    return false
}
/**
 * Invisible view
 *
 */
fun View.invisible(): Boolean {
    if (this.visibility == View.VISIBLE || this.visibility == View.GONE) {
        this.visibility = View.INVISIBLE
        return true
    }
    return false
}

fun dismissPowerViewDropDown(vararg views: PowerSpinnerView) {
    views.forEach {
        it.dismiss()
    }
}
