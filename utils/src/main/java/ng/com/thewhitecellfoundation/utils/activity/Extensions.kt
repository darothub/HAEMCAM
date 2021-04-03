package ng.com.thewhitecellfoundation.utils.activity

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager

fun Activity.hideSystemUI() {

    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )

}