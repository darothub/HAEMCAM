package ng.com.thewhitecellfoundation.haemcam.ui.main

import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.haemcam.R

abstract class MainBaseFragment(layout: Int) : Fragment(layout) {
    var backgroundDrawable: Int? = null
    var backgroundColor: Int? = R.color.primaryColor
    lateinit var backgroundState: BackgroundState
    init {
        setEachFragmentBackground()
    }
    override fun onStart() {
        super.onStart()
        backgroundState = requireActivity() as BackgroundState
        backgroundColor?.let { backgroundState.changeBackgroundColor(it) }
        backgroundDrawable?.let { backgroundState.changeBackgroundDrawable(it) }
    }

    fun setEachFragmentBackground() {
        when (this) {
            is LandingFragment, is WelcomeFragment -> {
                backgroundColor = R.color.primaryVariant
            }
            is LoginFragment -> {
                backgroundDrawable = R.drawable.whitecell_flower_drawable_bg
            }
            is CodeVerificationFragment -> {
                backgroundColor = R.color.primaryColor
            }
        }
    }
}
