package ng.com.thewhitecellfoundation.utils.views

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat

@SuppressLint("ClickableViewAccessibility")
fun EditText.customOnTouchListener(action:()->Unit) {
    setOnTouchListener { _, event ->
        drawableRightClickListener(event, action)
    }
}
fun EditText.drawableRightClickListener(event: MotionEvent, action:()->Unit): Boolean {
    val DRAWABLE_RIGHT = 2
    if (event.action == MotionEvent.ACTION_UP) {
        if (event.rawX >= right - compoundDrawables[DRAWABLE_RIGHT].bounds.width()
        ) {
            action.invoke()
            return true
        }
    }
    return false
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
fun View.hide():Boolean{
    if(this.visibility == View.VISIBLE || this.visibility == View.INVISIBLE){
        this.visibility = View.GONE
        return false
    }
    return this.visibility == View.GONE
}

/**
 * Show view
 *
 */
fun View.show():Boolean{
    if(this.visibility == View.INVISIBLE || this.visibility == View.GONE){
        this.visibility = View.VISIBLE
        return false
    }
    return this.visibility == View.VISIBLE
}
/**
 * Invisible view
 *
 */
fun View.invisible():Boolean{
    if(this.visibility == View.VISIBLE || this.visibility == View.GONE){
        this.visibility = View.INVISIBLE
        return false
    }
    return this.visibility == View.INVISIBLE
}

