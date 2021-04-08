package ng.com.thewhitecellfoundation.common.fragment

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

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
