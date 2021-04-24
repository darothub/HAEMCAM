package ng.com.thewhitecellfoundation.common.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

fun Drawable.changeBackgroundColor(context: Context, color: Int) {
    this.colorFilter = PorterDuffColorFilter(
        ContextCompat.getColor(context, color),
        PorterDuff.Mode.SRC_IN
    )
}
