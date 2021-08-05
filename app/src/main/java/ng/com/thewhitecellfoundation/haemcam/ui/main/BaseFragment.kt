package ng.com.thewhitecellfoundation.haemcam.ui.main

import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.ui.home.ButtonAndProgressBarState

abstract class BaseFragment(layout: Int) : Fragment(layout) {
    abstract val buttonAndProgressBarState: ButtonAndProgressBarState

    override fun onPause() {
        super.onPause()
        // Remove button
        buttonAndProgressBarState.buttonState("")
    }
}
