package ng.com.thewhitecellfoundation.common.fragment

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.activity.navigator
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

fun Fragment.onBackDispatcher(action: () -> Unit) {
    requireActivity().onBackPressedDispatcher?.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                action()
            }
        }
    )
}
val Fragment.navigator: Navigator get() = requireActivity().navigator
