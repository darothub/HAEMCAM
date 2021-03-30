package ng.com.thewhitecellfoundation.utils.views

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.MotionEvent
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

