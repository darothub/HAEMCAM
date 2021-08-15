package ng.com.thewhitecellfoundation.haemcam.ui.home

import androidx.fragment.app.Fragment

abstract class HomeBaseFragment(layout: Int) : Fragment(layout) {
    open val buttonAndProgressBarState: ButtonAndProgressBarState by lazy {
        requireActivity() as ButtonAndProgressBarState
    }

    override fun onPause() {
        super.onPause()
        // Remove button
        buttonAndProgressBarState.buttonState("")
    }
}
