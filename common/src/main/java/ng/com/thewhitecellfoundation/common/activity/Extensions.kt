package ng.com.thewhitecellfoundation.common.activity

import android.app.Activity
import android.view.WindowManager

fun Activity.hideSystemUI() {

    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}
