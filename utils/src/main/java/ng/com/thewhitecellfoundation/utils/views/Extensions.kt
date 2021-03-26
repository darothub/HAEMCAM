package ng.com.thewhitecellfoundation.utils.views

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.EditText

@SuppressLint("ClickableViewAccessibility")
fun EditText.customOnTouchListener(action:()->Unit) {
    setOnTouchListener { v, event ->
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