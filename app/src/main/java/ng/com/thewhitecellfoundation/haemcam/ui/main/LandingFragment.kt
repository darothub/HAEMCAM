package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.common.views.pressedEvent
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLandingBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

class LandingFragment : Fragment(R.layout.fragment_landing) {

    private val binding by viewBinding(FragmentLandingBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnTouchListener { v, event ->
            v as Button
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressedEvent(v, R.color.primary_color, getString(R.string.create_an_account))
                }
            }
            false
        }
        binding.loginBtn.setOnTouchListener { v, event ->
            v as Button
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressedEvent(v, R.color.primary_color, getString(R.string.login_str))
                }
            }
            false
        }

        binding.loginBtn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.loginFragment)
        }
        binding.signUpBtn.setOnClickListener {
            (requireActivity() as Navigator).goto(R.id.createAccountFragment)
        }
    }
}
