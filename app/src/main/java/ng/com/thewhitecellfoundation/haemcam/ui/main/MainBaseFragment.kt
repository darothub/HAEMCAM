package ng.com.thewhitecellfoundation.haemcam.ui.main

import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R

abstract class MainBaseFragment(layout: Int) : Fragment(layout) {
    open val backgroundDrawable: Int? = null
    open val backgroundColor: Int? = R.color.primaryColor
    lateinit var backgroundState: BackgroundState
    override fun onStart() {
        super.onStart()
        backgroundState = requireActivity() as BackgroundState
        backgroundColor?.let { backgroundState.changeBackgroundColor(it) }
        backgroundDrawable?.let { backgroundState.changeBackgroundDrawable(it) }
    }
}
