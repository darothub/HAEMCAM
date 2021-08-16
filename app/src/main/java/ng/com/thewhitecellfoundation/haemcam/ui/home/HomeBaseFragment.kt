package ng.com.thewhitecellfoundation.haemcam.ui.home

import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R

abstract class HomeBaseFragment(layout: Int) : Fragment(layout) {
    open val buttonAndProgressBarState: ButtonAndProgressBarState by lazy {
        requireActivity() as ButtonAndProgressBarState
    }
    open val bottomNavigationVisibility: BottomNavigationVisibility by lazy {
        requireActivity() as BottomNavigationVisibility
    }
    open val toolBarSetup: ToolBarSetup by lazy {
        requireActivity() as ToolBarSetup
    }
    open val showBottomNavigation = true

    open val toolRightImageDrawable: Int? = R.drawable.ic_primary_help_24
    open val toolLeftImageDrawable: Int? = R.drawable.ic_person_24
    open val toolBarGreetingText: String? = null
    open val toolBarTitle: String? = null
    open val leftAction: () -> Unit = {}

    override fun onStart() {
        super.onStart()
        if (showBottomNavigation) bottomNavigationVisibility.showBottomNavigationView()
        else bottomNavigationVisibility.hideBottomNavigationView()
        buttonAndProgressBarState.buttonState("")
        with(toolBarSetup) {
            toolRightImageDrawable?.let { setRightImage(it) }
            toolLeftImageDrawable?.let { setLeftImage(it, leftAction) }
            setGreetingText(toolBarGreetingText)
            setToolBarTitle(title = toolBarTitle)
        }
    }

    override fun onPause() {
        super.onPause()
        // Remove button
        buttonAndProgressBarState.buttonState("")
        with(toolBarSetup) {
            setGreetingText("null")
            setToolBarTitle(title = "null")
        }
    }
    fun subHomeFragmentToolBarSetup(title: String) {
    }
}
