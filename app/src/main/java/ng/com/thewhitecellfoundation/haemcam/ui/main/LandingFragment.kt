package ng.com.thewhitecellfoundation.haemcam.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.drawable.changeBackgroundColor
import ng.com.thewhitecellfoundation.common.fragment.navigator
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.common.views.pressedEvent
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentLandingBinding
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

class LandingFragment : Fragment(R.layout.fragment_landing) {
    private var shortAnimationDuration: Int = 0
    private val binding by viewBinding(FragmentLandingBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        // Initially hide the content view.
//        binding.signUpBtn.visibility = View.GONE

        // Retrieve and cache the system's default "short" animation time.
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
            binding.loginBtn.background.changeBackgroundColor(requireContext(), R.color.primaryColor)
            (requireActivity() as Navigator).goto(R.id.loginFragment)
        }
        binding.signUpBtn.setOnClickListener {
            binding.signUpBtn.background.changeBackgroundColor(requireContext(), R.color.primaryColor)
            navigator.goto(R.id.createAccountFragment)
        }

        crossfade()
    }
    private fun crossfade() {
        binding.signUpBtn.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        binding.signUpBtn.background.apply {

                            animate()
                                .alpha(0.8f)
                                .setDuration(shortAnimationDuration.toLong())
                                .setListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator?) {
                                        binding.signUpBtn.background.changeBackgroundColor(requireContext(), R.color.primaryColor)
                                    }
                                })
                        }
                    }
                })
        }
        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)

        binding.loginBtn.apply {
            // Set the content view to 0% opacity but visible, so that it is visible
            // (but fully transparent) during the animation.
            alpha = 0f
            visibility = View.VISIBLE

            // Animate the content view to 100% opacity, and clear any animation
            // listener set on the view.
            animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        binding.loginBtn.background.apply {

                            animate()
                                .alpha(0.8f)
                                .setDuration(shortAnimationDuration.toLong())
                                .setListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator?) {
                                        binding.loginBtn.background.changeBackgroundColor(requireContext(), R.color.primaryColor)
                                    }
                                })
                        }
                    }
                })
        }

//        binding.loginBtn.animate()
//            .alpha(0f)
//            .setDuration(shortAnimationDuration.toLong())
//            .setListener(object : AnimatorListenerAdapter() {
//                override fun onAnimationEnd(animation: Animator) {
//                    binding.loginBtn.visibility = View.GONE
//                }
//            })
    }
}
