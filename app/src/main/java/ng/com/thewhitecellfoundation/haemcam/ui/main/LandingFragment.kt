package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import ng.com.thewhitecellfoundation.common.extensions.pressedEvent
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLandingBinding
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

class LandingFragment() : MainBaseFragment(R.layout.fragment_landing) {
    private var shortAnimationDuration: Int = 0
    val binding by viewBinding(FragmentLandingBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shortAnimationDuration = 2000

        binding.signUpBtn.setOnTouchListener { v, event ->
            v as Button
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressedEvent(v, R.color.primaryColor, getString(R.string.create_an_account))
                }
            }
            false
        }
        binding.loginBtn.setOnTouchListener { v, event ->
            v as Button
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressedEvent(v, R.color.primaryColor, getString(R.string.login_str))
                }
            }
            false
        }

        binding.loginBtn.setOnClickListener {
            navigator.goto(R.id.loginFragment)
        }
        binding.signUpBtn.setOnClickListener {
            navigator.goto(R.id.createAccountFragment)
        }

        binding.signUpBtn.crossFade()
        binding.loginBtn.crossFade()
    }

    private fun Button.crossFade() {
        this.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).duration = shortAnimationDuration.toLong()
        }
    }
}
