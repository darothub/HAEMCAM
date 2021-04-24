package ng.com.thewhitecellfoundation.navigation.navigator.extensions

import android.app.Activity
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

val Activity.navigator: Navigator get() = this as Navigator
